package company.cawstudios.assignment;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadDataFromJSONFile {
	public  JSONArray  readData(String fileLocation) throws Exception, ParseException {
		// creating parser object
		JSONParser parser = new JSONParser();
		
		//creating reader object to read test data from the give location "/cawstudios/src/test/resources/testdata.json"
		FileReader reader = new FileReader(fileLocation);
		
		//parsing the file using parser object
		Object obj = parser.parse(reader);
		
		//casting object into JSONArray
		JSONArray jarr = (JSONArray) obj;

		return jarr;	

	}
}
