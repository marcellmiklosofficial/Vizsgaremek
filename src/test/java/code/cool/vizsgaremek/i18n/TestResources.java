package code.cool.vizsgaremek.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

public final class TestResources {
    public static final ResourceBundle RESOURCE_BUNDLE;

    static {
        String locale = System.getenv("LOCALE");
        String baseName = "i18n.resources";

        if (locale != null) {
            if (locale.equalsIgnoreCase("hu")) {
                RESOURCE_BUNDLE = ResourceBundle.getBundle(baseName, Locale.forLanguageTag("hu"));
            } else {
                RESOURCE_BUNDLE = ResourceBundle.getBundle(baseName, Locale.getDefault());
            }
        } else {
            RESOURCE_BUNDLE = ResourceBundle.getBundle(baseName, Locale.getDefault());
        }
    }

    private TestResources() {}

    public static String getResource(ResourceKeys resourceKey) {
        return RESOURCE_BUNDLE.getString(resourceKey.key);
    }

    public enum ResourceKeys {
        // NAV-BAR
        NAV_BAR_TEXT_HOME("nav_bar_text_home"),
        NAV_BAR_TEXT_ABOUT("nav_bar_text_about"),
        NAV_BAR_TEXT_PORTFOLIO("nav_bar_text_portfolio"),
        NAV_BAR_TEXT_BLOG("nav_bar_text_blog"),
        NAV_BAR_TEXT_GETINTOUCH("nav_bar_text_getInTouch"),
        NAV_BAR_TEXT_PROFILE("nav_bar_text_profile"),
        NAV_BAR_TEXT_LOGOUT("nav_bar_text_logout"),

        // LANDING PAGE
        LANDING_INTRODUCTION_SECTION("landing_introduction_section"),
        LANDING_COUNTER_TITLE("landing_counter_title");

        private final String key;

        ResourceKeys(String key) {
            this.key = key;
        }
    }
}
