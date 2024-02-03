package eCommerce_Page_Object.copy;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstract_Component.Abstract_Componet;

public class CartPage extends Abstract_Componet{

	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[class='cartSection'] h3")
	private List <WebElement> items;
	
	@FindBy(css="[class*='subtotal'] button")
	WebElement Checkout;
	
	By CountryName =By.cssSelector("[class*='ta-results']");
	
	public Boolean MatchItem(String product)
	{		
		Boolean cart=items.stream().anyMatch(sku->sku.getText().equalsIgnoreCase(product));
		return cart;
	}
	
	public Checkout_page goToCheckout() {
		Checkout.click();
		Checkout_page checkoutpage=new Checkout_page(driver);
		return checkoutpage;
		
	}
	
	

}
