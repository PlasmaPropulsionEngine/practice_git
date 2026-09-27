package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import reusableUtility.ResusableUtility;

public class ConformationPage  extends ResusableUtility{

	
WebDriver driver;


public ConformationPage(WebDriver driver) 
{
	super(driver);
	this.driver=driver;
	PageFactory.initElements(driver,this);
	
	
}
	

@FindBy(xpath = "//h1")
WebElement conformationMessage;	
	
	
public String getConformationMessage()
{
	
	String text = conformationMessage.getText();
	return text;
}
	
	
	
	
	
	
	
	
	
	
	
}
