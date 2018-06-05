
package vue;

import java.util.ArrayList;
import java.util.Iterator;

import g4p_controls.GAlign;
import g4p_controls.GButton;
import g4p_controls.GKnob;
import g4p_controls.GSlider;
import presenteur.PGeneral;
import processing.core.PApplet;
import processing.core.PImage;
import transverses.Positionnable;
import transverses.DTO.ElementDTO;

/**
 * Cet écran aura la charge d'afficher tous les éléments mobiles ou statics de l'application et de prendre en compte toutes les interactions de l'utilisateur.
 * Il est découpé en 2 parties :
 * - A gauche se trouve la partie relative à l'entraînement de l'animateur zoologique
 * - A droite la partie simulation.
 * 
 * Les bibliothèque Processing 3.3.6 et G4P 4.1.4 sont utilisées pour l'affichage des composants graphiques.
 * 
 * @author vincent.hofman.auditeur@lecnam.net
 * @version 1.0 du 25/05/2018
 *
 */
public class EZooOuvert extends PApplet implements Positionnable{

	//CONSTANTES DE LA CLASSE
	private final int COULEUR_FOND_1 = 200;
	private final int COULEUR_TEXTE_1 = 0;
	public final int NB_ZONES_X = 6;
	public final int NB_ZONES_Y = 4;
	
	
	///////////////////////////////////////////////////////// Attributs de classe ////////////////////////////////////////////////////////////////////
	/**
	 * Presenteur qui instancie l'écran.
	 * Sera appellé pour toute action.
	 */
	/*
	 * J'ai décidé de mettre cet attribut en static car, dans le lancement de l'appli je perdais le presenteur en cours de route.
	 * J'ai l'impression que l'objet instancié par le presenteur et ceui lancé par PApplet.main ne sont pas les mêmes. 
	 */
	private static PGeneral presenteur; 	
	/**
	 * Permettra de placer un icone dans le bouton.
	 */
	private PImage icone;
	/**
	 * Slider qui permettra d'accelerer/ralentir la simulation.
	 */
	private GSlider slider;
	/**
	 * Permettra de stopper la simulation.
	 */
	private GButton boutonArreter ;
	/**
	 * Pour avoir en visu la satisfaction globale des utilisateurs virtuels.
	 */
	private GKnob cadranSatisfaction;
	/**
	 * Liste des elements  afficher dans le canvas.
	 */
	private ArrayList<ElementDTO> mesElements;
	////////////////////////////////////////////////////////////Constructeurs.////////////////////////////////////////////////////////////////////////

	/////////////////////////////////////////////////////////////Accesseurs.//////////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////Processing.//////////////////////////////////////////////////////////////////////////
	
	/**
	 * Cette méthode propre au langage Processing contiendra tout ce qui se trouve en dehors du setup et du draw.
	 */
	@Override
	public void settings() {
		//Définition du canevas
		size(1280,800);
		
		//Récupération des premiers éléments
		mesElements = presenteur.initialiserElements();
		
		for (Iterator<ElementDTO> iterator = mesElements.iterator(); iterator.hasNext();) {
			ElementDTO elementDTO = (ElementDTO) iterator.next();
			System.out.println("Reception de l'element "+elementDTO.posX+" "+elementDTO.posY); 
		}
		//if (mesElements.size() > 0) System.out.println("Element reçu !");
	}
	
	/**
	 * Configuration de processing.
	 */
	@Override
	public void setup() {
		//PApplet, posX, posY, largeur, epaisseurTrait, diamBille)
		slider = new GSlider(this, 960, 465, 280, 10, 10);
		
		icone=loadImage("img/pde-32.png");
		//GButton(PApplet, posX, posY, largeur, hauteur)
		boutonArreter = new GButton(this, 1055, 515, 185, 50);
		boutonArreter.setIcon(icone, 1, GAlign.MIDDLE, GAlign.CENTER);
		boutonArreter.setIconPos(GAlign.WEST);
		boutonArreter.setText("Arrêter la simulation");
		
		cadranSatisfaction = new GKnob(this, POSX_CANVAS+11*LARGEUR_CANVAS/(2*NB_ZONES_X), POSY_CANVAS+HAUTEUR_CANVAS+10, 70, 70, (float) 0.75);
		cadranSatisfaction.setEnabled(false);
	}
	
