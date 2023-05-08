package com.codecool.vizsgaremek.functions;

import com.codecool.vizsgaremek.TestConstants;
import com.codecool.vizsgaremek.enums.Pages;
import com.codecool.vizsgaremek.pages.Landing;
import com.codecool.vizsgaremek.pages.Profile;
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

@Epic(TestConstants.NAME_EPIC)
@DisplayName("Existing Data Modification And Deletion Tests")
class TestExistingDataModificationAndDeletion extends TestBase {
    /**
     Instantiates a <code>WebDriver</code> from the <code>WebDriverFactory</code>, navigates to the Registration and
     Login page the test needs to be executed on, accepts the Terms and Conditions popup, logs in a built-in custom
     user then finally navigates to the newly registered user's profile page.

     @since 1.0
     */
    @Override
    @BeforeEach
    void setUp() {
        super.setUp();

        getPage(RegistrationAndLogin.class).navigateTo();
        getPage(RegistrationAndLogin.class).acceptTnC();
        getPage(RegistrationAndLogin.class).register(
                TestConstants.LOGIN_CUSTOM_USERNAME,
                TestConstants.LOGIN_CUSTOM_PASSWORD,
                TestConstants.REGISTRATION_EMAIL,
                TestConstants.REGISTRATION_DESCRIPTION);
        getPage(RegistrationAndLogin.class).login(TestConstants.LOGIN_CUSTOM_USERNAME, TestConstants.LOGIN_CUSTOM_PASSWORD);
        getPage(Landing.class).goToProfile();
    }

    @Override
    @Test
    @DisplayName(TestConstants.TEST_URL_DISPLAY_NAME)
    @Description(TestConstants.TEST_URL_DESCRIPTION)
    @Story(TestConstants.TEST_URL_STORY)
    @Severity(SeverityLevel.TRIVIAL)
    void correctUrl() {
        Assertions.assertEquals(Pages.PROFILE_PAGE.getUrl(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Modify existing User profile")
    @Description("Test whether the user's profile is modifiable")
    @Story("User's profile can be modified")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Existing Data Modification")
    void profileModification() {
        getPage(Profile.class).overwriteAccountInfo(
                TestConstants.DATA_MANIPULATION_TEST_NAME,
                TestConstants.DATA_MANIPULATION_TEST_BIO,
                TestConstants.DATA_MANIPULATION_TEST_PHONE_NO);

        Assertions.assertTrue(getPage(Profile.class).verifyProfileUpdate());
    }

    @Test
    @DisplayName("Delete existing User profile")
    @Description("Test whether the user's profile can be deleted")
    @Story("User's profile can be deleted")
    @Severity(SeverityLevel.TRIVIAL)
    @Feature("Existing Data Deletion")
    void deleteProfile() {
        getPage(Profile.class).deleteAccount();
        getPage(RegistrationAndLogin.class).login(TestConstants.LOGIN_CUSTOM_USERNAME, TestConstants.LOGIN_CUSTOM_PASSWORD);

        Assertions.assertFalse(getPage(RegistrationAndLogin.class).verifySuccessfulLogin());
    }
}
