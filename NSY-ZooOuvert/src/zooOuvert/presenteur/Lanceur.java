
package zooOuvert.presenteur;

import zooOuvert.vue.ecrans.EZooOuvert;

/**
 * @author vincent
 *
 */
public class Lanceur {

	/**
	 * Ce lanceur va permettre de creer le presenteur principal de l'application, et de passer 
	 * ce presenteur à l'écran d'accueil de l'application.
	 * @param args
	 */
	public static void main(String[] args) {
		
		EZooOuvert ecran;
		PGeneral presenteur = new PGeneral();
		
		
		ecran = new EZooOuvert();
		//presenteur, voici ton écran principal
		presenteur.lancerAppli(ecran);
	}

}
