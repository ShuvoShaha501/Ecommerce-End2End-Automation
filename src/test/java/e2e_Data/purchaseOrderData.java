package e2e_Data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class purchaseOrderData {
	


	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
		
	
	String jsonToString=FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\e2e_Data\\dataReader.json"),StandardCharsets.UTF_16);
	
	ObjectMapper maper=new ObjectMapper();
	
		List<HashMap<String, String>> data=maper.readValue(jsonToString,new TypeReference<List<HashMap<String, String>>>(){});
		return data;
	}

}
