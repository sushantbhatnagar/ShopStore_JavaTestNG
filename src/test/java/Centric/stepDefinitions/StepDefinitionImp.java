package Centric.stepDefinitions;
import java.io.IOException;

import org.testng.Assert;

import Centric.ShoppingStoreApplication.CheckoutPage;
import Centric.ShoppingStoreApplication.ElectronicsPage;
import Centric.ShoppingStoreApplication.LoginPage;
import Centric.ShoppingStoreApplication.OrderConfirmationPage;
import Centric.ShoppingStoreApplication.ProductCatalogue;
import Centric.ShoppingStoreApplication.ShoppingCart;
import Centric.ShoppingStoreTestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImp extends BaseTest {
	
	public LoginPage login;
	public ProductCatalogue productcatalogue;
	public ElectronicsPage electronics;
	public ShoppingCart cart;
	String count = "1";
	public CheckoutPage checkout;
	public OrderConfirmationPage order;
	String orderNum;

	
	@Given("I landed on the Centric Shopping Store")
	public void I_landed_on_the_Centric_Shopping_Store() throws IOException {
		login = loginToApplication();
	}
	
	
	@Given("^I logged in with username (.+) and password (.+)$")
	public void I_logged_in_with_username_and_password(String username, String password) {
		productcatalogue = login.loginApplication(username, password);

	}
	
	@When("^I add product (.+) from cart$")
	public void I_add_product_to_cart(String productName) {
		electronics = productcatalogue.navigateToElectronicsPage();		
		electronics.addProductToCart(productName);
	}
	
	@And("^I checkout (.+) and submit the order$")
	public void I_checkout_and_submit_order(String productName) {
		cart = electronics.navigateToShoppingCartPage();
		Boolean productDisplayed = cart.productNameDisplaysInCart(productName);
		Assert.assertTrue(productDisplayed);
		cart.updateQuantity(count);
		checkout = cart.navigateToCheckoutPage();
		checkout.confirmOrder();
	}
	
	@Then("{string} message is displayed on the Confirmation page")
	public void I_see_order_confirmation(String orderText) {
		order = checkout.navigateToOrderConfirmationPage();
		String orderConfirmationMsg = order.orderConfirmedMsg();		
		Assert.assertEquals(orderConfirmationMsg, orderText);		
		orderNum = order.retriveOrderNumber();
		System.out.println("Your order number is " + orderNum);	
		driver.close();
	}
	
	
	@Then("{string} error message should be displayed for {string}")
	public void error_message_should_be_displayed_for(String errorMessage, String forwhat){
		
		if(forwhat.equalsIgnoreCase("username")) {
			String wrongID = login.invalidUserID();
			Assert.assertEquals(errorMessage, wrongID);
		}
		else
		{
			String wrongPass = login.invalidpassWord();
			Assert.assertEquals(errorMessage, wrongPass);
		}
		driver.close();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
