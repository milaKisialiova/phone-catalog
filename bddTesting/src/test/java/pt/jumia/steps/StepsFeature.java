package pt.jumia.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepsFeature {
	WebDriver driver;

	@Before
	public void openBrowser() {
		System.setProperty("webdriver.gecko.driver", "driver//geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}

	@Given("^Open the Firefox and Web Catalog$")
	public void open_the_Firefox_and_Web_Catalog() throws Throwable {
		driver.get("http://localhost:8080/WebCatalog");
	}

	@When("^Filter by country \"([^\"]*)\"$")
	public void filter_by_country(String country) throws Throwable {
		waitForElement(By.className("filterCountry"));
		driver.findElement(By.className("filterCountry")).sendKeys(country);
	}

	@And("^Filter by state \"([^\"]*)\"$")
	public void filter_by_state(String state) throws Throwable {
		waitForElement(By.className("filterState"));
		driver.findElement(By.className("filterState")).sendKeys(state);
	}

	@Then("^See row with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void see_row_with_(String code, String number, String country, String state) throws Throwable {
		waitForElement(By.xpath("//tr/td[contains(text(), " + number + ")]"));
		driver.findElement(By.xpath("//tr[td/text()=" + number + "]/td[contains(text(), " + code + ")]"));
		driver.findElement(By.xpath("//tr[td/text()=" + number + "]/td[contains(text(), " + country + ")]"));
		driver.findElement(By.xpath("//tr[td/text()=" + number + "]/td[contains(text(), '" + state + "')]"));
	}

	@After()
	public void closeBrowser() {
		freezeStepSeconds(3);
		driver.quit();
		System.out.println("Browser was closed");
	}

	private void freezeStepSeconds(long seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			// do nothing
		}
	}

	private void waitForElement(By locator) {
		WebDriverWait driverWait = new WebDriverWait(driver, 30L);
		driverWait.until(ExpectedConditions.elementToBeClickable(locator));
	}
}
