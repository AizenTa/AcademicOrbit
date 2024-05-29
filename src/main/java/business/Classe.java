package business;

import java.util.ArrayList;

public class Classe {

	private String name;
	private String filliere;
	private String grade;
	private int id;
	private static int tmp=1;
	ArrayList<Module> modules = new ArrayList<>();
	ArrayList<Student> students = new ArrayList<>();
	
	// contructors
	public Classe(String name, String filliere, String grade) {
		super();
		this.id=tmp++;
		this.name = name;
		this.filliere = filliere;
		this.grade = grade;
	}

	
	public int getId() {
		return id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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


	public ArrayList<Student> getStudents() {
		return students;
	}


	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}


	@Override
	public String toString() {
		return "| Name : " + name + " | Filiere : " + filliere + " | Grade : " + grade ;
	}

	
	
	
	
}
