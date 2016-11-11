package com.xiaomi.market.sdk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

/* renamed from: com.xiaomi.market.sdk.r */
public class C2545r extends SQLiteOpenHelper {
    private static final String TAG = "MarketSDKDatabaseHelper";
    private static C2545r aY = null;
    static final String aZ = "xiaomi_market_sdk_update.db";
    static final int ba = 1;

    private C2545r(Context context) {
        super(context, aZ, null, ba);
    }

    private void m14543a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(C2539l.aH);
    }

    public static C2545r m14544j(Context context) {
        if (aY == null) {
            aY = new C2545r(context);
        }
        return aY;
    }

    public long m14545a(ContentValues contentValues) {
        SQLiteStatement compileStatement = getWritableDatabase().compileStatement("INSERT OR REPLACE INTO update_download(package_name,download_id,version_code,apk_url,apk_hash,diff_url,diff_hash,apk_path) VALUES(?,?,?,?,?,?,?,?)");
        compileStatement.bindString(ba, contentValues.getAsString(C2539l.PACKAGE_NAME));
        compileStatement.bindLong(2, contentValues.getAsLong(C2539l.aF).longValue());
        compileStatement.bindLong(3, (long) contentValues.getAsInteger(C2539l.aw).intValue());
        compileStatement.bindString(4, contentValues.getAsString(C2539l.ay));
        compileStatement.bindString(5, contentValues.getAsString(C2539l.az));
        compileStatement.bindString(6, contentValues.getAsString(C2539l.aB));
        compileStatement.bindString(7, contentValues.getAsString(C2539l.aC));
        compileStatement.bindString(8, contentValues.getAsString(C2539l.aG));
        compileStatement.execute();
        return 1;
    }

    public synchronized long m14546a(String str, ContentValues contentValues) {
        return getWritableDatabase().insert(str, null, contentValues);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        Log.d(TAG, "create database");
        m14543a(sQLiteDatabase);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public Cursor query(String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5) {
        return getReadableDatabase().query(str, strArr, str2, strArr2, str3, str4, str5);
    }
}
