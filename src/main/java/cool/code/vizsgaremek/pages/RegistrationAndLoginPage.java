package cool.code.vizsgaremek.pages;

import cool.code.vizsgaremek.enums.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegistrationAndLoginPage extends Page {
    // VARIABLES
    // - User
    private static final String USERNAME = "user_name";
    private static final String PASSWORD = "supersecretpassword";
    private static final String EMAIL = "lastsamurai@earth.com";
    private static final String DESCRIPTION = "I need to think of something smart.";

    // LOCATORS
    // - Terms and Conditions
    private static final By POPUP_TERMS_AND_CONDITIONS = By.className("popoup");
    private static final By BUTTON_ACCEPT_TERMS_AND_CONDITIONS = By.id("terms-and-conditions-button");

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

    public RegistrationAndLoginPage(WebDriver driver) {
        super(driver, Pages.REG_AND_LOGIN_PAGE.getUrl());
    }

    public boolean verifyVisibilityOfTnCPopup() {
        return findElementOnPage(POPUP_TERMS_AND_CONDITIONS).isDisplayed();
    }

    public void acceptTnC() {
        WebElement acceptTnCButton = findElementOnPage(BUTTON_ACCEPT_TERMS_AND_CONDITIONS);

        if (acceptTnCButton.isDisplayed()) {
            acceptTnCButton.click();
        }
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
        findElementOnPage(INPUT_USERNAME_LOGIN).sendKeys(USERNAME);
        findElementOnPage(INPUT_PASSWORD_LOGIN).sendKeys(PASSWORD);
        findElementOnPage(BUTTON_LOGIN).click();
    }
}
