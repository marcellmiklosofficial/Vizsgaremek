package com.codecool.vizsgaremek.functions;

import com.codecool.vizsgaremek.TestUtils;
import com.codecool.vizsgaremek.enums.Pages;
import com.codecool.vizsgaremek.pages.Portfolio;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestWriteDataToFile extends TestBase {
    @Override
    @BeforeEach
    void setUp() {
        super.setUp();

        getPage(Portfolio.class).navigateTo();
    }

    @Override
    void correctUrl() {
        Assertions.assertEquals(Pages.PORTFOLIO_PAGE.getUrl(), driver.getCurrentUrl());
    }

    @Test
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
