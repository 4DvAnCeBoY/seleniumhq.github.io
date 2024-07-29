package dev.selenium.browsers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverLogLevel;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.ie.InternetExplorerOptions;

import dev.selenium.BaseTest;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;

@EnabledOnOs(OS.WINDOWS)
public class InternetExplorerTest extends BaseTest {
    public InternetExplorerDriver driver;
    private File logLocation;
    private File tempDirectory;

    @AfterEach
    public void quit() {
        if (logLocation != null && logLocation.exists()) {
            logLocation.delete();
        }
        if (tempDirectory  != null && tempDirectory.exists()) {
            tempDirectory.delete();
        }

        driver.quit();
    }

    @Test
    public void basicOptionsWin10() {
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.attachToEdgeChrome();
        options.withEdgeExecutablePath(getEdgeLocation());
        driver = (InternetExplorerDriver) createRemoteSession(options);
    }

    @Test
    public void basicOptionsWin11() {
        InternetExplorerOptions options = new InternetExplorerOptions();
        driver = (InternetExplorerDriver) createRemoteSession(options);
    }


    private String getEdgeLocation() {
        return System.getenv("EDGE_BIN");
    }
}
