package dev.selenium.hello;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import dev.selenium.BaseTest;

public class HelloSelenium  {
    static WebDriver driver =  new BaseTest().createRemoteSession(new ChromeOptions());
    public static void main(String[] args) {
        
        driver.get("https://selenium.dev");

        driver.quit();
    }
}
