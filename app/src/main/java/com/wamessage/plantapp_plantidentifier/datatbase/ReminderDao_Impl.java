package com.wamessage.plantapp_plantidentifier.datatbase;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.wamessage.plantapp_plantidentifier.models.Reminder;
import com.wamessage.plantapp_plantidentifier.utils.DateTypeConverter;
import com.wamessage.plantapp_plantidentifier.utils.IntegerListConverter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public final class ReminderDao_Impl implements ReminderDao {
    private final DateTypeConverter __dateTypeConverter = new DateTypeConverter();
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<Reminder> __deletionAdapterOfReminder;
    private final EntityInsertionAdapter<Reminder> __insertionAdapterOfReminder;
    private final EntityDeletionOrUpdateAdapter<Reminder> __updateAdapterOfReminder;

    public ReminderDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfReminder = new EntityInsertionAdapter<Reminder>(__db) { // from class: com.wamessage.plantapp_plantidentifier.datatbase.ReminderDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR ABORT INTO `reminder` (`id`,`plantName`,`alarmName`,`alarmTime`,`dayOfWeeks`,`image`,`type`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, Reminder value) {
                stmt.bindLong(1, value.getId());
                if (value.getPlantName() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getPlantName());
                }
                if (value.getAlarmName() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getAlarmName());
                }
                Long timestamp = ReminderDao_Impl.this.__dateTypeConverter.toTimestamp(value.getAlarmTime());
                if (timestamp == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindLong(4, timestamp.longValue());
                }
                String fromIntegerList = IntegerListConverter.fromIntegerList(value.getDayOfWeeks());
                if (fromIntegerList == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, fromIntegerList);
                }
                if (value.getImage() == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindBlob(6, value.getImage());
                }
                if (value.getType() == null) {
                    stmt.bindNull(7);
                } else {
                    stmt.bindString(7, value.getType());
                }
            }
        };
        this.__deletionAdapterOfReminder = new EntityDeletionOrUpdateAdapter<Reminder>(__db) { // from class: com.wamessage.plantapp_plantidentifier.datatbase.ReminderDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `reminder` WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, Reminder value) {
                stmt.bindLong(1, value.getId());
            }
        };
        this.__updateAdapterOfReminder = new EntityDeletionOrUpdateAdapter<Reminder>(__db) { // from class: com.wamessage.plantapp_plantidentifier.datatbase.ReminderDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `reminder` SET `id` = ?,`plantName` = ?,`alarmName` = ?,`alarmTime` = ?,`dayOfWeeks` = ?,`image` = ?,`type` = ? WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, Reminder value) {
                stmt.bindLong(1, value.getId());
                if (value.getPlantName() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getPlantName());
                }
                if (value.getAlarmName() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getAlarmName());
                }
                Long timestamp = ReminderDao_Impl.this.__dateTypeConverter.toTimestamp(value.getAlarmTime());
                if (timestamp == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindLong(4, timestamp.longValue());
                }
                String fromIntegerList = IntegerListConverter.fromIntegerList(value.getDayOfWeeks());
                if (fromIntegerList == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, fromIntegerList);
                }
                if (value.getImage() == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindBlob(6, value.getImage());
                }
                if (value.getType() == null) {
                    stmt.bindNull(7);
                } else {
                    stmt.bindString(7, value.getType());
                }
                stmt.bindLong(8, value.getId());
            }
        };
    }

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.ReminderDao
    public long insertReminder(final Reminder reminder) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            long insertAndReturnId = this.__insertionAdapterOfReminder.insertAndReturnId(reminder);
            this.__db.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.ReminderDao
    public void deleteReminder(final Reminder reminder) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfReminder.handle(reminder);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.ReminderDao
    public void updateReminder(final Reminder reminder) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfReminder.handle(reminder);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.ReminderDao
    public List<Reminder> getReminders(final String type) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from reminder where reminder.type = ?", 1);
        if (type == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, type);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "plantName");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "alarmName");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "alarmTime");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "dayOfWeeks");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "image");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "type");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                Reminder reminder = new Reminder();
                reminder.setId(query.getLong(columnIndexOrThrow));
                reminder.setPlantName(query.isNull(columnIndexOrThrow2) ? null : query.getString(columnIndexOrThrow2));
                reminder.setAlarmName(query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3));
                reminder.setAlarmTime(this.__dateTypeConverter.toDate(query.isNull(columnIndexOrThrow4) ? null : Long.valueOf(query.getLong(columnIndexOrThrow4))));
                reminder.setDayOfWeeks(IntegerListConverter.toIntegerList(query.isNull(columnIndexOrThrow5) ? null : query.getString(columnIndexOrThrow5)));
                reminder.setImage(query.isNull(columnIndexOrThrow6) ? null : query.getBlob(columnIndexOrThrow6));
                reminder.setType(query.isNull(columnIndexOrThrow7) ? null : query.getString(columnIndexOrThrow7));
                arrayList.add(reminder);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
