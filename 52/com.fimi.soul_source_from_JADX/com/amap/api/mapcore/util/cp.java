package com.amap.api.mapcore.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class cp extends SQLiteOpenHelper {
    private cj f2350a;

    public cp(Context context, String str, CursorFactory cursorFactory, int i, cj cjVar) {
        super(context, str, cursorFactory, i);
        this.f2350a = cjVar;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        this.f2350a.m3878a(sQLiteDatabase);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        this.f2350a.m3879a(sQLiteDatabase, i, i2);
    }
}
