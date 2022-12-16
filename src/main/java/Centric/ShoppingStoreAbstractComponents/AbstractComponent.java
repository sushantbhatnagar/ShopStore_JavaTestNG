package Centric.ShoppingStoreAbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {
	
	WebDriver driver;
	
	@FindBy(xpath="//ul[@class=\"top-menu\"]//a[contains(text(),\"Electronics\")]")
	public WebElement electronicsPage;
	
	@FindBy(xpath="//*[@class=\"footer-block my-account\"]//a[text()=\"Orders\"]")
	public WebElement orderHistory;
	
	
	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
	}
	
	
	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#bar-notification > p")));
	}
	
	
	public void scrollWindowBy(Integer x, Integer y) {
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollBy(String.valueOf(x),String.valueOf(y))");
	}
	
	
	
	
	
	
}
