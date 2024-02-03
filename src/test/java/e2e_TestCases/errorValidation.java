package e2e_TestCases;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import e2e.TestComponent.BaseTest;
import eCommerce_Page_Object.copy.CartPage;
import eCommerce_Page_Object.copy.Product_catalog;

public class errorValidation extends BaseTest{
	@Test(groups={"ErrorHandelling"})
	public void loginErrorValidation() {
		
		loginpage.login("shuvoshaha@gail.com", "asfsadfgsg");
		Assert.assertEquals("Incorrect email or password.", loginpage.ErrorMsg());
	}
	
	
	@Test
	public void CartItemValodation() throws InterruptedException {
		String product="ZARA COAT 3";
		Product_catalog productCatalog=	loginpage.login("shuvoshaham@gmail.com", "Shuvo@123");
		List<WebElement>products=productCatalog.productslist(driver);
		productCatalog.addtocart(product);
		Thread.sleep(1500);
		CartPage cartPage=productCatalog.goToCartPage();
		Boolean cart=cartPage.MatchItem("ZARA COAT 33");		
		Assert.assertFalse(cart);
	}

}
