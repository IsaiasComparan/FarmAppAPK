package com.wamessage.plantapp_plantidentifier.models;


public class Expert {
    private String content;
    private Boolean isExpanded = false;
    private String title;

    public String getContent() {
        return this.content;
    }

    public Boolean getExpanded() {
        return this.isExpanded;
    }

    public String getTitle() {
        return this.title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setExpanded(Boolean expanded) {
        this.isExpanded = expanded;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Expert(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
