package com.baidu.tts.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.baidu.tts.client.model.Conditions;
import com.baidu.tts.client.model.ModelBags;
import com.baidu.tts.client.model.ModelFileBags;
import com.baidu.tts.p041e.C0794g;
import com.baidu.tts.p049k.C0822a;
import com.baidu.tts.tools.DataTool;
import com.baidu.tts.tools.SqlTool;
import com.baidu.tts.tools.StringTool;
import com.tencent.mm.sdk.platformtools.LocaleUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* renamed from: com.baidu.tts.database.a */
public class C0782a {
    private C0822a f4343a;
    private C0783b f4344b;
    private ReadWriteLock f4345c;
    private Lock f4346d;
    private Lock f4347e;

    public C0782a(C0822a c0822a) {
        this.f4345c = new ReentrantReadWriteLock();
        this.f4346d = this.f4345c.writeLock();
        this.f4347e = this.f4345c.readLock();
        this.f4343a = c0822a;
        this.f4344b = new C0783b(this.f4343a.m6829d());
    }

    private SQLiteDatabase m6716a() {
        return this.f4344b.getWritableDatabase();
    }

    private SQLiteDatabase m6717b() {
        return this.f4344b.getReadableDatabase();
    }

    public int m6718a(String str) {
        SQLiteDatabase a;
        int a2;
        this.f4346d.lock();
        try {
            a = m6716a();
            a2 = SpeechModelTable.m6712a(a, str);
            a.close();
            this.f4346d.unlock();
        } catch (Exception e) {
            a2 = -1;
            a.close();
            this.f4346d.unlock();
        } catch (Throwable th) {
            this.f4346d.unlock();
        }
        return a2;
    }

    public String m6719a(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder("select b.absPath from speechModel a left join modelFile b on a.");
        stringBuilder.append(str);
        stringBuilder.append("=b.id where a.id=?");
        Map a = m6722a(stringBuilder.toString(), new String[]{str2});
        return a != null ? (String) a.get(C0794g.ABS_PATH.m6742b()) : null;
    }

    public List<Map<String, String>> m6720a(Conditions conditions) {
        String str = null;
        String[] strArr = null;
        if (!StringTool.isEmpty(conditions.getVersion())) {
            str = "version_min <= ? and version_max >= ?";
            strArr = new String[]{conditions.getVersion(), conditions.getVersion()};
        }
        String[] domainArray = conditions.getDomainArray();
        String[] languageArray = conditions.getLanguageArray();
        String[] qualityArray = conditions.getQualityArray();
        String[] genderArray = conditions.getGenderArray();
        String[] speakerArray = conditions.getSpeakerArray();
        String[] modelIdsArray = conditions.getModelIdsArray();
        String buildInCondition = SqlTool.buildInCondition("domain", domainArray);
        String buildInCondition2 = SqlTool.buildInCondition("language", languageArray);
        String buildInCondition3 = SqlTool.buildInCondition("quality", qualityArray);
        String buildInCondition4 = SqlTool.buildInCondition("gender", genderArray);
        String buildInCondition5 = SqlTool.buildInCondition("speaker", speakerArray);
        String buildInCondition6 = SqlTool.buildInCondition(LocaleUtil.INDONESIAN, modelIdsArray);
        str = SqlTool.buildConditions("and", str, buildInCondition, buildInCondition2, buildInCondition3, buildInCondition4, buildInCondition5, buildInCondition6);
        if (StringTool.isEmpty(str)) {
            return null;
        }
        return m6727b("select * from speechModel where " + str, DataTool.connect(strArr, domainArray, languageArray, qualityArray, genderArray, speakerArray, modelIdsArray));
    }

    public List<Map<String, String>> m6721a(Set<String> set) {
        if (set == null || set.isEmpty()) {
            return null;
        }
        String[] fromSetToArray = DataTool.fromSetToArray(set);
        return m6727b("select * from modelFile where " + SqlTool.buildInCondition(LocaleUtil.INDONESIAN, fromSetToArray), fromSetToArray);
    }

    public Map<String, String> m6722a(String str, String[] strArr) {
        SQLiteDatabase b;
        Map<String, String> hashMap;
        Map<String, String> map;
        Exception exception;
        this.f4347e.lock();
        try {
            b = m6717b();
            try {
                Cursor rawQuery = b.rawQuery(str, strArr);
                if (rawQuery != null) {
                    if (rawQuery.moveToFirst()) {
                        hashMap = new HashMap();
                        try {
                            String[] columnNames = rawQuery.getColumnNames();
                            int length = columnNames.length;
                            for (int i = 0; i < length; i++) {
                                hashMap.put(columnNames[i], rawQuery.getString(rawQuery.getColumnIndex(columnNames[i])));
                            }
                        } catch (Exception e) {
                            Exception exception2 = e;
                            map = hashMap;
                            exception = exception2;
                            exception.printStackTrace();
                            if (b != null) {
                                b.close();
                            }
                            this.f4347e.unlock();
                            return map;
                        }
                    }
                    hashMap = null;
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    map = hashMap;
                } else {
                    map = null;
                }
                if (b != null) {
                    b.close();
                }
            } catch (Exception e2) {
                exception = e2;
                map = null;
                exception.printStackTrace();
                if (b != null) {
                    b.close();
                }
                this.f4347e.unlock();
                return map;
            }
            this.f4347e.unlock();
            return map;
        } catch (Throwable th) {
            this.f4347e.unlock();
        }
    }

    public void m6723a(ModelBags modelBags) {
        this.f4346d.lock();
        try {
            SpeechModelTable.m6714a(m6716a(), modelBags);
        } finally {
            this.f4346d.unlock();
        }
    }

