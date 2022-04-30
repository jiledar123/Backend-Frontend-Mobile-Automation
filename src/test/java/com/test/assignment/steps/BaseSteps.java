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
    @Before
    public void getScenario(Scenario scenario) {
        BaseSteps.scenario = scenario;
    }

    @After
    public void afterUITests(final Scenario scenario) {
        if (!scenario.isFailed()) {
            WebDriverFactory.quitCurrentDriver();
        }
    }
}
