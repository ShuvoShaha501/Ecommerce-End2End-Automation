package eCommerce_Page_Object.copy;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstract_Component.Abstract_Componet;

public class Product_catalog extends Abstract_Componet{
	
	WebDriver driver;

	public Product_catalog(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
		
	
//List<WebElement>products=driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	
	
	By WaitElement=By.cssSelector(".mb-3");
	By addcart=By.cssSelector(".card-body button:last-of-type");
	By toastmsg= By.id("toast-container");
	
	public List<WebElement> productslist(WebDriver driver) {
		
		waitToReady(WaitElement);
		return products;
		
		}
	
	public WebElement cartproduct(String product) 
	{
		WebElement cartpro=	products.stream().filter(addcart->addcart.
				findElement(By.cssSelector("b")).getText().
					equals(product)).findFirst().orElse(null);
		
		return cartpro;
	}
	
	public void addtocart(String product)
	{
		WebElement cartpro=cartproduct(product);
		cartpro.findElement(addcart).click();
		waitToReady(toastmsg);
		//waitToVanish(spinner);
	}
	

	
}
