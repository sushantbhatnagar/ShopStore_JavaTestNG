package Centric.ShoppingStoreApplication;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Centric.ShoppingStoreAbstractComponents.AbstractComponent;

public class Comparision extends AbstractComponent {
	
	WebDriver driver;
	
	public Comparision(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//*[@class=\"compare-products-table\"]//tr[@class=\"product-name\"]//a")
	List<WebElement> comparedProducts;
	
	
	public Boolean comparedProductFound(String product) {
		Boolean prodFound = comparedProducts.stream().anyMatch(prod -> prod.getText().equalsIgnoreCase(product));
		System.out.println("Product "+product+" is available for comparision");
		return prodFound;	
	}
}
