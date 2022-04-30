package com.test.assignment;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = { "src/test/resources/features/web-ui","src/test/resources/features/api" ,"src/test/resources/features/mobile"},
		glue = { "com.test.assignment.steps"},
		plugin = {"html:target/cukes", "json:target/cucumber-report.json", "junit:target/cucumber-report.xml", "pretty",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		strict = true,
		tags = {"@MobileTask"},
		monochrome = true)
public class Runner extends AbstractTestNGCucumberTests {
}
