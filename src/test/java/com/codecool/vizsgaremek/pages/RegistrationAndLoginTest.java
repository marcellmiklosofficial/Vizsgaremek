package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.TestConstants;
import com.codecool.vizsgaremek.TestUtils;
import com.codecool.vizsgaremek.WebDriverFactory;
import com.codecool.vizsgaremek.enums.Pages;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

@Epic(TestConstants.NAME_EPIC)
@Feature("Registration and Login")
@DisplayName("Registration and Login Tests")
class RegistrationAndLoginTest {
    // VARIABLES
    private WebDriver driver;
    private RegistrationAndLogin registrationAndLogin;

    @BeforeEach
    void setUp() {
        driver = WebDriverFactory.getWebDriver();
        registrationAndLogin = new RegistrationAndLogin(driver);

        registrationAndLogin.navigateTo();
    }

    @Test
    @DisplayName("The correct URL is opened")
    @Description("The correct URL is opened")
    @Story("User navigates to the Registration and Login page")
    @Severity(SeverityLevel.NORMAL)
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

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
