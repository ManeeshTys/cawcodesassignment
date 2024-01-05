package company.cawstudios.assignment;


import java.time.Duration;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;


public class DynamicTableHandling extends Driver {
	ObjectRepository orepo;
	ReadDataFromJSONFile rdf = new ReadDataFromJSONFile();
	WebDriverWait wait;
	
	@Test
	public void dynamicTableHandlingTest() throws Exception {
		orepo = new ObjectRepository(driver);
		JSONArray jarr = rdf.readData(orepo.fileLocation);
		
		//loading url
		driver.get(orepo.url);
		
		//creating webdriverwait object
		wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		wait.until(ExpectedConditions.visibilityOf(orepo.tableDataBtn));
		
		//Click on the Table Data button
		orepo.clickOnTableDataButton();
		
		//clearing the existing text area
		orepo.clearTextArea();
		
		//Insert the  data which we read from json file into input text box
		orepo.postTestData(jarr.toString());
		
		//clicking on the refresh button
		orepo.clickRefreshButton();
		
		//getting total number of rows from the web table
		int rows = orepo. numLocators( driver, orepo.locator);
		
		//getting total number of columns from the web table
		int colums = orepo. numLocators( driver, orepo.collLocator);
		
		//printing the number of rows and cols
		System.out.println("rows in webtable --"+rows+", columns in webtable --"+colums);
		
		//for iterating webtable rows
		for (int i=2;i<=rows;i++) {
			
			//getting name, age, gender from json array
			JSONObject obj = (JSONObject) jarr.get(i-2);
			String nameFromJSONArr = (String) obj.get("name");
			String ageFromJSONArr =  obj.get("age").toString();
			String genderFromJSONArr =  obj.get("gender").toString();
			
			
			//System.out.println("name:"+name+" age:"+age+" gender:"+gender);
			
			//for iterating webtable columns
			for(int j=1;j<=colums;j++) {
				
			//Retrieving cell data based on row number and column number	
			orepo.dynamicXpath(driver,i,j);
			
			//performing assertions 
			if(j==1) {
				String genderfromwebtable = orepo.dynamicXpath(driver,i,j).getText().toString();
				Assert.assertEquals(genderFromJSONArr, genderfromwebtable,"gernder validated");
				System.out.println(genderFromJSONArr+"-"+genderfromwebtable+" -> gender assertion validated successfully....");
			}
			else if (j==2) {
				String nameFromWebTable = orepo.dynamicXpath(driver,i,j).getText().toString();
				Assert.assertEquals(nameFromJSONArr, nameFromWebTable);
				System.out.println(nameFromJSONArr+"-"+nameFromWebTable+" -> Name assertion validated successfully....");

			}
			else if (j==3) {
				String ageFromWebTable = orepo.dynamicXpath(driver,i,j).getText().toString();
				Assert.assertEquals(ageFromJSONArr, ageFromWebTable);
				System.out.println(ageFromJSONArr+"-"+ageFromWebTable+" -> Name assertion validated successfully....");

			}
           //System.out.println(  "   "+orepo.dynamicXpath( driver, i, j).getText());
		} 
        }


}
}