    public void m6724a(ModelFileBags modelFileBags) {
        this.f4346d.lock();
        try {
            ModelFileTable.m6709a(m6716a(), modelFileBags);
        } finally {
            this.f4346d.unlock();
        }
    }

    public void m6725a(String str, int i) {
        SQLiteDatabase a;
        this.f4346d.lock();
        try {
            String str2 = "replace into fsFileInfo (absPath,state) values (?, ?)";
            String valueOf = String.valueOf(i);
            String[] strArr = new String[]{str, valueOf};
            a = m6716a();
            a.execSQL(str2, strArr);
            a.close();
            this.f4346d.unlock();
        } catch (Throwable th) {
            this.f4346d.unlock();
        }
    }

    public int m6726b(String str) {
        SQLiteDatabase a;
        int a2;
        this.f4346d.lock();
        try {
            a = m6716a();
            a2 = ModelFileTable.m6707a(a, str);
            a.close();
            this.f4346d.unlock();
        } catch (Exception e) {
            a2 = -1;
            a.close();
            this.f4346d.unlock();
        } catch (Throwable th) {
            this.f4346d.unlock();
        }
        return a2;
    }

    public java.util.List<java.util.Map<java.lang.String, java.lang.String>> m6727b(java.lang.String r10, java.lang.String[] r11) {
        /* JADX: method processing error */
/*
        Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.baidu.tts.database.a.b(java.lang.String, java.lang.String[]):java.util.List<java.util.Map<java.lang.String, java.lang.String>>. bs: [B:17:0x004a, B:23:0x0056]
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:86)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:57)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
        /*
        r9 = this;
        r2 = 0;
        r0 = r9.f4347e;
        r0.lock();
        r0 = new java.util.ArrayList;	 Catch:{ Exception -> 0x0053 }
        r0.<init>();	 Catch:{ Exception -> 0x0053 }
        r2 = r9.m6717b();	 Catch:{ Exception -> 0x006d }
        r3 = r2.rawQuery(r10, r11);	 Catch:{ Exception -> 0x006d }
        if (r3 == 0) goto L_0x0048;	 Catch:{ Exception -> 0x006d }
    L_0x0015:
        r1 = r3.moveToFirst();	 Catch:{ Exception -> 0x006d }
        if (r1 == 0) goto L_0x0043;	 Catch:{ Exception -> 0x006d }
    L_0x001b:
        r4 = r3.getColumnNames();	 Catch:{ Exception -> 0x006d }
    L_0x001f:
        r5 = new java.util.HashMap;	 Catch:{ Exception -> 0x006d }
        r5.<init>();	 Catch:{ Exception -> 0x006d }
        r6 = r4.length;	 Catch:{ Exception -> 0x006d }
        r1 = 0;	 Catch:{ Exception -> 0x006d }
    L_0x0026:
        if (r1 >= r6) goto L_0x003a;	 Catch:{ Exception -> 0x006d }
    L_0x0028:
        r7 = r4[r1];	 Catch:{ Exception -> 0x006d }
        r8 = r4[r1];	 Catch:{ Exception -> 0x006d }
        r8 = r3.getColumnIndex(r8);	 Catch:{ Exception -> 0x006d }
        r8 = r3.getString(r8);	 Catch:{ Exception -> 0x006d }
        r5.put(r7, r8);	 Catch:{ Exception -> 0x006d }
        r1 = r1 + 1;	 Catch:{ Exception -> 0x006d }
        goto L_0x0026;	 Catch:{ Exception -> 0x006d }
    L_0x003a:
        r0.add(r5);	 Catch:{ Exception -> 0x006d }
        r1 = r3.moveToNext();	 Catch:{ Exception -> 0x006d }
        if (r1 != 0) goto L_0x001f;	 Catch:{ Exception -> 0x006d }
    L_0x0043:
        if (r3 == 0) goto L_0x0048;	 Catch:{ Exception -> 0x006d }
    L_0x0045:
        r3.close();	 Catch:{ Exception -> 0x006d }
    L_0x0048:
        if (r2 == 0) goto L_0x004d;
    L_0x004a:
        r2.close();	 Catch:{ all -> 0x005f }
    L_0x004d:
        r1 = r9.f4347e;
        r1.unlock();
        return r0;
    L_0x0053:
        r0 = move-exception;
        r1 = r0;
        r0 = r2;
    L_0x0056:
        r1.printStackTrace();	 Catch:{ all -> 0x0066 }
        if (r2 == 0) goto L_0x004d;
    L_0x005b:
        r2.close();	 Catch:{ all -> 0x005f }
        goto L_0x004d;
    L_0x005f:
        r0 = move-exception;
        r1 = r9.f4347e;
        r1.unlock();
        throw r0;
    L_0x0066:
        r0 = move-exception;
        if (r2 == 0) goto L_0x006c;
    L_0x0069:
        r2.close();	 Catch:{ all -> 0x005f }
    L_0x006c:
        throw r0;	 Catch:{ all -> 0x005f }
    L_0x006d:
        r1 = move-exception;
        goto L_0x0056;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.tts.database.a.b(java.lang.String, java.lang.String[]):java.util.List<java.util.Map<java.lang.String, java.lang.String>>");
    }

    public Map<String, String> m6728c(String str) {
        return m6722a("select * from fsFileInfo where absPath=?", new String[]{str});
    }

    public Map<String, String> m6729d(String str) {
        return m6722a("select * from modelFile where id=?", new String[]{str});
    }

    public Map<String, String> m6730e(String str) {
        return m6722a("select * from speechModel where id=?", new String[]{str});
    }
}
