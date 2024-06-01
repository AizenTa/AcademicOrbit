package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import business.*;

public class StudentDAO {
	// attributes 
	MaConnexion connexion;
	Statement stmt;
	Scanner sc = new Scanner(System.in);
	
	// Constructor
	public StudentDAO(MaConnexion connexion) {
		this.connexion = connexion;
		stmt = connexion.getStmt();
	}
	
	public void consulterInformations(int id) throws SQLException {
		System.out.println("+----------------------------------------------------------------------------------------------------------------+");
    	System.out.println("|||||||||||||||||||||||||||||||||||||||||||| Vos Informations ||||||||||||||||||||||||||||||||||||||||||||||||||||");
		System.out.println("+----------------------------------------------------------------------------------------------------------------+");
    	ResultSet resultat_student = stmt.executeQuery("SELECT * FROM student WHERE ID='" + id + "'");
	    if (resultat_student.next()) {
	        Etudiant student = new Etudiant(resultat_student.getString("username"),
	                                      resultat_student.getString("password"),
	                                      resultat_student.getString("name"),
	                                      resultat_student.getString("last_name"),
	                                      resultat_student.getString("address"),
	                                      resultat_student.getString("sex"),
	                                      resultat_student.getInt("age"),
	                                      resultat_student.getString("cne_student"),
	                                      resultat_student.getInt("abscence_hours")
	        		);
	        String classe = classeStudent(id);
			System.out.println("\n+----------------------------------------------------------------------------------------------------------------+");
	        System.out.println(student+" | Classe : "+classe+"]");
			System.out.println("\n+----------------------------------------------------------------------------------------------------------------+");
	        System.out.print("| Voulez-vous changer les informations ? (o pour oui / n pour non ) :");
	        String reponse = sc.nextLine();
			System.out.println("\n+----------------------------------------------------------------------------------------------------------------+");
	        if ("o".equals(reponse)) {
				System.out.println("\n+----------------------------------------------------------------------------------------------------------------+");
	        	 System.out.print("| Choisir le champ à modifier (1:username, 2:password, 3: Nom, 4: Prenom, 5: Adresse, 6: Sexe, 7: Age, 8: CNE student) : ");
	 	        int reponse_champ = sc.nextInt();
				System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	 	        sc.nextLine();
	        	switch (reponse_champ) {
				case 1:
					System.out.println("\n+----------------------------------------------------------------------------------------------------------------+");
					System.out.print("| Entrer le nouveau username : ");
			        String username = sc.nextLine();
					System.out.println("+----------------------------------------------------------------------------------------------------------------+");
			        stmt.executeUpdate("UPDATE student SET username='" + username + "' WHERE ID='" + id + "'");
					break;
				case 2:
					 System.out.println("\n+----------------------------------------------------------------------------------------------------------------+");
					 System.out.print("| Entrer le nouveau password : ");
			         String password = sc.nextLine();
					 System.out.println("+----------------------------------------------------------------------------------------------------------------+");
			         stmt.executeUpdate("UPDATE student SET password='" + password + "' WHERE ID='" + id + "'");
					break;
				case 3:
					 System.out.println("\n+----------------------------------------------------------------------------------------------------------------+");
					 System.out.print("| Entrer le nouveau nom : ");
			         String name = sc.nextLine();
					 System.out.println("+----------------------------------------------------------------------------------------------------------------+");
			         stmt.executeUpdate("UPDATE student SET name='" + name + "' WHERE ID='" + id + "'");
					break;
				case 4:
					 System.out.println("\n+----------------------------------------------------------------------------------------------------------------+");
					 System.out.print("| Entrer le nouveau prenom : ");
			         String prenom = sc.nextLine();
					 System.out.println("+----------------------------------------------------------------------------------------------------------------+");
			         stmt.executeUpdate("UPDATE student SET last_name='" + prenom + "' WHERE ID='" + id + "'");
					break;
				case 5:
					 System.out.println("\n+----------------------------------------------------------------------------------------------------------------+");
					 System.out.print("| Entrer le nouveau address : ");
			         String address = sc.nextLine();
					 System.out.println("+----------------------------------------------------------------------------------------------------------------+");
			         stmt.executeUpdate("UPDATE student SET address='" + address + "' WHERE ID='" + id + "'");
					break;
				case 6:
					 System.out.println("\n+----------------------------------------------------------------------------------------------------------------+");
					 System.out.print("| Entrer le sex : ");
			         String sex = sc.nextLine();
					 System.out.println("+----------------------------------------------------------------------------------------------------------------+");
			         stmt.executeUpdate("UPDATE student SET sex='" + sex + "' WHERE ID='" + id + "'");
					break;
				case 7:
					 System.out.println("\n+----------------------------------------------------------------------------------------------------------------+");
					 System.out.print("| Entrer le nouveau age : ");
			         String age = sc.nextLine();
					 System.out.println("+----------------------------------------------------------------------------------------------------------------+");
			         stmt.executeUpdate("UPDATE student SET age='" + age + "' WHERE ID='" + id + "'");
					break;
				case 8:
					 System.out.println("\n+----------------------------------------------------------------------------------------------------------------+");
					 System.out.print("| Entrer le nouveau CNE : ");
			         String cne_student = sc.nextLine();
					 System.out.println("+----------------------------------------------------------------------------------------------------------------+");
			         stmt.executeUpdate("UPDATE student SET cne_student='" + cne_student + "' WHERE ID='" + id + "'");
					break;
				default:
					break;
				}
	        }
	    }
	}
	// id des etudiants.
	public ArrayList<Integer> idsModuleStudent(int id) throws SQLException {
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM student_module WHERE student_id='" + id + "'");
        ArrayList<Integer> ids = new ArrayList<>();
        while(resultSet.next()) {
        	ids.add(resultSet.getInt("module_id"));
        } 
        return ids;
	}

