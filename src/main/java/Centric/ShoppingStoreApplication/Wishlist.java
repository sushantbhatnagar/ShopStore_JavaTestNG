package Centric.ShoppingStoreApplication;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Centric.ShoppingStoreAbstractComponents.AbstractComponent;

public class Wishlist extends AbstractComponent {
	
	WebDriver driver;
	
	public Wishlist(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath=".//*[@class=\"cart\"]/tbody//*[@class=\"product\"]")
	List<WebElement> wishProducts;
	

	public Boolean wishedProduct(String productToWishFor) {
		Boolean wishNoted = wishProducts.stream().anyMatch(wishedProd -> wishedProd.getText().equalsIgnoreCase(productToWishFor));
		System.out.println("Wished Product "+productToWishFor+" was found on Wishlist Page");
		return wishNoted;
	}	

}
