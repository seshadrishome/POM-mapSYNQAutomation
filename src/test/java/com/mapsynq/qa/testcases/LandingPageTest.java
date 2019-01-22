package com.mapsynq.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mapsynq.qa.base.TestBase;
import com.mapsynq.qa.pages.LandingPage;
import com.mapsynq.qa.util.TestUtil;

/**********
* LandingPageTest.java consists the Test cases for the landing page of mapSYNQ.com
* LandingPage.java is the Page Repository of this test class.
* 
* It consists total 10 test cases.
* * 
* @author Seshadri @date - 01/22/2019
*
*/

public class LandingPageTest extends TestBase{
	
	LandingPage landingPage = null;
	
	
	public LandingPageTest(){
		super();
	}
	
	
	
	
	@BeforeMethod
	@Parameters("browser")
	public void setUp(String browserName){
		initialization(browserName);
		landingPage = new LandingPage();
	}
	
	/************************************** Validate Landing Page Title ***********************/
	
	
	@Test (priority=1)
	public void LandingPageTitleTest(){
		String title = landingPage.validateLandingPageTitle(driver);
		Assert.assertEquals(title, TestUtil.Page_Title);
	}
	
	/************************************** Validate if mapSYNQ logo is displaying ***********************/
	
	@Test (priority=2)
	public void landingPagelogoTest(){
		Assert.assertTrue(landingPage.validatemapSYNQLogo());
	}
	
	/************************************** TestNG Data Provider from Excel ***********************/
	
	@DataProvider(name = "data-provider-Search-Test")
	public Object[][] getLocationSearchTest(){
		Object data[][] = TestUtil.getTestData(TestUtil.Search_Sheet_Name);
		return data;
		
	}
	
	/******************************* Enter Search text and select an option from suggested options ***********************/
	
	@Test (priority=3, dataProvider="data-provider-Search-Test")
	public void searchLocationFromSuggestedOptionTest(String Search_Location){
		
		landingPage.searchLocationFromSuggestedOption(Search_Location);
		Assert.assertTrue(landingPage.searchResultIsVisible());
				
	}
	
	/******************************* Enter Search text and click on search location button ***********************/
	
	@Test (priority=4, dataProvider="data-provider-Search-Test")
	public void searchLocationBySearchButtonTest(String Search_Location){
		
		landingPage.searchLocationFromSuggestedOption(Search_Location);
		Assert.assertTrue(landingPage.searchResultIsVisible());
		
	}
	
	/******************************* Select a Location on the returned search result ***********************/
	
	@Test (priority=5, dataProvider="data-provider-Search-Test")
	public void selectLocationTest(String Search_Location){
		
		Assert.assertTrue(landingPage.selectLocation(Search_Location));
		
	}
	
	/************* Select the dropdown of a Location on the returned search result and select To Here from the dropdown ***********/
	
	@Test (priority=6, dataProvider="data-provider-Search-Test")
	public void selectToHereTest(String Search_Location){
		
		Assert.assertTrue(landingPage.selectToHere(Search_Location));
	
	}
	
	/*************  Select the dropdown of a Location on the returned search result and select From Here from the dropdown ********/
	
	@Test (priority=7, dataProvider="data-provider-Search-Test")
	public void selectFromHereTest(String Search_Location){
		
		Assert.assertTrue(landingPage.selectFromHere(Search_Location));
	
	}
	
	/*********** Select a Location on the returned search result and select From Here from the Point of Interest popup ************/
	
	@Test (priority=8, dataProvider="data-provider-Search-Test")
	public void selectFromHerePOITest(String Search_Location){
		
		Assert.assertTrue(landingPage.selectFromHerePOI(Search_Location));
	
	}
	
	
	/*********** Select a Location on the returned search result and select To Here from the Point of Interest popup ************/
	
	@Test (priority=9, dataProvider="data-provider-Search-Test")
	public void selectToHerePOITest(String Search_Location){
		
		Assert.assertTrue(landingPage.selectToHerePOI(Search_Location));
	
	}
	
	/*************************************** Close Point of Inetrest popup ****************************************/
	
	@Test (priority=10, dataProvider="data-provider-Search-Test")
	public void closePOIPopupTest(String Search_Location){
		
		Assert.assertTrue(landingPage.closePOIPopup(Search_Location));
	
	}
	
	
	
	@AfterMethod
	public void teardown(){
	    driver.quit(); 
	    driver=null;

	}


}
