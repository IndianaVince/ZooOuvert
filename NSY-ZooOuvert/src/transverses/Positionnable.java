package transverses;

import processing.core.PApplet;

/**
 * Cette interface contiendra toutes les constantes et signatures de méthodes ayant attrait à la position.
 * @author vincent.hofman.auditeur@lecnam.fr
 *
 */
public interface Positionnable {

	/**
	 * Point d'origine du canvas en X.
	 */
	public final int POSX_CANVAS = 65;
	/**
	 * Point d'origine du canvas en Y.
	 */
	public final int POSY_CANVAS = 100;
	/**
	 * Largeur du canvas.
	 */
	public final int LARGEUR_CANVAS = 800;
	/**
	 * hauteur du canvas.
	 */
	public final int HAUTEUR_CANVAS = 600;
	/**
	 * Nombre de découpe de la zone en X;
	 */	
	public final int NB_ZONES_X = 6;
	/**
	 * Nombre de découpe de la zone en Y;
	 */	
	public final int NB_ZONES_Y = 4;
	
	
}
