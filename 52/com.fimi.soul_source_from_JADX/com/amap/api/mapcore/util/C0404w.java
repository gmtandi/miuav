package com.amap.api.mapcore.util;

import android.database.sqlite.SQLiteDatabase;

/* renamed from: com.amap.api.mapcore.util.w */
public class C0404w implements cj {
    private static volatile C0404w f2541a;

    private C0404w() {
    }

    public static C0404w m4218a() {
        if (f2541a == null) {
            synchronized (C0404w.class) {
                if (f2541a == null) {
                    f2541a = new C0404w();
                }
            }
        }
        return f2541a;
    }

    public void m4219a(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS update_item (_id integer primary key autoincrement, title  TEXT, url TEXT,mAdcode TEXT,fileName TEXT,version TEXT,lLocalLength INTEGER,lRemoteLength INTEGER,localPath TEXT,mIndex INTEGER,isProvince INTEGER NOT NULL,mCompleteCode INTEGER,mCityCode TEXT,mState INTEGER, UNIQUE(mAdcode));");
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS update_item_file (_id integer primary key autoincrement,mAdcode TTEXT, file TEXT);");
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS update_item_download_info (_id integer primary key autoincrement,mAdcode TEXT,fileLength integer,splitter integer,startPos integer,endPos integer, UNIQUE(mAdcode));");
        } catch (Throwable th) {
            ce.m3829a(th, "DB", "onCreate");
            th.printStackTrace();
        }
    }

    public void m4220a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public String m4221b() {
        return "offlineDbV4.db";
    }

    public int m4222c() {
        return 1;
    }
}
