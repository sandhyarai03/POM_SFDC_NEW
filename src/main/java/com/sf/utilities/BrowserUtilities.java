package com.sf.utilities;

import static org.testng.Assert.assertTrue;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.*;
import com.google.common.base.Function;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.imageio.ImageIO;

public class BrowserUtilities {
	
	Logger log = Logger.getLogger(getClass().getSimpleName());
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;
	//public String[] expected = {"My Profile","My Settings","Developer Console","Switch to Lightning Experience","Logout"}; 

	@SuppressWarnings("deprecation")
	@Parameters("browserType")
	// @BeforeSuite
	public void launchBrowser(String browser) throws Exception {
		log.info("Launching Browser");

		// Check if parameter passed from TestNG is 'firefox'/chrome
		if (browser.toLowerCase().startsWith("ch")) {

			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--disable-notifications");
			TestBase.driver = new ChromeDriver(chromeOptions);
		} else if (browser.equalsIgnoreCase("ff") || browser.toLowerCase().startsWith("fi")) {
			// create firefox instance
			WebDriverManager.firefoxdriver().setup();
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("permissions.default.desktop-notification", 1);
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability(FirefoxDriver.PROFILE, profile);
			TestBase.driver = new FirefoxDriver(capabilities);
		} else if (browser.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			TestBase.driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			TestBase.driver = new EdgeDriver();
		} else {
			// If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
	
		TestBase.driver.manage().window().maximize();
		TestBase.driver.manage().deleteAllCookies();
		TestBase.driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		TestBase.driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);

	}

