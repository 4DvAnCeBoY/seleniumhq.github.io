package dev.selenium;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

public class BaseChromeTest extends BaseTest {

  @BeforeEach
  public void setup(TestInfo testInfo) {
    startChromeDriver();
  }
}
