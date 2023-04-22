package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.enums.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Landing extends Page {
    // LOCATORS
    // - Nav-menu
    private static final By LIST_NAV_MENU_ITEM = By.className("nav-item");
    private static final By BUTTON_NAV_MENU_LOGOUT = By.id("logout-link");

    // - Contents
    private static final By TEXT_INTRODUCTION = By.xpath("//h6/following-sibling::h1");
    private static final By TEXT_COUNTER_TITLE = By.className("text-white");

    public Landing(WebDriver driver) {
        super(driver, Pages.LANDING_PAGE.getUrl());
    }

    public boolean verifyNavMenuLabels(List<String> navMenuItems) {
        List<String> navMenuItemsOnPage = new ArrayList<>(findElementsOnPage(LIST_NAV_MENU_ITEM).stream().map(WebElement::getText).toList());

        Collections.sort(navMenuItems);
        Collections.sort(navMenuItemsOnPage);

        return navMenuItems.equals(navMenuItemsOnPage);
    }

    public void logoutUser() {
        if (isUserLoggedIn()) {
            findElementOnPage(BUTTON_NAV_MENU_LOGOUT).click();
        }
    }

    public boolean verifyIntroductionText(String expectedText) {
        return verifyTextOnElement(findElementOnPage(TEXT_INTRODUCTION), expectedText);
    }

    public boolean verifyCounterTitle(String expectedTitle) {
        return verifyTextOnElement(findElementOnPage(TEXT_COUNTER_TITLE), expectedTitle);
    }
}
