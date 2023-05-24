package Persistance.dao;

import com.google.gson.*;

import java.io.IOException;

/**
 * Classe per llegir el líric d'una cançó determinada
 */
public class SongLyricsApi  {

    // Declarem atributs
    private final ApiHelper apiHelper;

    /**
     * Constructir de la classe SongLyricsApi
     * @param apiHelper, variable per poder llegir la Api
     */
    public SongLyricsApi(ApiHelper apiHelper){
        this.apiHelper = apiHelper;
    }

    /**
     * Metode per llegir l'Api de lírics amb el nom i artista de la cançó i aquesta retorna el líric
     * @param artist, String amb el nom de l'artista
     * @param nameSong, String amb el nom de la cançó
     * @return String amb el líric de la cançó solicitada
     */
    public String readLyrics(String artist, String nameSong){
        // Inicialitzem els parametres correctament per passar a l'Api
        String lyricsSong = null;
        artist = artist.replaceAll(" ", "%20");
        nameSong = nameSong.replaceAll(" ", "%20");

        // Llegim el líric de l'Api a partir de l'url
        try {
            String lyrics = apiHelper.getFromUrl("https://balandrau.salle.url.edu/dpoo/lyrics/"+ artist +"/"+ nameSong);
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(lyrics);
            if (jsonElement.isJsonObject()) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                if (jsonObject.has("lyrics")) {
                    lyricsSong = jsonObject.get("lyrics").getAsString();
                } else if (jsonObject.has("error")) {
                    lyricsSong = jsonObject.get("error").getAsString();
                }
            }
            return lyricsSong;
        }catch (IOException e){
            return "Couldn't read the file!";
        }
    }
}
