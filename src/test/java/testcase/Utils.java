/**
 * 
 */
package testcase;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author imani
 * This class store the method to define Web Driver etc.
 */
public class Utils {
	public static final String HOME_URL = "http://hotel.testplanisphere.dev/ja/signup.html";
	
	public static WebDriver initWebDriver() {
		//driver executable setting
		System.setProperty("webdriver.chrome.driver", "..\\SeleniumProject\\chromedriver.exe");
		//initialize Chrome driver
		var driver = new ChromeDriver();
		//apply wait time
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//maximize window
		driver.manage().window().maximize();
		return driver;
	}
}

