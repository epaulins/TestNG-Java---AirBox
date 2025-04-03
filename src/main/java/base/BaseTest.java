package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.appium.java_client.Location;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.PreHomePage;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.net.URI;

public class BaseTest {
    protected AndroidDriver driver;
    protected static ExtentReports extent;
    protected ExtentTest test;

    @BeforeClass
    public void setUp() {
        try {
            // Set Capabilities
            UiAutomator2Options options = new UiAutomator2Options()
                    .setPlatformName("Android")
                    .setDeviceName("emulator-5554")
                    .setAutomationName("UiAutomator2")
                    .setNoReset(true)
                    .setAppPackage("com.google.android.apps.maps")
                    .setAppActivity("com.google.android.maps.MapsActivity");

            URI appiumUri = new URI("http://localhost:4723");

            // Start Appium driver
            driver = new AndroidDriver(appiumUri.toURL(), options);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            // Set the Geolocation
            driver.setLocation(new Location(51.52, -0.18, 10.0));
        } catch (Exception e) {
            throw new RuntimeException("Error during Appium driver setup", e);
        }
    }

    @BeforeMethod
    public void skipPreHomePage() {
        PreHomePage preHomePage = new PreHomePage(driver);
        if(preHomePage.isPageDisplayed()) {
            preHomePage.clickSkip();
        }
    }

    @AfterMethod
    public void resetApp() {
        driver.terminateApp("com.google.android.apps.maps");
        driver.activateApp("com.google.android.apps.maps");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeSuite
    public void setupExtent() {
        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @BeforeMethod
    public void startTest(Method method) {
        test = extent.createTest(method.getName());
    }

    @AfterMethod
    public void logTestResult(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test Passed");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            test.fail("Test Failed: " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.skip("Test Skipped");
        }
    }

    @AfterSuite
    public void tearDownExtent() {
        extent.flush();
    }
}
