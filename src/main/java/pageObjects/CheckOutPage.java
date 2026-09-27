package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import reusableUtility.ResusableUtility;

public class CheckOutPage extends ResusableUtility{

	
	WebDriver driver;
	
	
public CheckOutPage(WebDriver driver) {

	super(driver);
	this.driver=driver;
	PageFactory.initElements(driver,this);
}	
	
@FindBy(css="input[placeholder='Select Country']")	
WebElement selectCountry;


@FindBy(css="section.list-group button")
List<WebElement>countryOptions;

@FindBy(xpath = "//a[normalize-space()='Place Order']")
WebElement SubmitOrderBtn;

public void enterCountry(String countryName)
{
	Actions a=new Actions(driver);
	
	a.sendKeys(selectCountry,countryName).build().perform();
	
	List<WebElement> options = waitForCountryOptionDisplay(countryOptions);
	
	for(WebElement w:options)
	{
		if(w.getText().equalsIgnoreCase(countryName))
		{
			w.click();
			break;
		}	
	}	
}

public ConformationPage clickOPlaceOrderBtn()
{
	
	SubmitOrderBtn.click();
	ConformationPage conformationPage=new ConformationPage(driver);
	
	return conformationPage;
	
	
}
















	
	
	
	
	
	
	
	
	
	
	
}
