package com.tencent.stat;

import android.database.Cursor;
import org.json.JSONObject;

/* renamed from: com.tencent.stat.t */
class C2440t implements Runnable {
    final /* synthetic */ C2434n f12401a;

    C2440t(C2434n c2434n) {
        this.f12401a = c2434n;
    }

    public void run() {
        Cursor query;
        Object th;
        Throwable th2;
        try {
            query = this.f12401a.f12388d.getReadableDatabase().query("config", null, null, null, null, null, null);
            while (query.moveToNext()) {
                try {
                    int i = query.getInt(0);
                    String string = query.getString(1);
                    String string2 = query.getString(2);
                    int i2 = query.getInt(3);
                    C2406b c2406b = new C2406b(i);
                    c2406b.f12299a = i;
                    c2406b.f12300b = new JSONObject(string);
                    c2406b.f12301c = string2;
                    c2406b.f12302d = i2;
                    StatConfig.m13901a(c2406b);
                } catch (Throwable th3) {
                    th = th3;
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Throwable th4) {
            th2 = th4;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th2;
        }
    }
}
