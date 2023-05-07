package com.codecool.vizsgaremek.functions;

import com.codecool.vizsgaremek.enums.Pages;
import com.codecool.vizsgaremek.pages.RegistrationAndLogin;
import io.qameta.allure.Description;
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

@Feature("Registration")
@DisplayName("Registration Tests")
class TestRegistration extends TestBase {
    @Override
    @BeforeEach
    void setUp() {
        super.setUp();

        getPage(RegistrationAndLogin.class).navigateTo();
        getPage(RegistrationAndLogin.class).acceptTnC();
    }

    @Override
    @Test
    @DisplayName("The correct URL is opened")
    @Description("The correct URL is opened")
    @Story("User navigates to the correct URL")
    @Severity(SeverityLevel.NORMAL)
    void correctUrl() {
        Assertions.assertEquals(Pages.REG_AND_LOGIN_PAGE.getUrl(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Registration is possible")
    @Description("Registration is possible")
    @Story("User successfully registers with valid credentials multiple times")
    @Severity(SeverityLevel.BLOCKER)
    void multipleRegistration() throws IOException {
        List<Map<String, Object>> logins = new Yaml().load(Files.readString(Path.of("src/test/resources/test_logins.yml")));

        for (Map<String, Object> login : logins) {
            getPage(RegistrationAndLogin.class).registerUser(
                    login.get("name").toString(),
                    login.get("password").toString(),
                    login.get("email").toString(),
                    login.get("description").toString());

            Assertions.assertTrue(getPage(RegistrationAndLogin.class).verifySuccessfulRegistration());
            Assertions.assertFalse(getPage(RegistrationAndLogin.class).isUserLoggedIn());

            driver.navigate().refresh();
        }
    }
}