	public String student_module_avec_note(int id) throws SQLException {
	    ArrayList<String> modules = student_modules(id);
	    ArrayList<Float> notes = moduleNote(id);
	    String module = "+----------------------------------------------------------------------------------------------------------------+\n|||||||||||||||||||||||||||||||||||||||||||| Vos Notes ||||||||||||||||||||||||||||||||||||||||||||||||||||\n+----------------------------------------------------------------------------------------------------------------+";

	    for(int i = 0 ; i < modules.size();i++) {
	    	module=module+"| => le module : "+modules.get(i)+" | la note : "+notes.get(i)+"\n";
	    }   
	    return module;
	}

	public int idClasseStudent(int id) throws SQLException {
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM class_student WHERE student_id='" + id + "'");
        int id1=0;
        while(resultSet.next()) {
        	id1=resultSet.getInt("class_id");
        }
        return id1;
	}
	
	public String classeStudent(int id) throws SQLException {
		String classe=null;
		int id1=idClasseStudent(id);
		ResultSet result_classe =  stmt.executeQuery("SELECT * FROM classe WHERE ID='" + id1 + "'");
		while(result_classe.next()) {
			classe=result_classe.getString("name")+" "+ result_classe.getString("filliere")+" "+result_classe.getString("grade");
		}
		return classe;
	}
	
	public ArrayList<Float> moduleNote(int id) throws SQLException {
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM student_module WHERE student_id='" + id + "'");
        ArrayList<Float> notes = new ArrayList<>();
        
        while(resultSet.next()) {
        	notes.add(resultSet.getFloat("note_module")); 
        }  
        return notes;
	}
	
	
	public void consulterNotes(int id) throws SQLException {
	    String modules = student_module_avec_note(id);
	    System.out.println(modules);
	    ResultSet resultat_student = stmt.executeQuery("SELECT * FROM student WHERE ID='" + id + "'");
	    System.out.println("| Votre note finale du semestre : ");
	    while(resultat_student.next()) {
		    System.out.println(resultat_student.getFloat("note_finale"));
	    }
	}
	
	public ArrayList<String> student_modules(int id) throws SQLException {
	    		ArrayList<String> modules = new ArrayList<>();
	    		ArrayList<Integer> ids = idsModuleStudent(id);
	    		for(int id1 : ids) {
	            ResultSet result_module = stmt.executeQuery("SELECT * FROM module WHERE id='" + id1 + "'");
	            	while (result_module.next()) {
	            	modules.add(result_module.getString("name"));
	            	}
	    		}
	      return modules;
	}
	
	
}
