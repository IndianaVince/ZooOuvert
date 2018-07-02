package zooOuvert.metier.entites;

import zooOuvert.transverses.DTO.DepotDTO;

/**
 * Classe representant les stimulateurs fixes qui seront affichés dans le canvas.
 * Stimulateurs produisant des jets odorants, soit de carnivore, soit d’herbivore (mais un seul à la fois).
 * L’odeur reste en place dans la zone quelques instants avant de se dissiper.
 * 
 * @author vincent.hofman.auditeur@lecnam.net * 
 */
public class Stimulateur extends Odorant{

	/**
	 * Etat du stimulateur : INERTE ou diffuse qqch.
	 */
	private TypeDiffusion typeDiffusion;
	public enum TypeDiffusion {HERBIVORE,CARNIVORE,INERTE};
	
	public Stimulateur(Position position) {
		super(position);
		typeDiffusion = TypeDiffusion.INERTE;
	}
	
	
	
	/**
	 * @return the typeDiffusion
	 */
	public TypeDiffusion getTypeDiffusion() {
		return typeDiffusion;
	}



	/**
	 * @param typeDiffusion the typeDiffusion to set
	 */
	public void setTypeDiffusion(TypeDiffusion typeDiffusion) {
		this.typeDiffusion = typeDiffusion;
	}



	/**
	 * Redefinition de la méthode seDeplacer().
	 * Les stimulateurs ne se déplacent pas  mais peuvent eventuellement decrementer les portée de diffusion.
	 */
	@Override
	public void seDeplacer() {
		if (getPortee()>0) {
			setPortee(getPortee()-1);
			System.out.println("Portée : "+getPortee());
		}else {
			this.typeDiffusion = TypeDiffusion.INERTE;
		}
	}
	
	/**
	 * Méthode qui permettra de diffuser une odeur autour du stimulateur.
	 */
	public void diffuser(DepotDTO depot) {
		if (depot.getTypeElement() == DepotDTO.EnumTypeForme.HERBIVORE) { this.typeDiffusion = TypeDiffusion.HERBIVORE;}
		if (depot.getTypeElement() == DepotDTO.EnumTypeForme.CARNIVORE) { this.typeDiffusion = TypeDiffusion.CARNIVORE;}
		setPortee(160);
		System.out.println("Diffusion : "+ depot.getTypeElement());
	}

}
