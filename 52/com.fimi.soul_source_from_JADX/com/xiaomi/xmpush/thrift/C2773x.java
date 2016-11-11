package com.xiaomi.xmpush.thrift;

import com.xiaomi.channel.commonutils.logger.C2463b;
import org.p122a.p137b.C2478b;
import org.p122a.p137b.C3258g;
import org.p122a.p137b.C3272f;
import org.p122a.p137b.C3273h;
import org.p122a.p137b.p182b.C3252b;
import org.p122a.p137b.p182b.C3264o;

/* renamed from: com.xiaomi.xmpush.thrift.x */
public class C2773x {
    public static <T extends C2478b<T, ?>> void m15814a(T t, byte[] bArr) {
        if (bArr == null) {
            throw new C3258g("the message byte is empty.");
        }
        new C3272f(new C3264o(true, true, bArr.length)).m18102a(t, bArr);
    }

    public static <T extends C2478b<T, ?>> byte[] m15815a(T t) {
        byte[] bArr = null;
        if (t != null) {
            try {
                bArr = new C3273h(new C3252b()).m18103a(t);
            } catch (Throwable e) {
                C2463b.m14124a("convertThriftObjectToBytes catch TException.", e);
            }
        }
        return bArr;
    }
}
