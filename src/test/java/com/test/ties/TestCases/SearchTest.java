package com.test.ties.TestCases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.io.File;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.ties.test.base.TestBase;
import com.ties.test.pages.CartPage;
import com.ties.test.pages.HomePage;
import com.ties.test.pages.ProductPage;
import com.ties.test.pages.SearchPage;
import com.ties.test.util.TestUtil;

public class SearchTest extends TestBase {

	HomePage homepage;
	TestUtil testUtil;
	SearchPage searchpage;
	ProductPage productPage;
	CartPage cartPage;

	ExtentReports extent;
	ExtentTest logger;

	@BeforeSuite
	public void startReport() {
		extent = new ExtentReports(
				System.getProperty("user.dir") + "/test-output/output/" + getClass().getSimpleName() + ".html");
		extent.addSystemInfo("Host", "Skinny Ties Automation").addSystemInfo("Environment", "Local Environment")
				.addSystemInfo("user", "name of the user created");
		extent.loadConfig(new File(System.getProperty("user.dir") + "extent-cofig.xml"));
	}

	@AfterMethod
	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
			logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
		}
		extent.endTest(logger);
	}

	@AfterTest
	public void endReport() {
		extent.flush();
		extent.close();
	}

	public SearchTest() {
		initialization();
		homepage = new HomePage();
		testUtil = new TestUtil();
		searchpage = new SearchPage();
		productPage = new ProductPage();
		cartPage = new CartPage();
	}

	@Test
	public void Test_AddingSingleTie() {
		logger = extent.startTest("Test_AddingSingleTie");
		homepage.SearchTies();
		searchpage.ClickOnProduct(1);
		int intialvalue = cartPage.GetCartvalue();
		productPage.AddProductToCart();
		TestUtil.Wait(2000);
		int finalvalue = cartPage.GotoCartPage();

		assertNotEquals(intialvalue, finalvalue);
		assertEquals(intialvalue + 1, finalvalue);

		cartPage.RemoveCartItems();
		TestUtil.Wait(2000);
		int finalvalue2 = cartPage.GetCartvalue();

		assertEquals(finalvalue2, 0);
		logger.log(LogStatus.PASS, "Test_AddingSingleTie is passed");
	}

	@Test
	public void Test_AddingTieWithQunatity() {
		logger = extent.startTest("Test_AddingSingleTie");
		homepage.SearchTies();
		searchpage.ClickOnProduct(1);
		int intialvalue = cartPage.GetCartvalue();
		int quantity = 2;
		productPage.AddProductWithQuantity("" + quantity);
		TestUtil.Wait(2000);
		int finalvalue = cartPage.GotoCartPage();

		assertNotEquals(intialvalue, finalvalue);
		assertEquals(intialvalue + quantity, finalvalue);

		cartPage.RemoveCartItems();
		TestUtil.Wait(2000);
		int finalvalue2 = cartPage.GetCartvalue();

		assertEquals(finalvalue2, 0);
		logger.log(LogStatus.PASS, "Test_AddingSingleTie is passed");
	}

	@AfterClass
	public void teardown() {
		driver.close();
	}

}
