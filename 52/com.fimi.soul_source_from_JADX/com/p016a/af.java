package com.p016a;

import android.content.ContentValues;
import android.database.Cursor;

/* renamed from: com.a.af */
public class af implements C0239w<ag> {
    private static final String f527b;
    private static final String f528c;
    private static final String f529d;
    private ag f530a;

    static {
        f527b = C0263y.f1331o;
        f528c = C0263y.f1332p;
        f529d = C0263y.f1333q;
    }

    public af() {
        this.f530a = null;
    }

    public ContentValues m999a() {
        Throwable th;
        ContentValues contentValues = null;
        try {
            if (this.f530a == null) {
                return null;
            }
            ContentValues contentValues2 = new ContentValues();
            try {
                contentValues2.put(f527b, Boolean.valueOf(this.f530a.m1006a()));
                contentValues2.put(f528c, Boolean.valueOf(this.f530a.m1008b()));
                contentValues2.put(f529d, Boolean.valueOf(this.f530a.m1010c()));
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

    public /* synthetic */ Object m1000a(Cursor cursor) {
        return m1003b(cursor);
    }

    public void m1001a(ag agVar) {
        this.f530a = agVar;
    }

    public /* synthetic */ void m1002a(Object obj) {
        m1001a((ag) obj);
    }

    public ag m1003b(Cursor cursor) {
        ag agVar;
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
            agVar = new ag();
            try {
                agVar.m1005a(z2);
                agVar.m1009c(z);
                agVar.m1007b(z3);
            } catch (Throwable th2) {
                th = th2;
                th.printStackTrace();
                return agVar;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            agVar = null;
            th = th4;
            th.printStackTrace();
            return agVar;
        }
        return agVar;
    }

    public String m1004b() {
        return C0263y.f1321e;
    }
}
