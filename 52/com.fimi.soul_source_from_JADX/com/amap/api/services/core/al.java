package com.amap.api.services.core;

import android.content.ContentValues;
import android.database.Cursor;

public abstract class al implements ap<am> {
    private static final String f2979a;
    private static final String f2980b;
    private static final String f2981c;
    private static final String f2982d;
    private am f2983e;

    static {
        f2979a = ah.f2995l;
        f2980b = ah.f2996m;
        f2981c = ah.f2997n;
        f2982d = ah.f2989f;
    }

    public al() {
        this.f2983e = null;
    }

    public static String m4512a(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append(f2980b).append("=").append(i);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static String m4513a(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append(f2979a).append("='").append(str).append("'");
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public am m4514a(Cursor cursor) {
        Throwable th;
        am amVar = null;
        if (cursor == null) {
            return null;
        }
        try {
            String string = cursor.getString(1);
            int i = cursor.getInt(2);
            String string2 = cursor.getString(4);
            int i2 = cursor.getInt(3);
            am amVar2 = new am();
            try {
                amVar2.m4539a(string);
                amVar2.m4538a(i);
                amVar2.m4542b(at.m4574b(string2));
                amVar2.m4541b(i2);
                return amVar2;
            } catch (Throwable th2) {
                Throwable th3 = th2;
                amVar = amVar2;
                th = th3;
                th.printStackTrace();
                return amVar;
            }
        } catch (Throwable th4) {
            th = th4;
            th.printStackTrace();
            return amVar;
        }
    }

    public void m4515a(am amVar) {
        this.f2983e = amVar;
    }

    public ContentValues m4517b() {
        Throwable th;
        ContentValues contentValues = null;
        try {
            if (this.f2983e == null) {
                return null;
            }
            ContentValues contentValues2 = new ContentValues();
            try {
                contentValues2.put(f2979a, this.f2983e.m4540b());
                contentValues2.put(f2980b, Integer.valueOf(this.f2983e.m4537a()));
                contentValues2.put(f2982d, at.m4573a(this.f2983e.m4543c()));
                contentValues2.put(f2981c, Integer.valueOf(this.f2983e.m4544d()));
                return contentValues2;
            } catch (Throwable th2) {
                Throwable th3 = th2;
                contentValues = contentValues2;
                th = th3;
                th.printStackTrace();
                return contentValues;
            }
        } catch (Throwable th4) {
            th = th4;
            th.printStackTrace();
            return contentValues;
        }
    }

    public /* synthetic */ Object m4518b(Cursor cursor) {
        return m4514a(cursor);
    }
}
