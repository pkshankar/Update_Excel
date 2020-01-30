/*
 * AUTHOR : P K SHANKAR
 * 
 * PURPOSE : THIS METHOD IS USED TO READ DATA FROM EXCEL
 * 
 * */

package com.qa.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestUtil {

	public static String[][] readExcel(String filePath, String tabName) {

		String browserNameArray[][] = null;

		try {

			FileInputStream fis = new FileInputStream(filePath);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(tabName);
			int lastRow = sheet.getLastRowNum();
			browserNameArray = new String[lastRow - 1][sheet.getRow(0).getLastCellNum() - 1];

			for (int rw = 0; rw < lastRow - 1; rw++) {

				for (int cl = 0; cl < sheet.getRow(rw).getLastCellNum() - 1; cl++) {

					browserNameArray[rw][cl] = sheet.getRow(rw + 1).getCell(cl).getStringCellValue();
				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return browserNameArray;

	}

	/* PURPOSE : THIS METHOD IS USED TO WRITE DATA TO EXCEL */

	public static void writeExcel(String filePath, String tabName, String text, int rw, int cl) {

		try {

			FileInputStream fis = new FileInputStream(filePath);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(tabName);
			sheet.getRow(rw).createCell(cl);
			sheet.getRow(rw).getCell(cl).setCellValue(text);
			FileOutputStream fos = new FileOutputStream(filePath);
			workbook.write(fos);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
