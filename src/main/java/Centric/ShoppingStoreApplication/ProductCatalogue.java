package Centric.ShoppingStoreApplication;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import Centric.ShoppingStoreAbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public ElectronicsPage navigateToElectronicsPage() {
		electronicsPage.click();
		return new ElectronicsPage(driver);
	}
	
	public OrderHistory navigateToOrderHistoryPage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		orderHistory.click();
		return new OrderHistory(driver); 
	}
	

}
