package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions (
			features = "src/test/java/features/login.feature",
			glue="stepdefinition"
		)
public class Runner extends AbstractTestNGCucumberTests{

		
}
