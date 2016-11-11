package com.p016a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.autonavi.aps.amapapi.model.AmapLoc;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import com.xiaomi.mistatistic.sdk.MiStatInterface;
import it.p074a.p075a.C2799f;
import org.codehaus.jackson.smile.SmileConstants;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;

/* renamed from: com.a.cx */
public class cx {
    private static cx f809a;
    private String f810b;
    private String f811c;

    static {
        f809a = null;
    }

    public cx() {
        this.f810b = "2.0.201501131131".replace(".", C2915a.f14760f);
        this.f811c = null;
    }

    public static synchronized cx m1410a() {
        cx cxVar;
        synchronized (cx.class) {
            if (f809a == null) {
                f809a = new cx();
            }
            cxVar = f809a;
        }
        return cxVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean m1411a(android.database.sqlite.SQLiteDatabase r7, java.lang.String r8) {
        /*
        r6 = this;
        r2 = 0;
        r1 = 1;
        r0 = 0;
        r3 = android.text.TextUtils.isEmpty(r8);
        if (r3 == 0) goto L_0x000a;
    L_0x0009:
        return r0;
    L_0x000a:
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x004e, all -> 0x0057 }
        r3.<init>();	 Catch:{ Exception -> 0x004e, all -> 0x0057 }
        r4 = "SELECT count(*) as c FROM sqlite_master WHERE type = 'table' AND name = '";
        r3.append(r4);	 Catch:{ Exception -> 0x004e, all -> 0x0057 }
        r4 = r8.trim();	 Catch:{ Exception -> 0x004e, all -> 0x0057 }
        r4 = r3.append(r4);	 Catch:{ Exception -> 0x004e, all -> 0x0057 }
        r5 = r6.f810b;	 Catch:{ Exception -> 0x004e, all -> 0x0057 }
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x004e, all -> 0x0057 }
        r5 = "' ";
        r4.append(r5);	 Catch:{ Exception -> 0x004e, all -> 0x0057 }
        r4 = r3.toString();	 Catch:{ Exception -> 0x004e, all -> 0x0057 }
        r5 = 0;
        r2 = r7.rawQuery(r4, r5);	 Catch:{ Exception -> 0x004e, all -> 0x0057 }
        if (r2 == 0) goto L_0x0040;
    L_0x0032:
        r4 = r2.moveToFirst();	 Catch:{ Exception -> 0x005e, all -> 0x0057 }
        if (r4 == 0) goto L_0x0040;
    L_0x0038:
        r4 = 0;
        r4 = r2.getInt(r4);	 Catch:{ Exception -> 0x005e, all -> 0x0057 }
        if (r4 <= 0) goto L_0x0040;
    L_0x003f:
        r0 = r1;
    L_0x0040:
        r4 = 0;
        r5 = r3.length();	 Catch:{ Exception -> 0x005e, all -> 0x0057 }
        r3.delete(r4, r5);	 Catch:{ Exception -> 0x005e, all -> 0x0057 }
        if (r2 == 0) goto L_0x0009;
    L_0x004a:
        r2.close();
        goto L_0x0009;
    L_0x004e:
        r0 = move-exception;
        r0 = r2;
    L_0x0050:
        if (r0 == 0) goto L_0x0055;
    L_0x0052:
        r0.close();
    L_0x0055:
        r0 = r1;
        goto L_0x0009;
    L_0x0057:
        r0 = move-exception;
        if (r2 == 0) goto L_0x005d;
    L_0x005a:
        r2.close();
    L_0x005d:
        throw r0;
    L_0x005e:
        r0 = move-exception;
        r0 = r2;
        goto L_0x0050;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.cx.a(android.database.sqlite.SQLiteDatabase, java.lang.String):boolean");
    }

    protected synchronized void m1412a(Context context) {
        if (context != null) {
            SQLiteDatabase openOrCreateDatabase = context.openOrCreateDatabase("hmdb", 0, null);
            if (m1411a(openOrCreateDatabase, "hist")) {
                Cursor rawQuery;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("SELECT feature, nb, loc FROM ");
                stringBuilder.append("hist").append(this.f810b);
                stringBuilder.append(" WHERE time > ").append(dn.m1502a() - MiStatInterface.MAX_UPLOAD_INTERVAL);
                stringBuilder.append(" ORDER BY time ASC").append(";");
                try {
                    rawQuery = openOrCreateDatabase.rawQuery(stringBuilder.toString(), null);
                } catch (Throwable th) {
                    ev.m1777a(th, "DB", "fetchHist");
                    Object message = th.getMessage();
                    rawQuery = (TextUtils.isEmpty(message) || message.contains("no such table")) ? null : null;
                }
                StringBuilder stringBuilder2 = new StringBuilder();
                if (this.f811c == null) {
                    this.f811c = cs.m1375a("MD5", context.getPackageName());
                }
                if (rawQuery != null && rawQuery.moveToFirst()) {
                    int i = 0;
                    while (true) {
                        JSONObject jSONObject;
                        JSONObject jSONObject2;
                        if (rawQuery.getString(0).startsWith("{")) {
                            jSONObject = new JSONObject(rawQuery.getString(0));
                            stringBuilder2.delete(0, stringBuilder2.length());
                            if (!TextUtils.isEmpty(rawQuery.getString(1))) {
                                stringBuilder2.append(rawQuery.getString(1));
                            } else if (dn.m1514a(jSONObject, "mmac")) {
                                stringBuilder2.append("#").append(jSONObject.getString("mmac"));
                                stringBuilder2.append(",access");
                            }
                            jSONObject2 = new JSONObject(rawQuery.getString(2));
                            if (dn.m1514a(jSONObject2, SocialConstants.PARAM_TYPE)) {
                                jSONObject2.put(SocialConstants.PARAM_TYPE, "new");
                            }
                        } else {
                            jSONObject = new JSONObject(new String(cs.m1384d(fy.m1905b(rawQuery.getString(0)), this.f811c), C1142e.f5201a));
                            stringBuilder2.delete(0, stringBuilder2.length());
                            if (!TextUtils.isEmpty(rawQuery.getString(1))) {
                                stringBuilder2.append(new String(cs.m1384d(fy.m1905b(rawQuery.getString(1)), this.f811c), C1142e.f5201a));
                            } else if (dn.m1514a(jSONObject, "mmac")) {
                                stringBuilder2.append("#").append(jSONObject.getString("mmac"));
                                stringBuilder2.append(",access");
                            }
                            jSONObject2 = new JSONObject(new String(cs.m1384d(fy.m1905b(rawQuery.getString(2)), this.f811c), C1142e.f5201a));
                            if (dn.m1514a(jSONObject2, SocialConstants.PARAM_TYPE)) {
                                jSONObject2.put(SocialConstants.PARAM_TYPE, "new");
                            }
                        }
                        int i2 = i + 1;
                        AmapLoc amapLoc = new AmapLoc(jSONObject2);
                        String str = C2915a.f14760f;
                        if (dn.m1514a(jSONObject, "mmac") && dn.m1514a(jSONObject, "cgi")) {
                            str = (jSONObject.getString("cgi") + "#") + "network#";
                            str = jSONObject.getString("cgi").contains("#") ? str + "cgiwifi" : str + "wifi";
                        } else {
                            if (dn.m1514a(jSONObject, "cgi")) {
                                str = (jSONObject.getString("cgi") + "#") + "network#";
                                if (jSONObject.getString("cgi").contains("#")) {
                                    str = str + "cgi";
                                }
                            }
                            if (!rawQuery.moveToNext()) {
                                break;
                            }
                            i = i2;
                        }
                        cv.m1394a().m1401a(str + "&" + amapLoc.m5332e() + "&" + amapLoc.m5334f(), stringBuilder2, amapLoc, context, false);
                        if (!rawQuery.moveToNext()) {
                            break;
                        }
                        i = i2;
                    }
                }
                stringBuilder2.delete(0, stringBuilder2.length());
                if (rawQuery != null) {
                    rawQuery.close();
                }
                stringBuilder.delete(0, stringBuilder.length());
                if (openOrCreateDatabase != null && openOrCreateDatabase.isOpen()) {
                    openOrCreateDatabase.close();
                }
            } else if (openOrCreateDatabase != null && openOrCreateDatabase.isOpen()) {
                openOrCreateDatabase.close();
            }
        }
    }

    public void m1413a(Context context, int i) {
        String[] strArr = null;
        if (context != null) {
            SQLiteDatabase openOrCreateDatabase = context.openOrCreateDatabase("hmdb", 0, null);
            if (m1411a(openOrCreateDatabase, "hist")) {
                String str;
                switch (i) {
                    case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                        str = "time<?";
                        strArr = new String[]{String.valueOf(dn.m1502a() - MiStatInterface.MAX_UPLOAD_INTERVAL)};
                        break;
                    case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                        str = Constants.VIA_TO_TYPE_QQ_GROUP;
                        break;
                    default:
                        str = null;
                        break;
                }
                try {
                    openOrCreateDatabase.delete("hist" + this.f810b, str, strArr);
                } catch (Throwable th) {
                    ev.m1777a(th, "DB", "clearHist");
                    Object message = th.getMessage();
                    if (!TextUtils.isEmpty(message) && message.contains("no such table")) {
                    }
                }
                if (openOrCreateDatabase != null && openOrCreateDatabase.isOpen()) {
                    openOrCreateDatabase.close();
                }
            } else if (openOrCreateDatabase != null && openOrCreateDatabase.isOpen()) {
                openOrCreateDatabase.close();
            }
        }
    }

    protected synchronized void m1414a(Context context, String str, String str2, long j) {
        Throwable th;
        Cursor cursor = null;
        synchronized (this) {
            if (!(TextUtils.isEmpty(str) || context == null)) {
                String c = dn.m1529c(str);
                String c2 = dn.m1529c(str2);
                SQLiteDatabase openOrCreateDatabase;
                try {
                    int i;
                    ContentValues contentValues;
                    StringBuilder stringBuilder = new StringBuilder();
                    openOrCreateDatabase = context.openOrCreateDatabase("hmdb", 0, null);
                    try {
                        stringBuilder.append("CREATE TABLE IF NOT EXISTS ").append("hm");
                        stringBuilder.append(this.f810b);
                        stringBuilder.append(" (hash VARCHAR PRIMARY KEY, num INTEGER, extra VARCHAR, time VARCHAR);");
                        openOrCreateDatabase.execSQL(stringBuilder.toString());
                        stringBuilder.delete(0, stringBuilder.length());
                        stringBuilder.append("SELECT num FROM ").append("hm");
                        stringBuilder.append(this.f810b);
                        stringBuilder.append(" WHERE hash = '").append(c).append("';");
                        cursor = openOrCreateDatabase.rawQuery(stringBuilder.toString(), null);
                    } catch (Throwable th2) {
                        th = th2;
                        if (openOrCreateDatabase != null) {
                            if (openOrCreateDatabase.isOpen()) {
                                openOrCreateDatabase.close();
                            }
                        }
                        throw th;
                    }
                    if (cursor != null) {
                        if (cursor.moveToNext()) {
                            i = cursor.getInt(0);
                            if (i <= 0) {
                                i++;
                                contentValues = new ContentValues();
                                contentValues.put("num", Integer.valueOf(i));
                                contentValues.put("extra", c2);
                                contentValues.put("time", Long.valueOf(j));
                                openOrCreateDatabase.update("hm" + this.f810b, contentValues, "hash = '" + c + "'", null);
                            } else {
                                stringBuilder.delete(0, stringBuilder.length());
                                stringBuilder.append("REPLACE INTO ");
                                stringBuilder.append("hm").append(this.f810b);
                                stringBuilder.append(" VALUES (?, ?, ?, ?)");
                                openOrCreateDatabase.execSQL(stringBuilder.toString(), new Object[]{c, Integer.valueOf(1), c2, Long.valueOf(j)});
                            }
                            if (cursor != null) {
                                cursor.close();
                            }
                            stringBuilder.delete(0, stringBuilder.length());
                            if (openOrCreateDatabase != null) {
                                if (openOrCreateDatabase.isOpen()) {
                                    openOrCreateDatabase.close();
                                }
                            }
                        }
                    }
                    i = 0;
                    if (i <= 0) {
                        stringBuilder.delete(0, stringBuilder.length());
                        stringBuilder.append("REPLACE INTO ");
                        stringBuilder.append("hm").append(this.f810b);
                        stringBuilder.append(" VALUES (?, ?, ?, ?)");
                        openOrCreateDatabase.execSQL(stringBuilder.toString(), new Object[]{c, Integer.valueOf(1), c2, Long.valueOf(j)});
                    } else {
                        i++;
                        contentValues = new ContentValues();
                        contentValues.put("num", Integer.valueOf(i));
                        contentValues.put("extra", c2);
                        contentValues.put("time", Long.valueOf(j));
                        openOrCreateDatabase.update("hm" + this.f810b, contentValues, "hash = '" + c + "'", null);
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    stringBuilder.delete(0, stringBuilder.length());
                    if (openOrCreateDatabase != null) {
                        if (openOrCreateDatabase.isOpen()) {
                            openOrCreateDatabase.close();
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    Object obj = cursor;
                    if (openOrCreateDatabase != null) {
                        if (openOrCreateDatabase.isOpen()) {
                            openOrCreateDatabase.close();
                        }
                    }
                    throw th;
                }
            }
        }
    }

    protected void m1415a(String str, AmapLoc amapLoc, StringBuilder stringBuilder, Context context) {
        if (context != null) {
            if (this.f811c == null) {
                this.f811c = cs.m1375a("MD5", context.getPackageName());
            }
            JSONObject jSONObject = new JSONObject();
            if (str.contains("&")) {
                str = str.substring(0, str.indexOf("&"));
            }
            String substring = str.substring(str.lastIndexOf("#") + 1);
            if (substring.equals("cgi")) {
                jSONObject.put("cgi", str.substring(0, str.length() - (("network".length() + 2) + "cgi".length())));
            } else if (!(TextUtils.isEmpty(stringBuilder) || stringBuilder.indexOf("access") == -1)) {
                jSONObject.put("cgi", str.substring(0, str.length() - (substring.length() + ("network".length() + 2))));
                String[] split = stringBuilder.toString().split(",access");
                jSONObject.put("mmac", split[0].contains("#") ? split[0].substring(split[0].lastIndexOf("#") + 1) : split[0]);
            }
            if (dn.m1514a(jSONObject, "cgi") || dn.m1514a(jSONObject, "mmac")) {
                StringBuilder stringBuilder2 = new StringBuilder();
                SQLiteDatabase openOrCreateDatabase = context.openOrCreateDatabase("hmdb", 0, null);
                stringBuilder2.append("CREATE TABLE IF NOT EXISTS ").append("hist");
                stringBuilder2.append(this.f810b);
                stringBuilder2.append(" (feature VARCHAR PRIMARY KEY, nb VARCHAR, loc VARCHAR, time VARCHAR);");
                openOrCreateDatabase.execSQL(stringBuilder2.toString());
                stringBuilder2.delete(0, stringBuilder2.length());
                stringBuilder2.append("REPLACE INTO ");
                stringBuilder2.append("hist").append(this.f810b);
                stringBuilder2.append(" VALUES (?, ?, ?, ?)");
                Object[] objArr = new Object[]{cs.m1383c(jSONObject.toString().getBytes(C1142e.f5201a), this.f811c), cs.m1383c(stringBuilder.toString().getBytes(C1142e.f5201a), this.f811c), cs.m1383c(amapLoc.m5309F().getBytes(C1142e.f5201a), this.f811c), Long.valueOf(amapLoc.m5343k())};
                for (int i = 0; i < objArr.length - 1; i++) {
                    objArr[i] = fy.m1904b((byte[]) objArr[i]);
                }
                openOrCreateDatabase.execSQL(stringBuilder2.toString(), objArr);
                stringBuilder2.delete(0, stringBuilder2.length());
                stringBuilder2.append("SELECT COUNT(*) AS total FROM ");
                stringBuilder2.append("hist").append(this.f810b).append(";");
                Cursor rawQuery = openOrCreateDatabase.rawQuery(stringBuilder2.toString(), null);
                if (rawQuery == null || rawQuery.moveToFirst()) {
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    stringBuilder2.delete(0, stringBuilder2.length());
                    if (openOrCreateDatabase != null && openOrCreateDatabase.isOpen()) {
                        openOrCreateDatabase.close();
                        return;
                    }
                }
                if (rawQuery != null) {
                    rawQuery.close();
                }
                stringBuilder2.delete(0, stringBuilder2.length());
                if (openOrCreateDatabase != null) {
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.util.ArrayList<java.lang.String> m1416b(android.content.Context r12, int r13) {
        /*
        r11 = this;
        r0 = 0;
        monitor-enter(r11);
        if (r12 != 0) goto L_0x0006;
    L_0x0004:
        monitor-exit(r11);
        return r0;
    L_0x0006:
        r1 = "hmdb";
        r2 = 0;
        r3 = 0;
        r2 = r12.openOrCreateDatabase(r1, r2, r3);	 Catch:{ all -> 0x010c }
        r1 = "hm";
        r1 = r11.m1411a(r2, r1);	 Catch:{ all -> 0x00cd }
        if (r1 != 0) goto L_0x0031;
    L_0x0016:
        if (r2 == 0) goto L_0x0021;
    L_0x0018:
        r1 = r2.isOpen();	 Catch:{ all -> 0x00cd }
        if (r1 == 0) goto L_0x0021;
    L_0x001e:
        r2.close();	 Catch:{ all -> 0x00cd }
    L_0x0021:
        r1 = 0;
        if (r0 == 0) goto L_0x0004;
    L_0x0024:
        r2 = r1.isOpen();	 Catch:{ all -> 0x002e }
        if (r2 == 0) goto L_0x0004;
    L_0x002a:
        r1.close();	 Catch:{ all -> 0x002e }
        goto L_0x0004;
    L_0x002e:
        r0 = move-exception;
        monitor-exit(r11);
        throw r0;
    L_0x0031:
        r1 = new java.util.ArrayList;	 Catch:{ all -> 0x00cd }
        r1.<init>();	 Catch:{ all -> 0x00cd }
        switch(r13) {
            case 1: goto L_0x00b7;
            case 2: goto L_0x00db;
            default: goto L_0x0039;
        };
    L_0x0039:
        r3 = r0;
        r4 = r0;
    L_0x003b:
        r5 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00f1 }
        r5.<init>();	 Catch:{ Throwable -> 0x00f1 }
        r6 = "hm";
        r5 = r5.append(r6);	 Catch:{ Throwable -> 0x00f1 }
        r6 = r11.f810b;	 Catch:{ Throwable -> 0x00f1 }
        r5 = r5.append(r6);	 Catch:{ Throwable -> 0x00f1 }
        r5 = r5.toString();	 Catch:{ Throwable -> 0x00f1 }
        if (r3 == 0) goto L_0x00e1;
    L_0x0052:
        r4 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00f1 }
        r4.<init>();	 Catch:{ Throwable -> 0x00f1 }
        r6 = "SELECT hash, num, extra FROM ";
        r4.append(r6);	 Catch:{ Throwable -> 0x00f1 }
        r4.append(r5);	 Catch:{ Throwable -> 0x00f1 }
        r5 = " WHERE time < ";
        r5 = r4.append(r5);	 Catch:{ Throwable -> 0x00f1 }
        r6 = 0;
        r3 = r3[r6];	 Catch:{ Throwable -> 0x00f1 }
        r3 = r5.append(r3);	 Catch:{ Throwable -> 0x00f1 }
        r5 = ";";
        r3.append(r5);	 Catch:{ Throwable -> 0x00f1 }
        r3 = r4.toString();	 Catch:{ Throwable -> 0x00f1 }
        r4 = 0;
        r4 = r2.rawQuery(r3, r4);	 Catch:{ Throwable -> 0x00f1 }
        if (r4 == 0) goto L_0x00a4;
    L_0x007c:
        r3 = r4.moveToFirst();	 Catch:{ Throwable -> 0x00f1 }
        if (r3 == 0) goto L_0x00a4;
    L_0x0082:
        r3 = 0;
        r3 = r4.getString(r3);	 Catch:{ Throwable -> 0x00f1 }
        r5 = 2;
        r5 = r4.getString(r5);	 Catch:{ Throwable -> 0x00f1 }
        r6 = "{";
        r6 = r5.startsWith(r6);	 Catch:{ Throwable -> 0x00f1 }
        if (r6 != 0) goto L_0x009b;
    L_0x0094:
        r3 = com.p016a.dn.m1533d(r3);	 Catch:{ Throwable -> 0x00f1 }
        com.p016a.dn.m1533d(r5);	 Catch:{ Throwable -> 0x00f1 }
    L_0x009b:
        r1.add(r3);	 Catch:{ Throwable -> 0x00f1 }
        r3 = r4.moveToNext();	 Catch:{ Throwable -> 0x00f1 }
        if (r3 != 0) goto L_0x0082;
    L_0x00a4:
        if (r4 == 0) goto L_0x00a9;
    L_0x00a6:
        r4.close();	 Catch:{ Throwable -> 0x00f1 }
    L_0x00a9:
        if (r2 == 0) goto L_0x00b4;
    L_0x00ab:
        r0 = r2.isOpen();	 Catch:{ all -> 0x002e }
        if (r0 == 0) goto L_0x00b4;
    L_0x00b1:
        r2.close();	 Catch:{ all -> 0x002e }
    L_0x00b4:
        r0 = r1;
        goto L_0x0004;
    L_0x00b7:
        r4 = "time<?";
        r3 = 1;
        r3 = new java.lang.String[r3];	 Catch:{ all -> 0x00cd }
        r5 = 0;
        r6 = com.p016a.dn.m1502a();	 Catch:{ all -> 0x00cd }
        r8 = 1209600000; // 0x48190800 float:156704.0 double:5.97621805E-315;
        r6 = r6 - r8;
        r6 = java.lang.String.valueOf(r6);	 Catch:{ all -> 0x00cd }
        r3[r5] = r6;	 Catch:{ all -> 0x00cd }
        goto L_0x003b;
    L_0x00cd:
        r0 = move-exception;
        r1 = r2;
    L_0x00cf:
        if (r1 == 0) goto L_0x00da;
    L_0x00d1:
        r2 = r1.isOpen();	 Catch:{ all -> 0x002e }
        if (r2 == 0) goto L_0x00da;
    L_0x00d7:
        r1.close();	 Catch:{ all -> 0x002e }
    L_0x00da:
        throw r0;	 Catch:{ all -> 0x002e }
    L_0x00db:
        r3 = "1";
        r4 = r3;
        r3 = r0;
        goto L_0x003b;
    L_0x00e1:
        r2.delete(r5, r4, r3);	 Catch:{ Throwable -> 0x00f1 }
    L_0x00e4:
        if (r2 == 0) goto L_0x0004;
    L_0x00e6:
        r1 = r2.isOpen();	 Catch:{ all -> 0x002e }
        if (r1 == 0) goto L_0x0004;
    L_0x00ec:
        r2.close();	 Catch:{ all -> 0x002e }
        goto L_0x0004;
    L_0x00f1:
        r1 = move-exception;
        r3 = "DB";
        r4 = "clearHm";
        com.p016a.ev.m1777a(r1, r3, r4);	 Catch:{ all -> 0x00cd }
        r1 = r1.getMessage();	 Catch:{ all -> 0x00cd }
        r3 = android.text.TextUtils.isEmpty(r1);	 Catch:{ all -> 0x00cd }
        if (r3 != 0) goto L_0x00e4;
    L_0x0103:
        r3 = "no such table";
        r1 = r1.contains(r3);	 Catch:{ all -> 0x00cd }
        if (r1 == 0) goto L_0x00e4;
    L_0x010b:
        goto L_0x00e4;
    L_0x010c:
        r1 = move-exception;
        r10 = r1;
        r1 = r0;
        r0 = r10;
        goto L_0x00cf;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.cx.b(android.content.Context, int):java.util.ArrayList<java.lang.String>");
    }

    protected synchronized void m1417b(Context context) {
        Throwable th;
        SQLiteDatabase sQLiteDatabase = null;
        synchronized (this) {
            if (cf.f742a && context != null) {
                try {
                    SQLiteDatabase openOrCreateDatabase = context.openOrCreateDatabase("hmdb", 0, null);
                    Cursor rawQuery;
                    try {
                        if (m1411a(openOrCreateDatabase, "hm")) {
                            long a = dn.m1502a() - 1209600000;
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("SELECT hash, num, extra, time FROM ");
                            stringBuilder.append("hm").append(this.f810b);
                            stringBuilder.append(" WHERE time > ").append(a);
                            stringBuilder.append(" ORDER BY num DESC LIMIT 0,");
                            stringBuilder.append(C2799f.f14263a).append(";");
                            rawQuery = openOrCreateDatabase.rawQuery(stringBuilder.toString(), null);
                            stringBuilder.delete(0, stringBuilder.length());
                            if (rawQuery != null) {
                                rawQuery.moveToFirst();
                                int i = 0;
                                do {
                                    i++;
                                    String string = rawQuery.getString(0);
                                    int i2 = rawQuery.getInt(1);
                                    String string2 = rawQuery.getString(2);
                                    long j = rawQuery.getLong(3);
                                    if (!string2.startsWith("{")) {
                                        string = dn.m1533d(string);
                                        string2 = dn.m1533d(string2);
                                    }
                                    cz.m1420a().m1424a(context, string, string2, i2, j, false);
                                } while (rawQuery.moveToNext());
                            }
                            if (rawQuery != null) {
                                rawQuery.close();
                            }
                            if (openOrCreateDatabase != null) {
                                if (openOrCreateDatabase.isOpen()) {
                                    openOrCreateDatabase.close();
                                }
                            }
                        } else {
                            if (openOrCreateDatabase != null && openOrCreateDatabase.isOpen()) {
                                openOrCreateDatabase.close();
                            }
                            SQLiteDatabase sQLiteDatabase2 = null;
                            if (sQLiteDatabase != null) {
                                if (sQLiteDatabase2.isOpen()) {
                                    sQLiteDatabase2.close();
                                }
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        sQLiteDatabase = openOrCreateDatabase;
                        if (sQLiteDatabase != null) {
                            if (sQLiteDatabase.isOpen()) {
                                sQLiteDatabase.close();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (sQLiteDatabase != null) {
                        if (sQLiteDatabase.isOpen()) {
                            sQLiteDatabase.close();
                        }
                    }
                    throw th;
                }
            }
        }
    }
}
