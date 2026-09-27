package extentReportConfiguration;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportObject {

	
public static ExtentReports getExtentReportObject()
{
	
	String path=System.getProperty("user.dir")+"\\reports\\index.html";	
	ExtentSparkReporter extentSparkReporter=new ExtentSparkReporter(path);
	
	extentSparkReporter.config().setDocumentTitle("web automation tests");
	extentSparkReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
	extentSparkReporter.config().setEncoding("uft-8");
	extentSparkReporter.config().setProtocol(Protocol.HTTPS);
	extentSparkReporter.config().setReportName("Test Report");
	extentSparkReporter.config().setTheme(Theme.DARK);
	
	ExtentReports  extentReports=new ExtentReports();
	extentReports.attachReporter(extentSparkReporter);
	extentReports.setSystemInfo("Tester","Karan");
	
	return extentReports;
	

}
	
	
	
	
	
	
	
}
