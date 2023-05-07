package tests;


import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import constants.FileConstants;
import file.utils.propertiesFileUtil;
import listners.sfdcListners;
import pages.loginPage;
import pages.userMenuPage;


@Listeners(sfdcListners.class)
public class loginTest extends baseTest {
	
	
	
	@Test 
	public void LoginTest_TC01() throws IOException {
		WebDriver driver = baseTest.getBrowserType("chrome", false);
	    loginPage lp = new loginPage(driver);
	    
	    Assert.assertTrue(lp.launchApp(driver, "https://login.salesforce.com/"),
				"Actual url needs to match the expected url");
	    test.log(Status.INFO, "App is launched");
		lp.enterUserName(driver,
				propertiesFileUtil.readPropertiesFile(FileConstants.FILE_PATH_CREDENTIALS, "dev_username"));
		test.info("Username is entered");
		lp.enterpassword(driver,"");
		test.error("Enter password");
		lp.clickLoginButton(driver);
		test.info("Login button is clicked");
		Assert.assertEquals(lp.loginErrorMessage(),propertiesFileUtil.readPropertiesFile(FileConstants.FILE_PATH_CREDENTIALS, "password_error"), "Enter valid password");
	    test.pass("Pass : Login page TC01 ");
	}
	
	@Test 
	public void LoginTest_TC02() throws IOException {
		WebDriver driver = baseTest.getBrowserType("chrome", false);
	    loginPage lp = new loginPage(driver);
	    
	    Assert.assertTrue(lp.launchApp(driver, "https://login.salesforce.com/"),
				"Actual url needs to match the expected url");
	    test.info("App is launched");
		lp.enterUserName(driver,
				propertiesFileUtil.readPropertiesFile(FileConstants.FILE_PATH_CREDENTIALS, "dev_username"));
		test.info("Username is entered");
		lp.enterpassword(driver,propertiesFileUtil.readPropertiesFile(FileConstants.FILE_PATH_CREDENTIALS, "dev_password"));
		test.info("Password is entered");
		lp.clickLoginButton(driver);
		test.info("Login button is clicked");
		Assert.assertTrue(lp.isHomePageDisplayed(),"Home Page should be displayed") ;
		test.pass("Pass : Login page TC02 ");
	}

	@Test  
	public void loginTest_TC03() throws IOException, InterruptedException {

		WebDriver driver = baseTest.getBrowserType("chrome", false);
		// driver.get("https://login.salesforce.com/");
		loginPage lp = new loginPage(driver);
		userMenuPage ump = new userMenuPage(driver);

		Assert.assertTrue(lp.launchApp(driver, "https://login.salesforce.com/"),
				"Actual url needs to match the expected url");
		test.info("App is launched");
		lp.enterUserName(driver,
				propertiesFileUtil.readPropertiesFile(FileConstants.FILE_PATH_CREDENTIALS, "dev_username"));
		test.info("Username is entered");
		lp.enterpassword(driver,
				propertiesFileUtil.readPropertiesFile(FileConstants.FILE_PATH_CREDENTIALS, "dev_password"));
		test.info("Password is entered");
		Assert.assertTrue(lp.selectRememberMeCheckBox(), "Remember me checkbox has to be selected");
		test.info("Rememberme checkek is clicked");
		lp.clickLoginButton(driver);
		test.info("Login button is clicked");
		Assert.assertTrue(lp.isHomePageDisplayed(), "Home page should be displayed");
		test.info("Home page is displayed");
		Assert.assertTrue(ump.logout(driver), "Failed to Logout ");
		test.info("Logout button is clicked");
		Assert.assertTrue(lp.isLoginPageDisplayed(driver), "Login page should be displayed");
		test.info("Login page is displayed");
		Assert.assertEquals(lp.getSavedUserName(driver),
				propertiesFileUtil.readPropertiesFile(FileConstants.FILE_PATH_CREDENTIALS, "dev_username"),
				"Username should be a match");
		test.pass("Pass : Login page TC03 ");

	}

	 
	@Test 
	public void LoginTest_TC4A() throws IOException {
		WebDriver driver = baseTest.getBrowserType("chrome", false);
	    loginPage lp = new loginPage(driver);
	    
	    Assert.assertTrue(lp.launchApp(driver, "https://login.salesforce.com/"),
				"Actual url needs to match the expected url");
	    test.info("App is launched");
		lp.forgotPassword(driver);
		test.info("Forgot password is clicked");
		Assert.assertEquals(lp.forgotPasswordMessage(),propertiesFileUtil.readPropertiesFile(FileConstants.FILE_PATH_CREDENTIALS, "forgot_password"),"check your email"); 
		test.pass("Pass : Login page TC04A ");
	}
	
	@Test 
	public void LoginTest_TC4B() throws IOException {
		WebDriver driver = baseTest.getBrowserType("chrome", false);
	    loginPage lp = new loginPage(driver);
	    
	    Assert.assertTrue(lp.launchApp(driver, "https://login.salesforce.com/"),
				"Actual url needs to match the expected url");
	    test.info("App is launched");
		lp.enterUserName(driver,
				propertiesFileUtil.readPropertiesFile(FileConstants.FILE_PATH_CREDENTIALS, "wrong_username"));
		test.error("Username entered is not valid");
		lp.enterpassword(driver,propertiesFileUtil.readPropertiesFile(FileConstants.FILE_PATH_CREDENTIALS, "wrong_password"));
		test.error("Password entered is not valid");
		lp.clickLoginButton(driver);
		test.info("Login button is clicked");
		Assert.assertEquals(lp.loginErrorMessage(),propertiesFileUtil.readPropertiesFile(FileConstants.FILE_PATH_CREDENTIALS, "wrong_credentials"),"Enter valid username and password"); 
		test.pass("Pass : Login page TC04B ");
	}
}
