package test_components;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import extentReportConfiguration.ExtentReportObject;

public class Listeners extends BaseTest implements ITestListener{

	
	ExtentTest extentTest;
	
	ExtentReports extentReports  =ExtentReportObject.getExtentReportObject();
	
	ThreadLocal<ExtentTest>threadtest=new ThreadLocal<ExtentTest>();
	//ThreadLocal is class used for make the extentTest object synchroized and make thread safe it provides unique  id for particular thread

	@Override
	public void onTestStart(ITestResult result) {
		
		 extentTest = extentReports.createTest(result.getMethod().getMethodName());
		threadtest.set(extentTest); //it provides unique thread id for each extenttest object for each test
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		//extentTest.log(Status.PASS,"test passed !");
		
		threadtest.get().log(Status.PASS,"test passed !");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		//extentTest.fail(result.getThrowable());
		threadtest.get().fail(result.getThrowable());
		
		//take screenshot of failed test 
		
		try 
		{
			 driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			
		} 
		catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e1) {
		e1.printStackTrace();
		}
		
		String screenShotpath=null; 
		
		try 
		{
			screenShotpath = getScreenshot(result.getMethod().getMethodName(), driver);
			
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		//extentTest.addScreenCaptureFromPath(screenShotpath,result.getMethod().getMethodName());
		threadtest.get().addScreenCaptureFromPath(screenShotpath,result.getMethod().getMethodName());
		
	}

	
	@Override
	public void onTestSkipped(ITestResult result) {

		//extentTest.log(Status.SKIP,result.getMethod().getMethodName());
		threadtest.get().log(Status.SKIP,result.getMethod().getMethodName());
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
		extentReports.flush();
		
	}

	
	
	
	
	
	
	
	
	
	
}
