package cool.code.vizsgaremek.pages;

import code.cool.vizsgaremek.TestUtils;
import cool.code.vizsgaremek.WebDriverFactory;
import cool.code.vizsgaremek.enums.Pages;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

@Epic("Roxo webpage")
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
    @Description("The correct URL is opened")
    @Story("User navigates to the Registration and Login page")
    @Severity(SeverityLevel.NORMAL)
    void correctUrl() {
        Assertions.assertEquals(Pages.REG_AND_LOGIN_PAGE.getUrl(), driver.getCurrentUrl());
    }

    @Test
    @Description("Registration is possible")
    @Story("User successfully registers with valid credentials")
    @Severity(SeverityLevel.BLOCKER)
    void registration() {
        new TermsAndConditions(driver).acceptTnC();
        registrationAndLogin.registerUser();
        TestUtils.makeScreenshot("Registration successful", driver);

        Assertions.assertTrue(registrationAndLogin.verifySuccessfulRegistration());
    }

    @Nested
    @DisplayName("After registration is successful")
    class AfterRegistered {
        @Test
        @Description("Login is possible")
        @Story("User successfully logs in with valid credentials")
        @Severity(SeverityLevel.CRITICAL)
        void login() {
            new TermsAndConditions(driver).acceptTnC();
            registrationAndLogin.registerUser();
            driver.navigate().refresh();
            registrationAndLogin.loginUser();

            Assertions.assertEquals(Pages.LANDING_PAGE.getUrl(), driver.getCurrentUrl());
        }
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}