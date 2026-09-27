package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import reusableUtility.ResusableUtility;

public class LoginPage extends ResusableUtility{

		
WebDriver driver;

public LoginPage(WebDriver driver)
{
	super(driver);
	this.driver=driver;
	
	PageFactory.initElements(driver,this);
	
}

@FindBy(id="toast-container")
WebElement loginErrorMsg;



@FindBy(id = "userEmail")
WebElement userEmail;


@FindBy(id = "userPassword")
WebElement userPassword;


@FindBy(id = "login")
WebElement loginBtn;

@FindBy(id="toast-container")
WebElement loginSuccesToastMsg; 

	
public ProductCatalouge loginApplication(String username,String password)
{
	
	userEmail.sendKeys(username);
	userPassword.sendKeys(password);
	WebElement loginBtnClickable = waitForLoginBtnForHeadlessMode(loginBtn);
	loginBtnClickable.click();
	//loginBtn.click();
	//String loginSuccessMsg = waitForLoginSuccesMsgAppers(loginSuccesToastMsg);
	//Assert.assertEquals(loginSuccessMsg,"Login Successfully");

	ProductCatalouge productCatalouge=new ProductCatalouge(driver);
	return productCatalouge;
}
public void goTo()
{
	driver.get("https://rahulshettyacademy.com/client");
	
}	
	
	
public String getLoginErrorMessage()
{
	
	waitToAppearLoginErrorMessage(loginErrorMsg);
	
	String text = loginErrorMsg.getText();
	
	return text;
		
}
	
	
	
	
	
	
	
	
}
