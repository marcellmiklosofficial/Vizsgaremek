package cool.code.vizsgaremek.pages;

import cool.code.vizsgaremek.enums.Pages;
import org.openqa.selenium.WebDriver;

public class LandingPage extends Page {
    public LandingPage(WebDriver driver) {
        super(driver, Pages.LANDING_PAGE.getUrl());
    }
}
