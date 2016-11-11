package com.tencent.stat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.tencent.mm.sdk.message.RMsgInfo;
import com.tencent.stat.common.C2418k;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.tencent.stat.w */
class C2443w extends SQLiteOpenHelper {
    public C2443w(Context context) {
        super(context, C2418k.m14049v(context), null, 3);
    }

    private void m14110a(SQLiteDatabase sQLiteDatabase) {
        Object th;
        Throwable th2;
        String str = null;
        Cursor query;
        try {
            query = sQLiteDatabase.query("user", null, null, null, null, null, null);
            try {
                ContentValues contentValues = new ContentValues();
                if (query.moveToNext()) {
                    str = query.getString(0);
                    query.getInt(1);
                    query.getString(2);
                    query.getLong(3);
                    contentValues.put("uid", C2418k.m14023c(str));
                }
                if (str != null) {
                    sQLiteDatabase.update("user", contentValues, "uid=?", new String[]{str});
                }
                if (query != null) {
                    query.close();
                }
            } catch (Throwable th3) {
                th = th3;
                try {
                    C2434n.f12383e.m13978e(th);
                    if (query != null) {
                        query.close();
                    }
                } catch (Throwable th4) {
                    th2 = th4;
                    if (query != null) {
                        query.close();
                    }
                    throw th2;
                }
            }
        } catch (Throwable th5) {
            th2 = th5;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th2;
        }
    }

    private void m14111b(SQLiteDatabase sQLiteDatabase) {
        Cursor query;
        Object th;
        Cursor cursor;
        Throwable th2;
        try {
            query = sQLiteDatabase.query("events", null, null, null, null, null, null);
            try {
                List<C2444x> arrayList = new ArrayList();
                while (query.moveToNext()) {
                    arrayList.add(new C2444x(query.getLong(0), query.getString(1), query.getInt(2), query.getInt(3)));
                }
                ContentValues contentValues = new ContentValues();
                for (C2444x c2444x : arrayList) {
                    contentValues.put(RMsgInfo.COL_CONTENT, C2418k.m14023c(c2444x.f12408b));
                    sQLiteDatabase.update("events", contentValues, "event_id=?", new String[]{Long.toString(c2444x.f12407a)});
                }
                if (query != null) {
                    query.close();
                }
            } catch (Throwable th3) {
                th2 = th3;
                if (query != null) {
                    query.close();
                }
                throw th2;
            }
        } catch (Throwable th4) {
            th2 = th4;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th2;
        }
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists events(event_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, content TEXT, status INTEGER, send_count INTEGER, timestamp LONG)");
        sQLiteDatabase.execSQL("create table if not exists user(uid TEXT PRIMARY KEY, user_type INTEGER, app_ver TEXT, ts INTEGER)");
        sQLiteDatabase.execSQL("create table if not exists config(type INTEGER PRIMARY KEY NOT NULL, content TEXT, md5sum TEXT, version INTEGER)");
        sQLiteDatabase.execSQL("create table if not exists keyvalues(key TEXT PRIMARY KEY NOT NULL, value TEXT)");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        C2434n.f12383e.debug("upgrade DB from oldVersion " + i + " to newVersion " + i2);
        if (i == 1) {
            sQLiteDatabase.execSQL("create table if not exists keyvalues(key TEXT PRIMARY KEY NOT NULL, value TEXT)");
            m14110a(sQLiteDatabase);
            m14111b(sQLiteDatabase);
        }
        if (i == 2) {
            m14110a(sQLiteDatabase);
            m14111b(sQLiteDatabase);
        }
    }
}
