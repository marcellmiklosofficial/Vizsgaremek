package com.codecool.vizsgaremek.functions;

import com.codecool.vizsgaremek.TestConstants;
import com.codecool.vizsgaremek.WebDriverFactory;
import com.codecool.vizsgaremek.pages.About;
import com.codecool.vizsgaremek.pages.Blog;
import com.codecool.vizsgaremek.pages.Landing;
import com.codecool.vizsgaremek.pages.RegistrationAndLogin;
import com.codecool.vizsgaremek.pages.TermsAndConditions;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

@Epic(TestConstants.NAME_EPIC)
abstract class TestBase {
    protected WebDriver driver;

    private RegistrationAndLogin registrationAndLogin;
    private TermsAndConditions termsAndConditions;
    private Landing landing;
    private About about;
    private Blog blog;

    @BeforeEach
    void setUp() {
        driver = WebDriverFactory.getWebDriver();
    }

    @SuppressWarnings("unused")
    @Story("User navigates to the correct URL")
    @Severity(SeverityLevel.NORMAL)
    abstract void correctUrl();

    protected final RegistrationAndLogin getRegistrationAndLogin() {
        if (registrationAndLogin == null) {
            registrationAndLogin = new RegistrationAndLogin(driver);
        }

        return registrationAndLogin;
    }

    protected final TermsAndConditions getTermsAndConditions() {
        if (termsAndConditions == null) {
            termsAndConditions = new TermsAndConditions(driver);
        }

        return termsAndConditions;
    }

    protected final Landing getLanding() {
        if (landing == null) {
            landing = new Landing(driver);
        }

        return landing;
    }

    protected final About getAbout() {
        if (about == null) {
            about = new About(driver);
        }

        return about;
    }

    protected final Blog getBlog() {
        if (blog == null) {
            blog = new Blog(driver);
        }

        return blog;
    }

    @AfterEach
    final void tearDown() {
        driver.quit();
    }
}
