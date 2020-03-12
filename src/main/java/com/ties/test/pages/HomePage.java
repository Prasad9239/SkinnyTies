package com.ties.test.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ties.test.base.TestBase;

public class HomePage extends TestBase {
	
	@FindBy(xpath="//span[@class='search-toggle-button']")
	WebElement Searchlink;
	
	@FindBy(name="q")
	WebElement SearchBox;
	
	@FindBy(name="submit")
	WebElement SearchButton;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public void SearchTies(){
		Searchlink.click();
		SearchBox.click();
		SearchBox.sendKeys("tie");
		SearchButton.click();
	}
	
	
	
}
