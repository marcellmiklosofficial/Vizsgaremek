package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.enums.Pages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Blog extends Page {
    private static final By LIST_TITLES = By.tagName("h3");
    private static final By BUTTON_NEXT = By.xpath("//a[@aria-label='Next']");

    public Blog(WebDriver driver) {
        super(driver, Pages.BLOG_PAGE.getUrl());
    }

    @Step("Get all titles from a paginated list")
    public List<String> getAllTitlesFromMultiPageList() {
        List<String> titles = new ArrayList<>();

        while (true) {
            titles.addAll(findElementsOnPage(LIST_TITLES).stream().map(WebElement::getText).toList());

            WebElement button = findElementOnPage(BUTTON_NEXT);

            if (button.getAttribute("href") == null) {
                break;
            } else {
                button.click();
            }
        }

        return titles;
    }
}
