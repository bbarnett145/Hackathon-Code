package Example1;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public abstract class SymptomCategory {
	abstract String getName();
	abstract String getDescription();
	abstract List<String> getSymptoms();
	private String cat;
	private String severity;
	
	
	// Getters and Setters
	public String getCat() {
		return cat;
	}
	
	public String getSeverity() {
		return severity;
	}
	
	public void setCat(String s) {
		this.cat = s;
	}
	public void setSeverity(String s) {
		this.severity = s;
	}
	
	
	/**
	 * Prompts user to choose a category of potential symptoms
	 * @return Embedded methods that take user through questionnaire
	 */
	public static String promptSymptomCategory() {
		
		// Open scanner for user input
		Scanner scnr = new Scanner(System.in);
		System.out.println("[Select a symptom category. Enter the corresponding number.]");
		
		// Display list of possible symptoms.
		List<String> categories = Arrays.asList("1. Chest Pain", 
				"2. Shortness of Breath", 
				"3. Headache");
		System.out.println(categories);
		
		// User chooses from the list
		String answer = scnr.nextLine();
		
		// Pass a category to the next function depending on what the patient chose.
		SymptomCategory sc = null;
		switch(answer) {
		case "1":
			sc = new CardioCategory();
			sc.setCat("Cardio");
			return promptSymptoms(sc);
		
		case "2":
			sc = new RespiratoryCategory();
			sc.setCat("Respiratory");
			return promptSymptoms(sc);
		}
		return "invalid category";
		
		
	}
	
	/**
	 * 
	 * @param sc Category of potential symptoms
	 * @return Embedded answer evaluation method
	 */
	public static String promptSymptoms(SymptomCategory sc) {
		List<String> symptoms = Arrays.asList("");
		SymptomCategory scCopy = sc;
		
		// Returns list of category-relevant symptoms for user to choose from
		symptoms = sc.getSymptoms();
		
		// Open scanner for user input
		Scanner scnr = new Scanner(System.in);
		System.out.println("[Enter symptoms for selected category.]");
		
		
		// Create a list of answers for the instance of questionnaire
		List<String> answers = new ArrayList<>();
		
		// Loop through all relevant symptoms in the category, prompting the user to say whether or not they are experiencing them.
		for(int i=0; i< symptoms.size(); i++) {
			String symptom = symptoms.get(i);
			System.out.println("Enter 'y' if you have expereinced this symptom, or 'n' if not.");
			System.out.println(i+1 + ". " + symptom);
			String answer = scnr.nextLine();
			
			// If patient is experiencing the symptom, add it to an answers list.
			if(answer.equalsIgnoreCase("y")) {
				answers.add(symptom);
			}
		}
		scnr.close();
		return evaluate(answers, scCopy);
	}
	
	/**
	 * 
	 * @param answers List of user inputted symptoms
	 * @param sc Category of potential symptoms
	 * @return Final answer to the questionnaire, aka what steps this patient should take to receive the most efficient care.
	 */
	public static String evaluate(List<String> answers, SymptomCategory sc) {
		
		// Create instance of the Patient Record
		PatientRecord bobPR = new PatientRecord("src/Example1/familyhistory.txt");
		
		// Create instance of the Patient
		Patient quizTaker = new Patient("Bob", 26, bobPR);
		
		// Obtain the red flags from the Patient Record
		ArrayList<String> redFlags = new ArrayList<>();
		try {
			redFlags = bobPR.getRedFlags(bobPR.getFileName());
		} catch (FileNotFoundException e) {
			System.out.println("Invalid file name");
		}
		
		String cat = sc.getName();
		String severityRet = null;
		
		
		// Determine severity of Patient's situation based off of their records
		// In this case, only FamilyHistory is considered.
		if(answers.size() > 0) {
			// Looks for red flags
			switch(cat) {
			case "Cardio":
				for(String s : redFlags) {
					if(s.contains("Cardiac_Arrest")) {
						severityRet = "highest";
						break;
					}
					else if(s.contains("Cardiomyopathy")) {
						sc.setSeverity("moderate");
					}
					else if(s.contains("High_Cholesterol")) {
						sc.setSeverity("low");
					}
				}
				break;
			
			case "Respiratory":
				for(String s : redFlags) {
					if(s.contains("Cystic_Fibrosis")) {
						sc.setSeverity("highest");
					}
					else if(s.contains("Asthma")) {
						sc.setSeverity("lowest");
					}
				}
				break;
			}
		}
		else {
			return "A doctor's visit is not necessary.";
		}
		
		// Returns final answer based off of the severity of patient's current state
		switch(severityRet) {
		case "highest":
			return "Seek Emergency Help, or Call 911.";
			
		case "moderate":
			return "Seek medical attention such as Urgent Care.";
			
		case "low":
			return "Set up an appointment with your Physician, or video call one of our nurses.";
		
		case "lowest":
			return "See the Care Plan below for your next steps.";
			// Tamaflu Example
		}
		
		return "";
			
		
	}
	
}
