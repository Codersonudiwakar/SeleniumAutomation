package com.selenium.automation.utils;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.selenium.automation.reporting.ExtentReportManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;	

public class BeforeSetup extends AfterSetup {
    protected Properties properties;

    @BeforeSuite
    public void setupReport() {
        ExtentReportManager.initReports();
    }

    @BeforeMethod
    public void setup() throws IOException {
        properties = new Properties();
        FileInputStream fs = null;
        
        try {
            fs = new FileInputStream("Properties/config.properties");
            properties.load(fs);
        } catch (IOException e) {
            System.out.println("Error loading config.properties: " + e.getMessage());
            throw e;
        } finally {
            if (fs != null) {
                fs.close();
            }
        }

        // Get properties
        String chromeDriverPath = properties.getProperty("chromeDriverPath");
        String testURL = properties.getProperty("url");

        if (chromeDriverPath == null || testURL == null) {
            throw new RuntimeException("Missing required properties: chromeDriverPath or url.");
        }

        // Set ChromeDriver path
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        // Initialize ChromeDriver and WebDriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");

        driver = new ChromeDriver(options); // Assign driver properly
        driver.manage().window().maximize();
        System.out.println("Driver initialized successfully.");

        driver.get(testURL);
        System.out.println("Navigated to: " + testURL);
    }
}
