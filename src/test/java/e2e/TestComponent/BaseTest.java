package e2e.TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import eCommerce_Page_Object.copy.Login_Page;

public class BaseTest {
	public WebDriver driver;
	public Login_Page loginpage;
	public WebDriver installDriver() throws IOException {
		
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//src//main//java//e2e//resources//GlobalData.properties");
		prop.load(fis);	
		String browerName=prop.getProperty("browser");
		if(browerName.equals("chrome")) {
			
			 driver=new ChromeDriver();
			
		}else if(browerName.equalsIgnoreCase("firefox")) {
			 driver=new FirefoxDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    driver.manage().window().maximize();
	    return driver;
	}
	
	//Take ScreenShot
	public String getScreenShot(String TestName, WebDriver driver) throws IOException {
		
		TakesScreenshot scrnShot=((TakesScreenshot)driver);
		File source=scrnShot.getScreenshotAs(OutputType.FILE);
	    File file= new File(System.getProperty("user.dir")+"//reports//"+TestName+".png");
		FileUtils.copyFile(source, file);
		return (System.getProperty("user.dir")+"//reports//"+TestName+".png");
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String FilePath) throws IOException {
		
		String jsonToString=FileUtils.readFileToString(new File(FilePath),StandardCharsets.UTF_8);
		
		ObjectMapper maper=new ObjectMapper();
		
			List<HashMap<String, String>> data=maper.readValue(jsonToString,new TypeReference<List<HashMap<String, String>>>(){});
			return data;
		}
	
	@BeforeMethod(alwaysRun=true)
	public Login_Page launchApp() throws IOException {
		
		driver= installDriver();
		loginpage= new Login_Page(driver);
		loginpage.goToUrl();
		return loginpage;
		
		}
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}

}
