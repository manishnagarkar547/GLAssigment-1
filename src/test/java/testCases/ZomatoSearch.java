package testCases;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ZomatoSearch {

	WebDriver driver;
	Properties prop;
	FileInputStream instream;

	@BeforeTest
	public void setup() throws IOException {
		driver = new FirefoxDriver();
		prop = new Properties();
		instream = new FileInputStream(System.getProperty("user.dir") + "//Field.properties");
		prop.load(instream);
		driver.get(prop.getProperty("navigateURL"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	@Test(dataProvider = "DP")
	public void search(String search) throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.xpath(prop.getProperty("search"))).sendKeys(search);
		driver.findElement(By.xpath(prop.getProperty("search"))).click();
		// driver.findElements(By.xpath("//div[@class='sc-kNBZmU
		// eCoHZP']")).get(0).click();
		driver.findElement(By.xpath(prop.getProperty("orderNow"))).click();
		// driver.findElement(By.xpath("//*[text()='View all outlets' or text()='Order
		// Now' or text()='Currently not accepting orders']")).click();
		System.out.println(driver.getTitle());

	}

//	@DataProvider(name = "DP")
//	public Object[][] dataProvider() {
//		return new Object[][] { { "Haldiram" }, { "McDonald's" }, { "Burger King" }, { "Behrouz Biryani" } };
//	}

	@DataProvider(name = "DP")
	public Object[][] dataProvider() throws IOException {

		utilities.DataProvider data = new utilities.DataProvider();
		data.setExcelFile(prop.getProperty("excel"), prop.getProperty("sheet"));
		int count = data.getRowCountInSheet() + 1;
		System.out.println(count);
		Object[][] obj = new Object[count][1];
		for (int i = 0; i < count; i++) {
			System.out.println(data.getCellData(i, 0));
			obj[i][0] = data.getCellData(i, 0);
		}
		// System.out.println(Arrays.toString(obj[]));
		return obj;

	}

	@AfterTest
	public void close() {
		driver.close();
	}

}
