package Centric.ShoppingStoreTests;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Centric.ShoppingStoreApplication.CheckoutPage;
import Centric.ShoppingStoreApplication.ElectronicsPage;
import Centric.ShoppingStoreApplication.LoginPage;
import Centric.ShoppingStoreApplication.OrderConfirmationPage;
import Centric.ShoppingStoreApplication.OrderHistory;
import Centric.ShoppingStoreApplication.ProductCatalogue;
import Centric.ShoppingStoreApplication.ShoppingCart;
import Centric.ShoppingStoreTestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
	
	//String productName = "HTC One Mini Blue";
	String count = "1";
	String orderNum;

	@Test(dataProvider="getData", groups= {"Purchase"})
	//public void submitOrder(String email, String pass, String productName) throws IOException, InterruptedException  {
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException  {
		
		// Global variables				
		// Setting up Web-browser and Drivers	
		
		// LOGIN PAGE /	
		// Login to Centric Shopping Store
		//LoginPage login = loginToApplication();
		/*
		LoginPage login = new LoginPage(driver);
		login.goTo();
		*/
		ProductCatalogue productcatalogue = login.loginApplication(input.get("email"), input.get("pass"));
		
		// Clicking Electronics top-menu link
		//ProductCatalogue pc = new ProductCatalogue(driver);
		ElectronicsPage electronics = productcatalogue.navigateToElectronicsPage();
		
		// driver can execute Javascript commands using below - used for scrolling...
		
		// JavascriptExecutor js = (JavascriptExecutor) driver;
		// scroll window
		// js.executeScript("window.scrollBy(0,750)");
		
		// ADD PRODUCTS PAGE //
		// Adding HTC One Mini Blue phone to shopping cart
		
		electronics.addProductToCart(input.get("productName"));
		ShoppingCart cart = electronics.navigateToShoppingCartPage();
		
		
		// SHOPPING CART PAGE //
		// verify the item added to the card is displayed in the shopping cart		
		//ShoppingCart cart = new ShoppingCart(driver);
		Boolean productDisplayed = cart.productNameDisplaysInCart(input.get("productName"));
		Assert.assertTrue(productDisplayed);
		cart.updateQuantity(count);
		CheckoutPage checkout = cart.navigateToCheckoutPage();

		
		// CHECKOUT PAGE //
		// filling details on the checkout page
		
		
		//CheckoutPage checkout = new CheckoutPage(driver);
		checkout.confirmOrder();
		OrderConfirmationPage order = checkout.navigateToOrderConfirmationPage();
		
		// ORDER CONFIRMATION PAGE //
			
		//OrderConfirmationPage order = new OrderConfirmationPage(driver);
		String orderConfirmationMsg = order.orderConfirmedMsg();		
		Assert.assertEquals(orderConfirmationMsg, "Your order has been successfully processed!");
		
		orderNum = order.retriveOrderNumber();
		System.out.println("Your order number is " + orderNum);
	
	}
	
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderNumberHistory() {
		
		ProductCatalogue productcatalogue = login.loginApplication("seleniumtest@gmail.com", "seleniumtest");
		OrderHistory ordershistory = productcatalogue.navigateToOrderHistoryPage();
		ordershistory.findourOrder(orderNum);
	}
	
	// Data Provider to pass data
//	@DataProvider
//	public Object[][] getData() {
//		
//		return new Object[][] {{"seleniumtest@gmail.com", "seleniumtest", "HTC One Mini Blue"},{"seleniumtest2@gmail.com", "seleniumtest2", "Beats Pill 2.0 Wireless Speaker"}};
//	}
	
	
	// Hash map to create or pass data 
//	@DataProvider
//	public Object[][] getData() {
//		HashMap<String,String> map = new HashMap<String, String>();
//		map.put("email", "seleniumtest@gmail.com");
//		map.put("pass", "seleniumtest");
//		map.put("productName", "HTC One Mini Blue");
//		
//		HashMap<String,String> map2 = new HashMap<String, String>();
//		map2.put("email", "seleniumtest2@gmail.com");
//		map2.put("pass", "seleniumtest2");
//		map2.put("productName", "Beats Pill 2.0 Wireless Speaker");
//		
//		return new Object[][] {{map}, {map2}};
//	}
	
	// using JSON now to put the data
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+
				"//src//test//java//Centric//ShoppingStoreTestData//purchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
		
	}
	
	
	
	
	
	
	
}
