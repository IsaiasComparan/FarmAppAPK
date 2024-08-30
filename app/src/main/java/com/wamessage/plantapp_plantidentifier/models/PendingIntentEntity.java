package com.wamessage.plantapp_plantidentifier.models;

import android.app.PendingIntent;


public class PendingIntentEntity {
    public long id;
    PendingIntent pendingIntent;

    public long getId() {
        return this.id;
    }

    public PendingIntent getPendingIntent() {
        return this.pendingIntent;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPendingIntent(PendingIntent pendingIntent) {
        this.pendingIntent = pendingIntent;
    }

    public PendingIntentEntity(PendingIntent pendingIntent) {
        this.pendingIntent = pendingIntent;
    }
}
