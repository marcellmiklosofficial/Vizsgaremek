package cool.code.vizsgaremek;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {
    private static final Type TYPE;

    static {
        WebDriverManager.chromedriver().setup();

        String variable = System.getenv("BROWSER_SETTINGS");

        if (variable == null) {
            TYPE = Type.INVISIBLE;
        } else if (variable.equals("github")) {
            TYPE = Type.GITHUB;
        } else if (variable.equals("visible")) {
            TYPE = Type.VISIBLE;
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
            case VISIBLE: options.addArguments("start-maximized"); //fallthrough
            default: options.addArguments("incognito", "disable-extensions");
        }

        return new ChromeDriver(options);
    }

    private enum Type {
        VISIBLE, INVISIBLE, GITHUB
    }
}
