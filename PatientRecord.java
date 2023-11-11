package Example1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PatientRecord {
	private File allergies;
	private File familyRecord;
	private File currentMedication;
	private String fileName;
	
	// Method only relevant for testing
	public PatientRecord(String fileName) {
		this.fileName = fileName;
	}
	
	public File getAllergyFile() {
		return allergies;
	}
	
	public File getFamilyRecord() {
		return familyRecord;
	}
	
	public File getCurrentMedicationFile() {
		return currentMedication;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	/**
	 * 
	 * @param fileName Name of the Family History Record
	 * @return List of "red flags" found in the record.
	 * Red flags are certain medical conditions, medication intakes, causes of death, etc.
	 * that may impact the wellness of a patient at some point in their life.
	 * @throws FileNotFoundException
	 */
	public ArrayList<String> getRedFlags(String fileName) throws FileNotFoundException {
		File inFile = new File(fileName);
		Scanner scnr = new Scanner(inFile);
		ArrayList<String> redFlags = new ArrayList<>();
		
		while(scnr.hasNext()) {
			String line = scnr.nextLine();
			String[] tokens = line.split("\\s+");
			
			for(int i = 0;i<tokens.length;i++) {
				if(tokens[i].contains("(R)")) {
					redFlags.add(tokens[i]);
				}
			}
		}
		scnr.close();
		
		return redFlags;
	}

}
