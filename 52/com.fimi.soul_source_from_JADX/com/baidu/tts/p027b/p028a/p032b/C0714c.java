package com.baidu.tts.p027b.p028a.p032b;

import com.baidu.tts.p034l.C0824b;
import com.baidu.tts.p041e.C0797j;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.baidu.tts.b.a.b.c */
public class C0714c {
    private C0824b f4106a;
    private C0797j f4107b;

    /* renamed from: com.baidu.tts.b.a.b.c.1 */
    /* synthetic */ class C07131 {
        static final /* synthetic */ int[] f4105a;

        static {
            f4105a = new int[C0797j.values().length];
            try {
                f4105a[C0797j.DEFAULT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f4105a[C0797j.HIGH_SPEED_SYNTHESIZE_WIFI.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f4105a[C0797j.HIGH_SPEED_NETWORK.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f4105a[C0797j.HIGH_SPEED_SYNTHESIZE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    private boolean m6272a(int i) {
        return m6273b(i) >= 2;
    }

    private int m6273b(int i) {
        switch (i) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
            case Type.LONG /*7*/:
            case Opcodes.T_LONG /*11*/:
                return 1;
            case Type.BYTE /*3*/:
            case Type.INT /*5*/:
            case Type.FLOAT /*6*/:
            case Type.DOUBLE /*8*/:
            case Type.ARRAY /*9*/:
            case Type.OBJECT /*10*/:
            case Opcodes.FCONST_1 /*12*/:
            case Opcodes.DCONST_0 /*14*/:
            case Opcodes.DCONST_1 /*15*/:
                return 2;
            case Opcodes.FCONST_2 /*13*/:
                return 3;
            default:
                return 0;
        }
    }

    private boolean m6274b() {
        C0797j c0797j = null;
        try {
            c0797j = this.f4106a.m6838c();
        } catch (Exception e) {
        }
        if (this.f4107b == null) {
            if (c0797j == null) {
                this.f4107b = C0797j.DEFAULT;
                return true;
            }
            this.f4107b = c0797j;
            return true;
        } else if (c0797j == null) {
            return false;
        } else {
            if (this.f4107b.equals(c0797j)) {
                return false;
            }
            this.f4107b = c0797j;
            return true;
        }
    }

    private boolean m6275c(int i) {
        switch (i) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return true;
            default:
                return false;
        }
    }

    public void m6276a(C0824b c0824b) {
        this.f4106a = c0824b;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean m6277a() {
        /*
        r7 = this;
        r6 = 9;
        r2 = 1;
        r1 = 0;
        r0 = com.baidu.tts.p044g.p046b.C0809b.m6769a();	 Catch:{ Exception -> 0x0068 }
        r0 = r0.m6779h();	 Catch:{ Exception -> 0x0068 }
        if (r0 == 0) goto L_0x005c;
    L_0x000e:
        r3 = r7.m6274b();	 Catch:{ Exception -> 0x0068 }
        if (r3 == 0) goto L_0x0033;
    L_0x0014:
        r3 = r7.f4106a;	 Catch:{ Exception -> 0x0068 }
        r3 = r3.m6833a();	 Catch:{ Exception -> 0x0068 }
        r4 = r7.f4107b;	 Catch:{ Exception -> 0x0068 }
        r5 = com.baidu.tts.p041e.C0797j.HIGH_SPEED_SYNTHESIZE;	 Catch:{ Exception -> 0x0068 }
        r4 = r4.equals(r5);	 Catch:{ Exception -> 0x0068 }
        if (r4 != 0) goto L_0x002e;
    L_0x0024:
        r4 = r7.f4107b;	 Catch:{ Exception -> 0x0068 }
        r5 = com.baidu.tts.p041e.C0797j.HIGH_SPEED_SYNTHESIZE_WIFI;	 Catch:{ Exception -> 0x0068 }
        r4 = r4.equals(r5);	 Catch:{ Exception -> 0x0068 }
        if (r4 == 0) goto L_0x005e;
    L_0x002e:
        r4 = 1200; // 0x4b0 float:1.682E-42 double:5.93E-321;
        r3.m6353c(r4);	 Catch:{ Exception -> 0x0068 }
    L_0x0033:
        r3 = "connectivity";
        r0 = r0.getSystemService(r3);	 Catch:{ Exception -> 0x0068 }
        r0 = (android.net.ConnectivityManager) r0;	 Catch:{ Exception -> 0x0068 }
        r0 = r0.getActiveNetworkInfo();	 Catch:{ Exception -> 0x0068 }
        if (r0 == 0) goto L_0x005c;
    L_0x0041:
        r3 = r0.isConnected();	 Catch:{ Exception -> 0x0068 }
        if (r3 == 0) goto L_0x005c;
    L_0x0047:
        r3 = r0.getType();	 Catch:{ Exception -> 0x0068 }
        r0 = r0.getSubtype();	 Catch:{ Exception -> 0x0068 }
        r4 = com.baidu.tts.p027b.p028a.p032b.C0714c.C07131.f4105a;	 Catch:{ Exception -> 0x0068 }
        r5 = r7.f4107b;	 Catch:{ Exception -> 0x0068 }
        r5 = r5.ordinal();	 Catch:{ Exception -> 0x0068 }
        r4 = r4[r5];	 Catch:{ Exception -> 0x0068 }
        switch(r4) {
            case 1: goto L_0x0073;
            case 2: goto L_0x0073;
            case 3: goto L_0x007d;
            case 4: goto L_0x007d;
            default: goto L_0x005c;
        };	 Catch:{ Exception -> 0x0068 }
    L_0x005c:
        r0 = r1;
    L_0x005d:
        return r0;
    L_0x005e:
        r4 = com.baidu.tts.p041e.C0799l.MIX_ONLINE_REQUEST_TIMEOUT;	 Catch:{ Exception -> 0x0068 }
        r4 = r4.m6748b();	 Catch:{ Exception -> 0x0068 }
        r3.m6353c(r4);	 Catch:{ Exception -> 0x0068 }
        goto L_0x0033;
    L_0x0068:
        r0 = move-exception;
        r2 = "MixStrategy";
        r0 = r0.toString();
        com.baidu.tts.chainofresponsibility.logger.LoggerProxy.m6515d(r2, r0);
        goto L_0x005c;
    L_0x0073:
        r0 = r7.m6275c(r3);	 Catch:{ Exception -> 0x0068 }
        if (r0 != 0) goto L_0x007b;
    L_0x0079:
        if (r3 != r6) goto L_0x005c;
    L_0x007b:
        r0 = r2;
        goto L_0x005d;
    L_0x007d:
        r4 = r7.m6275c(r3);	 Catch:{ Exception -> 0x0068 }
        if (r4 != 0) goto L_0x008b;
    L_0x0083:
        if (r3 == r6) goto L_0x008b;
    L_0x0085:
        r0 = r7.m6272a(r0);	 Catch:{ Exception -> 0x0068 }
        if (r0 == 0) goto L_0x005c;
    L_0x008b:
        r0 = r2;
        goto L_0x005d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.tts.b.a.b.c.a():boolean");
    }
}
