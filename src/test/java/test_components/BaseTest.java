package test_components;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LoginPage;

public class BaseTest {

public WebDriver driver;	
	
public LoginPage loginPage;

public WebDriver initilizeWebDriver() throws IOException
{
	//To  read .properties use this class and it accepts object of fis
	Properties pro=new Properties();
	
FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\Globaldata.properties");

pro.load(fis);	

//Browser is maven parameter , browser key is global data properties
String browserName=System.getProperty("Browser")!=null ? System.getProperty("Browser") : pro.getProperty("browser");

String Options=System.getProperty("Options")!=null ? System.getProperty("Options") : pro.getProperty("visibiliy");

//String browserName = pro.getProperty("browser");

	if(browserName.equalsIgnoreCase("chrome"))
	{
		WebDriverManager.chromedriver().setup();
		ChromeOptions chromeOptions =new ChromeOptions();
		
		if(Options.equalsIgnoreCase("headless"))
		{
			chromeOptions.addArguments("headless");
			chromeOptions.addArguments("--window-size=1920,1080"); // ✅ this actually affects layout in headless
			//chromeOptions.addArguments("--headless=new"); // Modern headless argument
			chromeOptions.addArguments("--start-maximized");
		}
		
		chromeOptions.addArguments("--disable-notifications");
		driver=new ChromeDriver(chromeOptions);
		driver.manage().window().setSize(new Dimension(1440,900));
	
	}
	else if(browserName.equalsIgnoreCase("edge"))
	{
		//WebDriverManager.edgedriver().setup();
		//WebDriverManager.firefoxdriver().setup();
		EdgeOptions edgeoptions =new EdgeOptions();
		
		if(Options.equalsIgnoreCase("headless"))
		{
			edgeoptions.addArguments("headless");
		}
		edgeoptions.addArguments("--disable-notifications");
		driver=new EdgeDriver(edgeoptions);
		driver.manage().window().setSize(new Dimension(1440,900));
				
	}
	
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	
	//driver.manage().window().maximize();
	
	return driver;
		
}


@BeforeMethod(alwaysRun = true)
public LoginPage browserLaunch() throws IOException
{
	 driver = initilizeWebDriver();
	
	 loginPage=new LoginPage(driver); 
	 loginPage.goTo();
	 return loginPage;
}

//alwaysRun attribute is used because when we use groups for particular test or method then 
//testng.xml run only groups tagname not beformethod and driver will not lunch or execute
@AfterMethod(alwaysRun = true)
public void stop()
{

 
        driver.quit();

}
	
//josn data to hashmap conversion	
public List<HashMap<String, String>> getJsonDataToHashMap(String path) throws IOException
{
	
String jsonContent=	FileUtils.readFileToString(new File(path),StandardCharsets.UTF_8);	
	
//object mapper class used for json data conversion using jackson databind libery	
	
ObjectMapper mapper=new ObjectMapper();

List<HashMap<String, String>> map = mapper.readValue(jsonContent,new TypeReference<List<HashMap<String,String>>>(){});
		
return map;		
}
	
//screenshot code	
public String getScreenshot(String testcaseName,WebDriver driver) throws IOException
{
	
TakesScreenshot ts	=(TakesScreenshot)driver;
	
File screenshotAs = ts.getScreenshotAs(OutputType.FILE);

FileUtils.copyFile(screenshotAs,new File(System.getProperty("user.dir")+"\\reports\\"+testcaseName+".png"));
	
String path=System.getProperty("user.dir")+"\\reports\\"+testcaseName+".png";

return path;

	
}
	













	
	
	
	
	
	
	
	
}
