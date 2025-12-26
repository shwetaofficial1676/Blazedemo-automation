package pages;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import tests.BaseTest;

public class ReservePage extends BaseTest{
	WebDriver driver;
	public ReservePage(WebDriver driver) {

		this.driver = driver;
	}
	public void chooseThisFlight() {
		Random random = new Random();
        int min = 1;
        int max = 5;
        // Formula: random.nextInt((max - min) + 1) + min
        int randomNumber = random.nextInt((max - min) + 1) + min;
		driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr["+randomNumber+"]/td[1]/input")).click();
		sleep();
	}
	
}
