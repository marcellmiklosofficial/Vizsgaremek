package cool.code.vizsgaremek.pages;

import cool.code.vizsgaremek.enums.Pages;
import org.openqa.selenium.WebDriver;

public class Contact extends Page {

    public Contact(WebDriver driver) {
        super(driver, Pages.CONTACT_PAGE.getUrl());
    }
}
