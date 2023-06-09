package Presentation;

/**
 * Enumerations per a configurar les imatges de tots els
 * JButtons i els JLabels
 */
public enum AssetsFiles {

    LOGO_LABEL("files/imgs/logo.png"),

    LOGIN_LABEL("files/imgs/loginLabel.png"),

    REGISTER_LABEL("files/imgs/registerLabel.png"),

    ESPOTYFAI_LABEL("files/imgs/espotyfaiLabel.png"),
    LOGIN_BUTTON_IMG("files/imgs/loginButton.png"),

    REGISTER_BUTTON_IMG("files/imgs/registerButton.png"),

    BACK_BUTTON_IMG("files/imgs/backButton.png"),

    SONGLIST_BUTTON_IMG("files/imgs/songlistButton.png"),

    ADDSONG_BUTTON_IMG("files/imgs/addsongButton.png"),

    ADD_SONG_FILE_BUTTON_IMG("files/imgs/addSongFile.png"),

    DELETESONG_BUTTON_IMG("files/imgs/deletesongButton.png"),

    STATISTICS_BUTTON_IMG("files/imgs/statisticsButton.png"),

    MANAGELISTS_BUTTON_IMG("files/imgs/managelistsButton.png"),

    ADD_BUTTON_IMG("files/imgs/addButton.png"),

    DELETE_BUTTON_IMG("files/imgs/deleteButton.png"),

    BACK_LITTLEBUTTON_IMG("files/imgs/backLittleButton.png"),

    LOGOUT_LITTLEBUTTON_IMG("files/imgs/logoutLittleButton.png"),

    DELETEACC_LITTLEBUTTON_IMG("files/imgs/deleteLittleButton.png"),

    FOOT_PLAYBUTTON_IMG("files/imgs/FplayButton.png"),

    FOOT_PAUSEBUTTON_IMG("files/imgs/FpauseButton.png"),

    FOOT_STOPBUTTON_IMG("files/imgs/FstopButton.png"),

    FOOT_OLDBUTTON_IMG("files/imgs/FoldButton.png"),

    FOOT_NEXTBUTTON_IMG("files/imgs/FnextButton.png"),

    FOOT_REPEATBUTTON_IMG("files/imgs/FrepeatButton.png"),

    FOOT_REPEAT_PLAYLIST_BUTTON_IMG("files/imgs/FrepeatPlaylistButton.png"),
    BUSCADOR_BUTTON_IMG("files/imgs/lupaButton.png"),

    CREATE_LIST_BUTTON_IMG("files/imgs/createListLittleButton.png"),

    SONG_GO_UP_BUTTON_IMG("files/imgs/songGoUpLittleButton.png"),

    SONG_GO_DOWN_BUTTON_IMG("files/imgs/songGoDownLittleButton.png"),

    DELETE_LIST_BUTTON_IMG("files/imgs/deleteListLittleButton.png"),

    DELETE_SONG_LITTLE_BUTTON_IMG("files/imgs/deleteSongLittleButton.png"),

    DELETE_SONG_FROM_LIST_BUTTON_IMG("files/imgs/deleteSongFromListLittleButton.png"),
    LYRICS_IMG("files/imgs/FlyricsButton.png"),
    PLAYLIST_PLUS_ADD("files/imgs/addPlaylistButton.png"),

    GET_STATISTICS_BUTTON_IMG("files/imgs/getActualStatistics.png");

    private final String files;

    /**
     * Funció que servirà com a referència per a poder
     * agafar les imatges desde les diferents vistes
     *
     * @param files, ubicacions de les imatges
     */
    AssetsFiles(String files) { this.files = files; }

    /**
     * Funció per a retornar el link de les imatges
     *
     * @return files, link en qüestió
     */
    public String toString() {
        return this.files;
    }
}
