package com.fimi.soul.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/* renamed from: com.fimi.soul.utils.h */
public class C1968h extends SQLiteOpenHelper {
    private static final String f10150a = "miplaner";
    private static final int f10151b = 2;
    private Context f10152c;

    public C1968h(Context context, String str, CursorFactory cursorFactory, int i) {
        super(context, f10150a, cursorFactory, f10151b);
        this.f10152c = context;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        bq.m12439a(sQLiteDatabase);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL(C1982v.f10210b);
        sQLiteDatabase.execSQL(C1963c.f10126b);
    }
}
