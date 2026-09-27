package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.CheckOutPage;
import pageObjects.ConformationPage;
import pageObjects.ProductCatalouge;
import test_components.BaseTest;

public class DataProviderTest extends BaseTest {

	
//product name	
//String productName="ZARA COAT 3";	
	
@Test(dataProvider = "dataset")	
public void dataProviderTest(String email,String pass,String productName)
{
	
ProductCatalouge productCatalouge = loginPage.loginApplication(email,pass);
	
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
	
@DataProvider(name = "dataset")
public Object[][] getData()
{
	
	Object[][] data=new Object[2][3]; 
	
//	data[0][0]="database@gmail.com";
//	data[0][1]="Database@00";
//	data[0][2]="ZARA COAT 3";
//	
//	data[1][0]="jpa@gmail.com";
//	data[1][1]="Database@00";
//	data[1][2]="ADIDAS ORIGINAL";
//	
//	return data;
	
	
	return new Object[][] {{"database@gmail.com","Database@00","ZARA COAT 3"},{"jpa@gmail.com","Database@00","ADIDAS ORIGINAL"}};
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
