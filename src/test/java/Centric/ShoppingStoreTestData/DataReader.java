package Centric.ShoppingStoreTestData;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	
	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
		
		//read Json to String
		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+
				"//src//test//java//Centric//ShoppingStoreTestData//purchaseOrder.json"), StandardCharsets.UTF_8);
		
		// String to HashMap using Jackson Databind
		
		ObjectMapper mapper = new ObjectMapper();
		// stores 2 hash maps = {{map, map}}
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
			});
		
		return data;		
		} 
}
