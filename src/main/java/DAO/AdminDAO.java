package DAO;



import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import business.Admin;
import business.Classe;
import business.Etudiant;
import business.Professeur;
import business.Statistics;
import business.Module;

public class AdminDAO {

/////////////////// Attributs
	MaConnexion connexion;
	Statement stmt;
	Scanner sc = new Scanner(System.in);
	
    static Random rand = new Random(); 
    int i,j,k,m,n;
    private static final String[] DAYS ={"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi"};

/////////////////// Constructor
	public AdminDAO(MaConnexion connexion) {
		this.connexion = connexion;
		stmt = connexion.getStmt();
	}
	    
/////////////////// Methodes

	public List<Admin> getAllAdminsInfos() throws SQLException {
		List<Admin> admins = new ArrayList<>();
		ResultSet rs = stmt.executeQuery("SELECT * FROM admin");
			while (rs.next()) {
				Admin admin = new Admin(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("last_name")
				); 
				admins.add(admin);
			}
		return admins;
	}
	
	public void supprimerAdmin(String id) throws SQLException {
        stmt.executeUpdate("DELETE FROM admin WHERE id='" + id + "'");
    }
	 
	 public void ajouterAdmin(Admin admin) throws SQLException {
	        String admin_username = admin.getUsername();
	        String admin_password = admin.getPassword();
	        String nom = admin.getNom();
	        String prenom = admin.getPrenom(); 
	        
	        String hashed_password = hashString(admin_password);
	    	stmt.executeUpdate("INSERT INTO admin (username,password,name, last_name) VALUES ('" + admin_username + "','" + hashed_password + "','" + nom + "','" + prenom +"')");			
	    }
	 public Statistics getStatistics() throws SQLException {
	        int numberOfStudents = 0;
	        int numberOfProfessors = 0;
	        int numberOfClasses = 0;
	        int numberOfModules = 0;
	        int maleStudents = 0;
	        int femaleStudents = 0;
	        int maleProfessors = 0;
	        int femaleProfessors = 0;

	        // Requête pour le nombre d'étudiants
	        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS count FROM student");
	        if (rs.next()) {
	            numberOfStudents = rs.getInt("count");
	        }

	        // Requête pour la répartition des sexes des étudiants
	        rs = stmt.executeQuery("SELECT sex, COUNT(*) AS count FROM student GROUP BY sex");
	        while (rs.next()) {
	            if ("Homme".equalsIgnoreCase(rs.getString("sex"))) {
	                maleStudents = rs.getInt("count");
	            } else if ("Femme".equalsIgnoreCase(rs.getString("sex"))) {
	                femaleStudents = rs.getInt("count");
	            }
	        }

	        // Requête pour le nombre de professeurs
	        rs = stmt.executeQuery("SELECT COUNT(*) AS count FROM prof");
	        if (rs.next()) {
	            numberOfProfessors = rs.getInt("count");
	        }

	        // Requête pour la répartition des sexes des professeurs
	        rs = stmt.executeQuery("SELECT sex, COUNT(*) AS count FROM prof GROUP BY sex");
	        while (rs.next()) {
	            if ("Homme".equalsIgnoreCase(rs.getString("sex"))) {
	                maleProfessors = rs.getInt("count");
	            } else if ("Femme".equalsIgnoreCase(rs.getString("sex"))) {
	                femaleProfessors = rs.getInt("count");
	            }
	        }

	        // Requête pour le nombre de classes
	        rs = stmt.executeQuery("SELECT COUNT(*) AS count FROM classe");
	        if (rs.next()) {
	            numberOfClasses = rs.getInt("count");
	        }

	        // Requête pour le nombre de modules
	        rs = stmt.executeQuery("SELECT COUNT(*) AS count FROM module");
	        if (rs.next()) {
	            numberOfModules = rs.getInt("count");
	        }

	        return new Statistics(numberOfStudents, numberOfProfessors, numberOfClasses, numberOfModules, maleStudents, femaleStudents, maleProfessors, femaleProfessors);
	    }
		
		
	    public Admin getAdminByUsername(String username) throws SQLException {
	        ResultSet rs = null;
	        Admin admin = null;
	        try {
	        	 rs = stmt.executeQuery("SELECT * FROM admin WHERE username = '" + username + "'");

	            if (rs.next()) {
	                admin = new Admin(
	                    rs.getInt("id"),
	                    rs.getString("username"),
	                    rs.getString("password"),
	                    rs.getString("name"),
	                    rs.getString("last_name")
	                );
	            }
	        } finally {
	        }
	        return admin;
	    }

	    public void updateAdmin(Admin admin) throws SQLException {
	        try {
	            String query = "UPDATE admin SET " +
	                    "username = '" + admin.getUsername() + "', " +
	                    "password = '" + hashString(admin.getPassword()) + "', " +
	                    "name = '" + admin.getNom() + "', " +
	                    "last_name = '" + admin.getPrenom() + "' " +
	                    "WHERE id = " + admin.getId();
	            stmt.executeUpdate(query);
	        } finally {
	        }
	    }
	 public List<Professeur> getAllProfsInfos() throws SQLException {
		 List<Professeur> professors = new ArrayList<>();
	     ResultSet rs = stmt.executeQuery("SELECT * FROM prof");
	     while (rs.next()) {
	        Professeur prof = new Professeur(
	        		rs.getInt("id"),
	                rs.getString("username"),
	                rs.getString("password"),
	                rs.getString("name"),
	                rs.getString("last_name"),
	                rs.getString("address"),
	                rs.getString("sex"),
	                rs.getInt("age"),
	                rs.getString("cne_prof")
	        );
	        professors.add(prof);
	       }
	     return professors;
	 }

	 public void ajouterProf(Professeur prof) throws SQLException {
	        String prof_username = prof.getUsername();
	        String prof_password = prof.getPassword();
	        String nom = prof.getNom();
	        String prenom = prof.getPrenom(); 
	        String address = prof.getAddress();
	        String sex = prof.getSex();
	        int age = prof.getAge();
	        String cne_prof = prof.getCne_prof(); 
	        
	        String hashed_password = hashString(prof_password);
	    	stmt.executeUpdate("INSERT INTO prof (username,password,name, last_name, address, sex, age, cne_prof) VALUES ('" + prof_username + "','" + hashed_password + "','" + nom + "','" + prenom + "','" + address + "','" + sex + "'," + age + ",'" + cne_prof + "')");			
	    }
	
	 public void modifierProf(Professeur prof, int id) throws SQLException { 
		    String prof_username = prof.getUsername();
	        String prof_password = prof.getPassword();
	        String nom = prof.getNom();
	        String prenom = prof.getPrenom(); 
	        String address = prof.getAddress();
	        String sex = prof.getSex();
	        int age = prof.getAge();
	        String cne_prof = prof.getCne_prof(); 
	        String hashed_password = hashString(prof_password);
            stmt.executeUpdate("UPDATE prof SET username = '" + prof_username + "',password = '" + hashed_password + "',name = '" + nom + "', last_name = '" + prenom + "', address = '" + address + "', sex = '" + sex + "', age = '" + age + "', cne_prof = '" + cne_prof + "' WHERE id = " + id);
	 }
		 		
	 public void supprimerProf(String id) throws SQLException {  
		stmt.executeUpdate("DELETE FROM prof WHERE ID='" + id+"'");
	 }
	 
