package cool.code.vizsgaremek.pages;

import code.cool.vizsgaremek.TestConstants;
import code.cool.vizsgaremek.WebDriverFactory;
import code.cool.vizsgaremek.i18n.TestResources;
import cool.code.vizsgaremek.enums.Pages;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.List;

@Epic(TestConstants.NAME_EPIC)
@Feature("Landing page")
@DisplayName("Landing page Tests")
class LandingTest {
    private WebDriver driver;
    private Landing landing;

    @BeforeEach
    void setUp() {
        driver = WebDriverFactory.getWebDriver();
        landing = new Landing(driver);

        landing.navigateTo();
        new TermsAndConditions(driver).acceptTnC();
        new RegistrationAndLogin(driver).loginBuiltInUser();
    }

    @Test
    @DisplayName("The correct URL is opened")
    @Description("The correct URL is opened")
    @Story("User navigates to the Landing page")
    @Severity(SeverityLevel.NORMAL)
    void correctUrl() {
        Assertions.assertEquals(Pages.LANDING_PAGE.getUrl(), driver.getCurrentUrl());
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
                TestResources.getResource(TestResources.ResourceKeys.NAV_BAR_TEXT_GETINTOUCH),
                TestResources.getResource(TestResources.ResourceKeys.NAV_BAR_TEXT_PROFILE),
                TestResources.getResource(TestResources.ResourceKeys.NAV_BAR_TEXT_LOGOUT));

        Assertions.assertTrue(landing.verifyNavMenuLabels(navBarItemLabels));
    }

    @Test
    @DisplayName("The logged in user is able to log out")
    @Description("The logged in user is able to log out")
    @Story("User logs out after successfully logging in")
    @Severity(SeverityLevel.CRITICAL)
    void logout() {
        Assertions.assertTrue(landing.isUserLoggedIn());

        landing.logoutUser();

        Assertions.assertFalse(landing.isUserLoggedIn());
    }

    @Test
    @DisplayName("Introduction section has correct spelling")
    @Description("Introduction section has correct spelling")
    @Story("Introduction text doesn't contain any typos")
    @Severity(SeverityLevel.TRIVIAL)
    void introductionText() {
        Assertions.assertTrue(landing.verifyIntroductionText(TestResources.getResource(TestResources.ResourceKeys.LANDING_INTRODUCTION_SECTION)));
    }

    @Test
    @DisplayName("Counter section has correct title")
    @Description("Counter section has correct title")
    @Story("Counter title doesn't contain any typos")
    @Severity(SeverityLevel.TRIVIAL)
    void counterTitle() {
        Assertions.assertTrue(landing.verifyCounterTitle(TestResources.getResource(TestResources.ResourceKeys.LANDING_COUNTER_TITLE)));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
