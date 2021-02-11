package com.sf.pages;



import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sf.utilities.TestBase;

public class poForgotPasswordPage extends TestBase {
	
	Logger log = Logger.getLogger(getClass().getSimpleName());
	
	public poForgotPasswordPage(WebDriver driver) {
		TestBase.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(name = "un")
	WebElement forgotPasswordPageUsernameField;
	@FindBy(name = "continue")
	WebElement forgotPasswordPageContinueBtn;
	@FindBy(xpath = "//p[contains(text(),'We’ve sent you an email with a link to finish rese')]")
	WebElement passwordResetMessage;


public void enterUserName() throws Exception {
	oBroUti.ufSendKeys(forgotPasswordPageUsernameField, System.getProperty("td_emailId"));
   }
public void clickContinueBtn() throws Exception {
	oBroUti.ufClick(forgotPasswordPageContinueBtn);
	}

 public String verifyResetPasswodMessage() {
	 
	 return oBroUti.getText(passwordResetMessage);
 }

}










