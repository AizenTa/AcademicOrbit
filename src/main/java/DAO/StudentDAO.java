package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import business.*;
import business.Module;

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
	
	public Etudiant getEtudiantByUsername(String username) throws SQLException {
        ResultSet rs = null;
        Etudiant etud = null;
        try {
        	 rs = stmt.executeQuery("SELECT * FROM student WHERE username = '" + username + "'");

            if (rs.next()) {
            	etud = new Etudiant(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("name"),
                    rs.getString("last_name")
                );
            }
        } finally {
        }
        return etud;
    }
	
private static final String[] DAYS = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi"};

    
public String[][] showClassTimetable(int etudiantId) throws SQLException {
    String[][] timetable = new String[8][5]; 
    
    ResultSet rs = stmt.executeQuery("SELECT class_id FROM class_student WHERE student_id = " + etudiantId);
    int classeId = 0;
    
    while(rs.next()) {
        classeId = rs.getInt("class_id");
    }
    
    ResultSet resultSet = stmt.executeQuery("SELECT module_id, salle_id ,day_of_week, start_time FROM emploisclasses WHERE classe_id = " + classeId);
    while (resultSet.next()) {
        int moduleId = resultSet.getInt("module_id");
        int salle_id = resultSet.getInt("salle_id");
        int prof_id = resultSet.getInt("prof_id");
        String day = resultSet.getString("day_of_week");
        int startTime = resultSet.getInt("start_time");
        
        int indexDay = Arrays.asList(DAYS).indexOf(day);

        timetable[startTime][indexDay] = "module " + moduleId + "prof " + prof_id +" salle " + salle_id; 
    }
    return timetable;
}
	


public ArrayList<Integer> getIDsModulesBystudId(int id) throws SQLException {
    ResultSet resultSet = stmt.executeQuery("SELECT * FROM student_module WHERE student_id='" + id + "'");
    ArrayList<Integer> ids = new ArrayList<>();
    while(resultSet.next()) {
    	ids.add(resultSet.getInt("module_id"));
    } 
    return ids;
}


public List<Module> getModulesByStudId(int studId) throws SQLException {
List<Module> modules = new ArrayList<>();
List<Integer> ids = getIDsModulesBystudId(studId);
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






public float getNoteByStudentIdAndModuleId(int studentId, int moduleId) throws SQLException {
    float note = -1; // Initialize note to -1 indicating no note exists by default
    String query = "SELECT note_module FROM student_module WHERE student_id = " + studentId + " AND module_id = " + moduleId;
    System.out.println("Executing query: " + query);
    ResultSet resultSet = stmt.executeQuery(query);
    if (resultSet.next()) {
        note = resultSet.getFloat("note_module");
    }
    resultSet.close();
    return note;
}
















	// id des etudiants.


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
	
	
}
