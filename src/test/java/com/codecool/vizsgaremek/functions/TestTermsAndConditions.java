package com.codecool.vizsgaremek.functions;

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

@Feature("Terms and Conditions")
@DisplayName("Terms and Conditions Tests")
class TestTermsAndConditions extends TestBase {
    @Override
    @BeforeEach
    void setUp() {
        super.setUp();

        getTermsAndConditions().navigateTo();
    }

    @Override
    @Test
    @DisplayName("The correct URL is opened")
    @Description("The correct URL is opened")
    void correctUrl() {
        Assertions.assertEquals(Pages.REG_AND_LOGIN_PAGE.getUrl(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("The \"Terms and Conditions\" pop up is displayed")
    @Description("The \"Terms and Conditions\" pop up is displayed")
    @Story("User navigated to the Registration and Login page and Terms and Conditions is displayed")
    @Severity(SeverityLevel.NORMAL)
    void tnCVisible() {
        Assertions.assertTrue(getTermsAndConditions().verifyVisibilityOfTnCPopup());
    }

    @Test
    @DisplayName("The \"Terms and Conditions\" pop up can be accepted")
    @Description("The \"Terms and Conditions\" pop up can be accepted")
    @Story("User navigated to the Registration and Login page and the displayed Terms and Conditions disappears after clicking on the Accept button")
    @Severity(SeverityLevel.TRIVIAL)
    void acceptTnC() {
        getTermsAndConditions().acceptTnC();

        Assertions.assertFalse(getTermsAndConditions().verifyVisibilityOfTnCPopup());
    }
}
