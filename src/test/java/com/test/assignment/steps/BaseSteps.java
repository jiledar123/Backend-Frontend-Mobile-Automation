package com.test.assignment.steps;
import com.main.assignment.utils.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

/**
 * Cucumber hook steps definition class responsible for actions taken before and after test execution.
 */
public class BaseSteps {
    public static Scenario scenario;

    @Before(order = 0)
    public void beforeUITests() {
        if(System.getProperty("module").equalsIgnoreCase("frontend")) {
            WebDriverFactory.initialize();
        }else if(System.getProperty("module").equalsIgnoreCase("backend")) {
//            WebDriverFactory.initialize();
        }else if(System.getProperty("module").equalsIgnoreCase("mobile")) {
//            WebDriverFactory.initialize();
        }
    }

    @Before(order = 1)
    public void getScenario(Scenario scenario) {
        BaseSteps.scenario = scenario;
    }

    @AfterStep
    public void addScreenshotOnFailure(final Scenario scenario) {
        if (scenario.isFailed()) {
            TakesScreenshot ts = (TakesScreenshot) WebDriverFactory.getDriver();
            byte[] src = ts.getScreenshotAs(OutputType.BYTES);
            scenario.embed(src, "image/png", "FailShot");
        }
    }

    @After
    public void afterUITests(final Scenario scenario) {
        if (!scenario.isFailed()) {
            WebDriverFactory.quitCurrentDriver();
        }
    }
}
