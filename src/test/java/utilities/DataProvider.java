package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.*;

public class DataProvider {

		private static XSSFWorkbook workbook;
		private static XSSFSheet sheet;
		private static XSSFRow row;
		private static XSSFCell cell;

		public void setExcelFile(String excelFilePath,String sheetName) throws IOException {
		File file=new File(excelFilePath);
		FileInputStream istream=new FileInputStream(file);
		workbook=new XSSFWorkbook(istream);
		sheet=workbook.getSheet(sheetName);

		}
		public String getCellData(int rownum,int cellnum) {
			cell=sheet.getRow(rownum).getCell(cellnum);
			return cell.getStringCellValue();
			
		}
		public int getRowCountInSheet() {
			int rowcount=sheet.getLastRowNum()-sheet.getFirstRowNum();
			System.out.println(sheet.getLastRowNum()+" "+sheet.getFirstRowNum());
			return rowcount;
		}

}

