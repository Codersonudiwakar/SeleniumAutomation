package com.selenium.automation.utils;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;

import com.aventstack.extentreports.ExtentTest;
import com.selenium.automation.reporting.ExtentReportManager;



public class AfterSetup {
	protected WebDriver driver; // Declare WebDriver instance

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Driver closed successfully.");
        }
    }

    @AfterSuite
    public void tearDownReport() {
        ExtentReportManager.flushReports();
    }

}
