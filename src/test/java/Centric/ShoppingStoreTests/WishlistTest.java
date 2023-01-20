package Centric.ShoppingStoreTests;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Centric.ShoppingStoreApplication.ElectronicsPage;
import Centric.ShoppingStoreApplication.ProductCatalogue;
import Centric.ShoppingStoreApplication.Wishlist;
import Centric.ShoppingStoreTestComponents.BaseTest;

public class WishlistTest extends BaseTest {
	
	//String productToWishFor = "HTC One M8 Android L 5.0 Lollipop";
	
	@Test(dataProvider="getHTCWish")
	public void addProductToWishlist(ArrayList<String> data) {		
		ProductCatalogue pc = login.loginApplication("seleniumtest@gmail.com", "seleniumtest");
		ElectronicsPage ep = pc.navigateToElectronicsPage();
		ep.addProductToWishlist(data.get(1));
		Wishlist wish = ep.navigateToWishListPage();
		Boolean wishNoted = wish.wishedProduct(data.get(1));
		Assert.assertTrue(wishNoted);	
	}
	
	// using Excel to fetch data
	@DataProvider
	public Object[][] getHTCWish() throws IOException {		
		ArrayList<String> data = getExcelData("Wishlist", "HTCWishList");
		return new Object[][] {{data}};		
	}	
	
}
