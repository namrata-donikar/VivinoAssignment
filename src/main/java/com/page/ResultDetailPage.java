package com.page;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.model.SearchResult;

public class ResultDetailPage {
	
	private WebDriver driver;

	private SearchResult referenceResult;
	
	private Map<String, String> pageData;
	
	@FindBy(xpath = "//span[contains(@class,'headline')]/a[contains(@class,'winery')]")
	WebElement winery;

	@FindBy(xpath = "//h1/span[contains(@class,'vintage')]")
	WebElement name;

	@FindBy(xpath = "//div[@id='wine-location-header']//a[contains(@data-cy, 'breadcrumb-region')]")
	WebElement region;

	@FindBy(xpath = "//div[@id='wine-location-header']//a[contains(@data-cy, 'breadcrumb-country')]")
	WebElement country;
	
	@FindBy(xpath = "//div[@id='wine-location-header']//a[contains(@data-cy, 'breadcrumb-grape')]")
	WebElement grape;
	
	@FindBy(xpath = "//div[@id='wine-location-header']//a[contains(@data-cy, 'breadcrumb-type')]")
	WebElement type;
	
	public Map<String, String> getPageData() {
		return pageData;
	}
	
	public SearchResult getReferenceResult() {
		return referenceResult;
	}

	public ResultDetailPage(WebDriver driver, SearchResult referenceResult) {
		this.driver = driver;
		this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		PageFactory.initElements(this.driver, this);
		this.referenceResult = referenceResult;
		this.pageData = new LinkedHashMap<>();
	}
	
	public void parsePage() {
//		pageData.put("winery", winery.getText());
		pageData.put("name", winery.getText() + " " + name.getText());
		pageData.put("region", region.getText());
		pageData.put("country", country.getText());
//		pageData.put("grape", grape.getText());
//		pageData.put("type", type.getText());
	}
}
