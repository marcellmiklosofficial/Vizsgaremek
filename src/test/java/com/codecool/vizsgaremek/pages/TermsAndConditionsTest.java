package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.TestConstants;
import com.codecool.vizsgaremek.TestUtils;
import com.codecool.vizsgaremek.WebDriverFactory;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

@Epic(TestConstants.NAME_EPIC)
@Feature("Terms and Conditions")
@DisplayName("Terms and Conditions Tests")
class TermsAndConditionsTest {
    // VARIABLES
    private WebDriver driver;
    private TermsAndConditions termsAndConditions;

    @BeforeEach
    void setUp() {
        driver = WebDriverFactory.getWebDriver();
        termsAndConditions = new TermsAndConditions(driver);

        termsAndConditions.navigateTo();
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

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
