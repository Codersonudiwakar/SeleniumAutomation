package com.selenium.automation.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ScreenshotUtil {

	public static void takeScreenShot(WebDriver driver, String name) throws FileNotFoundException, IOException {
		String path = "Screenshots/" + name + ".jpg";
		TakesScreenshot t = (TakesScreenshot) driver;
		File srcFile = t.getScreenshotAs(OutputType.FILE);
		File destFile = new File(path);
		FileUtils.copyFile(srcFile, destFile);
	}

	public static void showScreenshot(ExtentTest test, WebDriver driver, String testName) {
		String workingDir = System.getProperty("user.dir");
		String path = workingDir + File.separator + "Screenshots" + File.separator + testName + ".jpg";

		test.addScreenCaptureFromPath(path);
	}

}
