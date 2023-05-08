package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.enums.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegistrationAndLogin extends Page {
    // LOCATORS
    // - Registration
    private static final By BUTTON_REGISTER_FORM = By.id("register-form-button");
    private static final By INPUT_USERNAME_REGISTRATION = By.id("register-username");
    private static final By INPUT_PASSWORD_REGISTRATION = By.id("register-password");
    private static final By INPUT_EMAIL = By.id("register-email");
    private static final By INPUT_DESCRIPTION = By.id("register-description");
    private static final By BUTTON_REGISTER = By.xpath("//button[@onclick='registerUser()']");
    private static final By FORM_REGISTER_LOGIN = By.xpath("//*[@id='register']/*[@id='login-form-button']");
    private static final By TEXT_CONFIRMATION_MESSAGE = By.id("register-alert");

    // - Login
    private static final By INPUT_USERNAME_LOGIN = By.id("email");
    private static final By INPUT_PASSWORD_LOGIN = By.id("password");
    private static final By BUTTON_LOGIN = By.xpath("//button[@onclick='myFunction()']");
    private static final By TEXT_FAILED_LOGIN_CONFIRMATION = By.id("alert");

    // - Terms and Conditions
    private static final By POPUP_TERMS_AND_CONDITIONS = By.className("popup");
    private static final By BUTTON_ACCEPT_TERMS_AND_CONDITIONS = By.id("terms-and-conditions-button");

    public RegistrationAndLogin(WebDriver driver) {
        super(driver, Pages.REG_AND_LOGIN_PAGE.getUrl());
    }

    // Terms and Conditions
    public boolean verifyVisibilityOfTnCPopup() {
        return findElementOnPage(POPUP_TERMS_AND_CONDITIONS).isDisplayed();
    }

    public void acceptTnC() {
        WebElement acceptTnCButton = findElementOnPage(BUTTON_ACCEPT_TERMS_AND_CONDITIONS);

        if (acceptTnCButton.isDisplayed()) {
            acceptTnCButton.click();
        }
    }

    // Registration
    public void register(String username, String password, String email, String description) {
        findElementOnPage(BUTTON_REGISTER_FORM).click();
        findElementOnPage(INPUT_USERNAME_REGISTRATION).sendKeys(username);
        findElementOnPage(INPUT_PASSWORD_REGISTRATION).sendKeys(password);
        findElementOnPage(INPUT_EMAIL).sendKeys(email);
        findElementOnPage(INPUT_DESCRIPTION).sendKeys(description);
        findElementOnPage(BUTTON_REGISTER).click();
    }

    public boolean verifySuccessfulRegistration() {
        return findElementOnPage(TEXT_CONFIRMATION_MESSAGE).getText().equals("User registered!");
    }

    // Login
    public void login(String username, String password) {
        try {
            findElementOnPage(FORM_REGISTER_LOGIN).click();
        } catch (Exception e) {
            // ignored, because if it fails that only means we are already at the correct place
        }

        findElementOnPage(INPUT_USERNAME_LOGIN).sendKeys(username);
        findElementOnPage(INPUT_PASSWORD_LOGIN).sendKeys(password);
        findElementOnPage(BUTTON_LOGIN).click();
    }

    public boolean verifySuccessfulLogin() {
        return !findElementOnPage(TEXT_FAILED_LOGIN_CONFIRMATION).getText().equals("Username or Password\nis not correct!");
    }
}
