package Persistance.dao;

import com.google.gson.*;

import java.io.IOException;

public class SongLyricsApi {

    private final ApiHelper apiHelper;

    public SongLyricsApi(ApiHelper apiHelper){
        this.apiHelper = apiHelper;
    }

    public String readLyrics(String artist, String nameSong)  {
        String lyricsSong = null;
        artist = artist.replaceAll(" ", "%20");
        nameSong = nameSong.replaceAll(" ", "%20");
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

    //Preguntar si es pot escriure...
    /*public void writeLyrics(String artist, String nameSong, String lyrics)  {
        Gson gson = new Gson();
        artist = artist.replaceAll(" ", "%20");
        nameSong = nameSong.replaceAll(" ", "%20");
        try {
            artist = gson.toJson(artist);
            nameSong = gson.toJson(nameSong);
            String body = gson.toJson(lyrics);
            String error = apiHelper.postToUrl("https://balandrau.salle.url.edu/dpoo/lyrics/artist",artist);
            System.out.println(error);
            apiHelper.postToUrl("https://balandrau.salle.url.edu/dpoo/lyrics/song",nameSong);
            apiHelper.postToUrl("https://balandrau.salle.url.edu/dpoo/lyrics/"+ artist +"/"+ nameSong, body);
        }catch (IOException e){
            System.out.println("Couldn't write the file!");
        }
    }*/

}