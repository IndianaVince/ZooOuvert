/**
 * 
 */
package zooOuvert.presenteur;

import java.util.ArrayList;

import zooOuvert.metier.facades.FacadeElement;
import zooOuvert.transverses.DTO.DepotDTO;
import zooOuvert.transverses.DTO.ElementDTO;
import zooOuvert.vue.ecrans.EZooOuvert;

/**
 * Presenteur principal de l'application.
 * Il fera office de passe plat entre les écrans et les presenteurs de fonctionnalités.
 * @author vincent
 *
 */
public class PGeneral implements ImplPGeneral{

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
	 * Méthode qui permettra d'aller chercher (ou créer) les éléments à jour.
	 */
	public ArrayList<ElementDTO> getElements(){
		return facadeElement.getElements();
	}
	
	@Override
		public void envoyerDepot(DepotDTO depot) {
			facadeElement.envoyerDepot(depot);
			
		}
////////////////////////////////////////////////////////////Méthodes privées//////////////////////////////////////////////////////////////////////

	@Override
	public void insererOdeur(ElementDTO odeur) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void creerElement(ElementDTO element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void detruireElement(ElementDTO positionElement) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
