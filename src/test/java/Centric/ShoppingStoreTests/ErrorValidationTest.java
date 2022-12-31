package Centric.ShoppingStoreTests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Centric.ShoppingStoreTestComponents.BaseTest;
import Centric.ShoppingStoreTestComponents.Retry;
import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class ErrorValidationTest extends BaseTest {
	
	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void invalidUserID() {
		
		login.loginApplication("test@gmail.com", "seleniumtest");
		String wrongID = login.invalidUserID();
		Assert.assertEquals("Login was unsuccessful. Please correct the errors and try again.", wrongID);
	}
	
	
	@Test
	public void invalidPassword() {
		
		login.loginApplication("test@gmail.com", "invalidPassword");
		String wrongPass = login.invalidpassWord();
		Assert.assertEquals("The credentials provided are incorrect", wrongPass);
	}
	
	
	@Test(dataProvider="getErrorValidationData", groups= {"ErrorHandling"})
	public void invalidIDUsingExcel(ArrayList<String> data) {
		System.out.println("Data coming in is "+data.get(1));
		login.loginApplication(data.get(1), data.get(2));
		String wrongPass = login.invalidpassWord();
		Assert.assertEquals("The credentials provided are incorrect", wrongPass);
	}
	
	
	 // using Excel to fetch data
	@DataProvider
	public Object[][] getErrorValidationData() throws IOException {
		
		ArrayList<String> data = getExcelData("Login", "ErrorValidation");
		return new Object[][] {{data}};		
	}	
}
