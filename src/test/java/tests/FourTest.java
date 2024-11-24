package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import resources.ProjBase;

public class FourTest extends ProjBase {
	
	public WebDriver driver;
	
	@Test
	public void testFour() throws IOException, InterruptedException {
		
		System.out.println("Ahmad updated this test-case");
		
		System.out.println("Test four is running!");
		driver = initializeApp();
		
		driver.get("https://tutorialsninja.com/demo/");
		Thread.sleep(2000);
		Assert.assertTrue(false);
	}
	
	@AfterMethod
	public void closure()
	{
		driver.close();;
	}
	
}
