package Example1;

import java.util.Arrays;
import java.util.List;

public class HeadCategory extends SymptomCategory {
	
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
		return Arrays.asList("Throbbing pain all around head", 
				"Feeling of tight band around head", 
				"Pain in back part of head or neck",
				"Sharp pain behind either or both eyes");	
	}
}
