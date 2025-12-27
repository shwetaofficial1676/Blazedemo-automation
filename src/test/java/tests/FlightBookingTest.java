package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.ConfirmationPage;
import pages.HomePage;
import pages.PurchasePage;
import pages.ReservePage;
import utils.ExcelUtils;

public class FlightBookingTest extends BaseTest {
	@Test(groups = { "smoke" })
	public void TC01_verifyHomePage() {

		HomePage homePage = new HomePage(driver);

		// open the homepage url
		homePage.navigateToHomePage();

		// Verify homepage loads correctly
		String actUrl = homePage.getCurrentHomePageUrl();
		Assert.assertEquals(actUrl, "https://blazedemo.com/");

		String actTitle = driver.getTitle();
		Assert.assertEquals(actTitle, "BlazeDemo");

		// Verify dropdowns visibility
		boolean flag = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			WebElement firstDropdown = driver.findElement(By.xpath("//select[@name=\"fromPort\"]"));
			wait.until(ExpectedConditions.visibilityOf(firstDropdown));
			sleep();
			flag = true;
			System.out.println("Dropdown visible...");

		} catch (Exception e) {

			flag = false;

			System.out.println("Expection is : " + e.getLocalizedMessage());

		}

		Assert.assertEquals(flag, true);

	}

	@Test(groups = { "functional" })
	public void TC02_searchFlightwithValidCity() {
		HomePage homePage = new HomePage(driver);

		// Select cities, click “Find Flights”.
		homePage.findFlights();
		String expectedHeaderText = "Flights from Paris to New York:";
		WebElement headerElement = driver.findElement(By.tagName("h3")); // Example locator
		String actualHeaderText = headerElement.getText();

		Assert.assertEquals(actualHeaderText, expectedHeaderText, "Page header text mismatch!");
	}

	@Test(groups = { "functional" })
	public void TC_03completeFlightBooking() {
		HomePage homePage = new HomePage(driver);

		// Select cities, click “Find Flights”.
		homePage.findFlights();
		// choose this flight button click
		ReservePage rp = new ReservePage(driver);
		rp.chooseThisFlight();
		// purchase flight
		PurchasePage pp = new PurchasePage(driver);
		pp.passengerInfo("Shweta", "Girinagar", "Bengaluru", "Karnataka", "560099", "998877776653", "Shweta");
		ConfirmationPage cp = new ConfirmationPage(driver);
		cp.confirmation();
	}

	@DataProvider(name = "PassengerData")
	public Object[][] getData() throws Exception {
		return ExcelUtils.getExcelData("src/test/resources/TestData.xlsx", "PassengerData");
	}

	@Test(dataProvider = "PassengerData")
	public void TC_04MultipleBookingsWithDataSets(String inputName, String address, String city, String state,
			String zipCode, String creditCardNumber, String nameOnCard) {
		HomePage homePage = new HomePage(driver);

		// Select cities, click “Find Flights”.
		homePage.findFlights();
		// choose this flight button click
		ReservePage rp = new ReservePage(driver);
		rp.chooseThisFlight();
		// purchase flight
		PurchasePage pp = new PurchasePage(driver);
		pp.passengerInfo(inputName, address, city, state, zipCode, creditCardNumber, nameOnCard);
		ConfirmationPage cp = new ConfirmationPage(driver);
		cp.confirmation();
	}

	@Test(groups = { "negative" })
	public void TC_05BlankCreditCard() {
		try {
			HomePage homePage = new HomePage(driver);

			// Select cities, click “Find Flights”.
			homePage.findFlights();
			// choose this flight button click
			ReservePage rp = new ReservePage(driver);
			rp.chooseThisFlight();
			// purchase flight
			PurchasePage pp = new PurchasePage(driver);
			pp.passengerInfo("Shweta", "Girinagar", "Bengaluru", "Karnataka", "560099", "", "Shweta");
			String expectedHeaderText = "Thank you for your purchase today!";
			WebElement headerElement = driver.findElement(By.tagName("h1")); // Example locator
			String actualHeaderText = headerElement.getText();
			Assert.assertNotEquals(actualHeaderText, expectedHeaderText, "Credit Card Number Is Blank!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test(groups = { "negative" })
	public void TC_06InvalidCreditCardChars() {
		try {
			HomePage homePage = new HomePage(driver);

			// Select cities, click “Find Flights”.
			homePage.findFlights();
			// choose this flight button click
			ReservePage rp = new ReservePage(driver);
			rp.chooseThisFlight();
			// purchase flight
			PurchasePage pp = new PurchasePage(driver);
			pp.passengerInfo("Shweta", "Girinagar", "Bengaluru", "Karnataka", "560099", "AAWW3455ZX99", "Shweta");
			String expectedHeaderText = "Thank you for your purchase today!";
			WebElement headerElement = driver.findElement(By.tagName("h1")); // Example locator
			String actualHeaderText = headerElement.getText();
			Assert.assertNotEquals(actualHeaderText, expectedHeaderText, "Invalid Credit Card Characters!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test(groups = { "negative" })
	public void TC_07SameDepartureNDestination() {
		try {
			HomePage homePage = new HomePage(driver);
			homePage.navigateToHomePage();
			WebElement firstDropdown = driver.findElement(By.xpath("//select[@name=\"fromPort\"]"));
			sleep();
			Select firstDd = new Select(firstDropdown);
			firstDd.selectByVisibleText("Paris");
			sleep();
			WebElement secondDropdown = driver.findElement(By.xpath("//select[@name=\"toPort\"]"));
			sleep();
			Select secondDd = new Select(secondDropdown);
			secondDd.selectByVisibleText("Paris");
			sleep();
			driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();
			sleep();

		} catch (Exception e) {
			System.out.println("Same Departure and Destination not allowed " + e.getMessage());
		}

	}
}
