/**
 * 
 */
package testcase;

import page.SignUpPage;
import static org.junit.jupiter.api.Assertions.*;
import static testcase.Utils.HOME_URL;
import java.time.LocalDate;
import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.*;


/**
 * @author imani
 * This class stores all the test cases for Sign Up page, with Utility implemented
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class SignUpTest {

	private static WebDriver driver;
	
	@BeforeAll
	//initialize ChromeDriver
	static void initiate() {
		driver = Utils.initWebDriver();
	}
	
	@AfterEach
	//Clear cookies before the beginning of each test
	void deleteCookies() {
		driver.manage().deleteAllCookies();
	}
	
	@AfterAll
	//quit driver and close browser
	public void tearDownAll() {
		driver.quit();
	}
	
	@Test
	@Order(1)
	@DisplayName("To verify success registration of user")
	//TC_001
	public void testSignupSuccess() {
		driver.get(HOME_URL);
		
		var signUpObj = new SignUpPage(driver);
		signUpObj.assignEmailVal("user1@test.com");
		signUpObj.assignPasswordVal("test1234");
		signUpObj.assignPasswordConfVal("test1234");
		signUpObj.assignUsernameVal("user1");
		signUpObj.assignMembershipType("一般会員");
		signUpObj.assignAddressVal("東京都江戸川区船堀");
		signUpObj.assignTelVal("01023459867");
		signUpObj.assignGenderVal("1");
		signUpObj.assignBirthdayVal(LocalDate.parse("1996-01-17"));
		signUpObj.assignNotificationChk(true);
		
		//direct to My Page after register button click
		var myPage = signUpObj.registerOnClick();
		//compare expected page header and retrieved page header
		assertEquals("マイページ", myPage.getHeaderText());
		driver.manage().deleteAllCookies();
	}
	
	@Test
	@Order(2)
	@DisplayName("Verify required field validation")
	//TC_002
	public void testVerifyRequired() {
		driver.get(HOME_URL);
		
		var signUpObj = new SignUpPage(driver);
		signUpObj.assignEmailVal("");
		signUpObj.assignPasswordVal("");
		signUpObj.assignPasswordConfVal("");
		signUpObj.assignUsernameVal("");
		signUpObj.assignMembershipType("プレミアム会員");
		signUpObj.assignAddressVal("東京都江戸川区船堀");
		signUpObj.assignTelVal("01023459867");
		signUpObj.assignGenderVal("1");
		signUpObj.assignBirthdayVal(LocalDate.parse("1996-01-17"));
		signUpObj.assignNotificationChk(true);
		
		//stay on Member Registration page after register button click
		signUpObj.registerOnClickFailed();
		//compare expected error messages and retrieved error messages
		assertAll("Error", () -> assertEquals("このフィールドを入力してください。", signUpObj.getEmailInvalidMsg()),
				   () -> assertEquals("このフィールドを入力してください。", signUpObj.getPasswordInvalidMsg()),
				   () -> assertEquals("このフィールドを入力してください。", signUpObj.getPasswordConfInvalidMsg()),
				   () -> assertEquals("このフィールドを入力してください。", signUpObj.getUsernameInvalidMsg())
				 );
		driver.manage().deleteAllCookies();
	}
	
	@Test
	@Order(3)
	@DisplayName("Verify the check for password mismatch")
	//TC_005
	public void testPasswordMismatch() {
		driver.get(HOME_URL);
		
		var signUpObj = new SignUpPage(driver);
		signUpObj.assignEmailVal("user1@test.com");
		signUpObj.assignPasswordVal("pswd1234");
		signUpObj.assignPasswordConfVal("pswd12345");
		signUpObj.assignUsernameVal("user1");
		signUpObj.assignMembershipType("プレミアム会員");
		signUpObj.assignAddressVal("東京都江戸川区船堀");
		signUpObj.assignTelVal("01023459867");
		signUpObj.assignGenderVal("1");
		signUpObj.assignBirthdayVal(LocalDate.parse("1996-01-17"));
		signUpObj.assignNotificationChk(true);
		
		//stay on Member Registration page after register button click
		signUpObj.registerOnClickFailed();
		//compare expected error message and retrieved error message
		assertEquals("入力されたパスワードと一致しません。", signUpObj.getPasswordConfInvalidMsg());
		driver.manage().deleteAllCookies();
	}
	
	@Test
	@Order(4)
	@DisplayName("Verify the checking for business error")
	//TC_003, TC_004, TC_008
	public void testBusinessError() {
		driver.get(HOME_URL);
		
		var signUpObj = new SignUpPage(driver);
		signUpObj.assignEmailVal("user1");
		signUpObj.assignPasswordVal("pswd123");
		signUpObj.assignPasswordConfVal("pswd123");
		signUpObj.assignUsernameVal("user1");
		signUpObj.assignMembershipType("一般会員");
		signUpObj.assignAddressVal("東京都江戸川区船堀");
		signUpObj.assignTelVal("０１０１２３４５６７８");
		signUpObj.assignGenderVal("1");
		signUpObj.assignBirthdayVal(LocalDate.parse("1996-01-17"));
		signUpObj.assignNotificationChk(true);
		
		//stay on Member Registration page after register button click
		signUpObj.registerOnClickFailed();
		//compare expected error messages and retrieved error messages
		assertAll("Error", () -> assertEquals("メールアドレスを入力してください。", signUpObj.getEmailInvalidMsg()),
				   () -> assertEquals("8文字以上で入力してください。", signUpObj.getPasswordInvalidMsg()),
				   () -> assertEquals("8文字以上で入力してください。", signUpObj.getPasswordConfInvalidMsg()),
				   () -> assertEquals("指定されている形式で入力してください。", signUpObj.getTelInvalidMsg())
				 );
		driver.manage().deleteAllCookies();
	}
	
}

