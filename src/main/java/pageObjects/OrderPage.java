package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import reusableUtility.ResusableUtility;

public class OrderPage extends ResusableUtility{
	WebDriver driver;

	public OrderPage(WebDriver driver) 
	{
		
		super(driver);
		this.driver=driver;
		
		PageFactory.initElements(driver,this);
		
	}
	
@FindBy(css="tr td:nth-child(3)")	
List<WebElement>productNames;	
	
	
public boolean checkProductDisplayOnPage(String productName)
{
	
	boolean anyMatch = productNames.stream().anyMatch(m->m.getText().contentEquals(productName));
	
	return anyMatch;
}
	
	
	
	
	
	
	
	
	
	
}
