package zooOuvert.vue.formes;

import processing.core.PApplet;

/**
 * Cette classe permet de representer des triangles equilateraux.
 * 
 * @author vincent.hofman.auditeur@lecnam.net
 *
 */
public abstract class TriangleEqui extends FormeGeometrique {

	/**
	 * Les coordonnées en X des points du triangle.
	 */
	private float posX1, posX2, posX3;
	/**
	 * Les coordonnées en Y des points du triangle.
	 */
	private float posY1, posY2, posY3;
	/**
	 * hauteur du triangle.
	 */
	private int hauteur;
	
	
	
	public TriangleEqui(PApplet p, EnumTypeForme type, float posInitX, float posInitY, int hauteur) {
		super(p,type, posInitX, posInitY);
		this.hauteur=hauteur;
		miseAJourPoints(posInitX, posInitY, hauteur);
	}


	@Override
	public void afficher() {
		getParent().triangle(posX1, posY1, posX2, posY2, posX3, posY3);
	}
	
	@Override
	public boolean testerSurvol(float mX, float mY) {
		//TODO A des fins pedagogiques, je vais tester si la souris est au dessus d'un carré contenant le rectangle en esperant trouver un algo qui colle au triangle par la suite.
		float cote = 3*hauteur/PApplet.sqrt(3);
		if(mX >= (this.getPosX()-cote/2) && mX <= (this.getPosX()+ cote/2) && ( mY >= this.getPosY()-cote/2) && mY <= (this.getPosY()+ cote/2)) {
			return true;
		}
		return false;
	}
	
	@Override
	public void setPosX(float posX) {
		// TODO Auto-generated method stub
		super.setPosX(posX);
		miseAJourPoints(posX, this.getPosY(), hauteur);
	}
	
	@Override
	public void setPosY(float posY) {
		// TODO Auto-generated method stub
		super.setPosY(posY);
		miseAJourPoints(this.getPosX(), posY, hauteur);
	}
	
	
	
	/**
	 * Méthode qui permettra de mettre à jour les points du triangle.
	 * C'est que du pythagore :-)
	 * @param posInitX centre en X du triangle
	 * @param posInitY centre en Y du triangle
	 * @param hauteur hauteur du triangle.
	 */
	private void miseAJourPoints(float posInitX, float posInitY, int hauteur) {
		posX1 = posInitX;
		posY1 = posInitY - (2*hauteur/3);
		posX2 = posInitX + PApplet.sqrt(PApplet.sq(2*hauteur/3));
		posY2 = posInitY + hauteur/3;
		posX3 = posInitX - PApplet.sqrt(PApplet.sq(2*hauteur/3) - PApplet.sq(hauteur/3));
		posY3 = posInitY + hauteur/3;
	}

}
