package business;


public class Admin extends Personne{
	public Admin(int id,String username,String password,String name, String last_name, String address, String sex, int age) {
		super(id,username,password,name, last_name, address, sex, age);
	}

	public Admin(int id, String name, String last_name) {
		super(id, last_name, last_name);
	}
	
	public Admin(String username,String password,String name, String last_name) {
		super(username,password,name,last_name);
	}
	
	public Admin(int id,String username,String password,String name, String last_name) {
		super(id,username,password,name,last_name);
	}

	public Admin() {
		super();
	}
	public void setId(int int1) {
		// TODO Auto-generated method stub
		
	}

	
	
}