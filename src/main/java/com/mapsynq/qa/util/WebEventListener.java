package com.mapsynq.qa.util;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.mapsynq.qa.base.TestBase;

/**********
 * WebEventListener.java is the EventListener class which implements WebDriverEventListener and extends TestBase to get WebDriver 
 * instance.
 * Here the method implementation of  WebDriverEventListener interface has been done to get run time logs and to generate screenshot
 * in case of exceptions.
 * 
 * @author Seshadri @date - 01/22/2019
 *
 */

public class WebEventListener extends TestBase implements WebDriverEventListener {

	
	public void beforeNavigateTo(String url, WebDriver driver) {
		System.out.println("Before navigating to: '" + url + "'");
		
	}
	
	public void afterNavigateTo(String url, WebDriver driver) {
		System.out.println("Navigated to:'" + url + "'");
		
	}
	
	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] text) {
				
		System.out.println("Value of Element " + element.toString()
		+ " before any changes made is: " +  String.valueOf(text));
		
	}
	
	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] text) {
		
		System.out.println("Value of Element " + element.toString()
		+ " Changed to: " +  String.valueOf(text));
		
	}
	
	public void beforeClickOn(WebElement element, WebDriver driver) {
		System.out.println("Trying to click on: " + element.toString());
		
	}
	
	public void afterClickOn(WebElement element, WebDriver driver) {
		System.out.println("Clicked on: " + element.toString());
		
	}
	
	public void beforeNavigateBack(WebDriver driver) {
		System.out.println("Navigating back to previous page");
		
	}
	
	public void afterNavigateBack(WebDriver driver) {
		System.out.println("Navigated back to previous page");
		
	}
	
	public void beforeNavigateForward(WebDriver driver) {
		System.out.println("Navigating forward to next page");
		
	}
	
	public void afterNavigateForward(WebDriver driver) {
		System.out.println("Navigated forward to next page");
		
	}
	
	public void onException(Throwable error, WebDriver driver) {
		System.out.println("Exception occured: " + error);
		try{
			TestUtil.takeScreenshotAtEndOfTest();
		}catch (IOException e){
			e.printStackTrace();
		}
		
	}
	
	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		System.out.println("Trying to find element by: " + by.toString());
		
	}
	
	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		System.out.println("Found element by: " + by.toString());
		
	}
	
	public void beforeAlertAccept(WebDriver driver) {
		System.out.println("Before accepting the alert");
		
	}
	
	public void afterAlertAccept(WebDriver driver) {
		System.out.println("Alert accepted");
		
	}
	
	public void beforeAlertDismiss(WebDriver arg0) {
		System.out.println("Before dismissing the alert");
		
	}

	public void afterAlertDismiss(WebDriver arg0) {
		System.out.println("Alert dismissed");
		
	}
	
	public void beforeGetText(WebElement element, WebDriver driver) {
		System.out.println("Getting the text of element: " + element.toString());
		
	}
	
	public void afterGetText(WebElement element, WebDriver driver, String getText) {
		System.out.println("Text of element: " + element.toString() + " is " + getText);
		
	}
	
	public void beforeNavigateRefresh(WebDriver driver) {
		System.out.println("Before refreshing the page");
		
	}	

	public void afterNavigateRefresh(WebDriver arg0) {
		System.out.println("Page refreshed");
		
	}
	
	public void beforeSwitchToWindow(String window, WebDriver driver) {
		System.out.println("Switching to window: " + window);
		
	}
	
	public void afterSwitchToWindow(String window, WebDriver driver) {
		System.out.println("Switched to window: " + window);
		
	}
	
	/*
	 * non overridden methods of WebListener class
	 */
	

	public <X> void afterGetScreenshotAs(OutputType<X> arg0, X arg1) {
		// TODO Auto-generated method stub
		
	}	

	public void afterScript(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}	

	public <X> void beforeGetScreenshotAs(OutputType<X> arg0) {
		// TODO Auto-generated method stub
		
	}	

	public void beforeScript(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

		

}
