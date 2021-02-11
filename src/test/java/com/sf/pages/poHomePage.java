package com.sf.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



import com.sf.utilities.TestBase;

public class poHomePage extends TestBase {
	
	public String[] expMenuItems = {"My Profile","My Settings","Developer Console","Switch to Lightning Experience","Logout"}; 
	Logger log = Logger.getLogger(getClass().getSimpleName());
	
	public poHomePage(WebDriver driver){
		TestBase.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[contains(text(),'My Profile')]")
	WebElement myProfileOption;
	@FindBy(xpath = "//input[@id='username']")
	WebElement Username;
	@FindBy(xpath = "//a[@href='/home/home.jsp']")
	WebElement HomeTab;
	@FindBy(id="userNavLabel")
	WebElement userMenuDropDown;
	@FindBy(xpath = "//a[.='Logout']")
	WebElement Logout;
	@FindBy(xpath="//*[@id=\"userNav-menuItems\"]/a")
	WebElement userNavMenuItems;
	
	
	
	public void clickProfile() throws Exception {
		oBroUti.ufClick(myProfileOption);	
	}
		
	public void logoutSFDC() throws Exception {
		//oBroUti.waitForElementVisible(driver, HomeTab, 5);
		oBroUti.ufClick(userMenuDropDown);
		oBroUti.ufClick(Logout);
		
	}
	public void clickUserMenuDropDown() throws Exception {
		//oBroUti.waitForElementVisible(driver, HomeTab, 5);
		oBroUti.ufClick(userMenuDropDown);
	}

	/*public void clickProfile() {
		try {
			oBroUti.ufClick(myProfileOption);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}*/
	
	
	
	/*public void userMenuDropDownOptions() {
			List<WebElement> menu =driver.findElements(By.xpath("//*[@id=\"userNav-menuItems\"]/a"));
			System.out.println(menu.size());
			int index = 0;
			for (WebElement dropdown : menu) {
			    //System.out.println(dropdown.getText());
			   // System.out.println(expected [index]);
			    if (dropdown.getText().equalsIgnoreCase(expMenuItems [index])) {
			    	log.info(expMenuItems [index] + " found");
			    	System.out.println("Inside loop " + dropdown.getText());
				}else 
				{
					log.info(expMenuItems[index] + " NOT found");
			    } 
			    index++;
			}		
		}*/
  
	}                     
	
