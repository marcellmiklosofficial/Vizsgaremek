package cool.code.vizsgaremek.pages;

import cool.code.vizsgaremek.enums.Pages;
import org.openqa.selenium.WebDriver;

public class Blog extends Page {

    public Blog(WebDriver driver) {
        super(driver, Pages.BLOG_PAGE.getUrl());
    }
}