	@SuppressWarnings("deprecation")
	public boolean waitForElementVisible(WebDriver driver, final WebElement ele, int iTimeInSeconds) throws Exception {

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(iTimeInSeconds, TimeUnit.SECONDS)
				.pollingEvery(500, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);

		wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				if (ele.isDisplayed()) {
					log.info("Element Displayed : " + ele);
					return ele;
				} else {
					log.info("******Element NOT Displayed : " + ele);
					return null;
				}
			}
		});

		return ele.isDisplayed();
	}

	public boolean waitForElementDisable(WebDriver driver, final WebElement ele, int iTimeInSeconds) {

		@SuppressWarnings("deprecation")
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(iTimeInSeconds, TimeUnit.SECONDS)
				.pollingEvery(50, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);

		wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				if (!ele.isDisplayed()) {
					log.info("Element Not Displayed : " + ele);
					return ele;
				} else {
					log.info("******Element is Still Displaying : " + ele);
					return null;
				}
			}
		});

		return !ele.isDisplayed();
	}

	public boolean isDisplayed(WebElement ele) throws Exception {
		boolean bRes_flag = false;
		try {
			if (ele.isDisplayed()) {
				log.info("Displayed " + ele);
				bRes_flag = true;
			}
		} catch (Exception ea) {
			bRes_flag = false;
		}
		return bRes_flag;
	}

	public void screenShotBrowser(WebDriver driver, String className) throws Exception {
		String destDir = "screenshots";
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		File scrFile = scrShot.getScreenshotAs(OutputType.FILE);
		String destFile = className + ".png";

		try {
			FileUtils.copyFile(scrFile,
					new File(System.getProperty("user.dir") + "/test-output/" + destDir + "/" + destFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void screenShotBrowserForWebElement(WebDriver driver, WebElement eleScreenArea, String className)
			throws Exception {
		String destDir = "screenshots";
		String destFile = className + ".png";
		log.info("ScreenShot");
		// Screenshot fpScreenshot = new
		// AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		Screenshot fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
				.takeScreenshot(driver, eleScreenArea);
		// Screenshot fpScreenshot = new AShot().takeScreenshot(driver, eleScreenArea);

		try {
			ImageIO.write(fpScreenshot.getImage(), "PNG",
					new File(System.getProperty("user.dir") + "/target/surefire-reports/" + destDir + "/" + destFile));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void isDisplayed(WebElement ele, String str) throws Exception {

		assertTrue(ele.isDisplayed(), "Actual:" + str + " is displayed. Expected:" + str + " is not displayed");
		log.info("Actual:" + str + " Expected:" + str + " ");
		assertTrue(ele.isEnabled(), "Actual:" + str + " is Enabled. Expected:" + str + " is not Enabled");

	}

	public boolean isAttribtuePresent(WebElement element, String attribute) {
		Boolean result = false;
		try {
			String value = element.getAttribute(attribute);
			if (value != null) {
				result = true;
			}
		} catch (Exception e) {
		}

		return result;
	}

	public boolean ScrollToView(WebDriver driver, WebElement ele) throws Exception {
		boolean bRes_Flag = false;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", ele);
		bRes_Flag = true;
		return bRes_Flag;
	}

	public void ClickUsingJS(WebElement ele) throws Exception {
		JavascriptExecutor executor = (JavascriptExecutor) TestBase.driver;
		executor.executeScript("arguments[0].click();", ele);
	}

	public void ufClick(WebElement ele) throws Exception {
		ele.click();
	}

	public void ufSendKeys(WebElement ele, String keysToSend) throws Exception {
		ele.sendKeys(keysToSend);
	}
	
	public void ufClearField(WebElement ele) throws Exception {
		ele.clear();
	}

	public String ufGetText(WebElement ele) throws Exception {
		return ele.getText();
	}
	

	public boolean waitForElementVisible_old(WebDriver driver, final WebElement ele, int iTimeInSeconds) throws Exception {
		boolean bRes_flag = false;

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).pollingEvery(Duration.ofMillis(500))
				.withTimeout(Duration.ofSeconds(iTimeInSeconds)).ignoring(NoSuchElementException.class);

		Function<WebDriver, WebElement> function = new Function<WebDriver, WebElement>() {
			float iSecCount = 0;

			public WebElement apply(WebDriver arg0) {
				boolean bFlag = ele.isDisplayed();
				if (!bFlag) {
					try {
						log.info("***Failed at intial check.. hence handling with hard wait 1 Second..." + ele);
						Thread.sleep(1000);
					} catch (Exception e) {
					}
					bFlag = ele.isDisplayed();
				}
				iSecCount++;
				if (bFlag) {
					log.info("Took: " + iSecCount * 500 / 1000 + " Seconds to find Element: " + ele);
				}
				return ele;

			}
		};
		wait.until(function);
		return bRes_flag;

	}

	public boolean waitForElementDisable_old(WebDriver driver, final WebElement ele, int iTimeInSeconds) {
		boolean bRes_flag = false;

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(iTimeInSeconds))
				.pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class);

		Function<WebDriver, WebElement> function = new Function<WebDriver, WebElement>() {
			float iSecCount = 0;

			public WebElement apply(WebDriver arg0) {
				boolean bFlag = !ele.isDisplayed();
				iSecCount++;
				if (bFlag) {
					log.info("Took: " + iSecCount * 500 / 1000 + " Seconds to Disable Element: " + ele);
				}
				return ele;
			}
		};
		wait.until(function);
		return bRes_flag;

	}
	public void SwitchFrame(String id) {
		TestBase.driver.switchTo().frame(id);
		System.out.println("Pass: we can switch to the " + id + " frame");
	}
	
	public void SwitchFrame_AbsuPath(String absuPath) {
		TestBase.driver.switchTo().frame(absuPath);
		System.out.println("Pass: we can switch to the " + absuPath + " frame");
	}
	
	public void SwitchFrame(WebElement element) {
		TestBase.driver.switchTo().frame(element);
		System.out.println("Pass: we can switch to the frame");
	}
	public void SwitchFrame(int index) {
		TestBase.driver.switchTo().frame(index);
		System.out.println("Pass: we can switch to the frame");
	}

	public void SwitchFrame() {
		TestBase.driver.switchTo().defaultContent();
		System.out.println("Pass: we can switch to the frame");
	}

	public String PageTitle() {
		return TestBase.driver.getTitle();
	}
	
	public void selectCheckBox(WebElement ele) {
		boolean isSelected = ele.isSelected();
		 
		//performing click operation if element is not checked
		if(isSelected == false) { 
		 ele.click();
		}
	}
	public String getText(WebElement ele) {
		 return ele.getText(); 
	}

}