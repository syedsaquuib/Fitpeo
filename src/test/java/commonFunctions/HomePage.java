package commonFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.relevantcodes.extentreports.LogStatus;

import config.AppUtil;
import driverFactory.AppTest;

public class HomePage extends AppUtil
{
	@FindBy (xpath = "//div[contains(text(),'Revenue Calculator')]")
	WebElement RevCal;
	
	
	
	public void HomePg_RvnPg()
	{
		RevCal.click();
		logger.log(LogStatus.PASS, "Navigating from Home_Page to Revenue-Calculator page");
	}

}
