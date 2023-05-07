package com.codecool.vizsgaremek.functions;

import com.codecool.vizsgaremek.enums.Pages;
import com.codecool.vizsgaremek.pages.Contact;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Feature("New Data Entry")
@DisplayName("New Data Entry Tests")
class TestNewDataEntry extends TestBase {
    @Override
    @BeforeEach
    void setUp() {
        super.setUp();

        getPage(Contact.class).navigateTo();
    }

    @Override
    @Test
    @DisplayName("The correct URL is opened")
    @Description("The correct URL is opened")
    void correctUrl() {
        Assertions.assertEquals(Pages.CONTACT_PAGE.getUrl(), driver.getCurrentUrl());
    }

    @Test
    void sendMessage() {
        String testFirstname = "Peter";
        String testLastname = "Doe";
        String testEmail = "example@first.second";
        String testProjectType = "Web Design";
        String testAbout = "Lorem ipsum";
        String expectedAlertMessage = "Message sent!";

        getPage(Contact.class).sendMessage(testFirstname, testLastname, testEmail, testProjectType, testAbout);

        Assertions.assertEquals(expectedAlertMessage, driver.switchTo().alert().getText());

        driver.switchTo().alert().accept();

        Assertions.assertFalse(getPage(Contact.class).statusVisible());
    }
}
