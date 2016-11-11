package com.p016a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/* renamed from: com.a.x */
public class C0262x extends SQLiteOpenHelper {
    private C0240u f1316a;

    public C0262x(Context context, String str, CursorFactory cursorFactory, int i, C0240u c0240u) {
        super(context, str, cursorFactory, i);
        this.f1316a = c0240u;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        this.f1316a.m1077a(sQLiteDatabase);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        this.f1316a.m1078a(sQLiteDatabase, i, i2);
    }
}
