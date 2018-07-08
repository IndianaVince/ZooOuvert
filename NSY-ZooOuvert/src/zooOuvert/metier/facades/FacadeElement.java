package zooOuvert.metier.facades;

import java.util.ArrayList;
import java.util.Iterator;
import zooOuvert.metier.entites.Carnivore;
import zooOuvert.metier.entites.Element;
import zooOuvert.metier.entites.Herbivore;
import zooOuvert.metier.entites.Position;
import zooOuvert.metier.entites.Stimulateur;
import zooOuvert.metier.entites.Visiteur;
import zooOuvert.transverses.Positionnable;
import zooOuvert.transverses.DTO.DepotDTO;
import zooOuvert.transverses.DTO.ElementDTO;
import zooOuvert.transverses.DTO.ElementDTO.EnumTypeElement;

/**
 * Facade de la classe métier Element.
 * @author vincent.hofman.auditeur@lecnam.net
 *
 */
public class FacadeElement implements Positionnable{

	
	
	///////////////////////////////////////////////////////// Attributs de classe ////////////////////////////////////////////////////////////////////

	/**
	 * Constante determinant le nombre d'élémnents d'un même type à crééer au départ.
	 */
	public final int NOMBRE_ELEMENT = 10;
	
	/**
	 * Seule et unique instance de la facade.
	 */
	private static FacadeElement instance;
	/**
	 * Liste des Elements présents dans le parc.
	 */
	private ArrayList<Element> listeElements;
////////////////////////////////////////////////////////////Constructeurs.////////////////////////////////////////////////////////////////////////
	
	/**
	 * Constructeur de la classe.
	 */
	public FacadeElement() {
		listeElements = new ArrayList<>();
	}
	
	
/////////////////////////////////////////////////////////////Accesseurs.//////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////Méthodes publiques.////////////////////////////////////////////////////////////////////
	
	/**
	 * Permet de donner l'instance static de la facade (Pas la peine d'en créer une à chaque fois).
	 * Si aucune instance n'éxiste, une nouvelle est crée.
	 */
	public static FacadeElement getInstance() {
		if (instance == null) {
			instance = new FacadeElement();
			System.out.println("Création d'une nouvelle instance de FacadeElement.");
		}
		return instance;
	}
	
	/**
	 * Méthode qui permettra de livrer les éléments.
	 * Si la liste est vide, il y a de fortes chances que ce soit la première fois qu'elle soit appellée.
	 * Il faudra donc créer les premiers élements
	 * On nourrit la liste que sera envoyée sous forme de DTO.
	 * @return liste de DTO
	 */
	public ArrayList<ElementDTO> getElements(){
		
		if (listeElements.isEmpty()) {		
			//Création des éléments initiaux
			listeElements.addAll(creerElements(NOMBRE_ELEMENT, 'v'));
			listeElements.addAll(creerElements(NOMBRE_ELEMENT, 'h'));
			listeElements.addAll(creerElements(NOMBRE_ELEMENT, 'c'));
			listeElements.addAll(creerElements(NOMBRE_ELEMENT, 's'));
		}else {
			//Tout le monde bouge !
			for (Iterator<Element> iterator = listeElements.iterator(); iterator.hasNext();) {
				Element element = (Element) iterator.next();
				element.seDeplacer();
			}
		}
		
		//mettreAJourElements();
		
		return toDTO(listeElements);
	}
	
	/**
	 * Méthode qui permettra de gerer le comportement en cas de relache d'une odeur ou d'un ajout sur le canvas.
	 * @param objetRelache objet déposer sur le canvas
	 * @param posX de l'objet
	 * @param posY de l'objet
	 */
	/*
	 * Il faut d'abord tester si l'objet relaché est une odeur ou un ajout et, en focntion du type, on fera ce qu'il faut.
	 */
	public void envoyerDepot(DepotDTO depot) {
		if (depot.getTypeAction() == DepotDTO.EnumTypeAction.ODEUR) {traiterDepotOdeur(depot); }
		if (depot.getTypeAction() == DepotDTO.EnumTypeAction.AJOUT) {traiterDepotAjout(depot); }
		if (depot.getTypeAction() == DepotDTO.EnumTypeAction.RETRAIT) {traiterDepotRetrait(depot); }
	}
	/**
	 * Méthode qui devra permettre de récupérer l'indice de staisfaction global des visiteurs.
	 * @return l'indice entre 0 et 1
	 */
	public float getSatisfaction() {
		
		int nbVisiteur=0;
		int sommeSatisfaction=0;
		for (Iterator<Element> iterator = listeElements.iterator(); iterator.hasNext();) {
			Element element = (Element) iterator.next();
			
			if (element instanceof Visiteur) {
				sommeSatisfaction += ((Visiteur) element).getIndiceSatisfaction();
				nbVisiteur++;
			}
			
		} 
		
		return (sommeSatisfaction / nbVisiteur);
	}
////////////////////////////////////////////////////////////Méthodes privées//////////////////////////////////////////////////////////////////////
	
