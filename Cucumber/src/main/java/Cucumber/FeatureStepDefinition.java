package Cucumber;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import ObjectReprository.LoginObject;
import ObjectReprository.PageObjects;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class FeatureStepDefinition {

	static WebDriver driver;
	int productcount = 0;

	// @Given("^Navigate to Home Page$")
	public void navigateToHomePage() throws Throwable {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Automation\\Cucumber\\Cucumber\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://10.232.237.143:443/TestMeApp/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	// @When("^user enters username and password$")
	public void userEntersUsernameAndPassword() throws Throwable {

		PageObjects po = PageFactory.initElements(driver, PageObjects.class);
		LoginObject lo = PageFactory.initElements(driver, LoginObject.class);
		po.signin.click();
		lo.username.sendKeys("lalitha");
		lo.password.sendKeys("password123");
		lo.login.click();
	}

	//@Then("^user logged in successfully$")
	public void userLoggedInSuccessfully() throws Throwable {
		// Assert.assertEquals(driver.getTitle(), "Admin Home");
		driver.quit();

	}

	//@Given("^Larry has registered in to TestMeApp$")
	public void larryHasRegisteredInToTestMeApp() throws Throwable {

	}

	//@When("^Larry searches below products in the search box:$")
	public void larrySearchesBelowProductsInTheSearchBox(DataTable productsdata) throws Throwable {
		List<String> productsName = productsdata.asList(String.class);
		navigateToHomePage();
		userEntersUsernameAndPassword();

		for (String product : productsName) {
			driver.findElement(By.name("products")).sendKeys(product);

			driver.findElement(By.xpath("//input[@value='FIND DETAILS']")).click();

			boolean found = isElementPresent("//a[text()[contains(.,'Add to cart')]]");
			if (found) {
				productcount++;
			}

			driver.findElement(By.xpath("//a[text()[contains(.,'Home')]]")).click();

		}

	}

	//@Then("^product should be added in the cart if available$")
	public void productShouldBeAddedInTheCartIfAvailable() throws Throwable {
		WebElement addedProductsInCart = driver.findElement(By.xpath("//div[@class='shop-menu pull-right']/ul/a/b"));
		int numberOfelements = Integer.parseInt(addedProductsInCart.getText());
		Assert.assertEquals(productcount, numberOfelements);
	}

	//@When("^user enters \"([^\"]*)\" and \"([^\"]*)\"$")
	public void userEntersAnd(String arg1, String arg2) throws Throwable {
		PageObjects po = PageFactory.initElements(driver, PageObjects.class);
		LoginObject lo = PageFactory.initElements(driver, LoginObject.class);
		po.signin.click();
		lo.username.sendKeys(arg1);
		lo.password.sendKeys(arg2);
		lo.login.click();
	}

	public static boolean isElementPresent(String locator) {
		int attempts = 0;
		boolean found = false;
		while (attempts < 2) {
			try {
				WebElement element = driver.findElement(By.xpath(locator));
				element.click();
				found = true;
				break;
			} catch (Exception e) {
			}
			attempts++;
		}
		return found;
	}

}
