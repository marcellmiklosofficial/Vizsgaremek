package code.cool.vizsgaremek;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {
    private static final Type TYPE;

    static {
        WebDriverManager.chromedriver().setup();

        String variable = System.getenv("BROWSER_SETTINGS");

        if (variable != null) {
            switch (variable) {
                case "github" -> TYPE = Type.GITHUB;
                case "visible" -> TYPE = Type.VISIBLE;
                default -> TYPE = Type.INVISIBLE;
            }
        } else {
            TYPE = Type.INVISIBLE;
        }
    }

    private WebDriverFactory() {}

    public static WebDriver getWebDriver() {
        ChromeOptions options = new ChromeOptions();

        switch (TYPE) {
            case GITHUB: options.addArguments("no-sandbox", "disable-dev-shm-usage"); //fallthrough
            case INVISIBLE: options.addArguments("headless"); //fallthrough
            case VISIBLE: options.addArguments("window-size=1280,800"); //fallthrough
            default: options.addArguments("incognito", "disable-extensions");
        }

        return new ChromeDriver(options);
    }

    private enum Type {
        VISIBLE, INVISIBLE, GITHUB
    }
}
