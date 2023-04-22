package Presentation;

public enum AssetsFiles {

    LOGO_LABEL("files/imgs/logo.png"),
    LOGIN_LABEL("files/imgs/loginLabel.png"),

    REGISTER_LABEL("files/imgs/registerLabel.png"),

    LOGIN_BUTTON_IMG("files/imgs/loginButton.png"),

    REGISTER_BUTTON_IMG("files/imgs/registerButton.png"),

    BACK_BUTTON_IMG("files/imgs/backButton.png"),

    SONGLIST_BUTTON_IMG("files/imgs/songlistButton.png");

    private final String files;
    AssetsFiles(String files) { this.files = files; }

    public String toString() {
        return this.files;
    }
}
