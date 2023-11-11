package Example1;

import java.util.Arrays;
import java.util.List;

public class RespiratoryCategory extends SymptomCategory {
	
	@Override
	String getName() {
		return "Respiratory";
	}
	
	@Override
	String getDescription() {
		return "Respiratory related symptoms.";
	}
	
	@Override
	List<String> getSymptoms() {
		return Arrays.asList("Shortness of Breath", 
				"Pressure on left or right side of chest", 
				"Persistent Cough", 
				"Wheezing",
				"Fatigue");
		
	}
	
}
