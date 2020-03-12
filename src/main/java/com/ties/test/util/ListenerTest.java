package com.ties.test.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.ties.test.base.TestBase;


public class ListenerTest extends TestBase implements ITestListener{

	public void onFinish(ITestContext arg0) {
		
	}

	public void onStart(ITestContext arg0) {
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		
	}

	public void onTestFailure(ITestResult Result) {
		System.out.println("The name of the testcase failed is :"+Result.getName());
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"\\Screenshots\\"+Result.getName()+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void onTestSkipped(ITestResult arg0) {
		
	}

	public void onTestStart(ITestResult arg0) {
		
	}

	public void onTestSuccess(ITestResult arg0) {
		
	}
}
