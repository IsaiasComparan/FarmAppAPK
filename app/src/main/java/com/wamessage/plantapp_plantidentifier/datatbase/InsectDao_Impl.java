package com.wamessage.plantapp_plantidentifier.datatbase;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.wamessage.plantapp_plantidentifier.models.Insect;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public final class InsectDao_Impl implements InsectDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<Insect> __deletionAdapterOfInsect;
    private final EntityInsertionAdapter<Insect> __insertionAdapterOfInsect;
    private final EntityDeletionOrUpdateAdapter<Insect> __updateAdapterOfInsect;

    public InsectDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfInsect = new EntityInsertionAdapter<Insect>(__db) { // from class: com.wamessage.plantapp_plantidentifier.datatbase.InsectDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR ABORT INTO `insect` (`id`,`image`,`name`,`score`,`isFavorited`) VALUES (nullif(?, 0),?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, Insect value) {
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
        this.__deletionAdapterOfInsect = new EntityDeletionOrUpdateAdapter<Insect>(__db) { // from class: com.wamessage.plantapp_plantidentifier.datatbase.InsectDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `insect` WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, Insect value) {
                stmt.bindLong(1, value.getId());
            }
        };
        this.__updateAdapterOfInsect = new EntityDeletionOrUpdateAdapter<Insect>(__db) { // from class: com.wamessage.plantapp_plantidentifier.datatbase.InsectDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `insect` SET `id` = ?,`image` = ?,`name` = ?,`score` = ?,`isFavorited` = ? WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, Insect value) {
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

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.InsectDao
    public long insertInsect(final Insect insect) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            long insertAndReturnId = this.__insertionAdapterOfInsect.insertAndReturnId(insect);
            this.__db.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.InsectDao
    public void deleteInsect(final Insect insect) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfInsect.handle(insect);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.InsectDao
    public void updateInsect(final Insect insect) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfInsect.handle(insect);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.InsectDao
    public List<Insect> getInsects() {
        Boolean valueOf;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from insect", 0);
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
                Insect insect = new Insect();
                insect.setId(query.getLong(columnIndexOrThrow));
                insect.setImage(query.isNull(columnIndexOrThrow2) ? null : query.getBlob(columnIndexOrThrow2));
                insect.setName(query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3));
                insect.setScore(query.isNull(columnIndexOrThrow4) ? null : Double.valueOf(query.getDouble(columnIndexOrThrow4)));
                Integer valueOf2 = query.isNull(columnIndexOrThrow5) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow5));
                if (valueOf2 == null) {
                    valueOf = null;
                } else {
                    valueOf = Boolean.valueOf(valueOf2.intValue() != 0);
                }
                insect.setFavorited(valueOf);
                arrayList.add(insect);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.InsectDao
    public Insect getFirstInsect() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM insect ORDER BY id ASC LIMIT 1", 0);
        this.__db.assertNotSuspendingTransaction();
        Insect insect = null;
        Boolean valueOf = null;
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "image");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "name");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "score");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "isFavorited");
            if (query.moveToFirst()) {
                Insect insect2 = new Insect();
                insect2.setId(query.getLong(columnIndexOrThrow));
                insect2.setImage(query.isNull(columnIndexOrThrow2) ? null : query.getBlob(columnIndexOrThrow2));
                insect2.setName(query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3));
                insect2.setScore(query.isNull(columnIndexOrThrow4) ? null : Double.valueOf(query.getDouble(columnIndexOrThrow4)));
                Integer valueOf2 = query.isNull(columnIndexOrThrow5) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow5));
                if (valueOf2 != null) {
                    valueOf = Boolean.valueOf(valueOf2.intValue() != 0);
                }
                insect2.setFavorited(valueOf);
                insect = insect2;
            }
            return insect;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.InsectDao
    public int getInsectCount() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM insect", 0);
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
