package com.sf.tests;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sf.pages.poForgotPasswordPage;
import com.sf.pages.poHomePage;
import com.sf.pages.poLoginPage;
import com.sf.utilities.TestBase;


public class LoginPageTest extends TestBase {

	
	poLoginPage loginPage;
	poHomePage homePage;
	poForgotPasswordPage forgotPasswordPage;
	Logger log = Logger.getLogger(getClass().getSimpleName());
	
	@BeforeTest
	public void settingUpEnvironment() throws Exception {
		sErrorMessage = "";
		sClassNameForScreenShot = getClass().getSimpleName();
		loginPage = new poLoginPage(driver);
		homePage = new poHomePage(driver);
		forgotPasswordPage = new poForgotPasswordPage(driver);
		
		//driver.get(oCons.getSFURL());
		
		//login.checkLoggedIntoTekAppOrNotElseLogout();
		
	}
	
	@BeforeMethod
	public void settingReqURL() throws Exception {
		driver.get(oCons.getSFURL());
	}
	
	
	@Test
	public void TC01_LoginErrorMessage() throws Exception { 
		loginPage.loginErrorMessage();
		log.info("TC01_LoginErrorMessage() PASSED");
	}
	@Test
	public void TC02_loginToSFDCWithValidCredentials() throws Exception {
		loginPage.loginToSFDC();
		String actualHomePageTitle= oBroUti.PageTitle();
		//Assert.assertEquals(System.getProperty("expectedHomePageTitle"), "Home Page Titles are not matching");
		Assert.assertEquals(actualHomePageTitle, System.getProperty("expectedHomePageTitle"), "Home Page Titles are not matching");
		log.info("TC02_loginToSFDCWithValidCredentials PASSED");
	}
	 
	@Test 
	public void TC4_A_rememberMeChkBox() throws Exception {
		
		loginPage.enterUserName();
		loginPage.enterPassword();
		loginPage.selectRememberMeChkBox();
		loginPage.clickLoginBtn();
		Thread.sleep(2000);
		String actualHomePageTitle = oBroUti.PageTitle();
		Assert.assertEquals(actualHomePageTitle, System.getProperty("expectedHomePageTitle"), "Home Page Titles are not matching");
		homePage.logoutSFDC();
		Thread.sleep(2000);
		String actualLoginPageTitle = oBroUti.PageTitle();
		Assert.assertEquals(actualLoginPageTitle, System.getProperty("expectedLoginPageTitle"), "Home Page Titles are not matching");
		String UnameText = loginPage.verifyUserNameFieldText();
		Assert.assertEquals(UnameText, System.getProperty("td_emailId"), "EmailId in username text box are not matching");
		
		log.info("TC4_A_rememberMeChkBox PASSED");
	}
	
	
	@Test
	public void TC4_B_LoginToSFDCWithInvalidUserNamePassword() throws Exception {
		loginPage.enterIncorrectUserName();
		loginPage.enterIncorrectPassword();
		loginPage.clickLoginBtn();
		String IncorrectUsrNamePwd = loginPage.verifyIncorrectUserNamePasswordMsg();
		Assert.assertEquals(IncorrectUsrNamePwd, System.getProperty("expectedIncorrecrUserNamePasswordMsg"));
		
		log.info("TC4_B_LoginToSFDCWithInvalidUserNamePassword");
	}

}
