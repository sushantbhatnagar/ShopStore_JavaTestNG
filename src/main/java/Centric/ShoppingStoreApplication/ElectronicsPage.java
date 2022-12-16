package Centric.ShoppingStoreApplication;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Centric.ShoppingStoreAbstractComponents.AbstractComponent;

public class ElectronicsPage extends AbstractComponent {
	
	WebDriver driver;
	
	public ElectronicsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//div[@class=\"product-grid\"]//div[@class=\"item-box\"]"
			+ "//div[@class=\"details\"]")
	List<WebElement> products;
	
	By findProduct = By.cssSelector("h2");
	By addToCart = By.cssSelector(".add-info > div:last-of-type > input");
	By shoppingCartToastMsg = By.cssSelector("#bar-notification > p");
	
	
	@FindBy(css= "#bar-notification a")
	WebElement shoppingCartNotification;
		
	
	public WebElement getProductByName(String productName) {
		WebElement htcProduct = products.stream().filter(product->product.findElement(findProduct).
								getText().equals(productName)).findFirst().orElse(null);
		return htcProduct;
		
	}
	
	public void addProductToCart(String productName ) {
		WebElement product = getProductByName(productName);
		product.findElement(addToCart).click();
	}
	
	public ShoppingCart navigateToShoppingCartPage() {
		waitForElementToAppear(shoppingCartToastMsg);
		shoppingCartNotification.click();
		return new ShoppingCart(driver);
	}
}
