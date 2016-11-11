package com.fimi.soul.drone.p114e;

import com.fimi.soul.base.C1236a;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* renamed from: com.fimi.soul.drone.e.c */
public class C1534c {
    public static String m10087a() {
        return new SimpleDateFormat(C1236a.f5614l).format(new Date());
    }

    public static Date m10088a(String str) {
        Date date = null;
        try {
            date = new SimpleDateFormat(C1236a.f5614l).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
