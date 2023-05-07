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
    @DisplayName("Team members' details are correct")
    @Description("Team members' details are correct")
    @Story("User can see correct details displayed on the About Page")
    @Severity(SeverityLevel.NORMAL)
    void correctMembersDetails() throws IOException {
        JSONObject jsonObject = new JSONObject(Files.readString(Path.of("src/test/resources/members_test_data.json")));

        List<String> testNames = jsonObject.keySet().stream().sorted().toList();
        List<String> testOccupations = jsonObject.toMap().values().stream().map(Object::toString).sorted().toList();

        Assertions.assertEquals(testNames, getPage(About.class).getTeamMembersNames().stream().sorted().toList());
        Assertions.assertEquals(testOccupations, getPage(About.class).getTeamMembersOccupations().stream().sorted().toList());
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
