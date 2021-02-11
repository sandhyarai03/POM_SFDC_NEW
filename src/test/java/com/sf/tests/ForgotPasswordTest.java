package com.sf.tests;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sf.pages.poForgotPasswordPage;
import com.sf.pages.poHomePage;
import com.sf.pages.poLoginPage;
import com.sf.utilities.TestBase;

public class ForgotPasswordTest extends TestBase {
	
	poLoginPage loginPage;
	poHomePage homePage;
	poForgotPasswordPage forgotPasswordPage;
	
	Logger log = Logger.getLogger(getClass().getSimpleName());
	
	@BeforeTest
	public void settingUpEnvironment() {
	sErrorMessage = "";
	sClassNameForScreenShot = getClass().getSimpleName();
	loginPage = new poLoginPage(driver);
	homePage = new poHomePage(driver);
	forgotPasswordPage = new poForgotPasswordPage(driver);
	
}
	
	@BeforeMethod
	public void settingReqURL() throws Exception {
		driver.get(oCons.getSFURL());
	}
	
	@Test(enabled=false)
	public void TC4_test_ForgotPassword() throws Exception
	{
		loginPage.clickForgotPassword();
		forgotPasswordPage.enterUserName();
		forgotPasswordPage.clickContinueBtn();
		String forgotPwdResetMessage = forgotPasswordPage.verifyResetPasswodMessage();
		Assert.assertEquals(forgotPwdResetMessage, System.getProperty("expectedResetPasswordMsg"), "ResetPassword message are not matching");
	}	
	

}
