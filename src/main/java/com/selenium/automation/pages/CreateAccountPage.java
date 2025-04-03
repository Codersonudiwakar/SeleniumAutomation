package com.selenium.automation.pages;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.selenium.automation.utils.Base;
import com.selenium.automation.utils.ScreenshotUtil;

public class CreateAccountPage extends Base {
	public CreateAccountPage(WebDriver driver) {
		super(driver);	}

	private ExtentTest test;

	// String openBtn="Open Account";
	 @FindBy(xpath = "//section[@class='hero']/h1")
	    WebElement welcomeTxt;

	    @FindBy(xpath = "//form[@class='registration-form']/h2")
	    WebElement pInfo;

	    @FindBy(xpath = "(//form[@class='registration-form']/input)[1]")
	    WebElement name;

	    @FindBy(xpath = "//div[@class='auth-links']/a[text()='Open Account']")
	    WebElement openBtn;

	    @FindBy(xpath = "(//form[@class='registration-form']/input)[2]")
	    WebElement birthDate;

	    @FindBy(xpath = "(//form[@class='registration-form']/input)[3]")
	    WebElement fatherName;

	    @FindBy(xpath = "(//form[@class='registration-form']/input)[4]")
	    WebElement aadhaarNo;

	    @FindBy(xpath = "(//form[@class='registration-form']/input)[5]")
	    WebElement panNo;

	    @FindBy(xpath = "(//form[@class='registration-form']/input)[6]")
	    WebElement phoneNo;

	    @FindBy(xpath = "(//form[@class='registration-form']/input)[7]")
	    WebElement email;

	    @FindBy(xpath = "(//form[@class='registration-form']/input)[8]")
	    WebElement password;

	    @FindBy(xpath = "(//form[@class='registration-form']/input)[9]")
	    WebElement homeNo;

	    @FindBy(xpath = "(//form[@class='registration-form']/input)[10]")
	    WebElement villageTown;

	    @FindBy(xpath = "(//form[@class='registration-form']/input)[11]")
	    WebElement city;

	    @FindBy(xpath = "(//form[@class='registration-form']/input)[12]")
	    WebElement state;

	    @FindBy(xpath = "(//form[@class='registration-form']/input)[13]")
	    WebElement zipCode;

	    @FindBy(xpath = "//button[text()='Register']")
	    WebElement regBtn;
//	WebElement openBtn=driver.findElement(By.xpath(""));

	public void validateHome() throws FileNotFoundException, IOException {
	String welcomeText=	welcomeTxt.getText();
//	test.info("This is Text From Home :"+welcomeText);
	Assert.assertEquals(welcomeText, "Welcome to Bank of City");
//	test.info("Home Page Validate");
	ScreenshotUtil.takeScreenShot(driver, welcomeText);
	ScreenshotUtil.showScreenshot(test, driver, welcomeText);
	}

	public void openButton() {
		boolean a=openBtn.isDisplayed();
		if (a==true) {
			openBtn.click();
		//	test.info("Clicked on Open Button");	
		}
	}

	public void fillRegistrationForm() throws FileNotFoundException, IOException {
		if (pInfo.isDisplayed()) {
			name.sendKeys("Sonu Kumar");
			birthDate.sendKeys("20/05/2010");
			fatherName.sendKeys("Mr Diwakar");
			aadhaarNo.sendKeys("659465980989");
			panNo.sendKeys("HRBTS4576G");
			phoneNo.sendKeys("9568548835");
			email.sendKeys("contactSonu@gmail.com");
			password.sendKeys("12345");
			homeNo.sendKeys("T5");
			villageTown.sendKeys("Rampur");
			city.sendKeys("Bangalore");
			state.sendKeys("Karnatka");
			zipCode.sendKeys("560066");
			ScreenshotUtil.takeScreenShot(driver, "Data Filed");
			ScreenshotUtil.showScreenshot(test, driver, "Data Filed");
			
		}

	}

	public void clickRegistationButton() throws FileNotFoundException, IOException {
		if (regBtn.isDisplayed()) {
			regBtn.click();
			ScreenshotUtil.takeScreenShot(driver, "Clicked Reg Button");
			ScreenshotUtil.showScreenshot(test, driver, "Clicked Reg Button");
		}

	}

}
