package business;

public class Module {
	private String name;
	private int id;
	private int nbr_heures;
	private static int tmp=1;
	
	
	
	public Module() {
		super();
	}


	public Module(String name) {
		super();
		this.id=tmp++;
		this.name = name;
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



	@Override
	public String toString() {
		return  "| ID : " + id +" | Name : " + name ;
	}


	public int getNbr_heures() {
		return nbr_heures;
	}


	public void setNbr_heures(int nbr_heures) {
		this.nbr_heures = nbr_heures;
	}

	

}
