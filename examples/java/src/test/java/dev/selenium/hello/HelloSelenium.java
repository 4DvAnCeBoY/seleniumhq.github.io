package dev.selenium.hello;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import dev.selenium.BaseTest;

public class HelloSelenium extends BaseTest {
    public static void main(String[] args) {
        WebDriver driver = createRemoteSession(new ChromeOptions());

        driver.get("https://selenium.dev");

        driver.quit();
    }
}
