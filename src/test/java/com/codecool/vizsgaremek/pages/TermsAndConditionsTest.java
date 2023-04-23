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

@Feature("Terms and Conditions")
@DisplayName("Terms and Conditions Tests")
class TermsAndConditionsTest extends TestBase {
    private TermsAndConditions termsAndConditions;

    @BeforeEach
    @Override
    void setUp() {
        super.setUp();

        termsAndConditions = new TermsAndConditions(driver);

        termsAndConditions.navigateTo();
    }

    @Test
    @DisplayName("The correct URL is opened")
    @Description("The correct URL is opened")
    @Story("User navigates to the Main page")
    @Severity(SeverityLevel.NORMAL)
    @Override
    void correctUrl() {
        Assertions.assertEquals(Pages.REG_AND_LOGIN_PAGE.getUrl(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("The \"Terms and Conditions\" pop up is displayed")
    @Description("The \"Terms and Conditions\" pop up is displayed")
    @Story("User navigated to the Registration and Login page and Terms and Conditions is displayed")
    @Severity(SeverityLevel.NORMAL)
    void displayTnC() {
        TestUtils.makeScreenshot("The \"Terms and Conditions\" pop up is displayed", driver);

        Assertions.assertTrue(termsAndConditions.verifyVisibilityOfTnCPopup());
    }

    @Test
    @DisplayName("The \"Terms and Conditions\" pop up can be accepted")
    @Description("The \"Terms and Conditions\" pop up can be accepted")
    @Story("User navigated to the Registration and Login page and the displayed Terms and Conditions disappears after clicking on the Accept button")
    @Severity(SeverityLevel.TRIVIAL)
    void acceptTnC() {
        termsAndConditions.acceptTnC();
        TestUtils.makeScreenshot("The \"Terms and Conditions\" pop up can be accepted", driver);

        Assertions.assertFalse(termsAndConditions.verifyVisibilityOfTnCPopup());
    }
}
