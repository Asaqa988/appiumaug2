package appiumTestcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class myTestCases {
	DesiredCapabilities caps = new DesiredCapabilities();

	AndroidDriver driver;

	@BeforeTest
	public void myBeforeTest() {
		// always day2man bn3mlhom

		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "aug");

		// we have two types of testing one on the browser , 2- on app

		// 1- on browser ( always when you need to use the browser on the mobile )

//		caps.setCapability("chromedriverExecutable", "D:\\chrome\\chromedriver.exe");
//		caps.setCapability(MobileCapabilityType.BROWSER_NAME, "chrome");

		// 2- when you need to test application

		File myApp = new File("src/myApplications/calculator.apk");
		caps.setCapability(MobileCapabilityType.APP, myApp.getAbsolutePath());
	}

	@Test(enabled = false)
	public void myTest() throws MalformedURLException {

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
		driver.get("https://www.saucedemo.com/");

		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();

		List<WebElement> myAddtoCartButtons = driver.findElements(By.className("btn"));

		for (int i = 0; i < myAddtoCartButtons.size(); i++) {
			myAddtoCartButtons.get(i).click();
		}

	}

	@Test(enabled = false)
	public void multiplyFunction() throws MalformedURLException {
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);

		// simple operation to do multiply 5* 9

		driver.findElement(By.id("com.google.android.calculator:id/digit_5")).click();
		driver.findElement(By.id("com.google.android.calculator:id/op_mul")).click();

		driver.findElement(By.id("com.google.android.calculator:id/digit_9")).click();
		String result = driver.findElement(By.id("com.google.android.calculator:id/result_preview")).getText();

		Assert.assertEquals(result, "45");
	}

	@Test(enabled = false)
	public void ClickOnAllnumbers() throws MalformedURLException {
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);

		List<WebElement> allButtons = driver.findElements(By.className("android.widget.ImageButton"));

		for (int i = 0; i < allButtons.size(); i++) {

			if (allButtons.get(i).getAttribute("resource-id").contains("digit")) {
				allButtons.get(i).click();
			} else {
				continue;
			}
		}

	}

	@Test()
	public void clickonevenNumbers() throws MalformedURLException {
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);

		List<WebElement> allButtons = driver.findElements(By.className("android.widget.ImageButton"));

		for (int i = 0; i < allButtons.size(); i++) {

//			if (allButtons.get(i).getAttribute("resource-id").contains("digit_2")
//					|| allButtons.get(i).getAttribute("resource-id").contains("digit_4")
//					|| allButtons.get(i).getAttribute("resource-id").contains("digit_6")
//					|| allButtons.get(i).getAttribute("resource-id").contains("digit_8")
//					|| allButtons.get(i).getAttribute("resource-id").contains("digit_0")) {
//				allButtons.get(i).click();
//			} else {
//				continue;
//			}

			if (allButtons.get(i).getAttribute("resource-id").contains("digit")) {

				int number = Integer.parseInt(allButtons.get(i).getAttribute("content-desc"));

				if (number % 2 == 0) {
					System.out.println(number);
					allButtons.get(i).click(); 
				}
			}

		}

	}

	@AfterTest

	public void myPostTesting() {
	}

}
