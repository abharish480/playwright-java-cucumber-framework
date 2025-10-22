package com.framework.utils;

import com.framework.base.PlaywrightDriver;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.TimeoutError;
import com.microsoft.playwright.options.WaitForSelectorState;

public class WebActions {

	private static Page page;

	// Initialize once via constructor
	public WebActions() {
		page = PlaywrightDriver.getPage();
	}

	/**
	 * Wait for element to be visible within given timeout
	 */
	public static void waitForElementVisible(String selector, int timeoutInSeconds) {
		try {
			page.locator(selector).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE)
					.setTimeout(timeoutInSeconds * 1000));
		} catch (TimeoutError e) {
			throw new RuntimeException("Element not visible after " + timeoutInSeconds + " seconds: " + selector, e);
		}
	}

	public static void waitForElementVisible(Page page, String selector, int timeoutInSeconds) {
		try {
			page.locator(selector).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE)
					.setTimeout(timeoutInSeconds * 1000));
		} catch (TimeoutError e) {
			throw new RuntimeException("Element not visible after " + timeoutInSeconds + " seconds: " + selector, e);
		}
	}

	/**
	 * Wait for element to be present in DOM
	 */
	public static void waitForElementPresent(String selector, int timeoutInSeconds) {
		try {
			page.locator(selector).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.ATTACHED)
					.setTimeout(timeoutInSeconds * 1000));
		} catch (TimeoutError e) {
			throw new RuntimeException("Element not present after " + timeoutInSeconds + " seconds: " + selector, e);
		}
	}

	/**
	 * Wait for element to be clickable (visible + enabled)
	 */
	public static void waitForElementClickable(String selector, int timeoutInSeconds) {
		try {
			Locator locator = page.locator(selector);
			locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE)
					.setTimeout(timeoutInSeconds * 1000));

			if (!locator.isEnabled()) {
				throw new RuntimeException("Element is visible but not enabled (clickable): " + selector);
			}
		} catch (TimeoutError e) {
			throw new RuntimeException("Element not clickable after " + timeoutInSeconds + " seconds: " + selector, e);
		}
	}

	/**
	 * Click when ready
	 */
	public static void clickWhenReady(String selector, int timeoutInSeconds) {
		waitForElementClickable(selector, timeoutInSeconds);
		page.locator(selector).click();
	}

	/**
	 * Type into element when ready
	 */
	public static void typeWhenReady(String selector, String text, int timeoutInSeconds) {
		waitForElementVisible(selector, timeoutInSeconds);
		page.locator(selector).fill(text);
	}
}
