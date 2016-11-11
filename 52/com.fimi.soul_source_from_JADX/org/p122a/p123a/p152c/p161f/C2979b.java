package org.p122a.p123a.p152c.p161f;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.codehaus.jackson.util.BufferRecycler;
import org.p122a.p123a.C3004e;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p180o.C3234a;

@C2912b
/* renamed from: org.a.a.c.f.b */
public final class C2979b {
    public static final String f14907a = "EEE, dd MMM yyyy HH:mm:ss zzz";
    public static final String f14908b = "EEE, dd-MMM-yy HH:mm:ss zzz";
    public static final String f14909c = "EEE MMM d HH:mm:ss yyyy";
    public static final TimeZone f14910d;
    private static final String[] f14911e;
    private static final Date f14912f;

    static {
        f14911e = new String[]{f14907a, f14908b, f14909c};
        f14910d = TimeZone.getTimeZone("GMT");
        Calendar instance = Calendar.getInstance();
        instance.setTimeZone(f14910d);
        instance.set(BufferRecycler.DEFAULT_WRITE_CONCAT_BUFFER_LEN, 0, 1, 0, 0, 0);
        instance.set(14, 0);
        f14912f = instance.getTime();
    }

    private C2979b() {
    }

    public static String m16914a(Date date) {
        return C2979b.m16915a(date, f14907a);
    }

    public static String m16915a(Date date, String str) {
        C3234a.m17886a((Object) date, C3004e.f15032r);
        C3234a.m17886a((Object) str, "Pattern");
        return C2980c.m16920a(str).format(date);
    }

    public static Date m16916a(String str) {
        return C2979b.m16918a(str, null, null);
    }

    public static Date m16917a(String str, String[] strArr) {
        return C2979b.m16918a(str, strArr, null);
    }

    public static Date m16918a(String str, String[] strArr, Date date) {
        C3234a.m17886a((Object) str, "Date value");
        if (strArr == null) {
            strArr = f14911e;
        }
        if (date == null) {
            date = f14912f;
        }
        if (str.length() > 1 && str.startsWith("'") && str.endsWith("'")) {
            str = str.substring(1, str.length() - 1);
        }
        for (String a : r6) {
            SimpleDateFormat a2 = C2980c.m16920a(a);
            a2.set2DigitYearStart(date);
            ParsePosition parsePosition = new ParsePosition(0);
            Date parse = a2.parse(str, parsePosition);
            if (parsePosition.getIndex() != 0) {
                return parse;
            }
        }
        return null;
    }

    public static void m16919a() {
        C2980c.m16921a();
    }
}
