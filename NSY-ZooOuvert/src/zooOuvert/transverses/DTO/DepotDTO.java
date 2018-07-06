package zooOuvert.transverses.DTO;

import zooOuvert.vue.formes.Ajout;
import zooOuvert.vue.formes.FormeGeometrique;
import zooOuvert.vue.formes.Odeur;
import zooOuvert.vue.formes.Retrait;

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
	private EnumTypeElement typeElement;
	/**
	 * Choix entre Visiteur, Carnivore et Herbivore.
	 */
	public enum EnumTypeElement {VISITEUR, HERBIVORE, CARNIVORE, VIDE};
	/**
	 * Type d'Element transporté.
	 * (Sert principalement à l'action à réaliser)
	 */
	private EnumTypeAction typeAction;
	/**
	 * Choix entre Odeur et Ajout.
	 */
	public enum EnumTypeAction {ODEUR, AJOUT, RETRAIT};
	
	
	public DepotDTO(FormeGeometrique forme) {
		this.posX = forme.getPosX();
		this.posY = forme.getPosY();

		if (forme.getTypeElement() == FormeGeometrique.EnumTypeForme.HERBIVORE) {typeElement = EnumTypeElement.HERBIVORE;}
		if (forme.getTypeElement() == FormeGeometrique.EnumTypeForme.CARNIVORE) {typeElement = EnumTypeElement.CARNIVORE;}
		if (forme.getTypeElement() == FormeGeometrique.EnumTypeForme.VISITEUR) {typeElement = EnumTypeElement.VISITEUR;}
		if (forme.getTypeElement() == FormeGeometrique.EnumTypeForme.VIDE) {typeElement = EnumTypeElement.VIDE;}
		

		if (forme instanceof Odeur) {typeAction = EnumTypeAction.ODEUR;}
		if (forme instanceof Ajout) {typeAction = EnumTypeAction.AJOUT;}
		if (forme instanceof Retrait) {typeAction = EnumTypeAction.RETRAIT;}
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
	public EnumTypeElement getTypeElement() {
		return typeElement;
	}

	/**
	 * @return the typeAction
	 */
	public EnumTypeAction getTypeAction() {
		return typeAction;
	}
	
	
	
	
	
	
	
	
	
	
	
}
