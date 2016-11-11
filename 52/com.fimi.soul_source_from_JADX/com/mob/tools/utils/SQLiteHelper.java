package com.mob.tools.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import org.codehaus.jackson.util.MinimalPrettyPrinter;
import org.p122a.p123a.C2915a;

public class SQLiteHelper {

    public class SingleTableDB {
        private Context context;
        private HashMap<String, Boolean> fieldLimits;
        private LinkedHashMap<String, String> fieldTypes;
        private String name;
        private String primary;
        private boolean primaryAutoincrement;
        private SQLiteOpenHelper sqlite;
        private int version;

        /* renamed from: com.mob.tools.utils.SQLiteHelper.SingleTableDB.1 */
        class C21811 extends SQLiteOpenHelper {
            C21811(Context context, String str, CursorFactory cursorFactory, int i) {
                super(context, str, cursorFactory, i);
            }

            public void onCreate(SQLiteDatabase sQLiteDatabase) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("create table  ").append(SingleTableDB.this.name).append("(");
                for (Entry entry : SingleTableDB.this.fieldTypes.entrySet()) {
                    String str = (String) entry.getKey();
                    String str2 = (String) entry.getValue();
                    boolean booleanValue = ((Boolean) SingleTableDB.this.fieldLimits.get(str)).booleanValue();
                    boolean equals = str.equals(SingleTableDB.this.primary);
                    boolean access$1000 = equals ? SingleTableDB.this.primaryAutoincrement : false;
                    stringBuilder.append(str).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(str2);
                    stringBuilder.append(booleanValue ? " not null" : C2915a.f14760f);
                    stringBuilder.append(equals ? " primary key" : C2915a.f14760f);
                    stringBuilder.append(access$1000 ? " autoincrement," : MiPushClient.ACCEPT_TIME_SEPARATOR);
                }
                stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), ");");
                sQLiteDatabase.execSQL(stringBuilder.toString());
            }

            public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            }
        }

        private SingleTableDB(Context context, String str, int i) {
            this.context = context;
            this.name = str;
            this.version = i;
            this.fieldTypes = new LinkedHashMap();
            this.fieldLimits = new HashMap();
        }

        private void close() {
            if (this.sqlite != null) {
                this.sqlite.close();
                this.sqlite = null;
            }
        }

        private String getName() {
            return this.name;
        }

        private SQLiteDatabase getReadableDatabase() {
            return this.sqlite.getReadableDatabase();
        }

        private SQLiteDatabase getWritableDatabase() {
            return this.sqlite.getWritableDatabase();
        }

        private void open() {
            if (this.sqlite == null) {
                this.sqlite = new C21811(this.context.getApplicationContext(), this.name, null, this.version);
            }
        }

        public void addField(String str, String str2, boolean z) {
            if (this.sqlite == null) {
                this.fieldTypes.put(str, str2);
                this.fieldLimits.put(str, Boolean.valueOf(z));
            }
        }

        public void setPrimary(String str, boolean z) {
            this.primary = str;
            this.primaryAutoincrement = z;
        }
    }

    public static void close(SingleTableDB singleTableDB) {
        singleTableDB.close();
    }

    public static int delete(SingleTableDB singleTableDB, String str, String[] strArr) {
        singleTableDB.open();
        return singleTableDB.getWritableDatabase().delete(singleTableDB.getName(), str, strArr);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void execSQL(com.mob.tools.utils.SQLiteHelper.SingleTableDB r2, java.lang.String r3) {
        /*
        r2.open();
        r1 = r2.getWritableDatabase();
        r1.beginTransaction();
        r1.execSQL(r3);	 Catch:{ Throwable -> 0x0014 }
        r1.setTransactionSuccessful();	 Catch:{ Throwable -> 0x0014 }
        r1.endTransaction();
        return;
    L_0x0014:
        r0 = move-exception;
        throw r0;	 Catch:{ all -> 0x0016 }
    L_0x0016:
        r0 = move-exception;
        r1.endTransaction();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.SQLiteHelper.execSQL(com.mob.tools.utils.SQLiteHelper$SingleTableDB, java.lang.String):void");
    }

    public static SingleTableDB getDatabase(Context context, String str, int i) {
        return new SingleTableDB(str, i, null);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getSize(com.mob.tools.utils.SQLiteHelper.SingleTableDB r5) {
        /*
        r1 = 0;
        r0 = 0;
        r5.open();
        r2 = r5.getReadableDatabase();
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0034 }
        r3.<init>();	 Catch:{ Throwable -> 0x0034 }
        r4 = "select count(*) from ";
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x0034 }
        r4 = r5.getName();	 Catch:{ Throwable -> 0x0034 }
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x0034 }
        r3 = r3.toString();	 Catch:{ Throwable -> 0x0034 }
        r4 = 0;
        r1 = r2.rawQuery(r3, r4);	 Catch:{ Throwable -> 0x0034 }
        r2 = r1.moveToNext();	 Catch:{ Throwable -> 0x0034 }
        if (r2 == 0) goto L_0x0030;
    L_0x002b:
        r0 = 0;
        r0 = r1.getInt(r0);	 Catch:{ Throwable -> 0x0034 }
    L_0x0030:
        r1.close();
        return r0;
    L_0x0034:
        r0 = move-exception;
        throw r0;	 Catch:{ all -> 0x0036 }
    L_0x0036:
        r0 = move-exception;
        r1.close();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.SQLiteHelper.getSize(com.mob.tools.utils.SQLiteHelper$SingleTableDB):int");
    }

    public static long insert(SingleTableDB singleTableDB, ContentValues contentValues) {
        singleTableDB.open();
        return singleTableDB.getWritableDatabase().replace(singleTableDB.getName(), null, contentValues);
    }

    public static Cursor query(SingleTableDB singleTableDB, String[] strArr, String str, String[] strArr2, String str2) {
        singleTableDB.open();
        return singleTableDB.getReadableDatabase().query(singleTableDB.getName(), strArr, str, strArr2, null, null, str2);
    }

    public static Cursor rawQuery(SingleTableDB singleTableDB, String str, String[] strArr) {
        singleTableDB.open();
        return singleTableDB.getWritableDatabase().rawQuery(str, strArr);
    }

    public static int update(SingleTableDB singleTableDB, ContentValues contentValues, String str, String[] strArr) {
        singleTableDB.open();
        return singleTableDB.getWritableDatabase().update(singleTableDB.getName(), contentValues, str, strArr);
    }
}
