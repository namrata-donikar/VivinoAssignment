package com.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

	@FindBy(xpath = "//input[contains(@class,'searchBar')]")
	WebElement search;

	@FindBy(xpath = "//title")
	WebElement title;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public boolean verifySearchField() {
		return search.isDisplayed();
	}

	public void search(String keyword) throws Exception {
		search.clear();
		search.sendKeys(keyword);
		search.sendKeys(Keys.ENTER);
	}

}