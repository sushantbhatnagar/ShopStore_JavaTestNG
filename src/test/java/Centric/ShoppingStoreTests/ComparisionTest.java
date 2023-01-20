package Centric.ShoppingStoreTests;

import org.junit.Assert;
import org.testng.annotations.Test;

import Centric.ShoppingStoreApplication.Comparision;
import Centric.ShoppingStoreApplication.ElectronicsPage;
import Centric.ShoppingStoreApplication.ProductCatalogue;
import Centric.ShoppingStoreTestComponents.BaseTest;

public class ComparisionTest extends BaseTest {
	
	String HTCOneM8 = "HTC One M8 Android L 5.0 Lollipop";
	String HTCOneMini = "HTC One Mini Blue";
	
	@Test
	public void addProductsToCompare() {
		
		ProductCatalogue pc = login.loginApplication("seleniumtest@gmail.com", "seleniumtest");
		ElectronicsPage ep = pc.navigateToElectronicsPage();
		ep.addProductToCompare(HTCOneM8);
		ep.addProductToCompare(HTCOneMini);
		Comparision compare = ep.navigateToComparisionPage();
		Boolean prod1 = compare.comparedProductFound(HTCOneM8);
		Assert.assertTrue(prod1);
		Boolean prod2 = compare.comparedProductFound(HTCOneMini);
		Assert.assertTrue(prod2);		
	}
}
