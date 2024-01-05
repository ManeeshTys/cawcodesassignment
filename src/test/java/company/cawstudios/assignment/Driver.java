package company.cawstudios.assignment;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class Driver {
	WebDriver driver;
	@BeforeTest
	
	//creating driver setup to launch browser
	public void setup() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(2));
	}
	
	//closing browser once test is completed
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
