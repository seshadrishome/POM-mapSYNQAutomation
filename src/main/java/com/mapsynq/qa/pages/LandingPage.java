package com.mapsynq.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mapsynq.qa.base.TestBase;
import com.mapsynq.qa.util.TestUtil;

/**********
 * LandingPage.java is the Page Repository class for the Landing Page of mapSYNQ.com.
 * The relevant webelements and the methods to access / manipulate them are defined here. 
 *  * 
 * @author Seshadri @date - 01/22/2019
 *
 */

public class LandingPage extends TestBase{
	
	//Page Factory - Object Repository
	
	
	//mapSYNQ Logo
	@FindBy(xpath="//a[@class='header_logo sprite']")
	WebElement mapSynqLogo;
	
	//From Point on Direction tab
	@FindBy(xpath="//input[@id='poi_from']")
	WebElement fromPoint;
	
	//To Point on Direction tab
	@FindBy(xpath="//input[@id='poi_to']")
	WebElement toPoint;
	
	//Search box at the top
	@FindBy (xpath="//input[@id = 'txtGlobalSearch']")
	WebElement searchBox;
	
	//Search Button Icon 
	@FindBy (xpath="//span[@class='search_icon sprite']")
	WebElement searchButton;

	//Search Suggestions after entering text in Search box
	@FindBy (xpath="//div[contains(@id,'AutocompleteContainter')]/following-sibling::ul/li[2]")
	WebElement searchSuggestion;
	
	//Search Result Display Area
	@FindBy (xpath="//div[@class='placeResult']")
	WebElement searchResult;
	
	
	//The link from search result which is to be selected
	@FindBy (xpath="//div[@class='placeResult']//div[@class='placeResultRow'][2]//a")
	WebElement searchResultToBeSelected;
	
	
	//The area which gets popped up, once clicked on the Search Result Link
	@FindBy (xpath="//div[@id='popup']")
	WebElement pointOfInterest;
	
	
	//The dropdown icon displays beside each search result links
	@FindBy (xpath="//div[@class='placeResultRow'][2]//tr[1]//td[3]//span[@class = 'jquery-icon icon-triangle-down']")
	WebElement optionIcon;
	
	
	//The first value of the drop down - To Here
	@FindBy (xpath="//ul[@class = 'context-menu-list  context-menu-root'][1]//span[text()='To Here'][1]")
	WebElement toHere;
	
	//The second value of the drop down - From Here
	@FindBy (xpath="//ul[@class = 'context-menu-list  context-menu-root'][1]//span[text()='From Here'][1]")
	WebElement fromHere;
	
	//To Here link on Point of Interest Popup
	@FindBy(xpath="//div[@id='popup']//a[text()='To Here']")
	WebElement toPointPOI;
	
	
	//From Here link on Point of Interest Popup
	@FindBy(xpath="//div[@id='popup']//a[text()='From Here']")
	WebElement fromPointPOI;
	
	
	//Point of Interest Popup close icon
	@FindBy(xpath="//div[@id='popup']//div[@id='popup_close']")
	WebElement poiPopUpClose;
	
	
	//Initializing the Page Objects
		public LandingPage() {
			PageFactory.initElements(driver, this);		
		}
		
		
	//Actions
		
		/*************************************Validate Landing Page Title***************************/
		
		
		public String validateLandingPageTitle(WebDriver driver){
			return driver.getTitle();
		}
		
		/********************************Validate Website Logo**********************************/
		
		
		public boolean validatemapSYNQLogo(){
			
			return mapSynqLogo.isDisplayed();
			
		}
		
		/********************************Select the suggested options once user enters text in search box********************************/
		
		
		public void searchLocationFromSuggestedOption(String loc){
			searchBox.clear();
			searchBox.sendKeys(loc);
			TestUtil.elementClickable(driver,searchSuggestion,10);
			searchSuggestion.click();
		}
		
		/********************************click on the search icon to get the search results********************************/
		
		public void searchLocationBySearchButton(String loc){
			searchBox.clear();
			searchBox.sendKeys(loc);
			searchButton.click();
			
		}
		
		/********************************Checking whether Search Result section is displayed or not********************************/
		
		public boolean searchResultIsVisible(){
			
			TestUtil.presenceOfElement(driver, searchResult, 10);
			return searchResult.isDisplayed();
			
		}
		
		/********************************click on the search result link - Point of Interest displayed********************************/
		
		public boolean selectLocation(String loc){
			searchBox.clear();
			searchBox.sendKeys(loc);
			searchButton.click();
			if(searchResultIsVisible())
				searchResultToBeSelected.click();
			TestUtil.presenceOfElement(driver,pointOfInterest,10);
			return pointOfInterest.isDisplayed();
		}
		
		
		/***************************Select From Here option on the Point of Interest pop up*****************************/
		
		public boolean selectFromHerePOI(String loc){
			
			Boolean bool=false;
			
			if(selectLocation(loc)){
				String fromPoint_S = searchResultToBeSelected.getText();
				fromPointPOI.click();
				TestUtil.presenceOfElement(driver, fromPoint, 10);
				String fromPoint_D = fromPoint.getAttribute("value");
				if(fromPoint_D.equalsIgnoreCase(fromPoint_S))
					bool=true;
			}
			return bool;
		}
		
		/***************************Select To Here option on the Point of Interest pop up*****************************/
		
		public boolean selectToHerePOI(String loc){
			
			Boolean bool=false;
			
			if(selectLocation(loc)){
				String toPoint_S = searchResultToBeSelected.getText();
				toPointPOI.click();
				TestUtil.presenceOfElement(driver, toPoint, 10);
				String toPoint_D = toPoint.getAttribute("value");
				if(toPoint_D.equalsIgnoreCase(toPoint_S))
					bool=true;
			}
			return bool;
		}
		
		
		
		
		/***************************Close Point of Interest pop up*****************************/
		
		public boolean closePOIPopup(String loc){
			
			boolean bool=false;
			if(selectLocation(loc)){
				poiPopUpClose.click();
			}
			
			try{
				pointOfInterest.isDisplayed();
				bool=false;
			}catch(org.openqa.selenium.NoSuchElementException e){
				bool=true;
				
			}
			
			return bool;
		}
		
		
		/***************************Select To Here option from the drop down of search result link*****************************/
		
		public boolean selectToHere(String loc){
			boolean bool = false;
			searchBox.clear();
			searchBox.sendKeys(loc);
			searchButton.click();
			if(searchResultIsVisible()){
				String toPoint_S = searchResultToBeSelected.getText();
				optionIcon.click();
				TestUtil.presenceOfElement(driver, toHere, 10);
				toHere.click();
				TestUtil.presenceOfElement(driver, toPoint, 10);
				String toPoint_D = toPoint.getAttribute("value");
				
				if(toPoint_D.equalsIgnoreCase(toPoint_S))
					bool=true;
				
			}
			
			return bool;
		}
		
		/***************************Select From Here option from the drop down of search result link*****************************/
		
		public boolean selectFromHere(String loc){
			boolean bool = false;
			searchBox.clear();
			searchBox.sendKeys(loc);
			searchButton.click();
			if(searchResultIsVisible()){
				String fromPoint_S = searchResultToBeSelected.getText();
				optionIcon.click();
				TestUtil.presenceOfElement(driver, fromHere, 10);
				fromHere.click();
				TestUtil.presenceOfElement(driver, fromPoint, 10);
				String fromPoint_D = fromPoint.getAttribute("value");
				
				if(fromPoint_D.equalsIgnoreCase(fromPoint_S))
					bool=true;
				
			}
			
			return bool;
		}
		

}
