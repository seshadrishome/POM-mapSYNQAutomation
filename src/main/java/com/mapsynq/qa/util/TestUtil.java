package com.mapsynq.qa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mapsynq.qa.base.TestBase;


/**********
 * TestUtil.java is the utility class. Here variables and methods are stored which will be used by multiple classes.
 * TestUtil.java currently holds the variables those are used for test case pass/fail verification purpose, Test data Location etc.
 * Also, it holds the excel data reader method, screenshot method, explicitly defined wait methods.
 * It extends TestBase.java to get WebDriver Instance.
 * 
 * @author Seshadri @date - 01/22/2019
 *
 */

public class TestUtil extends TestBase{
	
	public static long PAGE_LOAD_TIMEOUT = 30;
	public static String Page_Title = "mapSYNQ - Live Traffic Information Platform";
	
	public static String Test_Data_Location = "G:\\Selenium\\Selenium WorkSpace\\mapSYNQTest\\src\\main\\java\\com\\mapsynq\\qa\\testdata\\mapSYNQ_Test_Data.xlsx";
	
	
	public static String Direction_Sheet_Name = "From_To_Point";
	public static String Search_Sheet_Name = "Search";
	
	public static String Select_Journey_Time = "1 hour later";
	public static String Camera_Popup_Title = "Traffic Camera";
	public static String Tolls_Popup_Title = "ERP";
	public static String Toll_Dropdown_Value = "Motorcycle (Weekdays)";
	public static String Toll_List_Time = "18:55 - 19:00";
	public static String Toll_List_Value = "$1.25";
	
	static Workbook book;
	static Sheet sheet;
	
	
	/***************************** Read the whole data of an excel sheet workbook utility *****************************/
	
	public static Object[][]getTestData(String sheetName){
		FileInputStream file = null;
		try {
			file = new FileInputStream(Test_Data_Location);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			book = WorkbookFactory.create(file);
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];		
		for (int i = 0; i < sheet.getLastRowNum(); i++)
		{
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++){
				data[i][k] = sheet.getRow(i+1).getCell(k).toString();
			}
		}
		
		return data;
		
	}
	
	/***************************** Take Screenshot utility *****************************/
	
	public static void takeScreenshotAtEndOfTest()throws IOException{
		
		java.io.File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileHandler.copy(scrFile, new java.io.File(currentDir +"/screenshots/" + System.currentTimeMillis() + ".png"));
	}
	
	/***************************** Explicitly Defined Wait Utility *****************************/
	
	public static void clickOn(WebDriver driver, WebElement locator, int timeOut){
		
		new WebDriverWait(driver, timeOut).ignoring(NoSuchElementException.class).until(ExpectedConditions.elementToBeClickable(locator));
		locator.click();
	} 
	
	public static void presenceOfElement(WebDriver driver, WebElement locator, int timeOut){
		
		new WebDriverWait(driver, timeOut).ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOf(locator));
	} 
	
	public static void elementClickable(WebDriver driver, WebElement locator, int timeOut){
		
		new WebDriverWait(driver, timeOut).ignoring(NoSuchElementException.class).until(ExpectedConditions.elementToBeClickable(locator));
	} 
	

}
