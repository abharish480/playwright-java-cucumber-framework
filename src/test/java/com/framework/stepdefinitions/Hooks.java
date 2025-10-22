package com.framework.stepdefinitions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.framework.base.PlaywrightDriver;
import com.framework.utils.ConfigReader;
import com.framework.utils.SoftAssertManager;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	@Before
	public void setUp() {
		PlaywrightDriver.initDriver(System.getProperty("browser", "chromium"));
		PlaywrightDriver.getPage().navigate(System.getProperty("url", ConfigReader.get("url")));
		System.out.println("Thread ID: " + Thread.currentThread().threadId());
		SoftAssertManager.initSoftAssert();
	}

	@After
	public void tearDown(Scenario scenario) {
		byte[] screenshotOnFailure = null;

		try {
			SoftAssertManager.assertAll();
		} catch (AssertionError ae) {
			try {
				String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
				String screenshotName = scenario.getName() + "_" + timestamp;

				screenshotOnFailure = PlaywrightDriver.getPage().screenshot(); // default: PNG bytes

				// Attach to Cucumber report
				scenario.attach(screenshotOnFailure, "image/png", screenshotName);

				// Attach to Allure with content type (so it renders as an image)
//                Allure.addAttachment("Failed Screenshot", "image/png",
//                        new ByteArrayInputStream(screenshotOnFailure), "png");
			} catch (Exception e) {
				System.err.println("Error capturing screenshot: " + e.getMessage());
			}
			throw ae;
		} finally {
			SoftAssertManager.remove();
			PlaywrightDriver.close();
		}
	}

}