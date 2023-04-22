package cool.code.vizsgaremek.pages;

import cool.code.vizsgaremek.enums.Pages;
import org.openqa.selenium.WebDriver;

public class Profile extends Page {

    public Profile(WebDriver driver) {
        super(driver, Pages.PROFILE_PAGE.getUrl());
    }
}
