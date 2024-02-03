package eCommerce_Page_Object.copy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstract_Component.Abstract_Componet;

public class Confirmation_page extends Abstract_Componet {

	WebDriver driver;
	public Confirmation_page(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement Text;
	
	
	public String confirmationMsg()
	{
		String sucess=Text.getText();
		return sucess;
	}
	
	
}

