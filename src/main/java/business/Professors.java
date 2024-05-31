package business;

import java.util.ArrayList;

public class Professors extends Personne{
	private String cne_prof;
	ArrayList<Module> prof_modules = new ArrayList<>();



	// constructors
	public Professors(int id,String username, String password,String name, String last_name, String address, String sex, int age, String cne_prof) {
		super(id,username,password,name, last_name, address, sex, age);
		this.cne_prof = cne_prof;
	}

	public Professors(String username, String password,String name, String last_name, String address, String sex, int age, String cne_prof) {
		super(username,password,name, last_name, address, sex, age);
		this.cne_prof = cne_prof;
	}

	public String getCne_prof() {
		return cne_prof;
	}

	public void setCne_prof(String cne_prof) {
		this.cne_prof = cne_prof;
	}


	public ArrayList<Module> getProf_modules() {
		return prof_modules;
	}

	public void setProf_modules(Module module) {
		prof_modules.add(module);
	}

	@Override
	public String toString() {
		return super.toString()+" | CNE : " + cne_prof;
	}

	

	
	
	

		
	
	
}
