package dev.selenium.interactions;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import dev.selenium.BaseTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NavigationTest extends BaseTest {
    @Test
    public void navigateBrowser() {
        
        WebDriver driver = createRemoteSession(new ChromeOptions());
      
        //Convenient
        driver.get("https://selenium.dev");
            
        //Longer way
        driver.navigate().to("https://selenium.dev");
        String title = driver.getTitle();
        assertEquals(title, "Selenium");
        
        //Back
        driver.navigate().back();
        title = driver.getTitle();
        assertEquals(title, "Selenium");
        
        //Forward
        driver.navigate().forward();
        title = driver.getTitle();
        assertEquals(title, "Selenium");

        //Refresh
        driver.navigate().refresh();
        title = driver.getTitle();
        assertEquals(title, "Selenium");

        driver.quit();
    }
}
