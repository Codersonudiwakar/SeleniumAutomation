package com.selenium.automation.tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.selenium.automation.pages.CreateAccountPage;
import com.selenium.automation.reporting.ExtentReportManager;
import com.selenium.automation.utils.BeforeSetup;
import com.selenium.automation.utils.DataReader;
import com.selenium.automation.utils.ScreenshotUtil;

public class TestClass extends BeforeSetup {
	
	private ExtentTest test;

	  @Test(dataProvider = "getData", dataProviderClass = DataReader.class, priority = 1)
	    public void SampleTestCaseFirst(Map<String, String> mpData) throws FileNotFoundException, IOException {
		    System.out.println("Driver instance in TestClass: " + driver);

		  if (driver == null) {
	            throw new RuntimeException("WebDriver is not initialized! Check setup.");
	        }
	        test = ExtentReportManager.createTest("Validation of Lima UI URLs");
	        CreateAccountPage ac=new CreateAccountPage(driver);
	        ac.validateHome();
//	        ac.openButton();

	    }


}


