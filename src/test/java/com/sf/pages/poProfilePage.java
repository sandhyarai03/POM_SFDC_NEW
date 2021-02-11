package com.sf.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sf.utilities.TestBase;

public class poProfilePage extends TestBase {
	
	Logger log = Logger.getLogger(getClass().getSimpleName());

	public poProfilePage(WebDriver driver) {
		TestBase.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(xpath="//a[@class='contactInfoLaunch editLink']//img")
	WebElement EditProfilePencilImg;
	@FindBy(xpath="//iframe[@id='contactInfoContentId']")
	WebElement editProfileIframe;
	@FindBy(id="aboutTab")
	WebElement editProfileIframeAboutTab;
	@FindBy(id="lastName")
	WebElement editProfileIframeLastName;
	@FindBy(xpath="//input[@value='Save All']")
	WebElement editProfileIframeSaveAllBtb;
	@FindBy(xpath="//span[contains(text(),'test') and @id='tailBreadcrumbNode']")
	WebElement editedLastName;

	
	public void clickEditProfilePencilImg() throws Exception {
		oBroUti.ufClick(EditProfilePencilImg);
	}
	
	public void switchToEditProfileFrame() throws Exception {
		oBroUti.SwitchFrame("contactInfoContentId");
	}
	public void clickEditProfileIframeAboutTab() throws Exception {
		oBroUti.ufClick(editProfileIframeAboutTab);
	}
	public void enterEditProfileIframeLastName() throws Exception {
		oBroUti.ufSendKeys(editProfileIframeLastName, System.getProperty("editProfileLastName"));
	}
	public void clickEditProfileIframeSaveAllBtb() throws Exception {
		oBroUti.ufClick(editProfileIframeSaveAllBtb);
	}

	public String verifyUpdatedLastName() {
		return oBroUti.getText(editedLastName);
	}

	
}
