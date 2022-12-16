package Centric.ShoppingStoreApplication;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Centric.ShoppingStoreAbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(className="new-address-next-step-button")
	WebElement billingAddress;
		
	@FindBy(xpath="//*[@id=\"shipping-buttons-container\"]/input")
	WebElement shippingAddress;
	
	@FindBy(xpath="//*[@id=\"shipping-method-buttons-container\"]/input")
	WebElement shippingMethod;
	
	@FindBy(id="CreditCardType")
	WebElement creditCardType;
	
	@FindBy(id="CardholderName")
	WebElement cardHolderName;
	
	@FindBy(id="CardNumber")
	WebElement cardNumber;
	
	@FindBy(id="ExpireMonth")
	WebElement expireMonth;
	
	@FindBy(id="ExpireYear")
	WebElement expireYear;
	
	@FindBy(id="CardCode")
	WebElement cardCode;
	
	@FindBy(className="payment-info-next-step-button")
	WebElement paymentInfoCompleted;
	
	@FindBy(className="confirm-order-next-step-button")
	WebElement confirmOrder;
	
	
	public void confirmOrder() {
		billingAddress.click();
		shippingAddress.click();
		shippingMethod.click();
		paymentinfo();			
	}
		
	public void paymentinfo() {
		creditCardType.sendKeys("Discover");
		cardHolderName.sendKeys("SeleniumTests");
		cardNumber.sendKeys("6011402234383323");
		expireMonth.sendKeys("06");
		expireYear.sendKeys("2027");
		cardCode.sendKeys("719");
		paymentInfoCompleted.click();		
	}
	
	public OrderConfirmationPage navigateToOrderConfirmationPage() {
		confirmOrder.click();
		return new OrderConfirmationPage(driver);
	}

}