	 /// Etudiant
	 public void ajouterEtudiant(Etudiant etudiant) throws SQLException {
	        String etudiant_username = etudiant.getUsername();
	        String etudiant_password = etudiant.getPassword();
	        String nom = etudiant.getNom();
	        String prenom = etudiant.getPrenom(); 
	        String address = etudiant.getAddress();
	        String sex = etudiant.getSex();
	        int age = etudiant.getAge();
	        String cne_etudiant = etudiant.getCne_student(); 
	        
	        String hashed_password = hashString(etudiant_password);
	    	stmt.executeUpdate("INSERT INTO student (username,password,name, last_name, address, sex, age, cne_student) VALUES ('" + etudiant_username + "','" + hashed_password + "','" + nom + "','" + prenom + "','" + address + "','" + sex + "'," + age + ",'" + cne_etudiant + "')");			
	    } 
	
	 public List<Etudiant> getAllEtudiantInfos() throws SQLException {
			List<Etudiant> etudiants = new ArrayList<>();
			ResultSet rs = stmt.executeQuery("SELECT * FROM student");
				while (rs.next()) {
					int id = rs.getInt(1);
					Etudiant etudiant = new Etudiant(
							rs.getInt("id"),
							rs.getString("name"),
							rs.getString("last_name"),
							rs.getString("address"),
							rs.getString("sex"),
							rs.getInt("age"),
							rs.getString("cne_student"),
							rs.getFloat("note_finale"),
							rs.getInt("abscence_hours"),
							searchClasseById(searchClasseEtudiant(id)).getName()
					); 
					etudiants.add(etudiant);
				}
			return etudiants;
		}
	 
	 public List<Etudiant> getAllEtudiantClasse(int id) throws SQLException {
			List<Etudiant> etudiants = new ArrayList<>();
			List<Integer> ids = new ArrayList<>();
			ResultSet rs = stmt.executeQuery("SELECT student_id FROM class_student WHERE class_id='"+id+"'");
				while (rs.next()) {
					int student_id = rs.getInt("student_id");
					ids.add(student_id);
				}
			etudiants=getAllEtudiantById(ids);
			return etudiants;
		}
	 
	 public List<Etudiant> getAllEtudiantById(List<Integer> ids) throws SQLException {
		 List<Etudiant> etudiants = new ArrayList<>();	
		 for(int id : ids) {
				ResultSet rs = stmt.executeQuery("SELECT * FROM student WHERE ID='"+id+"'");
					while (rs.next()) {
						Etudiant etudiant = new Etudiant(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getString("last_name"),
								rs.getString("address"),
								rs.getString("sex"),
								rs.getInt("age"),
								rs.getString("cne_student"),
								rs.getFloat("note_finale"),
								rs.getInt("abscence_hours")
						); 
						etudiants.add(etudiant);
					}
			}
			return etudiants;
			}
	 
	 public void modifierEtudiant(Etudiant etudiant, int id) throws SQLException { 
		    String etudiant_username = etudiant.getUsername();
	        String etudiant_password = etudiant.getPassword();
	        String nom = etudiant.getNom();
	        String prenom = etudiant.getPrenom(); 
	        String address = etudiant.getAddress();
	        String sex = etudiant.getSex();
	        int age = etudiant.getAge();
	        String cne_etudiant = etudiant.getCne_student(); 
	        int abscence_hours = etudiant.getAbscence_hours();
	        String hashed_password = hashString(etudiant_password);
         stmt.executeUpdate("UPDATE student SET username = '" + etudiant_username + "',password = '" + hashed_password + "',name = '" + nom + "', last_name = '" + prenom + "', address = '" + address + "', sex = '" + sex + "', age = '" + age + "', cne_student = '" + cne_etudiant + "', abscence_hours = '" + abscence_hours + "' WHERE id = " + id);
	 }
	 

	 public void supprimerEtudiant(String id) throws SQLException {  
			stmt.executeUpdate("DELETE FROM student WHERE ID='" + id+"'");
		 }
	 
	 // classe
	 
	 public void ajouterClasse(Classe classe) throws SQLException {
	        String classe_name = classe.getName();
	        String classe_filliere = classe.getFilliere();
	        String classe_annee = classe.getGrade(); 
	        
	    	stmt.executeUpdate("INSERT INTO classe (name,filliere,grade) VALUES ('" + classe_name + "','" + classe_filliere + "','" + classe_annee + "')");			
	    } 
	
	 public void modifierClasse(Classe classe, int id) throws SQLException { 
		    String nom = classe.getName();
	        String filliere = classe.getFilliere(); 
	        String annee = classe.getGrade();
	        
      stmt.executeUpdate("UPDATE classe SET name = '" + nom + "',filliere = '" + filliere + "',grade = '" + annee + "' WHERE id = " + id);
	 }
	 
	 public List<Classe> getAllClasseInfos() throws SQLException {
			List<Classe> classes = new ArrayList<>();
			ResultSet rs = stmt.executeQuery("SELECT * FROM classe");
				while (rs.next()) {
					Classe classe = new Classe(
							rs.getInt("id"),
							rs.getString("name"),
							rs.getString("filliere"),
							rs.getString("grade")
					); 
					classes.add(classe);
				}
			return classes;
		}
	 
	 public void supprimerModule(String id) throws SQLException {
			stmt.executeUpdate("DELETE FROM module WHERE id='" + id + "'");
		}
	 
	 public void ajouterModule(Module module,String[] classIds,String[] profIds) throws SQLException {
			String name = module.getName();
			int nbr_heures = module.getNbr_heures();
			stmt.executeUpdate("INSERT INTO module (name, nbr_heures) VALUES ('" + name + "','" + nbr_heures +"')",Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()) {
				int module_id = rs.getInt(1);
				
				for(String profId : profIds) {
 					int profid = Integer.parseInt(profId);
					stmt.executeUpdate("INSERT INTO prof_module (professor_id, module_id) VALUES ('" + profid + "','" + module_id +"')");
				}
				
 				for(String classId : classIds) {
 					int classid = Integer.parseInt(classId);
					stmt.executeUpdate("INSERT INTO classe_module (classe_id, module_id) VALUES ('" + classId + "','" + module_id +"')");
 					emploisClasse(classid,module_id);
 				}//  					emploisClasse(profid,module_id);
 				
			}
	 }

	 public List<Integer> getAllIdsClasses() throws SQLException {
	        List<Integer> ids = new ArrayList<>();

	        	ResultSet rs = stmt.executeQuery("SELECT id FROM classe");
	            while (rs.next()) {
	            	int id = rs.getInt("id");
	            	ids.add(id);
	            }
		        return ids; 
	        }
	 
	 public List<Classe> getAvailableClasses() throws SQLException {
	        List<Classe> classes = new ArrayList<>();
	        List<Integer> ids = getAllIdsClasses();
	        for(int id : ids) {
	        	ResultSet rs = stmt.executeQuery("SELECT module_id FROM classe_module WHERE classe_id="+id);
                int som = 0;  
	        	if(rs.next()) {
		                som = som + nbr_heures_module(rs.getInt("module_id"));
		            } 
	        	  if (som < 24) {
	                  Classe classe = searchClasseById(id);
	                  if (classe != null) {
	                      classes.add(classe);
	                  }
	              }
	        }
	     return classes; 
	  }
	   
