package driverFactory;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.Revenue_CalculatorPage;
import config.AppUtil;
import utilities.ExcelFileUtil;

public class AppTest extends AppUtil
{
	String Inputpath = "./FileInput/Revenue_Calculator.xlsx";
	String Outputpath = "./FileOutPut/RevenueResult.xlsx";
	String TestData = "Revenue_Calculator";
	
	
	
	@Test
	
	public void StartTest() throws Throwable
	{
		
		//call Revenue_calculatorPage Class
		Revenue_CalculatorPage RCP = PageFactory.initElements(driver, Revenue_CalculatorPage.class);
		
		//call ExcelFile For TestData
		ExcelFileUtil xl = new ExcelFileUtil(Inputpath);
		int rc = xl.rowCount(TestData);
		Reporter.log("No of rows are ::"+rc,true);
		
		// Taking loop, coz if we want to enter different value including 560 in textBox
		for (int i = 1; i <=rc; i++) 
		{
			
			String total_patient = xl.getCellData(TestData, i, 1);
			logger.log(LogStatus.PASS, total_patient+" :: The Value fetch From Excel file and entered in TextBox");
			
			RCP.setPatientValueBySlider(820);
			RCP.setPatientValueByTextbox(total_patient);
			RCP.select_cpt_types();
			RCP.validating_header();
						
			report.endTest(logger);
			report.flush();
			
		}
		
	}

}
