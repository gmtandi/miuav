package it.p074a.p075a.p142c;

import com.tencent.connect.common.Constants;
import com.xiaomi.mistatistic.sdk.MiStatInterface;
import it.p074a.p075a.C2788s;
import it.p074a.p075a.C2804o;
import it.p074a.p075a.C2807r;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.p122a.p123a.p124f.p125c.C3022o;

/* renamed from: it.a.a.c.e */
public class C2793e implements C2788s {
    private static final Pattern f14240a;
    private static final DateFormat f14241b;

    static {
        f14240a = Pattern.compile("^([dl\\-])[r\\-][w\\-][xSs\\-][r\\-][w\\-][xSs\\-][r\\-][w\\-][xTt\\-]\\s+(?:\\d+\\s+)?\\S+\\s*\\S+\\s+(\\d+)\\s+(?:(\\w{3})\\s+(\\d{1,2}))\\s+(?:(\\d{4})|(?:(\\d{1,2}):(\\d{1,2})))\\s+([^\\\\*?\"<>|]+)(?: -> ([^\\\\*?\"<>|]+))?$");
        f14241b = new SimpleDateFormat("MMM dd yyyy HH:mm", Locale.US);
    }

    public C2804o[] m15966a(String[] strArr) {
        int length = strArr.length;
        if (length == 0) {
            return new C2804o[0];
        }
        if (strArr[0].startsWith("total")) {
            length--;
            String[] strArr2 = new String[length];
            for (int i = 0; i < length; i++) {
                strArr2[i] = strArr[i + 1];
            }
            strArr = strArr2;
        }
        Calendar instance = Calendar.getInstance();
        int i2 = instance.get(1);
        C2804o[] c2804oArr = new C2804o[length];
        int i3 = 0;
        while (i3 < length) {
            Matcher matcher = f14240a.matcher(strArr[i3]);
            if (matcher.matches()) {
                c2804oArr[i3] = new C2804o();
                String group = matcher.group(1);
                String group2 = matcher.group(2);
                String group3 = matcher.group(3);
                String group4 = matcher.group(4);
                String group5 = matcher.group(5);
                String group6 = matcher.group(6);
                String group7 = matcher.group(7);
                String group8 = matcher.group(8);
                String group9 = matcher.group(9);
                if (group.equals("-")) {
                    c2804oArr[i3].m15988a(0);
                } else {
                    if (group.equals("d")) {
                        c2804oArr[i3].m15988a(1);
                    } else {
                        if (group.equals("l")) {
                            c2804oArr[i3].m15988a(2);
                            c2804oArr[i3].m15993b(group9);
                        } else {
                            throw new C2807r();
                        }
                    }
                }
                try {
                    Object obj;
                    c2804oArr[i3].m15989a(Long.parseLong(group2));
                    if (group4.length() == 1) {
                        group4 = new StringBuffer().append(Constants.VIA_RESULT_SUCCESS).append(group4).toString();
                    }
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(group3);
                    stringBuffer.append(C3022o.f15055c);
                    stringBuffer.append(group4);
                    stringBuffer.append(C3022o.f15055c);
                    if (group5 == null) {
                        stringBuffer.append(i2);
                        obj = 1;
                    } else {
                        stringBuffer.append(group5);
                        obj = null;
                    }
                    stringBuffer.append(C3022o.f15055c);
                    if (group6 == null || group7 == null) {
                        stringBuffer.append("00:00");
                    } else {
                        if (group6.length() == 1) {
                            group6 = new StringBuffer().append(Constants.VIA_RESULT_SUCCESS).append(group6).toString();
                        }
                        if (group7.length() == 1) {
                            group7 = new StringBuffer().append(Constants.VIA_RESULT_SUCCESS).append(group7).toString();
                        }
                        stringBuffer.append(group6);
                        stringBuffer.append(':');
                        stringBuffer.append(group7);
                    }
                    try {
                        Date parse;
                        Date time;
                        synchronized (f14241b) {
                            parse = f14241b.parse(stringBuffer.toString());
                        }
                        if (obj != null) {
                            Calendar instance2 = Calendar.getInstance();
                            instance2.setTime(parse);
                            if (instance2.after(instance) && instance2.getTimeInMillis() - instance.getTimeInMillis() > MiStatInterface.MAX_UPLOAD_INTERVAL) {
                                instance2.set(1, i2 - 1);
                                time = instance2.getTime();
                                c2804oArr[i3].m15991a(time);
                                c2804oArr[i3].m15990a(group8);
                                i3++;
                            }
                        }
                        time = parse;
                        c2804oArr[i3].m15991a(time);
                        c2804oArr[i3].m15990a(group8);
                        i3++;
                    } catch (ParseException e) {
                        throw new C2807r();
                    }
                } catch (Throwable th) {
                    C2807r c2807r = new C2807r();
                }
            } else {
                throw new C2807r();
            }
        }
        return c2804oArr;
    }
}
