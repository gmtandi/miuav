package cn.sharesdk.framework.p013b.p014a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import cn.sharesdk.framework.utils.C0205d;
import com.tencent.mm.sdk.message.RMsgInfoDB;
import com.xiaomi.market.sdk.C2538k;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.util.ArrayList;
import org.p122a.p123a.C2915a;

/* renamed from: cn.sharesdk.framework.b.a.d */
public class C0168d {
    public static int f223a;
    public static int f224b;
    public static int f225c;

    static {
        f223a = 0;
        f224b = 1;
        f225c = 2;
    }

    public static synchronized long m463a(Context context, String str, long j) {
        long a;
        synchronized (C0168d.class) {
            if (str != null) {
                if (str.trim() != C2915a.f14760f) {
                    C0166b a2 = C0166b.m458a(context);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("post_time", Long.valueOf(j));
                    contentValues.put("message_data", str.toString());
                    a = a2.m461a(RMsgInfoDB.TABLE, contentValues);
                }
            }
            a = -1;
        }
        return a;
    }

    public static synchronized long m464a(Context context, ArrayList<String> arrayList) {
        long j;
        synchronized (C0168d.class) {
            if (arrayList == null) {
                j = 0;
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < arrayList.size(); i++) {
                    stringBuilder.append("'");
                    stringBuilder.append((String) arrayList.get(i));
                    stringBuilder.append("'");
                    stringBuilder.append(MiPushClient.ACCEPT_TIME_SEPARATOR);
                }
                C0205d.m752a().m743i("delete COUNT == %s", Integer.valueOf(C0166b.m458a(context).m460a(RMsgInfoDB.TABLE, "_id in ( " + stringBuilder.toString().substring(0, stringBuilder.length() - 1) + " )", null)));
                j = (long) C0166b.m458a(context).m460a(RMsgInfoDB.TABLE, "_id in ( " + stringBuilder.toString().substring(0, stringBuilder.length() - 1) + " )", null);
            }
        }
        return j;
    }

    public static synchronized ArrayList<C0167c> m465a(Context context) {
        ArrayList<C0167c> a;
        synchronized (C0168d.class) {
            a = C0166b.m458a(context).m459a(RMsgInfoDB.TABLE) > 0 ? C0168d.m466a(context, null, null) : new ArrayList();
        }
        return a;
    }

    private static synchronized ArrayList<C0167c> m466a(Context context, String str, String[] strArr) {
        ArrayList<C0167c> arrayList;
        synchronized (C0168d.class) {
            arrayList = new ArrayList();
            C0167c c0167c = new C0167c();
            StringBuilder stringBuilder = new StringBuilder();
            Cursor a = C0166b.m458a(context).m462a(RMsgInfoDB.TABLE, new String[]{C2538k._ID, "post_time", "message_data"}, str, strArr, null);
            StringBuilder stringBuilder2 = stringBuilder;
            C0167c c0167c2 = c0167c;
            while (a != null && a.moveToNext()) {
                c0167c2.f222b.add(a.getString(0));
                if (c0167c2.f222b.size() == 100) {
                    stringBuilder2.append(a.getString(2));
                    c0167c2.f221a = stringBuilder2.toString();
                    arrayList.add(c0167c2);
                    c0167c2 = new C0167c();
                    stringBuilder2 = new StringBuilder();
                } else {
                    stringBuilder2.append(a.getString(2) + "\n");
                }
            }
            a.close();
            if (c0167c2.f222b.size() != 0) {
                c0167c2.f221a = stringBuilder2.toString().substring(0, stringBuilder2.length() - 1);
                arrayList.add(c0167c2);
            }
        }
        return arrayList;
    }
}
