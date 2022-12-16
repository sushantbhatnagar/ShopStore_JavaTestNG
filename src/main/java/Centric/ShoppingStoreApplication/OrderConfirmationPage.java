package Centric.ShoppingStoreApplication;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Centric.ShoppingStoreAbstractComponents.AbstractComponent;

public class OrderConfirmationPage extends AbstractComponent {
	
	
	WebDriver driver;
	public String[] orderID;
	
	public OrderConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@class=\"section order-completed\"]/div/strong")
	WebElement orderConfirmationMsg;
	
	@FindBy(css=".order-number")
	WebElement orderNumber;
	
		
	public String orderConfirmedMsg() {
		return orderConfirmationMsg.getText();	
	}
	
	public String retriveOrderNumber() {
		orderID = orderNumber.getText().split(":");
		return orderID[1].trim();
	}	
}
