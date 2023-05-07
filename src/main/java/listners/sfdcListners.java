package listners;


import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import file.utils.CommonUtils;
import tests.baseTest;



public class sfdcListners  extends baseTest implements ITestListener{

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		 
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		baseTest.test.pass(MarkupHelper.createLabel(result.getName()+" :Passed",ExtentColor.GREEN));
		logger.debug("sfdcListners : onTestSuccess  : Test passed"+result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		try {
			String screenshotPath = CommonUtils.captureScreenshot(getdriver());
			logger.debug("sfdcListners : onTestFailure Screensht captured--"+result.getName());
			baseTest.test.addScreenCaptureFromPath(screenshotPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		baseTest.test.fail(MarkupHelper.createLabel(result.getName()+" Failed", ExtentColor.RED));
		logger.debug("sfdcListners : onTestFailure  : Test failed"+result.getName());
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	

	
}
