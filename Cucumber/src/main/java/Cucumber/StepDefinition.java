package Cucumber;

import org.testng.Assert;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinition {
	  static int x, y, r;

	 

	    @Given("^i have two numbers as (\\d+) and (\\d+)$")

	    public void iHaveTwoNumbers(int n1, int n2) throws Throwable {

	       x = n1;

	       y = n2;

	    }

	    @When("^i add these numbers$")

	    public void iAddTheseNumbers() throws Throwable {

	       r = x + y;

	    }

	  //  @Then("result should be {int}")

	    public void rresultShouldBe(int result) throws Throwable {

	       Assert.assertEquals(r, result);

	    }

	    @When("^i subtract these numbers$")

	    public void iSubtractTheseNumbers() throws Throwable {

	       r = x - y;

	    }

		@Then("^result should be (\\d+)$")
		public void resultShouldBe(int result) throws Throwable {
			 Assert.assertEquals(r, result);
		}

	

}
