package tests;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.CheckOutPage;
import pageObjects.ConformationPage;
import pageObjects.ProductCatalouge;
import test_components.BaseTest;

public class DataProviderHashMap extends BaseTest{


@Test(dataProvider = "dataset")	
public void dataProviderHashMapTest(HashMap<String,String>data)
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
public Object[][] getData()
{
	
	Object[][] data=new Object[2][3]; 	
HashMap<String,String>map=new HashMap<String, String>();
	
	map.put("email","database@gmail.com");
	map.put("pass","Database@00");
	map.put("productName","ZARA COAT 3");
	
HashMap<String,String>map1=new HashMap<String, String>();
	
	map1.put("email","jpa@gmail.com");
	map1.put("pass","Database@00");
	map1.put("productName","ADIDAS ORIGINAL");
	
	return new Object[][] {{map},{map1}};
	
	
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
