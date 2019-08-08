package ObjectReprository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginObject {

	@FindBy(name="userName")
	public WebElement username;
	
	@FindBy(name="password")
	public WebElement password;
	
	@FindBy(name="Login")
	public WebElement login;
}
