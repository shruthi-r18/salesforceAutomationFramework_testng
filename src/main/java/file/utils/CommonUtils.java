package file.utils;

 import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import constants.waitConstants;

public class CommonUtils {
	
	public static boolean waitForElementToLoad(WebDriver driver, WebElement element) {
		boolean isElementFound = false;
		WebDriverWait wait = new WebDriverWait(driver, waitConstants.WAIT_FOR_ELEMENT_TO_LOAD);
		try {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		isElementFound = true;
		}catch(Exception e)
		{e.printStackTrace();
		}
	 
		return isElementFound;
	}
	
	public static boolean waitForElementToDisappear(WebDriver driver, WebElement element) {
		boolean isElementFound = false;
		WebDriverWait wait = new WebDriverWait(driver, waitConstants.WAIT_FOR_ELEMENT_TO_DISAPPEAR);
		try {
		wait.until(ExpectedConditions.invisibilityOf(element));
		isElementFound = true;
		}catch(Exception e)
		{e.printStackTrace();
		}
	 
		return isElementFound;
	}

	 
 public static String captureScreenshot(WebDriver driver) throws IOException {
	 TakesScreenshot sc =(TakesScreenshot)driver;
	 String sFileNamePrefix = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	 String filePath = System.getProperty("user.dir")+"\\src\\main\\resources\\reports\\"+sFileNamePrefix+"_SFDC.PNG";
	 File src = sc.getScreenshotAs(OutputType.FILE);
	 File dst = new File(filePath);
	 FileUtils.copyFile(src, dst);
	return filePath;
	 
 }

}
