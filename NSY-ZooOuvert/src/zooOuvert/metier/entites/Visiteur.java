package zooOuvert.metier.entites;
/**
 * Classe representant les visiteurs qui seront affichés dans le canvas.
 * C’est le client de l’attraction, il se déplace dans le parc et est satisfait si des animaux se situent autour de lui. 
 * On le considérera invisible aux yeux de ceux-ci.
 * 
 * @author vincent.hofman.auditeur@lecnam.net * 
 */
public class Visiteur extends Element{
	
	/**
	 * indice de staisfaction globale du client.
	 * entre 0 et 100.
	 */
	private float indiceSatisfaction;

	public Visiteur(Position position) {
		super(position);
		// TODO La satisfaction est actuellement générée aleatoirement. Il faudra la calculer en fonction de la présence ou non d'animaux dans une proximité determinée.
		indiceSatisfaction = (int)(Math.random() * (100 +1));
	}
	
	/**
	 * Getter de Indice de satisfaction.
	 * @return l'indice de staisfaction de CE Visiteur.
	 */
	public float getIndiceSatisfaction() {
		return indiceSatisfaction;
	}
	
	/**
	 * Evolution de l'indice de satisfaction de 10%.
	 */
	@Override
	public void seDeplacer() {
		//TODO A Supprimer quand indice de satisfaction operationnel.
		indiceSatisfaction += -10 + (int)(Math.random() * (((10) - (-10) + 1)));
		super.seDeplacer();
	}

}
