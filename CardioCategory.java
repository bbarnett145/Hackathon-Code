package Example1;

import java.util.Arrays;
import java.util.List;

public class CardioCategory extends SymptomCategory {
	
	@Override
	String getName() {
		return "Cardio";
	}
	
	@Override
	String getDescription() {
		return "Cardio related symptoms.";
	}
	
	@Override
	List<String> getSymptoms() {
		return Arrays.asList("Chest Tightness",
				"Feeling of pressure or heaviness in the chest", 
				"Shortness of Breath", 
				"Throbbing pain", 
				"Hearing loss");
		
	}
	
}
