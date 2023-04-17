package cool.code.vizsgaremek.enums;

public enum Pages {
    REG_AND_LOGIN_PAGE("https://lennertamas.github.io/roxo/"),
    LANDING_PAGE("https://lennertamas.github.io/roxo/landing.html"),
    PORTFOLIO_PAGE("https://lennertamas.github.io/roxo/portfolio/"),
    CONTACT_PAGE("https://lennertamas.github.io/roxo/contact/"),
    PROFILE_PAGE("https://lennertamas.github.io/roxo/profile/"),
    ABOUT_PAGE("https://lennertamas.github.io/roxo/about/"),
    BLOG_PAGE("https://lennertamas.github.io/roxo/blog/");

    private final String url;

    Pages(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
