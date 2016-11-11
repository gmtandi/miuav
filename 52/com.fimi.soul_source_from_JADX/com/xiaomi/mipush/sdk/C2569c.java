package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.channel.commonutils.string.C2473a;
import com.xiaomi.xmpush.thrift.C2729a;
import com.xiaomi.xmpush.thrift.C2740g;
import com.xiaomi.xmpush.thrift.C2742h;
import com.xiaomi.xmpush.thrift.C2746j;
import com.xiaomi.xmpush.thrift.C2748k;
import com.xiaomi.xmpush.thrift.C2750l;
import com.xiaomi.xmpush.thrift.C2754n;
import com.xiaomi.xmpush.thrift.C2758p;
import com.xiaomi.xmpush.thrift.C2760q;
import com.xiaomi.xmpush.thrift.C2764s;
import com.xiaomi.xmpush.thrift.C2768u;
import com.xiaomi.xmpush.thrift.C2772w;
import com.xiaomi.xmpush.thrift.C2773x;
import java.nio.ByteBuffer;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p137b.C2478b;
import org.p122a.p137b.C3258g;

/* renamed from: com.xiaomi.mipush.sdk.c */
public class C2569c {
    private static final byte[] f12877a;

    /* renamed from: com.xiaomi.mipush.sdk.c.1 */
    /* synthetic */ class C25681 {
        static final /* synthetic */ int[] f12876a;

        static {
            f12876a = new int[C2729a.values().length];
            try {
                f12876a[C2729a.Registration.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f12876a[C2729a.UnRegistration.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f12876a[C2729a.Subscription.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f12876a[C2729a.UnSubscription.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f12876a[C2729a.SendMessage.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f12876a[C2729a.AckMessage.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f12876a[C2729a.SetConfig.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f12876a[C2729a.ReportFeedback.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                f12876a[C2729a.Notification.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                f12876a[C2729a.Command.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
        }
    }

    static {
        f12877a = new byte[]{(byte) 100, (byte) 23, (byte) 84, (byte) 114, (byte) 72, (byte) 0, (byte) 4, (byte) 97, (byte) 73, (byte) 97, (byte) 2, SmileConstants.TOKEN_KEY_LONG_STRING, (byte) 84, (byte) 102, (byte) 18, SmileConstants.TOKEN_LITERAL_EMPTY_STRING};
    }

    protected static <T extends C2478b<T, ?>> C2748k m14638a(Context context, T t, C2729a c2729a) {
        return C2569c.m14639a(context, t, c2729a, !c2729a.equals(C2729a.Registration));
    }

    protected static <T extends C2478b<T, ?>> C2748k m14639a(Context context, T t, C2729a c2729a, boolean z) {
        byte[] a = C2773x.m15815a(t);
        if (a == null) {
            C2463b.m14123a("invoke convertThriftObjectToBytes method, return null.");
            return null;
        }
        C2748k c2748k = new C2748k();
        if (z) {
            byte[] a2 = C2473a.m14155a(C2566a.m14615a(context).m14629f());
            C2463b.m14126b(Arrays.toString(a2));
            try {
                a = C2569c.m14644b(a2, a);
            } catch (Exception e) {
                C2463b.m14127c("encryption error. ");
            }
        }
        C2740g c2740g = new C2740g();
        c2740g.f13605a = 5;
        c2740g.f13606b = "fakeid";
        c2748k.m15564a(c2740g);
        c2748k.m15566a(ByteBuffer.wrap(a));
        c2748k.m15562a(c2729a);
        c2748k.m15575c(true);
        c2748k.m15571b(context.getPackageName());
        c2748k.m15567a(z);
        c2748k.m15565a(C2566a.m14615a(context).m14626c());
        return c2748k;
    }

    private static Cipher m14640a(byte[] bArr, int i) {
        Key secretKeySpec = new SecretKeySpec(bArr, "AES");
        AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(f12877a);
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(i, secretKeySpec, ivParameterSpec);
        return instance;
    }

    protected static C2478b m14641a(Context context, C2748k c2748k) {
        if (c2748k.m15576c()) {
            try {
                byte[] a = C2569c.m14643a(C2473a.m14155a(C2566a.m14615a(context).m14629f()), c2748k.m15580f());
            } catch (Throwable e) {
                throw new C3258g("the aes decrypt failed.", e);
            }
        }
        a = c2748k.m15580f();
        C2478b a2 = C2569c.m14642a(c2748k.m15561a());
        if (a2 != null) {
            C2773x.m15814a(a2, a);
        }
        return a2;
    }

    private static C2478b m14642a(C2729a c2729a) {
        switch (C25681.f12876a[c2729a.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return new C2754n();
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return new C2768u();
            case Type.BYTE /*3*/:
                return new C2764s();
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                return new C2772w();
            case Type.INT /*5*/:
                return new C2760q();
            case Type.FLOAT /*6*/:
                return new C2742h();
            case Type.LONG /*7*/:
                return new C2746j();
            case Type.DOUBLE /*8*/:
                return new C2758p();
            case Type.ARRAY /*9*/:
                return new C2750l();
            case Type.OBJECT /*10*/:
                return new C2746j();
            default:
                return null;
        }
    }

    public static byte[] m14643a(byte[] bArr, byte[] bArr2) {
        return C2569c.m14640a(bArr, 2).doFinal(bArr2);
    }

    public static byte[] m14644b(byte[] bArr, byte[] bArr2) {
        return C2569c.m14640a(bArr, 1).doFinal(bArr2);
    }
}
