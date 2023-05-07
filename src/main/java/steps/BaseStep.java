package steps;

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


public class BaseStep {
	public static ThreadLocal<WebDriver> threadlocaldriver = new ThreadLocal<WebDriver>();
	protected static ExtentReports extent = new ExtentReports();
	protected static ExtentHtmlReporter report = null;
	public static ExtentTest test = null;
	protected static Logger logger = LogManager.getLogger("");
//	
	

	@BeforeMethod
	public void setDriver(Method name) {
		WebDriver driver = BaseStep.getBrowserType("chrome", false);
		logger.debug("BaseStep: setDriver  driver congiguration complete");
		threadlocaldriver.set(driver);
		logger.debug("BaseStep: setDriver  driver saved on to threadlocal object");
		BaseStep.test = extent.createTest(name.getName());
		logger.debug("BaseStep: setDriver  test object created");
	}

	public static WebDriver getdriver() {
		logger.debug("BaseStep: getdriver driver configuratin retrieved from the thread");
		return threadlocaldriver.get();
	}
@AfterMethod
	public void removeDriver() {
	BaseStep.getdriver().close();
		threadlocaldriver.remove();
		logger.debug("BaseStep: removeDriver  driver congiguration removed from thread");
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
				logger.debug("BaseStep: getBrowserType Chrome headless browser configured");
			} else {
				driver = new ChromeDriver();
				logger.debug("BaseStep: getBrowserType Chrome browser configured");
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
				logger.debug("BaseStep: getBrowserType Edge headless browser configured");
			} else {
				driver = new EdgeDriver();
				logger.debug("BaseStep: getBrowserType Edge browser configured");
			}
			driver.manage().window().maximize();
			break;

		case "safari":
			WebDriverManager.chromedriver().setup();
			if (headless == true) {
				SafariOptions so = new SafariOptions();
				so.setCapability("--headless", true);
				driver = new SafariDriver(so);
				logger.debug("BaseStep: getBrowserType Safari headless browser configured");
			} else {
				driver = new ChromeDriver();
				logger.debug("BaseStep: getBrowserType Safari browser configured");
			}
			driver.manage().window().maximize();
			break;

		default:
			System.out.println("Enter valid browser: chrome,edge or safari");
			logger.error("BaseStep: getBrowserType browser name input is wrong");
		}
		return driver;
	}



}
