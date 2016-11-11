package cn.sharesdk.framework.p013b.p014a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cn.sharesdk.framework.utils.C0205d;

/* renamed from: cn.sharesdk.framework.b.a.b */
public class C0166b {
    private static C0166b f218c;
    private Context f219a;
    private C0165a f220b;

    static {
        f218c = null;
    }

    private C0166b(Context context) {
        this.f219a = context.getApplicationContext();
        this.f220b = new C0165a(this.f219a);
    }

    public static synchronized C0166b m458a(Context context) {
        C0166b c0166b;
        synchronized (C0166b.class) {
            if (f218c == null) {
                f218c = new C0166b(context);
            }
            c0166b = f218c;
        }
        return c0166b;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int m459a(java.lang.String r6) {
        /*
        r5 = this;
        r2 = 0;
        r0 = 0;
        r1 = r5.f220b;
        r1 = r1.getWritableDatabase();
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x002f }
        r3.<init>();	 Catch:{ Exception -> 0x002f }
        r4 = "select count(*) from ";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x002f }
        r3 = r3.append(r6);	 Catch:{ Exception -> 0x002f }
        r3 = r3.toString();	 Catch:{ Exception -> 0x002f }
        r4 = 0;
        r2 = r1.rawQuery(r3, r4);	 Catch:{ Exception -> 0x002f }
        r1 = r2.moveToNext();	 Catch:{ Exception -> 0x002f }
        if (r1 == 0) goto L_0x002b;
    L_0x0026:
        r1 = 0;
        r0 = r2.getInt(r1);	 Catch:{ Exception -> 0x002f }
    L_0x002b:
        r2.close();
    L_0x002e:
        return r0;
    L_0x002f:
        r1 = move-exception;
        r3 = cn.sharesdk.framework.utils.C0205d.m752a();	 Catch:{ all -> 0x003b }
        r3.m750w(r1);	 Catch:{ all -> 0x003b }
        r2.close();
        goto L_0x002e;
    L_0x003b:
        r0 = move-exception;
        r2.close();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sharesdk.framework.b.a.b.a(java.lang.String):int");
    }

    public int m460a(String str, String str2, String[] strArr) {
        int delete;
        Throwable e;
        try {
            delete = this.f220b.getWritableDatabase().delete(str, str2, strArr);
            try {
                C0205d.m752a().m737d("Deleted %d rows from table: %s", Integer.valueOf(delete), str);
            } catch (Exception e2) {
                e = e2;
                C0205d.m752a().m751w(e, "when delete database occur error table:%s,", str);
                return delete;
            }
        } catch (Throwable e3) {
            e = e3;
            delete = 0;
            C0205d.m752a().m751w(e, "when delete database occur error table:%s,", str);
            return delete;
        }
        return delete;
    }

    public long m461a(String str, ContentValues contentValues) {
        long j = -1;
        try {
            j = this.f220b.getWritableDatabase().replace(str, null, contentValues);
        } catch (Throwable e) {
            C0205d.m752a().m751w(e, "when insert database occur error table:%s,", str);
        }
        return j;
    }

    public Cursor m462a(String str, String[] strArr, String str2, String[] strArr2, String str3) {
        SQLiteDatabase writableDatabase = this.f220b.getWritableDatabase();
        C0205d.m752a().m737d("Query table: %s", str);
        try {
            return writableDatabase.query(str, strArr, str2, strArr2, null, null, str3);
        } catch (Throwable e) {
            C0205d.m752a().m751w(e, "when query database occur error table:%s,", str);
            return null;
        }
    }
}
