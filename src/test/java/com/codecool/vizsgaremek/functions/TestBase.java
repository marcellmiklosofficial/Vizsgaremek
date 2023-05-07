package com.codecool.vizsgaremek.functions;

import com.codecool.vizsgaremek.TestConstants;
import com.codecool.vizsgaremek.WebDriverFactory;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

@Epic(TestConstants.NAME_EPIC)
abstract class TestBase {
    protected WebDriver driver;

    private final Map<Class<?>, Object> pages = new HashMap<>();

    @BeforeEach
    void setUp() {
        driver = WebDriverFactory.getWebDriver();
    }

    @SuppressWarnings("unused")
    @Story("User navigates to the correct URL")
    @Severity(SeverityLevel.NORMAL)
    abstract void correctUrl();

    protected final <T> T getPage(Class<T> pageType) {
        Object pageObject = pages.get(pageType);

        if (pageObject == null) {
            try {
                pageObject = pageType.getDeclaredConstructor(WebDriver.class).newInstance(driver);
                pages.put(pageType, pageObject);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return pageType.cast(pageObject);
    }

    @AfterEach
    final void tearDown() {
        driver.quit();
    }
}
