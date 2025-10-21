package com.framework.pages;

import com.microsoft.playwright.Page;

public class HomePage {
    private Page page;

    public HomePage(Page page) {
        this.page = page;
    }

    public String getTitle() {
        return page.title();
    }
}