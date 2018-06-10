package metier.entites;


/**
 * Classe qui permet des créer des éléments qui seront affichés dans le canvas de l'écran.
 * Les éléments seront de type Visiteur, Carnivore ou Odorant.
 * 
 * @author vincent.hofman.auditeur@lecnam.net
 */
public abstract class Element{

	///////////////////////////////////////////////////////// Attributs de classe ////////////////////////////////////////////////////////////////////
	/**
	 * Position actuelle de l'élément.
	 */
	private Position position;
	////////////////////////////////////////////////////////////Constructeurs.////////////////////////////////////////////////////////////////////////
	

	/**
	 * Un élément ne peux être créé sans sa position.
	 */
	public Element(Position position) {
		this.position = position;
	}	
	/////////////////////////////////////////////////////////////Accesseurs.//////////////////////////////////////////////////////////////////////////


	/**
	 * @return the position
	 */
	public Position getPosition() {
		return position;
	}


	/**
	 * @param position the position to set
	 */
	public void setPosition(Position position) {
		this.position = position;
	}
	
	
	/////////////////////////////////////////////////////////////Processing.//////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////Méthodes publiques.////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////Méthodes privées//////////////////////////////////////////////////////////////////////

}