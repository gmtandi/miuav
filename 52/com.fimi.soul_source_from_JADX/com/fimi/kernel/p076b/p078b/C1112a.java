package com.fimi.kernel.p076b.p078b;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.fimi.kernel.b.b.a */
class C1112a extends SQLiteOpenHelper {
    public C1112a(Context context) {
        super(context, "download.db", null, 4);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table download_info(_id integer PRIMARY KEY AUTOINCREMENT, thread_id integer, start_pos long, end_pos long, compelete_size long,url char)");
        sQLiteDatabase.execSQL("create table download_task(_id integer PRIMARY KEY AUTOINCREMENT, url char,local_uri char,file_size int,dur char)");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        switch (i) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                try {
                    sQLiteDatabase.execSQL("drop table if exists download_info");
                    sQLiteDatabase.execSQL("drop table if exists download_task");
                    onCreate(sQLiteDatabase);
                    break;
                } catch (SQLException e) {
                    e.printStackTrace();
                    break;
                }
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                break;
            case Type.BYTE /*3*/:
                break;
            default:
                return;
        }
        try {
            sQLiteDatabase.execSQL("drop table if exists download_info");
            sQLiteDatabase.execSQL("drop table if exists download_task");
            onCreate(sQLiteDatabase);
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
        try {
            sQLiteDatabase.execSQL("alter table download_task colum dur char");
        } catch (SQLException e22) {
            e22.printStackTrace();
        }
    }
}
