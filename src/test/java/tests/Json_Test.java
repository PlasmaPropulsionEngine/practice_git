package tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.CheckOutPage;
import pageObjects.ConformationPage;
import pageObjects.ProductCatalouge;
import test_components.BaseTest;

public class Json_Test extends BaseTest {

@Test(dataProvider = "dataset")	
public void jsonDataTest(HashMap<String,String>data)
{

	ProductCatalouge productCatalouge = loginPage.loginApplication(data.get("email"),data.get("pass"));
	
	productCatalouge.AddProductTocart(data.get("productName"));
	
	CartPage cartPage = productCatalouge.gotoCartPage_Btn();
	
	boolean checkCartProductInList = cartPage.checkCartProductInList(data.get("productName"));
	
	Assert.assertTrue(checkCartProductInList);
	
	CheckOutPage checkOutPage = cartPage.clickOnCheckOutBtn();
	
	checkOutPage.enterCountry("india");
	ConformationPage conformationPage = checkOutPage.clickOPlaceOrderBtn();
	
	String conformationMessage = conformationPage.getConformationMessage();
	
	Assert.assertEquals(conformationMessage,"THANKYOU FOR THE ORDER.");
	
	
}
	
@DataProvider(name="dataset")	
public Object[][] getData() throws IOException
{
	
List<HashMap<String,String>> map = getJsonDataToHashMap(System.getProperty("user.dir")+"\\src\\test\\java\\json_data\\data.json");
		
return new Object[][] {{map.get(0)},{map.get(1)}};
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
