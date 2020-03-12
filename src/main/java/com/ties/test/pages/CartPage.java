package com.ties.test.pages;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.ties.test.base.TestBase;

public class CartPage extends TestBase {
	
	@FindBy(xpath="//a[@href='/cart']")
	WebElement CartPageLink;
	
	@FindBy(xpath="//span[@class='cart-item-count-wrap']")
	WebElement CartValue;
	
	By RemoveLinks=By.xpath("//a[@class='cart-remove-item']");
	
	public CartPage() {
		PageFactory.initElements(driver, this);
	}
	
	public int GetCartvalue(){
		int value = 0;
		String cartvalue=CartValue.getText();
		Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(cartvalue);
	     while(m.find()) {
	    	 value=Integer.parseInt(m.group(1));
	     }
	     return value;
	}
	
	public void RemoveCartItems(){
		CartPageLink.click();
		List<WebElement> links=driver.findElements(RemoveLinks);
		for(WebElement link:links){
			link.click();
		}
	}
	
	public int GotoCartPage(){
		CartPageLink.click();
		return GetCartvalue();
	}

}
