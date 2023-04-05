package cool.code.vizsgaremek.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

abstract class Page {
    private final WebDriver driver;
    private final Wait<WebDriver> wait;
    private final String url;

    protected Page(WebDriver driver, String url) {
        this.driver = driver;
        this.url = url;
        this.wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
    }

    public final void navigateTo() {
        driver.navigate().to(url);
    }

    public final WebElement findElementOnPage(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
