package com.hillel.auto.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {

    public static WebDriver createDriver() {
        Browser browserType = Browser.valueOf(WebDriverConfig.browser().toUpperCase());
        switch (browserType) {
            case CHROME:
                return new ChromeDriver();
            case FIREFOX:
                return new FirefoxDriver();
            case HEADLESS_CHROME:
                ChromeOptions options = new ChromeOptions();
                options.setHeadless(true);
                return new ChromeDriver(options);
            default:
                throw new UnsupportedBrowserException(browserType.name());
        }
    }
}
