package com.baidu.tts.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/* renamed from: com.baidu.tts.database.b */
public class C0783b extends SQLiteOpenHelper {
    public C0783b(Context context) {
        this(context, "ttsModel.db", null, 1);
    }

    public C0783b(Context context, String str, CursorFactory cursorFactory, int i) {
        super(context, str, cursorFactory, i);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(SpeechModelTable.m6713a());
        sQLiteDatabase.execSQL(ModelFileTable.m6708a());
        sQLiteDatabase.execSQL(FsFileInfoTable.m6703a());
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL(SpeechModelTable.m6715b());
        sQLiteDatabase.execSQL(ModelFileTable.m6710b());
        sQLiteDatabase.execSQL(FsFileInfoTable.m6704b());
        onCreate(sQLiteDatabase);
    }
}
