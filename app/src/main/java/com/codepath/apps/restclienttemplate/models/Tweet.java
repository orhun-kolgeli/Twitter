package com.codepath.apps.restclienttemplate.models;

import android.util.Log;

import com.codepath.apps.restclienttemplate.LoginActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel // make parcelable
public class Tweet {
    public String body;
    public String createdAt;
    public User user;
    public String mediaurl;


    public Tweet() {} // make parcelable

    public static Tweet fromJson(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();
        if(jsonObject.has("full_text")) {
            tweet.body = jsonObject.getString("full_text");
        } else {
            tweet.body = jsonObject.getString("text");
        }
        tweet.mediaurl = getEntity(jsonObject.getJSONObject("entities"));
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.user = User.fromJson(jsonObject.getJSONObject("user"));
        return tweet;
    }

    public static String getEntity(JSONObject jsonObject) throws JSONException {
        JSONArray allMedia = jsonObject.has("media") ? jsonObject.getJSONArray("media") : null;
        String url = "";
        String http_url = "";
        if (allMedia != null) {
            url =  allMedia.getJSONObject(0).getString("media_url_https");
            http_url =  allMedia.getJSONObject(0).getString("media_url");
            if (http_url.contains("video")) {
                return "";
            }
        }
        return url;
    }
    
    public static List<Tweet> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            tweets.add(fromJson(jsonArray.getJSONObject(i)));
        }
        return tweets;
    }
}
