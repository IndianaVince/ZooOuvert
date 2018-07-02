package zooOuvert.metier.entites;
import zooOuvert.transverses.Positionnable;

/**
 * Classe qui permet des créer des éléments qui seront affichés dans le canvas de l'écran.
 * Les éléments seront de type Visiteur, Carnivore ou Odorant.
 * 
 * @author vincent.hofman.auditeur@lecnam.net
 */
public abstract class Element implements Positionnable{


	///////////////////////////////////////////////////////// Constantes de classe ////////////////////////////////////////////////////////////////////
	/**
	 * Pas de déplacement en X.
	 */
	private float pasX;
	/**
	 * Pas de déplacement en Y.
	 */
	private float pasY;
	/**
	 * Nombre de pas effectués.
	 */
	private float nombrePas = 0;
	
	///////////////////////////////////////////////////////// Attributs de classe ////////////////////////////////////////////////////////////////////
	/**
	 * Position actuelle de l'élément.
	 */
	private Position positionActuelle;
	/**
	 * Position précedente de l'élement.
	 */
	private Position positionPrecedente;
	////////////////////////////////////////////////////////////Constructeurs.////////////////////////////////////////////////////////////////////////
	

	/**
	 * Un élément ne peux être créé sans sa position.
	 */
	public Element(Position position) {
		this.positionActuelle = position;
		
		
	}	
	/////////////////////////////////////////////////////////////Accesseurs.//////////////////////////////////////////////////////////////////////////


	/**
	 * @return the position
	 */
	public Position getPositionActuelle() {
		return positionActuelle;
	}


	/**
	 * @param position the position to set
	 */
	public void setPositionActuelle(Position position) {
		this.positionActuelle = position;
	}
	
	
	///////////////////////////////////////////////////////////Méthodes publiques.////////////////////////////////////////////////////////////////////
	/**
	 * Méthode qui, à chaque demande de rafraichissement de la vue, permettra à l'élement de changer de position.
	 * Le deplacement se fait en fonction de la position précedente de l'élement.
	 * Si aucune position précedente, on part sdans une direction aléatoire.
	 */
	public void seDeplacer() {
		//Test avec position en haut à gauche de la position actuelle
		//TODO Créer une direction aleatoire si pas de position precedente.
		
		//Si aucune position precedente n'est enregistrée (cas du premier mouvement.), il faudra créer une position aléatoire autour de la position actuelle.
		if (positionPrecedente == null ){
			float posXPrecedente = positionActuelle.getPosX() + (-pasX + (int)(Math.random() * ((pasX - (-pasX)) +1)));
			float posYPrecedente = positionActuelle.getPosY() + (-pasX + (int)(Math.random() * ((pasX - (-pasX)) +1)));
			positionPrecedente = new Position(posXPrecedente, posYPrecedente);
		}
		
		//Ajustement des trajectoires tous les  20 pas pour que ce soit moins monotone.
		if (nombrePas%20 == 0) {
			pasX = -1 + (int)(Math.random() * ((1 - (-1)) +1));
			pasY = -1 + (int)(Math.random() * ((1 - (-1)) +1));
		}
		
		
		//Gestion des collisions
		if ((positionActuelle.getPosX() + pasX < POSX_CANVAS || positionActuelle.getPosX() + pasX > POSX_CANVAS + LARGEUR_CANVAS)) {
			pasX = -pasX;
		}
		if ((positionActuelle.getPosY() + pasY < POSY_CANVAS || positionActuelle.getPosY() + pasY > POSY_CANVAS + HAUTEUR_CANVAS)) {
			pasY = -pasY;
		}	
		
		//Déplacement
		positionActuelle.setPosX(positionActuelle.getPosX()+pasX);
		positionActuelle.setPosY(positionActuelle.getPosY()+pasY);
		
		nombrePas++;
	}
	////////////////////////////////////////////////////////////Méthodes privées//////////////////////////////////////////////////////////////////////

}