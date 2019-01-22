package com.mapsynq.qa.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import com.mapsynq.qa.base.TestBase;
import com.mapsynq.qa.util.TestUtil;

/**********
 * DirectionsTab.java is the Page Repository class for Direction tab.
 * The relevant webelements and the methods to access / manipulate them are defined here. 
 *  * 
 * @author Seshadri @date - 01/22/2019
 *
 */

public class DirectionsTab extends TestBase{

	//Page Factory - Object Repository
		
	//Get Direction Button
	@FindBy (xpath="//input[@id='get_direction']")
	WebElement getDirectionButton;
	
	//From Point text box
	@FindBy(xpath="//input[@id='poi_from']")
	WebElement fromPoint;
	
	
	//To Point Text box
	@FindBy(xpath="//input[@id='poi_to']")
	WebElement toPoint;
	
	
	//Swap From and To Point button
	@FindBy(xpath="//input[@class='sprite route_swap_button']")
	WebElement swapFromToButton;
	
	//Selected option from From Point suggested option
	@FindBy (xpath="//div[contains(@id,'AutocompleteContainter')]/div[contains(@class,'autocomplete-w1')]/div[contains(@id,'Autocomplete')]/div[contains(text(),'Current')]/following-sibling::div[1]")
	WebElement fromPointOption;
	
	//Selected option from To Point suggested option
	@FindBy (xpath="//div[contains(@id,'AutocompleteContainter')]/following-sibling::div[2]/div[contains(@class,'autocomplete')]/div[contains(@id,'Autocomplete')]/div[contains(text(),'Current')]/following-sibling::div[1]")
	WebElement toPointOption;
	
	//Traffic Aware Checkbox
	@FindBy (xpath="//input[@id='also_traffic']")
	WebElement trafficAwareCheckbox;
	
	//Direction Tab Button
	@FindBy(xpath="//a[@class = 'tab_button directions_tab sprite']")
	WebElement directionTabButton;
	
	//Toll Aware Check Box
	@FindBy (xpath="//input[@id='also_erp']")
	WebElement tollAwareCheckbox;
	
	//Fastest CheckBox
	@FindBy (xpath="//input[@id='also_fastest']")
	WebElement fastestCheckbox;
	
	//Shortest Checkbox
	@FindBy (xpath="//input[@id='also_shortest']")
	WebElement shortestCheckbox;
	
	//Clear Route Button
	@FindBy (xpath="//a[@id='btnClear']")
	WebElement clearRouteButton;
	
	//Search Result Division
	@FindBy (xpath="//div[@id='divRouteCommence']")
	WebElement searchResult;
	
	//Current System Time 
	@FindBy (xpath="//span[@id='spanDirTimeStamp']")
	WebElement curTime;
	
	//Select Journey Commence Time Dropdown
	@FindBy (xpath="//select[@id='slJourneyTimeTraffic']")
	WebElement selectTimeDropdown;
	
	
	//Initializing the Page Objects
	public DirectionsTab() {
		PageFactory.initElements(driver, this);		
	}
	
	
	//Actions on Directions section
	
	/************************************** Click on Direction Tab Button***********************/
	public void clickOnDirections(){
		
		directionTabButton.click();
	}
	
	/************************************** Get From Point Textbox value ***********************/
	
	public String getFromPoint(){
		return fromPoint.getAttribute("value");
	}
	
	
	/************************************** Get To Point Textbox value ***********************/
	
	public String getToPoint(){
		return toPoint.getAttribute("value");
	}
	
	/************************************** Enter From Point text and select value from suggested options ***********************/
	
	public void enterFromPoint(String fPoint){
		
		fromPoint.clear();
		fromPoint.sendKeys(fPoint);
		TestUtil.clickOn(driver,fromPointOption,15);
		
	}
	
	/************************************** Enter To Point text and select value from suggested options ***********************/
	
	public void enterToPoint(String tPoint){
		
		toPoint.clear();
		toPoint.sendKeys(tPoint);
		TestUtil.clickOn(driver,toPointOption,15);
	}
	
	/************************************** Select Traffic Aware Checkbox Option ***********************/
	
	public void selectTrafficAwareCheckbox(){
		
		trafficAwareCheckbox.click();
	}
	
	/************************************** Select Toll Aware Checkbox Option ***********************/
	
	public void selectTollAwareCheckbox(){
		
		tollAwareCheckbox.click();
	}
	
	/************************************** Select Fastest Checkbox Option ***********************/
	
	public void selectFastestCheckbox(){
		
		fastestCheckbox.click();
	}
	
	/************************************** Select Shortest Checkbox Option ***********************/
	
	public void selectShortestCheckbox(){
		
		shortestCheckbox.click();
	}
	
	/************************************** Swap From Point and To Point ***********************/
	
	public boolean swapFromToPoint(String fPoint, String tPoint){
		
		clickOnDirections();
		enterFromPoint(fPoint);		
		enterToPoint(tPoint);
		swapFromToButton.click();
		if(fromPoint.getAttribute("value").equalsIgnoreCase(tPoint) && toPoint.getAttribute("value").equalsIgnoreCase(fPoint))
			return true;
		else 
			return false;
		
	}
	
	/************************************** Get Direction based on From Point and To Point ***********************/
	
	public void getDirection(String fPoint, String tPoint){
		
		clickOnDirections();
		enterFromPoint(fPoint);
		enterToPoint(tPoint);
		selectTollAwareCheckbox();
		selectFastestCheckbox();
		selectShortestCheckbox();
		getDirectionButton.click();
	}
	
	/************************************** Verify if Direction Search Result is displayed ***********************/
	
	public boolean searchResultIsVisible(){
		
		TestUtil.presenceOfElement(driver, searchResult, 15);
		return searchResult.isDisplayed();
		
	}
	
	/************************************** Enter From To Point and clear route ***********************/	
	
	public void clearRoute(String fPoint, String tPoint){
		
		clickOnDirections();
		enterFromPoint(fPoint);
		enterToPoint(tPoint);
		clearRouteButton.click();
	}
	
	/************************************** Get the Time of journey commencing at ***********************/
	
	public String journeyCommencingAt(){
		
		TestUtil.presenceOfElement(driver, curTime, 15);
		return curTime.getText();
		
	}
	
	/************************************** Select Leave At Option ***********************/
	
	public boolean leaveAt(String fPoint, String tPoint) {
		
		getDirection(fPoint, tPoint);
		String currTime[] = journeyCommencingAt().split(":");
		
		int hour = Integer.parseInt(currTime[0]);
		
		int minute = Integer.parseInt(currTime[1]);
		
        int leaveAtMinute_I = minute+60;
		int leaveAtHour_I = hour+leaveAtMinute_I/60;
		leaveAtMinute_I = minute%60;
		
		
		TestUtil.elementClickable(driver, selectTimeDropdown, 15);
		Select select = new Select(selectTimeDropdown);
		select.selectByVisibleText(TestUtil.Select_Journey_Time);
		
		if(leaveAtHour_I==24){
		    String leaveAtTime_S = "0"+":"+Integer.toString(leaveAtMinute_I);
		    
		    return(journeyCommencingAt().equalsIgnoreCase(leaveAtTime_S));
		}
		
		
		else if(leaveAtHour_I!=24){
		    String leaveAtTime_S = Integer.toString(leaveAtHour_I)+":"+Integer.toString(leaveAtMinute_I);
		    
		    return(journeyCommencingAt().equalsIgnoreCase(leaveAtTime_S));
		}
		else
			return false;
	}
	
}
