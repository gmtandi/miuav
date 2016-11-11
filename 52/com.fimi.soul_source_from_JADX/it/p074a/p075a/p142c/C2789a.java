package it.p074a.p075a.p142c;

import it.p074a.p075a.C2788s;
import it.p074a.p075a.C2804o;
import it.p074a.p075a.C2807r;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.codehaus.jackson.util.MinimalPrettyPrinter;

/* renamed from: it.a.a.c.a */
public class C2789a implements C2788s {
    private static final Pattern f14234a;
    private static final DateFormat f14235b;

    static {
        f14234a = Pattern.compile("^(\\d{2})-(\\d{2})-(\\d{2})\\s+(\\d{2}):(\\d{2})(AM|PM)\\s+(<DIR>|\\d+)\\s+([^\\\\/*?\"<>|]+)$");
        f14235b = new SimpleDateFormat("MM/dd/yy hh:mm a");
    }

    public C2804o[] m15960a(String[] strArr) {
        int length = strArr.length;
        C2804o[] c2804oArr = new C2804o[length];
        int i = 0;
        while (i < length) {
            Matcher matcher = f14234a.matcher(strArr[i]);
            if (matcher.matches()) {
                String group = matcher.group(1);
                String group2 = matcher.group(2);
                String group3 = matcher.group(3);
                String group4 = matcher.group(4);
                String group5 = matcher.group(5);
                String group6 = matcher.group(6);
                String group7 = matcher.group(7);
                String group8 = matcher.group(8);
                c2804oArr[i] = new C2804o();
                c2804oArr[i].m15990a(group8);
                if (group7.equalsIgnoreCase("<DIR>")) {
                    c2804oArr[i].m15988a(1);
                    c2804oArr[i].m15989a(0);
                } else {
                    try {
                        long parseLong = Long.parseLong(group7);
                        c2804oArr[i].m15988a(0);
                        c2804oArr[i].m15989a(parseLong);
                    } catch (Throwable th) {
                        C2807r c2807r = new C2807r();
                    }
                }
                group8 = new StringBuffer().append(group).append("/").append(group2).append("/").append(group3).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(group4).append(":").append(group5).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(group6).toString();
                try {
                    Date parse;
                    synchronized (f14235b) {
                        parse = f14235b.parse(group8);
                    }
                    c2804oArr[i].m15991a(parse);
                    i++;
                } catch (ParseException e) {
                    throw new C2807r();
                }
            }
            throw new C2807r();
        }
        return c2804oArr;
    }
}
