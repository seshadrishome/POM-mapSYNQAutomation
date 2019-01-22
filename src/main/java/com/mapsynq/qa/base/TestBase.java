package com.mapsynq.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import com.mapsynq.qa.util.TestUtil;
import com.mapsynq.qa.util.WebEventListener;

/**********
 * TestBase.java is the class where the WebDriver instance has been created and the url has been launched.
 * Also, WebDriver Event Listener is implemented here to get run time log.
 * All the Page Repositories, Test classes and Utility class extends TestBase.java to use WebDriver instance.
 * 
 * 
 * @author Seshadri @date - 01/22/2019
 *
 */


public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop = null;
	public static EventFiringWebDriver e_driver = null;
	public static WebEventListener eventListener = null;
	
	public TestBase(){
		
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("G:\\Selenium\\Selenium WorkSpace\\mapSYNQTest\\src\\main\\java\\com"
					+ "\\mapsynq\\qa\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
						e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void initialization(String browserName){
		driver = null;
		
		if (browserName.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", "G:\\Selenium\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
		}else if (browserName.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver", "G:\\Selenium\\geckodriver-v0.23.0-win64\\geckodriver.exe");
			driver = new FirefoxDriver();
		}else if (browserName.equalsIgnoreCase("edge")){
			System.setProperty("webdriver.edge.driver", "G:\\Selenium\\Edge driver\\MicrosoftWebDriver.exe");
			driver = new EdgeDriver();
			
		}
		
		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		
	}
	
	
	
	

}
