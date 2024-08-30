package com.wamessage.plantapp_plantidentifier.datatbase;

import androidx.core.app.NotificationCompat;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public final class AppDatabase_Impl extends AppDatabase {
    private volatile InsectDao _insectDao;
    private volatile MushroomDao _mushroomDao;
    private volatile PendingIntentEntityDao _pendingIntentEntityDao;
    private volatile PlantDao _plantDao;
    private volatile PlantHealthDao _plantHealthDao;
    private volatile ReminderDao _reminderDao;
    private volatile TreeDao _treeDao;

    @Override // androidx.room.RoomDatabase
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
        return configuration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(configuration.context).name(configuration.name).callback(new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(2) { // from class: com.wamessage.plantapp_plantidentifier.datatbase.AppDatabase_Impl.1
            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPostMigrate(SupportSQLiteDatabase _db) {
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void createAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("CREATE TABLE IF NOT EXISTS `plant` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `image` BLOB, `name` TEXT, `score` REAL, `isFavorited` INTEGER)");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `insect` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `image` BLOB, `name` TEXT, `score` REAL, `isFavorited` INTEGER)");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `planthealth` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `image` BLOB, `name` TEXT, `score` REAL, `isFavorited` INTEGER)");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `mushroom` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `image` BLOB, `name` TEXT, `score` REAL, `isFavorited` INTEGER)");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `tree` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `image` BLOB, `name` TEXT, `score` REAL, `isFavorited` INTEGER)");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `reminder` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `plantName` TEXT, `alarmName` TEXT, `alarmTime` INTEGER, `dayOfWeeks` TEXT, `image` BLOB, `type` TEXT)");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `my_day` (`idMyDay` INTEGER NOT NULL, `namedDay` INTEGER NOT NULL, `reminderID` INTEGER NOT NULL, `selected` INTEGER NOT NULL, PRIMARY KEY(`idMyDay`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `pending_intent_entity` (`id` INTEGER NOT NULL, `pendingIntent` TEXT, PRIMARY KEY(`id`))");
                _db.execSQL(RoomMasterTable.CREATE_QUERY);
                _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'ad2e34dd030f635c12041a2fe7b0a506')");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void dropAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("DROP TABLE IF EXISTS `plant`");
                _db.execSQL("DROP TABLE IF EXISTS `insect`");
                _db.execSQL("DROP TABLE IF EXISTS `planthealth`");
                _db.execSQL("DROP TABLE IF EXISTS `mushroom`");
                _db.execSQL("DROP TABLE IF EXISTS `tree`");
                _db.execSQL("DROP TABLE IF EXISTS `reminder`");
                _db.execSQL("DROP TABLE IF EXISTS `my_day`");
                _db.execSQL("DROP TABLE IF EXISTS `pending_intent_entity`");
                if (AppDatabase_Impl.this.mCallbacks != null) {
                    int size = AppDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) AppDatabase_Impl.this.mCallbacks.get(i)).onDestructiveMigration(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onCreate(SupportSQLiteDatabase _db) {
                if (AppDatabase_Impl.this.mCallbacks != null) {
                    int size = AppDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) AppDatabase_Impl.this.mCallbacks.get(i)).onCreate(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onOpen(SupportSQLiteDatabase _db) {
                AppDatabase_Impl.this.mDatabase = _db;
                AppDatabase_Impl.this.internalInitInvalidationTracker(_db);
                if (AppDatabase_Impl.this.mCallbacks != null) {
                    int size = AppDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) AppDatabase_Impl.this.mCallbacks.get(i)).onOpen(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPreMigrate(SupportSQLiteDatabase _db) {
                DBUtil.dropFtsSyncTriggers(_db);
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
                HashMap hashMap = new HashMap(5);
                hashMap.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                hashMap.put("image", new TableInfo.Column("image", "BLOB", false, 0, null, 1));
                hashMap.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, 1));
                hashMap.put("score", new TableInfo.Column("score", "REAL", false, 0, null, 1));
                hashMap.put("isFavorited", new TableInfo.Column("isFavorited", "INTEGER", false, 0, null, 1));
                TableInfo tableInfo = new TableInfo("plant", hashMap, new HashSet(0), new HashSet(0));
                TableInfo read = TableInfo.read(_db, "plant");
                if (!tableInfo.equals(read)) {
                    return new RoomOpenHelper.ValidationResult(false, "plant(com.wamessage.plantapp_plantidentifier.models.Plant).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
                }
                HashMap hashMap2 = new HashMap(5);
                hashMap2.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                hashMap2.put("image", new TableInfo.Column("image", "BLOB", false, 0, null, 1));
                hashMap2.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, 1));
                hashMap2.put("score", new TableInfo.Column("score", "REAL", false, 0, null, 1));
                hashMap2.put("isFavorited", new TableInfo.Column("isFavorited", "INTEGER", false, 0, null, 1));
                TableInfo tableInfo2 = new TableInfo("insect", hashMap2, new HashSet(0), new HashSet(0));
                TableInfo read2 = TableInfo.read(_db, "insect");
                if (!tableInfo2.equals(read2)) {
                    return new RoomOpenHelper.ValidationResult(false, "insect(com.wamessage.plantapp_plantidentifier.models.Insect).\n Expected:\n" + tableInfo2 + "\n Found:\n" + read2);
                }
                HashMap hashMap3 = new HashMap(5);
                hashMap3.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                hashMap3.put("image", new TableInfo.Column("image", "BLOB", false, 0, null, 1));
                hashMap3.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, 1));
                hashMap3.put("score", new TableInfo.Column("score", "REAL", false, 0, null, 1));
                hashMap3.put("isFavorited", new TableInfo.Column("isFavorited", "INTEGER", false, 0, null, 1));
                TableInfo tableInfo3 = new TableInfo("planthealth", hashMap3, new HashSet(0), new HashSet(0));
                TableInfo read3 = TableInfo.read(_db, "planthealth");
                if (!tableInfo3.equals(read3)) {
                    return new RoomOpenHelper.ValidationResult(false, "planthealth(com.wamessage.plantapp_plantidentifier.models.PlantHealth).\n Expected:\n" + tableInfo3 + "\n Found:\n" + read3);
                }
                HashMap hashMap4 = new HashMap(5);
                hashMap4.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                hashMap4.put("image", new TableInfo.Column("image", "BLOB", false, 0, null, 1));
                hashMap4.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, 1));
                hashMap4.put("score", new TableInfo.Column("score", "REAL", false, 0, null, 1));
                hashMap4.put("isFavorited", new TableInfo.Column("isFavorited", "INTEGER", false, 0, null, 1));
                TableInfo tableInfo4 = new TableInfo("mushroom", hashMap4, new HashSet(0), new HashSet(0));
                TableInfo read4 = TableInfo.read(_db, "mushroom");
                if (!tableInfo4.equals(read4)) {
                    return new RoomOpenHelper.ValidationResult(false, "mushroom(com.wamessage.plantapp_plantidentifier.models.Mushroom).\n Expected:\n" + tableInfo4 + "\n Found:\n" + read4);
                }
                HashMap hashMap5 = new HashMap(5);
                hashMap5.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                hashMap5.put("image", new TableInfo.Column("image", "BLOB", false, 0, null, 1));
                hashMap5.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, 1));
                hashMap5.put("score", new TableInfo.Column("score", "REAL", false, 0, null, 1));
                hashMap5.put("isFavorited", new TableInfo.Column("isFavorited", "INTEGER", false, 0, null, 1));
                TableInfo tableInfo5 = new TableInfo("tree", hashMap5, new HashSet(0), new HashSet(0));
                TableInfo read5 = TableInfo.read(_db, "tree");
                if (!tableInfo5.equals(read5)) {
                    return new RoomOpenHelper.ValidationResult(false, "tree(com.wamessage.plantapp_plantidentifier.models.Tree).\n Expected:\n" + tableInfo5 + "\n Found:\n" + read5);
                }
                HashMap hashMap6 = new HashMap(7);
                hashMap6.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                hashMap6.put("plantName", new TableInfo.Column("plantName", "TEXT", false, 0, null, 1));
                hashMap6.put("alarmName", new TableInfo.Column("alarmName", "TEXT", false, 0, null, 1));
                hashMap6.put("alarmTime", new TableInfo.Column("alarmTime", "INTEGER", false, 0, null, 1));
                hashMap6.put("dayOfWeeks", new TableInfo.Column("dayOfWeeks", "TEXT", false, 0, null, 1));
                hashMap6.put("image", new TableInfo.Column("image", "BLOB", false, 0, null, 1));
                hashMap6.put("type", new TableInfo.Column("type", "TEXT", false, 0, null, 1));
                TableInfo tableInfo6 = new TableInfo(NotificationCompat.CATEGORY_REMINDER, hashMap6, new HashSet(0), new HashSet(0));
                TableInfo read6 = TableInfo.read(_db, NotificationCompat.CATEGORY_REMINDER);
                if (!tableInfo6.equals(read6)) {
                    return new RoomOpenHelper.ValidationResult(false, "reminder(com.wamessage.plantapp_plantidentifier.models.Reminder).\n Expected:\n" + tableInfo6 + "\n Found:\n" + read6);
                }
                HashMap hashMap7 = new HashMap(4);
                hashMap7.put("idMyDay", new TableInfo.Column("idMyDay", "INTEGER", true, 1, null, 1));
                hashMap7.put("namedDay", new TableInfo.Column("namedDay", "INTEGER", true, 0, null, 1));
                hashMap7.put("reminderID", new TableInfo.Column("reminderID", "INTEGER", true, 0, null, 1));
                hashMap7.put("selected", new TableInfo.Column("selected", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo7 = new TableInfo("my_day", hashMap7, new HashSet(0), new HashSet(0));
                TableInfo read7 = TableInfo.read(_db, "my_day");
                if (!tableInfo7.equals(read7)) {
                    return new RoomOpenHelper.ValidationResult(false, "my_day(com.wamessage.plantapp_plantidentifier.models.MyDay).\n Expected:\n" + tableInfo7 + "\n Found:\n" + read7);
                }
                HashMap hashMap8 = new HashMap(2);
                hashMap8.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                hashMap8.put("pendingIntent", new TableInfo.Column("pendingIntent", "TEXT", false, 0, null, 1));
                TableInfo tableInfo8 = new TableInfo("pending_intent_entity", hashMap8, new HashSet(0), new HashSet(0));
                TableInfo read8 = TableInfo.read(_db, "pending_intent_entity");
                if (!tableInfo8.equals(read8)) {
                    return new RoomOpenHelper.ValidationResult(false, "pending_intent_entity(com.wamessage.plantapp_plantidentifier.models.PendingIntentEntity).\n Expected:\n" + tableInfo8 + "\n Found:\n" + read8);
                }
                return new RoomOpenHelper.ValidationResult(true, null);
            }
        }, "ad2e34dd030f635c12041a2fe7b0a506", "adab4377ad80d6624033be04f0f90b26")).build());
    }

    @Override // androidx.room.RoomDatabase
    protected InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "plant", "insect", "planthealth", "mushroom", "tree", NotificationCompat.CATEGORY_REMINDER, "my_day", "pending_intent_entity");
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `plant`");
            writableDatabase.execSQL("DELETE FROM `insect`");
            writableDatabase.execSQL("DELETE FROM `planthealth`");
            writableDatabase.execSQL("DELETE FROM `mushroom`");
            writableDatabase.execSQL("DELETE FROM `tree`");
            writableDatabase.execSQL("DELETE FROM `reminder`");
            writableDatabase.execSQL("DELETE FROM `my_day`");
            writableDatabase.execSQL("DELETE FROM `pending_intent_entity`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            writableDatabase.query("PRAGMA wal_checkpoint(FULL)").close();
            if (!writableDatabase.inTransaction()) {
                writableDatabase.execSQL("VACUUM");
            }
        }
    }

    @Override // androidx.room.RoomDatabase
    protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        HashMap hashMap = new HashMap();
        hashMap.put(PlantDao.class, PlantDao_Impl.getRequiredConverters());
        hashMap.put(InsectDao.class, InsectDao_Impl.getRequiredConverters());
        hashMap.put(PlantHealthDao.class, PlantHealthDao_Impl.getRequiredConverters());
        hashMap.put(MushroomDao.class, MushroomDao_Impl.getRequiredConverters());
        hashMap.put(TreeDao.class, TreeDao_Impl.getRequiredConverters());
        hashMap.put(ReminderDao.class, ReminderDao_Impl.getRequiredConverters());
        hashMap.put(PendingIntentEntityDao.class, PendingIntentEntityDao_Impl.getRequiredConverters());
        return hashMap;
    }

    @Override // androidx.room.RoomDatabase
    public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
        return new HashSet();
    }

    @Override // androidx.room.RoomDatabase
    public List<Migration> getAutoMigrations(Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
        return Arrays.asList(new Migration[0]);
    }

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.AppDatabase
    public PlantDao plantDao() {
        PlantDao plantDao;
        if (this._plantDao != null) {
            return this._plantDao;
        }
        synchronized (this) {
            if (this._plantDao == null) {
                this._plantDao = new PlantDao_Impl(this);
            }
            plantDao = this._plantDao;
        }
        return plantDao;
    }

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.AppDatabase
    public InsectDao insectDao() {
        InsectDao insectDao;
        if (this._insectDao != null) {
            return this._insectDao;
        }
        synchronized (this) {
            if (this._insectDao == null) {
                this._insectDao = new InsectDao_Impl(this);
            }
            insectDao = this._insectDao;
        }
        return insectDao;
    }

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.AppDatabase
    public PlantHealthDao plantHealthDao() {
        PlantHealthDao plantHealthDao;
        if (this._plantHealthDao != null) {
            return this._plantHealthDao;
        }
        synchronized (this) {
            if (this._plantHealthDao == null) {
                this._plantHealthDao = new PlantHealthDao_Impl(this);
            }
            plantHealthDao = this._plantHealthDao;
        }
        return plantHealthDao;
    }

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.AppDatabase
    public MushroomDao mushroomDao() {
        MushroomDao mushroomDao;
        if (this._mushroomDao != null) {
            return this._mushroomDao;
        }
        synchronized (this) {
            if (this._mushroomDao == null) {
                this._mushroomDao = new MushroomDao_Impl(this);
            }
            mushroomDao = this._mushroomDao;
        }
        return mushroomDao;
    }

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.AppDatabase
    public TreeDao treeDao() {
        TreeDao treeDao;
        if (this._treeDao != null) {
            return this._treeDao;
        }
        synchronized (this) {
            if (this._treeDao == null) {
                this._treeDao = new TreeDao_Impl(this);
            }
            treeDao = this._treeDao;
        }
        return treeDao;
    }

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.AppDatabase
    public ReminderDao reminderDao() {
        ReminderDao reminderDao;
        if (this._reminderDao != null) {
            return this._reminderDao;
        }
        synchronized (this) {
            if (this._reminderDao == null) {
                this._reminderDao = new ReminderDao_Impl(this);
            }
            reminderDao = this._reminderDao;
        }
        return reminderDao;
    }

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.AppDatabase
    public PendingIntentEntityDao pendingIntentEntityDao() {
        PendingIntentEntityDao pendingIntentEntityDao;
        if (this._pendingIntentEntityDao != null) {
            return this._pendingIntentEntityDao;
        }
        synchronized (this) {
            if (this._pendingIntentEntityDao == null) {
                this._pendingIntentEntityDao = new PendingIntentEntityDao_Impl(this);
            }
            pendingIntentEntityDao = this._pendingIntentEntityDao;
        }
        return pendingIntentEntityDao;
    }
}
