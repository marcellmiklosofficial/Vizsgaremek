package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.enums.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class Contact extends Page {
    private static final By INPUT_NAME_FIRST = By.id("first-name");
    private static final By INPUT_NAME_LAST = By.id("last-name");
    private static final By INPUT_EMAIL = By.id("email");
    private static final By LIST_PROJECT_TYPE = By.id("projectType");
    private static final By TEXTAREA_ABOUT_PROJECT = By.id("aboutProject");
    private static final By BUTTON_SEND = By.id("contact-form-button");
    private static final By TEXT_STATUS = By.id("contact-form-status");

    public Contact(WebDriver driver) {
        super(driver, Pages.CONTACT_PAGE.getUrl());
    }

    public void sendMessage(String firstname, String lastname, String email, String projectType, String about) {
        findElementOnPage(INPUT_NAME_FIRST).sendKeys(firstname);
        findElementOnPage(INPUT_NAME_LAST).sendKeys(lastname);
        findElementOnPage(INPUT_EMAIL).sendKeys(email);
        new Select(findElementOnPage(LIST_PROJECT_TYPE)).selectByVisibleText(projectType);
        findElementOnPage(TEXTAREA_ABOUT_PROJECT).sendKeys(about);
        findElementOnPage(BUTTON_SEND).click();
    }

    public boolean statusVisible() {
        return findElementOnPage(TEXT_STATUS).isDisplayed();
    }
}
