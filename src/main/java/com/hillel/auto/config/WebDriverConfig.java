package com.hillel.auto.config;

import io.github.bonigarcia.wdm.WebDriverManager;

import static com.hillel.auto.config.Browser.*;


public class WebDriverConfig {

    private static final String browser = System.getProperty("browser", CHROME.name().toLowerCase());

    public static void load() {
        Browser browserType = Browser.valueOf(browser.toUpperCase());
        switch (browserType) {
            case CHROME:
            case HEADLESS_CHROME:
                WebDriverManager.chromedriver().setup();
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                break;
            default:
                throw new UnsupportedBrowserException(browser);
        }
    }

    public static String browser() {
        return browser;
    }

    public static boolean isChrome() {
        return browser.equalsIgnoreCase(CHROME.name());
    }

    public static boolean isFirefox() {
        return browser.equalsIgnoreCase(FIREFOX.name());
    }

    public static boolean isHeadlessChrome() {
        return browser.equalsIgnoreCase(HEADLESS_CHROME.name());
    }
}
