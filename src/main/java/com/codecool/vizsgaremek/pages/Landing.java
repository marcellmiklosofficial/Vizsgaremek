package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.enums.Pages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Landing extends Page {
    // LOCATORS
    // - Nav-menu
    private static final By LIST_NAV_MENU_ITEM = By.className("nav-item");
    private static final By BUTTON_PROFILE = By.id("profile-btn");

    public Landing(WebDriver driver) {
        super(driver, Pages.LANDING_PAGE.getUrl());
    }

    @Step("Gather current nav-menu items displayed on the current page")
    public List<String> getNavMenuLabels() {
        return findElementsOnPage(LIST_NAV_MENU_ITEM).stream().map(WebElement::getText).toList();
    }

    @Step("Click on the profile link")
    public void goToProfile() {
        findElementOnPage(BUTTON_PROFILE).click();
    }
}
