package dev.selenium.browsers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariDriverService;
import org.openqa.selenium.safari.SafariOptions;

import dev.selenium.BaseTest;

@EnabledOnOs(OS.MAC)
public class SafariTest extends BaseTest {
    public SafariDriver driver;

    @AfterEach
    public void quit() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void basicOptions() {
        SafariOptions options = new SafariOptions();
        driver = (SafariDriver) createRemoteSession(options);
    }

}
