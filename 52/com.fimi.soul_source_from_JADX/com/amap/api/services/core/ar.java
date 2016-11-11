package com.amap.api.services.core;

import android.content.ContentValues;
import android.database.Cursor;

public class ar implements ap<as> {
    private static final String f3022b;
    private static final String f3023c;
    private static final String f3024d;
    private as f3025a;

    static {
        f3022b = ah.f2998o;
        f3023c = ah.f2999p;
        f3024d = ah.f3000q;
    }

    public ar() {
        this.f3025a = null;
    }

    public as m4561a(Cursor cursor) {
        as asVar;
        Throwable th;
        boolean z = true;
        try {
            int i = cursor.getInt(1);
            int i2 = cursor.getInt(2);
            int i3 = cursor.getInt(3);
            boolean z2 = i != 0;
            boolean z3 = i2 != 0;
            if (i3 == 0) {
                z = false;
            }
            asVar = new as();
            try {
                asVar.m4567a(z2);
                asVar.m4571c(z);
                asVar.m4569b(z3);
            } catch (Throwable th2) {
                th = th2;
                th.printStackTrace();
                return asVar;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            asVar = null;
            th = th4;
            th.printStackTrace();
            return asVar;
        }
        return asVar;
    }

    public String m4562a() {
        return ah.f2988e;
    }

    public void m4563a(as asVar) {
        this.f3025a = asVar;
    }

    public ContentValues m4565b() {
        Throwable th;
        ContentValues contentValues = null;
        try {
            if (this.f3025a == null) {
                return null;
            }
            ContentValues contentValues2 = new ContentValues();
            try {
                contentValues2.put(f3022b, Boolean.valueOf(this.f3025a.m4568a()));
                contentValues2.put(f3023c, Boolean.valueOf(this.f3025a.m4570b()));
                contentValues2.put(f3024d, Boolean.valueOf(this.f3025a.m4572c()));
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

    public /* synthetic */ Object m4566b(Cursor cursor) {
        return m4561a(cursor);
    }
}
