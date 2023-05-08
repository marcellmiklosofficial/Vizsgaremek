package com.codecool.vizsgaremek.functions;

import com.codecool.vizsgaremek.TestConstants;
import com.codecool.vizsgaremek.enums.Pages;
import com.codecool.vizsgaremek.pages.RegistrationAndLogin;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

@Epic(TestConstants.NAME_EPIC)
@Feature("Registration")
@DisplayName("Registration Tests")
class TestRegistration extends TestBase {
    /**
     Instantiates a <code>WebDriver</code> from the <code>WebDriverFactory</code>, navigates to the Registration and
     Login page the test needs to be executed on then accepts the Terms and Conditions popup.

     @since 1.0
     */
    @Override
    @BeforeEach
    void setUp() {
        super.setUp();

        getPage(RegistrationAndLogin.class).navigateTo();
        getPage(RegistrationAndLogin.class).acceptTnC();
    }

    @Override
    @Test
    @DisplayName(TestConstants.TEST_URL_DISPLAY_NAME)
    @Description(TestConstants.TEST_URL_DESCRIPTION)
    @Story(TestConstants.TEST_URL_STORY)
    @Severity(SeverityLevel.TRIVIAL)
    void correctUrl() {
        Assertions.assertEquals(Pages.REG_AND_LOGIN_PAGE.getUrl(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Registration is possible")
    @Description("Registers multiple users from \"test_logins.yml\"")
    @Story("Successful register with valid credentials multiple times")
    @Severity(SeverityLevel.BLOCKER)
    void multipleRegistration() throws IOException {
        List<Map<String, Object>> logins = new Yaml().load(Files.readString(Path.of("src/test/resources/test_logins.yml")));

        for (Map<String, Object> login : logins) {
            getPage(RegistrationAndLogin.class).register(
                    login.get("name").toString(),
                    login.get("password").toString(),
                    login.get("email").toString(),
                    login.get("description").toString());

            Assertions.assertTrue(getPage(RegistrationAndLogin.class).verifySuccessfulRegistration());
            Assertions.assertFalse(getPage(RegistrationAndLogin.class).isLoggedIn());

            driver.navigate().refresh();
        }
    }
}
