//package Centric.ShoppingStoreTestData;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Iterator;
//
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//public class excelDataDriven {
//	
//	public ArrayList<String> getExcelData() throws IOException {
//		// TODO Auto-generated method stub
//		ArrayList<String> testData = new ArrayList<String>();
//		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+
//		"//src//test//java//Centric//ShoppingStoreTestData//excelData.xlsx");
//		
//		XSSFWorkbook workbook = new XSSFWorkbook(fis);
//		
//		int worksheets = workbook.getNumberOfSheets();
//		for(int i=0; i<worksheets; i++) {		
//			if(workbook.getSheetName(i).equalsIgnoreCase("Login")) {
//				XSSFSheet worksheet = workbook.getSheetAt(i);
//				
//				Iterator<Row> rows = worksheet.iterator();
//				Row firstrow = rows.next(); // First Row				
//				Iterator<Cell> cells = firstrow.cellIterator();
//				int k = 0;
//				int col = 0;
//				while(cells.hasNext()) {
//					Cell cellValue = cells.next();
//					if(cellValue.getStringCellValue().equalsIgnoreCase("TestCases")) {
//						col=k;
//					}
//					k++;
//				}
//				System.out.println("Column index is " +col);
//				
//				while(rows.hasNext()) {
//					Row r = rows.next();
//					Cell c = r.getCell(col);
//					if(c.getStringCellValue().equalsIgnoreCase("Login")) {
//						Iterator<Cell> cv = r.cellIterator();
//						while(cv.hasNext()) {
//							testData.add(cv.next().getStringCellValue());
//							
//					}				
//				}
//			}							
//		}		
//	}
//	System.out.println(testData);
//	return testData;	
//}
//	
//	
//	public static void main(String[] args) {
//		
//	}
//	
//	
//	
//	
//	
//}
