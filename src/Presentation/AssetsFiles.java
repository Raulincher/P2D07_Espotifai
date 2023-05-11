package Presentation;

public enum AssetsFiles {

    LOGO_LABEL("files/imgs/logo.png"),

    LOGIN_LABEL("files/imgs/loginLabel.png"),

    REGISTER_LABEL("files/imgs/registerLabel.png"),

    MENU_LABEL("files/imgs/menuLabel.png"),

    DELETE_LABEL("files/imgs/deleteLabel.png"),

    ADDSONG_LABEL("files/imgs/addSongLabel.png"),

    LISTMANAGING_LABEL("files/imgs/listManagingLabel.png"),

    MUSIC_LABEL("files/imgs/musicLabel.png"),

    SONG_LABEL("files/imgs/songLabel.png"),

    STATISTICS_LABEL("files/imgs/statisticsLabel.png"),

    YOURLIST_LABEL("files/imgs/yourListLabel.png"),
    LOGIN_BUTTON_IMG("files/imgs/loginButton.png"),

    REGISTER_BUTTON_IMG("files/imgs/registerButton.png"),

    BACK_BUTTON_IMG("files/imgs/backButton.png"),

    SONGLIST_BUTTON_IMG("files/imgs/songlistButton.png"),

    ADDSONG_BUTTON_IMG("files/imgs/addsongButton.png"),

    DELETESONG_BUTTON_IMG("files/imgs/deletesongButton.png"),

    STATISTICS_BUTTON_IMG("files/imgs/statisticsButton.png"),

    MANAGELISTS_BUTTON_IMG("files/imgs/managelistsButton.png"),

    ADD_BUTTON_IMG("files/imgs/addButton.png"),

    DELETE_BUTTON_IMG("files/imgs/deleteButton.png"),

    BACK_LITTLEBUTTON_IMG("files/imgs/backLittleButton.png"),

    LOGOUT_LITTLEBUTTON_IMG("files/imgs/logoutLittleButton.png"),

    DELETEACC_LITTLEBUTTON_IMG("files/imgs/deleteLittleButton.png"),

    FOOT_PLAYBUTTON_IMG("files/imgs/FplayButton.png"),

    FOOT_OLDBUTTON_IMG("files/imgs/FoldButton.png"),

    FOOT_NEXTBUTTON_IMG("files/imgs/FnextButton.png"),

    FOOT_REPEATBUTTON_IMG("files/imgs/FrepeatButton.png"),

    FOOT_REPEAT_PLAYLIST_BUTTON_IMG("files/imgs/FrepeatPlaylistButton.png"),
    BUSCADOR_BUTTON_IMG("files/imgs/lupaButton.png"),

    CREATE_LIST_BUTTON_IMG("files/imgs/createlistButton.png"),

    ADD_SONG_FILE_BUTTON_IMG("files/imgs/addSongFile.png");

    private final String files;
    AssetsFiles(String files) { this.files = files; }

    public String toString() {
        return this.files;
    }
}
