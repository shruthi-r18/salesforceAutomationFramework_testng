package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import constants.FileConstants;
import file.utils.propertiesFileUtil;
import listners.sfdcListners;
import pages.loginPage;
import pages.userMenuPage;
@Listeners(sfdcListners.class)
public class userMenuTest extends baseTest {
	
	WebDriver  driver;
	
		@Test
		public void baseUserMenuPage() throws IOException {
			  driver = baseTest.getBrowserType("chrome", false);
		    loginPage lp = new loginPage(driver);
		    
		    Assert.assertTrue(lp.launchApp(driver, "https://login.salesforce.com/"),
					"Actual url needs to match the expected url");
			lp.enterUserName(driver,
					propertiesFileUtil.readPropertiesFile(FileConstants.FILE_PATH_CREDENTIALS, "dev_username"));
			lp.enterpassword(driver,propertiesFileUtil.readPropertiesFile(FileConstants.FILE_PATH_CREDENTIALS, "dev_password"));
			lp.clickLoginButton(driver);
			Assert.assertTrue(lp.isHomePageDisplayed(),"Home Page should be displayed") ;
		}
		
		@Test (enabled=false)
		public void userMenu_TC05() throws IOException, InterruptedException {
//	     	WebDriver driver= loginTest.getdriver();
			userMenuPage ump = new userMenuPage(driver);
			ump.clickUserMenu(driver);
		 	Assert.assertTrue(ump.comparedUserMenuList(),"Lists should match");
	}
		@Test 
		public void userMenu_TC06() throws InterruptedException {
			userMenuPage ump = new userMenuPage(driver);
			ump.clickUserMenu(driver);
			Assert.assertTrue(ump.selectOptionsUserMenuOptions(driver, "My Profile"),"My Profile page should be displayed");
			
		}
		@Test (enabled=false)
		public void userMenu_TC07() {
			userMenuPage ump = new userMenuPage(driver);
			ump.clickUserMenu(driver);
		}
		
		@Test
		public void userMenu_TC08() throws IOException, InterruptedException {
		//	  driver= baseTest.getdriver();
			userMenuPage ump = new userMenuPage(driver );
			ump.clickUserMenu(driver);
//			boolean check = ump.selectOptionsUserMenuOptions(driver, "Developer Console");
//			System.out.println(check);
			 Assert.assertTrue(ump.selectOptionsUserMenuOptions(driver, "Developer Console"), "Developer console should be displayed");
		 	 
	}

}
