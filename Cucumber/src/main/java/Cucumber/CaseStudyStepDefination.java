package Cucumber;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import ObjectReprository.LoginObject;
import ObjectReprository.PageObjects;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CaseStudyStepDefination {
	
	@And("^Go to the signIn$")
	public void goToTheSignIn() throws Throwable {
		driver.findElement(By.xpath("//a[@href='RegisterUser.htm']")).click();
	}

	@When("^user enter username as \"([^\"]*)\"$")
	public void userEnterUsernameAs(String arg1) throws Throwable {
		driver.findElement(By.name("userName")).sendKeys(arg1);
	}

	@When("^user enter firstname as \"([^\"]*)\"$")
	public void userEnterFirstnameAs(String arg1) throws Throwable {
		driver.findElement(By.name("firstName")).sendKeys(arg1);
	}

	@When("^user enter lastname as \"([^\"]*)\"$")
	public void userEnterLastnameAs(String arg1) throws Throwable {
		driver.findElement(By.name("lastName")).sendKeys(arg1);
	}

	@When("^user enter password as \"([^\"]*)\"$")
	public void userEnterPasswordAs(String arg1) throws Throwable {
		driver.findElement(By.name("password")).sendKeys(arg1);
	}

	@When("^user enter confirm password as \"([^\"]*)\"$")
	public void userEnterConfirmPasswordAs(String arg1) throws Throwable {
		driver.findElement(By.name("confirmPassword")).sendKeys(arg1);
	}

	@When("^user select gender as \"([^\"]*)\"$")
	public void userSelectGenderAs(String arg1) throws Throwable {
		if(arg1.equalsIgnoreCase("male")) {
			driver.findElement(By.xpath("//input[@value='Male']")).click();
		}
		else 
			if(arg1.equalsIgnoreCase("female")) {
				driver.findElement(By.xpath("//input[@value='Female']")).click();
			}
	}

	@And("^user enter email as \"([^\"]*)\"$")
	public void userEnterEmailAs(String arg1) throws Throwable {
		driver.findElement(By.name("emailAddress")).sendKeys(arg1);
	}

	@And("^user enter mobilenumbner as \"([^\"]*)\"$")
	public void userEnterMobilenumbnerAs(String arg1) throws Throwable {
		driver.findElement(By.name("mobileNumber")).sendKeys(arg1);
	}

	@When("^user enter dob as \"([^\"]*)\"$")
	public void userEnterDobAs(String arg1) throws Throwable {
		driver.findElement(By.name("dob")).sendKeys(arg1);
	}

	@When("^user enter address as \"([^\"]*)\"$")
	public void userEnterAddressAs(String arg1) throws Throwable {
		driver.findElement(By.name("address")).sendKeys(arg1);
	}

	@And("^user selects security question as \"([^\"]*)\" and enter answer as \"([^\"]*)\"$")
	public void userSelectsSecurityQuestionAsAndEnterAnswerAs(String arg1, String arg2) throws Throwable {
		Select question = new Select(driver.findElement(By.name("securityQuestion")));
		question.selectByVisibleText(arg1);
		driver.findElement(By.name("answer")).sendKeys(arg2);
	}

	@Then("^user clicks on Register$")
	public void userClicksOnRegister() throws Throwable {
		driver.findElement(By.xpath("//input[@value='Register']")).click();
	}

	@And("^verify he is registered successsfully$")
	public void verifyHeIsRegisteredSuccesssfully() throws Throwable {
		String message = driver.findElement(By.xpath("//fieldset/div[8]")).getText();
			Assert.assertEquals(message.trim(),"User Registered Succesfully!!! Please login");
	}

	static WebDriver driver;
	int productcount=0;
	
	@Given("^Navigate to Home Page$")
	public void navigateToHomePage() throws Throwable {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Automation\\Cucumber\\Cucumber\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://10.232.237.143:443/TestMeApp/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@When("^user enters username and password$")
	public void userEntersUsernameAndPassword() throws Throwable {

		PageObjects po = PageFactory.initElements(driver, PageObjects.class);
		LoginObject lo = PageFactory.initElements(driver, LoginObject.class);
		po.signin.click();
		lo.username.sendKeys("lalitha");
		lo.password.sendKeys("password123");
		lo.login.click();
	}

	@Then("^user logged in successfully$")
	public void userLoggedInSuccessfully() throws Throwable {
		//Assert.assertEquals(driver.getTitle(), "Admin Home");
		//driver.quit();

	}
	
	
	
	@Given("^Larry has registered in to TestMeApp$")
	public void larryHasRegisteredInToTestMeApp() throws Throwable {

	}

	@When("^Larry searches below products in the search box:$")
	public void larrySearchesBelowProductsInTheSearchBox(DataTable productsdata) throws Throwable {
		List<String> productsName = productsdata.asList(String.class);
		//navigateToHomePage();
		//userEntersUsernameAndPassword();

		for (String product : productsName) {
			driver.findElement(By.name("products")).sendKeys(product);

			driver.findElement(By.xpath("//input[@value='FIND DETAILS']")).click();

			boolean found = isElementPresent("//a[text()[contains(.,'Add to cart')]]");
			if(found) {
				productcount++;
			}

			driver.findElement(By.xpath("//a[text()[contains(.,'Home')]]")).click();

		}

	}

	@Then("^product should be added in the cart if available$")
	public void productShouldBeAddedInTheCartIfAvailable() throws Throwable {
		WebElement addedProductsInCart = driver.findElement(By.xpath("//div[@class='shop-menu pull-right']/ul/a/b"));
		int numberOfelements = Integer.parseInt(addedProductsInCart.getText());
		Assert.assertEquals(productcount, numberOfelements);
	}

	@When("^user enters \"([^\"]*)\" and \"([^\"]*)\"$")
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

	
	@Given("^product added in the cart$")
	public void productAddedInTheCart() throws Throwable {
		
	}

	@When("^user open cart$")
	public void userOpenCart() throws Throwable {
	driver.findElement(By.xpath("//a[@href='displayCart.htm']")).click();
	}

	@And("^user click on checkout$")
	public void userClickOnCheckout() throws Throwable {
		driver.findElement(By.xpath("//a[@href='checkout.htm']")).click();
	}



	@And("^user fills address as \"([^\"]*)\" details$")
	public void userFillsAddressAsDetails(String arg1) throws Throwable {
		driver.findElement(By.name("ShippingAdd")).sendKeys(arg1);
	}

	@And("^user clicks on proceed to pay$")
	public void userClicksOnProceedToPay() throws Throwable {
		driver.findElement(By.xpath("//input[@value='Proceed to Pay']")).click();
	}

	@Then("^verify Welcome to payment Gateway$")
	public void verifyWelcomeToPaymentGateway() throws Throwable {
		String message1 = driver.findElement(By.xpath("//h2")).getText();
		Assert.assertEquals(message1.trim(),"Welcome to Payment Gateway -");
	}
	
	
	
	

	
	
	
	
	
	
}
