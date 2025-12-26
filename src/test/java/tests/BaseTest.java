package tests;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
	public static WebDriver driver;

	@BeforeSuite(alwaysRun = true)
	public void initDriver() {

		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	public static void sleep() {

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void takeScreenShots() {
		try {
			sleep();
			// TakesScreenShot is an interface from selenium webdriver which help to take
			// SS.
			TakesScreenshot ts = (TakesScreenshot) driver;
			// ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			File sourceFile = ts.getScreenshotAs(OutputType.FILE);
			FileHandler.copy(sourceFile, new File("C://FolderName/Shweta.jpeg"));
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

	@AfterSuite(alwaysRun = true)
	public static void quitDriver() {
		driver.quit();
	}
}
