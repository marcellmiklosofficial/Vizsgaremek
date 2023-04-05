package cool.code.vizsgaremek.pages;

import code.cool.vizsgaremek.TestUtils;
import cool.code.vizsgaremek.WebDriverFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

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
    @Description("The \"Terms and Conditions\" pop up is displayed")
    @Story("User navigated to the Registration and Login page and Terms and Conditions is displayed")
    @Severity(SeverityLevel.NORMAL)
    void displayTnC() {
        TestUtils.makeScreenshot("The \"Terms and Conditions\" pop up is displayed", driver);

        Assertions.assertTrue(termsAndConditions.verifyVisibilityOfTnCPopup());
    }

    @Test
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