package com.ties.test.pages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ties.test.base.TestBase;

public class ProductPage extends TestBase {

	@FindBy(xpath = "//input[@value='Add to Cart']")
	WebElement AddToCartLink;

	@FindBy(name = "quantity")
	WebElement QuantityBox;

	public ProductPage() {
		PageFactory.initElements(driver, this);
	}

	public void AddProductToCart() {
		AddToCartLink.click();
	}

	public void AddProductWithQuantity(String quantity) {
		QuantityBox.click();
		QuantityBox.clear();
		QuantityBox.sendKeys(quantity);
		AddToCartLink.click();
	}
}
