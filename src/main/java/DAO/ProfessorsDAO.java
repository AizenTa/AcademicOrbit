package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import business.Classe;
import business.Etudiant;
import business.Professeur;
import business.Module;


public class ProfessorsDAO {
	// attributes 
	MaConnexion connexion;
	Statement stmt;
	Scanner sc = new Scanner(System.in);
	
	// Constructor
	public ProfessorsDAO(MaConnexion connexion) {
		this.connexion = connexion;
		stmt = connexion.getStmt();
	}
	
	// Methods
	
	public Professeur getProfByUsername(String username) throws SQLException {
        ResultSet rs = null;
        Professeur prof = null;
        try {
        	 rs = stmt.executeQuery("SELECT * FROM prof WHERE username = '" + username + "'");

            if (rs.next()) {
                prof = new Professeur(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("name"),
                    rs.getString("last_name")
                );
            }
        } finally {
        }
        return prof;
    }
	///////////////////////////////
	
    
    
    private static final String[] DAYS = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi"};

    
    public String[][] showProfessorTimetable(int profId) throws SQLException {
        String[][] timetable = new String[8][5];   
        ResultSet resultSet = stmt.executeQuery("SELECT salle_id, module_id, day_of_week, start_time, classe_id FROM emploisclasses WHERE prof_id = " + profId);
        while (resultSet.next()) {
            int classe_id = resultSet.getInt("classe_id");
            int salle_id = resultSet.getInt("salle_id");
            int module_id = resultSet.getInt("module_id");
            String day = resultSet.getString("day_of_week");
            int startTime = resultSet.getInt("start_time");
            int indexDay = Arrays.asList(DAYS).indexOf(day);
            timetable[startTime][indexDay] = "Salle " + salle_id + " Module " + module_id + " Classe " + classe_id;
        }
        return timetable;
    }
    
 // Méthode pour obtenir le nombre total d'étudiants
    public int getTotalStudents() throws SQLException {
        int totalStudents = 0;
        try (ResultSet resultSet = stmt.executeQuery("SELECT COUNT(*) AS total FROM student")) {
            if (resultSet.next()) {
                totalStudents = resultSet.getInt("total");
            }
        }
        return totalStudents;
    }

 // Méthode pour obtenir le nombre total de notes enregistrées par un professeur
    public int getTotalGradesEntered(int professorId) throws SQLException {
        int totalGradesEntered = 0;
        try {
            // Récupérer les modules associés au professeur
            List<Integer> moduleIds = getIDsModulesByProfId(professorId);
            for (int moduleId : moduleIds) {
                // Récupérer les notes enregistrées pour chaque module
                ResultSet resultSet = stmt.executeQuery("SELECT COUNT(*) AS total FROM student_module WHERE module_id = " + moduleId);
                if (resultSet.next()) {
                    totalGradesEntered += resultSet.getInt("total");
                }
            }
        } catch (SQLException e) {
            // Gérer l'exception selon votre logique
            e.printStackTrace();
        }
        return totalGradesEntered;
    }
    // Method to get modules by professor ID
    public List<Integer> getIDsModulesByProfId(int profId) throws SQLException {
        List<Integer> ids = new ArrayList<>();
        ResultSet resultSet = stmt.executeQuery("SELECT module_id FROM prof_module WHERE professor_id = " + profId);
        while (resultSet.next()) {
            ids.add(resultSet.getInt("module_id"));
        }
        resultSet.close();
        return ids;
    }
    public List<Module> getModulesByProfId(int profId) throws SQLException {
        List<Module> modules = new ArrayList<>();
        List<Integer> ids = getIDsModulesByProfId(profId);
        for (int id : ids) {
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM module WHERE id = " + id);
            while (resultSet.next()) {
            	modules.add(new Module(resultSet.getInt("id"), 
                        resultSet.getString("name"), 
                        resultSet.getInt("nbr_heures")));
}           
            resultSet.close();
            }
        return modules;
        }
    
    
    public List<Integer> getIDsClassesByModuleId(int moduleId) throws SQLException {
        List<Integer> ids = new ArrayList<>();
        ResultSet resultSet = stmt.executeQuery("SELECT classe_id FROM classe_module WHERE module_id = " + moduleId);
        while (resultSet.next()) {
            ids.add(resultSet.getInt("classe_id"));
        }
        resultSet.close();
        return ids;
    }

    public List<Classe> getClassesByModuleId(int moduleId) throws SQLException {
        List<Classe> classes = new ArrayList<>();
        List<Integer> ids = getIDsClassesByModuleId(moduleId);
        for (int id : ids) {
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM classe WHERE id = " + id);
            while (resultSet.next()) {
                classes.add(new Classe(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("filliere"), resultSet.getString("grade")));
            }
            resultSet.close();
        }
        return classes;
    }
    
    public List<Etudiant> getStudentsByModuleId(int moduleId) throws SQLException {
        List<Etudiant> students = new ArrayList<>();
        // Retrieve classes associated with the module
        List<Classe> classes = getClassesByModuleId(moduleId);
        for (Classe classe : classes) {
            // Query for students in each class
            ResultSet resultSet = stmt.executeQuery("SELECT student.* FROM student JOIN class_student ON student.id = class_student.student_id WHERE class_student.class_id = " + classe.getId());
            while (resultSet.next()) {
                students.add(new Etudiant(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("last_name")));
            }
        }
        return students;
    }

    public float getNoteByStudentIdAndModuleId(int studentId, int moduleId) throws SQLException {
        float note = -1; // Initialize note to -1 indicating no note exists by default
        ResultSet resultSet = stmt.executeQuery("SELECT note_module FROM student_module WHERE student_id = " + studentId + " AND module_id = " + moduleId);
        if (resultSet.next()) {
            note = resultSet.getFloat("note_module");
        }
        return note;
    }


    // Method to affect note to a student
    public void affecterNoteEtudiant(int moduleId, int studentId, float note) throws SQLException {
        stmt.executeUpdate("INSERT INTO student_module (student_id, module_id, note_module) VALUES (" + studentId + ", " + moduleId + ", " + note + ")");
    }
		
    
    
		// id des professeurs.
	public ArrayList<Integer> idsModuleProf(int id) throws SQLException {
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM prof_module WHERE professor_id='" + id + "'");
        ArrayList<Integer> ids = new ArrayList<>();
        while(resultSet.next()) {
        	ids.add(resultSet.getInt("module_id"));
        } // module vide 
        return ids;
	}
	
		// consulter les classes du professeurs.
	
		// id des classes.
	public ArrayList<Integer> idsClasseProf(int id) throws SQLException {
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM classe_module WHERE module_id='" + id + "'");
        ArrayList<Integer> ids = new ArrayList<>();
        while(resultSet.next()) {
        	ids.add(resultSet.getInt("classe_id"));
        }
        return ids;
	}
	
		

	
	// id des etudiants
	public ArrayList<Integer> idsStudentModule(int id) throws SQLException {
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM class_student WHERE class_id='" + id + "'");
		ArrayList<Integer> ids = new ArrayList<>();
        while(resultSet.next()) {
        	ids.add(resultSet.getInt("student_id"));
        }
        return ids;
	}
			
	
	public ArrayList<String> prof_modules(int id) throws SQLException {
		ArrayList<String> modules=new ArrayList<>();
		ArrayList<Integer> ids = idsModuleProf(id);
		for(int id1 : ids) {
			ResultSet result_module =  stmt.executeQuery("SELECT * FROM module WHERE ID='" + id1 + "'");
			while(result_module.next()) {
				modules.add(result_module.getString("name"));
			}
		}
		return modules;
	}
}
