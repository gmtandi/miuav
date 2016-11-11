package com.xiaomi.push.providers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.market.sdk.C2539l;
import com.xiaomi.mipush.sdk.MiPushClient;
import org.codehaus.jackson.util.MinimalPrettyPrinter;

/* renamed from: com.xiaomi.push.providers.a */
public class C2628a extends SQLiteOpenHelper {
    public static final Object f13057a;
    private static int f13058b;
    private static final String[] f13059c;

    static {
        f13058b = 1;
        f13057a = new Object();
        f13059c = new String[]{C2539l.PACKAGE_NAME, "TEXT", "message_ts", " LONG DEFAULT 0 ", "bytes", " LONG DEFAULT 0 ", "network_type", " INT DEFAULT -1 ", "rcv", " INT DEFAULT -1 ", "imsi", "TEXT"};
    }

    public C2628a(Context context) {
        super(context, "traffic.db", null, f13058b);
    }

    private void m14891a(SQLiteDatabase sQLiteDatabase) {
        StringBuilder stringBuilder = new StringBuilder("CREATE TABLE traffic(_id INTEGER  PRIMARY KEY ,");
        for (int i = 0; i < f13059c.length - 1; i += 2) {
            if (i != 0) {
                stringBuilder.append(MiPushClient.ACCEPT_TIME_SEPARATOR);
            }
            stringBuilder.append(f13059c[i]).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(f13059c[i + 1]);
        }
        stringBuilder.append(");");
        sQLiteDatabase.execSQL(stringBuilder.toString());
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        synchronized (f13057a) {
            try {
                m14891a(sQLiteDatabase);
            } catch (Throwable e) {
                C2463b.m14125a(e);
            }
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
