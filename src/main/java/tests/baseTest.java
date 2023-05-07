package tests;

 

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author shrut
 *
 */

public class baseTest {

	public static ThreadLocal<WebDriver> threadlocaldriver = new ThreadLocal<WebDriver>();
	protected static ExtentReports extent = new ExtentReports();
	protected static ExtentHtmlReporter report = null;
	public static ExtentTest test = null;
	protected static Logger logger = LogManager.getLogger("");
	
	@BeforeSuite
	public void setup() {
		logger.debug("baseTest: setup  report configuration started");
		String sFileNamePrefix = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		 String filePath = System.getProperty("user.dir")+"\\src\\main\\resources\\reports\\"+sFileNamePrefix+"_SFDC.html";
		 report = new ExtentHtmlReporter(filePath);
		 extent.attachReporter(report);
		 logger.debug("baseTest: setup  report configuration completed");
			}
	
	@AfterSuite
	public void tearDown() {
		extent.flush();
		logger.debug("baseTest: tearDown  complete");
	}

	@BeforeMethod
	public void setDriver(Method name) {
		WebDriver driver = baseTest.getBrowserType("chrome", false);
		logger.debug("baseTest: setDriver  driver congiguration complete");
		threadlocaldriver.set(driver);
		logger.debug("baseTest: setDriver  driver saved on to threadlocal object");
		baseTest.test = extent.createTest(name.getName());
		logger.debug("baseTest: setDriver  test object created");
	}

	public static WebDriver getdriver() {
		logger.debug("baseTest: getdriver driver configuratin retrieved from the thread");
		return threadlocaldriver.get();
	}
@AfterMethod
	public void removeDriver() {
		baseTest.getdriver().close();
		threadlocaldriver.remove();
		logger.debug("baseTest: removeDriver  driver congiguration removed from thread");
	}

	/**
	 * @param browserName
	 * @param headless
	 * @return
	 */
	public static WebDriver getBrowserType(String browserName, boolean headless) {

		String browser_Name = browserName.toLowerCase();
		WebDriver driver = null;
		switch (browser_Name) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			if (headless == true) {
				ChromeOptions co = new ChromeOptions();
				co.addArguments("--headless");
				driver = new ChromeDriver(co);
				logger.debug("baseTest: getBrowserType Chrome headless browser configured");
			} else {
				driver = new ChromeDriver();
				logger.debug("baseTest: getBrowserType Chrome browser configured");
			}
			driver.manage().window().maximize();
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			if (headless == true) {
				EdgeOptions eo = new EdgeOptions();
				eo.setPageLoadStrategy("normal");
				eo.setCapability("--headless", true);
				driver = new EdgeDriver(eo);
				logger.debug("baseTest: getBrowserType Edge headless browser configured");
			} else {
				driver = new EdgeDriver();
				logger.debug("baseTest: getBrowserType Edge browser configured");
			}
			driver.manage().window().maximize();
			break;

		case "safari":
			WebDriverManager.chromedriver().setup();
			if (headless == true) {
				SafariOptions so = new SafariOptions();
				so.setCapability("--headless", true);
				driver = new SafariDriver(so);
				logger.debug("baseTest: getBrowserType Safari headless browser configured");
			} else {
				driver = new ChromeDriver();
				logger.debug("baseTest: getBrowserType Safari browser configured");
			}
			driver.manage().window().maximize();
			break;

		default:
			System.out.println("Enter valid browser: chrome,edge or safari");
			logger.error("baseTest: getBrowserType browser name input is wrong");
		}
		return driver;
	}

}
