package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.enums.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationAndLogin extends Page {
    // VARIABLES
    // - User
    private static final String USERNAME = "user_name";
    private static final String PASSWORD = "supersecretpassword";
    private static final String BUILT_IN_USERNAME = "lovasia";
    private static final String BUILT_IN_PASSWORD = "kispal123";
    private static final String EMAIL = "lastsamurai@earth.com";
    private static final String DESCRIPTION = "I need to think of something smart.";

    // LOCATORS
    // - Registration
    private static final By BUTTON_REGISTER_FORM = By.id("register-form-button");
    private static final By INPUT_USERNAME_REGISTRATION = By.id("register-username");
    private static final By INPUT_PASSWORD_REGISTRATION = By.id("register-password");
    private static final By INPUT_EMAIL = By.id("register-email");
    private static final By INPUT_DESCRIPTION = By.id("register-description");
    private static final By BUTTON_REGISTER = By.xpath("//button[@onclick='registerUser()']");
    private static final By TEXT_CONFIRMATION_MESSAGE = By.id("register-alert");

    // - Login
    private static final By BUTTON_LOGIN_FORM = By.id("login-form-button");
    private static final By INPUT_USERNAME_LOGIN = By.id("email");
    private static final By INPUT_PASSWORD_LOGIN = By.id("password");
    private static final By BUTTON_LOGIN = By.xpath("//button[@onclick='myFunction()']");

    public RegistrationAndLogin(WebDriver driver) {
        super(driver, Pages.REG_AND_LOGIN_PAGE.getUrl());
    }

    public void registerUser() {
        findElementOnPage(BUTTON_REGISTER_FORM).click();
        findElementOnPage(INPUT_USERNAME_REGISTRATION).sendKeys(USERNAME);
        findElementOnPage(INPUT_PASSWORD_REGISTRATION).sendKeys(PASSWORD);
        findElementOnPage(INPUT_EMAIL).sendKeys(EMAIL);
        findElementOnPage(INPUT_DESCRIPTION).sendKeys(DESCRIPTION);
        findElementOnPage(BUTTON_REGISTER).click();
    }

    public boolean verifySuccessfulRegistration() {
        return findElementOnPage(TEXT_CONFIRMATION_MESSAGE).getText().equals("User registered!");
    }

    public void loginUser() {
        findElementOnPage(BUTTON_LOGIN_FORM).click();
        findElementOnPage(INPUT_USERNAME_LOGIN).sendKeys(BUILT_IN_USERNAME);
        findElementOnPage(INPUT_PASSWORD_LOGIN).sendKeys(BUILT_IN_PASSWORD);
        findElementOnPage(BUTTON_LOGIN).click();
    }
}
