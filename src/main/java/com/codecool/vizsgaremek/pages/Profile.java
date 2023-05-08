package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.enums.Pages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Profile extends Page {
    private static final By INPUT_NAME = By.id("name");
    private static final By INPUT_BIO = By.id("bio");
    private static final By INPUT_PHONE_NUMBER = By.id("phone-number");
    private static final By BUTTON_SAVE_PROFILE = By.xpath("//*[@onclick='editUser()']");
    private static final By BUTTON_DELETE_PROFILE1 = By.xpath("//*[@onclick='showRealDeleteAccBtn()']");
    private static final By BUTTON_DELETE_PROFILE2 = By.id("delete-account-btn");
    private static final By TEXT_CONFIRMATION = By.id("edit-alert");

    public Profile(WebDriver driver) {
        super(driver, Pages.PROFILE_PAGE.getUrl());
    }

    @Step("Overwrite current account information")
    public void overwriteAccountInfo(String name, String bio, String phoneNo) {
        findElementOnPage(INPUT_NAME).sendKeys(name);
        findElementOnPage(INPUT_BIO).sendKeys(bio);
        findElementOnPage(INPUT_PHONE_NUMBER).sendKeys(phoneNo);
        findElementOnPage(BUTTON_SAVE_PROFILE).click();
    }

    @Step("Verify whether the profile update was successful")
    public boolean verifyProfileUpdate() {
        return findElementOnPage(TEXT_CONFIRMATION).getText().equals("Profile Edited!");
    }

    @Step("Delete current account")
    public void deleteAccount() {
        findElementOnPage(BUTTON_DELETE_PROFILE1).click();
        findElementOnPage(BUTTON_DELETE_PROFILE2).click();
    }
}
