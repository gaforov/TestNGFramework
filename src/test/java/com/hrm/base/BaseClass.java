package com.hrm.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.hrm.utils.ConfigsReader;
import com.hrm.utils.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.time.Duration;
import static com.hrm.base.PageInitializer.*;

public class BaseClass {
    public static WebDriver driver;
    ExtentReports reports;

    // This entire generateReport was moved to Listener class inside onFinish() method. That way BaseClass will stay cleaner and do what it is meant to do only.
//    @BeforeTest(alwaysRun = true)
//    public void generateReport() {
//        System.out.println("BEFORE TEST RUNNING"); // just to see if this method running
//        ExtentSparkReporter reporter = new ExtentSparkReporter(Constants.REPORT_FILEPATH);
//        reporter.config().setTheme(Theme.DARK);
//        reporter.config().setDocumentTitle("HRM Test Report");
//        reporter.config().setReportName("HRM Extent Report");
//        reports = new ExtentReports();
//        reports.attachReporter(reporter);
//        ExtentTest test = reports.createTest("testLoginAdminUser");
//        reports.flush();
//    }

    // No need for this method, you can implement flush right inside generaReport() and will do the job. Also, you can move entire report to listener onFinish() as well.
//    @AfterTest(alwaysRun = true)
//    public void writeReport() {
//        reports.flush();
//    }

    @BeforeMethod(alwaysRun = true)
    public static WebDriver setUp() {
        ConfigsReader.readProperties(Constants.CONFIGURATION_FILEPATH);

        switch (ConfigsReader.getProperty("browser").toLowerCase()) {
            case "chrome" -> {
                System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_PATH);
                driver = new ChromeDriver();
            }
            case "firefox" -> {
                System.setProperty("webdriver.gecko.driver", Constants.GECKO_DRIVER_PATH);
                driver = new FirefoxDriver();
            }
            default -> throw new RuntimeException("Browser is not supported");
        }

        driver.manage().window().fullscreen();
//        driver.manage().window().maximize();
        driver.get(ConfigsReader.getProperty("url"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICIT_WAIT_TIME));  // wait time can be changes in Constants class

        initialize(); // <-- initialize all page objects as part of setup to reuse their methods without creating their objects again everytime.
        return driver;
    }

    @AfterMethod(alwaysRun = true)
    public static void tearDown() {
        if (driver != null) {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.quit();
        }
    }

}
