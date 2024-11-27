package config;

import java.awt.Robot;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.HomePage;

public class AppUtil
{
	public static WebDriver driver;
	public static Properties conpro;
	public static ExtentReports report;
	public static ExtentTest logger;
	public static Robot rbt;

	
	@BeforeTest
	
	public static void setUp() throws Throwable
	{
		conpro = new Properties();
		conpro.load(new FileInputStream("./PropertyFiles/Environment.properties"));
		if(conpro.getProperty("Browser").equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
			rbt = new Robot();
			report = new ExtentReports("./target/Reports/RevenueCal.html");
			logger = report.startTest("RevenuePage");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get(conpro.getProperty("Url"));
			Reporter.log("URL launched Successfully in Chrome Browser",true);
			
			//call HomePage class
			HomePage navgtR =PageFactory.initElements(driver, HomePage.class);
			navgtR.HomePg_RvnPg();
			Reporter.log("Navigated to Revenue-calculator page Successfully in Chrome Browser",true);
		}
		else if(conpro.getProperty("Browser").equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get(conpro.getProperty("Url"));
			Reporter.log("URL launched Successfully in firefox Browser",true);
			
			//call HomePage class
			HomePage navgtR =PageFactory.initElements(driver, HomePage.class);
			navgtR.HomePg_RvnPg();
			Reporter.log("Navigated to Revenue-calculator page Successfully firefox Browser",true);
		}
	}
	
	@AfterTest
	public static void tearDown()
		{
			driver.quit();
		}
	}
