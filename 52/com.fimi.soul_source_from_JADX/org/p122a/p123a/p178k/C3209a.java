package org.p122a.p123a.p178k;

import org.apache.http.HeaderElement;
import org.apache.http.NameValuePair;
import org.apache.http.message.HeaderValueFormatter;
import org.apache.http.util.CharArrayBuffer;
import org.codehaus.jackson.org.objectweb.asm.signature.SignatureVisitor;
import org.p122a.p123a.p124f.p125c.C3022o;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p180o.C3234a;

@C2912b
/* renamed from: org.a.a.k.a */
public class C3209a implements HeaderValueFormatter {
    @Deprecated
    public static final C3209a f15654a;
    public static final C3209a f15655b;
    public static final String f15656c = " ;,:@()<>\\\"/[]?={}\t";
    public static final String f15657d = "\"\\";

    static {
        f15654a = new C3209a();
        f15655b = new C3209a();
    }

    public static String m17789a(HeaderElement headerElement, boolean z, HeaderValueFormatter headerValueFormatter) {
        if (headerValueFormatter == null) {
            headerValueFormatter = f15655b;
        }
        return headerValueFormatter.formatHeaderElement(null, headerElement, z).toString();
    }

    public static String m17790a(NameValuePair nameValuePair, boolean z, HeaderValueFormatter headerValueFormatter) {
        if (headerValueFormatter == null) {
            headerValueFormatter = f15655b;
        }
        return headerValueFormatter.formatNameValuePair(null, nameValuePair, z).toString();
    }

    public static String m17791a(HeaderElement[] headerElementArr, boolean z, HeaderValueFormatter headerValueFormatter) {
        if (headerValueFormatter == null) {
            headerValueFormatter = f15655b;
        }
        return headerValueFormatter.formatElements(null, headerElementArr, z).toString();
    }

    public static String m17792a(NameValuePair[] nameValuePairArr, boolean z, HeaderValueFormatter headerValueFormatter) {
        if (headerValueFormatter == null) {
            headerValueFormatter = f15655b;
        }
        return headerValueFormatter.formatParameters(null, nameValuePairArr, z).toString();
    }

    protected int m17793a(HeaderElement headerElement) {
        int i = 0;
        if (headerElement == null) {
            return 0;
        }
        int length = headerElement.getName().length();
        String value = headerElement.getValue();
        if (value != null) {
            length += value.length() + 3;
        }
        int parameterCount = headerElement.getParameterCount();
        if (parameterCount <= 0) {
            return length;
        }
        while (i < parameterCount) {
            length += m17794a(headerElement.getParameter(i)) + 2;
            i++;
        }
        return length;
    }

    protected int m17794a(NameValuePair nameValuePair) {
        if (nameValuePair == null) {
            return 0;
        }
        int length = nameValuePair.getName().length();
        String value = nameValuePair.getValue();
        return value != null ? length + (value.length() + 3) : length;
    }

    protected int m17795a(HeaderElement[] headerElementArr) {
        int i = 0;
        if (headerElementArr != null && headerElementArr.length >= 1) {
            int length = (headerElementArr.length - 1) * 2;
            i = length;
            length = 0;
            while (length < headerElementArr.length) {
                int a = m17793a(headerElementArr[length]) + i;
                length++;
                i = a;
            }
        }
        return i;
    }

    protected int m17796a(NameValuePair[] nameValuePairArr) {
        int i = 0;
        if (nameValuePairArr != null && nameValuePairArr.length >= 1) {
            int length = (nameValuePairArr.length - 1) * 2;
            i = length;
            length = 0;
            while (length < nameValuePairArr.length) {
                int a = m17794a(nameValuePairArr[length]) + i;
                length++;
                i = a;
            }
        }
        return i;
    }

    protected void m17797a(CharArrayBuffer charArrayBuffer, String str, boolean z) {
        int i = 0;
        if (!z) {
            for (int i2 = 0; i2 < str.length() && !r7; i2++) {
                z = m17798a(str.charAt(i2));
            }
        }
        if (z) {
            charArrayBuffer.append(C3022o.f15057e);
        }
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (m17799b(charAt)) {
                charArrayBuffer.append(C3022o.f15058f);
            }
            charArrayBuffer.append(charAt);
            i++;
        }
        if (z) {
            charArrayBuffer.append(C3022o.f15057e);
        }
    }

    protected boolean m17798a(char c) {
        return f15656c.indexOf(c) >= 0;
    }

    protected boolean m17799b(char c) {
        return f15657d.indexOf(c) >= 0;
    }

    public CharArrayBuffer formatElements(CharArrayBuffer charArrayBuffer, HeaderElement[] headerElementArr, boolean z) {
        C3234a.m17886a((Object) headerElementArr, "Header element array");
        int a = m17795a(headerElementArr);
        if (charArrayBuffer == null) {
            charArrayBuffer = new CharArrayBuffer(a);
        } else {
            charArrayBuffer.ensureCapacity(a);
        }
        for (a = 0; a < headerElementArr.length; a++) {
            if (a > 0) {
                charArrayBuffer.append(", ");
            }
            formatHeaderElement(charArrayBuffer, headerElementArr[a], z);
        }
        return charArrayBuffer;
    }

    public CharArrayBuffer formatHeaderElement(CharArrayBuffer charArrayBuffer, HeaderElement headerElement, boolean z) {
        C3234a.m17886a((Object) headerElement, "Header element");
        int a = m17793a(headerElement);
        if (charArrayBuffer == null) {
            charArrayBuffer = new CharArrayBuffer(a);
        } else {
            charArrayBuffer.ensureCapacity(a);
        }
        charArrayBuffer.append(headerElement.getName());
        String value = headerElement.getValue();
        if (value != null) {
            charArrayBuffer.append(SignatureVisitor.INSTANCEOF);
            m17797a(charArrayBuffer, value, z);
        }
        int parameterCount = headerElement.getParameterCount();
        if (parameterCount > 0) {
            for (a = 0; a < parameterCount; a++) {
                charArrayBuffer.append("; ");
                formatNameValuePair(charArrayBuffer, headerElement.getParameter(a), z);
            }
        }
        return charArrayBuffer;
    }

    public CharArrayBuffer formatNameValuePair(CharArrayBuffer charArrayBuffer, NameValuePair nameValuePair, boolean z) {
        C3234a.m17886a((Object) nameValuePair, "Name / value pair");
        int a = m17794a(nameValuePair);
        if (charArrayBuffer == null) {
            charArrayBuffer = new CharArrayBuffer(a);
        } else {
            charArrayBuffer.ensureCapacity(a);
        }
        charArrayBuffer.append(nameValuePair.getName());
        String value = nameValuePair.getValue();
        if (value != null) {
            charArrayBuffer.append(SignatureVisitor.INSTANCEOF);
            m17797a(charArrayBuffer, value, z);
        }
        return charArrayBuffer;
    }

    public CharArrayBuffer formatParameters(CharArrayBuffer charArrayBuffer, NameValuePair[] nameValuePairArr, boolean z) {
        C3234a.m17886a((Object) nameValuePairArr, "Header parameter array");
        int a = m17796a(nameValuePairArr);
        if (charArrayBuffer == null) {
            charArrayBuffer = new CharArrayBuffer(a);
        } else {
            charArrayBuffer.ensureCapacity(a);
        }
        for (a = 0; a < nameValuePairArr.length; a++) {
            if (a > 0) {
                charArrayBuffer.append("; ");
            }
            formatNameValuePair(charArrayBuffer, nameValuePairArr[a], z);
        }
        return charArrayBuffer;
    }
}
