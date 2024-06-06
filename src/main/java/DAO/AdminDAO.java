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
	    ///// filter mothods

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
	        
	        Statistics stat = new Statistics(numberOfStudents, numberOfProfessors, numberOfClasses, numberOfModules, maleStudents, femaleStudents, maleProfessors, femaleProfessors);
	        return stat;
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
       	 	updateNotes();
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
	 public void ajouterEtudiant(Etudiant etudiant,String[] classIds) throws SQLException {
	        String etudiant_username = etudiant.getUsername();
	        String etudiant_password = etudiant.getPassword();
	        String nom = etudiant.getNom();
	        String prenom = etudiant.getPrenom(); 
	        String address = etudiant.getAddress();
	        String sex = etudiant.getSex();
	        int age = etudiant.getAge();
	        String cne_etudiant = etudiant.getCne_student(); 
	        
	        String hashed_password = hashString(etudiant_password);
	    	stmt.executeUpdate("INSERT INTO student (username,password,name, last_name, address, sex, age, cne_student) VALUES ('" + etudiant_username + "','" + hashed_password + "','" + nom + "','" + prenom + "','" + address + "','" + sex + "'," + age + ",'" + cne_etudiant + "')",Statement.RETURN_GENERATED_KEYS);			
			ResultSet rs = stmt.getGeneratedKeys();
	    	if(rs.next()) {
				int student_id = rs.getInt(1);
				
				for(String classId : classIds) {
					int classid = Integer.parseInt(classId);
					stmt.executeUpdate("INSERT INTO class_student (class_id, student_id) VALUES ('" + classid + "','" + student_id +"')");
					
				}
			}  		  
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
				}
			}
	 }
	 
	 public List<Etudiant> getAllEtudiantInfos() throws SQLException {
			List<Etudiant> etudiants = new ArrayList<>();
			ResultSet rs = stmt.executeQuery("SELECT * FROM student");
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
	 
	 public List<Classe> getAllModuleClasse(int id) throws SQLException {
			List<Classe> classe = new ArrayList<>();
			List<Integer> ids = new ArrayList<>();
			ResultSet rs = stmt.executeQuery("SELECT classe_id FROM classe_module WHERE module_id='"+id+"'");
				while (rs.next()) {
					int classe_id = rs.getInt("classe_id");
					ids.add(classe_id);
				}
			classe=getAllModuleById(ids);
			return classe;
		}
	 
	 public List<Classe> getAllModuleById(List<Integer> ids) throws SQLException {
		 List<Classe> classes = new ArrayList<>();	
		 for(int id : ids) {
				ResultSet rs = stmt.executeQuery("SELECT * FROM classe WHERE ID='"+id+"'");
					while (rs.next()) {
						Classe classe = new Classe(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getString("filliere"),
								rs.getString("grade")
						); 
						classes.add(classe);
					}
			}
			return classes;
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

	}
	
	private ArrayList<Integer> idsStudents() throws SQLException {
	    ResultSet resultat_student = stmt.executeQuery("SELECT * FROM student");
		ArrayList<Integer> ids = new ArrayList<>();    
	    while(resultat_student.next()) {    
		     ids.add(resultat_student.getInt("id"));
	    }
    return ids;
	}
	
	
	private void updateNotes() throws SQLException {
	    ArrayList<Integer> ids = idsStudents();
        	for(int id : ids) {
                float note_finale = calculerNoteFinale(id);
                stmt.executeUpdate("UPDATE student SET note_finale='" + note_finale + "' WHERE ID= " + id);
        	}
	}
	
	private float calculerNoteFinale(int id1) throws SQLException {
		float note_finale=0;
		ResultSet resultSet = stmt.executeQuery("SELECT * FROM student_module WHERE student_id='" + id1 + "'");
	    ArrayList<Float> notes = new ArrayList<>();

	    while (resultSet.next()) {
	        float moduleNote = resultSet.getFloat("note_module");
	        notes.add(moduleNote);
	    }

	    int nbr_modules = notes.size();
	    float sum_notes = 0;

	    for (int i = 0; i < notes.size(); i++) {
	        sum_notes = sum_notes + notes.get(i);
	    }

	    if (nbr_modules > 0) {
	        note_finale = sum_notes / nbr_modules;
	    }
		return note_finale;
		
	}

	
}