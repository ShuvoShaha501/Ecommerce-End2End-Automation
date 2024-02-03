package eCommerce_Page_Object.copy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import abstract_Component.Abstract_Componet;

public class Checkout_page extends Abstract_Componet{
	
	WebDriver driver;
	public Checkout_page(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[placeholder*='Select Country']")
	WebElement CnameField;
	
	
	@FindBy(css="[class*='ta-results'] button")
	WebElement Select;
	
	@FindBy(css="[class*='action__submit']")
	WebElement place;
	
	
	By result=By.cssSelector("span[class='ng-star-inserted']");
	By sucesstext= By.cssSelector(".hero-primary");

	
	public void countryName(String Cname)
	{
		CnameField.sendKeys(Cname);
		waitToReady(result);
		Select.click();
	}
	
	
	public Confirmation_page placeOrder() {
		
		place.click();
		waitToReady(sucesstext);
		Confirmation_page confirmation_page= new Confirmation_page(driver);
		return confirmation_page;
	}
	

}
