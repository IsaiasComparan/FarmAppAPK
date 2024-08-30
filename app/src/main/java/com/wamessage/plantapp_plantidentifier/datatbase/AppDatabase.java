package com.wamessage.plantapp_plantidentifier.datatbase;

import android.content.Context;
import androidx.room.Room;
import androidx.room.RoomDatabase;


public abstract class AppDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "plant.db";
    private static AppDatabase instance;

    public abstract InsectDao insectDao();

    public abstract MushroomDao mushroomDao();

    public abstract PendingIntentEntityDao pendingIntentEntityDao();

    public abstract PlantDao plantDao();

    public abstract PlantHealthDao plantHealthDao();

    public abstract ReminderDao reminderDao();

    public abstract TreeDao treeDao();

    public static synchronized AppDatabase getInstance(Context context) {
        AppDatabase appDatabase;
        synchronized (AppDatabase.class) {
            if (instance == null) {
                instance = (AppDatabase) Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
            }
            appDatabase = instance;
        }
        return appDatabase;
    }
}
