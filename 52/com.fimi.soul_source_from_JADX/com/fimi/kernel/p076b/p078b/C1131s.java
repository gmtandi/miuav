package com.fimi.kernel.p076b.p078b;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.kernel.b.b.s */
class C1131s {
    private static C1131s f5185a;
    private SQLiteDatabase f5186b;
    private Context f5187c;

    static {
        f5185a = null;
    }

    private C1131s(Context context) {
        this.f5186b = null;
        this.f5187c = context;
        try {
            this.f5186b = new C1112a(context).getWritableDatabase();
        } catch (Exception e) {
            Log.d("ljh", e.getMessage());
        }
    }

    public static C1131s m7844a(Context context) {
        if (context == null) {
            return null;
        }
        if (f5185a == null) {
            f5185a = new C1131s(context);
            f5185a.f5187c = context;
        }
        return f5185a;
    }

    private SQLiteDatabase m7845d() {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = new C1112a(this.f5187c).getWritableDatabase();
        } catch (Exception e) {
            Log.d("Good", e + C2915a.f14760f);
        }
        return sQLiteDatabase;
    }

    public synchronized C1113b m7846a(Context context, String str, C1123u c1123u) {
        Cursor rawQuery;
        Exception exception;
        Cursor cursor;
        C1113b c1113b;
        Throwable th;
        try {
            rawQuery = f5185a.f5186b.rawQuery("select url,local_uri,file_size from download_task where url=?", new String[]{str});
            C1113b c1113b2 = null;
            while (rawQuery.moveToNext()) {
                try {
                    c1113b2 = C1113b.m7742a(context, rawQuery.getString(0), rawQuery.getLong(2), Boolean.valueOf(false), rawQuery.getString(1), new C1132t(this, c1123u));
                } catch (Exception e) {
                    exception = e;
                    cursor = rawQuery;
                    c1113b = c1113b2;
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
                c1113b = c1113b2;
            } else {
                c1113b = c1113b2;
            }
        } catch (Exception e2) {
            cursor = null;
            Exception exception2 = e2;
            c1113b = null;
            exception = exception2;
            try {
                exception.printStackTrace();
                if (cursor != null) {
                    cursor.close();
                }
                return c1113b;
            } catch (Throwable th3) {
                th = th3;
                rawQuery = cursor;
                if (rawQuery != null) {
                    rawQuery.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            rawQuery = null;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
        return c1113b;
    }

    public List<String> m7847a() {
        Cursor cursor = null;
        List<String> arrayList = new ArrayList();
        try {
            cursor = f5185a.f5186b.rawQuery("select url from download_info group by url", null);
            while (cursor.moveToNext()) {
                arrayList.add(cursor.getString(0));
            }
            if (cursor != null) {
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return arrayList;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return arrayList;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void m7848a(int r5, long r6, java.lang.String r8) {
        /*
        r4 = this;
        monitor-enter(r4);
        r0 = f5185a;	 Catch:{ Exception -> 0x0035 }
        r0 = r0.f5186b;	 Catch:{ Exception -> 0x0035 }
        r0.beginTransaction();	 Catch:{ Exception -> 0x0035 }
        r0 = "update download_info set compelete_size=? where thread_id=? and url=?";
        r1 = 3;
        r1 = new java.lang.Object[r1];	 Catch:{ Exception -> 0x0035 }
        r2 = 0;
        r3 = java.lang.Long.valueOf(r6);	 Catch:{ Exception -> 0x0035 }
        r1[r2] = r3;	 Catch:{ Exception -> 0x0035 }
        r2 = 1;
        r3 = java.lang.Integer.valueOf(r5);	 Catch:{ Exception -> 0x0035 }
        r1[r2] = r3;	 Catch:{ Exception -> 0x0035 }
        r2 = 2;
        r1[r2] = r8;	 Catch:{ Exception -> 0x0035 }
        r2 = f5185a;	 Catch:{ Exception -> 0x0035 }
        r2 = r2.f5186b;	 Catch:{ Exception -> 0x0035 }
        r2.execSQL(r0, r1);	 Catch:{ Exception -> 0x0035 }
        r0 = f5185a;	 Catch:{ Exception -> 0x0035 }
        r0 = r0.f5186b;	 Catch:{ Exception -> 0x0035 }
        r0.setTransactionSuccessful();	 Catch:{ Exception -> 0x0035 }
        r0 = f5185a;	 Catch:{ Exception -> 0x0051 }
        r0 = r0.f5186b;	 Catch:{ Exception -> 0x0051 }
        r0.endTransaction();	 Catch:{ Exception -> 0x0051 }
    L_0x0033:
        monitor-exit(r4);
        return;
    L_0x0035:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0043 }
        r0 = f5185a;	 Catch:{ Exception -> 0x0041 }
        r0 = r0.f5186b;	 Catch:{ Exception -> 0x0041 }
        r0.endTransaction();	 Catch:{ Exception -> 0x0041 }
        goto L_0x0033;
    L_0x0041:
        r0 = move-exception;
        goto L_0x0033;
    L_0x0043:
        r0 = move-exception;
        r1 = f5185a;	 Catch:{ Exception -> 0x004f }
        r1 = r1.f5186b;	 Catch:{ Exception -> 0x004f }
        r1.endTransaction();	 Catch:{ Exception -> 0x004f }
    L_0x004b:
        throw r0;	 Catch:{ all -> 0x004c }
    L_0x004c:
        r0 = move-exception;
        monitor-exit(r4);
        throw r0;
    L_0x004f:
        r1 = move-exception;
        goto L_0x004b;
    L_0x0051:
        r0 = move-exception;
        goto L_0x0033;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fimi.kernel.b.b.s.a(int, long, java.lang.String):void");
    }

    public synchronized void m7849a(C1113b c1113b) {
        try {
            Object[] objArr = new Object[]{c1113b.m7791m(), c1113b.m7786h(), Long.valueOf(c1113b.m7787i()), c1113b.m7773a()};
            f5185a.f5186b.execSQL("insert into download_task(url,local_uri,file_size,dur) values (?,?,?,?)", objArr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void m7850a(List<C1128p> list) {
        try {
            for (C1128p c1128p : list) {
                Object[] objArr = new Object[]{Integer.valueOf(c1128p.m7823b()), Long.valueOf(c1128p.m7825c()), Long.valueOf(c1128p.m7827d()), Long.valueOf(c1128p.m7828e()), c1128p.m7819a()};
                f5185a.f5186b.execSQL("insert into download_info(thread_id,start_pos, end_pos,compelete_size,url) values (?,?,?,?,?)", objArr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized boolean m7851a(String str) {
        boolean z = true;
        synchronized (this) {
            int i;
            Cursor cursor = null;
            try {
                cursor = f5185a.f5186b.rawQuery("select count(*)  from download_info where url=?", new String[]{str});
                i = cursor.moveToFirst() ? cursor.getInt(0) : -1;
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (cursor != null) {
                    cursor.close();
                    i = -1;
                } else {
                    i = -1;
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
            }
            if (i <= 0) {
                z = false;
            }
        }
        return z;
    }

    public synchronized C1113b m7852b(String str) {
        C1113b c1113b;
        Exception exception;
        Exception exception2;
        Throwable th;
        Cursor rawQuery;
        try {
            rawQuery = f5185a.f5186b.rawQuery("select url,local_uri,dur from download_task where url=?", new String[]{str});
            c1113b = null;
            while (rawQuery.moveToNext()) {
                try {
                    C1113b c1113b2 = new C1113b(rawQuery.getString(0), rawQuery.getString(1));
                    try {
                        if (rawQuery.getString(2) != null) {
                            c1113b2.m7778a(rawQuery.getString(2));
                            c1113b = c1113b2;
                        } else {
                            c1113b = c1113b2;
                        }
                    } catch (Exception e) {
                        exception = e;
                        c1113b = c1113b2;
                        exception2 = exception;
                    }
                } catch (Exception e2) {
                    exception2 = e2;
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
        } catch (Exception e3) {
            rawQuery = null;
            exception = e3;
            c1113b = null;
            exception2 = exception;
            try {
                exception2.printStackTrace();
                if (rawQuery != null) {
                    rawQuery.close();
                }
                return c1113b;
            } catch (Throwable th2) {
                th = th2;
                if (rawQuery != null) {
                    rawQuery.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            rawQuery = null;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
        return c1113b;
    }

    public synchronized List<C1113b> m7853b() {
        List<C1113b> arrayList;
        Cursor cursor = null;
        synchronized (this) {
            arrayList = new ArrayList();
            try {
                cursor = f5185a.f5186b.rawQuery("select url,local_uri from download_task", null);
                cursor.moveToFirst();
                while (cursor.moveToNext()) {
                    arrayList.add(new C1113b(cursor.getString(0), cursor.getString(1)));
                }
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        return arrayList;
    }

    public synchronized List<C1128p> m7854c(String str) {
        List<C1128p> arrayList;
        Cursor rawQuery;
        Exception e;
        Throwable th;
        arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            rawQuery = f5185a.f5186b.rawQuery("select thread_id, start_pos, end_pos,compelete_size,url from download_info where url=?", new String[]{str});
            while (rawQuery.moveToNext()) {
                try {
                    arrayList.add(new C1128p(rawQuery.getInt(0), rawQuery.getLong(1), rawQuery.getLong(2), rawQuery.getLong(3), rawQuery.getString(4)));
                } catch (Exception e2) {
                    e = e2;
                    cursor = rawQuery;
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
        } catch (Exception e3) {
            e = e3;
            try {
                e.printStackTrace();
                if (cursor != null) {
                    cursor.close();
                }
                return arrayList;
            } catch (Throwable th3) {
                th = th3;
                rawQuery = cursor;
                if (rawQuery != null) {
                    rawQuery.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            rawQuery = null;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
        return arrayList;
    }

    public synchronized void m7855c() {
        try {
            f5185a.f5186b.execSQL("DELETE FROM download_info");
            f5185a.f5186b.execSQL("DELETE FROM download_task");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void m7856d(String str) {
        try {
            f5185a.f5186b.delete("download_info", "url=?", new String[]{str});
            f5185a.f5186b.delete("download_task", "url=?", new String[]{str});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void finalize() {
        this.f5186b.close();
        super.finalize();
    }
}
