package com.wamessage.plantapp_plantidentifier.datatbase;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.wamessage.plantapp_plantidentifier.models.Tree;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public final class TreeDao_Impl implements TreeDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<Tree> __deletionAdapterOfTree;
    private final EntityInsertionAdapter<Tree> __insertionAdapterOfTree;
    private final EntityDeletionOrUpdateAdapter<Tree> __updateAdapterOfTree;

    public TreeDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfTree = new EntityInsertionAdapter<Tree>(__db) { // from class: com.wamessage.plantapp_plantidentifier.datatbase.TreeDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR ABORT INTO `tree` (`id`,`image`,`name`,`score`,`isFavorited`) VALUES (nullif(?, 0),?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, Tree value) {
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
        this.__deletionAdapterOfTree = new EntityDeletionOrUpdateAdapter<Tree>(__db) { // from class: com.wamessage.plantapp_plantidentifier.datatbase.TreeDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `tree` WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, Tree value) {
                stmt.bindLong(1, value.getId());
            }
        };
        this.__updateAdapterOfTree = new EntityDeletionOrUpdateAdapter<Tree>(__db) { // from class: com.wamessage.plantapp_plantidentifier.datatbase.TreeDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `tree` SET `id` = ?,`image` = ?,`name` = ?,`score` = ?,`isFavorited` = ? WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, Tree value) {
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

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.TreeDao
    public long insertTree(final Tree tree) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            long insertAndReturnId = this.__insertionAdapterOfTree.insertAndReturnId(tree);
            this.__db.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.TreeDao
    public void deleteTree(final Tree tree) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfTree.handle(tree);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.TreeDao
    public void updateTree(final Tree tree) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfTree.handle(tree);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.TreeDao
    public List<Tree> getTrees() {
        Boolean valueOf;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from tree", 0);
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
                Tree tree = new Tree();
                tree.setId(query.getLong(columnIndexOrThrow));
                tree.setImage(query.isNull(columnIndexOrThrow2) ? null : query.getBlob(columnIndexOrThrow2));
                tree.setName(query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3));
                tree.setScore(query.isNull(columnIndexOrThrow4) ? null : Double.valueOf(query.getDouble(columnIndexOrThrow4)));
                Integer valueOf2 = query.isNull(columnIndexOrThrow5) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow5));
                if (valueOf2 == null) {
                    valueOf = null;
                } else {
                    valueOf = Boolean.valueOf(valueOf2.intValue() != 0);
                }
                tree.setFavorited(valueOf);
                arrayList.add(tree);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.TreeDao
    public Tree getFirstTree() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM tree ORDER BY id ASC LIMIT 1", 0);
        this.__db.assertNotSuspendingTransaction();
        Tree tree = null;
        Boolean valueOf = null;
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "image");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "name");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "score");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "isFavorited");
            if (query.moveToFirst()) {
                Tree tree2 = new Tree();
                tree2.setId(query.getLong(columnIndexOrThrow));
                tree2.setImage(query.isNull(columnIndexOrThrow2) ? null : query.getBlob(columnIndexOrThrow2));
                tree2.setName(query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3));
                tree2.setScore(query.isNull(columnIndexOrThrow4) ? null : Double.valueOf(query.getDouble(columnIndexOrThrow4)));
                Integer valueOf2 = query.isNull(columnIndexOrThrow5) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow5));
                if (valueOf2 != null) {
                    valueOf = Boolean.valueOf(valueOf2.intValue() != 0);
                }
                tree2.setFavorited(valueOf);
                tree = tree2;
            }
            return tree;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.wamessage.plantapp_plantidentifier.datatbase.TreeDao
    public int getTreeCount() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM tree", 0);
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
