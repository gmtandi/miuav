package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.string.C2473a;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.util.MinimalPrettyPrinter;

/* renamed from: com.xiaomi.push.service.z */
public class C2673z {
    private static int f13229a;
    private byte[] f13230b;
    private int f13231c;
    private int f13232d;
    private int f13233e;

    static {
        f13229a = 8;
    }

    public C2673z() {
        this.f13233e = -666;
        this.f13230b = new byte[Opcodes.ACC_NATIVE];
        this.f13232d = 0;
        this.f13231c = 0;
    }

    public static int m15123a(byte b) {
        return b >= null ? b : b + Opcodes.ACC_NATIVE;
    }

    public static String m15124a(byte[] bArr, String str) {
        return String.valueOf(C2473a.m14158a(C2673z.m15129a(bArr, str.getBytes())));
    }

    private void m15125a(int i, byte[] bArr, boolean z) {
        int i2 = 0;
        int length = bArr.length;
        for (int i3 = 0; i3 < Opcodes.ACC_NATIVE; i3++) {
            this.f13230b[i3] = (byte) i3;
        }
        this.f13232d = 0;
        this.f13231c = 0;
        while (this.f13231c < i) {
            this.f13232d = ((this.f13232d + C2673z.m15123a(this.f13230b[this.f13231c])) + C2673z.m15123a(bArr[this.f13231c % length])) % Opcodes.ACC_NATIVE;
            C2673z.m15127a(this.f13230b, this.f13231c, this.f13232d);
            this.f13231c++;
        }
        if (i != Opcodes.ACC_NATIVE) {
            this.f13233e = ((this.f13232d + C2673z.m15123a(this.f13230b[i])) + C2673z.m15123a(bArr[i % length])) % Opcodes.ACC_NATIVE;
        }
        if (z) {
            System.out.print("S_" + (i - 1) + ":");
            while (i2 <= i) {
                System.out.print(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + C2673z.m15123a(this.f13230b[i2]));
                i2++;
            }
            System.out.print("   j_" + (i - 1) + "=" + this.f13232d);
            System.out.print("   j_" + i + "=" + this.f13233e);
            System.out.print("   S_" + (i - 1) + "[j_" + (i - 1) + "]=" + C2673z.m15123a(this.f13230b[this.f13232d]));
            System.out.print("   S_" + (i - 1) + "[j_" + i + "]=" + C2673z.m15123a(this.f13230b[this.f13233e]));
            if (this.f13230b[1] != null) {
                System.out.print("   S[1]!=0");
            }
            System.out.println();
        }
    }

    private void m15126a(byte[] bArr) {
        m15125a((int) Opcodes.ACC_NATIVE, bArr, false);
    }

    private static void m15127a(byte[] bArr, int i, int i2) {
        byte b = bArr[i];
        bArr[i] = bArr[i2];
        bArr[i2] = b;
    }

    public static byte[] m15128a(String str, String str2) {
        int i = 0;
        byte[] a = C2473a.m14155a(str);
        byte[] bytes = str2.getBytes();
        byte[] bArr = new byte[((a.length + 1) + bytes.length)];
        for (int i2 = 0; i2 < a.length; i2++) {
            bArr[i2] = a[i2];
        }
        bArr[a.length] = (byte) 95;
        while (i < bytes.length) {
            bArr[(a.length + 1) + i] = bytes[i];
            i++;
        }
        return bArr;
    }

    public static byte[] m15129a(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr2.length];
        C2673z c2673z = new C2673z();
        c2673z.m15126a(bArr);
        c2673z.m15130b();
        for (int i = 0; i < bArr2.length; i++) {
            bArr3[i] = (byte) (bArr2[i] ^ c2673z.m15132a());
        }
        return bArr3;
    }

    private void m15130b() {
        this.f13232d = 0;
        this.f13231c = 0;
    }

    public static byte[] m15131b(byte[] bArr, String str) {
        return C2673z.m15129a(bArr, C2473a.m14155a(str));
    }

    byte m15132a() {
        this.f13231c = (this.f13231c + 1) % Opcodes.ACC_NATIVE;
        this.f13232d = (this.f13232d + C2673z.m15123a(this.f13230b[this.f13231c])) % Opcodes.ACC_NATIVE;
        C2673z.m15127a(this.f13230b, this.f13231c, this.f13232d);
        return this.f13230b[(C2673z.m15123a(this.f13230b[this.f13231c]) + C2673z.m15123a(this.f13230b[this.f13232d])) % Opcodes.ACC_NATIVE];
    }
}
