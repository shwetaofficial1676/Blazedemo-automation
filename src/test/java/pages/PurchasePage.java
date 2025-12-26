package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import tests.BaseTest;

public class PurchasePage extends BaseTest {
	WebDriver driver;

	public PurchasePage(WebDriver driver) {

		this.driver = driver;
	}

	public void passengerInfo(String inputName,String address,String city,String state,String zipCode,String creditCardNumber,String nameOnCard) {
		driver.findElement(By.id("inputName")).sendKeys(inputName);
		driver.findElement(By.id("address")).sendKeys(address);
		driver.findElement(By.id("city")).sendKeys(city);
		driver.findElement(By.id("state")).sendKeys(state);
		driver.findElement(By.id("zipCode")).sendKeys(zipCode);
		driver.findElement(By.id("creditCardNumber")).sendKeys(creditCardNumber);
		driver.findElement(By.id("nameOnCard")).sendKeys(nameOnCard);
//	driver.findElement(By.id("rememberMe")).click();
		sleep();
		driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();
		sleep();
	}

}
