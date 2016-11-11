package org.p122a.p123a.p152c.p161f;

import com.tencent.mm.sdk.platformtools.Util;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.message.ParserCursor;
import org.apache.http.util.CharArrayBuffer;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.signature.SignatureVisitor;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2922b;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p154h.C3050e;
import org.p122a.p123a.p178k.C3210b;
import org.p122a.p123a.p180o.C3237d;

@C2912b
/* renamed from: org.a.a.c.f.l */
public class C2989l {
    public static final String f14939a = "application/x-www-form-urlencoded";
    private static final char f14940b = '&';
    private static final char f14941c = ';';
    private static final String f14942d = "=";
    private static final char[] f14943e;
    private static final String f14944f;
    private static final BitSet f14945g;
    private static final BitSet f14946h;
    private static final BitSet f14947i;
    private static final BitSet f14948j;
    private static final BitSet f14949k;
    private static final BitSet f14950l;
    private static final BitSet f14951m;
    private static final int f14952n = 16;

    static {
        int i;
        f14943e = new char[]{f14940b, f14941c};
        f14944f = "[" + new String(f14943e) + "]";
        f14945g = new BitSet(Opcodes.ACC_NATIVE);
        f14946h = new BitSet(Opcodes.ACC_NATIVE);
        f14947i = new BitSet(Opcodes.ACC_NATIVE);
        f14948j = new BitSet(Opcodes.ACC_NATIVE);
        f14949k = new BitSet(Opcodes.ACC_NATIVE);
        f14950l = new BitSet(Opcodes.ACC_NATIVE);
        f14951m = new BitSet(Opcodes.ACC_NATIVE);
        for (i = 97; i <= Opcodes.ISHR; i++) {
            f14945g.set(i);
        }
        for (i = 65; i <= 90; i++) {
            f14945g.set(i);
        }
        for (i = 48; i <= 57; i++) {
            f14945g.set(i);
        }
        f14945g.set(95);
        f14945g.set(45);
        f14945g.set(46);
        f14945g.set(42);
        f14951m.or(f14945g);
        f14945g.set(33);
        f14945g.set(Opcodes.IAND);
        f14945g.set(39);
        f14945g.set(40);
        f14945g.set(41);
        f14946h.set(44);
        f14946h.set(59);
        f14946h.set(58);
        f14946h.set(36);
        f14946h.set(38);
        f14946h.set(43);
        f14946h.set(61);
        f14947i.or(f14945g);
        f14947i.or(f14946h);
        f14948j.or(f14945g);
        f14948j.set(47);
        f14948j.set(59);
        f14948j.set(58);
        f14948j.set(64);
        f14948j.set(38);
        f14948j.set(61);
        f14948j.set(43);
        f14948j.set(36);
        f14948j.set(44);
        f14950l.set(59);
        f14950l.set(47);
        f14950l.set(63);
        f14950l.set(58);
        f14950l.set(64);
        f14950l.set(38);
        f14950l.set(61);
        f14950l.set(43);
        f14950l.set(36);
        f14950l.set(44);
        f14950l.set(91);
        f14950l.set(93);
        f14949k.or(f14950l);
        f14949k.or(f14945g);
    }

    public static String m16977a(Iterable<? extends NameValuePair> iterable, char c, Charset charset) {
        StringBuilder stringBuilder = new StringBuilder();
        for (NameValuePair nameValuePair : iterable) {
            String f = C2989l.m16996f(nameValuePair.getName(), charset);
            String f2 = C2989l.m16996f(nameValuePair.getValue(), charset);
            if (stringBuilder.length() > 0) {
                stringBuilder.append(c);
            }
            stringBuilder.append(f);
            if (f2 != null) {
                stringBuilder.append(f14942d);
                stringBuilder.append(f2);
            }
        }
        return stringBuilder.toString();
    }

    public static String m16978a(Iterable<? extends NameValuePair> iterable, Charset charset) {
        return C2989l.m16977a((Iterable) iterable, (char) f14940b, charset);
    }

    private static String m16979a(String str, String str2) {
        if (str == null) {
            return null;
        }
        return C2989l.m16981a(str, str2 != null ? Charset.forName(str2) : C2922b.f14781e, true);
    }

