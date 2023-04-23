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

@Feature("Registration and Login")
@DisplayName("Registration and Login Tests")
class RegistrationAndLoginTest extends TestBase {
    private RegistrationAndLogin registrationAndLogin;

    @BeforeEach
    @Override
    void setUp() {
        super.setUp();

        registrationAndLogin = new RegistrationAndLogin(driver);

        registrationAndLogin.navigateTo();
    }

    @Test
    @DisplayName("The correct URL is opened")
    @Description("The correct URL is opened")
    @Story("User navigates to the Registration and Login page")
    @Severity(SeverityLevel.NORMAL)
    @Override
    void correctUrl() {
        Assertions.assertEquals(Pages.REG_AND_LOGIN_PAGE.getUrl(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Registration is possible")
    @Description("Registration is possible")
    @Story("User successfully registers with valid credentials")
    @Severity(SeverityLevel.BLOCKER)
    void registration() {
        new TermsAndConditions(driver).acceptTnC();
        registrationAndLogin.registerUser();
        TestUtils.makeScreenshot("Registration successful", driver);

        Assertions.assertTrue(registrationAndLogin.verifySuccessfulRegistration());
    }

    @Test
    @DisplayName("Login is possible")
    @Description("Login is possible")
    @Story("User successfully logs in with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    void login() {
        new TermsAndConditions(driver).acceptTnC();
        registrationAndLogin.loginBuiltInUser();

        Assertions.assertEquals(Pages.LANDING_PAGE.getUrl(), driver.getCurrentUrl());
    }
}
