package zooOuvert.transverses.DTO;


import zooOuvert.transverses.Positionnable;

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
	public static enum EnumTypeElement {VISITEUR,HERBIVORE,CARNIVORE,STIMULATEUR}
	/**
	 * Portée de l'odeur pour les odorants.
	 */
	private int porteeOdeur;
	/**
	 * Type d'odeur.
	 */
	private EnumTypeOdeur typeOdeur;
	public static enum EnumTypeOdeur {HERBIVORE,CARNIVORE,INERTE}
	
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
	/**
	 * @return the porteeOdeur
	 */
	public int getPorteeOdeur() {
		return porteeOdeur;
	}
	/**
	 * @param porteeOdeur the porteeOdeur to set
	 */
	public void setPorteeOdeur(int porteeOdeur) {
		this.porteeOdeur = porteeOdeur;
	}
	/**
	 * @return the typeOdeur
	 */
	public EnumTypeOdeur getTypeOdeur() {
		return typeOdeur;
	}
	/**
	 * @param typeOdeur the typeOdeur to set
	 */
	public void setTypeOdeur(EnumTypeOdeur typeOdeur) {
		this.typeOdeur = typeOdeur;
	}
	
	
	
/////////////////////////////////////////////////////////////Processing.//////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////Méthodes publiques.////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////Méthodes privées//////////////////////////////////////////////////////////////////////
	

}
