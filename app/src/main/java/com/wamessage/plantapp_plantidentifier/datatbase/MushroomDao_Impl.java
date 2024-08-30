package com.wamessage.plantapp_plantidentifier.datatbase;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.wamessage.plantapp_plantidentifier.models.Mushroom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public final class MushroomDao_Impl implements MushroomDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<Mushroom> __deletionAdapterOfMushroom;
    private final EntityInsertionAdapter<Mushroom> __insertionAdapterOfMushroom;
    private final EntityDeletionOrUpdateAdapter<Mushroom> __updateAdapterOfMushroom;

    public MushroomDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfMushroom = new EntityInsertionAdapter<Mushroom>(__db) { // from class: com.wamessage.plantapp_plantidentifier.datatbase.MushroomDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR ABORT INTO `mushroom` (`id`,`image`,`name`,`score`,`isFavorited`) VALUES (nullif(?, 0),?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, Mushroom value) {
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
        this.__deletionAdapterOfMushroom = new EntityDeletionOrUpdateAdapter<Mushroom>(__db) { // from class: com.wamessage.plantapp_plantidentifier.datatbase.MushroomDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `mushroom` WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, Mushroom value) {
                stmt.bindLong(1, value.getId());
            }
        };
        this.__updateAdapterOfMushroom = new EntityDeletionOrUpdateAdapter<Mushroom>(__db) { // from class: com.wamessage.plantapp_plantidentifier.datatbase.MushroomDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `mushroom` SET `id` = ?,`image` = ?,`name` = ?,`score` = ?,`isFavorited` = ? WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, Mushroom value) {
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

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.MushroomDao
    public long insertMushroom(final Mushroom mushroom) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            long insertAndReturnId = this.__insertionAdapterOfMushroom.insertAndReturnId(mushroom);
            this.__db.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.MushroomDao
    public void deleteMushroom(final Mushroom mushroom) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfMushroom.handle(mushroom);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.MushroomDao
    public void updateMushroom(final Mushroom mushroom) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfMushroom.handle(mushroom);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.MushroomDao
    public List<Mushroom> getMushrooms() {
        Boolean valueOf;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from mushroom", 0);
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
                Mushroom mushroom = new Mushroom();
                mushroom.setId(query.getLong(columnIndexOrThrow));
                mushroom.setImage(query.isNull(columnIndexOrThrow2) ? null : query.getBlob(columnIndexOrThrow2));
                mushroom.setName(query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3));
                mushroom.setScore(query.isNull(columnIndexOrThrow4) ? null : Double.valueOf(query.getDouble(columnIndexOrThrow4)));
                Integer valueOf2 = query.isNull(columnIndexOrThrow5) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow5));
                if (valueOf2 == null) {
                    valueOf = null;
                } else {
                    valueOf = Boolean.valueOf(valueOf2.intValue() != 0);
                }
                mushroom.setFavorited(valueOf);
                arrayList.add(mushroom);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.MushroomDao
    public Mushroom getFirstMushroom() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM mushroom ORDER BY id ASC LIMIT 1", 0);
        this.__db.assertNotSuspendingTransaction();
        Mushroom mushroom = null;
        Boolean valueOf = null;
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "image");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "name");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "score");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "isFavorited");
            if (query.moveToFirst()) {
                Mushroom mushroom2 = new Mushroom();
                mushroom2.setId(query.getLong(columnIndexOrThrow));
                mushroom2.setImage(query.isNull(columnIndexOrThrow2) ? null : query.getBlob(columnIndexOrThrow2));
                mushroom2.setName(query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3));
                mushroom2.setScore(query.isNull(columnIndexOrThrow4) ? null : Double.valueOf(query.getDouble(columnIndexOrThrow4)));
                Integer valueOf2 = query.isNull(columnIndexOrThrow5) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow5));
                if (valueOf2 != null) {
                    valueOf = Boolean.valueOf(valueOf2.intValue() != 0);
                }
                mushroom2.setFavorited(valueOf);
                mushroom = mushroom2;
            }
            return mushroom;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.MushroomDao
    public int getMushroomCount() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM mushroom", 0);
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
