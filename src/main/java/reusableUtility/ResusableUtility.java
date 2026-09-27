package reusableUtility;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.CartPage;
import pageObjects.OrderPage;

public class ResusableUtility {

WebDriver driver;

public ResusableUtility(WebDriver driver)
{
	
	this.driver=driver;
	PageFactory.initElements(driver,this);	
}
	

//wait for login succes message	
public String waitForLoginSuccesMsgAppers(WebElement w)
{
		
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	//wait for login success msg vissible
	WebElement toast = wait.until(ExpectedConditions.visibilityOf(w));		
	return toast.getText();
	
}
	
	
//wait for all product vissible on productcatalouge page		
public List<WebElement> waitFortoDisplayAllproducts(List<WebElement> products2)
{
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElements(products2));

	return products;
}


//wait for single product titles bold names
public WebElement waitForProductTitle(By productTitle)
{
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	WebElement productTitlew = wait.until(ExpectedConditions.visibilityOfElementLocated(productTitle));
	
	return productTitlew;
	
}

//wait to spinner to disappers
public void waitForSpinnerToDisapper(WebElement addtoCartMsg,WebElement spinner)
{
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));

	wait.until(ExpectedConditions.visibilityOf(addtoCartMsg));
	
	wait.until(ExpectedConditions.invisibilityOf(spinner));
}


//wait for country option to display

public List<WebElement> waitForCountryOptionDisplay(List<WebElement>countryOptions)
{
	
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElements(countryOptions));
	return options;
}

//wait for login error message	
public void waitToAppearLoginErrorMessage(WebElement w)
{
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOf(w));
	
}

//waitForLoginBtnForHeadlessMode
public WebElement waitForLoginBtnForHeadlessMode(WebElement w)
{
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(w));	
	return loginBtn;
	
}



//header bar cart btn	
@FindBy(xpath ="//button[@routerlink='/dashboard/cart']")
WebElement CartBtn;

//click on order header btn
@FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
WebElement OrderheaderBtn;



public CartPage gotoCartPage_Btn()
{
	
CartBtn.click();
CartPage cartPage=new CartPage(driver);
return cartPage;
	
}

public OrderPage goToOrderPage_Btn()
{
	OrderheaderBtn.click();
	OrderPage orderPage=new OrderPage(driver);
	return orderPage;
	
}


	
}
