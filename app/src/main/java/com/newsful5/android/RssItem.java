package com.newsful5.android;

public class RssItem {

    private final String title;
    private final String description;
    private final String link;
    private final String thumbnailLink;


    public RssItem(String title, String link,String Des,String tl) {
        this.title = title;
        this.link = link;
        this.description=Des;
        this.thumbnailLink=tl;
    }

    public String getDescription(){
        return description;
    }
    public String getTitle() {
        return title;
    }

    public String getImageViewLink(){
        return thumbnailLink;
    }

    public String getLink() {
        return link;
    }
}