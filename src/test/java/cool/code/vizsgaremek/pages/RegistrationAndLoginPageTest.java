package cool.code.vizsgaremek.pages;

import cool.code.vizsgaremek.WebDriverFactory;
import cool.code.vizsgaremek.enums.Pages;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

@Epic("Roxo webpage")
@Feature("Registration and Login")
class RegistrationAndLoginPageTest {
    // VARIABLES
    private WebDriver driver;
    private RegistrationAndLoginPage registrationAndLoginPage;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        driver = WebDriverFactory.getWebDriver(WebDriverFactory.Type.GITHUB);
        registrationAndLoginPage = new RegistrationAndLoginPage(driver);

        registrationAndLoginPage.navigateTo();
    }

    @Test
    @Description("The correct URL is opened")
    @Story("User navigates to the Registration and Login page")
    @Severity(SeverityLevel.NORMAL)
    void correctUrl() {
        Assertions.assertEquals(Pages.REG_AND_LOGIN_PAGE.getUrl(), driver.getCurrentUrl());
    }

    @Test
    @Description("The \"Terms and Conditions\" pop up is displayed")
    @Story("User navigated to the Registration and Login page and Terms and Conditions is displayed")
    @Severity(SeverityLevel.NORMAL)
    void displayTnC() {
        Assertions.assertTrue(registrationAndLoginPage.verifyVisibilityOfTnCPopup());
    }

    @Test
    @Description("The \"Terms and Conditions\" pop up can be accepted")
    @Story("User navigated to the Registration and Login page and the displayed Terms and Conditions disappears after clicking on the Accept button")
    @Severity(SeverityLevel.TRIVIAL)
    void acceptTnC() {
        registrationAndLoginPage.acceptTnC();

        Assertions.assertFalse(registrationAndLoginPage.verifyVisibilityOfTnCPopup());
    }

    @Test
    @Description("Registration is possible")
    @Story("User successfully registers with valid credentials")
    @Severity(SeverityLevel.BLOCKER)
    void registration() {
        registrationAndLoginPage.acceptTnC();
        registrationAndLoginPage.registerUser();

        Assertions.assertTrue(registrationAndLoginPage.verifySuccessfulRegistration());
    }

    @Test
    @Description("Login is possible")
    @Story("User successfully logs in with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    void login() {
        registrationAndLoginPage.acceptTnC();
        registrationAndLoginPage.registerUser();
        driver.navigate().refresh();
        registrationAndLoginPage.loginUser();

        Assertions.assertEquals(Pages.LANDING_PAGE.getUrl(), driver.getCurrentUrl());
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}