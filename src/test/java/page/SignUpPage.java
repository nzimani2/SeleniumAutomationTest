/**
 * 
 */
package page;

import java.time.LocalDate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;

/**
 * @author imani
 * This class stores all locators and methods of Sign Up page
 */
public class SignUpPage {

	private WebDriver driver;
	
	public SignUpPage(WebDriver driver) {
		this.driver = driver;
		if(!this.driver.getTitle().startsWith("会員登録")) {
			throw new IllegalStateException("The current page is incorrect: " + this.driver.getTitle());
		}
	}
	
	public void assignEmailVal(String email) {
		var emailField = driver.findElement(By.id("email"));
		emailField.clear();
		emailField.sendKeys(email);
	}
	
	public void assignPasswordVal(String password) {
		var pswdField = driver.findElement(By.id("password"));
		pswdField.clear();
		pswdField.sendKeys(password);
	}
	
	public void assignPasswordConfVal(String password) {
		var pswdField = driver.findElement(By.id("password-confirmation"));
		pswdField.clear();
		pswdField.sendKeys(password);
	}
	
	public void assignUsernameVal(String username) {
		var usernameField = driver.findElement(By.id("username"));
		usernameField.clear();
		usernameField.sendKeys(username);
	}
	
	public void assignMembershipType(String membership) {
		if(membership == "プレミアム会員") {
			var premium = driver.findElement(By.id("rank-premium"));
			premium.click();
		}else {
			var normal = driver.findElement(By.id("rank-normal"));
			normal.click();
		}
	}
	
	public void assignAddressVal(String address) {
		var addressField = driver.findElement(By.id("address"));
		addressField.clear();
		addressField.sendKeys(address);
	}
	
	public void assignTelVal(String telNo) {
		var telNoField = driver.findElement(By.id("tel"));
		telNoField.clear();
		telNoField.sendKeys(telNo);
	}
	
	public void assignGenderVal(String gender) {
		var genderField = new Select(driver.findElement(By.id("gender")));
		genderField.selectByValue(gender);
	}
	
	public void assignBirthdayVal(LocalDate birthday) {
	    var input = birthday != null ? birthday.toString() : "";
	    var birthdayInput = driver.findElement(By.id("birthday"));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1]", birthdayInput, input);
	}
	
	public void assignNotificationChk(boolean notification) {
	    var notificationField = driver.findElement(By.id("notification"));
	    if (notificationField.isSelected() != notification) {
	    	notificationField.click();
	    }
	}
	
	public MyPage registerOnClick() {
	    var signupBtn = driver.findElement(By.cssSelector("#signup-form > button"));
	    signupBtn.click();
	    return new MyPage(driver);
	}
	
	public void registerOnClickFailed() {
	    var signupBtn = driver.findElement(By.cssSelector("#signup-form > button"));
	    signupBtn.click();
	}
	
	
	public String getEmailInvalidMsg() {
		var invalidMsg = driver.findElement(By.cssSelector("#email ~ .invalid-feedback"));
	    return invalidMsg.getText();
	}
	
	public String getPasswordInvalidMsg() {
	    var invalidMsg = driver.findElement(By.cssSelector("#password ~ .invalid-feedback"));
	    return invalidMsg.getText();
	}
	
	public String getPasswordConfInvalidMsg() {
	    var invalidMsg = driver.findElement(By.cssSelector("#password-confirmation ~ .invalid-feedback"));
	    return invalidMsg.getText();
	}
	
	public String getUsernameInvalidMsg() {
	    var invalidMsg = driver.findElement(By.cssSelector("#username ~ .invalid-feedback"));
	    return invalidMsg.getText();
	}
	
	public String getTelInvalidMsg() {
	    var invalidMsg = driver.findElement(By.cssSelector("#tel ~ .invalid-feedback"));
	    return invalidMsg.getText();
	}
	
}
