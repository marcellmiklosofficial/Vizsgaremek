package com.codecool.vizsgaremek.functions;

import com.codecool.vizsgaremek.TestConstants;
import com.codecool.vizsgaremek.TestUtils;
import com.codecool.vizsgaremek.enums.Pages;
import com.codecool.vizsgaremek.i18n.TestResources;
import com.codecool.vizsgaremek.pages.About;
import com.codecool.vizsgaremek.pages.Landing;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

@Epic(TestConstants.NAME_EPIC)
@Feature("Data Listing")
@DisplayName("Data Listing Tests")
class TestDataListing extends TestBase {
    /**
     Instantiates a <code>WebDriver</code> from the <code>WebDriverFactory</code> and navigates to the About page the
     test needs to be executed on.

     @since 1.0
     */
    @Override
    @BeforeEach
    void setUp() {
        super.setUp();

        getPage(About.class).navigateTo();
    }

    @Override
    @Test
    @DisplayName(TestConstants.TEST_URL_DISPLAY_NAME)
    @Description(TestConstants.TEST_URL_DESCRIPTION)
    @Story(TestConstants.TEST_URL_STORY)
    @Severity(SeverityLevel.TRIVIAL)
    void correctUrl() {
        Assertions.assertEquals(Pages.ABOUT_PAGE.getUrl(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Test number of team members")
    @Description("Test to see if there are six team members displayed on the page")
    @Story("There are six Team Members displayed on the About Page")
    @Severity(SeverityLevel.NORMAL)
    void numberOfTeamMembers() {
        getPage(About.class).scrollToTeamMemberSection();
        TestUtils.makeScreenshot("Team members section", driver);

        Assertions.assertEquals(6, getPage(About.class).numberOfTeamMembers());
    }

    @Test
    @DisplayName("Test details (name, occupation) of Team Members")
    @Description("Testing the name and occupation of each Team Member")
    @Story("Correct details are displayed on the About Page")
    @Severity(SeverityLevel.NORMAL)
    void correctMembersDetails() throws IOException {
        JSONObject jsonObject = new JSONObject(Files.readString(Path.of("src/test/resources/members_test_data.json")));

        List<String> testNames = jsonObject.keySet().stream().sorted().toList();
        List<String> testOccupations = jsonObject.toMap().values().stream().map(Object::toString).sorted().toList();

        Assertions.assertEquals(testNames, getPage(About.class).getTeamMembersNames().stream().sorted().toList());
        Assertions.assertEquals(testOccupations, getPage(About.class).getTeamMembersOccupations().stream().sorted().toList());
    }

    @Test
    @DisplayName("Navbar content test")
    @Description("The navbar contains all items specified, possible multilingual test")
    @Story("User checks the navbar for all items present")
    @Severity(SeverityLevel.MINOR)
    void verifyNavBarItems() {
        // This list demonstrates the possibility of a multilingual (i18n) check of the page's contents
        List<String> navBarItemLabels = Arrays.asList(
                TestResources.getResource(TestResources.ResourceKeys.NAV_BAR_TEXT_HOME),
                TestResources.getResource(TestResources.ResourceKeys.NAV_BAR_TEXT_ABOUT),
                TestResources.getResource(TestResources.ResourceKeys.NAV_BAR_TEXT_PORTFOLIO),
                TestResources.getResource(TestResources.ResourceKeys.NAV_BAR_TEXT_BLOG),
                TestResources.getResource(TestResources.ResourceKeys.NAV_BAR_TEXT_GETINTOUCH));

        Assertions.assertTrue(getPage(Landing.class).verifyNavMenuLabels(navBarItemLabels));
    }
}
