package Centric.ShoppingStoreResources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getReportObject() {
		String path = System.getProperty("user.dir")+"\\reports\\myapp.html";
		ExtentSparkReporter report = new ExtentSparkReporter(path);
		report.config().setReportName("Shopping Store Automation Results");
		report.config().setDocumentTitle("Test Results");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Tester", "Centric QA");
		return extent;
	}
	
	

}
