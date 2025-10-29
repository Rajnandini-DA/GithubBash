package com.comcast.crm.generic.baseClassutility;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcost.crm.generic.webdriverutility.UtilityClassObject;

public class ListenerImp implements ITestListener, ISuiteListener{
	public static ExtentReports report;
	public static ExtentTest test;
	
	
	@Override
	public void onStart(ISuite suite) {
		System.out.println("Report config");
		//spark report config
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReports/report.html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM report");
		spark.config().setTheme(Theme.DARK);
		
		//Add environment info
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS","Windows-10");
		report.setSystemInfo("Browser","Chrome");
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Report backup");
		report.flush();
		
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("===>"+result.getMethod().getMethodName()+">===START====");
		test = report.createTest(result.getMethod().getMethodName());
		test.log(Status.INFO,result.getMethod().getMethodName()+"===>Started<====");
		UtilityClassObject.setTest(test);
		test = report.createTest("CreateContactTest");
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("===>"+result.getMethod().getMethodName()+">===END====");
		test.log(Status.PASS,result.getMethod().getMethodName()+"===>Completed<====");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		String time = new Date().toString().replace(" ", "_").replace(":","_");
		TakesScreenshot eDriver = (TakesScreenshot)BaseClass.sdriver;
		String filepath= eDriver.getScreenshotAs(OutputType.BASE64);
		
		test.addScreenCaptureFromBase64String(filepath, testName+"_"+time);
		test.log(Status.FAIL,result.getMethod().getMethodName()+"===>Failed<====");
		
	}

	
	
	
	

}