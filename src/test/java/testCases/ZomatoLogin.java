package testCases;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

public class ZomatoLogin {
	WebDriver driver;
	Properties prop;
	FileInputStream instream;

	@BeforeTest
	public void setup() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	@BeforeClass
	public void property() throws IOException {
		prop = new Properties();
		instream = new FileInputStream(System.getProperty("user.dir") + "//Field.properties");
		prop.load(instream);
	}

	@Parameters({ "phoneNo" })
	@Test
	public void login(String phoneNo) {
		driver.get(prop.getProperty("loginURL"));
		driver.manage().window().maximize();
		driver.findElement(By.linkText(prop.getProperty("login"))).click();
		System.out.println(phoneNo);
		WebElement otpButton = driver.findElement(By.xpath(prop.getProperty("otpButton")));
		driver.findElement(By.xpath(prop.getProperty("phone"))).sendKeys(phoneNo);
//		if(otpButton.isEnabled())
//		{
//			System.out.println(phoneNo+" :- Login Succesfull");
//		}
//		else
//		{
//			System.out.println(phoneNo+" :- unable to login");
//		}
		assertEquals(true, otpButton.isEnabled());
	}

	@AfterTest
	public void close() {
		driver.close();
	}

}
