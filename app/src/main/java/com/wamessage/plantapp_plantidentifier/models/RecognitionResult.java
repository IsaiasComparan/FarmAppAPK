package com.wamessage.plantapp_plantidentifier.models;




public class RecognitionResult {
    private String message;
    private RecognitionPayload payload;

    public String getMessage() {
        return this.message;
    }

    public RecognitionPayload getRecognitionPayload() {
        return this.payload;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRecognitionPayload(RecognitionPayload recognitionPayload) {
        this.payload = recognitionPayload;
    }

    public RecognitionResult(String message, RecognitionPayload recognitionPayload) {
        this.message = message;
        this.payload = recognitionPayload;
    }

    public String toString() {
        return "RecognitionResult{message='" + this.message + "', recognitionPayload=" + this.payload + '}';
    }
}
