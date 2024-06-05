package business;

public class Statistics {
    private int numberOfStudents;
    private int numberOfProfessors;
    private int numberOfClasses;
    private int numberOfModules;
    private int maleStudents;
    private int femaleStudents;
    private int maleProfessors;
    private int femaleProfessors;
    private int nombreNotesEntrees = 0;
    private int nombreNotesNonEntrees = 0;

    // Constructeur, getters et setters

    public Statistics(int numberOfStudents, int numberOfProfessors, int numberOfClasses, int numberOfModules, int maleStudents, int femaleStudents, int maleProfessors, int femaleProfessors) {
        this.numberOfStudents = numberOfStudents;
        this.numberOfProfessors = numberOfProfessors;
        this.numberOfClasses = numberOfClasses;
        this.numberOfModules = numberOfModules;
        this.maleStudents = maleStudents;
        this.femaleStudents = femaleStudents;
        this.maleProfessors = maleProfessors;
        this.femaleProfessors = femaleProfessors;
    }

	public int getNumberOfStudents() {
		return numberOfStudents;
	}

	public void setNumberOfStudents(int numberOfStudents) {
		this.numberOfStudents = numberOfStudents;
	}

	public int getNumberOfProfessors() {
		return numberOfProfessors;
	}

	public void setNumberOfProfessors(int numberOfProfessors) {
		this.numberOfProfessors = numberOfProfessors;
	}

	public int getNumberOfClasses() {
		return numberOfClasses;
	}

	public void setNumberOfClasses(int numberOfClasses) {
		this.numberOfClasses = numberOfClasses;
	}

	public void setNumberOfModules(int numberOfModules) {
		this.numberOfModules = numberOfModules;
	}

	public int getMaleStudents() {
		return maleStudents;
	}

	public void setMaleStudents(int maleStudents) {
		this.maleStudents = maleStudents;
	}

	public int getFemaleStudents() {
		return femaleStudents;
	}

	public void setFemaleStudents(int femaleStudents) {
		this.femaleStudents = femaleStudents;
	}

	public int getMaleProfessors() {
		return maleProfessors;
	}

	public int getNombreNotesEntrees() {
		return nombreNotesEntrees;
	}

	public void setNombreNotesEntrees(int nombreNotesEntrees) {
		this.nombreNotesEntrees = nombreNotesEntrees;
	}

	public int getNombreNotesNonEntrees() {
		return nombreNotesNonEntrees;
	}

	public void setNombreNotesNonEntrees(int nombreNotesNonEntrees) {
		this.nombreNotesNonEntrees = nombreNotesNonEntrees;
	}

	public void setMaleProfessors(int maleProfessors) {
		this.maleProfessors = maleProfessors;
	}

	public int getFemaleProfessors() {
		return femaleProfessors;
	}

	public void setFemaleProfessors(int femaleProfessors) {
		this.femaleProfessors = femaleProfessors;
	}
	
	public double getEnteredGradesPercentage() {
        int totalStudents = maleStudents + femaleStudents;
        int totalProfessors = maleProfessors + femaleProfessors;
        if (totalStudents == 0 || totalProfessors == 0 || numberOfClasses == 0) {
            return 0.0;
        }
        return ((double) (nombreNotesEntrees) / (nombreNotesEntrees + nombreNotesNonEntrees)) * 100.0;
    }

    // Méthode pour récupérer le nombre de modules
    public int getNumberOfModules() {
        return numberOfModules;
    }
    // Getters et setters pour les nouveaux champs...
}