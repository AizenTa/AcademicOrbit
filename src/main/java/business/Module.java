package business;

public class Module {
	private String nom;
	private int id;
	private int nbr_heures;	
	
	
	public Module() {
		super();
	}


	public Module(String nom) {
		super();
		this.nom = nom;
	}
	
	
	public int getId() {
		return id;
	}


	public void setName(String nom) {
		this.nom = nom;
	}

	public String getName() {
		return nom;
	}



	@Override
	public String toString() {
		return  "| ID : " + id +" | Name : " + nom ;
	}


	public int getNbr_heures() {
		return nbr_heures;
	}


	public void setNbr_heures(int nbr_heures) {
		this.nbr_heures = nbr_heures;
	}

	

}
