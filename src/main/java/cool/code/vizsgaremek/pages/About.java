package cool.code.vizsgaremek.pages;

import cool.code.vizsgaremek.enums.Pages;
import org.openqa.selenium.WebDriver;

public class About extends Page {

    public About(WebDriver driver) {
        super(driver, Pages.ABOUT_PAGE.getUrl());
    }
}
