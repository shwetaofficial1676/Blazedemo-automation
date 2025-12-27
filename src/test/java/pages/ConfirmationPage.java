package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class ConfirmationPage {
	WebDriver driver;
	public ConfirmationPage(WebDriver driver) {

		this.driver = driver;
	}
	
	public void confirmation() {
		String expectedHeaderText = "Thank you for your purchase today!";
		WebElement headerElement = driver.findElement(By.tagName("h1")); // Example locator
		String actualHeaderText = headerElement.getText();
		Assert.assertEquals(actualHeaderText, expectedHeaderText, "Something went wrong!");
	}
}
