package com.wamessage.plantapp_plantidentifier.models;

import java.io.Serializable;



public class Plant implements Serializable {
    private long id;
    private byte[] image;
    private Boolean isFavorited;
    private String name;
    private Double score;

    public Boolean getFavorited() {
        return this.isFavorited;
    }

    public long getId() {
        return this.id;
    }

    public byte[] getImage() {
        return this.image;
    }

    public String getName() {
        return this.name;
    }

    public void setFavorited(Boolean favorited) {
        this.isFavorited = favorited;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Plant() {
    }

    public Plant(String name, Double score) {
        this.name = name;
        this.score = score;
        this.isFavorited = false;
    }

    public Plant(byte[] image, String name, Double score) {
        this.image = image;
        this.name = name;
        this.score = score;
        this.isFavorited = false;
    }

    public Double getScore() {
        return this.score.doubleValue() > 1.0d ? this.score : Double.valueOf(Math.floor((this.score.doubleValue() * 100.0d) * 100.0d) / 100.0d);
    }

    public String toString() {
        return "Plant{name='" + this.name + "', score=" + this.score + '}';
    }
}
