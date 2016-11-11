package com.fimi.soul.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.utils.v */
public class C1982v {
    public static C1982v f10209a;
    public static final String f10210b;
    private C1968h f10211c;
    private SQLiteDatabase f10212d;
    private Context f10213e;

    static {
        f10210b = "create table  if not exists " + C1983w.f10214a + "(" + C1983w.f10215b + " varchar(20) not null ," + C1983w.f10216c + " long default 0 ," + C1983w.f10217d + " double default 0," + C1983w.f10218e + " varchar(20) not null)";
    }

    public C1982v(Context context) {
        this.f10213e = context;
        this.f10211c = new C1968h(context, null, null, 0);
        this.f10212d = this.f10211c.getWritableDatabase();
        this.f10212d.execSQL(f10210b);
    }

    public static C1982v m12518a(Context context) {
        if (f10209a == null) {
            f10209a = new C1982v(context);
        }
        return f10209a;
    }

    private ContentValues m12519b(C1984x c1984x) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(C1983w.f10216c, Long.valueOf(c1984x.m12529c()));
        contentValues.put(C1983w.f10217d, Double.valueOf(c1984x.m12530d()));
        contentValues.put(C1983w.f10215b, c1984x.m12527b());
        contentValues.put(C1983w.f10218e, c1984x.m12523a());
        return contentValues;
    }

    public List<C1984x> m12520a(String str, String str2) {
        List<C1984x> list = null;
        if (!(str == null || C2915a.f14760f.equals(str) || str2 == null || C2915a.f14760f.equals(str2))) {
            Cursor query = this.f10212d.query(C1983w.f10214a, new String[]{C1983w.f10218e, C1983w.f10215b, C1983w.f10216c, C1983w.f10217d}, C1983w.f10218e + " = ? and " + C1983w.f10215b + " = ? ", new String[]{str2, str}, null, null, null);
            if (query != null && query.getCount() > 0) {
                list = new ArrayList();
                query.moveToFirst();
                do {
                    C1984x c1984x = new C1984x();
                    c1984x.m12528b(str);
                    c1984x.m12526a(str2);
                    c1984x.m12525a(query.getLong(query.getColumnIndex(C1983w.f10216c)));
                    c1984x.m12524a(query.getDouble(query.getColumnIndex(C1983w.f10217d)));
                    list.add(c1984x);
                } while (query.moveToNext());
            }
        }
        return list;
    }

    public void m12521a(C1984x c1984x) {
        this.f10212d.insert(C1983w.f10214a, null, m12519b(c1984x));
    }

    public boolean m12522b(String str, String str2) {
        if (str == null || C2915a.f14760f.equals(str) || str2 == null || C2915a.f14760f.equals(str2)) {
            return false;
        }
        return this.f10212d.delete(C1983w.f10214a, new StringBuilder().append(C1983w.f10215b).append(" = ? and ").append(C1983w.f10218e).append(" = ? ").toString(), new String[]{str, str2}) > 0;
    }
}
