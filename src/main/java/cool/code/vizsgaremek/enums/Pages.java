package cool.code.vizsgaremek.enums;

public enum Pages {
    REG_AND_LOGIN_PAGE("https://lennertamas.github.io/roxo/"),
    LANDING_PAGE("https://lennertamas.github.io/roxo/landing.html");

    private final String url;

    Pages(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
