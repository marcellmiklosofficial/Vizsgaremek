package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.TestConstants;
import com.codecool.vizsgaremek.WebDriverFactory;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

@Epic(TestConstants.NAME_EPIC)
abstract class TestBase {
    protected WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = WebDriverFactory.getWebDriver();
    }

    @SuppressWarnings("unused")
    abstract void correctUrl();

    @AfterEach
    final void tearDown() {
        driver.quit();
    }
}
