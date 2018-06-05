package metier.entites;

import java.util.ArrayList;

import metier.facades.FacadeElement;
import transverses.Positionnable;
import transverses.DTO.ElementDTO;

/**
 * Classe qui permet des créer des éléments qui seront affichés dans le canvas de l'écran.
 * Les éléments seront de type Visiteur, Carnivore ou Odorant.
 * 
 * @author vincent.hofman.auditeur@lecnam.net
 */
public class Element implements Positionnable{

		
	
	
	
	///////////////////////////////////////////////////////// Attributs de classe ////////////////////////////////////////////////////////////////////
	
	private Position position;
	////////////////////////////////////////////////////////////Constructeurs.////////////////////////////////////////////////////////////////////////
	

	/**
	 * Un élément ne peux être créé sans sa position.
	 */
	public Element() {
		float posX = POSX_CANVAS + (float)(Math.random() * (((POSX_CANVAS + LARGEUR_CANVAS) - POSX_CANVAS) + 1));
		float posY = POSY_CANVAS + (float)(Math.random() * (((POSY_CANVAS + HAUTEUR_CANVAS) - POSY_CANVAS) + 1));
		
		position = new Position(posX, posY);
	}
	
	
	/////////////////////////////////////////////////////////////Accesseurs.//////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////Processing.//////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////Méthodes publiques.////////////////////////////////////////////////////////////////////
	
	/**
	 * Première initialisation d'élements.
	 * @return la première liste d'éléments à afficher.
	 */
	public static ArrayList<ElementDTO> initialiserElements(){
		
		ArrayList<ElementDTO> liste = new ArrayList<>();
		//Ajout de 10 visiteurs, 10 carnivores et 10 herbivores.
		for (int i = 0; i < 10; i++) {
			Element e = new Element();
			liste.add(e.toDTO());
		}		
		return liste;
	}
	
	/**
	 * Permet la transformation de cet element en DTO pour le transfert entre les couches.
	 */
	public ElementDTO toDTO() {
		ElementDTO elementDTO = new ElementDTO();
		elementDTO.posX = this.position.getPosX();
		elementDTO.posY = this.position.getPosY();

		return elementDTO;
	}
	////////////////////////////////////////////////////////////Méthodes privées//////////////////////////////////////////////////////////////////////

}