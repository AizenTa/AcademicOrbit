package business;

public class Personne {
	
	// attributes
	private String username;
	private String password;
	private String name;
	private String last_name;
	private String address;
	private String sex;
	private int age;
	private static int tmp=1;
	private int id;
	
	
	
	// constructors
	public Personne(String username,String password,String name, String last_name, String address, String sex, int age) {
		super();
		this.id=tmp++;
		this.username=username;
		this.password=password;
		this.name = name;
		this.last_name = last_name;
		this.address = address;
		this.sex = sex;
		this.age = age;
	}
	
	
	// getter & setter 
	
	public String getName() {
		return name;
	}
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getId() {
		return id;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}


	public void setName(String name) {
		this.name = name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSex() {
		return sex;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}


	@Override
	public String toString() {
		return "| Name : " + name + " | Prenom  : " + last_name + " | Addresse : " + address+"| sex : " + sex + " | age :  " + age;
	}


	
	
	
	
}
