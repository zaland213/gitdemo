package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobject.AccountInfo;
import pageobject.HomePage;
import pageobject.LoginPage;
import resources.ProjBase;

public class loginTest extends ProjBase {

	WebDriver driver;
	Logger log;
	
	@BeforeMethod
	public void openApplication() throws IOException {
		log = LogManager.getLogger(loginTest.class.getName());
		driver = initializeApp();
		log.debug("Browser Got Launched");
		driver.get(prop.getProperty("url"));
		log.debug("Navigated to Application URL");
	}
	
	@Test(dataProvider="getLoginData")
	public void login(String email, String password, String status) throws IOException {
		HomePage homepage = new HomePage(driver);
		homepage.myAccountDrop().click();
		log.debug("Clicked on My Account DropDown in Home Page");
		homepage.loginLink().click();
		log.debug("Clicked on Login option under My Account");
		
		LoginPage loginpage = new LoginPage(driver);
		loginpage.emailField().sendKeys(email);
		log.debug("Email address got entered");
		loginpage.passwordField().sendKeys(password);
		log.debug("Password got entered");
		loginpage.loginButton().click();
		log.debug("Clicked on Login Button");
		AccountInfo accountinfo = new AccountInfo(driver);
		String actualResult;
		try {
			
			accountinfo.accountEditPage().isDisplayed();
			log.debug("User Got Logged in");
			actualResult="Successful";
		}
		catch(Exception e) {
			log.debug("User didn't log in");
			actualResult="Failed";
		}
		Assert.assertEquals(actualResult, status);
		log.info("Login Test Got Passed");
	}
	
	@DataProvider
	public Object[][] getLoginData() {
		Object[][] data = {{"ahmad@gmail.com","123456","Successful"},{"abc@gmail.com","abc123","Failed"}};
		return data;
	}
	
	@AfterMethod
	public void closure() {
		driver.close();
		log.debug("Browser Got closed");
	}
}