	 public Classe searchClasseById(int id) throws SQLException {
			ResultSet rs = stmt.executeQuery("SELECT id,name,filliere,grade FROM classe WHERE id="+id);
			Classe classe = null;
			if (rs.next()) {
				 classe = new Classe(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("filliere"),
						rs.getString("grade")
				);
			}
			return classe;
	 }
	 
	 public int searchClasseEtudiant(int etudiantid) throws SQLException {
			ResultSet rs = stmt.executeQuery("SELECT class_id FROM class_student WHERE student_id="+etudiantid);
			int id = 0; 
			if (rs.next()) {
				id=rs.getInt("class_id");
			}
			return id;
	 }
	 
	 public List<Module> getAllModulesInfos() throws SQLException {
			List<Module> modules = new ArrayList<>();
			ResultSet rs = stmt.executeQuery("SELECT * FROM module");
			while (rs.next()) {
				Module module = new Module(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getInt("nbr_heures")
				);
				modules.add(module);
			}
			return modules;
		}
	 public void supprimerClasse(String id) throws SQLException {  
		stmt.executeUpdate("DELETE FROM classe WHERE ID='" + id+"'");
		stmt.executeUpdate("DELETE FROM class_student WHERE ID='" + id+"'");
		stmt.executeUpdate("DELETE FROM classe_module WHERE ID='" + id+"'");
	 }
	 
	/////////////////////////////////////////////////prof
	 
	 
	 
	 public List<Integer> getAllIdsProfs() throws SQLException {
	        List<Integer> ids = new ArrayList<>();

	        	ResultSet rs = stmt.executeQuery("SELECT id FROM prof");
	            while (rs.next()) {
	            	int id = rs.getInt("id");
	            	ids.add(id);
	            }
		        return ids; 
	        }
	 
	 public List<Professeur> getAvailableProfs() throws SQLException {
	        List<Professeur> profs = new ArrayList<>();
	        List<Integer> ids = getAllIdsProfs();
	        for(int id : ids) {
	        	ResultSet rs = stmt.executeQuery("SELECT module_id FROM prof_module WHERE professor_id="+id);
             int som = 0;  
	        	if(rs.next()) {
		                som = som + nbr_heures_module(rs.getInt("module_id"));
		            } 
	        	  if (som < 20) {
	                  Professeur prof = searchProfById(id);
	                  if (profs != null) {
	                      profs.add(prof);
	                  }
	              }
	        }
	     return profs; 
	  }
	   
	 public Professeur searchProfById(int id) throws SQLException {
			ResultSet rs = stmt.executeQuery("SELECT id,name,last_name,cne_prof FROM prof WHERE id="+id);
			Professeur prof = null;
			if (rs.next()) {
				 prof = new Professeur(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("last_name"),
						rs.getString("cne_prof")
				);
			}
			return prof;
	 }
	
