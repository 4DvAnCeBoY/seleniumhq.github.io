package dev.selenium.browsers;

import dev.selenium.BaseTest;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.regex.Pattern;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chromium.ChromiumDriverLogLevel;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.service.DriverFinder;


public class EdgeTest extends BaseTest {
  @AfterEach
  public void clearProperties() {
    System.clearProperty(EdgeDriverService.EDGE_DRIVER_LOG_PROPERTY);
    System.clearProperty(EdgeDriverService.EDGE_DRIVER_LOG_LEVEL_PROPERTY);
  }

  @Test
  public void basicOptions() {
    EdgeOptions options = new EdgeOptions();
    driver = createRemoteSession(options);
  }

  @Test
  public void arguments() {
    EdgeOptions options = new EdgeOptions();

    options.addArguments("--start-maximized");

    driver = createRemoteSession(options);
  }

  @Test
  public void setBrowserLocation() {
    EdgeOptions options = new EdgeOptions();

    options.setBinary(getEdgeLocation());

    driver = createRemoteSession(options);
  }

  @Test
  public void extensionOptions() {
    EdgeOptions options = new EdgeOptions();
    Path path = Paths.get("src/test/resources/extensions/webextensions-selenium-example.crx");
    File extensionFilePath = new File(path.toUri());

    options.addExtensions(extensionFilePath);

    driver = createRemoteSession(options);
    driver.get("https://www.selenium.dev/selenium/web/blank.html");
    WebElement injected = driver.findElement(By.id("webextensions-selenium-example"));
    Assertions.assertEquals(
        "Content injected by webextensions-selenium-example", injected.getText());
  }

  @Test
  public void excludeSwitches() {
    EdgeOptions options = new EdgeOptions();

    options.setExperimentalOption("excludeSwitches", List.of("disable-popup-blocking"));

    driver = createRemoteSession(options);
  }

  @Test
  public void loggingPreferences() {
    EdgeOptions options = new EdgeOptions();
    LoggingPreferences logPrefs = new LoggingPreferences();
    logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
    options.setCapability(EdgeOptions.LOGGING_PREFS, logPrefs);

    driver = createRemoteSession(options);
    driver.get("https://www.selenium.dev");

    LogEntries logEntries = driver.manage().logs().get(LogType.PERFORMANCE);
    Assertions.assertFalse(logEntries.getAll().isEmpty());
  }

}
