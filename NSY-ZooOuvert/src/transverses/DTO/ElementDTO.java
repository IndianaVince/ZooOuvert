package transverses.DTO;


import transverses.Positionnable;

/**
 * Coquille qui servira au transport d'éléments entre les couches applicatives.
 * @author vincent.hofman.auditeur@lecnam.net
 *
 */
public class ElementDTO implements Positionnable{

	
///////////////////////////////////////////////////////// Attributs de classe ////////////////////////////////////////////////////////////////////
	/**
	 * Position en X.
	 */
	private float posX;
	/**
	 * Position en Y.
	 */
	private float posY;
	/**
	 * Type d'Element transporté.
	 * (Sert principalement à l'affichage)
	 */
	private EnumTypeElement typeElement;
	public static enum EnumTypeElement {VISITEUR, HERBIVORE, CARNIVORE, STIMULATEUR}
	
////////////////////////////////////////////////////////////Constructeurs.////////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////Accesseurs.//////////////////////////////////////////////////////////////////////////
	/**
	 * @return the posX
	 */
	public float getPosX() {
		return posX;
	}
	/**
	 * @param posX the posX to set
	 */
	public void setPosX(float posX) {
		this.posX = posX;
	}
	/**
	 * @return the posY
	 */
	public float getPosY() {
		return posY;
	}
	/**
	 * @param posY the posY to set
	 */
	public void setPosY(float posY) {
		this.posY = posY;
	}
	/**
	 * @return the typeElement
	 */
	public EnumTypeElement getTypeElement() {
		return typeElement;
	}
	/**
	 * @param typeElement the typeElement to set
	 */
	public void setTypeElement(EnumTypeElement type) {
		this.typeElement = type;
	}
/////////////////////////////////////////////////////////////Processing.//////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////Méthodes publiques.////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////Méthodes privées//////////////////////////////////////////////////////////////////////
	

}
