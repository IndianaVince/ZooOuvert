package metier.entites;
/**
 * Classe representant les stimulateurs fixes qui seront affichés dans le canvas.
 * Stimulateurs produisant des jets odorants, soit de carnivore, soit d’herbivore (mais un seul à la fois).
 * L’odeur reste en place dans la zone quelques instants avant de se dissiper.
 * 
 * @author vincent.hofman.auditeur@lecnam.net * 
 */
public class Stimulateur extends Odorant{

	public Stimulateur(Position position) {
		super(position);
	}
	
	/**
	 * Redefinition de la méthode seDeplacer().
	 * Les stimulateurs ne se déplacent pas !
	 */
	@Override
	public void seDeplacer() {
		//Rien à faire
	}

}
