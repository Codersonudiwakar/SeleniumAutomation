package com.selenium.automation.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {
    private static ExtentReports extent;
    private static ExtentTest test;
    private static String reportPath = "Reports/ExtentReport.html";

    // Initialize Extent Reports
    public static void initReports() {
        if (extent == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
        }
    }

    // Create a test in the report
    public static ExtentTest createTest(String testName) {
        test = extent.createTest(testName);
        return test;
    }
    // Flush report
    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }
}
