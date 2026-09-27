package tests;

import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;
import pageObjects.CartPage;
import pageObjects.CheckOutPage;
import pageObjects.ConformationPage;
import pageObjects.ProductCatalouge;
import test_components.BaseTest;
import test_components.Retry;

public class StandAlonePageObjectTest  extends BaseTest{

//product name	
String productName="ZARA COAT 3";
	
@Test
public void EndToEndTest()
{
	
	ProductCatalouge productCatalouge = loginPage.loginApplication("database@gmail.com","Database@00");
	
	productCatalouge.AddProductTocart(productName);
	
	CartPage cartPage = productCatalouge.gotoCartPage_Btn();
	
	boolean checkCartProductInList = cartPage.checkCartProductInList(productName);
	
	Assert.assertTrue(checkCartProductInList);
	
	CheckOutPage checkOutPage = cartPage.clickOnCheckOutBtn();
	
	checkOutPage.enterCountry("india");
	ConformationPage conformationPage = checkOutPage.clickOPlaceOrderBtn();
	
	String conformationMessage = conformationPage.getConformationMessage();
	
	Assert.assertEquals(conformationMessage,"THANKYOU FOR THE ORDER.");

	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
