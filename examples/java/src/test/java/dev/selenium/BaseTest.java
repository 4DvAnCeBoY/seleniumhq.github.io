package dev.selenium;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {
  protected RemoteWebDriver driver;
  protected WebDriverWait wait;
  protected File driverPath;
  protected File browserPath;
  public static URL gridUrl;

  public BaseTest() {
     try {
      gridUrl = new URL(System.getenv("gridUrl"));
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
  }
  
  public  RemoteWebDriver createRemoteSession (MutableCapabilities options) {
    HashMap<String, Object> ltOptions = new HashMap<String, Object>();
           ltOptions.put("name",Thread.currentThread().getStackTrace()[3].getClassName() );
    ((MutableCapabilities) options).setCapability("LT:Options", ltOptions);
    driver = new RemoteWebDriver(gridUrl,(Capabilities) options);
  return driver;
  }

  public WebElement getLocatedElement(WebDriver driver, By by) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    return wait.until(d -> driver.findElement(by));
  }

  protected RemoteWebDriver startFirefoxDriver() {
    FirefoxOptions options = new FirefoxOptions();
    HashMap<String, Object> ltOptions = new HashMap<String, Object>();
         ltOptions.put("name",Thread.currentThread().getStackTrace()[3].getClassName() );
    options.setCapability("LT:Options", ltOptions);
    return createRemoteSession(options);
  }

  protected RemoteWebDriver startFirefoxDriver(FirefoxOptions options) {
    options.setImplicitWaitTimeout(Duration.ofSeconds(1));
    HashMap<String, Object> ltOptions = new HashMap<String, Object>();
           ltOptions.put("name",Thread.currentThread().getStackTrace()[3].getClassName() );
    options.setCapability("LT:Options", ltOptions);
    driver = createRemoteSession(options);
    return  driver;
  }

  protected RemoteWebDriver startChromeDriver() {
    ChromeOptions options = new ChromeOptions();
    options.setImplicitWaitTimeout(Duration.ofSeconds(1));
    HashMap<String, Object> ltOptions = new HashMap<String, Object>();
      ltOptions.put("name",Thread.currentThread().getStackTrace()[3].getClassName() );
    options.setCapability("LT:Options", ltOptions);
    return createRemoteSession(options);
  }

  protected RemoteWebDriver startChromeDriver(ChromeOptions options) {
    options.setImplicitWaitTimeout(Duration.ofSeconds(1));
    HashMap<String, Object> ltOptions = new HashMap<String, Object>();
           ltOptions.put("name",Thread.currentThread().getStackTrace()[3].getClassName() );
    options.setCapability("LT:Options", ltOptions);
  //  StackTraceElement[] stackelement = Thread.currentThread().getStackTrace();
  //  for (StackTraceElement stackTraceElement : stackelement) {
  //   System.out.println(stackTraceElement.getClassName() + " -> " + stackTraceElement.getMethodName());
  //  }

    return createRemoteSession(options);
  }

  protected File getTempDirectory(String prefix) {
    File tempDirectory = null;
    try {
      tempDirectory = Files.createTempDirectory(prefix).toFile();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    tempDirectory.deleteOnExit();

    return tempDirectory;
  }

  protected File getTempFile(String prefix, String suffix) {
    File logLocation = null;
    try {
      logLocation = File.createTempFile(prefix, suffix);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    logLocation.deleteOnExit();

    return logLocation;
  }

  protected URL startStandaloneGrid() {
    return gridUrl;
    // int port = PortProber.findFreePort();
    // try {
    //   Main.main(
    //       new String[] {
    //           "standalone",
    //           "--port",
    //           String.valueOf(port),
    //           "--selenium-manager",
    //           "true",
    //           "--enable-managed-downloads",
    //           "true",
    //           "--log-level",
    //           "WARNING"
    //       });
    //   return new URL("http://localhost:" + port);
    // } catch (Exception e) {
    //   throw new RuntimeException(e);
    // }
  }

  protected void enableLogging() {
    Logger logger = Logger.getLogger("");
    logger.setLevel(Level.FINE);
    Arrays.stream(logger.getHandlers()).forEach(handler -> {
      handler.setLevel(Level.FINE);
    });
  }

  @AfterEach
  public void quit(TestInfo testInfo) {
    if (driver != null) {
      driver.executeScript("lambda-name="+testInfo.getTestClass() +"-> "+testInfo.getDisplayName(),"");
      // driver.executeScript("lambda-status="+testInfo.getTags()., null);
      driver.quit();
    }
  }
}
