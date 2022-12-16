package Centric.ShoppingStoreTestComponents;

import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
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
