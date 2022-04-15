package com.common;

import org.testng.annotations.DataProvider;

public class DataProviderClass {

	@DataProvider(name = "getdata")
	public static Object[][] getCsvdata() throws Exception {
		InputReader ir = new InputReader();
		return ir.getKeywords().toArray(new String[0][]);
	}
}
