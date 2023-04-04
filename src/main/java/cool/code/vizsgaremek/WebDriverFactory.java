package cool.code.vizsgaremek;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {
    private WebDriverFactory() {}

    public static WebDriver getWebDriver(Type type) {
        ChromeOptions options = new ChromeOptions();

        switch (type) {
            case GITHUB: options.addArguments("no-sandbox", "disable-dev-shm-usage"); //fallthrough
            case INVISIBLE: options.addArguments("headless"); //fallthrough
            case VISIBLE: options.addArguments("start-maximized"); //fallthrough
            default: options.addArguments("incognito");
        }

        return new ChromeDriver(options);
    }

    public enum Type {
        VISIBLE, INVISIBLE, GITHUB
    }
}
