package com.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class SearchResult {

	String keyword;
	String title;
	String region;
	String country;
	
	List<String> attributesContaining;
	List<String> attributesNotContaining;
	
	public String getKeyword() {
		return keyword;
	}
	
	public String getTitle() {
		return title;
	}

	public String getRegion() {
		return region;
	}

	public String getCountry() {
		return country;
	}

	public List<String> getAttributesContaining() {
		return attributesContaining;
	}

	public List<String> getAttributesNotContaining() {
		return attributesNotContaining;
	}
	
	public Map<String, String> getAllValues() {
		Map<String, String> data = new HashMap<>();
		data.put("name", title);
		data.put("region", region);
		data.put("country", country);
		return data;
	}
	
	public SearchResult(String keyword, String title, String region, String country) {
		this.keyword = keyword;
		this.title = title;
		this.region = region;
		this.country = country;
		attributesContaining = new ArrayList<String>();
		attributesNotContaining = new ArrayList<String>();
		
		if (StringUtils.containsIgnoreCase(title, keyword)) {
			attributesContaining.add("name");
		} else {
			attributesNotContaining.add("name");
		}
		if (StringUtils.containsIgnoreCase(region, keyword)) {
			attributesContaining.add("region");
		} else {
			attributesNotContaining.add("region");
		}
		if (StringUtils.containsIgnoreCase(country, keyword)) {
			attributesContaining.add("country");
		} else {
			attributesNotContaining.add("country");
		}
	}
	
}
