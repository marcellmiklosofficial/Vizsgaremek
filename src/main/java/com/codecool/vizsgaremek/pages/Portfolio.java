package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.enums.Pages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Portfolio extends Page {
    private static final By TEXT_HEADER = By.className("site-project-header-content");

    public Portfolio(WebDriver driver) {
        super(driver, Pages.PORTFOLIO_PAGE.getUrl());
    }

    @Step("Get text from Portfolio Page header")
    public String getHeaderText() {
        return findElementOnPage(TEXT_HEADER).getText();
    }
}
