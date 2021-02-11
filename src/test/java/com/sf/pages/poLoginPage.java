package com.sf.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.sf.utilities.TestBase;

public class poLoginPage extends TestBase  {

	Logger log = Logger.getLogger(getClass().getSimpleName());

	public poLoginPage(WebDriver driver) {
		TestBase.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@id='username']")
	WebElement Username;
	@FindBy(xpath = "//input[@id='password']")
	WebElement Password;
	@FindBy(xpath = "//input[@id='Login']")
	WebElement BtnLogin;
	@FindBy(xpath = "//a[@href='/home/home.jsp']")
	WebElement HomeTab;
	@FindBy(id = "error")
	WebElement EnterPasswordErrorMsg;
	@FindBy(xpath = "//input[@id='rememberUn']")
	WebElement rememberMeChkBox;
	@FindBy(xpath = "//span[contains(text(),'raisandhya2006@gmail.com') and @id='idcard-identity']")
	WebElement userNameFieldText;
	@FindBy(linkText = "Forgot Your Password?")
	WebElement forgotpassword;
	@FindBy(id = "error")
	WebElement incorrectUnamePwdMessage;
	
	
	
	
	public boolean loginErrorMessage() throws Exception {
		boolean bRes_Flag = false;
		oBroUti.waitForElementVisible(driver, Username, 5);
		oBroUti.ufSendKeys(Username, System.getProperty("td_emailId"));
		oBroUti.ufClearField(Password);
		oBroUti.ufClick(BtnLogin);
		oBroUti.waitForElementVisible(driver, EnterPasswordErrorMsg, 5);
		if(oBroUti.isDisplayed(EnterPasswordErrorMsg))
			bRes_Flag=true;
		return bRes_Flag;
	}
	
	
	public void loginToSFDC() throws Exception {
		oBroUti.waitForElementVisible(driver, Username, 5);
		oBroUti.ufSendKeys(Username, System.getProperty("td_emailId"));
		oBroUti.ufSendKeys(Password, System.getProperty("td_password"));
		oBroUti.ufClick(BtnLogin);
		oBroUti.waitForElementVisible(driver, HomeTab, 5);
		
	}
	
	public void selectRememberMeChkBox() throws Exception {
		oBroUti.selectCheckBox(rememberMeChkBox);
	}
	
	public void enterUserName() throws Exception {
		oBroUti.waitForElementVisible(driver, Username, 5);
		oBroUti.ufSendKeys(Username, System.getProperty("td_emailId"));
	}
	public void enterIncorrectUserName() throws Exception {
		oBroUti.waitForElementVisible(driver, Username, 5);
		oBroUti.ufSendKeys(Username, System.getProperty("td_invalid_emailId"));
	}
	public void enterPassword() throws Exception {
		//oBroUti.waitForElementVisible(driver, Username, 5);
		oBroUti.ufSendKeys(Password, System.getProperty("td_password"));
	}
	public void enterIncorrectPassword() throws Exception {
		//oBroUti.waitForElementVisible(driver, Username, 5);
		oBroUti.ufSendKeys(Password, System.getProperty("td_invalid_password"));
	}
	public void clearPassword() throws Exception {
		//oBroUti.waitForElementVisible(driver, Username, 5);
		oBroUti.ufSendKeys(Password, System.getProperty("td_password"));
		oBroUti.ufClearField(Password);
	}
	public void clickLoginBtn() throws Exception {
		oBroUti.ufClick(BtnLogin);
	}
	
	public String  verifyUserNameFieldText() {
		 return oBroUti.getText(userNameFieldText);
	}
	public void clickForgotPassword() throws Exception {
		oBroUti.waitForElementVisible(driver, Username, 5);
		oBroUti.ufClick(forgotpassword);
	}
	public String verifyIncorrectUserNamePasswordMsg() {
		return oBroUti.getText(incorrectUnamePwdMessage);
		
	}
	
	public String actualPageTitle() {
		return oBroUti.PageTitle();	 
	}
}
