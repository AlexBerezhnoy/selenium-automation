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

    @BeforeClass
    public void setUpDriver (){
        WebDriverManager.chromedriver().setup();
    }
    @BeforeMethod
    public void setUp () {
        driver = new ChromeDriver();
        WebDriver.Window window = driver.manage().window();
        window.fullscreen();
    }

    @AfterMethod
    public void quot () {
        driver.quit();
    }
    @Test
    public void seleniumDevSiteShouldBeOpen () {

        driver.get("https://www.selenium.dev/");
        String title = driver.getTitle();//SeleniumHQ Browser Automation
        System.out.println(title);
        assertEquals(title, "SeleniumHQ Browser Automation");
    }
        @Test
        public void seleniumProjectShouldBeOpen (){
            WebDriver.Navigation navigate = driver.navigate();

            navigate.to("https://www.selenium.dev/projects");
            navigate.back();
            navigate.forward();
            navigate.refresh();
            String title = driver.getTitle();
            System.out.println(title);
//            assertEquals(title, "Selenium Projects");
            assertThat(title).
                    isEqualTo("Selenium Projects");
        }

}
