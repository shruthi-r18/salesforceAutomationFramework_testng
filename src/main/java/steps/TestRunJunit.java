package steps;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src\\main\\java\\features" , glue="steps" ,
plugin ={"pretty","html:\\src\\main\\resources\\reports\\cucumber.html"}, monochrome=true)
public class TestRunJunit {
	protected static ExtentReports extent = new ExtentReports();
	protected static ExtentHtmlReporter report = null;
	public static ExtentTest test = null;
	protected static Logger logger = LogManager.getLogger("");
	@BeforeClass
	public static void  setup() {
		logger.debug("BaseStep: setup  report configuration started");
		String sFileNamePrefix = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		 String filePath = System.getProperty("user.dir")+"\\src\\main\\resources\\reports\\"+sFileNamePrefix+"_SFDC.html";
		 report = new ExtentHtmlReporter(filePath);
		 extent.attachReporter(report);
		 logger.debug("BaseStep: setup  report configuration completed");
			}
	
	@AfterClass
	public static void tearDown() {
		extent.flush();
		logger.debug("baseTest: tearDown  complete");
	}

}
