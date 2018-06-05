package metier.facades;

import java.util.ArrayList;
import java.util.Iterator;

import metier.entites.Element;
import transverses.DTO.ElementDTO;

/**
 * Facade de la classe métier Element.
 * @author vincent.hofman.auditeur@lecnam.net
 *
 */
public class FacadeElement {

///////////////////////////////////////////////////////// Attributs de classe ////////////////////////////////////////////////////////////////////
	/**
	 * Seule et unique instance de la facade.
	 */
	private static FacadeElement instance;
	
////////////////////////////////////////////////////////////Constructeurs.////////////////////////////////////////////////////////////////////////
	
	/**
	 * Constructeur de la classe.
	 * Elle devra instancier une instance d'Element par défaut.
	 */
	public FacadeElement() {
		
	}
	
	
/////////////////////////////////////////////////////////////Accesseurs.//////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////Méthodes publiques.////////////////////////////////////////////////////////////////////
	
	/**
	 * Permet de donner l'instance static de la facade (Pas la peine d'en créer une à chaque fois).
	 * Si aucune instance n'éxiste, une nouvelle est crée.
	 */
	public static FacadeElement getInstance() {
		if (instance == null) {
			instance = new FacadeElement();
			System.out.println("Création d'une nouvelle instance de FacadeElement.");
		}
		return instance;
	}
	
	/**
	 * Méthode qui permettra d'aller chercher (créer) les premiers éléments.
	 */
	public ArrayList<ElementDTO> initialiserElements(){
		return Element.initialiserElements();
	}
////////////////////////////////////////////////////////////Méthodes privées//////////////////////////////////////////////////////////////////////
	
}
