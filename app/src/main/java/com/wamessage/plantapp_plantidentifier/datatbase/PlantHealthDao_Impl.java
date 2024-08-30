package com.wamessage.plantapp_plantidentifier.datatbase;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.wamessage.plantapp_plantidentifier.models.PlantHealth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public final class PlantHealthDao_Impl implements PlantHealthDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<PlantHealth> __deletionAdapterOfPlantHealth;
    private final EntityInsertionAdapter<PlantHealth> __insertionAdapterOfPlantHealth;
    private final EntityDeletionOrUpdateAdapter<PlantHealth> __updateAdapterOfPlantHealth;

    public PlantHealthDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfPlantHealth = new EntityInsertionAdapter<PlantHealth>(__db) { // from class: com.wamessage.plantapp_plantidentifier.datatbase.PlantHealthDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR ABORT INTO `planthealth` (`id`,`image`,`name`,`score`,`isFavorited`) VALUES (nullif(?, 0),?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, PlantHealth value) {
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
        this.__deletionAdapterOfPlantHealth = new EntityDeletionOrUpdateAdapter<PlantHealth>(__db) { // from class: com.wamessage.plantapp_plantidentifier.datatbase.PlantHealthDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `planthealth` WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, PlantHealth value) {
                stmt.bindLong(1, value.getId());
            }
        };
        this.__updateAdapterOfPlantHealth = new EntityDeletionOrUpdateAdapter<PlantHealth>(__db) { // from class: com.wamessage.plantapp_plantidentifier.datatbase.PlantHealthDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `planthealth` SET `id` = ?,`image` = ?,`name` = ?,`score` = ?,`isFavorited` = ? WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, PlantHealth value) {
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

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.PlantHealthDao
    public long insertPlantHealth(final PlantHealth plantHealth) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            long insertAndReturnId = this.__insertionAdapterOfPlantHealth.insertAndReturnId(plantHealth);
            this.__db.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.PlantHealthDao
    public void deletePlantHealth(final PlantHealth planthealth) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfPlantHealth.handle(planthealth);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.PlantHealthDao
    public void updatePlantHealth(final PlantHealth plantHealth) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfPlantHealth.handle(plantHealth);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.PlantHealthDao
    public List<PlantHealth> getPlantHealths() {
        Boolean valueOf;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from planthealth", 0);
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
                PlantHealth plantHealth = new PlantHealth();
                plantHealth.setId(query.getLong(columnIndexOrThrow));
                plantHealth.setImage(query.isNull(columnIndexOrThrow2) ? null : query.getBlob(columnIndexOrThrow2));
                plantHealth.setName(query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3));
                plantHealth.setScore(query.isNull(columnIndexOrThrow4) ? null : Double.valueOf(query.getDouble(columnIndexOrThrow4)));
                Integer valueOf2 = query.isNull(columnIndexOrThrow5) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow5));
                if (valueOf2 == null) {
                    valueOf = null;
                } else {
                    valueOf = Boolean.valueOf(valueOf2.intValue() != 0);
                }
                plantHealth.setFavorited(valueOf);
                arrayList.add(plantHealth);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.PlantHealthDao
    public PlantHealth getFirstPlantHealth() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM planthealth ORDER BY id ASC LIMIT 1", 0);
        this.__db.assertNotSuspendingTransaction();
        PlantHealth plantHealth = null;
        Boolean valueOf = null;
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "image");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "name");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "score");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "isFavorited");
            if (query.moveToFirst()) {
                PlantHealth plantHealth2 = new PlantHealth();
                plantHealth2.setId(query.getLong(columnIndexOrThrow));
                plantHealth2.setImage(query.isNull(columnIndexOrThrow2) ? null : query.getBlob(columnIndexOrThrow2));
                plantHealth2.setName(query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3));
                plantHealth2.setScore(query.isNull(columnIndexOrThrow4) ? null : Double.valueOf(query.getDouble(columnIndexOrThrow4)));
                Integer valueOf2 = query.isNull(columnIndexOrThrow5) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow5));
                if (valueOf2 != null) {
                    valueOf = Boolean.valueOf(valueOf2.intValue() != 0);
                }
                plantHealth2.setFavorited(valueOf);
                plantHealth = plantHealth2;
            }
            return plantHealth;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.PlantHealthDao
    public int getPlantHealthCount() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM planthealth", 0);
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
