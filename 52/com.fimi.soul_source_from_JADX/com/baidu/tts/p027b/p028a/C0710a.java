package com.baidu.tts.p027b.p028a;

import com.baidu.tts.p027b.p028a.p031a.C0700d;
import com.baidu.tts.p027b.p028a.p031a.C0705c;
import com.baidu.tts.p027b.p028a.p032b.C0711b;
import com.baidu.tts.p027b.p028a.p032b.C0717d;
import com.baidu.tts.p027b.p028a.p032b.C0723e;
import com.baidu.tts.p027b.p028a.p032b.C0727f;
import com.baidu.tts.p041e.C0793f;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.baidu.tts.b.a.a */
public class C0710a {
    private static volatile C0710a f4103a;

    /* renamed from: com.baidu.tts.b.a.a.1 */
    /* synthetic */ class C06981 {
        static final /* synthetic */ int[] f4088a;

        static {
            f4088a = new int[C0793f.values().length];
            try {
                f4088a[C0793f.ONLINE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f4088a[C0793f.OFFLINE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f4088a[C0793f.MIX.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    static {
        f4103a = null;
    }

    private C0710a() {
    }

    private C0700d m6248a(C0711b c0711b) {
        C0700d c0705c = new C0705c();
        c0705c.m6162a(c0711b);
        return c0705c;
    }

    public static C0710a m6249a() {
        if (f4103a == null) {
            synchronized (C0710a.class) {
                if (f4103a == null) {
                    f4103a = new C0710a();
                }
            }
        }
        return f4103a;
    }

    private C0700d m6250b() {
        return m6248a(new C0727f());
    }

    private C0700d m6251c() {
        return m6248a(new C0723e());
    }

    private C0700d m6252d() {
        return m6248a(new C0717d());
    }

    public C0700d m6253a(C0793f c0793f) {
        switch (C06981.f4088a[c0793f.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return m6250b();
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return m6251c();
            case Type.BYTE /*3*/:
                return m6252d();
            default:
                return null;
        }
    }
}
