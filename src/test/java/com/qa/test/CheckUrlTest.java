package com.qa.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.util.TestUtil;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CheckUrlTest {

	WebDriver driver;
	String filePath = "C:\\Users\\pkshank\\eclipse-workspace\\Update_Excel\\Test Data\\ReadBrowser_UpdateExcel.xlsx";
	String tabName = "Sheet1";
	int rCnt = 1; // USED TO INITIALIZE THE ROW COUNT FOR STATUS COLUMN
	int cCnt = 2; // COLUMN VALUR FOR STATUS IS HARDCODED AS IT WILL IN ALL LIKELIHOOD NOT CHANGE.

	@BeforeMethod
	public void setUp() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}

	@DataProvider
	public String[][] getData() {

		return TestUtil.readExcel(filePath, tabName);
	}

	@Test(dataProvider = "getData")
	public void checkUrl(String url, String title) {

		driver.get(url);

		if (driver.getTitle().equals(title)) {

			TestUtil.writeExcel(filePath, tabName, "SUCCESS", rCnt, cCnt);
			rCnt = rCnt + 1;
		}

		else {

			TestUtil.writeExcel(filePath, tabName, "FAILURE", rCnt, cCnt);
			rCnt = rCnt + 1;

		}

	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
	}

}
