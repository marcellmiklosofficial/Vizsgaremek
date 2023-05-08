package com.codecool.vizsgaremek.pages;

import com.codecool.vizsgaremek.enums.Pages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class About extends Page {
    private static final By LIST_TEAM_MEMBERS = By.className("site-team-member");
    private static final By LIST_TEAM_MEMBERS_NAME = By.tagName("h3");
    private static final By LIST_TEAM_MEMBERS_OCCUPATION = By.tagName("p");

    private List<WebElement> teamMembers;

    public About(WebDriver driver) {
        super(driver, Pages.ABOUT_PAGE.getUrl());
    }

    @Step("Count how many Team Members are displayed on the About page")
    public int numberOfTeamMembers() {
        getAllTeamMembers();

        return teamMembers.size();
    }

    @Step("Scroll down to the end of the Team Members' section so it will be visible on the screenshot")
    public void scrollToTeamMemberSection() {
        getAllTeamMembers();

        new Actions(getDriver()).scrollToElement(teamMembers.get(teamMembers.size() - 1)).perform();
    }

    @Step("Gather Team Members' names")
    public List<String> getTeamMembersNames() {
        return getTeamMembersDetails(LIST_TEAM_MEMBERS_NAME);
    }

    @Step("Gather Team Members' occupations")
    public List<String> getTeamMembersOccupations() {
        return getTeamMembersDetails(LIST_TEAM_MEMBERS_OCCUPATION);
    }

    private void getAllTeamMembers() {
        if (teamMembers == null || teamMembers.isEmpty()) {
            teamMembers = findElementsOnPage(LIST_TEAM_MEMBERS);
        }
    }

    private List<String> getTeamMembersDetails(By locator) {
        getAllTeamMembers();

        return teamMembers.stream().map(element -> element.findElement(locator).getText()).toList();
    }
}
