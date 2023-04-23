package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.enums.Pages;
import org.openqa.selenium.WebDriver;

public class Portfolio extends Page {

    public Portfolio(WebDriver driver) {
        super(driver, Pages.PORTFOLIO_PAGE.getUrl());
    }
}
