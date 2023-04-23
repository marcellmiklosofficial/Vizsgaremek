package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.enums.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class About extends Page {
    private static final By LIST_TEAM_MEMBERS = By.className("site-team-member");

    public About(WebDriver driver) {
        super(driver, Pages.ABOUT_PAGE.getUrl());
    }

    public int numberOfTeamMembers() {
        return findElementsOnPage(LIST_TEAM_MEMBERS).size();
    }

    public void scrollToTeamMemberSection() {
        List<WebElement> teamMembers = findElementsOnPage(LIST_TEAM_MEMBERS);

        new Actions(getDriver()).scrollToElement(teamMembers.get(teamMembers.size() - 1)).perform();
    }
}
