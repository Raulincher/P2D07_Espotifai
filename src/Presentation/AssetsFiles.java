package Presentation;

public enum AssetsFiles {

    LOGO_LABEL("files/imgs/logo.png"),

    LOGIN_LABEL("files/imgs/loginLabel.png"),

    REGISTER_LABEL("files/imgs/registerLabel.png"),

    LOGIN_BUTTON_IMG("files/imgs/loginButton.png"),

    REGISTER_BUTTON_IMG("files/imgs/registerButton.png"),

    BACK_BUTTON_IMG("files/imgs/backButton.png"),

    SONGLIST_BUTTON_IMG("files/imgs/songlistButton.png"),

    ADDSONG_BUTTON_IMG("files/imgs/addsongButton.png"),

    DELETESONG_BUTTON_IMG("files/imgs/deletesongButton.png"),

    STADISTICS_BUTTON_IMG("files/imgs/stadisticsButton.png"),

    MANAGELISTS_BUTTON_IMG("files/imgs/managelistsButton.png"),

    ADD_BUTTON_IMG("files/imgs/addButton.png"),

    DELETE_BUTTON_IMG("files/imgs/deleteButton.png"),

    BACK_LITTLEBUTTON_IMG("files/imgs/backLittleButton.png"),

    LOGOUT_LITTLEBUTTON_IMG("files/imgs/logoutLittleButton.png"),

    DELETEACC_LITTLEBUTTON_IMG("files/imgs/deleteLittleButton.png");

    private final String files;
    AssetsFiles(String files) { this.files = files; }

    public String toString() {
        return this.files;
    }
}
