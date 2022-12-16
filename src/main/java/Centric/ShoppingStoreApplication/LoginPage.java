package Centric.ShoppingStoreApplication;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Centric.ShoppingStoreAbstractComponents.AbstractComponent;

public class LoginPage extends AbstractComponent {
	
	WebDriver driver;
	
	// constructor to initialize the elements
	public LoginPage(WebDriver driver) {
		super(driver);
		// local variable assigned the driver from the object of loginPage
		this.driver=driver;
		PageFactory.initElements(driver, this);
	} 
	
	
	// PageFactory DesignPattern		
	@FindBy(id="Email")
	WebElement userID;
	
	@FindBy(id="Password")
	WebElement password;
	
	@FindBy(className="login-button")
	WebElement loginButton;
	
	@FindBy(css=".validation-summary-errors>span")
	WebElement invalidUser;
	
	@FindBy(xpath=".//*[@class=\"message-error\"]//ul/li")
	WebElement invalidPassword;
	
	public ProductCatalogue loginApplication(String email, String pass) {
		userID.sendKeys(email);
		password.sendKeys(pass);
		loginButton.click();
		return new ProductCatalogue(driver);
	}
	
	public String invalidUserID() {
		return invalidUser.getText();
	}
	
	public String invalidpassWord() {
		return invalidPassword.getText();
	}
	
	
	public void goTo() {
		driver.get("http://centricconsulting.azurewebsites.net/login");
		
	}

	
	
	

}
