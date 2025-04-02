package com.selenium.automation.utils;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;

import com.aventstack.extentreports.ExtentTest;
import com.selenium.automation.reporting.ExtentReportManager;



public class AfterSetup {
	public WebDriver driver;
    public ExtentTest test;

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void closeReport() {
        ExtentReportManager.flushReports();
    }
}
