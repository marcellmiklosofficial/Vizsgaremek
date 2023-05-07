package com.codecool.vizsgaremek.functions;

import com.codecool.vizsgaremek.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

abstract class TestBase {
    protected WebDriver driver;

    private final Map<Class<?>, Object> pages = new HashMap<>();

    /**
     Instantiates a <code>WebDriver</code> from the <code>WebDriverFactory</code>.

     @since 1.0
     */
    @BeforeEach
    void setUp() {
        driver = WebDriverFactory.getWebDriver();
    }

    @Test
    abstract void correctUrl();

    /**
     Returns a <code>Page</code> object specified by <code>pageType</code>. Uses lazy initialization and stores all
     instances in the <code>pages</code> HashMap.

     @param pageType The <code>Class</code> of the page type needed
     @return An instance of the <code>pageType</code> provided
     @since 1.0
     */
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

    /**
     Closes all instances of the <code>WebDriver</code>

     @since 1.0
     */
    @AfterEach
    final void tearDown() {
        driver.quit();
    }
}
