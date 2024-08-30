package com.wamessage.plantapp_plantidentifier.datatbase;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.wamessage.plantapp_plantidentifier.models.Plant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public final class PlantDao_Impl implements PlantDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<Plant> __deletionAdapterOfPlant;
    private final EntityInsertionAdapter<Plant> __insertionAdapterOfPlant;
    private final EntityDeletionOrUpdateAdapter<Plant> __updateAdapterOfPlant;

    public PlantDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfPlant = new EntityInsertionAdapter<Plant>(__db) { // from class: com.wamessage.plantapp_plantidentifier.datatbase.PlantDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR ABORT INTO `plant` (`id`,`image`,`name`,`score`,`isFavorited`) VALUES (nullif(?, 0),?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, Plant value) {
                stmt.bindLong(1, value.getId());
                if (value.getImage() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindBlob(2, value.getImage());
                }
                if (value.getName() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getName());
                }
                if (value.getScore() == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindDouble(4, value.getScore().doubleValue());
                }
                Integer valueOf = value.getFavorited() == null ? null : Integer.valueOf(value.getFavorited().booleanValue() ? 1 : 0);
                if (valueOf == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindLong(5, valueOf.intValue());
                }
            }
        };
        this.__deletionAdapterOfPlant = new EntityDeletionOrUpdateAdapter<Plant>(__db) { // from class: com.wamessage.plantapp_plantidentifier.datatbase.PlantDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `plant` WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, Plant value) {
                stmt.bindLong(1, value.getId());
            }
        };
        this.__updateAdapterOfPlant = new EntityDeletionOrUpdateAdapter<Plant>(__db) { // from class: com.wamessage.plantapp_plantidentifier.datatbase.PlantDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `plant` SET `id` = ?,`image` = ?,`name` = ?,`score` = ?,`isFavorited` = ? WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, Plant value) {
                stmt.bindLong(1, value.getId());
                if (value.getImage() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindBlob(2, value.getImage());
                }
                if (value.getName() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getName());
                }
                if (value.getScore() == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindDouble(4, value.getScore().doubleValue());
                }
                Integer valueOf = value.getFavorited() == null ? null : Integer.valueOf(value.getFavorited().booleanValue() ? 1 : 0);
                if (valueOf == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindLong(5, valueOf.intValue());
                }
                stmt.bindLong(6, value.getId());
            }
        };
    }

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.PlantDao
    public long insertPlant(final Plant plant) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            long insertAndReturnId = this.__insertionAdapterOfPlant.insertAndReturnId(plant);
            this.__db.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.PlantDao
    public void deletePlant(final Plant plant) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfPlant.handle(plant);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.PlantDao
    public void updatePlant(final Plant plant) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfPlant.handle(plant);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.PlantDao
    public List<Plant> getPlants() {
        Boolean valueOf;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from plant", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "image");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "name");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "score");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "isFavorited");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                Plant plant = new Plant();
                plant.setId(query.getLong(columnIndexOrThrow));
                plant.setImage(query.isNull(columnIndexOrThrow2) ? null : query.getBlob(columnIndexOrThrow2));
                plant.setName(query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3));
                plant.setScore(query.isNull(columnIndexOrThrow4) ? null : Double.valueOf(query.getDouble(columnIndexOrThrow4)));
                Integer valueOf2 = query.isNull(columnIndexOrThrow5) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow5));
                if (valueOf2 == null) {
                    valueOf = null;
                } else {
                    valueOf = Boolean.valueOf(valueOf2.intValue() != 0);
                }
                plant.setFavorited(valueOf);
                arrayList.add(plant);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.PlantDao
    public Plant getFirstPlant() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM plant ORDER BY id ASC LIMIT 1", 0);
        this.__db.assertNotSuspendingTransaction();
        Plant plant = null;
        Boolean valueOf = null;
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "image");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "name");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "score");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "isFavorited");
            if (query.moveToFirst()) {
                Plant plant2 = new Plant();
                plant2.setId(query.getLong(columnIndexOrThrow));
                plant2.setImage(query.isNull(columnIndexOrThrow2) ? null : query.getBlob(columnIndexOrThrow2));
                plant2.setName(query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3));
                plant2.setScore(query.isNull(columnIndexOrThrow4) ? null : Double.valueOf(query.getDouble(columnIndexOrThrow4)));
                Integer valueOf2 = query.isNull(columnIndexOrThrow5) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow5));
                if (valueOf2 != null) {
                    valueOf = Boolean.valueOf(valueOf2.intValue() != 0);
                }
                plant2.setFavorited(valueOf);
                plant = plant2;
            }
            return plant;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.PlantDao
    public int getPlantCount() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM plant", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
