package Centric.ShoppingStoreTestComponents;

import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Centric.ShoppingStoreApplication.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LoginPage login;
	
	
	public WebDriver initializeDriver() throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+
				"//src//main//java//Centric//ShoppingStoreResources/GlobalData.properties");
		prop.load(fis);
		
		String browserName = prop.getProperty("browser");
		
		if(browserName.contains("chrome")){
			
			// running chrome in headless mode
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless"))
			{
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			// driver.manage().window().setSize(new Dimension(1440,900));
		}else if(browserName.equalsIgnoreCase("firefox")){
			// Firefox browser
		}else if(browserName.equalsIgnoreCase("edge")) {
			// Edge browser
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {		
		//read Json to String
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);		
		// String to HashMap using Jackson Databind	
		ObjectMapper mapper = new ObjectMapper();
		// stores 2 hash maps = {{map, map}}
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
			});	
		return data;		
	}
	
	
	
	public ArrayList<String> getExcelData(String SheetName, String testcaseName) throws IOException {
		
		ArrayList<String> testData = new ArrayList<String>();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+
		"//src//test//java//Centric//ShoppingStoreTestData//excelData.xlsx");
		
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		int worksheets = workbook.getNumberOfSheets();
		for(int i=0; i<worksheets; i++) {		
			if(workbook.getSheetName(i).equalsIgnoreCase(SheetName)) {
				XSSFSheet worksheet = workbook.getSheetAt(i);
				
				Iterator<Row> rows = worksheet.iterator();
				Row firstrow = rows.next(); // First Row				
				Iterator<Cell> cells = firstrow.cellIterator();
				int k = 0;
				int col = 0;
				while(cells.hasNext()) {
					Cell cellValue = cells.next();
					if(cellValue.getStringCellValue().equalsIgnoreCase("TestCases")) {
						col=k;
					}
					k++;
				}
				System.out.println("Column index is " +col);
				
				while(rows.hasNext()) {
					
					Row r = rows.next();			
					Cell c = r.getCell(col);
					if(c.getStringCellValue().equalsIgnoreCase(testcaseName)) {
						Iterator<Cell> cv = r.cellIterator();
						while(cv.hasNext()) {
							
							Cell cell = cv.next();
							if(cell.getCellType()== CellType.STRING) {
								testData.add(cell.getStringCellValue());
								System.out.println(testData);
							}
							else {
								testData.add(NumberToTextConverter.toText(cell.getNumericCellValue()));
								System.out.println(testData);

						}		
					}				
				}
			}							
		}		
	}
	System.out.println(testData);	
	return testData;		
}
	
	
	@BeforeMethod(alwaysRun=true)
	public LoginPage loginToApplication() throws IOException {
		driver = initializeDriver();
		login = new LoginPage(driver);
		login.goTo();
		return login;		
	}
	
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}
	
	//taking screenshots
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File destination = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, destination);
		return System.getProperty("user.dir")+"//reports//"+ testCaseName + ".png";	
	}
	
	
}
