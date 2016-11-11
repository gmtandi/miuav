package org.p122a.p123a.p180o;

/* renamed from: org.a.a.o.f */
public final class C3239f {
    public static boolean m17910a(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    public static boolean m17911b(CharSequence charSequence) {
        if (charSequence == null) {
            return true;
        }
        for (int i = 0; i < charSequence.length(); i++) {
            if (!Character.isWhitespace(charSequence.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
