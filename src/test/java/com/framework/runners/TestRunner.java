package com.framework.runners;

import org.testng.annotations.DataProvider;

import com.framework.utils.ConfigReader;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.framework.stepdefinitions", "hooks"},
    plugin = {
        "pretty",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
        "html:target/cucumber-report.html",
        "json:target/cucumber.json"
    },
    monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}

	static {
//        String tags = System.getProperty("tags", "@smoke2"); // fallback if not passed
//        System.setProperty("cucumber.filter.tags", tags);
//        String threads = System.getProperty("threads", "2");
//        System.setProperty("dataproviderthreadcount", threads);

		String tags = ConfigReader.get("tags");
		String threads = ConfigReader.get("threads");
		String browser = ConfigReader.get("browser");
		String env = ConfigReader.get("env");

		System.setProperty("cucumber.filter.tags", tags);
		System.setProperty("dataproviderthreadcount", threads);
		System.setProperty("browser", browser);
		System.setProperty("env", env);

		System.out.println(">>> Running with tags: " + tags);
		System.out.println(">>> Threads: " + threads);
		System.out.println(">>> Browser: " + browser);
		System.out.println(">>> Env: " + env);

	}
}