	/**
	 * Meéthode qui devra, à partir des constantes trouvées dans Positionnable, calculer la position de chaque stimulateur sur le canvas.
	 * @return la liste des positions des stimulateurs.
	 */
	private ArrayList<Position> calculPositionsStimulateurs(){
		ArrayList<Position> laListe = new ArrayList<>();
		//Je dois calculer la position du premier element puis déduire la position des autres tout en restant dans le canvas.
		
		
		float posXInitiale = (POSX_CANVAS + LARGEUR_CANVAS)/(NB_ZONES_X);
		float posYInitiale = (POSY_CANVAS + HAUTEUR_CANVAS)/(NB_ZONES_Y);
			
		int compteX=0;
		while (compteX < NB_ZONES_X) {
			
			int compteY=0;
			//Le premier de la colonne
			//Si liste vide, c'est le premier en haut à gauche
			if (laListe.isEmpty()) {
				laListe.add(new Position(posXInitiale, posYInitiale));
			}else {
				//C'est le premier de la colonne suivante.
				laListe.add(new Position(laListe.get(laListe.size()-1).getPosX() +  + (LARGEUR_CANVAS/NB_ZONES_X), posYInitiale));
			}
			while(compteY < NB_ZONES_Y -1) {
				
				//Les suivants de la colonne
				laListe.add(new Position(
						laListe.get(laListe.size()-1).getPosX(), 
						laListe.get(laListe.size()-1).getPosY() + HAUTEUR_CANVAS/NB_ZONES_Y));
				compteY++;
			}
			
			compteX++;
		}
		return laListe;
	}
	
	/**
	 * Méthode permettant de créer une ensemble d'élements de même nature.
	 * @param la quantité d'éléments à retourner
	 * @param le tyme d'éléments à retourner sous forme de char ('v', 'h', 'c' ou 's')
	 * @return une liste d'éléménts
	 */
	private ArrayList<Element> creerElements(int quantite, char type){
		ArrayList<Element> laListe = new ArrayList<>();
		
		//Petite particularité pour les stimulateurs qui sont fixes.
		if (type == 's') {
			ArrayList<Position> listePositionStimulateurs = calculPositionsStimulateurs();
			for (Iterator<Position> iterator = listePositionStimulateurs.iterator(); iterator.hasNext();) {
				laListe.add(fabriquerElement(type, (Position) iterator.next()));
				
			}
		} else {
			for (int i = 0; i < quantite; i++) { 
				float posX = POSX_CANVAS + (float)(Math.random() * (((POSX_CANVAS + LARGEUR_CANVAS) - POSX_CANVAS) + 1));
				float posY = POSY_CANVAS + (float)(Math.random() * (((POSY_CANVAS + HAUTEUR_CANVAS) - POSY_CANVAS) + 1));
				laListe.add(fabriquerElement(type, new Position(posX, posY)));
			}		
		}
		return laListe;
	}
	
	/**
	 * Méthode qui devra fabriquer un ou plusieurs élements concrets (visiteur, herbivore, carnivore ou stimulateur).
	 * @param character representant la commande d'élement : "v", "h", "c" ou "s".
	 * @return un élement créé.
	 */
	private Element fabriquerElement(char commande, Position position) {
		
		switch (commande) {
			case 'v':
				return new Visiteur(position);	
			case 'h':
				return new Herbivore(position);
			case 'c':
				return new Carnivore(position);
			case 's':
				return new Stimulateur(position);
			default:
				return null;
		}		
	}
	
