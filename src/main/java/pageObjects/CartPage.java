package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import reusableUtility.ResusableUtility;

public class CartPage extends ResusableUtility {

	
WebDriver driver;	
	
	
public CartPage(WebDriver driver)
{
	super(driver);
	this.driver=driver;
	PageFactory.initElements(driver,this);

}	
	
@FindBy(css="div.infoWrap h3")	
List<WebElement>cartProducts;

@FindBy(css="li.totalRow:nth-child(3) button")
WebElement checkOutBtn;

public boolean checkCartProductInList(String productName)
{
		
	boolean anyMatch = cartProducts.stream().anyMatch(m->m.getText().contains(productName));
	
	return anyMatch;
	
}


public CheckOutPage clickOnCheckOutBtn()
{

	checkOutBtn.click();
	CheckOutPage checkOutPage=new CheckOutPage(driver);
	return checkOutPage;
}











	
	
	
	
	
	
	
	
	
}
