package business;


public class Admin extends Personne{
	public Admin(String username,String password,String name, String last_name, String address, String sex, int age) {
		super(username,password,name, last_name, address, sex, age);
	}

	public Admin(int id, String name, String last_name) {
		super(id, last_name, last_name);
	}
	
	public Admin(String username,String password,String name, String last_name) {
		super(username,password,name,last_name);
	}
	
}
