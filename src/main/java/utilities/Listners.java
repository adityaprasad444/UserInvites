package utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Listners implements ITestListener {

	ExtentReports reports;
	ExtentTest test;
	
	@Override
	public void onStart(ITestContext context) {
		reports = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("Report.html");
		reports.attachReporter(spark);
		reports.setSystemInfo("OS", "Windows 10 pro");
		reports.setSystemInfo("Environment", "Test (QA)");
		reports.setSystemInfo("CreatedBY", "Aditya Prasad Y");

	}
	@Override
	public void onFinish(ITestContext context) {
		reports.flush();
	}
	
	
	@Override
	public void onTestStart(ITestResult result) {
		
		test = reports.createTest(result.getMethod().getMethodName());
		test.log(Status.INFO, result.getMethod().getMethodName() + "Test is started");
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, result.getMethod().getMethodName() + "Test is passed");
		
	}
	@Override
	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL, result.getMethod().getMethodName() + "Test is failed");
		
	}
	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, result.getMethod().getMethodName() + "Test is Skipped");
	}
	
}
