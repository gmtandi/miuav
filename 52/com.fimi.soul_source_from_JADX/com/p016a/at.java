package com.p016a;

import android.database.sqlite.SQLiteDatabase;

/* renamed from: com.a.at */
public class at implements C0240u {
    private static at f573a;

    private at() {
    }

    public static synchronized at m1080c() {
        at atVar;
        synchronized (at.class) {
            if (f573a == null) {
                f573a = new at();
            }
            atVar = f573a;
        }
        return atVar;
    }

    public String m1081a() {
        return "dynamicamapfile.db";
    }

    public void m1082a(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS file (_id integer primary key autoincrement, sdkname  varchar(20), filename varchar(100),md5 varchar(20),version varchar(20),dynamicversion varchar(20),status varchar(20),reservedfield varchar(20));");
        } catch (Throwable th) {
            C0247g.m1917a(th, "DynamicFileDBCreator", "onCreate");
        }
    }

    public void m1083a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public int m1084b() {
        return 1;
    }
}
