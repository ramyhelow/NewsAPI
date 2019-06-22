package com.ramyhelow.newsapi.URL;

public class URL {
    public static final String URL = "https://newsapi.org/v2/top-headlines?country=us&apiKey=f901227801b84ac3b5b82f58321899c0";
    public static final String URL_SOURCES = "https://newsapi.org/v2/sources?apiKey=f901227801b84ac3b5b82f58321899c0";

    public static String getHeadlinesFromSourceURL(String source_id){
        return "https://newsapi.org/v2/top-headlines?sources="+source_id+"&apiKey=f901227801b84ac3b5b82f58321899c0";
    }
}
