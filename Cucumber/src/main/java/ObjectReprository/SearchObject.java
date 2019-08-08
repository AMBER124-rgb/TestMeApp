package ObjectReprository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SearchObject {

	@FindBy(how=How.XPATH,using = "")
	public WebElement addtocart;
	
	@FindBy(name="")
	public WebElement home;
}
