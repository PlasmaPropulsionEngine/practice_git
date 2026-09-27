package tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {


	//WebDriverManager.chromedriver().setup();

	WebDriver driver=new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	driver.manage().window().maximize();
		
	driver.get("https://rahulshettyacademy.com/client");	
		
	//enter username
	driver.findElement(By.id("userEmail")).sendKeys("database@gmail.com");	
	
	//enter passwword
	driver.findElement(By.id("userPassword")).sendKeys("Database@00");	
	
	//click on login btn
	driver.findElement(By.id("login")).click();	
		
	String productName="ZARA COAT 3";
	
	//wait for login success msg vissible
	WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
	
	//assert succes msg
	Assert.assertEquals(toast.getText(),"Login Successfully");
			
	System.out.println("same");
		
//wait for all products to vissible		
List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.card")));

//get zara coat prduct if available
WebElement firstProduct = products.stream().filter(p->p.findElement(By.cssSelector("div.card-body h5 b")).getText()
		.equalsIgnoreCase(productName)).findFirst().orElse(null);
	
//click on add to cart btn
firstProduct.findElement(By.cssSelector("div.card-body button.w-10")).click();
		
	
//wait for add to cart and message show added product	
wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#toast-container")));

//.ng-animating css for loading and wait for loading
wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	
//click on add to cart button
driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click(); 

//get list of cart products		
List<WebElement> cartProducts = driver.findElements(By.cssSelector("div.infoWrap h3"));		

//check cart procut in list
boolean anyMatch = cartProducts.stream().anyMatch(m->m.getText().contains(productName));		
		
Assert.assertTrue(anyMatch);

//click on checkout button
driver.findElement(By.cssSelector("li.totalRow:nth-child(3) button")).click();


Actions a=new Actions(driver);

a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")),"ind")
.build().perform();

List<WebElement> countryOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("section.list-group button")));

for(WebElement w:countryOptions)
{	
	if(w.getText().equalsIgnoreCase("India"))
	{
		
		w.click();
		break;
	}	
}

//click on submit order
driver.findElement(By.xpath("//a[normalize-space()='Place Order']")).click();

String successMsg = driver.findElement(By.xpath("//h1")).getText();		

String expectedMsg=" Thankyou for the order. ";

Assert.assertEquals(successMsg.trim(),expectedMsg.toUpperCase().trim());

driver.close();

		
	}

}
