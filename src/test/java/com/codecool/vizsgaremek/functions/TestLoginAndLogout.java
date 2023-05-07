package com.codecool.vizsgaremek.functions;

import com.codecool.vizsgaremek.TestConstants;
import com.codecool.vizsgaremek.enums.Pages;
import com.codecool.vizsgaremek.pages.Landing;
import com.codecool.vizsgaremek.pages.RegistrationAndLogin;
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

@Epic(TestConstants.NAME_EPIC)
@DisplayName("Login and logout Tests")
class TestLoginAndLogout extends TestBase {
    /**
     Instantiates a <code>WebDriver</code> from the <code>WebDriverFactory</code>, navigates to the Registration and
     Login page the test needs to be executed on then accepts the Terms and Conditions popup.

     @since 1.0
     */
    @Override
    @BeforeEach
    void setUp() {
        super.setUp();

        getPage(RegistrationAndLogin.class).navigateTo();
        getPage(RegistrationAndLogin.class).acceptTnC();
    }

    @Override
    @Test
    @DisplayName(TestConstants.TEST_URL_DISPLAY_NAME)
    @Description(TestConstants.TEST_URL_DESCRIPTION)
    @Story(TestConstants.TEST_URL_STORY)
    @Severity(SeverityLevel.TRIVIAL)
    void correctUrl() {
        Assertions.assertEquals(Pages.REG_AND_LOGIN_PAGE.getUrl(), driver.getCurrentUrl(), "Incorrect URL");
    }

    @Test
    @DisplayName("Test login capabilities")
    @Description("Login is possible")
    @Story("User successfully logs in with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Login")
    void login() {
        getPage(RegistrationAndLogin.class).loginBuiltInUser();

        Assertions.assertTrue(getPage(Landing.class).isUserLoggedIn(), "User failed to log in");
        Assertions.assertEquals(Pages.LANDING_PAGE.getUrl(), driver.getCurrentUrl(), "Login failed with valid credentials");
    }

    @Test
    @DisplayName("Test logout capabilities")
    @Description("Logout is possible")
    @Story("User successfully logs out")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Logout")
    void logout() {
        getPage(RegistrationAndLogin.class).loginBuiltInUser();

        Assertions.assertTrue(getPage(Landing.class).isUserLoggedIn(), "Login failed");

        getPage(Landing.class).logoutUser();

        Assertions.assertFalse(getPage(Landing.class).isUserLoggedIn(), "Logout failed");
    }
}
