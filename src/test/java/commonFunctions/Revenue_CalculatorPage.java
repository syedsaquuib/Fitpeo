package commonFunctions;


import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import com.relevantcodes.extentreports.LogStatus;

import config.AppUtil;




public class Revenue_CalculatorPage extends AppUtil
{
	WebDriver driver;


	//constructor for invoking webdriver methods
	public Revenue_CalculatorPage(WebDriver driver)
	{
		this.driver=driver;
	}

	@FindBy (xpath = "//input[@aria-valuemax='2000']")
	WebElement Slider;

	@FindBy (xpath = "//input[@type='number']")
	WebElement Textbox;

	@FindBy (xpath = "//span[contains(text(),'57')]")
	WebElement CPT_99091_CheckBox;

	@FindBy (xpath = "//span[contains(text(),'19.19')]")
	WebElement CPT_99453_CheckBox;

	@FindBy (xpath = "//span[contains(text(),'63')]")
	WebElement CPT_99454_CheckBox;

	@FindBy (xpath = "//span[contains(text(),'15')]")
	WebElement CPT_99474_CheckBox;

	@FindBy (xpath = "//p[contains(text(),'Total Recurring')]/p")
	WebElement H_Display;



	public void setPatientValueBySlider(int no_patient) throws Throwable
	{
		Thread.sleep(3000);
		
		//ScrollDown Upto Slider
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,400)");
		logger.log(LogStatus.PASS, "ScrollDown the page Upto Slider");
		Thread.sleep(3000);
		
		//Click and move Slider
		Actions ac = new Actions(driver);

		//Move Slider to desired Value
		ac.clickAndHold(Slider).moveByOffset(93, 0).perform();
		
		for(int i=1; i<=4; i++)
		{
			rbt.keyPress(KeyEvent.VK_RIGHT);
			rbt.keyRelease(KeyEvent.VK_RIGHT);
			Thread.sleep(1000);

		}
		ac.release().perform();			
		
		logger.log(LogStatus.PASS, "Move Slider to 820");
		Thread.sleep(2000);


	}

	public void setPatientValueByTextbox(String no_patient) throws Throwable
	{
		//Click to TextBox
		this.Textbox.click();
		rbt.keyPress(KeyEvent.VK_CONTROL);
		rbt.keyPress(KeyEvent.VK_A);
		rbt.keyPress(KeyEvent.VK_BACK_SPACE);
		logger.log(LogStatus.PASS, "Click to TextBox and clear the existing value");

		Thread.sleep(2000);
		//Enter Value in TextBox
		this.Textbox.sendKeys(no_patient);
		logger.log(LogStatus.PASS, "Enter 560 in TextBox");
		Thread.sleep(2000);

	}

	public void select_cpt_types() throws Throwable
	{
		JavascriptExecutor js =(JavascriptExecutor)driver;
		
		//Check the CPT_99091_CheckBox
		this.CPT_99091_CheckBox.click();
		logger.log(LogStatus.PASS, "Check the CPT_99091_CheckBox");
		Thread.sleep(2000);

		//Check the CPT_99453_CheckBox
		this.CPT_99453_CheckBox.click();
		logger.log(LogStatus.PASS, "Check the CPT_99453_CheckBox");
		Thread.sleep(2000);

		//Check the CPT_99454_CheckBox
		this.CPT_99454_CheckBox.click();
		logger.log(LogStatus.PASS, "Check the CPT_99454_CheckBox");
		Thread.sleep(2000);

		//Scroll Upto CPT_99474_CheckBox is Successful
		js.executeScript("window.scrollToview",CPT_99474_CheckBox);
		logger.log(LogStatus.PASS, "Scroll Upto CPT_99474_CheckBox");
		Thread.sleep(2000);

		//Check the CPT_99474_CheckBox is Successful
		this.CPT_99474_CheckBox.click();
		logger.log(LogStatus.PASS, "Check the CPT_99474_CheckBox");
		Thread.sleep(2000);


	}


	public boolean validating_header()
	{
		//Validating the Heading_Term
		String Exp_data = "$110700";
		logger.log(LogStatus.PASS," ::Exp Value :: "+Exp_data);

		String Act_data = this.H_Display.getText();
		logger.log(LogStatus.PASS,"Act Value :: "+Act_data);

		if(Act_data.equals(Exp_data))
		{
			logger.log(LogStatus.PASS,"Actual_value :: "+Act_data+"    "+"Expected_Value"+Exp_data+"  "+ "Validation PASS :: Expected is Equal to Actual");
			Reporter.log("Runtime_Value :: "+Act_data+"    "+"Expected_Value"+Exp_data+"  "+"Expected value is Matching with Actual",true);
			return true;
		}
		else
		{
			logger.log(LogStatus.FAIL,"Actual_value :: "+Act_data+"    "+"Expected_Value"+Exp_data+"  "+ "Validation FAIL :: Expected is not equal to Actual");
			Reporter.log("Runtime_Value :: "+Act_data+"    "+"Expected_Value"+Exp_data+"  "+"Expected value is not matching with Actual",true);
			return false;
		}


	}






}
