package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import tests.BaseTest;

public class HomePage extends BaseTest{
	WebDriver driver;

	public HomePage(WebDriver driver) {

		this.driver = driver;
	}

	public void navigateToHomePage() {
		driver.get("https://blazedemo.com/");
		
	}

	public String getCurrentHomePageUrl() {
		return driver.getCurrentUrl();
	}
	
	public void findFlights() {
		this.navigateToHomePage();	
		WebElement firstDropdown = driver.findElement(By.xpath("//select[@name=\"fromPort\"]"));
		sleep();
		Select firstDd = new Select(firstDropdown);
		firstDd.selectByVisibleText("Paris");
		sleep();
		WebElement secondDropdown = driver.findElement(By.xpath("//select[@name=\"toPort\"]"));
		sleep();
		Select secondDd = new Select(secondDropdown);
		secondDd.selectByVisibleText("New York");
		sleep();
		driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();
		sleep();
	}

}
