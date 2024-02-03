package abstract_Component;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import eCommerce_Page_Object.copy.CartPage;
import eCommerce_Page_Object.copy.OrderPage;

public class Abstract_Componet {
	
	WebDriver driver;
	public Abstract_Componet(WebDriver driver) {
		this.driver=driver;
		
	}

	@FindBy(css="[routerlink*='/dashboard/cart']")
	WebElement CartIcon;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderspage;
	By cartpage= By.cssSelector("[routerlink*='/dashboard/cart']");
	
	public void waitToReady(By FindBy) {
		
		WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));

	}
	

	public OrderPage GoToOrderPage() {
		
		  orderspage.click();
		OrderPage orderpage=new OrderPage(driver);
		return orderpage;
		}
	
	public CartPage goToCartPage() 
	{
		//waitToReady(cartpage);
		CartIcon.click();
		CartPage cartPage=new CartPage(driver);
		return  cartPage;
	}
	
	public void waitToVanish(WebElement vanish) {
		
		WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(vanish));
	}
	
}
