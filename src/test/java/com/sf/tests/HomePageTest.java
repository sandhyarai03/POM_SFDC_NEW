package com.sf.tests;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import com.sf.pages.poHomePage;
import com.sf.pages.poLoginPage;
import com.sf.utilities.TestBase;

public class HomePageTest extends TestBase { 
	
	poLoginPage loginPage;
	poHomePage homePage;
	
	Logger log = Logger.getLogger(getClass().getSimpleName());
	
	@BeforeTest
	public void settingUpEnvironment() throws Exception {
		sErrorMessage = "";
		sClassNameForScreenShot = getClass().getSimpleName();
		loginPage = new poLoginPage(driver);
		homePage = new poHomePage(driver);
		
	}
	@BeforeMethod
	public void settingReqURL() throws Exception {
		driver.get(oCons.getSFURL());
	}
	
	@Test
	public void TC5_verifyUserMenuDropdown() throws Exception {
		
		loginPage.enterUserName();
		loginPage.enterPassword();
		loginPage.clickLoginBtn();
		Thread.sleep(2000); 
		
		String actualHomePageTitle = oBroUti.PageTitle();
		Assert.assertEquals(actualHomePageTitle, System.getProperty("expectedHomePageTitle"), "Home Page Titles are not matching");
		
		homePage.clickUserMenuDropDown();
		//homePage.userMenuDropDownOptions();
		Thread.sleep(2000);
		homePage.clickUserMenuDropDown();
		homePage.logoutSFDC();
		Thread.sleep(2000);
		String actualLoginPageTitle = oBroUti.PageTitle();
		Assert.assertEquals(actualLoginPageTitle, System.getProperty("expectedLoginPageTitle"), "Home Page Titles are not matching");
		
		log.info("TC5_verifyUserMenuDropdown PASSED");
		
	}
	
	
}
