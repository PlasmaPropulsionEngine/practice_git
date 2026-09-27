package extentReportConfiguration;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExtentReportConfiguration {


	WebDriver driver;
	ExtentReports extentReports;

	
@BeforeTest	
public void configurationForExtentReport()
{
	
	String path=System.getProperty("user.dir")+"\\reports\\index.html";	
	ExtentSparkReporter extentSparkReporter=new ExtentSparkReporter(path);
	
	extentSparkReporter.config().setDocumentTitle("web automation tests");
	extentSparkReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
	extentSparkReporter.config().setEncoding("uft-8");
	extentSparkReporter.config().setProtocol(Protocol.HTTPS);
	extentSparkReporter.config().setReportName("Test Report");
	extentSparkReporter.config().setTheme(Theme.DARK);
	
	extentReports=new ExtentReports();
	extentReports.attachReporter(extentSparkReporter);
	extentReports.setSystemInfo("Tester","Karan");
	
	
}


@Test	
public void test() throws IOException
{
	ExtentTest extentTest = extentReports.createTest("open Browser");
	
	WebDriverManager.chromedriver().setup();
	driver=new ChromeDriver();
	driver.get("https://demo.automationtesting.in/Register.html");
	System.out.println(driver.getTitle());
	
	File screenshotAs = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	
	FileUtils.copyFile(screenshotAs,new File(System.getProperty("user.dir")+"\\reports\\shot.png"));
	
	String path=System.getProperty("user.dir")+"\\reports\\shot.png";
	

	extentTest.addScreenCaptureFromPath(path);
	extentReports.flush();
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
