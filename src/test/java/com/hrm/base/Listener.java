package com.hrm.base;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.hrm.utils.Constants;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {
    ExtentReports reports;

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Functionality Testing Started ");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("Functionality Testing Finished " + iTestContext.getName());
        ExtentSparkReporter reporter = new ExtentSparkReporter(Constants.REPORT_FILEPATH);
        reporter.config().setTheme(Theme.DARK);
        reporter.config().setDocumentTitle("HRM Test Report");
        reporter.config().setReportName("HRM Extent Report");
        reports = new ExtentReports();
        reports.attachReporter(reporter);
        ExtentTest test = reports.createTest("testLoginAdminUser");
        reports.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Started: " + result.getName()); // .getName => method name
        ITestListener.super.onTestStart(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Success: " + result.getName());
        ITestListener.super.onTestSuccess(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed: "+ result.getName());
        ITestListener.super.onTestFailure(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped: " + result.getName());
        ITestListener.super.onTestSkipped(result);
    }
}
