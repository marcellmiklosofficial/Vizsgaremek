package com.codecool.vizsgaremek.functions;

import com.codecool.vizsgaremek.enums.Pages;
import com.codecool.vizsgaremek.pages.Landing;
import com.codecool.vizsgaremek.pages.RegistrationAndLogin;
import com.codecool.vizsgaremek.pages.TermsAndConditions;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Feature("Login and logout")
@DisplayName("Login and logout Tests")
class TestLoginAndLogout extends TestBase {
    @Override
    @BeforeEach
    void setUp() {
        super.setUp();

        getPage(TermsAndConditions.class).navigateTo();
        getPage(TermsAndConditions.class).acceptTnC();
    }

    @Override
    @Test
    @DisplayName("The correct URL is opened")
    @Description("The correct URL is opened")
    void correctUrl() {
        Assertions.assertEquals(Pages.REG_AND_LOGIN_PAGE.getUrl(), driver.getCurrentUrl(), "Incorrect URL");
    }

    @Test
    @DisplayName("Login is possible")
    @Description("Login is possible")
    @Story("User successfully logs in with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    void login() {
        getPage(RegistrationAndLogin.class).loginBuiltInUser();

        Assertions.assertTrue(getPage(Landing.class).isUserLoggedIn(), "User failed to log in");
        Assertions.assertEquals(Pages.LANDING_PAGE.getUrl(), driver.getCurrentUrl(), "Login failed with valid credentials");
    }

    @Test
    @DisplayName("Logout is possible")
    @Description("Logout is possible")
    @Story("User successfully logs out")
    @Severity(SeverityLevel.CRITICAL)
    void logout() {
        getPage(RegistrationAndLogin.class).loginBuiltInUser();

        Assertions.assertTrue(getPage(Landing.class).isUserLoggedIn(), "Login failed");

        getPage(Landing.class).logoutUser();

        Assertions.assertFalse(getPage(Landing.class).isUserLoggedIn(), "Logout failed");
    }
}
