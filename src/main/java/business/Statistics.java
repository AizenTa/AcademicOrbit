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

	public int getNumberOfModules() {
		return numberOfModules;
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

	public void setMaleProfessors(int maleProfessors) {
		this.maleProfessors = maleProfessors;
	}

	public int getFemaleProfessors() {
		return femaleProfessors;
	}

	public void setFemaleProfessors(int femaleProfessors) {
		this.femaleProfessors = femaleProfessors;
	}

    // Getters et setters pour les nouveaux champs...
}