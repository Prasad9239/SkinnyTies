package com.ties.test.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.ties.test.util.TestUtil;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties props;
	
	public TestBase() {
		
		FileInputStream fis;
		try {
			fis = new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\ties\\test\\config\\config.properties"));
			props=new Properties();
			props.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void initialization(){
		String browsername=props.getProperty("driver");
		if(browsername.equals("IE")){
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\IEDriverServer.exe");
			driver=new InternetExplorerDriver();
		}
		if(browsername.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
			driver=new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIME_OUT, TimeUnit.SECONDS);
		String url=props.getProperty("url");
		driver.get(url);
		
	}

}
