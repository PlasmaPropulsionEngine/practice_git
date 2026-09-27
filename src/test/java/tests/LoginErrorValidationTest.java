package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import test_components.BaseTest;

public class LoginErrorValidationTest extends BaseTest {
	
	
	String productName="ZARA COAT 3";		
	
@Test	
public void InvalidEmailPasswordTest()
{
	
	 loginPage.loginApplication("database@gmail.com","Database@11");
	 
	 Assert.assertEquals(loginPage.getLoginErrorMessage(),"Incorrect email or password.");
			
	
}
	
	
	
	
}
