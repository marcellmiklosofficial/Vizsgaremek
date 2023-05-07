package com.codecool.vizsgaremek.functions;

import com.codecool.vizsgaremek.TestConstants;
import com.codecool.vizsgaremek.TestUtils;
import com.codecool.vizsgaremek.enums.Pages;
import com.codecool.vizsgaremek.pages.Portfolio;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Write Data To File Tests")
class TestWriteDataToFile extends TestBase {
    /**
     Instantiates a <code>WebDriver</code> from the <code>WebDriverFactory</code> and navigates to the Portfolio page
     the test needs to be executed on.

     @since 1.0
     */
    @Override
    @BeforeEach
    void setUp() {
        super.setUp();

        getPage(Portfolio.class).navigateTo();
    }

    @Override
    @Test
    @DisplayName(TestConstants.TEST_URL_DISPLAY_NAME)
    @Description(TestConstants.TEST_URL_DESCRIPTION)
    @Story(TestConstants.TEST_URL_STORY)
    @Severity(SeverityLevel.TRIVIAL)
    void correctUrl() {
        Assertions.assertEquals(Pages.PORTFOLIO_PAGE.getUrl(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Content from page written to file")
    @Description("Content from the webpage is successfully written into a file")
    @Story("The header contents of the Portfolio page is written into a file and is checked for correct contents")
    @Severity(SeverityLevel.MINOR)
    @Feature("Write Data To File")
    void writeHeaderTextToFile() {
        String headerText = getPage(Portfolio.class).getHeaderText();
        String testFileName = "testOutput.txt";

        TestUtils.writeToFile(headerText, testFileName);

        Assertions.assertEquals(headerText, TestUtils.readFromFile(testFileName));
    }

    @AfterAll
    static void afterAll() {
        TestUtils.deleteTestFiles();
    }
}
