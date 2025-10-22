package com.framework.base;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightDriver {
	private static ThreadLocal<Playwright> playwright = new ThreadLocal<>();
	private static ThreadLocal<Browser> browser = new ThreadLocal<>();
	private static ThreadLocal<Page> page = new ThreadLocal<>();

	public static void initDriver(String browserName) {
		playwright.set(Playwright.create());
		System.out.println("Running in : >> " + browserName);

		Browser launchedBrowser;
		switch (browserName.toLowerCase()) {
		case "firefox":
			launchedBrowser = playwright.get().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
			break;
		case "webkit":
			launchedBrowser = playwright.get().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
			break;
		case "chrome":
			launchedBrowser = playwright.get().chromium()
					.launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
			break;
		default:
			launchedBrowser = playwright.get().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			break;
		}

		browser.set(launchedBrowser);

		// Create context with viewport disabled (null) → maximized/full window
		Browser.NewContextOptions contextOptions = new Browser.NewContextOptions().setViewportSize(1920, 1080);

		BrowserContext context = browser.get().newContext(contextOptions);

		page.set(context.newPage());
	}

	public static Page getPage() {
		return page.get();
	}

	public static void close() {
		if (page.get() != null)
			page.get().close();
		if (browser.get() != null)
			browser.get().close();
		if (playwright.get() != null)
			playwright.get().close();
		playwright.remove();
		browser.remove();
		page.remove();
	}
}