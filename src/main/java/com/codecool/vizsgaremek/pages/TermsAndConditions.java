package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.enums.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TermsAndConditions extends Page {
    // LOCATORS
    // - Terms and Conditions
    private static final By POPUP_TERMS_AND_CONDITIONS = By.className("popup");
    private static final By BUTTON_ACCEPT_TERMS_AND_CONDITIONS = By.id("terms-and-conditions-button");

    public TermsAndConditions(WebDriver driver) {
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
}
