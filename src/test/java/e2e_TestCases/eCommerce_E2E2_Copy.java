package e2e_TestCases;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.xml.sax.Locator;

import eCommerce_Page_Object.copy.CartPage;
import eCommerce_Page_Object.copy.Login_Page;
import eCommerce_Page_Object.copy.Product_catalog;

public class eCommerce_E2E2_Copy {
	
	public static void main (String[]arg) throws InterruptedException {
		
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		String product="ZARA COAT 3";
		Login_Page loginpage= new Login_Page(driver);
		loginpage.goToUrl();
		Product_catalog productCatalog=loginpage.login("shuvoshaham@gmail.com", "Shuvo@123");
		List<WebElement>products=productCatalog.productslist(driver);
		productCatalog.addtocart(product);
		Thread.sleep(1500);
		CartPage cartPage=productCatalog.goToCartPage();
		Boolean cart=cartPage.MatchItem(product);		
		Assert.assertTrue(cart);
		
		driver.findElement(By.cssSelector("[class*='subtotal'] button")).click();
		
		driver.findElement(By.cssSelector("[placeholder*='Select Country']")).sendKeys("India");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class*='ta-results']")));
		driver.findElement(By.cssSelector("[class*='ta-results'] button")).click();

		Thread.sleep(1500);
		WebElement	submit=driver.findElement(By.cssSelector("[class*='action__submit']"));
		submit.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
		String sucess=driver.findElement(By.cssSelector(".hero-primary")).getText();
		
		Assert.assertEquals(sucess, "THANKYOU FOR THE ORDER.");
	
	
	}
}
