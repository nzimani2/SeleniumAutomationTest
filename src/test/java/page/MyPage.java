/**
 * 
 */
package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

/**
 * @author imani
 * This class stores all locators and methods of My Page page
 */
public class MyPage {

	private WebDriver driver;

	  public MyPage(WebDriver driver) {
	    this.driver = driver;
	    if (!this.driver.getTitle().startsWith("マイページ")) {
	      throw new IllegalStateException("The current page is incorrect: " + this.driver.getTitle());
	    }
	  }
	  
	  //retrieve page header text
	  public String getHeaderText() {
	    var header = driver.findElement(By.tagName("h2"));
	    return header.getText();
	  }
}
