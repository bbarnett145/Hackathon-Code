package Example1;

/**
 * (Currently filler) Patient class for constructing the patient who will be using the app.
 */
public class Patient {
	private String name;
	private int DOB;
	private PatientRecord PR;
	
	public Patient(String name, int DOB, PatientRecord PR) {
		this.name = name;
		this.DOB = DOB;
		this.PR = PR;
	}
}
