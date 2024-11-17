package listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.ProjBase;
import utilities.ExtentReporter;

public class Listeners extends ProjBase implements ITestListener{
	
	public WebDriver driver;
	
	ExtentReports extentReport = ExtentReporter.getExtentReport();
	ExtentTest extentTest;
	
	public void onTestStart(ITestResult result) {
		String testName = result.getName();
		extentTest = extentReport.createTest(testName + "Execution started");
	}
	public void onTestSuccess(ITestResult result) {
		String testName = result.getName();
		extentTest.log(Status.PASS, testName + "Test Got Passed");
	}
	public void onTestFailure(ITestResult result) {
		
		String testName = result.getName();
		extentTest.fail(result.getThrowable());
		
		try {
		
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			String screenshotFilePath = takeScreenshot(testName,driver);
			extentTest.addScreenCaptureFromPath(screenshotFilePath, testName);
			
		}catch(IOException e) {
			
		}
	}
	public void onStart(ITestContext context) {
		
	}
	public void onFinish(ITestContext context) {
		extentReport.flush();
	}
}
