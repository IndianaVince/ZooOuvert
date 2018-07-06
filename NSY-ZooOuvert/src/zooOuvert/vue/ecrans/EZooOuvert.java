
package zooOuvert.vue.ecrans;

import java.util.ArrayList;
import java.util.Iterator;

import g4p_controls.GAlign;
import g4p_controls.GButton;
import g4p_controls.GKnob;
import g4p_controls.GSlider;
import processing.core.PApplet;
import processing.core.PImage;
import zooOuvert.presenteur.ImplPGeneral;
import zooOuvert.presenteur.PGeneral;
import zooOuvert.transverses.Positionnable;
import zooOuvert.transverses.DTO.ElementDTO;
import zooOuvert.transverses.DTO.DepotDTO;
import zooOuvert.vue.formes.Odeur;
import zooOuvert.vue.formes.Retrait;
import zooOuvert.vue.formes.Ajout;
import zooOuvert.vue.formes.FormeGeometrique.EnumTypeForme;

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
	private final int COULEUR_FOND_1 = color(200,200,200);
	private final int COULEUR_TEXTE_1 = color(0,0,0);	
	private final int COULEUR_VISITEUR = color(0,0,255);
	private final int COULEUR_HERBIVORE = color(0,255,0);
	private final int COULEUR_PORTEE_HERBIVORE = color(100,255,100,50);
	private final int COULEUR_CARNIVORE = color(255,0,0);
	private final int COULEUR_PORTEE_CARNIVORE = color(255,100,100,50);
	private final int COULEUR_STIMULATEUR = color(255,0,255);	
	///////////////////////////////////////////////////////// Attributs de classe ////////////////////////////////////////////////////////////////////
	/**
	 * Presenteur qui instancie l'écran.
	 * Sera appellé pour toute action.
	 */
	/*
	 * J'ai décidé de mettre cet attribut en static car, dans le lancement de l'appli je perdais le presenteur en cours de route.
	 * J'ai l'impression que l'objet instancié par le presenteur et ceui lancé par PApplet.main ne sont pas les mêmes. 
	 */
	private static ImplPGeneral presenteur; 	
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
	 * Odeur d'herbivore à glisser/déposer sur un stimulateur.
	 */
	private Odeur odeurHerbivore;
	/**
	 * Odeur d'herbivore à glisser/déposer sur un stimulateur.
	 */
	private Odeur odeurCarnivore;
	/**
	 * Ajout d'un visiteur par glisser/deposer.
	 */
	private Ajout ajoutVisiteur;
	/**
	 * Ajout d'un herbivore par glisser/deposer.
	 */
	private Ajout ajoutHerbivore;
	/**
	 * Ajout d'un carnivore par glisser/deposer.
	 */
	private Ajout ajoutCarnivore;
	/**
	 * Retrait d'un élément par glisser/deposer.
	 */
	private Retrait retraitElement;
	
	
	////////////////////////////////////////////////////////////Constructeurs.////////////////////////////////////////////////////////////////////////

	/////////////////////////////////////////////////////////////Accesseurs.//////////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////Processing.//////////////////////////////////////////////////////////////////////////
	
	/**
	 * Cette méthode propre au langage Processing contiendra tout ce qui se trouve en dehors du setup et du draw.
	 */
	@Override
	public void settings() {
		//Définition du canvas
		size(1280,800);
		
	}
	
	/**
	 * Configuration de processing.
	 */
	@Override
	public void setup() {
		smooth();
		frameRate(60);
		stroke(2);
		
		creerSlider();
		creerBoutonArreter();
		creerCadranSatisfaction();
		creerOdeurs();
		creerAjouts();
		creerRetraits();
		ellipseMode(CENTER);
	}
	
	/**
	 * Affichage des composants.
	 */
	@Override
	public void draw() {
		
		afficherStructure();
		//Demandera au métier les élements à jour.
		afficherElements(presenteur.getElements());
		afficherTemps();
		afficherOdeurs();
		afficherAjouts();
		afficherRetraits();
	}
	
	/**
	 * Méthode qui permettra d'indique à l'utilisateur que la souris se trouve sur la forme par un changement de couleur.
	 */
	public void mouseMoved(){
	 if (odeurHerbivore.testerSurvol(mouseX, mouseY)) odeurHerbivore.survoler(); else odeurHerbivore.finSurvoler();
	 if (odeurCarnivore.testerSurvol(mouseX, mouseY)) odeurCarnivore.survoler(); else odeurCarnivore.finSurvoler();
	 if (ajoutVisiteur.testerSurvol(mouseX, mouseY)) ajoutVisiteur.survoler(); else ajoutVisiteur.finSurvoler();
	 if (ajoutHerbivore.testerSurvol(mouseX, mouseY)) ajoutHerbivore.survoler(); else ajoutHerbivore.finSurvoler();
	 if (ajoutCarnivore.testerSurvol(mouseX, mouseY)) ajoutCarnivore.survoler(); else ajoutCarnivore.finSurvoler();
	 if (retraitElement.testerSurvol(mouseX, mouseY)) retraitElement.survoler(); else retraitElement.finSurvoler();
	}
	
	/**
	 * Méthode qui permettra d'indique à l'utilisateur qu'il a saisi la forme à l'aide du bouton de la souris.
	 */
	public void mousePressed(){
		if (odeurHerbivore.testerSurvol(mouseX, mouseY)) odeurHerbivore.presser();
		if (odeurCarnivore.testerSurvol(mouseX, mouseY)) odeurCarnivore.presser();
		if (ajoutVisiteur.testerSurvol(mouseX, mouseY)) ajoutVisiteur.presser();
		if (ajoutHerbivore.testerSurvol(mouseX, mouseY)) ajoutHerbivore.presser();
		if (ajoutCarnivore.testerSurvol(mouseX, mouseY)) ajoutCarnivore.presser();
		if (retraitElement.testerSurvol(mouseX, mouseY)) retraitElement.presser();
	}
	
	/**
	 * Méthode qui gère le comportement de relaché de pression en fonction du type de forme.
	 */
	public void mouseReleased(){//TODO factoriser avec mousePressed() si pas d'évolution du code
		//Prise en compte et reinitialisation pour les formes.
		if (odeurHerbivore.testerSurvol(mouseX, mouseY)) { 
			presenteur.envoyerDepot(new DepotDTO(odeurHerbivore));
			odeurHerbivore.relacher();
		}
		if (odeurCarnivore.testerSurvol(mouseX, mouseY)) {
			presenteur.envoyerDepot(new DepotDTO(odeurCarnivore));
			odeurCarnivore.relacher();
		}
		if (ajoutVisiteur.testerSurvol(mouseX, mouseY)) {
			presenteur.envoyerDepot(new DepotDTO(ajoutVisiteur));
			ajoutVisiteur.relacher();
		}
		if (ajoutHerbivore.testerSurvol(mouseX, mouseY)) {
			presenteur.envoyerDepot(new DepotDTO(ajoutHerbivore));
			ajoutHerbivore.relacher();
		}
		if (ajoutCarnivore.testerSurvol(mouseX, mouseY)) {
			presenteur.envoyerDepot(new DepotDTO(ajoutCarnivore));
			ajoutCarnivore.relacher();
		}
		if (retraitElement.testerSurvol(mouseX, mouseY)) {
			presenteur.envoyerDepot(new DepotDTO(retraitElement));
			retraitElement.relacher();
		}
	}

	/**
	 * Modifiera les coordonnées de la forme pour correspondre au déplacement de la souris.
	 */
	public void mouseDragged(){
		//TODO Créer un verrou qui empeche d'embarquer les autres deposable au survol de ceux-ci.
	  if (odeurHerbivore.testerSurvol(mouseX, mouseY)){ odeurHerbivore.setPosX(mouseX); odeurHerbivore.setPosY(mouseY);} 
	  if (odeurCarnivore.testerSurvol(mouseX, mouseY)){ odeurCarnivore.setPosX(mouseX); odeurCarnivore.setPosY(mouseY);} 
	  if (ajoutVisiteur.testerSurvol(mouseX, mouseY)){ ajoutVisiteur.setPosX(mouseX); ajoutVisiteur.setPosY(mouseY);} 
	  if (ajoutHerbivore.testerSurvol(mouseX, mouseY)){ ajoutHerbivore.setPosX(mouseX); ajoutHerbivore.setPosY(mouseY);} 
	  if (ajoutCarnivore.testerSurvol(mouseX, mouseY)){ ajoutCarnivore.setPosX(mouseX); ajoutCarnivore.setPosY(mouseY);} 
	  if (retraitElement.testerSurvol(mouseX, mouseY)){ retraitElement.setPosX(mouseX); retraitElement.setPosY(mouseY);} 
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
		PApplet.main("zooOuvert.vue.ecrans.EZooOuvert"); 
	}
	
	///////////////////////////////////////////////////////////////////Méthodes privées////////////////////////////////////////////////////////////////
	

	private void creerSlider() {
		slider = new GSlider(this, 960, 465, 280, 10, 10);
	}
	private void creerBoutonArreter() {
		icone=loadImage("img/pde-32.png");
		boutonArreter = new GButton(this, 1055, 515, 185, 50);
		boutonArreter.setIcon(icone, 1, GAlign.MIDDLE, GAlign.CENTER);
		boutonArreter.setIconPos(GAlign.WEST);
		boutonArreter.setText("Arrêter la simulation");
	}
	private void creerCadranSatisfaction(){
		cadranSatisfaction = new GKnob(this, POSX_CANVAS+11*LARGEUR_CANVAS/(2*NB_ZONES_X), POSY_CANVAS+HAUTEUR_CANVAS+10, 70, 70, (float) 0.75);
		cadranSatisfaction.setEnabled(false);
	}
	private void creerOdeurs() {
		odeurHerbivore = new Odeur(this, EnumTypeForme.HERBIVORE, 130, 740, 30);
		odeurCarnivore = new Odeur(this, EnumTypeForme.CARNIVORE, 260, 740, 30);
	}
	private void creerAjouts() {
		ajoutVisiteur = new Ajout(this, EnumTypeForme.VISITEUR, 1000, 160, 20);
		ajoutHerbivore = new Ajout(this, EnumTypeForme.HERBIVORE, 1000, 215, 20);
		ajoutCarnivore = new Ajout(this, EnumTypeForme.CARNIVORE, 1000, 270, 20);
	}
	private void creerRetraits() {
		retraitElement = new Retrait(this, EnumTypeForme.VIDE, 1000, 370, 20);
	}
	
	
	
	
	/**
	 * Méthode qui aura la charge d'afficher le template de l'application (Tous ce qui sera fixe).
	 */
	private void afficherStructure() {
		fill(COULEUR_FOND_1);
		//Pour que même le background soit regénéré pour le passage des ajouts.
		rect(0,0,width,height);
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
		text("Temps d'entraînement : ",635,80);
		
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
		text("Retrait",1040,375);
		text("Gestion du temps",960,435);
		
		
		
	}

	/**
	 * Méthode qui affichera tous les éléments mobiles.
	 * @param la liste des éléments à afficher.
	 */
	private void afficherElements(ArrayList<ElementDTO> elementsDTO) {
		noStroke();
		
		for (Iterator<ElementDTO> iterator = elementsDTO.iterator(); iterator.hasNext();) {
			ElementDTO elementDTO = (ElementDTO) iterator.next();

			if (elementDTO.getTypeElement() == ElementDTO.EnumTypeElement.VISITEUR) {fill(COULEUR_VISITEUR);}
			if (elementDTO.getTypeElement() == ElementDTO.EnumTypeElement.CARNIVORE) {fill(COULEUR_CARNIVORE);}
			
			if (elementDTO.getTypeElement() == ElementDTO.EnumTypeElement.HERBIVORE) {
				fill(COULEUR_PORTEE_HERBIVORE);
				ellipse(elementDTO.getPosX(), elementDTO.getPosY(), elementDTO.getPorteeOdeur(), elementDTO.getPorteeOdeur());
				fill(COULEUR_HERBIVORE);
				}

			if (elementDTO.getTypeElement() == ElementDTO.EnumTypeElement.STIMULATEUR) {
				
				if (! elementDTO.getTypeOdeur().equals(ElementDTO.EnumTypeOdeur.INERTE)) {
					System.out.println("Odeur non inerte à afficher.");
					if (elementDTO.getTypeOdeur().equals(ElementDTO.EnumTypeOdeur.CARNIVORE)) {
						fill(COULEUR_PORTEE_CARNIVORE);
					} else {
						fill(COULEUR_PORTEE_HERBIVORE);
					}
					//Création de la portée de l'odeur.
					ellipse(elementDTO.getPosX(), elementDTO.getPosY(), elementDTO.getPorteeOdeur(), elementDTO.getPorteeOdeur());
				}
				fill(COULEUR_STIMULATEUR);
			}					
			
			//Création visuelle de l'élément lui-même.
			ellipse(elementDTO.getPosX(), elementDTO.getPosY(), 10, 10);
		}		
		
		stroke(2);
	}

	/**
	 * Méthode qui affichera les deux odeurs.
	 */
	private void afficherOdeurs() {
		odeurHerbivore.afficher();
		odeurCarnivore.afficher();
	}
	/**
	 * Méthode qui affichera les ajouts.
	 */
	private void afficherAjouts() {
		ajoutVisiteur.afficher();
		ajoutHerbivore.afficher();
		ajoutCarnivore.afficher();
	}
	/**
	 * Méthode qui affichera les retraits.
	 */
	private void afficherRetraits() {
		retraitElement.afficher();
	}
	
	/**
	 * Fonction qui affichera le temps simulé en haut à droite.
	 */
	private void afficherTemps() {
		text(frameCount,850,80);
	}
}