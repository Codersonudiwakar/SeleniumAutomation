package com.selenium.automation.utils;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestNGMethod;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

public class DataReader {

	private static XSSFWorkbook ExcelWBook;
	private static XSSFSheet ExcelWSheet;
	private static FormulaEvaluator Evaluator;
	private static XSSFCell Cell;
	private static XSSFRow Row;
	private static int rowCount;
	public static int columnCount;

	private static void loadDataFile(String FileName) {
		try {
			Properties properties = new Properties();
			FileInputStream fisConfig = new FileInputStream("Properties/config.properties");
			properties.load(fisConfig);
			String FilePath = properties.getProperty("excelFilePath");
			// String FilePath = "./ExcelFiles/" + FileName;
			FileInputStream ExcelFile = new FileInputStream(FilePath);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			Evaluator = ExcelWBook.getCreationHelper().createFormulaEvaluator();
			ExcelWSheet = ExcelWBook.getSheetAt(0);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static int getRowCount(String FileName) {
		loadDataFile(FileName);
		rowCount = ExcelWSheet.getLastRowNum();
		return rowCount;
	}

	public static Map<String, String> getDataSet(int rowNum, String FileName) {
		loadDataFile(FileName);
		DataFormatter formatter = new DataFormatter();
		Row = ExcelWSheet.getRow(rowNum);
		columnCount = Row.getLastCellNum();
		Map<String, String> mpData = new HashMap<String, String>();
		for (int i = 0; i < columnCount; i++) {
			String value = "";
			Cell = ExcelWSheet.getRow(0).getCell(i);
			String key = formatter.formatCellValue(Cell, Evaluator);
			try {
				Cell = Row.getCell(i);
				value = Cell.getStringCellValue();
			} catch (Exception e) {
				value = "";
			}
			mpData.put(key, value);
		}
		return mpData;
	}

	@DataProvider(name = "getData")
	public static Object[][] getData(Method m) {
		String dataFileName = "TestData.xlsx";
		getRowCount(dataFileName);
		int intTestRowCount = 0;
		int intStartRow = 0;
		for (int i = 0; i < rowCount; i++) {
			if (getDataSet(i + 1, dataFileName).containsValue(m.getName())) {
				intTestRowCount++;
				if (intStartRow == 0)
					intStartRow = i + 1;
			}
		}
		StoreIteration.strIterationCount = StoreIteration.strIterationCount + m.getName() + ":" + intTestRowCount + ",";
		
		Object[][] iterationData = new Object[intTestRowCount][1];
		int intCounter = 0;
		for (int i = intStartRow; i < intStartRow + intTestRowCount; i++) {
			Map<String, String> mpData = getDataSet(i, dataFileName);
			iterationData[intCounter] = new Map[] { mpData };
			intCounter++;
		}

		return iterationData;
	}

	@DataProvider(name = "getTestData")
	public static Object[][] getTestData(ITestNGMethod m) {
		Class<?> cls = m.getRealClass();
		String sheetName = cls.getSimpleName();
		System.out.println("dataprovider is being called from the class: " + sheetName);

		String dataFileName = "TestData.xlsx";
		System.out.println("sheet name: " + sheetName);
		getRowCount(dataFileName, sheetName);
		int intTestRowCount = 0;
		int intStartRow = 0;
		for (int i = 0; i < rowCount; i++) {
			if (getDataSet(i + 1, dataFileName, sheetName).containsValue(m.getMethodName())) {
				intTestRowCount++;
				if (intStartRow == 0)
					intStartRow = i + 1;
			}
		}

		Object[][] iterationData = new Object[intTestRowCount][1];
		int intCounter = 0;
		for (int i = intStartRow; i < intStartRow + intTestRowCount; i++) {
			Map<String, String> mpData = getDataSet(i, dataFileName, sheetName);
			iterationData[intCounter] = new Map[] { mpData };
			intCounter++;
		}

		return iterationData;
	}

	public static Map<String, String> getDataSet(int rowNum, String FileName, String sheetName) {
		loadDataFile(FileName);
		DataFormatter formatter = new DataFormatter();
		Row = ExcelWSheet.getRow(rowNum);
		columnCount = Row.getLastCellNum();
		Map<String, String> mpData = new HashMap<String, String>();
		for (int i = 0; i < columnCount; i++) {
			String value = "";
			Cell = ExcelWSheet.getRow(0).getCell(i);
			String key = formatter.formatCellValue(Cell, Evaluator);
			try {
				Cell = Row.getCell(i);
				value = Cell.getStringCellValue();
			} catch (Exception e) {
				value = "";
			}
			mpData.put(key, value);
		}
		return mpData;
	}

	public static int getRowCount(String FileName, String sheetName) {
		loadDataFile(FileName);
		ExcelWSheet = ExcelWBook.getSheet(sheetName);
		rowCount = ExcelWSheet.getLastRowNum();
		return rowCount;
	}
}