	/**
	 * Affichage des composants.
	 */
	@Override
	public void draw() {
		
		afficherStructure();
		afficherElements();
	}
	
	/////////////////////////////////////////////////////////////////Méthodes publiques.///////////////////////////////////////////////////////////////
	
	/**
	 * Cette méthode remplace le main utilisé habituellement lorsqu'il n'y a qu'une seule classe PApplet.
	 * A partir de cemoment, même si l'écran est instancié par son présenteur, c'est lui qui mène la danse
	 * avec la méthode draw().
	 * 
	 * @param le presenteur qui instancie cet écran.
	 */
	public void executer(PGeneral p) { 
		EZooOuvert.presenteur = p;
		//Compatibilité avec Processing.
		PApplet.main("vue.EZooOuvert"); 
	}
	

	
	
	
	
	///////////////////////////////////////////////////////////////////Méthodes privées////////////////////////////////////////////////////////////////
	
	/**
	 * Méthode qui aura la charge d'afficher le template de l'application (Tous ce qui sera fixe).
	 */
	private void afficherStructure() {
		fill(COULEUR_FOND_1);
		//Les deux principaux
		rect(10, 10, 910, 780);
		rect(930, 10, 340, 780);
		//Les zones de droite
		rect(940, 105, 320,200);
		rect(940, 315, 320,80);
		rect(940, 405, 320,190);
		
		//Le canvas avec les lignes verticales, puis horizontales
		rect(POSX_CANVAS, POSY_CANVAS, LARGEUR_CANVAS, HAUTEUR_CANVAS);
		for (int i = 1; i < NB_ZONES_X; i++) {
			line(POSX_CANVAS + (LARGEUR_CANVAS/NB_ZONES_X)*i,
					POSY_CANVAS,
					POSX_CANVAS + (LARGEUR_CANVAS/NB_ZONES_X)*i,
					POSY_CANVAS + HAUTEUR_CANVAS);
		}
		for (int i = 1; i < NB_ZONES_X; i++) {
			line(POSX_CANVAS,
					POSY_CANVAS + (HAUTEUR_CANVAS/NB_ZONES_Y)*i,
					POSX_CANVAS + LARGEUR_CANVAS,
					POSY_CANVAS + (HAUTEUR_CANVAS/NB_ZONES_Y)*i);
		}
				
		fill(COULEUR_TEXTE_1);
		
		
		textSize(30);
		text("Monitoring", 40, 45);
		text("Simulation", 950, 45);
		
		textSize(15);
		text("Le parc",50,80);
		text("Temps d'entraînement : HH:MM:SS",635,80);
		
		//Un peu de bidouille pour bien afficher le texte sous les première et deuxième colonnes.
		textAlign(CENTER);
		text("Odeur végé",POSX_CANVAS+LARGEUR_CANVAS/(NB_ZONES_X*2),POSY_CANVAS+HAUTEUR_CANVAS+75);
		text("Odeur carni",POSX_CANVAS+3*LARGEUR_CANVAS/(NB_ZONES_X*2),POSY_CANVAS+HAUTEUR_CANVAS+75);
		text("Satisfaction globales des clients :",POSX_CANVAS+9*LARGEUR_CANVAS/(NB_ZONES_X*2),POSY_CANVAS+HAUTEUR_CANVAS+50);
		textAlign(LEFT);
		
		text("Ajouts",960,135);
		text("Visiteur",1040,165);
		text("Herbivore",1040,220);
		text("Carnivore",1040,275);
		text("Suppression",960,345);
		text("Gestion du temps",960,435);
		
		
		
	}

	/**
	 * Méthode qui affichera tous les éléments mobiles.
	 */
	private void afficherElements() {
		for (Iterator<ElementDTO> iterator = mesElements.iterator(); iterator.hasNext();) {
			ElementDTO elementDTO = (ElementDTO) iterator.next();
			ellipse(elementDTO.posX, elementDTO.posY, 10, 10);
			
		}
	}
}
