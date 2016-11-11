package com.fimi.kernel.p084e;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.fimi.kernel.e.l */
public class C1173l {
    public static final String f5329a = "yyyy-MM-dd HH:mm:ss";
    public static final String f5330b = "yyyy-MM-dd";
    public static final String f5331c = "yyyy-MM";
    public static final String f5332d = "yyyy-MM-dd HH:mm";
    public static final String f5333e = "yyyyMMddHHmmss";
    public static final String f5334f = "MM/dd";
    public static final String f5335g = "HH:mm:ss";
    public static final String f5336h = "HH:mm";
    public static final String f5337i = "AM";
    public static final String f5338j = "PM";

    public static int m8137a(long j, long j2) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        Calendar instance2 = Calendar.getInstance();
        instance2.setTimeInMillis(j2);
        int i = instance.get(1);
        int i2 = instance2.get(1);
        int i3 = instance.get(6);
        int i4 = instance2.get(6);
        if (i - i2 > 0) {
            return instance2.getActualMaximum(6) + (i3 - i4);
        }
        if (i - i2 >= 0) {
            return i3 - i4;
        }
        return (i3 - i4) - instance.getActualMaximum(6);
    }

    public static long m8138a() {
        try {
            return C1173l.m8147a(C1173l.m8141a(f5330b) + " 00:00:00", f5329a).getTime();
        } catch (Exception e) {
            return -1;
        }
    }

    public static String m8139a(long j) {
        if (j <= 1000) {
            return j + "\u6beb\u79d2";
        }
        if ((j / 1000) / 60 <= 1) {
            return (j / 1000) + "\u79d2";
        }
        return ((j / 1000) / 60) + "\u5206" + ((j / 1000) % 60) + "\u79d2";
    }

    public static String m8140a(long j, String str) {
        String str2 = null;
        try {
            str2 = new SimpleDateFormat(str).format(Long.valueOf(j));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str2;
    }

    public static String m8141a(String str) {
        C1181t.m8221a(C1173l.class, "getCurrentDate:" + str);
        String str2 = null;
        try {
            str2 = new SimpleDateFormat(str).format(new GregorianCalendar().getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str2;
    }

    private static String m8142a(String str, int i) {
        try {
            Calendar gregorianCalendar = new GregorianCalendar();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
            int i2 = gregorianCalendar.get(7);
            if (i2 == i) {
                return simpleDateFormat.format(gregorianCalendar.getTime());
            }
            i2 = i - i2;
            if (i == 1) {
                i2 = 7 - Math.abs(i2);
            }
            gregorianCalendar.add(5, i2);
            return simpleDateFormat.format(gregorianCalendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String m8143a(String str, int i, int i2) {
        String str2 = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
            Calendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.add(i, i2);
            str2 = simpleDateFormat.format(gregorianCalendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str2;
    }

    public static String m8144a(String str, String str2, int i, int i2) {
        String str3 = null;
        try {
            Calendar gregorianCalendar = new GregorianCalendar();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str2);
            gregorianCalendar.setTime(simpleDateFormat.parse(str));
            gregorianCalendar.add(i, i2);
            str3 = simpleDateFormat.format(gregorianCalendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str3;
    }

    public static String m8145a(Date date, String str) {
        String str2 = null;
        try {
            str2 = new SimpleDateFormat(str).format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str2;
    }

    public static String m8146a(Date date, String str, int i, int i2) {
        String str2 = null;
        try {
            Calendar gregorianCalendar = new GregorianCalendar();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
            gregorianCalendar.setTime(date);
            gregorianCalendar.add(i, i2);
            str2 = simpleDateFormat.format(gregorianCalendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str2;
    }

    public static Date m8147a(String str, String str2) {
        Date date = null;
        try {
            date = new SimpleDateFormat(str2).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static void m8148a(String[] strArr) {
        System.out.println(C1173l.m8156c("2012-3-2 12:2:20", "MM\u6708dd\u65e5  HH:mm"));
    }

    public static boolean m8149a(int i) {
        return (i % 4 == 0 && i % 400 != 0) || i % 400 == 0;
    }

    public static int m8150b(long j, long j2) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        Calendar instance2 = Calendar.getInstance();
        instance2.setTimeInMillis(j2);
        return (instance.get(11) - instance2.get(11)) + (C1173l.m8137a(j, j2) * 24);
    }

    public static long m8151b() {
        try {
            return C1173l.m8147a(C1173l.m8141a(f5330b) + " 24:00:00", f5329a).getTime();
        } catch (Exception e) {
            return -1;
        }
    }

    public static String m8152b(String str) {
        return C1173l.m8142a(str, 2);
    }

    public static String m8153b(String str, String str2) {
        String str3 = null;
        try {
            Calendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(new SimpleDateFormat(f5329a).parse(str));
            str3 = new SimpleDateFormat(str2).format(gregorianCalendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str3;
    }

    public static int m8154c(long j, long j2) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        Calendar instance2 = Calendar.getInstance();
        instance2.setTimeInMillis(j2);
        return (instance.get(12) - instance2.get(12)) + (C1173l.m8150b(j, j2) * 60);
    }

    public static String m8155c(String str) {
        return C1173l.m8142a(str, 1);
    }

    public static String m8156c(String str, String str2) {
        DateFormat simpleDateFormat = new SimpleDateFormat(f5329a);
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        try {
            instance2.setTime(simpleDateFormat.parse(str));
            instance.setTime(new Date());
            int a = C1173l.m8137a(instance.getTimeInMillis(), instance2.getTimeInMillis());
            if (a == 0) {
                a = C1173l.m8150b(instance.getTimeInMillis(), instance2.getTimeInMillis());
                if (a > 0) {
                    return "\u4eca\u5929" + C1173l.m8153b(str, f5336h);
                }
                if (a >= 0 && a == 0) {
                    a = C1173l.m8154c(instance.getTimeInMillis(), instance2.getTimeInMillis());
                    if (a > 0) {
                        return a + "\u5206\u949f\u524d";
                    }
                    if (a >= 0) {
                        return "\u521a\u521a";
                    }
                }
            } else if (a > 0) {
                if (a != 1 && a == 2) {
                }
            } else if (a < 0 && a != -1 && a == -2) {
            }
            String b = C1173l.m8153b(str, str2);
            return !C1184w.m8281b(b) ? b : str;
        } catch (Exception e) {
            return str;
        }
    }

    public static String m8157d(String str) {
        String str2 = null;
        try {
            Calendar gregorianCalendar = new GregorianCalendar();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
            gregorianCalendar.set(5, 1);
            str2 = simpleDateFormat.format(gregorianCalendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str2;
    }

    public static String m8158d(String str, String str2) {
        String str3 = "\u661f\u671f\u65e5";
        Calendar gregorianCalendar = new GregorianCalendar();
        try {
            gregorianCalendar.setTime(new SimpleDateFormat(str2).parse(str));
            switch (gregorianCalendar.get(7) - 1) {
                case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                    return "\u661f\u671f\u65e5";
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    return "\u661f\u671f\u4e00";
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    return "\u661f\u671f\u4e8c";
                case Type.BYTE /*3*/:
                    return "\u661f\u671f\u4e09";
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    return "\u661f\u671f\u56db";
                case Type.INT /*5*/:
                    return "\u661f\u671f\u4e94";
                case Type.FLOAT /*6*/:
                    return "\u661f\u671f\u516d";
                default:
                    return str3;
            }
        } catch (Exception e) {
            return "\u9519\u8bef";
        }
    }

    public static String m8159e(String str) {
        String str2 = null;
        try {
            Calendar gregorianCalendar = new GregorianCalendar();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
            gregorianCalendar.set(5, 1);
            gregorianCalendar.roll(5, -1);
            str2 = simpleDateFormat.format(gregorianCalendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str2;
    }

    public static String m8160e(String str, String str2) {
        return C1173l.m8147a(str, str2).getHours() >= 12 ? f5338j : f5337i;
    }

    public Date m8161a(Date date, int i, int i2) {
        Calendar gregorianCalendar = new GregorianCalendar();
        try {
            gregorianCalendar.setTime(date);
            gregorianCalendar.add(i, i2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gregorianCalendar.getTime();
    }
}
