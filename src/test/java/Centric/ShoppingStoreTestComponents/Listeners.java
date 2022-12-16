package Centric.ShoppingStoreTestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Centric.ShoppingStoreResources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ExtentTest test;

	//Thread-safe - each object creation has its own thread to not interfere when running parallely
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); 
	
	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); // for a test, it assigns a unique thread ID
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test passed.");		
	}


	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());;
		//test.fail(result.getThrowable());
		//Screenshot
		String filePath = null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}


	@Override
	public void onTestSkipped(ITestResult result) {}
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
	
	@Override
	public void onStart(ITestContext context) {}
	
	@Override
	public void onFinish(ITestContext context) {
		extent.flush();		
	}
	

}
