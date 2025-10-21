package com.framework.base;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightDriver {
    private static ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    private static ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static ThreadLocal<Page> page = new ThreadLocal<>();

    public static void initDriver(String browserName) {
        playwright.set(Playwright.create());
        System.out.println("Running in : >> "+browserName);
        switch (browserName.toLowerCase()) {
            case "firefox":
                browser.set(playwright.get().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "webkit":
                browser.set(playwright.get().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
        	case "chrome":
    			browser.set(playwright.get().chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false)));
    			break;
            default:
                browser.set(playwright.get().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
        }
        page.set(browser.get().newPage());
    }

    public static Page getPage() {
        return page.get();
    }

    public static void close() {
        if (page.get() != null) page.get().close();
        if (browser.get() != null) browser.get().close();
        if (playwright.get() != null) playwright.get().close();
        playwright.remove();
        browser.remove();
        page.remove();
    }
}