package business;

public class Etudiant extends Personne{
	// attributes
	private String cne_student;
	private float note_finale;
	private int abscence_hours;
	private String classe_student;
	
	// constructors 
	public Etudiant(int id,String username,String password,String nom, String prenom, String address, String sex, int age, String cne_student) {
		super(id,username,password,nom, prenom, address, sex, age);
		this.cne_student = cne_student;
	}
	
	public Etudiant(int id,String nom, String prenom) {
		super(id,nom, prenom);
	}

	public Etudiant(int id,String username,String password,String nom, String prenom, String address, String sex, int age, String cne_student,int abscence_hours) {
		super(id,username,password,nom, prenom, address, sex, age);
		this.cne_student = cne_student;
		this.abscence_hours=abscence_hours;
	}
	
	public Etudiant(int id,String nom, String prenom, String address, String sex, int age, String cne_student,float note_finale,int abscence_hours) {
		super(id,nom, prenom, address, sex, age);
		this.cne_student = cne_student;
		this.note_finale=note_finale;
		this.abscence_hours=abscence_hours;
	}
	public Etudiant(int id,String nom, String prenom, String address, String sex, int age, String cne_student,float note_finale,int abscence_hours, String classe_student) {
		super(id,nom, prenom, address, sex, age);
		this.cne_student = cne_student;
		this.note_finale=note_finale;
		this.abscence_hours=abscence_hours;
		this.classe_student=classe_student;
	}
	public Etudiant(int id,String nom, String prenom, String address, String sex, int age, String cne_student,int abscence_hours) {
		super(id,nom, prenom, address, sex, age);
		this.cne_student = cne_student;
		this.abscence_hours=abscence_hours;
	}

	public Etudiant(String username,String password,String nom, String prenom, String address, String sex, int age, String cne_student,int abscence_hours) {
		super(username,password,nom, prenom, address, sex, age);
		this.cne_student = cne_student;
		this.abscence_hours=abscence_hours;
	}
	


	public Etudiant(String username,String password,String nom, String prenom, String address, String sex, int age, String cne_student) {
		super(username,password,nom, prenom, address, sex, age);
		this.cne_student = cne_student;
	}
	

	public Etudiant(int id,String username,String password,String nom, String prenom) {
		super(id,username,password,nom, prenom);
	}
	


	// getter & setter 
	public String getId_student() {
		return cne_student;
	}
	

	public float getNote_finale() {
		return note_finale;
	}
	public void setNote_finale(float note_finale) {
		this.note_finale = note_finale;
	}
	public int getAbscence_hours() {
		return abscence_hours;
	}
	public void setAbscence_hours(int abscence_hours) {
		this.abscence_hours = abscence_hours;
	}

	

	public String getCne_student() {
		return cne_student;
	}



	public void setCne_student(String cne_student) {
		this.cne_student = cne_student;
	}



	public String getClasse_student() {
		return classe_student;
	}



	public void setClasse_student(String classe_student) {
		this.classe_student = classe_student;
	}



	@Override
	public String toString() {
		return super.toString()+" | CNE : " + cne_student + " | Abscence : "
				+ abscence_hours + " heurs ";
	}


}
