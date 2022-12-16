package Centric.ShoppingStoreApplication;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Centric.ShoppingStoreAbstractComponents.AbstractComponent;

public class OrderHistory extends AbstractComponent {
	
	WebDriver driver;
	
	public OrderHistory(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);		
	}
	
	
	@FindBy(xpath="//*[@class=\"section order-item\"]/div[@class=\"title\"]")
	List<WebElement> orderIDs;
	
	public void findourOrder(String orderNum) {
		orderIDs.stream().anyMatch(order->
		order.getText().equalsIgnoreCase("Order Number: "+ orderNum));
		System.out.println("Your order "+orderNum+" was found on Order History page");
		
	}	
}
