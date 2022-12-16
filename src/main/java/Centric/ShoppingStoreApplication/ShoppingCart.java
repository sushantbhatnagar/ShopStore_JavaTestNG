package Centric.ShoppingStoreApplication;

import java.util.List;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Centric.ShoppingStoreAbstractComponents.AbstractComponent;

public class ShoppingCart extends AbstractComponent {
	
	WebDriver driver;
	
	public ShoppingCart(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@class=\"table-wrapper\"]//tbody//*[@class=\"product\"]")
	List<WebElement> allProductsInCart;
	
	@FindBy(xpath="//*[@class=\"table-wrapper\"]//tbody//*[@class=\"quantity\"]/input")
	WebElement quantity;
	
	@FindBy(className="update-cart-button")
	WebElement updateQuantity;
	
	@FindBy(id="termsofservice")
	WebElement termsOfService;
	
	@FindBy(id="checkout")
	WebElement checkoutButton;
	
	public Boolean productNameDisplaysInCart(String productName) {
		Boolean productDisplayed = allProductsInCart.stream().anyMatch(product 
				-> product.getText().equalsIgnoreCase(productName));
		return productDisplayed;
	}
	
	
	public void updateQuantity(String count) {
		quantity.sendKeys(Keys.CONTROL+"A");
		quantity.sendKeys(Keys.BACK_SPACE);
		quantity.sendKeys(count);
		updateQuantity.click();
	}
	
	public CheckoutPage navigateToCheckoutPage() {
		termsOfService.click();
		checkoutButton.click();
		return new CheckoutPage(driver);
	}
	
}
