package com.xiaomi.mistatistic.sdk.controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* renamed from: com.xiaomi.mistatistic.sdk.controller.p */
public class C2602p extends SQLiteOpenHelper {
    public static final Object f12975a;

    static {
        f12975a = new Object();
    }

    public C2602p(Context context) {
        super(context, "mistat.db", null, 1);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        synchronized (f12975a) {
            sQLiteDatabase.execSQL(String.format("create table %s(_id integer primary key autoincrement, category text, ts integer, key text, value text, type text, extra text)", new Object[]{"mistat_event"}));
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
