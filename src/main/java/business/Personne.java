package business;

public class Personne {
	
	// attributes
	private String username;
	private String password;
	private String nom;
	private String prenom;
	private String address;
	private String sex;
	private int age;
	private int id;
	
	
	
	// constructors
	public Personne(int id,String username,String password,String nom, String prenom, String address, String sex, int age) {
		super();
		this.id=id;
		this.username=username;
		this.password=password;
		this.nom = nom;
		this.prenom = prenom;
		this.address = address;
		this.sex = sex;
		this.age = age;
	}
	// constructors
		public Personne(String username,String password,String nom, String prenom, String address, String sex, int age) {
			super();
			this.username=username;
			this.password=password;
			this.nom = nom;
			this.prenom = prenom;
			this.address = address;
			this.sex = sex;
			this.age = age;
		}
		
	public Personne(int id,String nom, String prenom) {
		super();
		this.id=id;
		this.nom = nom;
		this.prenom = prenom;
		
	}
	// getter & setter 
	
	public Personne(String username, String password, String nom, String prenom) {
		super();
		this.username=username;
		this.password=password;
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public Personne(int id,String username, String password, String nom, String prenom) {
		super();
		this.id=id;
		this.username=username;
		this.password=password;
		this.nom = nom;
		this.prenom = prenom;
	}
	public Personne() {
		super();
	}
	public Personne(int id2, String nom2, String prenom2, String address2, String sex2, int age2) {
		super();
		this.id=id2;
		this.nom=nom2;
		this.prenom=prenom2;
		this.address=address2;
		this.sex=sex2;
		this.age=age2;
	}
	
	public String getNom() {
		return nom;
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


	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
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
		return "| Name : " + nom + " | Prenom  : " + prenom + " | Addresse : " + address+"| sex : " + sex + " | age :  " + age;
	}


	
	
	
	
}