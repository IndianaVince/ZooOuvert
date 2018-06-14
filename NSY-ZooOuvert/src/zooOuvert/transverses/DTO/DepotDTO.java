package zooOuvert.transverses.DTO;

import zooOuvert.vue.formes.FormeGeometrique;
import zooOuvert.vue.formes.Odeur;

public class DepotDTO {

	/**
	 * Position en X de l'objet (son centre)
	 */
	private float posX;
	/**
	 * Position en Y de l'objet (son centre)
	 */
	private float posY;
	
	/**
	 * Type d'Element transporté.
	 * (Sert principalement à l'affichage de la couleur)
	 */
	private EnumTypeForme typeElement;
	/**
	 * Choix entre Visiteur, Carnivore et Herbivore.
	 */
	public enum EnumTypeForme {VISITEUR, HERBIVORE, CARNIVORE};
	/**
	 * Type d'Element transporté.
	 * (Sert principalement à l'action à réaliser)
	 */
	private EnumTypeAction typeAction;
	/**
	 * Choix entre Visiteur, Carnivore et Herbivore.
	 */
	public enum EnumTypeAction {ODEUR, AJOUT};
	
	
	public DepotDTO(FormeGeometrique forme) {
		this.posX = forme.getPosX();
		this.posY = forme.getPosY();

		if (forme.getTypeElement() == FormeGeometrique.EnumTypeForme.HERBIVORE) {typeElement = EnumTypeForme.HERBIVORE;}
		if (forme.getTypeElement() == FormeGeometrique.EnumTypeForme.CARNIVORE) {typeElement = EnumTypeForme.CARNIVORE;}
		if (forme.getTypeElement() == FormeGeometrique.EnumTypeForme.VISITEUR) {typeElement = EnumTypeForme.VISITEUR;}
		

		if (forme instanceof Odeur) {typeAction = EnumTypeAction.ODEUR;}
		//TODO Gérer les ajouts
		//if (forme instanceof Ajout) {typeAction = EnumTypeAction.AJOUT;}
	}


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
	public EnumTypeForme getTypeElement() {
		return typeElement;
	}

	/**
	 * @return the typeAction
	 */
	public EnumTypeAction getTypeAction() {
		return typeAction;
	}
	
	
	
	
	
	
	
	
	
	
	
}
