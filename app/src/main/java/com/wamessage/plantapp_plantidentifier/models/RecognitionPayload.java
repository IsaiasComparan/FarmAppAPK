package com.wamessage.plantapp_plantidentifier.models;

import java.util.List;



public class RecognitionPayload {
    private List<Plant> predictions;

    public List<Plant> getPredictions() {
        return this.predictions;
    }

    public void setPredictions(List<Plant> predictions) {
        this.predictions = predictions;
    }

    public RecognitionPayload(List<Plant> predictions) {
        this.predictions = predictions;
    }

    public String toString() {
        return "RecognitionPayload{predictions=" + this.predictions + '}';
    }
}
