package com.codecool.vizsgaremek.functions;

import com.codecool.vizsgaremek.TestConstants;
import com.codecool.vizsgaremek.enums.Pages;
import com.codecool.vizsgaremek.pages.Contact;
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
@Feature("New Data Entry")
@DisplayName("New Data Entry Tests")
class TestNewDataEntry extends TestBase {
    /**
     Instantiates a <code>WebDriver</code> from the <code>WebDriverFactory</code> and navigates to the Contact page the
     test needs to be executed on.

     @since 1.0
     */
    @Override
    @BeforeEach
    void setUp() {
        super.setUp();

        getPage(Contact.class).navigateTo();
    }

    @Override
    @Test
    @DisplayName(TestConstants.TEST_URL_DISPLAY_NAME)
    @Description(TestConstants.TEST_URL_DESCRIPTION)
    @Story(TestConstants.TEST_URL_STORY)
    @Severity(SeverityLevel.TRIVIAL)
    void correctUrl() {
        Assertions.assertEquals(Pages.CONTACT_PAGE.getUrl(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Send message test")
    @Description("Test to send a message on the Contact form")
    @Story("Message can be sent from the contact form and confirmation pops up")
    @Severity(SeverityLevel.NORMAL)
    void sendMessage() {
        getPage(Contact.class).sendMessage(
                TestConstants.NEW_DATA_ENTRY_FIRSTNAME,
                TestConstants.NEW_DATA_ENTRY_LASTNAME,
                TestConstants.NEW_DATA_ENTRY_EMAIL,
                TestConstants.NEW_DATA_ENTRY_PROJECT_TYPE,
                TestConstants.NEW_DATA_ENTRY_ABOUT);

        Assertions.assertEquals(TestConstants.NEW_DATA_ENTRY_ALERT_MESSAGE, driver.switchTo().alert().getText());

        driver.switchTo().alert().accept();

        Assertions.assertFalse(getPage(Contact.class).isMessageSentTextVisible());
    }
}
