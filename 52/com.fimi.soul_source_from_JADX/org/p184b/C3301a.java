package org.p184b;

import com.fimi.soul.module.setting.newhand.C1873o;
import java.util.Arrays;
import java.util.Iterator;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.p122a.p123a.p124f.p125c.C3022o;
import org.p184b.p186b.C3302a;
import org.p184b.p186b.C3305d;

/* renamed from: org.b.a */
public abstract class C3301a implements C3300k {
    private <T> C3300k m18227a(String str, String str2, String str3, Iterator<T> it) {
        return m18229b(str, str2, str3, new C3305d(it));
    }

    private String m18228b(Object obj) {
        try {
            return String.valueOf(obj);
        } catch (Exception e) {
            return obj.getClass().getName() + "@" + Integer.toHexString(obj.hashCode());
        }
    }

    private C3300k m18229b(String str, String str2, String str3, Iterator<? extends C3274r> it) {
        Object obj = null;
        m18239b(str);
        while (it.hasNext()) {
            if (obj != null) {
                m18239b(str2);
            }
            m18236a((C3274r) it.next());
            obj = 1;
        }
        m18239b(str3);
        return this;
    }

    private void m18230b(char c) {
        switch (c) {
            case Type.ARRAY /*9*/:
                m18239b("\\t");
            case Type.OBJECT /*10*/:
                m18239b("\\n");
            case Opcodes.FCONST_2 /*13*/:
                m18239b("\\r");
            case C1873o.f9538Z /*34*/:
                m18239b("\\\"");
            default:
                m18237a(c);
        }
    }

    private void m18231c(String str) {
        m18237a((char) C3022o.f15057e);
        for (int i = 0; i < str.length(); i++) {
            m18230b(str.charAt(i));
        }
        m18237a((char) C3022o.f15057e);
    }

    public C3300k m18232a(Object obj) {
        if (obj == null) {
            m18239b("null");
        } else if (obj instanceof String) {
            m18231c((String) obj);
        } else if (obj instanceof Character) {
            m18237a((char) C3022o.f15057e);
            m18230b(((Character) obj).charValue());
            m18237a((char) C3022o.f15057e);
        } else if (obj instanceof Short) {
            m18237a('<');
            m18239b(m18228b(obj));
            m18239b("s>");
        } else if (obj instanceof Long) {
            m18237a('<');
            m18239b(m18228b(obj));
            m18239b("L>");
        } else if (obj instanceof Float) {
            m18237a('<');
            m18239b(m18228b(obj));
            m18239b("F>");
        } else if (obj.getClass().isArray()) {
            m18227a("[", ", ", "]", new C3302a(obj));
        } else {
            m18237a('<');
            m18239b(m18228b(obj));
            m18237a('>');
        }
        return this;
    }

    public C3300k m18233a(String str) {
        m18239b(str);
        return this;
    }

    public <T> C3300k m18234a(String str, String str2, String str3, Iterable<T> iterable) {
        return m18227a(str, str2, str3, iterable.iterator());
    }

    public <T> C3300k m18235a(String str, String str2, String str3, T... tArr) {
        return m18234a(str, str2, str3, Arrays.asList(tArr));
    }

    public C3300k m18236a(C3274r c3274r) {
        c3274r.m18104a(this);
        return this;
    }

    protected abstract void m18237a(char c);

    public C3300k m18238b(String str, String str2, String str3, Iterable<? extends C3274r> iterable) {
        return m18229b(str, str2, str3, iterable.iterator());
    }

    protected void m18239b(String str) {
        for (int i = 0; i < str.length(); i++) {
            m18237a(str.charAt(i));
        }
    }
}
