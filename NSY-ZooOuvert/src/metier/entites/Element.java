package metier.entites;


/**
 * Classe qui permet des créer des éléments qui seront affichés dans le canvas de l'écran.
 * Les éléments seront de type Visiteur, Carnivore ou Odorant.
 * 
 * @author vincent.hofman.auditeur@lecnam.net
 */
public abstract class Element{


	///////////////////////////////////////////////////////// Constantes de classe ////////////////////////////////////////////////////////////////////
	/**
	 * Pas de déplacement en X.
	 */
	private final float pasX = 1;
	/**
	 * Pas de déplacement en Y.
	 */
	private final float pasY = 1;
	
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
		positionPrecedente = new Position(positionActuelle.getPosX()-pasX, positionActuelle.getPosY()-pasY);
		
		//TODO Gerer le demi tour avec les bords du canvas.
		//Si la position actuelle est à droite de la position precedente, je continue vers la droite.
		if (positionActuelle.getPosX() > positionPrecedente.getPosX()) {
			positionActuelle.setPosX(positionActuelle.getPosX()+pasX);
		}else {
			positionActuelle.setPosX(positionActuelle.getPosX()-pasX);
		}
		//Si la position actuelle est au dessus de la position precedente, je continue vers le haut.
		if (positionActuelle.getPosY() > positionPrecedente.getPosY()) {
			positionActuelle.setPosY(positionActuelle.getPosY()-pasY);
		}else {
			positionActuelle.setPosY(positionActuelle.getPosY()+pasY);
		}
	}
	////////////////////////////////////////////////////////////Méthodes privées//////////////////////////////////////////////////////////////////////

}