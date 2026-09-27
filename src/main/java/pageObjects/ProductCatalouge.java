package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import reusableUtility.ResusableUtility;

public class ProductCatalouge extends ResusableUtility {

	WebDriver driver;
	
	
	public ProductCatalouge(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
				
		PageFactory.initElements(driver,this);
		
	}
	

	@FindBy(css="div.card")
	List<WebElement>products;
	
	
	By productTitle=By.cssSelector("div.card-body h5 b");
	
	
	By addToCartBtn=By.cssSelector("div.card-body button.w-10");
	
	@FindBy(css=".ng-animating")
	WebElement addtoCartMsg;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
//get list of products	
public List<WebElement> getProductList()
{
	
	List<WebElement> productList = waitFortoDisplayAllproducts(products);
	
	return productList;	
}
	

//get product name from list 
public WebElement getProductByName(String productName)
{
		waitForProductTitle(productTitle);	
		
	WebElement singleProduct = getProductList().stream()
	.filter(p->p.findElement(productTitle).getText().equalsIgnoreCase(productName))
	.findFirst().orElse(null);
		return singleProduct;
}


//click on add to cart btn

public void AddProductTocart(String productName)
{
	
	WebElement productByName = getProductByName(productName);
	
	productByName.findElement(addToCartBtn).click();
	
	waitForSpinnerToDisapper(addtoCartMsg,spinner);	
	
}








	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