	 /////////////////////////////////////////////////////
	public String hashString(String input) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] encodedHash = digest.digest(input.getBytes());
			return bytesToHex(encodedHash);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	private static String bytesToHex(byte[] hash) {
		StringBuilder hexString = new StringBuilder(2 * hash.length);
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}

	public String[][] showProfessorTimetable(int profId) throws SQLException {
	    String[][] timetable = new String[8][5];   
	    ResultSet resultSet = stmt.executeQuery("SELECT salle_id, module_id, day_of_week, start_time, classe_id FROM EmploisClasses WHERE prof_id = " + profId);
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
	
	private void emploisClasse(int classeId, int moduleId) throws SQLException {
        List<Integer> availableSlots = new ArrayList<>();
        List<Integer> occupiedSlots = new ArrayList<>();
        int[][] emplois = new int[5][8];
        int[] dailyHours = new int[5]; // For tracking hours per day
        int totalWeeklyHours = 0; // For tracking total hours per week

        // Get occupied slots for the class
        ResultSet resultSet = stmt.executeQuery("SELECT day_of_week, start_time, end_time FROM EmploisClasses WHERE classe_id = " + classeId);
        while (resultSet.next()) {
            String day = resultSet.getString("day_of_week");
            int startTime = resultSet.getInt("start_time");
            int endTime = resultSet.getInt("end_time");
            int indexDay = Arrays.asList(DAYS).indexOf(day);

            for (int i = startTime; i < endTime; i++) {
                occupiedSlots.add(indexDay * 10 + i);
                dailyHours[indexDay]++;
                totalWeeklyHours++;
            }
        }

        // Get available slots for the class
        for (int day = 0; day < 5; day++) {
            for (int period = 0; period < 8; period++) {
                int slot = day * 10 + period;
                if (!occupiedSlots.contains(slot)) {
                    availableSlots.add(slot);
                }
            }
        }

        int nbrHeures = nbr_heures_module(moduleId);

        if (availableSlots.size() >= nbrHeures) {
            // Get a random room
            int salleId = getRandomSalle();
            int profId = getProfessorIdByModule(moduleId);

            for (int i = 0; i < nbrHeures; i++) {
                if (availableSlots.isEmpty()) {
                    System.out.println("Not enough available slots for the given course credits.");
                    return;
                }

                int slotIndex = rand.nextInt(availableSlots.size());
                int slot = availableSlots.get(slotIndex);
                int period = slot % 10;
                int day = slot / 10;

                // Check availability in the room and professor's schedule
                if (!isRoomAvailable(salleId, day, period) || !isProfessorAvailable(profId, day, period)) {
                    availableSlots.remove(slotIndex);
                    i--;
                    continue;
                }

                // Check class daily and weekly hour constraints
                if (dailyHours[day] >= 6 || totalWeeklyHours >= 24) {
                    availableSlots.remove(slotIndex);
                    i--;
                    continue;
                }

                // Check professor continuous hour constraints
                if (!canProfessorTeach(profId, day, period)) {
                    availableSlots.remove(slotIndex);
                    i--;
                    continue;
                }

                emplois[day][period] = moduleId;
                dailyHours[day]++;
                totalWeeklyHours++;
                availableSlots.remove(slotIndex);
            }
            enregistrerEmploi(classeId, moduleId, emplois, salleId);
        } else {
            System.out.println("Not enough available slots for the given course credits.");
        }
    }

    private boolean canProfessorTeach(int profId, int day, int period) throws SQLException {
        int continuousHours = 0;

        // Check for continuous hours in the morning (8h - 12h) and afternoon (14h - 18h)
        if (period < 4) { // Morning
            for (int i = 0; i < 4; i++) {
                String query = "SELECT COUNT(*) AS count FROM EmploisClasses WHERE prof_id = " + profId +
                               " AND day_of_week = '" + DAYS[day] + "' AND start_time = " + i;
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next() && rs.getInt("count") > 0) {
                    continuousHours++;
                }
            }
        } else { // Afternoon
            for (int i = 4; i < 8; i++) {
                String query = "SELECT COUNT(*) AS count FROM EmploisClasses WHERE prof_id = " + profId +
                               " AND day_of_week = '" + DAYS[day] + "' AND start_time = " + i;
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next() && rs.getInt("count") > 0) {
                    continuousHours++;
                }
            }
        }

        return continuousHours < 4;
    }

	private int nbr_heures_module(int module_id) throws SQLException {
        ResultSet resultSet = stmt.executeQuery("SELECT nbr_heures FROM module WHERE id = "+module_id);
        if (resultSet.next()) {
            return resultSet.getInt("nbr_heures");
        } else {
            throw new SQLException("Course not found with ID: " + module_id);
        }
	}
	
	private void enregistrerEmploi(int classeId, int moduleId, int[][] emploi, int salleId) throws SQLException {
	    for (int day = 0; day < DAYS.length; day++) {
	        for (int period = 0; period < 8; period++) {
	            if (emploi[day][period] == moduleId) {
	                String dayOfWeek = DAYS[day];
	                int startTime = period;
	                int endTime = period + 1;
	                int profId = getProfessorIdByModule(moduleId);


	                // Insert into EmploisClasses
	                stmt.executeUpdate("INSERT INTO EmploisClasses (classe_id, module_id, day_of_week, start_time, end_time, salle_id, prof_id) VALUES (" 
	                                    + classeId + ", " + moduleId + ", '" + dayOfWeek + "', " + startTime + ", " + endTime + ", " + salleId + ", " + profId+")");

	           }
	        }
	    }
	}

	private int getProfessorIdByModule(int moduleId) throws SQLException {
	    ResultSet rs = stmt.executeQuery("SELECT professor_id FROM prof_module WHERE module_id = " + moduleId);
	    if (rs.next()) {
	        return rs.getInt("professor_id");
	    } else {
	        throw new SQLException("Professor not found for module ID: " + moduleId);
	    }
	}
	
	private int getRandomSalle() throws SQLException {
	    ResultSet rs = stmt.executeQuery("SELECT id_salle FROM Salles ORDER BY RAND() LIMIT 1");
	    if (rs.next()) {
	        return rs.getInt("id_salle");
	    } else {
	        throw new SQLException("No rooms available.");
	    }
	}
	
	private boolean isProfessorAvailable(int profId, int day, int period) throws SQLException {
	    ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS count FROM EmploisClasses WHERE prof_id = " + profId +
                " AND day_of_week = '" + DAYS[day] + "' AND start_time = " + period);
	    if (rs.next()) {
	        return rs.getInt("count") == 0;
	    }
	    return false;
	}

	private boolean isRoomAvailable(int salleId, int day, int period) throws SQLException {
	    ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS count FROM EmploisClasses WHERE salle_id = " + salleId +
                " AND day_of_week = '" + DAYS[day] + "' AND start_time = " + period);
	    if (rs.next()) {
	        return rs.getInt("count") == 0;
	    }
	    return false;
	}
	
	public void showClassTimetable(int classeId) throws SQLException {
	    String[][] timetable = new String[8][5]; // Inverted to have hours in columns

	    ResultSet resultSet = stmt.executeQuery("SELECT module_id, salle_id ,day_of_week, start_time FROM EmploisClasses WHERE classe_id = " + classeId);
	    while (resultSet.next()) {
	        int moduleId = resultSet.getInt("module_id");
	        int salle_id = resultSet.getInt("salle_id");
	        String day = resultSet.getString("day_of_week");
	        int startTime = resultSet.getInt("start_time");
	        int indexDay = Arrays.asList(DAYS).indexOf(day);

	        timetable[startTime][indexDay] = "module " + moduleId + " salle " + salle_id; // Simplified course display
	    }

	    printTimetable(timetable, "Timetable for class ID: " + classeId);
	}
	
	public void showRoomTimetable(int salleId) throws SQLException {
	    String[][] timetable = new String[8][5]; // Inverted to have hours in columns

	    ResultSet resultSet = stmt.executeQuery("SELECT classe_id, day_of_week, start_time FROM EmploisClasses WHERE salle_id = " + salleId);
	    while (resultSet.next()) {
	        int classe_id = resultSet.getInt("classe_id");
	        String day = resultSet.getString("day_of_week");
	        int startTime = resultSet.getInt("start_time");
	        int indexDay = Arrays.asList(DAYS).indexOf(day);

	        timetable[startTime][indexDay] = "classe " + classe_id; // Simplified course display
	    }

	    printTimetable(timetable, "Timetable for room ID: " + salleId);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 
	 
	 
	 public void ajouterAdmin() throws SQLException {
		char reponse = 'o';
		while (reponse == 'o') {
			System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||| Ajoutez Admin |||||||||||||||||||||||||||||||||||||||||||||||||||");
			System.out.println("+----------------------------------------------------------------------------------------------------------------+");
			System.out.print("| Entrer username : ");
			String admin_username = sc.nextLine();
			System.out.print("| Entrer password : ");
			String admin_password = sc.nextLine();
			System.out.print("| Entrer nom : ");
			String admin_nom = sc.nextLine();
			System.out.print("| Entrer prenom : ");
			String admin_prenom = sc.nextLine();

			String hashed_username = hashString(admin_username);
			String hashed_password = hashString(admin_password);

			stmt.executeUpdate("INSERT INTO admin (username, password, name, last_name) VALUES ('" + hashed_username + "','" + hashed_password + "','" + admin_nom + "','" + admin_prenom + "')");
			System.out.println("| + => Vous avez bien ajoutez " + admin_nom + " " + admin_prenom);
			System.out.print("| Voulez vous ajoutez un nouveau admin (o pour oui / n pour non ) : ");
			reponse = sc.next().charAt(0);
			sc.nextLine();
			System.out.println("\n+----------------------------------------------------------------------------------------------------------------+");

		}
	}
	
	public void addClasse() {
	    char reponse = 'o';
	    while (reponse == 'o') {
	        System.out.println("||||||||||||||||||||||||||||||||||||||||||||||| Ajoutez la classe ||||||||||||||||||||||||||||||||||||||||||||||||");
			System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	        System.out.print("| Entrer le nom de la classe  :");
	        String name_classe = sc.nextLine();
			System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	        System.out.print("| Entrer la filiere de la classe : ");
	        String filiere_classe = sc.nextLine();
			System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	        System.out.print("|Entrer le grade de la classe : ");
	        String grade_classe = sc.nextLine();

	        try {
	            stmt.executeUpdate("INSERT INTO classe (name, filliere, grade) VALUES ('" + name_classe + "','" + filiere_classe + "','" + grade_classe + "')", Statement.RETURN_GENERATED_KEYS);

	            ResultSet generatedKeys = stmt.getGeneratedKeys();
	            int classId = -1;
	            if (generatedKeys.next()) {
	                classId = generatedKeys.getInt(1);
	            } else {
	                throw new SQLException("Failed to get the generated class ID.");
	            }

	            char reponse_module = 'o';
	            while (reponse_module == 'o') {
					System.out.println("+----------------------------------------------------------------------------------------------------------------+");
					System.out.println("| |||||||||||||||||||||||||||||||||  Ajouter un module pour cette classe |||||||||||||||||||||||||||||||||||||||| ");
					System.out.println("+----------------------------------------------------------------------------------------------------------------+");
					System.out.print("| Choisir l'id de ce module : ");
	                int module_id = sc.nextInt();
					System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	                boolean moduleExists = checkIfModuleExists(module_id);

	                if (moduleExists) {
	                    stmt.executeUpdate("INSERT INTO classe_module (classe_id, module_id) VALUES ('" + classId + "','" + module_id + "')");
	                	emploisClasse(classId,module_id);
	                } else {
	                    System.out.println("Module with ID " + module_id + " does not exist. Please add the module first.");
	                }

	                System.out.print("| Voulez-vous ajouter un autre module à cette classe? (si oui, tapez Oui, si non, tapez n'importe quoi..) : ");
	                reponse_module = sc.next().charAt(0);

	                sc.nextLine();
	            }
				System.out.println("\n+----------------------------------------------------------------------------------------------------------------+");
	            System.out.print("| Voulez-vous ajouter un autre classe? (si oui, tapez Oui, si non, tapez n'importe quoi..)");
	            reponse = sc.next().charAt(0);
				System.out.println("+----------------------------------------------------------------------------------------------------------------+\n");
	            sc.nextLine();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	
	
    
	
	
    


	private void printTimetable(String[][] timetable, String title) {
		System.out.println(title);
		System.out.println("--------------------------------------------------");
		System.out.print("Day/Time | ");
		for (int period = 0; period < 8; period++) {
			int t = period;
			if(t + 8 < 11) {
				System.out.printf("%02d:00 - %02d:00 | ", 8 + t, 8 + t + 1);
			}
			if(t + 8 > 11) {
				System.out.printf("%02d:00 - %02d:00 | ", 8 + t + 2, 8 + t + 3);
			}
		}
		System.out.println();
		System.out.println("--------------------------------------------------");

		for (int day = 0; day < DAYS.length; day++) {
			System.out.printf("%-9s | ", DAYS[day]);
			for (int period = 0; period < 8; period++) {
				if (timetable[period][day] != null) {
					System.out.printf("%-9s | ", timetable[period][day]);
				} else {
					System.out.print("         | ");
				}
			}
			System.out.println();
		}
		System.out.println("--------------------------------------------------");
	}
	


	public void addStudent() {
	    char reponse = 'o';
	    while (reponse == 'o') {
	        System.out.println("|||||||||||||||||||||||||||||||||||||||||||| Ajoutez Etudiant ||||||||||||||||||||||||||||||||||||||||||||||||||||");
			System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	    	System.out.print("| Entrer username d'etudiant : ");
	        String student_username = sc.nextLine();
			System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	        System.out.print("| Entrer un mot de passe pour le prof  : ");
	        String student_password= sc.nextLine();
			System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	        System.out.print("| Entrer le nom de l'etudiant : ");
	        String name = sc.nextLine();
			System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	        System.out.print("| Entrer le last name de l'etudiant " + name + " : ");
	        String last_name = sc.nextLine();
			System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	        System.out.print("| Entrer le address de l'etudiant " + name + " : ");
	        String address = sc.nextLine();
			System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	        System.out.print("| Entrer le sex de l'etudiant " + name + " : ");
	        String sex = sc.nextLine();
			System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	        System.out.print("| Entrer l'age de l'etudiant " + name + " : ");
	        int age = sc.nextInt();
			System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	        sc.nextLine(); 
	        System.out.print("| Entrer le cne de l'etudiant " + name + " : ");
	        String cne_student = sc.nextLine();
			System.out.println("+----------------------------------------------------------------------------------------------------------------+");

			String hashed_username = hashString(student_username);
			String hashed_password = hashString(student_password);

	        try {
	            // Insert the student into the 'students' table
	            stmt.executeUpdate("INSERT INTO student (username,password,name, last_name, address, sex, age, cne_student) VALUES ('"+hashed_username+"','"+ hashed_password+"','"+name + "','" + last_name + "','" + address + "','" + sex + "'," + age + ",'" + cne_student + "')", Statement.RETURN_GENERATED_KEYS);

	            // Get the generated student ID
	            ResultSet generatedKeys = stmt.getGeneratedKeys();
	            int studentId = -1;
	            if (generatedKeys.next()) {
	                studentId = generatedKeys.getInt(1);
	            } else {
	                throw new SQLException("| Failed to get the generated student ID.");
	            }
    			System.out.println("+----------------------------------------------------------------------------------------------------------------+");
		        System.out.println("||||||||||||||||||||||||||||||||||||||| Affecter ce etudiant à une classe ||||||||||||||||||||||||||||||||||||||||");
    			System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	            System.out.print("| Choisir l'id de la classe : ");
	            int classe_id = sc.nextInt();
    			System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	            sc.nextLine();  
	            stmt.executeUpdate("INSERT INTO class_student (class_id, student_id) VALUES (" + classe_id + "," + studentId + ")");
    			System.out.println("\n+----------------------------------------------------------------------------------------------------------------+");
	            System.out.println("| + => Étudiant ajouté avec succès à la classe ");
    			System.out.println("+----------------------------------------------------------------------------------------------------------------+\n");
	        } catch (SQLException e) {
	        	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
		    	System.out.println("|!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!|");
				System.out.println("|                                   Votre informations est fause  -_-                                            |");
		    	System.out.println("|!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!|");
				System.out.println("+----------------------------------------------------------------------------------------------------------------+\n");
		    	addStudent();
	        }
			System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	        System.out.print("| Voulez-vous ajouter un autre étudiant? (si oui, tapez Oui, si non, tapez n'importe quoi..) : ");
	        reponse = sc.next().charAt(0);
			System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	        sc.nextLine();
	    }
	}

	public void ajouterModule() {
	    char reponse = 'o';
	    while (reponse == 'o') {
	        System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||| Ajoutez Module ||||||||||||||||||||||||||||||||||||||||||||||||||");
			System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	        System.out.print("| Entrer le nom du module : ");
	        String name = sc.nextLine();
            System.out.print("| Entrer nbr heures module : ");
            int nbr_heures = sc.nextInt();
			System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	        try {
	            // Insert the module into the 'modules' table
	            stmt.executeUpdate("INSERT INTO module (name,nbr_heures) VALUES ('" + name + "," + nbr_heures +"')", Statement.RETURN_GENERATED_KEYS);

	            // Get the generated module ID
	            ResultSet generatedKeys = stmt.getGeneratedKeys();
	            int moduleId = -1;
	            if (generatedKeys.next()) {
	                moduleId = generatedKeys.getInt(1);
	            } else {
	                throw new SQLException("| Failed to get the generated module ID.");
	            }


	            char reponse_prof = 'o';
	            while (reponse_prof == 'o') {
	    			System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	    	        System.out.println("||||||||||||||||||||||||||||||||||||| Affecter un professeur à ce module |||||||||||||||||||||||||||||||||||||||||");
	    			System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	                System.out.print("| Entrer l'id du professeur : ");
	                int professor_id = sc.nextInt();
	    			System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	                sc.nextLine();  
	                stmt.executeUpdate("INSERT INTO prof_module (professor_id, module_id) VALUES (" + professor_id + "," + moduleId + ")");
	    			System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	                System.out.println("| + => Professeur ajouté avec succès au module.");
	    			System.out.println("+----------------------------------------------------------------------------------------------------------------+\n");
	    			System.out.println("\n+----------------------------------------------------------------------------------------------------------------+");
	                System.out.print("| Voulez-vous ajouter un autre professeur à ce module? (si oui, tapez Oui, si non, tapez n'importe quoi..) : ");
	                reponse_prof = sc.next().charAt(0);
	    			System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	                sc.nextLine();
	            }
	            System.out.println("| + => Module ajouté avec succès.");
	        } catch (SQLException e) {
	        	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
		    	System.out.println("|!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!|");
				System.out.println("|                                  Votre informations est fause  -_-                                             |");
		    	System.out.println("|!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!|");
				System.out.println("+----------------------------------------------------------------------------------------------------------------+\n");
		    	ajouterModule();
	        }
	        
			System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	        System.out.print("| Voulez-vous ajouter un autre module? (si oui, tapez Oui, si non, tapez n'importe quoi..) : ");
	        reponse = sc.next().charAt(0);
			System.out.println("+----------------------------------------------------------------------------------------------------------------+\n");
	        sc.nextLine();
	    }
	}


	// modifier section 

	

	public void modifierStudent() {
	    char reponse = 'o';
	    while (reponse == 'o') {
	    	System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||| Modifier Etudiant |||||||||||||||||||||||||||||||||||||||||||||||");
			System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	        System.out.print("| Entrer l'id de l'étudiant à modifier : ");
	        int id_student = sc.nextInt();
			System.out.println("+----------------------------------------------------------------------------------------------------------------+\n");
	        sc.nextLine();
			System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	        System.out.println("| Choisir le champ à modifier (1:username, 2:password, 3: Nom, 4: Prenom, 5: Adresse, 6: Sexe, 7: Age, 8: CNE Etudiant, 9: Modifier la classe) : ");
	        int reponse_champ = sc.nextInt();
			System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	        sc.nextLine();

	        try {
	            switch (reponse_champ) {
	            	case 1:
	    				System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	            		System.out.print("| Entrer le nom de l'étudiant : ");
	                    String username = sc.nextLine();
	    				System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	                    stmt.executeUpdate("UPDATE student SET username = '" + username + "' WHERE ID='" + id_student+"'");
	                    break;
	                case 2:
	    				System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	                    System.out.print("| Entrer le prénom de l'étudiant : ");
	                    String password = sc.nextLine();
	    				System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	                    stmt.executeUpdate("UPDATE student SET password = '" + password + "' WHERE ID='" + id_student+"'");
	                    break;
	                case 3:
	    				System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	                    System.out.print("| Entrer le nom de l'étudiant : ");
	                    String student_name = sc.nextLine();
	    				System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	                    stmt.executeUpdate("UPDATE student SET name = '" + student_name + "' WHERE ID='" + id_student+"'");
	                    break;
	                case 4:
	    				System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	                    System.out.print("| Entrer le prénom de l'étudiant : ");
	                    String student_prenom = sc.nextLine();
	    				System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	                    stmt.executeUpdate("UPDATE student SET last_name = '" + student_prenom + "' WHERE ID='" + id_student+"'");
	                    break;
	                case 5:
	    				System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	                    System.out.print("| Entrer l'adresse de l'étudiant : ");
	                    String student_address = sc.nextLine();
	    				System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	                    stmt.executeUpdate("UPDATE student SET address = '" + student_address + "' WHERE ID='" + id_student+"'");
	                    break;
	                case 6:
	    				System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	                    System.out.print("| Entrer le sexe de l'étudiant : ");
	                    String student_sex = sc.nextLine();
	    				System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	                    stmt.executeUpdate("UPDATE student SET sex = '" + student_sex + "' WHERE ID='" + id_student+"'");
	                    break;
	                case 7:
	    				System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	                    System.out.print("| Entrer l'âge de l'étudiant :");
	                    int student_age = sc.nextInt();
	    				System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	                    sc.nextLine();  
	                    stmt.executeUpdate("UPDATE student SET age = " + student_age + " WHERE ID='" + id_student+"'");
	                    break;
	                case 8:
	    				System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	                    System.out.print("| Entrer le CNE de l'étudiant :");
	                    String student_cne = sc.nextLine();
	    				System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	                    stmt.executeUpdate("UPDATE student SET cne_student = '" + student_cne + "' WHERE ID='" + id_student+"'");
	                    break;
	                case 9:
	    				System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	                    System.out.print("| Choisir l'id de la nouvelle classe : ");
	                    int id_classe_student = sc.nextInt();
	    				System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	                    sc.nextLine();  
	                    stmt.executeUpdate("UPDATE class_student SET class_id = " + id_classe_student + " WHERE student_id='" + id_student+"'");
	                    break;
	                default:
						System.out.println("+----------------------------------------------------------------------------------------------------------------+");
						System.out.println("|!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!|");
						System.out.println("|                                      CHAMP INVALIDE       -_-                                                  |");
						System.out.println("|!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!|");
						System.out.println("+----------------------------------------------------------------------------------------------------------------+\n");
						modifierStudent();
	    				break;
	            }
            	System.out.println("\n+----------------------------------------------------------------------------------------------------------------+");
	            System.out.println("| / => Étudiant modifié avec succès.");
            	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	        } catch (SQLException e) {
				System.out.println("+----------------------------------------------------------------------------------------------------------------+");
				System.out.println("|!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!|");
				System.out.println("|                                      CHAMP INVALIDE       -_-                                                  |");
				System.out.println("|!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!|");
				System.out.println("+----------------------------------------------------------------------------------------------------------------+\n");
				modifierStudent();
	        }
        	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	        System.out.print("| Voulez-vous modifier un autre étudiant? ('o' pour oui, 'n'importe quel caractère' pour non) : ");
	        reponse = sc.next().charAt(0);
        	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	        sc.nextLine();
	    }
	}
	
	public void modifierModule() {
	    char reponse = 'o';
	    while (reponse == 'o') {
	    	System.out.println("||||||||||||||||||||||||||||||||||||||||||||||| Modifier Module ||||||||||||||||||||||||||||||||||||||||||||||||||");
        	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	        System.out.print("| Entrer l'id du module à modifier : ");
	        int id_module = sc.nextInt();  
        	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
        	sc.nextLine();
        	System.out.print("| Choisir le champ à modifier (1: Nom, 2: Modifier le professeur qui enseigne ce module) : ");
	        int reponse_champ = sc.nextInt();
	        sc.nextLine();
        	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	        try {
	            switch (reponse_champ) {
	                case 1:
	                	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	                    System.out.print("| Entrer le nom du module  : ");
	                    String module_name = sc.nextLine();
	                	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	                    stmt.executeUpdate("UPDATE module SET name = '" + module_name + "' WHERE ID='" + id_module+"'");
	                    break;
	                case 2:
	                	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	                    System.out.print("| Entrer l'id du nouveau professeur qui a enseigné ce module : ");
	                    int id_module_prof = sc.nextInt();
	                	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	                    sc.nextLine();  
	                    
	                    boolean moduleExists = checkIfModuleExists(id_module);
	                    boolean professorExists = checkIfProfessorExists(id_module_prof);

	                    if (moduleExists && professorExists) {
	                        stmt.executeUpdate("UPDATE prof_module SET professor_id = " + id_module_prof + " WHERE module_id='" + id_module+"'");
	                    	System.out.println("\n+----------------------------------------------------------------------------------------------------------------+");
	                        System.out.println("| / => Module modifié avec succès.");
	                    	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	                    } else {
	                    	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	                        System.out.println("| x => Le module ou le professeur n'existe pas.");
	                    	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	                    }
	                    break;
	                default:
						System.out.println("+----------------------------------------------------------------------------------------------------------------+");
						System.out.println("|!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!|");
						System.out.println("|                                      CHAMP INVALIDE       -_-                                                  |");
						System.out.println("|!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!|");
						System.out.println("+----------------------------------------------------------------------------------------------------------------+\n");
						modifierModule();
	    				break;
	            }

	        } catch (SQLException e) {
				System.out.println("+----------------------------------------------------------------------------------------------------------------+");
				System.out.println("|!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!|");
				System.out.println("|                                       ID INVALIDE       -_-                                                    |");
				System.out.println("|!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!|");
				System.out.println("+----------------------------------------------------------------------------------------------------------------+\n");
				modifierModule();
	        }
        	System.out.println("\n+----------------------------------------------------------------------------------------------------------------+");
	        System.out.print("| Voulez-vous modifier un autre module? ('o' pour oui, 'n'importe quel caractère' pour non) : ");
	        reponse = sc.next().charAt(0);
        	System.out.println("+----------------------------------------------------------------------------------------------------------------+\n");
	        sc.nextLine();
	    }
	}

	
	public void modifierClasse() {
	    char reponse = 'o';
	    while (reponse == 'o') {
	    	System.out.println("|||||||||||||||||||||||||||||||||||||||||||| Modifier Classe |||||||||||||||||||||||||||||||||||||||||||||||||||||");
        	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	        System.out.print("| Entrer l'id de la classe : ");
	        int id_classe = sc.nextInt();
        	System.out.println("+----------------------------------------------------------------------------------------------------------------+\n");
	        sc.nextLine();
        	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	        System.out.print("| Choisir le champ à modifier (1: Nom, 2: Filière, 3: Grade, 4: Ajouter un module, 5: Supprimer un module, 6: Ajouter un étudiant, 7: Supprimer un étudiant) : ");
	        int reponse_champ = sc.nextInt();
        	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	        sc.nextLine();

	        try {
	            switch (reponse_champ) {
	                case 1:
	                	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	                    System.out.print("| Entrer le nom de la classe : ");
	                    String classe_name = sc.nextLine();
	                	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	                    stmt.executeUpdate("UPDATE classe SET name = '" + classe_name + "' WHERE ID='" + id_classe+"'");
	                    break;
	                case 2:
	                	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	                    System.out.print("| Entrer la filière de la classe : ");
	                    String classe_filliere = sc.nextLine();
	                	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	                    stmt.executeUpdate("UPDATE classe SET filliere = '" + classe_filliere + "' WHERE id = " + id_classe);
	                    break;
	                case 3:
	                	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	                    System.out.print("| Entrer le grade de la classe : ");
	                    String classe_grade = sc.nextLine();
	                	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	                    stmt.executeUpdate("UPDATE classe SET grade = '" + classe_grade + "' WHERE ID='" + id_classe+"'");
	                    break;
	                case 4:
	                	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	                    System.out.print("| Entrer l'id du module à ajouter à cette classe : ");
	                    int id_module_classe_add = sc.nextInt();
	                	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	                    sc.nextLine();
	                    stmt.executeUpdate("INSERT INTO classe_module (classe_id, module_id) VALUES (" + id_classe + ", " + id_module_classe_add + ")");
	                    break;
	                case 5:
	                	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	                    System.out.print("| Entrer l'id du module à supprimer de cette classe : ");
	                    int id_module_classe_supp = sc.nextInt();
	                	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	                    sc.nextLine();  
	                    stmt.executeUpdate("DELETE FROM classe_module WHERE classe_id = " + id_classe + " AND module_id ='" + id_module_classe_supp+"'");
	                    break;
	                case 6:
	                	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	                    System.out.print("| Entrer l'id de l'étudiant à ajouter à cette classe : ");
	                    int id_etudiant_classe_add = sc.nextInt();
	                	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	                    sc.nextLine();  
	                    stmt.executeUpdate("INSERT INTO class_student (class_id, student_id) VALUES (" + id_classe + ", " + id_etudiant_classe_add + ")");
	                    // 
	                    break;
	                case 7:
	                	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	                    System.out.print("| Entrer l'id de l'étudiant à supprimer de cette classe : ");
	                    int id_student_classe_supp = sc.nextInt();
	                	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	                    sc.nextLine();  
	                    stmt.executeUpdate("DELETE FROM class_student WHERE student_id='" + id_student_classe_supp+"'");
	                    break;
	                default:
	                	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	    		    	System.out.println("|!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!|");
	    				System.out.println("|                                          Champ Invalide    -_-                                                 |");
	    		    	System.out.println("|!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!|");
	    				System.out.println("+----------------------------------------------------------------------------------------------------------------+\n");
	    				modifierClasse();
	    				break;
	            }
            	System.out.println("\n+----------------------------------------------------------------------------------------------------------------+");
	            System.out.println("| / => Classe modifiée avec succès.");
            	System.out.println("+----------------------------------------------------------------------------------------------------------------+");

	        } catch (SQLException e) {
            	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
		    	System.out.println("|!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!|");
				System.out.println("|                                             Champ Invalide    -_-                                              |");
		    	System.out.println("|!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!|");
				System.out.println("+----------------------------------------------------------------------------------------------------------------+\n");
				modifierClasse();
	        }
        	System.out.println("\n+----------------------------------------------------------------------------------------------------------------+");
	        System.out.print("| Voulez-vous modifier une autre classe? ('o' pour oui, 'n'importe quel caractère' pour non) : ");
	        reponse = sc.next().charAt(0);
        	System.out.println("+----------------------------------------------------------------------------------------------------------------+");

	        sc.nextLine();
	    }
	}
	
	// supprimer section 
	
	
	public void supprimerStudent() {
    	System.out.println("||||||||||||||||||||||||||||||||||||||||| Supprimer Etudiant |||||||||||||||||||||||||||||||||||||||||||||||||||||");
    	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	    System.out.print("| Choisir l'id d'étudiant : ");
	    int student_id = sc.nextInt();
    	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	    sc.nextLine();

	    try {
			if(checkIfStudentExist(student_id)==true){
				stmt.executeUpdate("DELETE FROM class_student WHERE student_id='" + student_id+"'");
				stmt.executeUpdate("DELETE FROM student_module WHERE student_id='" + student_id+"'");
				stmt.executeUpdate("DELETE FROM student WHERE id='" + student_id+"'");
				System.out.println("+----------------------------------------------------------------------------------------------------------------+");
				System.out.println("| - => Étudiant supprimé avec succès.");
				System.out.println("+----------------------------------------------------------------------------------------------------------------+");

			} else {
				System.out.println("+----------------------------------------------------------------------------------------------------------------+");
				System.out.println("| !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! STUDENT DEJA SUPPRIME !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! |");
				System.out.println("+----------------------------------------------------------------------------------------------------------------+");
			}
	         } catch (SQLException e) {
	    	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	    	System.out.println("|!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!|");
			System.out.println("|                                       ID INVALIDE       -_-                                                    |");
	    	System.out.println("|!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!|");
			System.out.println("+----------------------------------------------------------------------------------------------------------------+\n");
			supprimerStudent();
	    }
	}
	public void supprimerModule() {
    	System.out.println("||||||||||||||||||||||||||||||||||||||||||||||| Supprimer Etudiant |||||||||||||||||||||||||||||||||||||||||||||||");
    	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	    System.out.print("| Choisir l'id de module :  ");
	    int module_id = sc.nextInt();
    	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	    sc.nextLine();
	    try {
	    	if(checkIfModuleExists(module_id)==true){
				stmt.executeUpdate("DELETE FROM classe_module WHERE module_id='" + module_id + "'");

				stmt.executeUpdate("DELETE FROM module WHERE ID='" + module_id + "'");
				System.out.println("+----------------------------------------------------------------------------------------------------------------+");
				System.out.println("| - => Module supprimé avec succès.");
				System.out.println("+----------------------------------------------------------------------------------------------------------------+");

			}else{
				System.out.println("+----------------------------------------------------------------------------------------------------------------+");
				System.out.println("| !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! MODULE DEJA SUPPRIME !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! |");
				System.out.println("+----------------------------------------------------------------------------------------------------------------+");
			}
	      } catch (SQLException e) {
	    	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	    	System.out.println("|!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!|");
			System.out.println("|                                         ID INVALIDE       -_-                                                  |");
	    	System.out.println("|!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!|");
			System.out.println("+----------------------------------------------------------------------------------------------------------------+\n");
			supprimerModule();
	    }
	}
	
	public void supprimerClasse() {
    	System.out.println("|||||||||||||||||||||||||||||||||||||||||||||| Supprimer Classe ||||||||||||||||||||||||||||||||||||||||||||||||||");
    	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	    System.out.print("| Choisir l'id de la Classe : ");
	    int classe_id = sc.nextInt();
    	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	    sc.nextLine();

	    try {
			if(checkIfClasseExist(classe_id)==true){
				stmt.executeUpdate("DELETE FROM class_student WHERE class_id='" + classe_id+"'");

				stmt.executeUpdate("DELETE FROM classe_module WHERE classe_id='" + classe_id+"'");

				stmt.executeUpdate("DELETE FROM classe WHERE ID='" + classe_id+"'");
				System.out.println("+----------------------------------------------------------------------------------------------------------------+");
				System.out.println("| - => Classe supprimée avec succès.");
				System.out.println("+----------------------------------------------------------------------------------------------------------------+");
			}else{
				System.out.println("+----------------------------------------------------------------------------------------------------------------+");
				System.out.println("| !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! CLASSE DEJA SUPPRIME !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! |");
				System.out.println("+----------------------------------------------------------------------------------------------------------------+");
			}
	    } catch (SQLException e) {
	    	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	    	System.out.println("|!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!|");
			System.out.println("|                                          ID INVALIDE       -_-                                                 |");
	    	System.out.println("|!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!|");
			System.out.println("+----------------------------------------------------------------------------------------------------------------+\n");
			supprimerClasse();
	    }
	}
// affichage section
	 public void afficherModules() {
	        try {
	        	System.out.println("\n+----------------------------------------------------------------------------------------------------------------+");
	            System.out.println("| Liste des Modules ");
		    	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	            ResultSet resultSet = stmt.executeQuery("SELECT * FROM module");
		    	 while (resultSet.next()) {
	                System.out.println("| ID : " + resultSet.getInt("id") +
	                        " | Nom : " + resultSet.getString("name"));
	            }
	        } catch (SQLException e) {
	        	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
		    	System.out.println("|!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!|");
				System.out.println("|                                          MODULE VIDE       -_-                                                 |");
		    	System.out.println("|!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!|");
				System.out.println("+----------------------------------------------------------------------------------------------------------------+\n");
	        }
	    }

	    public void afficherProfesseurs() {
	        try {
	        	System.out.println("\n+----------------------------------------------------------------------------------------------------------------+");
	            System.out.println("| Liste des Professeurs ");
		    	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	            ResultSet resultSet = stmt.executeQuery("SELECT * FROM prof");
		    	 while (resultSet.next()) {
	                System.out.println("| ID : " + resultSet.getInt("id") +
	                        " | Nom : " + resultSet.getString("name") +
	                        " | Prénom : " + resultSet.getString("last_name"));
	            }
	        } catch (SQLException e) {
	        	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
		    	System.out.println("|!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!|");
				System.out.println("|                                         PROF  VIDE      -_-                                                    |");
		    	System.out.println("|!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!|");
				System.out.println("+----------------------------------------------------------------------------------------------------------------+\n");
	      	        }
	    }

	    public void afficherEtudiants() {
	        try {
	        	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
	        	System.out.println("| Liste des Étudiants ");
	        	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
     
	            ResultSet resultSet = stmt.executeQuery("SELECT * FROM student");
		           while (resultSet.next()) {
	                System.out.println("| ID : " + resultSet.getInt("id") +
	                        " | Nom : " + resultSet.getString("name") +
	                        " | Prénom : " + resultSet.getString("last_name"));
	            }
	        } catch (SQLException e) {
	        	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
		    	System.out.println("|!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!|");
				System.out.println("|                                          ETUDIANT  VIDE      -_-                                               |");
		    	System.out.println("|!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!|");
				System.out.println("+----------------------------------------------------------------------------------------------------------------+\n");
				}
	    }

	    public void afficherClasses() {
	        try {
	        	 ResultSet resultSet = stmt.executeQuery("SELECT * FROM classe");
	        	  while (resultSet.next()) {
	                System.out.println("| ID : " + resultSet.getInt("id") +
	                        " | Nom : " + resultSet.getString("name") +
	                        " | Filière : " + resultSet.getString("filliere") +
	                        " | Grade : " + resultSet.getString("grade"));
	            }
	        } catch (SQLException e) {
	        	System.out.println("+----------------------------------------------------------------------------------------------------------------+");
		    	System.out.println("|!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!|");
				System.out.println("|                                        CLASSES   VIDE      -_-                                                 |");
		    	System.out.println("|!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!|");
				System.out.println("+----------------------------------------------------------------------------------------------------------------+\n");
					        }
	    }


	private boolean checkIfModuleExists(int moduleId) throws SQLException {
		String query = "SELECT COUNT(*) AS count FROM module WHERE id = " + moduleId;
		try (ResultSet resultSet = stmt.executeQuery(query)) {
			if (resultSet.next()) {
				int count = resultSet.getInt("count");
				return count > 0;
			}
		}
		return false;
	}

	private boolean checkIfProfessorExists(int professorId) throws SQLException {
		String query = "SELECT COUNT(*) AS count FROM prof WHERE id = " + professorId;
		try (ResultSet resultSet = stmt.executeQuery(query)) {
			if (resultSet.next()) {
				int count = resultSet.getInt("count");
				return count > 0;
			}
		}
		return false;
	}
	
	private boolean checkIfClasseExist(int classeId) throws SQLException {
		String query = "SELECT COUNT(*) AS count FROM classe WHERE id = " + classeId;
		try (ResultSet resultSet = stmt.executeQuery(query)) {
			if (resultSet.next()) {
				int count = resultSet.getInt("count");
				return count > 0;
			}
		}
		return false;
	}
	
	private boolean checkIfStudentExist(int studentId) throws SQLException {
		String query = "SELECT COUNT(*) AS count FROM student WHERE id = " + studentId;
		try (ResultSet resultSet = stmt.executeQuery(query)) {
			if (resultSet.next()) {
				int count = resultSet.getInt("count");
				return count > 0;
			}
		}
		return false;
	}
	
}