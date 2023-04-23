package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.enums.Pages;
import org.openqa.selenium.WebDriver;

public class Contact extends Page {

    public Contact(WebDriver driver) {
        super(driver, Pages.CONTACT_PAGE.getUrl());
    }
}
