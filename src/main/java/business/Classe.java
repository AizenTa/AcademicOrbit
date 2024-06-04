package business;

import java.util.ArrayList;

public class Classe {

	private String nom;
	private String filliere;
	private String grade;
	private int id;
	ArrayList<Module> modules = new ArrayList<>();
	ArrayList<Etudiant> students = new ArrayList<>();
	
	// contructors
	public Classe(String nom, String filliere, String grade) {
		super();
		this.nom = nom;
		this.filliere = filliere;
		this.grade = grade;
	}

	public Classe(int id,String nom, String filliere, String grade) {
		super();
		this.id=id;
		this.nom = nom;
		this.filliere = filliere;
		this.grade = grade;
	}
	
	public Classe(int id,String nom) {
		super();
		this.id=id;
		this.nom=nom;
	}


	public int getId() {
		return id;
	}


	public String getName() {
		return nom;
	}

	public void setName(String nom) {
		this.nom = nom;
	}

	public String getFilliere() {
		return filliere;
	}

	public void setFilliere(String filliere) {
		this.filliere = filliere;
	}


	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}


	public ArrayList<Module> getModules() {
		return modules;
	}


	public void setModules(ArrayList<Module> modules) {
		this.modules = modules;
	}


	public ArrayList<Etudiant> getStudents() {
		return students;
	}


	public void setStudents(ArrayList<Etudiant> students) {
		this.students = students;
	}


	@Override
	public String toString() {
		return "| Name : " + nom + " | Filiere : " + filliere + " | Grade : " + grade ;
	}

	
	
	
	
}
