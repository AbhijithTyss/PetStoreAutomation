package api.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	
	public static String[][] getAllData(String sheetName) throws EncryptedDocumentException, IOException{
		FileInputStream fis=new FileInputStream("./src/test/resources/UserData.xlsx");
		Workbook workbook = WorkbookFactory.create(fis);
		int rowCount = workbook.getSheet(sheetName).getPhysicalNumberOfRows();
		int columnCount = workbook.getSheet(sheetName).getRow(0).getPhysicalNumberOfCells();
		String[][] data=new String[rowCount][columnCount];
		for(int i=1;i<rowCount;i++) {
			for(int j=0;j<columnCount;j++) {
				data[i][j]=workbook.getSheet(sheetName).getRow(i).getCell(j).getStringCellValue();
			}
		}
		return data;
	}
	
	public static String[][] getAllUserNames(String sheetName,int rowNum) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("./src/test/resources/UserData.xlsx");
		Workbook workbook = WorkbookFactory.create(fis);
		int rowCount = workbook.getSheet(sheetName).getPhysicalNumberOfRows();
		int columnCount = workbook.getSheet(sheetName).getRow(0).getPhysicalNumberOfCells();
		String[][] data=new String[rowCount][columnCount];
		for(int i=0;i<rowCount;i++) {
			if(i==rowNum) {
				for(int j=0;j<columnCount;j++) {
					data[i][j]=workbook.getSheet(sheetName).getRow(i).getCell(j).getStringCellValue();
				}
			}
		}
		return data;
	}
}
