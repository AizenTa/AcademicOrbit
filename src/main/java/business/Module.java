package business;

public class Module {
	private int id;
	private String name;

	private int nbr_heures;	
	
	
	public Module() {
		super();
	}

	public Module(int id, String name, int nbrHeures) {
		super();
		this.id=id;
		this.name = name;
		this.nbr_heures=nbrHeures;
	}

	public Module(String name) {
		super();
		this.name = name;
	}

	public Module(String name, int nbrHeures) {
		super();
		this.name = name;
		this.nbr_heures=nbrHeures;
	}


	public int getId() {
		return id;
	}


	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getNbr_heures() {
		return nbr_heures;
	}


	public void setNbr_heures(int nbr_heures) {
		this.nbr_heures = nbr_heures;
	}



	@Override
	public String toString() {
		return  "| ID : " + id +" | Name : " + name ;
	}
}