//package steps;
//
//import java.io.IOException;
//import java.lang.reflect.Method;
//
//import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
//
//import com.aventstack.extentreports.Status;
//
//import constants.FileConstants;
//import file.utils.propertiesFileUtil;
//import io.cucumber.java.After;
//import io.cucumber.java.Before;
//import io.cucumber.java.en.*;
//import pages.loginPage;
//
//
//public class Login_Steps extends BaseStep{
//	WebDriver driver;
//	loginPage lp = new loginPage(driver);
//	
//	
//	
//@Before
//@Given("User launches Login Page")
//public void user_launches_login_page() {
//	 driver = BaseStep.getBrowserType("chrome", false);
//	 
//    
//    Assert.assertTrue(lp.launchApp(driver, "https://login.salesforce.com/"),
//			"Actual url needs to match the expected url");
//   
//	
//}
//
//@When("You enter username and password")
//public void you_enter_username_and_password() throws IOException {
//
//	lp.enterUserName(driver,
//			propertiesFileUtil.readPropertiesFile(FileConstants.FILE_PATH_CREDENTIALS, "dev_username"));
//	//test.info("Username is entered");
//	lp.enterpassword(driver,"");
//	//test.error("Enter password");
//}
//
//@When("The login button is clicked")
//public void the_login_button_is_clicked() {
//
//	 lp.clickLoginButton(driver);
////		test.info("Login button is clicked");
//    // Write code here that turns the phrase above into concrete actions
//   // throw new io.cucumber.java.PendingException();
//}
//
//@Then("Home page is displayed")
//public void home_page_is_displayed() throws IOException {
//
//	Assert.assertEquals(lp.loginErrorMessage(),propertiesFileUtil.readPropertiesFile(FileConstants.FILE_PATH_CREDENTIALS, "password_error"), "Enter valid password");
//   // test.pass("Pass : Login page TC01 ");
//}
//
//@After
//public void tear_down() {
//	driver.close();
//}
//
//
//}
