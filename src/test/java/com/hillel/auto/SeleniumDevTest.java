package com.hillel.auto;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;

public class SeleniumDevTest {
    public WebDriver driver;
    public String title;

    @BeforeClass
    public void setUpDriver (){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setUp () {
        driver = new ChromeDriver();
        WebDriver.Window window = driver.manage().window();
//        window.fullscreen();
    }

    @AfterMethod
    public void quot () {
        driver.quit();
    }

    @Test
    public void seleniumDevSiteShouldBeOpen () {

        driver.get("https://www.selenium.dev/");
        title = driver.getTitle();//SeleniumHQ Browser Automation
        System.out.println(title);
        assertEquals(title, "SeleniumHQ Browser Automation");
    }

    @Test
    public void seleniumDownloadsShouldBeOpen (){
        driver.get("https://www.selenium.dev/downloads/");
        String title = driver.getTitle();
        System.out.println(title);
        assertThat(title).
                isEqualTo("Downloads");
    }
    @Test
        public void seleniumProjectShouldBeOpen (){
            WebDriver.Navigation navigate = driver.navigate();
            navigate.to("https://www.selenium.dev/projects");
            navigate.back();
            navigate.forward();
            navigate.refresh();
            title = driver.getTitle();
            System.out.println(title);
//            assertEquals(title, "Selenium Projects");
            assertThat(title).
                    isEqualTo("Selenium Projects");
        }

    @Test
    public void seleniumDocumentationShouldBeOpen (){
        driver.get("https://www.selenium.dev/documentation/");
        title = driver.getTitle();
        System.out.println(title);
        assertThat(title).
                isEqualTo("The Selenium Browser Automation Project :: Documentation for Selenium");
    }

    @Test
    public void seleniumSupportShouldBeOpen (){
        driver.get("https://www.selenium.dev/support/");
        title = driver.getTitle();
        System.out.println(title);
        assertThat(title).
                isEqualTo("Selenium Support");
    }

    @Test
    public void seleniumBlogShouldBeOpen (){
        driver.get("https://www.selenium.dev/blog/");
        title = driver.getTitle();
        System.out.println(title);
        assertThat(title).
                isEqualTo("Selenium Blog");
    }
}
