package com.wamessage.plantapp_plantidentifier.models;

import java.util.List;



public class GetAllPayload {
    private List<String> recognized;

    public List<String> getRecognized() {
        return this.recognized;
    }

    public void setRecognized(List<String> recognized) {
        this.recognized = recognized;
    }

    public GetAllPayload(List<String> recognized) {
        this.recognized = recognized;
    }

    public String toString() {
        return "GetAllPayload{recognized=" + this.recognized + '}';
    }
}
