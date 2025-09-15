package hooks;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import drivers.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ConfigReader;
import utils.ExtentManager;

public class Hooks {
    public static ExtentReports extent;
    public static ExtentTest test;
    public static WebDriver driver;

    @Before
    public void setUp(Scenario scenario) {
        extent = ExtentManager.getInstance();
        test = extent.createTest(scenario.getName());

        driver = DriverFactory.getDriver();
        String appUrl = ConfigReader.getKey("appURL");
        if (scenario.getSourceTagNames().contains("@Login")) {
            appUrl = ConfigReader.getKey("appURL.login");
        }

        driver.get(appUrl);
        System.out.println("Launching browser before scenario and navigating to: " + appUrl);
    }

    @After
    public void tearDown(Scenario scenario) throws IOException {
        String scenarioName = scenario.getName().replaceAll("[^a-zA-Z0-9_]", "_");

        if (scenario.isFailed()) {
            // Screenshot for Extent report (File)
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenshotPath = System.getProperty("user.dir") + "\\reports\\Screenshots\\" + scenarioName + ".png";
            FileUtils.copyFile(src, new File(screenshotPath));

            // Screenshot for Cucumber report (byte[])
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenarioName);

            test.fail("Scenario failed: " + scenarioName,
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } else {
        	String screenshotPath = System.getProperty("user.dir") + "\\reports\\Screenshots\\" + scenarioName + ".png";
            test.pass("Scenario passed: " + scenarioName,  MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        }	

        DriverFactory.quitDriver();
        System.out.println("Closing browser after scenario...");
        extent.flush();
    }
}
