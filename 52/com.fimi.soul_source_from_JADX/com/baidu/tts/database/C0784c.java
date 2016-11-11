package com.baidu.tts.database;

import android.database.sqlite.SQLiteDatabase;

/* renamed from: com.baidu.tts.database.c */
public class C0784c {
    private SQLiteDatabase f4348a;
    private C0779a f4349b;

    /* renamed from: com.baidu.tts.database.c.a */
    public interface C0779a {
        boolean m6705a(SQLiteDatabase sQLiteDatabase);
    }

    public C0784c(SQLiteDatabase sQLiteDatabase, C0779a c0779a) {
        this.f4348a = sQLiteDatabase;
        this.f4349b = c0779a;
    }

    public boolean m6731a() {
        boolean z = false;
        if (!(this.f4349b == null || this.f4348a == null)) {
            try {
                this.f4348a.beginTransaction();
                z = this.f4349b.m6705a(this.f4348a);
                if (z) {
                    this.f4348a.setTransactionSuccessful();
                }
                if (this.f4348a != null) {
                    this.f4348a.endTransaction();
                    this.f4348a.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (this.f4348a != null) {
                    this.f4348a.endTransaction();
                    this.f4348a.close();
                }
            } catch (Throwable th) {
                if (this.f4348a != null) {
                    this.f4348a.endTransaction();
                    this.f4348a.close();
                }
            }
        }
        return z;
    }
}
