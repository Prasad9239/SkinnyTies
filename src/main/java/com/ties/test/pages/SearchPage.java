package com.ties.test.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ties.test.base.TestBase;
import com.ties.test.util.TestUtil;

public class SearchPage extends TestBase {
	
	By Productlinks=By.xpath("//ul/li/div/article[@class='product-item']");
	
	public SearchPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void ClickOnProduct(int index){
		List<WebElement> productList=driver.findElements(Productlinks);
		if(index <= productList.size()){
			TestUtil.ScrollIntoView(productList.get(index-1));
			productList.get(index-1).click();
		}else{
			System.out.println("Given Index is not available");
		}
	}
}
