package com.mapsynq.qa.testcases;





import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mapsynq.qa.base.TestBase;
import com.mapsynq.qa.pages.DirectionsTab;
import com.mapsynq.qa.util.TestUtil;

/**********
* DirectionsTabTest.java consists the Test cases for the Direction tab on the landing page of mapSYNQ.com
* DirectionTab.java is the Page Repository of this test class.
* 
* it consists 4 Test cases.
* * 
* @author Seshadri @date - 01/22/2019
*
*/

public class DirectionsTabTest extends TestBase{
	
	DirectionsTab directionsTab=null;
	
	public DirectionsTabTest(){
		super();
	}
	
	@BeforeMethod
	@Parameters("browser")
	public void setUp(String browserName){
		initialization(browserName);
		directionsTab = new DirectionsTab();
	}
	
	/************************************** TestNG Data Provider from Excel ***********************/
	
	@DataProvider(name = "data-provider-Directions-Test")
	public Object[][] getFromToPointForDirectionsTest(){
		
		Object data[][] = TestUtil.getTestData(TestUtil.Direction_Sheet_Name);
		return data;
		
	}
	
	/************************************** Swap From To Point Test ***********************/
	
	@Test (priority=11, dataProvider="data-provider-Directions-Test")
	public void swapFromToPointTest(String From_Point, String To_Point){
		
		Assert.assertTrue(directionsTab.swapFromToPoint(From_Point, To_Point));
		
	}
	
	/************************************** Get Direction based on From To Point Test ***********************/
	
	
	@Test (priority=12, dataProvider="data-provider-Directions-Test")
	public void getDirectionsTest(String From_Point, String To_Point){
		
		directionsTab.getDirection(From_Point, To_Point);
		Assert.assertTrue(directionsTab.searchResultIsVisible());
		
	}
	
	/************************************** Clear Route Test ***********************/

	
	@Test (priority=13, dataProvider="data-provider-Directions-Test")
	public void clearRouteTest(String From_Point, String To_Point){
		
		directionsTab.clearRoute(From_Point, To_Point);
		Assert.assertEquals("",directionsTab.getFromPoint());
		Assert.assertEquals("",directionsTab.getToPoint());
	}
	
	/************************************** Leave At Time Test ***********************/
	
	@Test (priority=14, dataProvider="data-provider-Directions-Test")
	public void leaveAtTime(String From_Point, String To_Point){

		Assert.assertTrue(directionsTab.leaveAt(From_Point, To_Point));
		
	}
	
	@AfterMethod
	public void teardown(){
	            driver.quit();
	            driver=null;
	            
	    

	}

}
