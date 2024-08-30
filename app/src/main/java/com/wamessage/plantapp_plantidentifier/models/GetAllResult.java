package com.wamessage.plantapp_plantidentifier.models;




public class GetAllResult {
    private String message;
    private GetAllPayload payload;

    public String getMessage() {
        return this.message;
    }

    public GetAllPayload getPayload() {
        return this.payload;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPayload(GetAllPayload payload) {
        this.payload = payload;
    }

    public GetAllResult(String message, GetAllPayload payload) {
        this.message = message;
        this.payload = payload;
    }

    public String toString() {
        return "GetAllResult{message='" + this.message + "', payload=" + this.payload + '}';
    }
}
