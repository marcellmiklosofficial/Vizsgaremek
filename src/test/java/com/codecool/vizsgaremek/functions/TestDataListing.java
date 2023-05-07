package com.codecool.vizsgaremek.functions;

import com.codecool.vizsgaremek.TestUtils;
import com.codecool.vizsgaremek.enums.Pages;
import com.codecool.vizsgaremek.i18n.TestResources;
import com.codecool.vizsgaremek.pages.About;
import com.codecool.vizsgaremek.pages.Landing;
import io.qameta.allure.Description;
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

@Feature("Data Listing")
@DisplayName("Data Listing Tests")
class TestDataListing extends TestBase {
    @Override
    @BeforeEach
    void setUp() {
        super.setUp();

        getPage(About.class).navigateTo();
    }

    @Override
    @Test
    @DisplayName("The correct URL is opened")
    @Description("The correct URL is opened")
    void correctUrl() {
        Assertions.assertEquals(Pages.ABOUT_PAGE.getUrl(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("There are six team members displayed")
    @Description("There are six team members displayed")
    @Story("User can see six team members displayed on the About Page")
    @Severity(SeverityLevel.TRIVIAL)
    void numberOfTeamMembers() {
        getPage(About.class).scrollToTeamMemberSection();
        TestUtils.makeScreenshot("Team members section", driver);

        Assertions.assertEquals(6, getPage(About.class).numberOfTeamMembers());
    }

    @Test
    @DisplayName("Team members' names are correct")
    @Description("Team members' names are correct")
    @Story("User can see correct names displayed on the About Page")
    @Severity(SeverityLevel.NORMAL)
    void correctMembersDetails() {
        List<String> testData = Arrays.asList("PABLO ESCOBAR", "MONTINO RIAU", "ALEX NAASRI", "HONGMAN CHIOA", "SANTIO ANDRESS", "RAMESH PAUL");

        Assertions.assertEquals(testData, getPage(About.class).getTeamMembersNames());
    }

    @Test
    @DisplayName("The navbar contains all items")
    @Description("The navbar contains all items")
    @Story("User checks the navbar for all items present")
    @Severity(SeverityLevel.NORMAL)
    void verifyNavBarItems() {
        List<String> navBarItemLabels = Arrays.asList(
                TestResources.getResource(TestResources.ResourceKeys.NAV_BAR_TEXT_HOME),
                TestResources.getResource(TestResources.ResourceKeys.NAV_BAR_TEXT_ABOUT),
                TestResources.getResource(TestResources.ResourceKeys.NAV_BAR_TEXT_PORTFOLIO),
                TestResources.getResource(TestResources.ResourceKeys.NAV_BAR_TEXT_BLOG),
                TestResources.getResource(TestResources.ResourceKeys.NAV_BAR_TEXT_GETINTOUCH));

        Assertions.assertTrue(getPage(Landing.class).verifyNavMenuLabels(navBarItemLabels));
    }
}
