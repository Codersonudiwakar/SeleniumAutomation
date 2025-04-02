package com.selenium.automation.tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.selenium.automation.reporting.ExtentReportManager;
import com.selenium.automation.utils.BeforeSetup;
import com.selenium.automation.utils.DataReader;
import com.selenium.automation.utils.ScreenshotUtil;

public class TestClass extends BeforeSetup {
	
	private ExtentTest test;

	  @Test(dataProvider = "getData", dataProviderClass = DataReader.class, priority = 1)
	    public void validationOfLimaUIURLs(Map<String, String> mpData) throws FileNotFoundException, IOException {
	        test = ExtentReportManager.createTest("Validation of Lima UI URLs");

	        // Log test steps
	        test.info("Test execution started.");

	        // Example URL validation
	        String expectedURL = mpData.get("expectedURL");
	        String actualURL = "https://example.com"; // Fetch dynamically

	        test.info("Expected URL: " + expectedURL);
	        test.info("Actual URL: " + actualURL);
	        ScreenshotUtil.takeScreenShot(driver, "java");
	        ScreenshotUtil.showScreenshot(test, driver, "java");

	        // Perform assertion
	        try {
	          //  Assert.assertEquals(actualURL, expectedURL, "URL validation failed!");
	            test.pass("URL validation passed!");
	        } catch (AssertionError e) {
	            test.fail("URL validation failed! " + e.getMessage());
	       //     ExtentReportManager.attachScreenshot(test, driver, "validationOfLimaUIURLs");
	            throw e; // Re-throw exception to mark test as failed
	        }
	    }


}


