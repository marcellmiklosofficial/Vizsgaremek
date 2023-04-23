package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.TestUtils;
import com.codecool.vizsgaremek.enums.Pages;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Feature("About Page")
@DisplayName("About Page Tests")
class AboutTest extends TestBase {
    private About about;

    @BeforeEach
    @Override
    void setUp() {
        super.setUp();

        about = new About(driver);

        about.navigateTo();
    }

    @Test
    @DisplayName("The correct URL is opened")
    @Description("The correct URL is opened")
    @Story("User navigates to the About page")
    @Severity(SeverityLevel.NORMAL)
    @Override
    void correctUrl() {
        Assertions.assertEquals(Pages.ABOUT_PAGE.getUrl(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("There are six team members displayed")
    @Description("There are six team members displayed")
    @Story("User can see six team members displayed on the About Page")
    @Severity(SeverityLevel.TRIVIAL)
    void numberOfTeamMembers() {
        about.scrollToTeamMemberSection();
        TestUtils.makeScreenshot("Team members section", driver);

        Assertions.assertEquals(6, about.numberOfTeamMembers());
    }
}
