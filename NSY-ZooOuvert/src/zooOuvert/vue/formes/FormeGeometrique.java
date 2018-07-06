package zooOuvert.vue.formes;

import processing.core.PApplet;

/**
 * Cette classe abstraite permettra de définir les objets déposables au sein du canvas.
 * 
 * @author vincent.hofman.auditeur@lecnam.net
 *
 */
abstract public class FormeGeometrique implements ImplDeposable{

	/**
	 * Objet PApplet appellant l'objet.
	 */
	private PApplet parent;
	/**
	 * Position en X de l'objet (son centre)
	 */
	private float posX;
	/**
	 * Position en Y de l'objet (son centre)
	 */
	private float posY;
	/**
	 * Position initiale en X de l'objet (son centre)
	 */
	private float posInitialeX;
	/**
	 * Position initiale en Y de l'objet (son centre)
	 */
	private float posInitialeY;
	/**
	 * Couleur que prendra la forme.
	 */
	private int couleurActuelle;
	/**
	 * Couleur du contour de la forme. 
	 */
	private int contourActuel;
	/**
	 * Couleur que prendra la forme au repos.
	 */
	private int couleurRepos;
	/**
	 * Couleur du contour de la forme survolée. 
	 */
	private int contourRepos;
	/**
	 * Couleur que prendra la forme au survol.
	 */
	private int couleurSurvol;
	/**
	 * Couleur du contour de la forme survolée. 
	 */
	private int contourSurvol;
	/**
	 * Couleur que prendra la forme saisie.
	 */
	private int couleurSaisie;
	/**
	 * Couleur du contour de la forme saisie. 
	 */
	private int contourSaisi;
	
	/**
	 * Type d'Element transporté.
	 * (Sert principalement à l'affichage)
	 */
	private EnumTypeForme typeElement;
	/**
	 * Choix entre Visiteur, Carnivore et Herbivore.
	 */
	public enum EnumTypeForme {VISITEUR, HERBIVORE, CARNIVORE, VIDE};
	
	
	/**
	 * Constructeur de la classe.
	 * @param p l'objet PApplet appellant cet instance.
	 * @parma le type de la forme en fonction de l'énumération
	 * @param posInitX Position initiale en X de l'objet (son centre)
	 * @param posInitYPosition initiale en Y de l'objet (son centre)
	 */
	public FormeGeometrique(PApplet p, EnumTypeForme type, float posInitX, float posInitY) {
		this.parent=p;
		this.typeElement=type;
		this.posInitialeX=posInitX;
		this.posInitialeY=posInitY;
		
		initialiserCouleurs();
		initialiser();
		
		
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
	 * @return the parent
	 */
	public PApplet getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(PApplet parent) {
		this.parent = parent;
	}

	/**
	 * @return the couleurActuelle
	 */
	public int getCouleurActuelle() {
		return couleurActuelle;
	}

	/**
	 * @param couleurActuelle the couleurActuelle to set
	 */
	public void setCouleurActuelle(int couleurActuelle) {
		this.couleurActuelle = couleurActuelle;
	}

	/**
	 * @return the contourActuel
	 */
	public int getContourActuel() {
		return contourActuel;
	}

	/**
	 * @param contourActuel the contourActuel to set
	 */
	public void setContourActuel(int contourActuel) {
		this.contourActuel = contourActuel;
	}
	
	/**
	 * @return the couleurRepos
	 */
	public int getCouleurRepos() {
		return couleurRepos;
	}


	/**
	 * @return the contourRepos
	 */
	public int getContourRepos() {
		return contourRepos;
	}

	/**
	 * @return the couleurSurvol
	 */
	public int getCouleurSurvol() {
		return couleurSurvol;
	}

	/**
	 * @return the contourSurvol
	 */
	public int getContourSurvol() {
		return contourSurvol;
	}

	
	/**
	 * @return the couleurSaisie
	 */
	public int getCouleurSaisie() {
		return couleurSaisie;
	}

	/**
	 * @return the contourSaisi
	 */
	public int getContourSaisi() {
		return contourSaisi;
	}

	/**
	 * @return the typeElement
	 */
	public EnumTypeForme getTypeElement() {
		return typeElement;
	}
	
	@Override
	public void initialiser() {
		couleurActuelle=couleurRepos;
		contourActuel=contourRepos;	
		setPosX(posInitialeX);
		setPosY(posInitialeY);
	}

	@Override
	public void survoler() {
		setCouleurActuelle(getCouleurSurvol());
		setContourActuel(getContourSurvol());
	}

	@Override
	public void finSurvoler() {
		setCouleurActuelle(getCouleurRepos());
		setContourActuel(getContourRepos());
	}

	@Override
	public void presser() {
		setCouleurActuelle(getCouleurSaisie());
		setContourActuel(getContourSaisi());
	}

	@Override
	public void relacher() {
		initialiser();
	}

	/**
	 * Permet de transformer le tableau de int des constantes en couleur PApplet.
	 */
	protected int toColor(int[] tabCouleurs) {
		if (tabCouleurs.length == 3 && testerCouleurs(tabCouleurs)) {
			return parent.color(tabCouleurs[0],tabCouleurs[1],tabCouleurs[2]);
		}else {
			return -1;
		}		
	}
	
	private boolean testerCouleurs(int[] couleurs) {
		for (int i = 0; i < couleurs.length; i++) {
			if (couleurs[i] <= 0 && couleurs[i] >= 255) {
				return false;
			}
		}
		return true;
	}
	
	private void initialiserCouleurs() {
		switch (typeElement) {
		case VISITEUR:
			setCouleurs(COL_ODEUR_VISIT);
			break;
		case CARNIVORE:
			setCouleurs(COL_ODEUR_CARNI);
			break;
		case HERBIVORE:
			setCouleurs(COL_ODEUR_HERBI);
			break;
		case VIDE:
			setCouleurs(COL_ODEUR_VIDE);
			break;
		}
	}
	private void setCouleurs(int[][] palette) {
		couleurRepos = parent.color(toColor(palette[0]));
		couleurSurvol = parent.color(toColor(palette[1]));
		couleurSaisie = parent.color(toColor(palette[2]));
		contourRepos = parent.color(toColor(palette[3]));
		contourSurvol = parent.color(toColor(palette[4]));
		contourSaisi = parent.color(toColor(palette[5]));
	}
}
