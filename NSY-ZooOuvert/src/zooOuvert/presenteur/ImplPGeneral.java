package zooOuvert.presenteur;

import java.util.ArrayList;
import zooOuvert.transverses.DTO.DepotDTO;
import zooOuvert.transverses.DTO.ElementDTO;

/**
 * Interface qui permettra de définir ce qu'aura à faire le presenteur d'application
 * sans pour autant être dépendant de celui-ci.
 * Cette interface permet donc de faire tampon entre la vue et le presenteur.
 * 
 * @author vincent
 *
 */
public interface ImplPGeneral {

	/**
	 * Méthode qui permettra d'aller chercher les élements à afficher dans la vue.
	 * @param : rien
	 * @return : la liste des éléments à afficher sous forme de DTO.
	 */
	public ArrayList<ElementDTO> getElements();
	
	/**
	 * Méthode qui devra insérer une odeur dans le métier.
	 * Celle-ci sera de type Herbivore ou carnivore.
	 * Un DataTransferObject de type ElementDTO devra être utilisé.
	 * @param un élement DTO contenant la position et le type d'element à simuler.
	 * @return rien
	 */
	public void insererOdeur(ElementDTO odeur);
	
	/**
	 * Méthode qui devra créer un élement.
	 * Il sera de type Visiteur, Carnivore ou Herbivore.
	 * Un DataTransferObject de type ElementDTO devra être utilisé.
	 * @param un élement DTO contenant la position et le type d'element à créer.
	 */
	public void creerElement(ElementDTO element);
	/**
	 * Méthode qui permettra de gerer le comportement en cas de relache d'une odeur, d'un ajout ou d'un retrait sur le canvas.
	 * @param typeObjetRelache objet déposer sur le canvas avec les coordonnées.
	 */
	public void envoyerDepot(DepotDTO depot);
	/**
	 * Méthode qui devra permettre de récupérer l'indice de staisfaction global des visiteurs.
	 * @return l'indice entre 0 et 1
	 */
	public float getSatisfaction();
}
