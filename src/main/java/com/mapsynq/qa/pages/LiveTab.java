package com.mapsynq.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.mapsynq.qa.base.TestBase;
import com.mapsynq.qa.util.TestUtil;

/**********
 * LiveTab.java is the Page Repository class for the Live Tab.
 * The relevant webelements and the methods to access / manipulate them are defined here. 
 *  * 
 * @author Seshadri @date - 01/22/2019
 *
 */

public class LiveTab extends TestBase{
	
	//Elements in Live section
	
	
	//Live Tab Button
	@FindBy(xpath="//a[@class = 'tab_button live_tab sprite tab_active']")
	WebElement liveTabButton;
	
	
	//Incidents Button
	@FindBy(xpath="//h2[text()='Incidents']")
	WebElement incidentsButton;
	
	
	//Cameras Button
	@FindBy(xpath="//h2[text()='Cameras']")
	WebElement camerasButton;
	
	
	//Tolls Button
	@FindBy(xpath="//h2[text()='Tolls']")
	WebElement tollsButton;	
	
	
	//Camera link to be clickable
	@FindBy(xpath="//ul[@id='camera_location_singapore']//li[4]//div//a")
	WebElement camLink;
	
	
	//Toll link to be clickable
	@FindBy(xpath="//ul[@id='erp_locationsingapore']//li[6]//div//a")
	WebElement tollLink;
	
	
	//Pop ups generated after Camera / Toll link click
	@FindBy(xpath="//div[@id='popup']")
	WebElement popUp;
	
	
	//Pop up Title
	@FindBy(xpath="//div[@id='popup']//div[@class='popuptitle']//b")
	WebElement popUpTitle;
	
	
	//Pop up Close Icon
	@FindBy(xpath="//div[@id='popup']//div[@id='popup_close']")
	WebElement popUpClose;
	
	
	//Toll Pop up frame id
	@FindBy(id="myDropdownList")
	WebElement tollFrameId;
	
	
	//Toll Name in frame
	@FindBy(xpath="//html/body/label/b")
	WebElement frameTollName;
	
	
	//Dropdown in Toll Pop up frame
	@FindBy(xpath="//select[@id='select_price_chart']")
	WebElement frameTollDropDownId; 
	
	
	//Selected Time for selected vehicle group in Toll popup frame
	@FindBy(xpath="//div[@id='div_erp_rate']//table[@id='mc_m']//tr[20]//td[@class='time']")
	WebElement frameTollTime; 
	
	
	//Selected Price for selected vehicle group in Toll popup frame
	@FindBy(xpath="//div[@id='div_erp_rate']//table[@id='mc_m']//tr[20]//td[@class='price']")
	WebElement frameTollPrice; 
	
		
	
	//Initializing the Page Objects
	public LiveTab() {
			PageFactory.initElements(driver, this);		
		}
	
	
	/************************************** Navigate to Live Tab ***********************/
	
	public void clickOnlive(){
			
		liveTabButton.click();
	
	}
	
	/************************************** Click on Camera button ***********************/
	
	public void clickOnCamera(){
		
		camerasButton.click();
	}
	
	/************************************** Select specific camera footage ***********************/
	
	public boolean getCameraImage(){
		
		clickOnCamera();
		camLink.click();
		if(popUpTitle.getText().equalsIgnoreCase(TestUtil.Camera_Popup_Title))
			return true;
		else 
			return false;
			
	}
	
	/************************************** Close Camera Popup ***********************/
	
	public boolean closeCameraPopup(){
		
		boolean bool=false;
		if(getCameraImage()){
			popUpClose.click();
		}
		
		try{
			popUp.isDisplayed();
			bool=false;
		}catch(org.openqa.selenium.NoSuchElementException e){
			bool=true;
			
		}
		
		return bool;
	}
	
	/************************************** Click on Tolls button ***********************/	
	
	public void clickOnTolls(){
		
		tollsButton.click();
	}
	
	/************************************** Get Tolls chart for selected Toll ***********************/
	
	public boolean getTollsList(){
		
		clickOnTolls();
		tollLink.click();
		if(popUpTitle.getText().equalsIgnoreCase(TestUtil.Tolls_Popup_Title))
			return true;
		else 
			return false;
		
	}
	
	/************************************** Close Toll popup ***********************/
	
	public boolean closeTollPopup(){
		
		boolean bool=false;
		if(getTollsList()){
			popUpClose.click();
		}
		
		try{
			popUp.isDisplayed();
			bool=false;
		}catch(org.openqa.selenium.NoSuchElementException e){
			bool=true;
			
		}
		
		return bool;
	}
	
	/************************************** Verify Toll Chart for selected vehicle group and time ***********************/
	
	public boolean viewToll(){
		boolean bool=false;
		if(getTollsList()){
			String tollName=tollLink.getText();
			driver.switchTo().frame(tollFrameId);
			String fTollName = frameTollName.getText();
			if(tollName.equalsIgnoreCase(fTollName)){
				Select select = new Select(frameTollDropDownId);
				select.selectByVisibleText(TestUtil.Toll_Dropdown_Value);
			}
			if(TestUtil.Toll_List_Time.equalsIgnoreCase(frameTollTime.getText()) && 
					TestUtil.Toll_List_Value.equalsIgnoreCase(frameTollPrice.getText())){
				bool=true;
				
			}	
					
		}
		
		return bool;
	}
	

}
