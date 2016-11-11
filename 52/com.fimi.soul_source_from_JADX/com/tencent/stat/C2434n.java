package com.tencent.stat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Handler;
import android.os.HandlerThread;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.message.RMsgInfo;
import com.tencent.open.SocialConstants;
import com.tencent.stat.common.C2418k;
import com.tencent.stat.common.StatLogger;
import com.tencent.stat.p136a.C2394e;
import com.xiaomi.market.sdk.C2537j;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.p122a.p123a.C2915a;

/* renamed from: com.tencent.stat.n */
public class C2434n {
    private static StatLogger f12383e;
    private static C2434n f12384f;
    Handler f12385a;
    volatile int f12386b;
    DeviceInfo f12387c;
    private C2443w f12388d;
    private HashMap<String, String> f12389g;

    static {
        f12383e = C2418k.m14018b();
        f12384f = null;
    }

    private C2434n(Context context) {
        this.f12385a = null;
        this.f12386b = 0;
        this.f12387c = null;
        this.f12389g = new HashMap();
        try {
            HandlerThread handlerThread = new HandlerThread("StatStore");
            handlerThread.start();
            f12383e.m13981w("Launch store thread:" + handlerThread);
            this.f12385a = new Handler(handlerThread.getLooper());
            Context applicationContext = context.getApplicationContext();
            this.f12388d = new C2443w(applicationContext);
            this.f12388d.getWritableDatabase();
            this.f12388d.getReadableDatabase();
            m14106b(applicationContext);
            m14107c();
            m14099f();
            this.f12385a.post(new C2435o(this));
        } catch (Object th) {
            f12383e.m13978e(th);
        }
    }

    public static synchronized C2434n m14082a(Context context) {
        C2434n c2434n;
        synchronized (C2434n.class) {
            if (f12384f == null) {
                f12384f = new C2434n(context);
            }
            c2434n = f12384f;
        }
        return c2434n;
    }

    public static C2434n m14089b() {
        return f12384f;
    }

    private synchronized void m14091b(int i) {
        try {
            if (this.f12386b > 0 && i > 0) {
                f12383e.m13979i("Load " + Integer.toString(this.f12386b) + " unsent events");
                List arrayList = new ArrayList();
                List<C2444x> arrayList2 = new ArrayList();
                if (i == -1 || i > StatConfig.m13896a()) {
                    i = StatConfig.m13896a();
                }
                this.f12386b -= i;
                m14096c(arrayList2, i);
                f12383e.m13979i("Peek " + Integer.toString(arrayList2.size()) + " unsent events.");
                if (!arrayList2.isEmpty()) {
                    m14095b((List) arrayList2, 2);
                    for (C2444x c2444x : arrayList2) {
                        arrayList.add(c2444x.f12408b);
                    }
                    C2424d.m14068b().m14072b(arrayList, new C2441u(this, arrayList2, i));
                }
            }
        } catch (Object th) {
            f12383e.m13978e(th);
        }
    }

    private synchronized void m14092b(C2394e c2394e, C2407c c2407c) {
        if (StatConfig.getMaxStoreEventCount() > 0) {
            try {
                this.f12388d.getWritableDatabase().beginTransaction();
                if (this.f12386b > StatConfig.getMaxStoreEventCount()) {
                    f12383e.warn("Too many events stored in db.");
                    this.f12386b -= this.f12388d.getWritableDatabase().delete("events", "event_id in (select event_id from events where timestamp in (select min(timestamp) from events) limit 1)", null);
                }
                ContentValues contentValues = new ContentValues();
                String c = C2418k.m14023c(c2394e.m13939d());
                contentValues.put(RMsgInfo.COL_CONTENT, c);
                contentValues.put("send_count", Constants.VIA_RESULT_SUCCESS);
                contentValues.put(RMsgInfo.COL_STATUS, Integer.toString(1));
                contentValues.put("timestamp", Long.valueOf(c2394e.m13936b()));
                if (this.f12388d.getWritableDatabase().insert("events", null, contentValues) == -1) {
                    f12383e.error("Failed to store event:" + c);
                } else {
                    this.f12386b++;
                    this.f12388d.getWritableDatabase().setTransactionSuccessful();
                    if (c2407c != null) {
                        c2407c.m13973a();
                    }
                }
                try {
                    this.f12388d.getWritableDatabase().endTransaction();
                } catch (Throwable th) {
                }
            } catch (Throwable th2) {
            }
        }
    }

