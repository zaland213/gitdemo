package stepdefinition;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pageobject.AccountInfo;
import pageobject.HomePage;
import pageobject.LoginPage;
import resources.ProjBase;

public class Login extends ProjBase {

		WebDriver driver;
		HomePage homepage;
		LoginPage loginpage;
		AccountInfo accountinfo;
		
		@Given("^open any browser$")
		public void open_any_browser() throws IOException {
			driver = initializeApp();
		}
		
		@And("^navigate to Login page$")
		public void navigate_to_Login_page() throws InterruptedException {
			driver.get(prop.getProperty("url"));
			homepage = new HomePage(driver);
			homepage.myAccountDrop().click();
			homepage.loginLink().click();
			Thread.sleep(3000);
		}
		
		@When("^User enters username as \"([^\"]*)\" and password as \"([^\"]*)\" into the fields$")
		public void User_enters_username_as_something_and_password_as_something_into_the_fields(String username, String password) {
			loginpage = new LoginPage(driver);
			loginpage.emailField().sendKeys(username);
			loginpage.passwordField().sendKeys(password);
		}
		
		@And("^User clicks on Login button$")
		public void User_clicks_on_Login_button() {
			loginpage.loginButton().click();
		}		
		@After
		public void closeBrowser() {
			driver.close();
		}
}