	/**
	 * Permet la transformation de la liste des élements à afficher en DTO.
	 * Pour rappel, le DTO n'a que les infos de l'élément et son appartenance à un type (donné par la classe fille.)
	 * 
	 * @param une liste d'élements
	 * @return la liste des elements sous forme de DTO.
	 */
	private ArrayList<ElementDTO> toDTO(ArrayList<Element> liste){
		
		ArrayList<ElementDTO> listeDTO = new ArrayList<>();
		for (Iterator<Element> iterator = listeElements.iterator(); iterator.hasNext();) {
			Element element = iterator.next();
			
			//Création de l'élement générique
			ElementDTO e = new ElementDTO();
			e.setPosX(element.getPositionActuelle().getPosX());
			e.setPosY(element.getPositionActuelle().getPosY());
			
			//System.out.println("toDTO : "+element.getClass()+" trouvé.");	
			//Saisie de la particularité en fonction du type réellement instancié.
			if (element instanceof Visiteur) {e.setTypeElement(EnumTypeElement.VISITEUR);} 
			if (element instanceof Herbivore) {
				e.setTypeElement(EnumTypeElement.HERBIVORE);
				e.setPorteeOdeur(((Herbivore) element).getPortee());
				} 
			if (element instanceof Carnivore) {e.setTypeElement(EnumTypeElement.CARNIVORE);} 
			
			if (element instanceof Stimulateur) {
				e.setTypeElement(EnumTypeElement.STIMULATEUR);
				e.setPorteeOdeur(((Stimulateur) element).getPortee());
				if (((Stimulateur) element).getTypeDiffusion() == Stimulateur.TypeDiffusion.CARNIVORE) {
					e.setTypeOdeur(ElementDTO.EnumTypeOdeur.CARNIVORE);
				} else if (((Stimulateur) element).getTypeDiffusion() == Stimulateur.TypeDiffusion.HERBIVORE) {
					e.setTypeOdeur(ElementDTO.EnumTypeOdeur.HERBIVORE);
				} else {
					e.setTypeOdeur(ElementDTO.EnumTypeOdeur.INERTE);
				}
			} 
			
		
			listeDTO.add(e);
		}
		return listeDTO;
	}
	
	/**
	 * Gère un depot d'odeur après relachement du bouton de la souris.
	 * @param depot d'odeur sous forme DTO.
	 */
	private void traiterDepotOdeur(DepotDTO depot) {
		/*
		 * Pour chaque stimulateur de la liste, je vais calculer la distance entre les coord du depot et celles de l'élement.
		 * Si la distance est inferieur à la distance minimale (30), c'est qu'on est dedans.
		 */
		for (Iterator<Element> iterator = listeElements.iterator(); iterator.hasNext();) {
			Element element = (Element) iterator.next();
			
			if (element instanceof Stimulateur) {
				double distanceReelle = Math.sqrt(
										Math.pow(element.getPositionActuelle().getPosX() - depot.getPosX(), 2) + 
										Math.pow(element.getPositionActuelle().getPosY() - depot.getPosY(), 2)
										);
				//System.out.println("distance réelle : "+distanceReelle);
				if (distanceReelle<=30) {
					((Stimulateur) element).diffuser(depot);
				}
			}
			
		}
	}
	
	/**
	 * Gère un depot d'ajout d'élément après relachement du bouton de la souris.
	 * @param depot d'ajout sous forme DTO.
	 */
	private void traiterDepotAjout(DepotDTO depot) {
		DTOToElement(depot);
	}
	
	private void DTOToElement(DepotDTO depot) {
		Element e = null;
		switch (depot.getTypeElement()) {
			case VISITEUR:
				e = new Visiteur(new Position(depot.getPosX(), depot.getPosY()));
				break;
			case HERBIVORE:
				e = new Herbivore(new Position(depot.getPosX(), depot.getPosY()));
				break;
			case CARNIVORE:
				e = new Carnivore(new Position(depot.getPosX(), depot.getPosY()));
				break;
			case VIDE:
				break;
		}
		if (e!=null) {
			listeElements.add(e);
		}else {
			System.out.println("Probleme à l'ajout d'un élément");
		}
		
	}
	
	/**
	 * Gère un depot de type retrait qui devra supprimer un ou plusieurs élément après relachement du bouton de la souris.
	 * @param depot d'ajout sous forme DTO.
	 */
	private void traiterDepotRetrait(DepotDTO depot) {
		//Les éléments à supprimer pour eviter de modifier la liste pendant que je le parcoure.
		ArrayList<Element> aSupprimer = new ArrayList<>();
		
		//Etape 1 : On identifie les éléments à sacrifier
		for (Iterator<Element> iterator = listeElements.iterator(); iterator.hasNext();) {
			Element element = (Element) iterator.next();
			
			if (!(element instanceof Stimulateur)) {
				double distanceReelle = Math.sqrt(
										Math.pow(element.getPositionActuelle().getPosX() - depot.getPosX(), 2) + 
										Math.pow(element.getPositionActuelle().getPosY() - depot.getPosY(), 2)
										);
				//System.out.println("distance réelle : "+distanceReelle);
				if (distanceReelle<=10) {
					aSupprimer.add(element);
				}
			}			
		}
		//Etape 2 : Couic les élements !
		listeElements.removeAll(aSupprimer);
		
	}
}
