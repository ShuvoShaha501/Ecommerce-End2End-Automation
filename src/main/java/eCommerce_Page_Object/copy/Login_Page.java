package eCommerce_Page_Object.copy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstract_Component.Abstract_Componet;

public class Login_Page extends Abstract_Componet{
	
	WebDriver driver;

	public Login_Page(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(id="userEmail")
	WebElement email;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="div[aria-label='Incorrect email or password.']")
	WebElement loginError;
	
	
	
	
	//driver.findElement(By.id("userEmail")).sendKeys("shuvoshaham@gmail.com");
	//driver.findElement(By.id("userPassword")).sendKeys("Shuvo@123");
	//driver.findElement(By.id("login")).click();
	
	public String ErrorMsg() {
		
		return loginError.getText();
		}
	
	public Product_catalog login(String Email, String pass) {
		
		email.sendKeys(Email);
		password.sendKeys(pass);
		submit.click();
		Product_catalog productCatalog =new Product_catalog(driver);
		return productCatalog;
		}
	
	public void goToUrl() {
		driver.get("https://rahulshettyacademy.com/client");
		}
	

	
}
