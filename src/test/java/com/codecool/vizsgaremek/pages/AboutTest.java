package cool.code.vizsgaremek.pages;

import code.cool.vizsgaremek.TestConstants;
import code.cool.vizsgaremek.TestUtils;
import code.cool.vizsgaremek.WebDriverFactory;
import cool.code.vizsgaremek.enums.Pages;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.*;

@Epic(TestConstants.NAME_EPIC)
@Feature("About Page")
@DisplayName("About Page Tests")
class AboutTest {
    private WebDriver driver;
    private About about;

    @BeforeEach
    void setUp() {
        driver = WebDriverFactory.getWebDriver();
        about = new About(driver);

        about.navigateTo();
    }

    @Test
    @DisplayName("The correct URL is opened")
    @Description("The correct URL is opened")
    @Story("User navigates to the About page")
    @Severity(SeverityLevel.NORMAL)
    void correctUrl() {
        Assertions.assertEquals(Pages.ABOUT_PAGE.getUrl(), driver.getCurrentUrl());
    }

    @Test
    @DisplayName("There are six team members displayed")
    @Description("There are six team members displayed")
    @Story("User can see six team members displayed on the About Page")
    @Severity(SeverityLevel.TRIVIAL)
    void numberOfTeamMembers() {
        about.scrollToTeamMemberSection();
        TestUtils.makeScreenshot("Team members section", driver);

        Assertions.assertEquals(6, about.numberOfTeamMembers());
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
