package pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import file.utils.propertiesFileUtil;

public class userMenuPage extends basePage {

	public userMenuPage(WebDriver driver) {
		// PageFactory.initElements(driver, loginPage.class);
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userNavButton")
	public WebElement homeUserMenu;

	@FindBy(xpath = "//div[@id='userNav-menuItems']/a")
	public List<WebElement> userMenuOptions;

	@FindBy(id = "userNav-menuItems/a[1]")
	public WebElement myProfile;

	@FindBy(id = "userNav-menuItems/a[2]")
	public WebElement mySettings;

	@FindBy(id = "userNav-menuItems/a[3]")
	public WebElement developerConsole;
	
	@FindBy(id = "userNav-menuItems/a[4]")
	public WebElement SwitchtoLightningExperience;

	@FindBy(id = "userNav-menuItems/a[5]")
	public WebElement logout;

	@FindBy(xpath = "//*/a[@class=\"contactInfoLaunch editLink\"]/img[@alt=\"Edit Profile\"]")
	public WebElement editProfileLink;

	@FindBy(xpath = "//iframe[@id=\"contactInfoContentId\"]")
	public WebElement profileIframe;

	@FindBy(xpath = "//div/textarea[@id=\"street\"]")
	public WebElement addressContactTab;

	@FindBy(xpath = "//li[@id=\"aboutTab\"]")
	public WebElement AboutTab;

	@FindBy(xpath = "//input[@id=\"lastName\"]")
	public WebElement lastNameAboutTab;

	@FindBy(xpath = "//input[@value=\"Save All\"]")
	public WebElement saveProfile;

	@FindBy(id = "publisherAttachTextPost")
	public WebElement postButton;

	@FindBy(xpath = "//iframe[@title='Rich Text Editor, publisherRichTextEditor']")
	public WebElement postTextIframe;

	@FindBy(xpath = "//html[1]/body[1]")
	public WebElement postText;

	@FindBy(id = "publishersharebutton")
	public WebElement postShareButton;

	@FindBy(xpath = "//div[@class=\"cxfeeditemtextadditional\"]")
	public WebElement verifyPostedText;

	@FindBy(xpath = "//span[@class=\"publisherattachtext \" and text()=\"File\"]")
	public WebElement fileButton;

	@FindBy(xpath = "//a[@id='chatterUploadFileAction']")
	public WebElement uploadFileLink;

	@FindBy(id = "chatterFile")
	public WebElement chooseFileButton;

	@FindBy(id = "publishersharebutton")
	public WebElement shareFileLink;

	@FindBy(xpath = "//span[@id=\"displayBadge\"]")
	public WebElement photoModerator;

	@FindBy(id = "uploadLink")
	public WebElement uploadPhotoButton;

	@FindBy(xpath = "//input[@class=\"fileInput\"]")
	public WebElement choosePhotoToUpload;

	@FindBy(xpath = "//div[@class=\"uploadButtonPanel\"]/input[@id=\"j_id0:uploadFileForm:save\"]")
	public WebElement savePhoto;

	@FindBy(xpath = "//div[@id=\"PersonalInfo\"]/a/span[ @id=\"PersonalInfo_font\"]")
	public WebElement personalButton;

	@FindBy(id = "PersonalInfo_child")
	public WebElement settingsPersonalMenu;

	@FindBy(id = "LoginHistory_font")
	public WebElement personalLoginHistory;

	@FindBy(id = "DisplayAndLayout_font")
	public WebElement settingsDisplayLayout;

	@FindBy(id = "DisplayAndLayout_child")
	public WebElement displayLayoutMenu;

	@FindBy(id = "CustomizeTabs_font")
	public WebElement displayLayoutCustomizeTab;

//	@FindBy()
//	public WebElement  ;
//
	public void clickUserMenu(WebDriver driver) {
		if (homeUserMenu.isDisplayed()) {
			homeUserMenu.click();
			logger.info("userMenuPage: ");
		} else {
			System.out.println("User menu is not displayed");
			logger.info("userMenuPage: ");
		}
	}

	public boolean selectOptionsUserMenuOptions(WebDriver driver, String sOptions) throws InterruptedException {
		boolean isOptionClicked = false;
//		
		if (homeUserMenu.isDisplayed()) {
			homeUserMenu.click();
			logger.info("userMenuPage:selectOptionsUserMenuOptions : user menu is displayed");
		}
		Thread.sleep(10000);
		WebElement userMenuOption =driver.findElement(By.xpath("//*[text()='" + sOptions + "']"));
System.out.println("//*[text()='" + sOptions + "']");
		if (userMenuOption.isDisplayed()) {
			userMenuOption.click();
			isOptionClicked = true;
			logger.info("userMenuPage:selectOptionsUserMenuOptions: "+sOptions+" is selected");
		} else {
			System.out.println(sOptions + " Element is not displayed");
			logger.info("userMenuPage:selectOptionsUserMenuOptions : "+sOptions+ " is not selected");
		}
		return isOptionClicked;
	}

	public boolean logout(WebDriver driver) throws InterruptedException {
           boolean isSelected = false;
		if (selectOptionsUserMenuOptions(driver, "Logout")) {
			isSelected = true;
			logger.info("userMenuPage: logout: logout is performed");
		} else {
			logger.info("userMenuPage: logout: logout is performed");
		}
		return isSelected;

	}

	public boolean comparedUserMenuList() throws IOException {
		// TODO Auto-generated method stub
		boolean isOptionsverified = true;
		String[] expectedUserMenuItems = propertiesFileUtil.readUserMenuTestData("exp_menuItems").split(",");
		for (int i = 0; i < expectedUserMenuItems.length; i++) {
			String actualUserMenuItem = userMenuOptions.get(i).getText();
			if (actualUserMenuItem.equals(expectedUserMenuItems[i])) {
				System.out.println("The option " + expectedUserMenuItems[i] + " Passed");
			} else {
				isOptionsverified = false;
				System.out.println(
						"Actual option: " + actualUserMenuItem + " Expected option: " + expectedUserMenuItems[i]);
			}
		}
		return isOptionsverified;
	}

	public boolean selectMyProfile(WebDriver driver) throws InterruptedException {
//		boolean isSelected = false;
//		if (selectOptionsUserMenuOptions(driver, "My Profile")) {
//			isSelected = true;
//			logger.info("userMenuPage: selectMyProfile: My Profile is clicked");
//		} else {
//			logger.info("userMenuPage: selectMyProfile: My Profile is not clicked");
//		}
//		return isSelected;
		return selectOptionsUserMenuOptions(driver, "My Profile");
		 
}
	
public boolean selectMySettings(WebDriver driver) throws InterruptedException {
	boolean isSelected = false;
	if (selectOptionsUserMenuOptions(driver, "My Settings")) {
		isSelected = true;
		logger.info("userMenuPage: selectMySettings: My Settings is clicked");
	} else {
		logger.info("userMenuPage: selectMySettings: My Settings is not clicked");
	}
	return isSelected;
	
}

public boolean selectDeveloperConsole(WebDriver driver) throws InterruptedException {
//	boolean isSelected = false;
//	if (selectOptionsUserMenuOptions(driver, "Developer Console")) {
//		isSelected = true;
//		logger.info("userMenuPage: selectDeveloperConsole:Developer Console is clicked");
//	} else {
//		logger.info("userMenuPage: selectDeveloperConsole: Developer Console is not clicked");
//	}
//	return isSelected;
	return this.selectOptionsUserMenuOptions(driver, "Developer Console");
	
}


	}
