package com.ramyhelow.newsapi.Model;

public class Source {
    private String source_id;
    private String source_name;

    public Source(String source_id, String source_name) {
        this.source_id = source_id;
        this.source_name = source_name;
    }

    public String getSource_id() {
        return source_id;
    }

    public void setSource_id(String source_id) {
        this.source_id = source_id;
    }

    public String getSource_name() {
        return source_name;
    }

    public void setSource_name(String source_name) {
        this.source_name = source_name;
    }
}
