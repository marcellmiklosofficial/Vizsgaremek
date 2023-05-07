package com.codecool.vizsgaremek.functions;

import com.codecool.vizsgaremek.enums.Pages;
import com.codecool.vizsgaremek.pages.Landing;
import com.codecool.vizsgaremek.pages.Profile;
import com.codecool.vizsgaremek.pages.RegistrationAndLogin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestExistingDataModificationAndDeletion extends TestBase {
    @Override
    @BeforeEach
    void setUp() {
        super.setUp();

        getPage(RegistrationAndLogin.class).navigateTo();
        getPage(RegistrationAndLogin.class).acceptTnC();
        getPage(RegistrationAndLogin.class).registerCustomUser();
        getPage(RegistrationAndLogin.class).loginCustomUser();
        getPage(Landing.class).goToProfile();
    }

    @Override
    @Test
    void correctUrl() {
        Assertions.assertEquals(Pages.PROFILE_PAGE.getUrl(), driver.getCurrentUrl());
    }

    @Test
    void profileModification() {
        String testName = "testName";
        String testBio = "testData";
        String testPhoneNo = "testNo";

        getPage(Profile.class).overwriteAccountInfo(testName, testBio, testPhoneNo);

        Assertions.assertTrue(getPage(Profile.class).verifyProfileUpdate());
    }

    @Test
    void deleteProfile() {
        getPage(Profile.class).deleteAccount();
        getPage(RegistrationAndLogin.class).loginCustomUser();

        Assertions.assertTrue(getPage(RegistrationAndLogin.class).verifyFailedLogin());
    }
}
