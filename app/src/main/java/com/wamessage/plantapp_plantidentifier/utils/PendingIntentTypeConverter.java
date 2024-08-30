package com.wamessage.plantapp_plantidentifier.utils;

import android.app.PendingIntent;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class PendingIntentTypeConverter {
    public static String fromPendingIntent(PendingIntent pendingIntent) {
        if (pendingIntent == null) {
            return null;
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(pendingIntent);
            objectOutputStream.close();
            return new String(byteArrayOutputStream.toByteArray(), "ISO-8859-1");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static PendingIntent toPendingIntent(String data) {
        if (data == null) {
            return null;
        }
        try {
            return (PendingIntent) new ObjectInputStream(new ByteArrayInputStream(data.getBytes("ISO-8859-1"))).readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
