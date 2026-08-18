package org.userway.selenium.manual;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.levelci.selenium.AccessibilityAuditor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.userway.selenium.levelci.LevelSetup;

import static org.assertj.core.api.Assertions.assertThat;

public class ManualScanTest {

    private static WebDriver driver;

    @BeforeAll
    public static void setup() {
        var options = new ChromeOptions();
        options.addArguments(
                "--headless=new",
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--disable-gpu");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void captureLevelCiReport() {
        var auditConfig = LevelSetup.getAuditConfig(driver);
        var result = AccessibilityAuditor.levelAnalyze(auditConfig);
        assertThat(result.getError()).isNull();
    }

    @AfterAll
    @SneakyThrows
    public static void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Should scan page and save Level CI scope report")
    void shouldScanPageAndSaveReport() {
        driver.get("https://www.w3.org/WAI/test-evaluate/preliminary/#images");
    }
}
