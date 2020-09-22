package com.example.masterusingjson;

import com.example.masterusingjson.Model.ItuneStuff;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonItuneParser {


    public static ItuneStuff getItuneStuff(String url) throws JSONException
    {
        ItuneStuff ituneStuff = new ItuneStuff();

        JSONObject jsonObject = new JSONObject(url);

        JSONArray resultArray = jsonObject.getJSONArray("results");

        JSONObject artistObject = resultArray.getJSONObject(0);
        ituneStuff.setType(getString("wrapperType",artistObject));
        ituneStuff.setKind(getString("kind",artistObject));
        ituneStuff.setArtistName(getString("artistName",artistObject));
        ituneStuff.setCollectionName(getString("collectionName",artistObject));
        ituneStuff.setTrackName(getString("trackName",artistObject));
        ituneStuff.setArtistViewUrl(getString("artworkUrl100",artistObject));

        return ituneStuff;
    }



    private static JSONObject getJsonObject(String tagName, JSONObject jsonObject) throws  JSONException
    {
          return jsonObject.getJSONObject(tagName);
    }


    // if values are types in String avrrives from json...then use this Strong method
    private static String getString(String tagName, JSONObject jsonObject) throws JSONException
    {
        return jsonObject.getString(tagName);
    }


    // if values are types in float avrrives from json...then use this float method
    private static float getFloat(String tagName,JSONObject jsonObject) throws JSONException
    {
        return (float) jsonObject.getDouble(tagName);
    }

    // if values are types in int  avrrives from json...then use this int method
    private static int getInt(String tagName,JSONObject jsonObject) throws JSONException
    {
        return (int) jsonObject.getDouble(tagName);
    }


}
