package zooOuvert.metier.entites;
import zooOuvert.transverses.Positionnable;

/**
 * Position d'un élément dans le canvas.
 * @author vincent.hofman.auditeur@lecnam.net
 *
 */
public class Position implements Positionnable{

	/**
	 * Position en X.
	 */
	private float posX;
	/**
	 * Position en Y.
	 */
	private float posY;
	
	/**
	 * Constructeur de la classe.
	 * @param x : la position en x;
	 * @param y : la position en y;
	 */
	public Position(float x, float y) {
		setPosX(x);
		setPosY(y);
	}

	
	/**
	 * Retourne la position en X.
	 * @return the posX
	 */
	public float getPosX() {
		return posX;
	}

	/**
	 * Retourne la position en Y.
	 * @return the posY
	 */
	public float getPosY() {
		return posY;
	}

	/**
	 * Affecte une valeur à la postion en X.
	 * Ne peut pas être à l'exterieur du canvas ; ne change pas la valeur dans le cas contraire.
	 * @param posX the posX to set
	 */
	public void setPosX(float posX) {
		if (posX < POSX_CANVAS || posX > POSX_CANVAS + LARGEUR_CANVAS) {
			//System.err.println("tentative de sortie du canvas.");
		} else {
			this.posX = posX;
		}
	}

	/**
	 * Affecte une valeur à la postion en Y.
	 * Ne peut pas être à l'exterieur du canvas ; ne change pas la valeur dans le cas contraire.
	 * @param posY the posY to set
	 */
	public void setPosY(float posY) {
		if (posY < POSY_CANVAS || posY > POSY_CANVAS + HAUTEUR_CANVAS) {
			//System.err.println("tentative de sortie du canvas.");
		} else {
			this.posY = posY;
		}
	}
	
	
	
}
