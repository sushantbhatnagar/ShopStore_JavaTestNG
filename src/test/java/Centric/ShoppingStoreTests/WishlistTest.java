package Centric.ShoppingStoreTests;

import org.junit.Assert;
import org.testng.annotations.Test;

import Centric.ShoppingStoreApplication.ElectronicsPage;
import Centric.ShoppingStoreApplication.ProductCatalogue;
import Centric.ShoppingStoreApplication.Wishlist;
import Centric.ShoppingStoreTestComponents.BaseTest;

public class WishlistTest extends BaseTest {
	
	String productToWishFor = "HTC One M8 Android L 5.0 Lollipop";
	
	@Test
	public void addProductToWishlist() {		
	ProductCatalogue pc = login.loginApplication("seleniumtest@gmail.com", "seleniumtest");
	ElectronicsPage ep = pc.navigateToElectronicsPage();
	ep.addProductToWishlist(productToWishFor);
	Wishlist wish = ep.navigateToWishListPage();
	Boolean wishNoted = wish.wishedProduct(productToWishFor);
	Assert.assertTrue(wishNoted);	
	}
}
