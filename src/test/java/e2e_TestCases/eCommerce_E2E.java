package e2e_TestCases;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.xml.sax.Locator;

import e2e.TestComponent.BaseTest;
import eCommerce_Page_Object.copy.CartPage;
import eCommerce_Page_Object.copy.Checkout_page;
import eCommerce_Page_Object.copy.Confirmation_page;
import eCommerce_Page_Object.copy.Login_Page;
import eCommerce_Page_Object.copy.OrderPage;
import eCommerce_Page_Object.copy.Product_catalog;

public class eCommerce_E2E extends BaseTest{
	String product="ZARA COAT 3";
	
	@Test(dataProvider= "getData", groups= {"purchase"})
public void SubmitOrder(HashMap <String, String>input) throws IOException, InterruptedException {		
		
		Product_catalog productCatalog=	loginpage.login(input.get("email"), input.get("password"));
		List<WebElement>products=productCatalog.productslist(driver);
		productCatalog.addtocart(input.get("product"));
		Thread.sleep(1500);
		CartPage cartPage=productCatalog.goToCartPage();
		Boolean cart=cartPage.MatchItem(input.get("product"));		
		Assert.assertTrue(cart);
		Thread.sleep(1000);
		Checkout_page checkoutpage=cartPage.goToCheckout();	
		checkoutpage.countryName("Bangladesh");
		driver.findElement(By.name("coupon")).sendKeys("shuvo");
		Thread.sleep(2000);
		Confirmation_page confirmation_page=checkoutpage.placeOrder();
		confirmation_page.confirmationMsg();
		String sucess=confirmation_page.confirmationMsg();
		Assert.assertEquals(sucess, "THANKYOU FOR THE ORDER.");

	}
	
	@Test(dependsOnMethods= {"SubmitOrder"})
	public void verifyOrderPage() {
		
		Product_catalog productCatalog=loginpage.login("shuvoshaham@gmail.com","Shuvo@123");
	    OrderPage orderpage  =	productCatalog.GoToOrderPage();
		Assert.assertTrue(orderpage.VerifyOrderDisplay(product));
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String, String>> data=getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\e2e_Data\\purchase.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
	}
}
