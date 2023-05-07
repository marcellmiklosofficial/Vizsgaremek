package com.codecool.vizsgaremek.functions;

import com.codecool.vizsgaremek.TestUtils;
import com.codecool.vizsgaremek.enums.Pages;
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

@Feature("Registration")
@DisplayName("Registration Tests")
class TestRegistration extends TestBase {
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
        Assertions.assertEquals(Pages.REG_AND_LOGIN_PAGE.getUrl(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Registration is possible")
    @Description("Registration is possible")
    @Story("User successfully registers with valid credentials")
    @Severity(SeverityLevel.BLOCKER)
    void registration() {
        getPage(RegistrationAndLogin.class).registerUser();
        TestUtils.makeScreenshot("Registration successful", driver);

        Assertions.assertTrue(getPage(RegistrationAndLogin.class).verifySuccessfulRegistration());
    }
}
