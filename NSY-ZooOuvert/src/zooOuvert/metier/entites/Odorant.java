package zooOuvert.metier.entites;
/**
 * Classe abstraite permettant aux herbivore et aux stimulateurs de dégager une odeur.
 * 
 * @author vincent.hofman.auditeur@lecnam.net * 
 */
public abstract class Odorant extends Element{

	/**
	 * Portée de l'odeur.
	 */
	private int portee;
	
	public Odorant(Position position) {
		super(position);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the portee
	 */
	public int getPortee() {
		return portee;
	}

	/**
	 * @param portee the portee to set
	 */
	public void setPortee(int portee) {
		this.portee = portee;
	}
	
	
}
