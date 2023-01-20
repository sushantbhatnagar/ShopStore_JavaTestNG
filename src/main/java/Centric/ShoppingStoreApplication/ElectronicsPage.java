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
	By addToWishlist = By.cssSelector(".add-info > div:last-of-type > input:nth-child(3)");
	By addToCompare = By.cssSelector(".add-info > div:last-of-type > input:nth-child(2)");
	By shoppingCartToastMsg = By.cssSelector("#bar-notification > p");

	
	@FindBy(css= "#bar-notification a")
	WebElement shoppingCartNotification;
	
	@FindBy(xpath="//*[@id=\"bar-notification\"]/span")
	WebElement closeToastMessage;
	
	
	public WebElement getProductByName(String productName) {
		WebElement htcProduct = products.stream().filter(product->product.findElement(findProduct).
								getText().equals(productName)).findFirst().orElse(null);
		return htcProduct;
		
	}
	
	
	public void addProductToCart(String productName) {
		WebElement product = getProductByName(productName);
		product.findElement(addToCart).click();
	}
	
	
	public void addProductToWishlist(String productToWishFor) {
		WebElement wishlistProduct = getProductByName(productToWishFor);
		wishlistProduct.findElement(addToWishlist).click();	
	}
	
	
	public void addProductToCompare(String productToCompare) {
		WebElement prodToCompare = getProductByName(productToCompare);
		prodToCompare.findElement(addToCompare).click();
	}
	
	
	public ShoppingCart navigateToShoppingCartPage() {
		waitForElementToAppear(shoppingCartToastMsg);
		shoppingCartNotification.click();
		return new ShoppingCart(driver);
	}
	
	
	public Wishlist navigateToWishListPage() {
		waitForElementToAppear(shoppingCartToastMsg);
		shoppingCartNotification.click();
		return new Wishlist(driver);
	}
	
	public Comparision navigateToComparisionPage() {
		waitForElementToAppear(shoppingCartToastMsg);
		shoppingCartNotification.click();
		return new Comparision(driver);
	}

	
	
	
}
