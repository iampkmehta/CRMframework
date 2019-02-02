package testscript;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class Keywords 
{
	static ChromeDriver driver;
	static Properties prop;
	static FileInputStream input;
	
	public void openbrowser() throws IOException
	{
		System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		prop = new Properties();
		input = new FileInputStream("F:\\Project\\java project\\CRMframework\\src\\objectreposetry\\objectreposetry.properties");
		prop.load(input);
	}
	public void navigate(String testData)
	{
		driver.get(testData);
	}
	public void input(String testData, String objectName)
	{
		driver.findElement(By.xpath(prop.getProperty(objectName))).sendKeys(testData);
	}
	
	public void click(String objectName)
	{
		driver.findElement(By.xpath(prop.getProperty(objectName))).click();
	}
	
	public String verifyTitle()
	{
		return driver.getTitle();
	}
}