package com.mapsynq.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mapsynq.qa.base.TestBase;
import com.mapsynq.qa.pages.LiveTab;

/**********
* LiveTabTest.java consists the Test cases for the Live tab on the landing page of mapSYNQ.com
* LiveTab.java is the Page Repository of this test class.
* 
* It consists total 5 test cases.
* * 
* @author Seshadri @date - 01/22/2019
*
*/

public class LiveTabTest extends TestBase{
	
	LiveTab  liveTab=null;
	
	public LiveTabTest(){
		super();
	}
	
	@BeforeMethod
	@Parameters("browser")
	public void setUp(String browserName){
		initialization(browserName);
		liveTab = new LiveTab();
	}
	
	/************************************** Verify Camera Footage Display test ***********************/
	
	
	@Test (priority=15)
	public void getCameraImageTest(){
		
		Assert.assertTrue(liveTab.getCameraImage());
		
	}
	
	/************************************** Close Camera footage popup test ***********************/
	
	@Test (priority=16)
	public void closeCameraPopupTest(){
		
		Assert.assertTrue(liveTab.closeCameraPopup());
		
	}
	
	/************************************** Verify Toll Chart Display test ***********************/
	
	@Test (priority=17)
	public void getTollsListTest(){
		
		Assert.assertTrue(liveTab.getTollsList());
		
	}
	
	/************************************** Close Toll popup test ***********************/
	
	
	@Test (priority=18)
	public void closeTollPopupTest(){
		
		Assert.assertTrue(liveTab.closeTollPopup());
		
	}
	
	/********************************** Verify Toll Chart against a selected vehicle group and Time frame ***********************/
	
	@Test (priority=19)
	public void viewTollTest(){
		
		Assert.assertTrue(liveTab.viewToll());
		
	}
	
	
	
	
	@AfterMethod
	public void teardown(){
	    driver.quit(); 
	    driver=null;

	}
	

}
