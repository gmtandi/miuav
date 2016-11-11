package it.p074a.p075a.p142c;

import com.xiaomi.mipush.sdk.MiPushClient;
import it.p074a.p075a.C2788s;
import it.p074a.p075a.C2804o;
import it.p074a.p075a.C2807r;
import java.util.Date;
import java.util.StringTokenizer;
import org.codehaus.jackson.org.objectweb.asm.signature.SignatureVisitor;

/* renamed from: it.a.a.c.b */
public class C2790b implements C2788s {
    public static void m15961b(String[] strArr) {
        int i = 0;
        C2804o[] a = new C2790b().m15962a(new String[]{"+i8388621.29609,m824255902,/,\tdev", "+i8388621.44468,m839956783,r,s10376,\tRFCEPLF"});
        while (i < a.length) {
            System.out.println(a[i]);
            i++;
        }
    }

    public C2804o[] m15962a(String[] strArr) {
        int length = strArr.length;
        C2804o[] c2804oArr = null;
        for (int i = 0; i < length; i++) {
            String str = strArr[i];
            if (str.charAt(0) != SignatureVisitor.EXTENDS) {
                throw new C2807r();
            }
            int indexOf = str.indexOf(9);
            if (indexOf == -1) {
                throw new C2807r();
            }
            String substring = str.substring(1, indexOf);
            String substring2 = str.substring(indexOf + 1, str.length());
            Date date = null;
            Object obj = null;
            long j = 0;
            StringTokenizer stringTokenizer = new StringTokenizer(substring, MiPushClient.ACCEPT_TIME_SEPARATOR);
            while (stringTokenizer.hasMoreTokens()) {
                substring = stringTokenizer.nextToken();
                int length2 = substring.length();
                if (length2 > 0) {
                    if (length2 != 1) {
                        char charAt = substring.charAt(0);
                        substring = substring.substring(1, length2);
                        if (charAt == 's') {
                            try {
                                j = Long.parseLong(substring);
                            } catch (Throwable th) {
                            }
                        } else if (charAt == 'm') {
                            try {
                                date = new Date(Long.parseLong(substring) * 1000);
                            } catch (Throwable th2) {
                            }
                        }
                    } else if (substring.equals("/")) {
                        obj = 1;
                    }
                }
            }
            if (c2804oArr == null) {
                c2804oArr = new C2804o[length];
            }
            c2804oArr[i] = new C2804o();
            c2804oArr[i].m15990a(substring2);
            c2804oArr[i].m15991a(date);
            c2804oArr[i].m15989a(j);
            c2804oArr[i].m15988a(obj != null ? 1 : 0);
        }
        return c2804oArr;
    }
}
