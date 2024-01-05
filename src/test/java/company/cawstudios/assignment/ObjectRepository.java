package company.cawstudios.assignment;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ObjectRepository {
	/* in this class storing all the locators of webelements as part of pom framework and also creating resuable methods to webelements*/
	WebDriver driver;
	String fileLocation = ".\\src\\test\\resources\\testdata\\testdata.json";
	String url = "https://testpages.herokuapp.com/styled/tag/dynamic-table.html";
	@FindBy(xpath = "//summary[text()='Table Data']") WebElement tableDataBtn;
	@FindBy(id = "jsondata") WebElement textArea;
	@FindBy(id = "refreshtable") WebElement refreshButton;
	@FindBy(xpath =  "//table[@id='dynamictable']/tr") WebElement totalRows;
	By locator = By.xpath("//table[@id='dynamictable']//tr");
	@FindBy(xpath = "//table[@id='dynamictable']/tr/td") WebElement totalCols;
	By collLocator=By.xpath("//table[@id='dynamictable']//th");
	
	
	ObjectRepository(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	public WebElement dynamicXpath(WebDriver driver,int i,int j) {
		return driver.findElement(By.xpath("//table[@id='dynamictable']//tr["+i+"]/td["+j+"]"));
		 
	}
	
	public void clickOnTableDataButton() {
		tableDataBtn.click();
	}
	public void clearTextArea() {
		textArea.clear();
	}
	public void postTestData(String json) {
		textArea.sendKeys(json);
	}
	public void clickRefreshButton() {
		refreshButton.click();
	}
	public int numLocators(WebDriver driver,By ele) {
		int rownum=driver.findElements(ele).size();
		return rownum;
		
	}
	public List<WebElement> getTableRows() {
        return driver.findElements(locator);
    }
	
}
