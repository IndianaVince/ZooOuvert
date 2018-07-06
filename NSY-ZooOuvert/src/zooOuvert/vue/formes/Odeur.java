package zooOuvert.vue.formes;

import processing.core.PApplet;

public class Odeur extends TriangleEqui {

	public Odeur(PApplet p, EnumTypeForme type, float posInitX, float posInitY, int hauteur) {
		super(p, type, posInitX, posInitY, hauteur);
	}
	
	@Override
	public void afficher() {
		getParent().fill(this.getCouleurActuelle());
		super.afficher();

	}
}
