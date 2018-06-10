package metier.facades;

import java.util.ArrayList;
import java.util.Iterator;
import metier.entites.Carnivore;
import metier.entites.Element;
import metier.entites.Herbivore;
import metier.entites.Position;
import metier.entites.Stimulateur;
import metier.entites.Visiteur;
import transverses.Positionnable;
import transverses.DTO.ElementDTO;
import transverses.DTO.ElementDTO.EnumTypeElement;

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
		}
		
		//mettreAJourElements();
		
		return toDTO(listeElements);
	}
////////////////////////////////////////////////////////////Méthodes privées//////////////////////////////////////////////////////////////////////
	
	/**
	 * Meéthode qui devra, à partir des constantes trouvées dans Positionnable, calculer la position de chaque stimulateur sur le canvas.
	 * @return
	 */
	private ArrayList<Position> calculPositionsStimulateurs(){
		ArrayList<Position> laListe = new ArrayList<>();
		//Je dois calculer la position du premier element puis déduire la position des autres tout en restant dans le canvas.
		
		//Le premier
		laListe.add(new Position(
				(POSX_CANVAS + LARGEUR_CANVAS)/(NB_ZONES_X), 
				(POSY_CANVAS + HAUTEUR_CANVAS)/(NB_ZONES_Y)));
		//Et les autres
			
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
			e.setPosX(element.getPosition().getPosX());
			e.setPosY(element.getPosition().getPosY());
			
			//System.out.println("toDTO : "+element.getClass()+" trouvé.");	
			//Saisie de la particularité en fonction du type réellement instancié.
			if (element instanceof Visiteur) {e.setTypeElement(EnumTypeElement.VISITEUR);} 
			if (element instanceof Herbivore) {e.setTypeElement(EnumTypeElement.HERBIVORE);} 
			if (element instanceof Carnivore) {e.setTypeElement(EnumTypeElement.CARNIVORE);} 
			if (element instanceof Stimulateur) {e.setTypeElement(EnumTypeElement.STIMULATEUR);} 
			
		
			listeDTO.add(e);
		}
		return listeDTO;
	}
}
