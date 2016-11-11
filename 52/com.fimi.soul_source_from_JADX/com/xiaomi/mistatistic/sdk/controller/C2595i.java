package com.xiaomi.mistatistic.sdk.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.SharedPref;
import com.tencent.open.SocialConstants;
import com.tencent.stat.DeviceInfo;
import com.xiaomi.mistatistic.sdk.data.C2621h;
import org.p122a.p123a.C2915a;

/* renamed from: com.xiaomi.mistatistic.sdk.controller.i */
public class C2595i {
    private static C2602p f12965a;

    public static C2621h m14744a(Cursor cursor) {
        C2621h c2621h = new C2621h();
        long j = cursor.getLong(2);
        String string = cursor.getString(4);
        String string2 = cursor.getString(5);
        String string3 = cursor.getString(1);
        String string4 = cursor.getString(3);
        String string5 = cursor.getString(6);
        C2621h c2621h2 = new C2621h();
        c2621h2.f13002a = string3;
        c2621h2.f13004c = string4;
        c2621h2.f13006e = string;
        c2621h2.f13003b = j;
        c2621h2.f13005d = string2;
        c2621h2.f13007f = string5;
        return c2621h2;
    }

    public static void m14745a() {
        f12965a = new C2602p(C2588a.m14708a());
    }

    public C2621h m14746a(String str, String str2) {
        Cursor cursor;
        Throwable th;
        Cursor cursor2 = null;
        try {
            C2621h a;
            Cursor query = f12965a.getReadableDatabase().query("mistat_event", null, "category=? AND key=?", new String[]{str, str2}, null, null, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        a = C2595i.m14744a(query);
                        if (query != null) {
                            return a;
                        }
                        query.close();
                        return a;
                    }
                } catch (SQLiteException e) {
                    cursor = query;
                    if (cursor != null) {
                        return null;
                    }
                    cursor.close();
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    cursor2 = query;
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    throw th;
                }
            }
            a = null;
            if (query != null) {
                return a;
            }
            query.close();
            return a;
        } catch (SQLiteException e2) {
            cursor = null;
            if (cursor != null) {
                return null;
            }
            cursor.close();
            return null;
        } catch (Throwable th3) {
            th = th3;
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th;
        }
    }

    public void m14747a(long j) {
        try {
            f12965a.getWritableDatabase().delete("mistat_event", "ts<=?", new String[]{String.valueOf(j)});
        } catch (SQLiteException e) {
        }
    }

    public void m14748a(C2621h c2621h) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("category", c2621h.f13002a);
        contentValues.put(SharedPref.KEY, TextUtils.isEmpty(c2621h.f13004c) ? C2915a.f14760f : c2621h.f13004c);
        contentValues.put(DeviceInfo.TAG_TIMESTAMPS, Long.valueOf(c2621h.f13003b));
        contentValues.put(SocialConstants.PARAM_TYPE, TextUtils.isEmpty(c2621h.f13005d) ? C2915a.f14760f : c2621h.f13005d);
        contentValues.put(SharedPref.VALUE, TextUtils.isEmpty(c2621h.f13006e) ? C2915a.f14760f : c2621h.f13006e);
        contentValues.put("extra", TextUtils.isEmpty(c2621h.f13007f) ? C2915a.f14760f : c2621h.f13007f);
        try {
            f12965a.getWritableDatabase().insert("mistat_event", C2915a.f14760f, contentValues);
        } catch (Throwable e) {
            new C2601o().m14770a("Error to insert data into DB, key=" + c2621h.f13004c, e);
        }
    }

    public void m14749a(String str, String str2, String str3) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SharedPref.VALUE, str3);
        try {
            f12965a.getWritableDatabase().update("mistat_event", contentValues, "category=? AND key=?", new String[]{str2, str});
        } catch (Throwable e) {
            new C2601o().m14770a("Error to update data from DB, key=" + str, e);
        }
    }

    public Cursor m14750b() {
        try {
            return f12965a.getReadableDatabase().query("mistat_event", null, null, null, null, null, "ts DESC");
        } catch (Throwable e) {
            new C2601o().m14770a("Error while reading data from DB", e);
            return null;
        }
    }

    public void m14751c() {
        long currentTimeMillis = System.currentTimeMillis() - 259200000;
        try {
            f12965a.getWritableDatabase().delete("mistat_event", "ts<=? and category <> ?", new String[]{String.valueOf(currentTimeMillis), "mistat_basic"});
        } catch (SQLiteException e) {
        }
    }

    public int m14752d() {
        Cursor cursor;
        Throwable th;
        Cursor cursor2 = null;
        try {
            Cursor query = f12965a.getReadableDatabase().query("mistat_event", new String[]{"count(*)"}, null, null, null, null, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        int i = query.getInt(0);
                        if (query == null) {
                            return i;
                        }
                        query.close();
                        return i;
                    }
                } catch (SQLiteException e) {
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    return 0;
                } catch (Throwable th2) {
                    th = th2;
                    cursor2 = query;
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    throw th;
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (SQLiteException e2) {
            cursor = null;
            if (cursor != null) {
                cursor.close();
            }
            return 0;
        } catch (Throwable th3) {
            th = th3;
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th;
        }
        return 0;
    }
}
