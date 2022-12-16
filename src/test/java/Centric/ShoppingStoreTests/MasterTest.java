package Centric.ShoppingStoreTests;

import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MasterTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Global variables
		String productToBeAdded = "HTC One Mini Blue";
		
		
		// Setting up Web-browser and Drivers
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://centricconsulting.azurewebsites.net/login");
		driver.manage().window().maximize();
		
		// LOGIN PAGE //
		// Login to Centric Shopping Store
		
		driver.findElement(By.id("Email")).sendKeys("seleniumtest@gmail.com");
		driver.findElement(By.id("Password")).sendKeys("seleniumtest");
		driver.findElement(By.className("login-button")).click();
		
		// Clicking Electronics top-menu link
		
		WebElement electronics = driver.findElement(By.xpath("//ul[@class=\"top-menu\"]//a[contains(text(),\"Electronics\")]"));
		electronics.click();
		
		// driver can execute Javascript commands using below - used for scrolling...
		
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		scroll window
//		js.executeScript("window.scrollBy(0,750)");
		
		// ADD PRODUCTS PAGE //
		// Adding HTC One Mini Blue phone to shopping cart
		
		List<WebElement> products = driver.findElements(By.xpath("//div[@class=\"product-grid\"]//div[@class=\"item-box\"]"
				+ "//div[@class=\"details\"]"));
		
		System.out.println("Total products "+ " " +products.size());
		WebElement htcProduct = products.stream().filter(product->product.findElement(By.cssSelector("h2")).
				getText().equals(productToBeAdded)).findFirst().orElse(null);		
		htcProduct.findElement(By.cssSelector(".add-info > div:last-of-type > input")).click();
		
		// Toast displayed to see if the product is added to the card and navigating to shopping cart
		
		// explicitly waiting for the element to appear/disappear	
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#bar-notification > p")));
		driver.findElement(By.cssSelector("#bar-notification a")).click();
		
		
		// SHOPPING CART PAGE //
		// verify the item added to the card is displayed in the shopping cart
		
		List<WebElement> cartProduct = driver.findElements(By.xpath("//*[@class=\"table-wrapper\"]//tbody//*[@class=\"product\"]"));
		Boolean productDisplayed = cartProduct.stream().anyMatch(product -> 
		product.getText().equalsIgnoreCase(productToBeAdded));
		
		Assert.assertTrue(productDisplayed);
		WebElement quantity = driver.findElement(By.xpath("//*[@class=\"table-wrapper\"]//tbody//*[@class=\"quantity\"]/input"));
		quantity.sendKeys(Keys.CONTROL+"A");
		quantity.sendKeys(Keys.BACK_SPACE);
		quantity.sendKeys("1");
		driver.findElement(By.className("update-cart-button")).click();
		
		driver.findElement(By.id("termsofservice")).click();
		driver.findElement(By.id("checkout")).click();
		
		// CHECKOUT PAGE //
		// filling details on the checkout page
		
		// BILLING ADDRESS SECTION //
//		driver.findElement(By.id("BillingNewAddress_CountryId")).sendKeys("United States");
//		driver.findElement(By.id("BillingNewAddress_StateProvinceId")).sendKeys("Ohio");
//		driver.findElement(By.id("BillingNewAddress_City")).sendKeys("Columbus");
//		driver.findElement(By.id("BillingNewAddress_Address1")).sendKeys("Selenium Test Address");
//		driver.findElement(By.id("BillingNewAddress_Address2")).sendKeys("999");
//		driver.findElement(By.id("BillingNewAddress_ZipPostalCode")).sendKeys("43235");
//		driver.findElement(By.id("BillingNewAddress_PhoneNumber")).sendKeys("6148667098");	
		driver.findElement(By.className("new-address-next-step-button")).click();
		
		// SHIPPING ADDRESS SECTION //
		driver.findElement(By.xpath("//*[@id=\"shipping-buttons-container\"]/input")).click();
		
		// SHIPPING METHOD SECTION //
		driver.findElement(By.xpath("//*[@id=\"shipping-method-buttons-container\"]/input")).click();
		
		// PAYMENT INFO SECTION //
		driver.findElement(By.id("CreditCardType")).sendKeys("Discover");
		driver.findElement(By.id("CardholderName")).sendKeys("SeleniumTests");
		driver.findElement(By.id("CardNumber")).sendKeys("6011402234383323");
		driver.findElement(By.id("ExpireMonth")).sendKeys("06");
		driver.findElement(By.id("ExpireYear")).sendKeys("2027");
		driver.findElement(By.id("CardCode")).sendKeys("719");
		driver.findElement(By.className("payment-info-next-step-button")).click();
		
		// CONFIRM ORDER SECTION //
		driver.findElement(By.className("confirm-order-next-step-button")).click();
		
		// ORDER CONFIRMATION PAGE //
		
		String orderConfirmationMsg = driver.findElement(By.xpath("//*[@class=\"section order-completed\"]/div/strong")).getText();
		Assert.assertEquals(orderConfirmationMsg, "Your order has been successfully processed!");
		
		String[] orderNumber = driver.findElement(By.cssSelector(".order-number")).getText().split(":");
		System.out.println("Your order number is " + orderNumber[1].trim());
		
		// closing browser and exiting
		
		//driver.quit();
	}

}
