package zooOuvert.presenteur;

import java.util.ArrayList;

import zooOuvert.metier.entites.Herbivore;
import zooOuvert.transverses.DTO.DepotDTO;
import zooOuvert.transverses.DTO.ElementDTO;
import zooOuvert.vue.formes.FormeGeometrique;
import zooOuvert.vue.formes.ImplDeposable;

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
	 * Méthode qui devra détruire un élement.
	 * Seul le métier aura la responsabilité de déterminer si un élement à détruire existe ou non et
	 * pourra donc le détruire .
	 * La vue ne donnera que la position de la zone de destruction.
	 * @param un élement DTO qui ne transportera que la position.
	 * @return rien
	 */
	public void detruireElement(ElementDTO positionElement);
	/**
	 * Méthode qui permettra de gerer le comportement en cas de relache d'une odeur ou d'un ajout sur le canvas.
	 * @param typeObjetRelache objet déposer sur le canvas avec les coordonnées.
	 */
	public void envoyerDepot(DepotDTO depot);
	
}
