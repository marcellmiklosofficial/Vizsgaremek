package com.codecool.vizsgaremek.functions;

import com.codecool.vizsgaremek.TestConstants;
import com.codecool.vizsgaremek.enums.Pages;
import com.codecool.vizsgaremek.pages.Blog;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

@Epic(TestConstants.NAME_EPIC)
@Feature("Multi Page list")
@DisplayName("Multi Page list Tests")
class TestMultiPageList extends TestBase {
    /**
     Instantiates a <code>WebDriver</code> from the <code>WebDriverFactory</code> and navigates to the Blog page the
     test needs to be executed on.

     @since 1.0
     */
    @Override
    @BeforeEach
    void setUp() {
        super.setUp();

        getPage(Blog.class).navigateTo();
    }

    @Override
    @Test
    @DisplayName(TestConstants.TEST_URL_DISPLAY_NAME)
    @Description(TestConstants.TEST_URL_DESCRIPTION)
    @Story(TestConstants.TEST_URL_STORY)
    @Severity(SeverityLevel.TRIVIAL)
    void correctUrl() {
        Assertions.assertEquals(Pages.BLOG_PAGE.getUrl(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Titles gathered from multiple pages")
    @Description("Test to gather titles from multiple pages")
    @Story("User can see all titles from all pages")
    @Severity(SeverityLevel.NORMAL)
    void gatherAllTitles() {
        List<String> testData = Arrays.asList(
                "Design Inspiration: The Best Projects From December",
                "The 10 Biggest Rebrands and Logo Designs of 2019",
                "Design Inspiration: The Best Projects From November",
                "Pt Chooses Classic Blue for Its Colour of the Year 2020",
                "The 10 Biggest Product Stories of 2019");

        Assertions.assertEquals(testData, getPage(Blog.class).getAllTitlesFromMultiPageList());
    }
}
