package com.scripts;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.common.BaseTest;
import com.common.DataProviderClass;
import com.model.SearchResult;
import com.page.HomePage;
import com.page.ResultDetailPage;
import com.page.SearchResultsPage;

public class VivinoTest extends BaseTest {

	HomePage hp;
	SearchResultsPage srp;
	ResultDetailPage rdp;

	@BeforeClass
	public void startup() {
		launchBrowser();
		hp = new HomePage(driver);
	}

	@Test(priority = 0)
	public void verifyHome() {
		Assert.assertEquals(hp.getTitle(), "Vivino (Denmark) - Buy the Right Wine");
	}

	@Test(dataProvider = "getdata", dataProviderClass = DataProviderClass.class, priority = 1)
	public void searchOnDashboard(String keyword) throws Exception {
		Assert.assertTrue(hp.verifySearchField());
		System.out.println("Searching for: " + keyword);
		hp.search(keyword);
		verifyResults(keyword);
	}

	private void verifyResults(String keyword) {
		srp = new SearchResultsPage(driver);
		srp.parseResults(keyword);
		List<SearchResult> sr = srp.getSearchResults();
		for (SearchResult result : sr) {
			System.out.println("-------------------------------------------");
			Assert.assertTrue(result.getAttributesContaining().size() > 0);
			System.out.println("for result \"" + result.getTitle() + "\", following attributes contain the keyword:"
					+ result.getKeyword());
			System.out.println(result.getAttributesContaining());
			if (result.getAttributesNotContaining().size() > 0) {
				System.out.println("following attributes do not contain the keyword:" + result.getKeyword());
				System.out.println(result.getAttributesNotContaining());
			}
			System.out.println("-------------------------------------------");
		}
		int randomIndex = srp.clickOnRandomTitle();
		verifyResultDetail(sr.get(randomIndex));
	}

	private void verifyResultDetail(SearchResult sr) {
		rdp = new ResultDetailPage(driver, sr);
		rdp.parsePage();
		Map<String, String> pageData = rdp.getPageData();
		Map<String, String> resultData = rdp.getReferenceResult().getAllValues();
		for (Map.Entry<String, String> entry : pageData.entrySet()) {
			String pageValue = entry.getValue();
			String resultValue = resultData.get(entry.getKey());
			Assert.assertTrue(StringUtils.containsIgnoreCase(resultValue, pageValue));
			if (StringUtils.containsIgnoreCase(pageValue, rdp.getReferenceResult().getKeyword())) {
				System.out.println("for selected result, attribute containing the keyword is: " + entry.getKey());
			}
		}
	}

	@AfterClass
	public void shutdown() {
		quitBrowser();
	}

}