    private static String m16980a(String str, Charset charset, BitSet bitSet, boolean z) {
        if (str == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        ByteBuffer encode = charset.encode(str);
        while (encode.hasRemaining()) {
            int i = encode.get() & Util.MASK_8BIT;
            if (bitSet.get(i)) {
                stringBuilder.append((char) i);
            } else if (z && i == 32) {
                stringBuilder.append(SignatureVisitor.EXTENDS);
            } else {
                stringBuilder.append("%");
                char toUpperCase = Character.toUpperCase(Character.forDigit((i >> 4) & 15, f14952n));
                char toUpperCase2 = Character.toUpperCase(Character.forDigit(i & 15, f14952n));
                stringBuilder.append(toUpperCase);
                stringBuilder.append(toUpperCase2);
            }
        }
        return stringBuilder.toString();
    }

    private static String m16981a(String str, Charset charset, boolean z) {
        if (str == null) {
            return null;
        }
        ByteBuffer allocate = ByteBuffer.allocate(str.length());
        CharBuffer wrap = CharBuffer.wrap(str);
        while (wrap.hasRemaining()) {
            char c = wrap.get();
            if (c == '%' && wrap.remaining() >= 2) {
                c = wrap.get();
                char c2 = wrap.get();
                int digit = Character.digit(c, f14952n);
                int digit2 = Character.digit(c2, f14952n);
                if (digit == -1 || digit2 == -1) {
                    allocate.put((byte) 37);
                    allocate.put((byte) c);
                    allocate.put((byte) c2);
                } else {
                    allocate.put((byte) ((digit << 4) + digit2));
                }
            } else if (z && c == SignatureVisitor.EXTENDS) {
                allocate.put(SmileConstants.TOKEN_LITERAL_EMPTY_STRING);
            } else {
                allocate.put((byte) c);
            }
        }
        allocate.flip();
        return charset.decode(allocate).toString();
    }

    public static String m16982a(List<? extends NameValuePair> list, char c, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (NameValuePair nameValuePair : list) {
            String b = C2989l.m16990b(nameValuePair.getName(), str);
            String b2 = C2989l.m16990b(nameValuePair.getValue(), str);
            if (stringBuilder.length() > 0) {
                stringBuilder.append(c);
            }
            stringBuilder.append(b);
            if (b2 != null) {
                stringBuilder.append(f14942d);
                stringBuilder.append(b2);
            }
        }
        return stringBuilder.toString();
    }

    public static String m16983a(List<? extends NameValuePair> list, String str) {
        return C2989l.m16982a((List) list, (char) f14940b, str);
    }

    public static List<NameValuePair> m16984a(String str, Charset charset) {
        return C2989l.m16985a(str, charset, f14943e);
    }

    public static List<NameValuePair> m16985a(String str, Charset charset, char... cArr) {
        if (str == null) {
            return Collections.emptyList();
        }
        C3210b c3210b = C3210b.f15659b;
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(str.length());
        charArrayBuffer.append(str);
        ParserCursor parserCursor = new ParserCursor(0, charArrayBuffer.length());
        List<NameValuePair> arrayList = new ArrayList();
        while (!parserCursor.atEnd()) {
            NameValuePair a = c3210b.m17807a(charArrayBuffer, parserCursor, cArr);
            if (a.getName().length() > 0) {
                arrayList.add(new BasicNameValuePair(C2989l.m16995e(a.getName(), charset), C2989l.m16995e(a.getValue(), charset)));
            }
        }
        return arrayList;
    }

    public static List<NameValuePair> m16986a(URI uri, String str) {
        String rawQuery = uri.getRawQuery();
        if (rawQuery == null || rawQuery.length() <= 0) {
            return Collections.emptyList();
        }
        List arrayList = new ArrayList();
        C2989l.m16989a(arrayList, new Scanner(rawQuery), f14944f, str);
        return arrayList;
    }

    public static List<NameValuePair> m16987a(HttpEntity httpEntity) {
        C3050e a = C3050e.m17159a(httpEntity);
        if (a != null && a.m17164a().equalsIgnoreCase(f14939a)) {
            String a2 = C3237d.m17901a(httpEntity, C2922b.f14782f);
            if (a2 != null && a2.length() > 0) {
                Charset b = a.m17167b();
                if (b == null) {
                    b = Charset.forName("ISO-8859-1");
                }
                return C2989l.m16985a(a2, b, f14943e);
            }
        }
        return Collections.emptyList();
    }

    public static void m16988a(List<NameValuePair> list, Scanner scanner, String str) {
        C2989l.m16989a((List) list, scanner, f14944f, str);
    }

    public static void m16989a(List<NameValuePair> list, Scanner scanner, String str, String str2) {
        scanner.useDelimiter(str);
        while (scanner.hasNext()) {
            String a;
            String str3 = null;
            String next = scanner.next();
            int indexOf = next.indexOf(f14942d);
            if (indexOf != -1) {
                a = C2989l.m16979a(next.substring(0, indexOf).trim(), str2);
                str3 = C2989l.m16979a(next.substring(indexOf + 1).trim(), str2);
            } else {
                a = C2989l.m16979a(next.trim(), str2);
            }
            list.add(new BasicNameValuePair(a, str3));
        }
    }

    private static String m16990b(String str, String str2) {
        if (str == null) {
            return null;
        }
        return C2989l.m16980a(str, str2 != null ? Charset.forName(str2) : C2922b.f14781e, f14951m, true);
    }

    static String m16991b(String str, Charset charset) {
        return C2989l.m16980a(str, charset, f14947i, false);
    }

    public static boolean m16992b(HttpEntity httpEntity) {
        Header contentType = httpEntity.getContentType();
        if (contentType == null) {
            return false;
        }
        HeaderElement[] elements = contentType.getElements();
        return elements.length > 0 ? elements[0].getName().equalsIgnoreCase(f14939a) : false;
    }

    static String m16993c(String str, Charset charset) {
        return C2989l.m16980a(str, charset, f14949k, false);
    }

    static String m16994d(String str, Charset charset) {
        return C2989l.m16980a(str, charset, f14948j, false);
    }

    private static String m16995e(String str, Charset charset) {
        if (str == null) {
            return null;
        }
        if (charset == null) {
            charset = C2922b.f14781e;
        }
        return C2989l.m16981a(str, charset, true);
    }

    private static String m16996f(String str, Charset charset) {
        if (str == null) {
            return null;
        }
        if (charset == null) {
            charset = C2922b.f14781e;
        }
        return C2989l.m16980a(str, charset, f14951m, true);
    }
}
