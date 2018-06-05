/**
 * 
 */
package presenteur;

import java.util.ArrayList;
import java.util.List;

import metier.facades.FacadeElement;
import transverses.DTO.ElementDTO;
import vue.EZooOuvert;

/**
 * Presenteur principal de l'application.
 * Il fera office de passe plat entre les écrans et les presenteurs de fonctionnalités.
 * @author vincent
 *
 */
public class PGeneral {

///////////////////////////////////////////////////////// Attributs de classe ////////////////////////////////////////////////////////////////////
	/**
	 * Ceci sera l'écran principal de l'application.
	 */
	private EZooOuvert ecranPrincipal;
	/**
	 * Facade de la classe métier Element.
	 */
	private FacadeElement facadeElement;
	
////////////////////////////////////////////////////////////Constructeurs.////////////////////////////////////////////////////////////////////////
	
	/**
	 * Constructeur de la classe.
	 * Elle devra instancier les facades du metier.
	 */
	public PGeneral() {
		facadeElement = FacadeElement.getInstance();
	}
	
/////////////////////////////////////////////////////////////Accesseurs.//////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////Processing.//////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////Méthodes publiques.////////////////////////////////////////////////////////////////////
	
	/**
	 * Méthode qui sera utilisée par le lanceur pour démarrer l'outil.
	 * @param l'écran principal de l'application.
	 */
	public void lancerAppli(EZooOuvert ecran) {
		ecranPrincipal = ecran;
		ecranPrincipal.executer(this);
	}
	
	/**
	 * Méthode qui permettra d'aller chercher (créer) les premiers éléments.
	 */
	public ArrayList<ElementDTO> initialiserElements(){
		return facadeElement.initialiserElements();
	}
////////////////////////////////////////////////////////////Méthodes privées//////////////////////////////////////////////////////////////////////
	
	
	
}
