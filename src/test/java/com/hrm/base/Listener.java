package com.hrm.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.hrm.utils.CommonMethods;
import com.hrm.utils.Constants;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {
    ExtentReports reports;

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Functionality Testing Started ");

        ExtentSparkReporter reporter = new ExtentSparkReporter(Constants.REPORT_FILEPATH);
        reporter.config().setTheme(Theme.DARK);
        reporter.config().setDocumentTitle("HRM Test Report");
        reporter.config().setReportName("HRM Extent Report");
        reports = new ExtentReports();
        reports.attachReporter(reporter);
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("Functionality Testing Finished: " + iTestContext.getName());

        reports.flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("Test Started: " + iTestResult.getName()); // .getName => @Test method name

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("Test Success: " + iTestResult.getName());

        ExtentTest test = reports.createTest(iTestResult.getName());
        test.pass("Test Case Passed: " + iTestResult.getName());
        test.addScreenCaptureFromPath(CommonMethods.takeScreenshot("PASS/" + iTestResult.getName())); // Selenium 4 with element parameter doesn't work, selenium 3 works.
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("Test Failed: "+ iTestResult.getName());

        ExtentTest test = reports.createTest(iTestResult.getName());
        test.fail("Test Case Failed: " + iTestResult.getName());
        test.addScreenCaptureFromPath(CommonMethods.takeScreenshot("FAIL/" + iTestResult.getName()));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("Test Skipped: " + iTestResult.getName());

        ExtentTest test = reports.createTest(iTestResult.getName());
        test.skip("Test Case Skipped: " + iTestResult.getName());
    }
}
