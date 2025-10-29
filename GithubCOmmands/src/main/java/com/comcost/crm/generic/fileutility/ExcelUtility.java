package com.comcost.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {

	public String getDatafromExcelFile(String sheetname , int rownum , int cellnum ) throws IOException {
		
		FileInputStream fis1 = new FileInputStream(
				"C:\\Users\\abkmo\\OneDrive\\Documents\\DDt Excel\\TekPyramid_DDT.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		String data = wb.getSheet(sheetname).getRow(rownum).getCell(cellnum).getStringCellValue();
		wb.close();
		return data;
	}
	
	public int getRowCount(String sheetname) throws IOException {
		
		FileInputStream fis1 = new FileInputStream(
				"C:\\Users\\abkmo\\OneDrive\\Documents\\DDt Excel\\TekPyramid_DDT.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		int rownum = wb.getSheet(sheetname).getLastRowNum();
		wb.close();
		return rownum;
	}
	
	public void setDataIntoExcel(String sheetname , int rownum , int cellnum , String data) throws IOException {
		
		FileInputStream fis1 = new FileInputStream(
				"C:\\Users\\abkmo\\OneDrive\\Documents\\DDt Excel\\TekPyramid_DDT.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		data = wb.getSheet(sheetname).getRow(rownum).getCell(cellnum).getStringCellValue();
		
		FileOutputStream fos = new FileOutputStream("C:\\Users\\abkmo\\OneDrive\\Documents\\DDt Excel\\TekPyramid_DDT.xlsx");
		wb.write(fos);
		wb.close();
	}
	
	
	
	
	
}
