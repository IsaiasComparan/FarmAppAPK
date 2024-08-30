package com.wamessage.plantapp_plantidentifier.datatbase;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.wamessage.plantapp_plantidentifier.models.PendingIntentEntity;
import com.wamessage.plantapp_plantidentifier.utils.PendingIntentTypeConverter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public final class PendingIntentEntityDao_Impl implements PendingIntentEntityDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<PendingIntentEntity> __deletionAdapterOfPendingIntentEntity;
    private final EntityInsertionAdapter<PendingIntentEntity> __insertionAdapterOfPendingIntentEntity;
    private final EntityDeletionOrUpdateAdapter<PendingIntentEntity> __updateAdapterOfPendingIntentEntity;

    public PendingIntentEntityDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfPendingIntentEntity = new EntityInsertionAdapter<PendingIntentEntity>(__db) { // from class: com.wamessage.plantapp_plantidentifier.datatbase.PendingIntentEntityDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR ABORT INTO `pending_intent_entity` (`id`,`pendingIntent`) VALUES (?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, PendingIntentEntity value) {
                stmt.bindLong(1, value.id);
                String fromPendingIntent = PendingIntentTypeConverter.fromPendingIntent(value.getPendingIntent());
                if (fromPendingIntent == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, fromPendingIntent);
                }
            }
        };
        this.__deletionAdapterOfPendingIntentEntity = new EntityDeletionOrUpdateAdapter<PendingIntentEntity>(__db) { // from class: com.wamessage.plantapp_plantidentifier.datatbase.PendingIntentEntityDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `pending_intent_entity` WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, PendingIntentEntity value) {
                stmt.bindLong(1, value.id);
            }
        };
        this.__updateAdapterOfPendingIntentEntity = new EntityDeletionOrUpdateAdapter<PendingIntentEntity>(__db) { // from class: com.wamessage.plantapp_plantidentifier.datatbase.PendingIntentEntityDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `pending_intent_entity` SET `id` = ?,`pendingIntent` = ? WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, PendingIntentEntity value) {
                stmt.bindLong(1, value.id);
                String fromPendingIntent = PendingIntentTypeConverter.fromPendingIntent(value.getPendingIntent());
                if (fromPendingIntent == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, fromPendingIntent);
                }
                stmt.bindLong(3, value.id);
            }
        };
    }

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.PendingIntentEntityDao
    public long insertPendingIntentEntity(final PendingIntentEntity pendingIntentEntity) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            long insertAndReturnId = this.__insertionAdapterOfPendingIntentEntity.insertAndReturnId(pendingIntentEntity);
            this.__db.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.PendingIntentEntityDao
    public void deletePendingIntentEntity(final PendingIntentEntity pendingIntentEntity) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfPendingIntentEntity.handle(pendingIntentEntity);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.PendingIntentEntityDao
    public void updatePendingIntentEntity(final PendingIntentEntity pendingIntentEntity) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfPendingIntentEntity.handle(pendingIntentEntity);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.PendingIntentEntityDao
    public List<PendingIntentEntity> getPendingIntentEntities() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from pending_intent_entity", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "pendingIntent");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                PendingIntentEntity pendingIntentEntity = new PendingIntentEntity(PendingIntentTypeConverter.toPendingIntent(query.isNull(columnIndexOrThrow2) ? null : query.getString(columnIndexOrThrow2)));
                pendingIntentEntity.id = query.getLong(columnIndexOrThrow);
                arrayList.add(pendingIntentEntity);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.PendingIntentEntityDao
    public PendingIntentEntity getPendingIntentEntityById(final long id) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM pending_intent_entity WHERE id = ?", 1);
        acquire.bindLong(1, id);
        this.__db.assertNotSuspendingTransaction();
        PendingIntentEntity pendingIntentEntity = null;
        String string = null;
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "pendingIntent");
            if (query.moveToFirst()) {
                if (!query.isNull(columnIndexOrThrow2)) {
                    string = query.getString(columnIndexOrThrow2);
                }
                PendingIntentEntity pendingIntentEntity2 = new PendingIntentEntity(PendingIntentTypeConverter.toPendingIntent(string));
                pendingIntentEntity2.id = query.getLong(columnIndexOrThrow);
                pendingIntentEntity = pendingIntentEntity2;
            }
            return pendingIntentEntity;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
