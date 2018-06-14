package zooOuvert.metier.entites;
/**
 * Classe representant les herbivores qui seront affichés dans le canvas.
 * Animaux se déplaçant au grès de leurs envies. 
 * Au contact d’une odeur de carnivore, ils fuient dans la direction opposée à celle-ci. 
 * Ils dégagent une odeur plus faible que celle des stimulateurs.
 * 
 * @author vincent.hofman.auditeur@lecnam.net * 
 */
public class Herbivore extends Odorant{

	public Herbivore(Position position) {
		super(position);
		setPortee(40);
	}

}
