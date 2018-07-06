package zooOuvert.vue.formes;

/**
 * Cet interface permettra d'imposer un comportement par défaut à tous les objets qui pourront être glissé/déposé.
 * 
 * @author vincent.hofman.auditeur@lecnam.net
 *
 */
public interface ImplDeposable {

	/**
	 * Tableau de tableau de int permettant de reproduire la couleur d'un herbivore en processing.
	 * 3 couleurs initiales, 3 couleurs au survol, 3 couleurs à la saisie,
	 * 3 couleurs de contour initiales, 3 couleurs de contour au survol, 3 couleurs de contour à la saisie.
	 */
	public final int[][] COL_ODEUR_HERBI={
			{0,195,0},//Initial
			{0,225,0},//Survol
			{0,255,0},//Saisi
			{0,185,0},//Initial contour
			{0,215,0},//Survol contour
			{0,245,0}//Saisi contour
	};
	
	/**
	 * Tableau de tableau de int permettant de reproduire la couleur d'un carnivore en processing.
	 * 3 couleurs initiales, 3 couleurs au survol, 3 couleurs à la saisie,
	 * 3 couleurs de contour initiales, 3 couleurs de contour au survol, 3 couleurs de contour à la saisie.
	 */
	public final int[][] COL_ODEUR_CARNI={
			{195,0,0},//Initial
			{225,0,0},//Survol
			{255,0,0},//Saisi
			{185,0,0},//Initial contour
			{215,0,0},//Survol contour
			{245,0,0}//Saisi contour
	};
	
	/**
	 * Tableau de tableau de int permettant de reproduire la couleur d'un visteur en processing.
	 * 3 couleurs initiales, 3 couleurs au survol, 3 couleurs à la saisie,
	 * 3 couleurs de contour initiales, 3 couleurs de contour au survol, 3 couleurs de contour à la saisie.
	 */
	public final int[][] COL_ODEUR_VISIT={
			{0,0,195},//Initial
			{0,0,225},//Survol
			{0,0,255},//Saisi
			{0,0,185},//Initial contour
			{0,0,215},//Survol contour
			{0,0,245}//Saisi contour
	};
	
	/**
	 * Tableau de tableau de int permettant de reproduire la couleur d'un retrait en processing.
	 * 3 couleurs initiales, 3 couleurs au survol, 3 couleurs à la saisie,
	 * 3 couleurs de contour initiales, 3 couleurs de contour au survol, 3 couleurs de contour à la saisie.
	 */
	public final int[][] COL_ODEUR_VIDE={
			{50,50,50},//Initial
			{80,80,80},//Survol
			{110,110,110},//Saisi
			{20,20,20},//Initial contour
			{50,50,50},//Survol contour
			{80,80,80}//Saisi contour
	};
	

	
	
	/**
	 * Change l'état de l'objet au survol de celui-ci.
	 */
	public void survoler();
	/**
	 * Change l'état de l'objet à la fin du survol de celui-ci.
	 */
	public void finSurvoler();
	/**
	 * Change l'état de l'objet lorsque celui-ci est selectionné.
	 */
	public void presser();
	/**
	 * Change l'état de l'objet lorsque la pression de la souris est relachée.
	 */
	public void relacher();
	/**
	 * Chaque objet deposable doit être affiché.
	 */
	public void afficher();
	/**
	 * Indique si oui ou non le curseur est au dessus de la forme geometrique.
	 * @param mX position de la souris en X
	 * @param mY position de la souris en Y
	 * @return si le curseur est au dessus
	 */
	public boolean testerSurvol(float mX, float mY);
	/**
	 * Initialisera les couleurs et la position à l'instanciation 
	 */
	public void initialiser();
	
}
