package com.page;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.model.SearchResult;

public class SearchResultsPage {
	
	private WebDriver driver;
	
	@FindBy(xpath = "//span[contains(@class,'wine-card__name')]/a")
	private List<WebElement> titles;

	private List<SearchResult> searchResults;
	
	public List<SearchResult> getSearchResults() {
		return searchResults;
	}
	
	@FindBy(xpath = "//div[contains(@class,'wine-card__content')]")
	private List<WebElement> results;
	
	private By byTitle = By.xpath(".//span[contains(@class,'wine-card__name')]/a");
	private By byRegion = By.xpath(".//span[contains(@class,'wine-card__region')]/a");
	private By byCountry = By.xpath(".//span[contains(@class,'wine-card__region')]/a[contains(@data-item-type,'country')]");
	
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		this.searchResults = new ArrayList<>();
		PageFactory.initElements(this.driver, this);
	}
	
	public void parseResults(String keyword) {
		for (WebElement result : results) {
			String title = result.findElement(byTitle).getText();
			String region = result.findElement(byRegion).getText();
			String country = result.findElement(byCountry).getText();
			
			SearchResult sr = new SearchResult(keyword, title, region, country);
			searchResults.add(sr);
		}
	}
	
	public int clickOnRandomTitle() {
		int i = new Random().nextInt(titles.size());
		titles.get(i).click();
		return i;
	}
	
}
