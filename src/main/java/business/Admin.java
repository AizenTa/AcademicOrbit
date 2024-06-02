package business;


public class Admin extends Personne{
	
	public Admin(int id,String username,String password,String nom, String prenom, String address, String sex, int age) {
		super(id,username,password,nom, prenom, address, sex, age);
	}

	public Admin(int id, String nom, String prenom) {
		super(id, nom, prenom);
	}
	
	public Admin(String username,String password,String nom, String prenom) {
		super(username,password,nom,prenom);
	}
	
	
}
