package Centric.ShoppingStoreTests;

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
	
	
}