    private synchronized void m14093b(C2406b c2406b) {
        Cursor query;
        Object obj;
        Throwable th;
        try {
            long update;
            String a = c2406b.m13972a();
            String a2 = C2418k.m14012a(a);
            ContentValues contentValues = new ContentValues();
            contentValues.put(RMsgInfo.COL_CONTENT, c2406b.f12300b.toString());
            contentValues.put("md5sum", a2);
            c2406b.f12301c = a2;
            contentValues.put(C2537j.aq, Integer.valueOf(c2406b.f12302d));
            query = this.f12388d.getReadableDatabase().query("config", null, null, null, null, null, null);
            do {
                try {
                    if (!query.moveToNext()) {
                        obj = null;
                        break;
                    }
                } catch (Throwable th2) {
                    obj = th2;
                }
            } while (query.getInt(0) != c2406b.f12299a);
            obj = 1;
            if (1 == obj) {
                update = (long) this.f12388d.getWritableDatabase().update("config", contentValues, "type=?", new String[]{Integer.toString(c2406b.f12299a)});
            } else {
                contentValues.put(SocialConstants.PARAM_TYPE, Integer.valueOf(c2406b.f12299a));
                update = this.f12388d.getWritableDatabase().insert("config", null, contentValues);
            }
            if (update == -1) {
                f12383e.m13978e("Failed to store cfg:" + a);
            } else {
                f12383e.m13976d("Sucessed to store cfg:" + a);
            }
            if (query != null) {
                query.close();
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void m14094b(java.util.List<com.tencent.stat.C2444x> r11) {
        /*
        r10 = this;
        monitor-enter(r10);
        r0 = f12383e;	 Catch:{ all -> 0x009f }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x009f }
        r1.<init>();	 Catch:{ all -> 0x009f }
        r2 = "Delete ";
        r1 = r1.append(r2);	 Catch:{ all -> 0x009f }
        r2 = r11.size();	 Catch:{ all -> 0x009f }
        r1 = r1.append(r2);	 Catch:{ all -> 0x009f }
        r2 = " sent events in thread:";
        r1 = r1.append(r2);	 Catch:{ all -> 0x009f }
        r2 = java.lang.Thread.currentThread();	 Catch:{ all -> 0x009f }
        r1 = r1.append(r2);	 Catch:{ all -> 0x009f }
        r1 = r1.toString();	 Catch:{ all -> 0x009f }
        r0.m13979i(r1);	 Catch:{ all -> 0x009f }
        r0 = r10.f12388d;	 Catch:{ Throwable -> 0x0065 }
        r0 = r0.getWritableDatabase();	 Catch:{ Throwable -> 0x0065 }
        r0.beginTransaction();	 Catch:{ Throwable -> 0x0065 }
        r1 = r11.iterator();	 Catch:{ Throwable -> 0x0065 }
    L_0x0038:
        r0 = r1.hasNext();	 Catch:{ Throwable -> 0x0065 }
        if (r0 == 0) goto L_0x0076;
    L_0x003e:
        r0 = r1.next();	 Catch:{ Throwable -> 0x0065 }
        r0 = (com.tencent.stat.C2444x) r0;	 Catch:{ Throwable -> 0x0065 }
        r2 = r10.f12386b;	 Catch:{ Throwable -> 0x0065 }
        r3 = r10.f12388d;	 Catch:{ Throwable -> 0x0065 }
        r3 = r3.getWritableDatabase();	 Catch:{ Throwable -> 0x0065 }
        r4 = "events";
        r5 = "event_id = ?";
        r6 = 1;
        r6 = new java.lang.String[r6];	 Catch:{ Throwable -> 0x0065 }
        r7 = 0;
        r8 = r0.f12407a;	 Catch:{ Throwable -> 0x0065 }
        r0 = java.lang.Long.toString(r8);	 Catch:{ Throwable -> 0x0065 }
        r6[r7] = r0;	 Catch:{ Throwable -> 0x0065 }
        r0 = r3.delete(r4, r5, r6);	 Catch:{ Throwable -> 0x0065 }
        r0 = r2 - r0;
        r10.f12386b = r0;	 Catch:{ Throwable -> 0x0065 }
        goto L_0x0038;
    L_0x0065:
        r0 = move-exception;
        r1 = f12383e;	 Catch:{ all -> 0x00a9 }
        r1.m13978e(r0);	 Catch:{ all -> 0x00a9 }
        r0 = r10.f12388d;	 Catch:{ SQLiteException -> 0x00a2 }
        r0 = r0.getWritableDatabase();	 Catch:{ SQLiteException -> 0x00a2 }
        r0.endTransaction();	 Catch:{ SQLiteException -> 0x00a2 }
    L_0x0074:
        monitor-exit(r10);
        return;
    L_0x0076:
        r0 = r10.f12388d;	 Catch:{ Throwable -> 0x0065 }
        r0 = r0.getWritableDatabase();	 Catch:{ Throwable -> 0x0065 }
        r0.setTransactionSuccessful();	 Catch:{ Throwable -> 0x0065 }
        r0 = r10.f12388d;	 Catch:{ Throwable -> 0x0065 }
        r0 = r0.getReadableDatabase();	 Catch:{ Throwable -> 0x0065 }
        r1 = "events";
        r0 = android.database.DatabaseUtils.queryNumEntries(r0, r1);	 Catch:{ Throwable -> 0x0065 }
        r0 = (int) r0;	 Catch:{ Throwable -> 0x0065 }
        r10.f12386b = r0;	 Catch:{ Throwable -> 0x0065 }
        r0 = r10.f12388d;	 Catch:{ SQLiteException -> 0x0098 }
        r0 = r0.getWritableDatabase();	 Catch:{ SQLiteException -> 0x0098 }
        r0.endTransaction();	 Catch:{ SQLiteException -> 0x0098 }
        goto L_0x0074;
    L_0x0098:
        r0 = move-exception;
        r1 = f12383e;	 Catch:{ all -> 0x009f }
        r1.m13977e(r0);	 Catch:{ all -> 0x009f }
        goto L_0x0074;
    L_0x009f:
        r0 = move-exception;
        monitor-exit(r10);
        throw r0;
    L_0x00a2:
        r0 = move-exception;
        r1 = f12383e;	 Catch:{ all -> 0x009f }
        r1.m13977e(r0);	 Catch:{ all -> 0x009f }
        goto L_0x0074;
    L_0x00a9:
        r0 = move-exception;
        r1 = r10.f12388d;	 Catch:{ SQLiteException -> 0x00b4 }
        r1 = r1.getWritableDatabase();	 Catch:{ SQLiteException -> 0x00b4 }
        r1.endTransaction();	 Catch:{ SQLiteException -> 0x00b4 }
    L_0x00b3:
        throw r0;	 Catch:{ all -> 0x009f }
    L_0x00b4:
        r1 = move-exception;
        r2 = f12383e;	 Catch:{ all -> 0x009f }
        r2.m13977e(r1);	 Catch:{ all -> 0x009f }
        goto L_0x00b3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.stat.n.b(java.util.List):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void m14095b(java.util.List<com.tencent.stat.C2444x> r13, int r14) {
        /*
        r12 = this;
        monitor-enter(r12);
        r0 = f12383e;	 Catch:{ all -> 0x010e }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x010e }
        r1.<init>();	 Catch:{ all -> 0x010e }
        r2 = "Update ";
        r1 = r1.append(r2);	 Catch:{ all -> 0x010e }
        r2 = r13.size();	 Catch:{ all -> 0x010e }
        r1 = r1.append(r2);	 Catch:{ all -> 0x010e }
        r2 = " sending events to status:";
        r1 = r1.append(r2);	 Catch:{ all -> 0x010e }
        r1 = r1.append(r14);	 Catch:{ all -> 0x010e }
        r2 = " in thread:";
        r1 = r1.append(r2);	 Catch:{ all -> 0x010e }
        r2 = java.lang.Thread.currentThread();	 Catch:{ all -> 0x010e }
        r1 = r1.append(r2);	 Catch:{ all -> 0x010e }
        r1 = r1.toString();	 Catch:{ all -> 0x010e }
        r0.m13979i(r1);	 Catch:{ all -> 0x010e }
        r1 = new android.content.ContentValues;	 Catch:{ Throwable -> 0x0087 }
        r1.<init>();	 Catch:{ Throwable -> 0x0087 }
        r0 = "status";
        r2 = java.lang.Integer.toString(r14);	 Catch:{ Throwable -> 0x0087 }
        r1.put(r0, r2);	 Catch:{ Throwable -> 0x0087 }
        r0 = r12.f12388d;	 Catch:{ Throwable -> 0x0087 }
        r0 = r0.getWritableDatabase();	 Catch:{ Throwable -> 0x0087 }
        r0.beginTransaction();	 Catch:{ Throwable -> 0x0087 }
        r2 = r13.iterator();	 Catch:{ Throwable -> 0x0087 }
    L_0x0050:
        r0 = r2.hasNext();	 Catch:{ Throwable -> 0x0087 }
        if (r0 == 0) goto L_0x0111;
    L_0x0056:
        r0 = r2.next();	 Catch:{ Throwable -> 0x0087 }
        r0 = (com.tencent.stat.C2444x) r0;	 Catch:{ Throwable -> 0x0087 }
        r3 = r0.f12410d;	 Catch:{ Throwable -> 0x0087 }
        r3 = r3 + 1;
        r4 = com.tencent.stat.StatConfig.getMaxSendRetryCount();	 Catch:{ Throwable -> 0x0087 }
        if (r3 <= r4) goto L_0x0098;
    L_0x0066:
        r3 = r12.f12386b;	 Catch:{ Throwable -> 0x0087 }
        r4 = r12.f12388d;	 Catch:{ Throwable -> 0x0087 }
        r4 = r4.getWritableDatabase();	 Catch:{ Throwable -> 0x0087 }
        r5 = "events";
        r6 = "event_id=?";
        r7 = 1;
        r7 = new java.lang.String[r7];	 Catch:{ Throwable -> 0x0087 }
        r8 = 0;
        r10 = r0.f12407a;	 Catch:{ Throwable -> 0x0087 }
        r0 = java.lang.Long.toString(r10);	 Catch:{ Throwable -> 0x0087 }
        r7[r8] = r0;	 Catch:{ Throwable -> 0x0087 }
        r0 = r4.delete(r5, r6, r7);	 Catch:{ Throwable -> 0x0087 }
        r0 = r3 - r0;
        r12.f12386b = r0;	 Catch:{ Throwable -> 0x0087 }
        goto L_0x0050;
    L_0x0087:
        r0 = move-exception;
        r1 = f12383e;	 Catch:{ all -> 0x0103 }
        r1.m13978e(r0);	 Catch:{ all -> 0x0103 }
        r0 = r12.f12388d;	 Catch:{ SQLiteException -> 0x013c }
        r0 = r0.getWritableDatabase();	 Catch:{ SQLiteException -> 0x013c }
        r0.endTransaction();	 Catch:{ SQLiteException -> 0x013c }
    L_0x0096:
        monitor-exit(r12);
        return;
    L_0x0098:
        r3 = "send_count";
        r4 = r0.f12410d;	 Catch:{ Throwable -> 0x0087 }
        r4 = r4 + 1;
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ Throwable -> 0x0087 }
        r1.put(r3, r4);	 Catch:{ Throwable -> 0x0087 }
        r3 = f12383e;	 Catch:{ Throwable -> 0x0087 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0087 }
        r4.<init>();	 Catch:{ Throwable -> 0x0087 }
        r5 = "Update event:";
        r4 = r4.append(r5);	 Catch:{ Throwable -> 0x0087 }
        r6 = r0.f12407a;	 Catch:{ Throwable -> 0x0087 }
        r4 = r4.append(r6);	 Catch:{ Throwable -> 0x0087 }
        r5 = " for content:";
        r4 = r4.append(r5);	 Catch:{ Throwable -> 0x0087 }
        r4 = r4.append(r1);	 Catch:{ Throwable -> 0x0087 }
        r4 = r4.toString();	 Catch:{ Throwable -> 0x0087 }
        r3.m13979i(r4);	 Catch:{ Throwable -> 0x0087 }
        r3 = r12.f12388d;	 Catch:{ Throwable -> 0x0087 }
        r3 = r3.getWritableDatabase();	 Catch:{ Throwable -> 0x0087 }
        r4 = "events";
        r5 = "event_id=?";
        r6 = 1;
        r6 = new java.lang.String[r6];	 Catch:{ Throwable -> 0x0087 }
        r7 = 0;
        r8 = r0.f12407a;	 Catch:{ Throwable -> 0x0087 }
        r0 = java.lang.Long.toString(r8);	 Catch:{ Throwable -> 0x0087 }
        r6[r7] = r0;	 Catch:{ Throwable -> 0x0087 }
        r0 = r3.update(r4, r1, r5, r6);	 Catch:{ Throwable -> 0x0087 }
        if (r0 > 0) goto L_0x0050;
    L_0x00e5:
        r3 = f12383e;	 Catch:{ Throwable -> 0x0087 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0087 }
        r4.<init>();	 Catch:{ Throwable -> 0x0087 }
        r5 = "Failed to update db, error code:";
        r4 = r4.append(r5);	 Catch:{ Throwable -> 0x0087 }
        r0 = java.lang.Integer.toString(r0);	 Catch:{ Throwable -> 0x0087 }
        r0 = r4.append(r0);	 Catch:{ Throwable -> 0x0087 }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x0087 }
        r3.m13978e(r0);	 Catch:{ Throwable -> 0x0087 }
        goto L_0x0050;
    L_0x0103:
        r0 = move-exception;
        r1 = r12.f12388d;	 Catch:{ SQLiteException -> 0x0144 }
        r1 = r1.getWritableDatabase();	 Catch:{ SQLiteException -> 0x0144 }
        r1.endTransaction();	 Catch:{ SQLiteException -> 0x0144 }
    L_0x010d:
        throw r0;	 Catch:{ all -> 0x010e }
    L_0x010e:
        r0 = move-exception;
        monitor-exit(r12);
        throw r0;
    L_0x0111:
        r0 = r12.f12388d;	 Catch:{ Throwable -> 0x0087 }
        r0 = r0.getWritableDatabase();	 Catch:{ Throwable -> 0x0087 }
        r0.setTransactionSuccessful();	 Catch:{ Throwable -> 0x0087 }
        r0 = r12.f12388d;	 Catch:{ Throwable -> 0x0087 }
        r0 = r0.getReadableDatabase();	 Catch:{ Throwable -> 0x0087 }
        r1 = "events";
        r0 = android.database.DatabaseUtils.queryNumEntries(r0, r1);	 Catch:{ Throwable -> 0x0087 }
        r0 = (int) r0;	 Catch:{ Throwable -> 0x0087 }
        r12.f12386b = r0;	 Catch:{ Throwable -> 0x0087 }
        r0 = r12.f12388d;	 Catch:{ SQLiteException -> 0x0134 }
        r0 = r0.getWritableDatabase();	 Catch:{ SQLiteException -> 0x0134 }
        r0.endTransaction();	 Catch:{ SQLiteException -> 0x0134 }
        goto L_0x0096;
    L_0x0134:
        r0 = move-exception;
        r1 = f12383e;	 Catch:{ all -> 0x010e }
        r1.m13977e(r0);	 Catch:{ all -> 0x010e }
        goto L_0x0096;
    L_0x013c:
        r0 = move-exception;
        r1 = f12383e;	 Catch:{ all -> 0x010e }
        r1.m13977e(r0);	 Catch:{ all -> 0x010e }
        goto L_0x0096;
    L_0x0144:
        r1 = move-exception;
        r2 = f12383e;	 Catch:{ all -> 0x010e }
        r2.m13977e(r1);	 Catch:{ all -> 0x010e }
        goto L_0x010d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.stat.n.b(java.util.List, int):void");
    }

    private void m14096c(List<C2444x> list, int i) {
        Object th;
        Cursor cursor;
        Throwable th2;
        Cursor cursor2 = null;
        try {
            Cursor query = this.f12388d.getReadableDatabase().query("events", null, "status=?", new String[]{Integer.toString(1)}, null, null, "event_id", Integer.toString(i));
            while (query.moveToNext()) {
                try {
                    list.add(new C2444x(query.getLong(0), C2418k.m14026d(query.getString(1)), query.getInt(2), query.getInt(3)));
                } catch (Throwable th3) {
                    th2 = th3;
                    cursor2 = query;
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Throwable th4) {
            th2 = th4;
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th2;
        }
    }

    private void m14098e() {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(RMsgInfo.COL_STATUS, Integer.valueOf(1));
            this.f12388d.getWritableDatabase().update("events", contentValues, "status=?", new String[]{Long.toString(2)});
            this.f12386b = (int) DatabaseUtils.queryNumEntries(this.f12388d.getReadableDatabase(), "events");
            f12383e.m13979i("Total " + this.f12386b + " unsent events.");
        } catch (Object th) {
            f12383e.m13978e(th);
        }
    }

    private void m14099f() {
        Cursor query;
        Object th;
        Throwable th2;
        try {
            query = this.f12388d.getReadableDatabase().query("keyvalues", null, null, null, null, null, null);
            while (query.moveToNext()) {
                try {
                    this.f12389g.put(query.getString(0), query.getString(1));
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

    public int m14100a() {
        return this.f12386b;
    }

    void m14101a(int i) {
        this.f12385a.post(new C2442v(this, i));
    }

    void m14102a(C2394e c2394e, C2407c c2407c) {
        if (StatConfig.isEnableStatService()) {
            try {
                if (Thread.currentThread().getId() == this.f12385a.getLooper().getThread().getId()) {
                    m14092b(c2394e, c2407c);
                } else {
                    this.f12385a.post(new C2438r(this, c2394e, c2407c));
                }
            } catch (Object th) {
                f12383e.m13978e(th);
            }
        }
    }

    void m14103a(C2406b c2406b) {
        if (c2406b != null) {
            this.f12385a.post(new C2439s(this, c2406b));
        }
    }

    void m14104a(List<C2444x> list) {
        try {
            if (Thread.currentThread().getId() == this.f12385a.getLooper().getThread().getId()) {
                m14094b((List) list);
            } else {
                this.f12385a.post(new C2437q(this, list));
            }
        } catch (Exception e) {
            f12383e.m13977e(e);
        }
    }

    void m14105a(List<C2444x> list, int i) {
        try {
            if (Thread.currentThread().getId() == this.f12385a.getLooper().getThread().getId()) {
                m14095b((List) list, i);
            } else {
                this.f12385a.post(new C2436p(this, list, i));
            }
        } catch (Object th) {
            f12383e.m13978e(th);
        }
    }

    public synchronized DeviceInfo m14106b(Context context) {
        DeviceInfo deviceInfo;
        Cursor query;
        Object obj;
        Cursor cursor;
        Throwable th;
        if (this.f12387c != null) {
            deviceInfo = this.f12387c;
        } else {
            try {
                query = this.f12388d.getReadableDatabase().query("user", null, null, null, null, null, null, null);
                obj = null;
                try {
                    String d;
                    String str;
                    String l;
                    String str2 = C2915a.f14760f;
                    if (query.moveToNext()) {
                        Object obj2;
                        d = C2418k.m14026d(query.getString(0));
                        int i = query.getInt(1);
                        str2 = query.getString(2);
                        long currentTimeMillis = System.currentTimeMillis() / 1000;
                        int i2 = (i == 1 || C2418k.m14011a(query.getLong(3) * 1000).equals(C2418k.m14011a(1000 * currentTimeMillis))) ? i : 1;
                        int i3 = !str2.equals(C2418k.m14045r(context)) ? i2 | 2 : i2;
                        String[] split = d.split(MiPushClient.ACCEPT_TIME_SEPARATOR);
                        if (split == null || split.length <= 0) {
                            str2 = C2418k.m14019b(context);
                            d = str2;
                            str = str2;
                            int i4 = 1;
                        } else {
                            str2 = split[0];
                            if (str2 == null || str2.length() < 11) {
                                l = C2418k.m14039l(context);
                                if (l == null || l.length() <= 10) {
                                    l = str2;
                                    obj2 = null;
                                } else {
                                    obj2 = 1;
                                }
                                str = d;
                                d = l;
                            } else {
                                String str3 = str2;
                                obj2 = null;
                                str = d;
                                d = str3;
                            }
                        }
                        if (split == null || split.length < 2) {
                            l = C2418k.m14022c(context);
                            if (l != null && l.length() > 0) {
                                str = d + MiPushClient.ACCEPT_TIME_SEPARATOR + l;
                                obj2 = 1;
                            }
                        } else {
                            l = split[1];
                            str = d + MiPushClient.ACCEPT_TIME_SEPARATOR + l;
                        }
                        this.f12387c = new DeviceInfo(d, l, i3);
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("uid", C2418k.m14023c(str));
                        contentValues.put("user_type", Integer.valueOf(i3));
                        contentValues.put("app_ver", C2418k.m14045r(context));
                        contentValues.put(DeviceInfo.TAG_TIMESTAMPS, Long.valueOf(currentTimeMillis));
                        if (obj2 != null) {
                            this.f12388d.getWritableDatabase().update("user", contentValues, "uid=?", new String[]{r10});
                        }
                        if (i3 != i) {
                            this.f12388d.getWritableDatabase().replace("user", null, contentValues);
                            obj = 1;
                        } else {
                            i2 = 1;
                        }
                    }
                    if (obj == null) {
                        str2 = C2418k.m14019b(context);
                        str = C2418k.m14022c(context);
                        l = (str == null || str.length() <= 0) ? str2 : str2 + MiPushClient.ACCEPT_TIME_SEPARATOR + str;
                        long currentTimeMillis2 = System.currentTimeMillis() / 1000;
                        d = C2418k.m14045r(context);
                        ContentValues contentValues2 = new ContentValues();
                        contentValues2.put("uid", C2418k.m14023c(l));
                        contentValues2.put("user_type", Integer.valueOf(0));
                        contentValues2.put("app_ver", d);
                        contentValues2.put(DeviceInfo.TAG_TIMESTAMPS, Long.valueOf(currentTimeMillis2));
                        this.f12388d.getWritableDatabase().insert("user", null, contentValues2);
                        this.f12387c = new DeviceInfo(str2, str, 0);
                    }
                    if (query != null) {
                        query.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                query = null;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
            deviceInfo = this.f12387c;
        }
        return deviceInfo;
    }

    void m14107c() {
        this.f12385a.post(new C2440t(this));
    }
}
