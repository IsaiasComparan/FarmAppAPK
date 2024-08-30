package com.wamessage.plantapp_plantidentifier.models;


public class Language {
    private String go;
    private String id;
    private int img;
    private String languageName;
    private String name;

    public String getGo() {
        return this.go;
    }

    public String getId() {
        return this.id;
    }

    public int getImg() {
        return this.img;
    }

    public String getLanguageName() {
        return this.languageName;
    }

    public String getName() {
        return this.name;
    }

    public Language(String id, String name, String go, String languageName, int img) {
        this.id = id;
        this.name = name;
        this.go = go;
        this.languageName = languageName;
        this.img = img;
    }
}
