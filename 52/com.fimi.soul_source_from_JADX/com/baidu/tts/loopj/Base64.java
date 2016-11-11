package com.baidu.tts.loopj;

import com.fimi.soul.drone.p107c.p108a.p109a.C1448k;
import com.fimi.soul.module.droneui.DroneMap;
import com.fimi.soul.module.setting.newhand.C1874p;
import java.io.UnsupportedEncodingException;
import org.codehaus.jackson.smile.SmileConstants;

public class Base64 {
    public static final int CRLF = 4;
    public static final int DEFAULT = 0;
    public static final int NO_CLOSE = 16;
    public static final int NO_PADDING = 1;
    public static final int NO_WRAP = 2;
    public static final int URL_SAFE = 8;

    abstract class Coder {
        public int op;
        public byte[] output;

        Coder() {
        }

        public abstract int maxOutputSize(int i);

        public abstract boolean process(byte[] bArr, int i, int i2, boolean z);
    }

    class Decoder extends Coder {
        private static final int[] DECODE;
        private static final int[] DECODE_WEBSAFE;
        private static final int EQUALS = -2;
        private static final int SKIP = -1;
        private final int[] alphabet;
        private int state;
        private int value;

        static {
            DECODE = new int[]{SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, 62, SKIP, SKIP, SKIP, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, SKIP, SKIP, SKIP, EQUALS, SKIP, SKIP, SKIP, Base64.DEFAULT, Base64.NO_PADDING, Base64.NO_WRAP, 3, Base64.CRLF, 5, 6, 7, Base64.URL_SAFE, 9, 10, 11, 12, 13, 14, 15, Base64.NO_CLOSE, 17, 18, 19, 20, 21, 22, 23, 24, 25, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP};
            DECODE_WEBSAFE = new int[]{SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, 62, SKIP, SKIP, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, SKIP, SKIP, SKIP, EQUALS, SKIP, SKIP, SKIP, Base64.DEFAULT, Base64.NO_PADDING, Base64.NO_WRAP, 3, Base64.CRLF, 5, 6, 7, Base64.URL_SAFE, 9, 10, 11, 12, 13, 14, 15, Base64.NO_CLOSE, 17, 18, 19, 20, 21, 22, 23, 24, 25, SKIP, SKIP, SKIP, SKIP, 63, SKIP, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP};
        }

        public Decoder(int i, byte[] bArr) {
            this.output = bArr;
            this.alphabet = (i & Base64.URL_SAFE) == 0 ? DECODE : DECODE_WEBSAFE;
            this.state = Base64.DEFAULT;
            this.value = Base64.DEFAULT;
        }

        public int maxOutputSize(int i) {
            return ((i * 3) / Base64.CRLF) + 10;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean process(byte[] r11, int r12, int r13, boolean r14) {
            /*
            r10 = this;
            r0 = r10.state;
            r1 = 6;
            if (r0 != r1) goto L_0x0007;
        L_0x0005:
            r0 = 0;
        L_0x0006:
            return r0;
        L_0x0007:
            r4 = r13 + r12;
            r3 = r10.state;
            r1 = r10.value;
            r0 = 0;
            r5 = r10.output;
            r6 = r10.alphabet;
            r2 = r12;
        L_0x0013:
            if (r2 >= r4) goto L_0x0133;
        L_0x0015:
            if (r3 != 0) goto L_0x0067;
        L_0x0017:
            r7 = r2 + 4;
            if (r7 > r4) goto L_0x005a;
        L_0x001b:
            r1 = r11[r2];
            r1 = r1 & 255;
            r1 = r6[r1];
            r1 = r1 << 18;
            r7 = r2 + 1;
            r7 = r11[r7];
            r7 = r7 & 255;
            r7 = r6[r7];
            r7 = r7 << 12;
            r1 = r1 | r7;
            r7 = r2 + 2;
            r7 = r11[r7];
            r7 = r7 & 255;
            r7 = r6[r7];
            r7 = r7 << 6;
            r1 = r1 | r7;
            r7 = r2 + 3;
            r7 = r11[r7];
            r7 = r7 & 255;
            r7 = r6[r7];
            r1 = r1 | r7;
            if (r1 < 0) goto L_0x005a;
        L_0x0044:
            r7 = r0 + 2;
            r8 = (byte) r1;
            r5[r7] = r8;
            r7 = r0 + 1;
            r8 = r1 >> 8;
            r8 = (byte) r8;
            r5[r7] = r8;
            r7 = r1 >> 16;
            r7 = (byte) r7;
            r5[r0] = r7;
            r0 = r0 + 3;
            r2 = r2 + 4;
            goto L_0x0017;
        L_0x005a:
            if (r2 < r4) goto L_0x0067;
        L_0x005c:
            r2 = r1;
        L_0x005d:
            if (r14 != 0) goto L_0x0105;
        L_0x005f:
            r10.state = r3;
            r10.value = r2;
            r10.op = r0;
            r0 = 1;
            goto L_0x0006;
        L_0x0067:
            r12 = r2 + 1;
            r2 = r11[r2];
            r2 = r2 & 255;
            r2 = r6[r2];
            switch(r3) {
                case 0: goto L_0x0076;
                case 1: goto L_0x0086;
                case 2: goto L_0x0097;
                case 3: goto L_0x00b7;
                case 4: goto L_0x00ed;
                case 5: goto L_0x00fc;
                default: goto L_0x0072;
            };
        L_0x0072:
            r2 = r3;
        L_0x0073:
            r3 = r2;
            r2 = r12;
            goto L_0x0013;
        L_0x0076:
            if (r2 < 0) goto L_0x007e;
        L_0x0078:
            r1 = r3 + 1;
            r9 = r2;
            r2 = r1;
            r1 = r9;
            goto L_0x0073;
        L_0x007e:
            r7 = -1;
            if (r2 == r7) goto L_0x0072;
        L_0x0081:
            r0 = 6;
            r10.state = r0;
            r0 = 0;
            goto L_0x0006;
        L_0x0086:
            if (r2 < 0) goto L_0x008e;
        L_0x0088:
            r1 = r1 << 6;
            r1 = r1 | r2;
            r2 = r3 + 1;
            goto L_0x0073;
        L_0x008e:
            r7 = -1;
            if (r2 == r7) goto L_0x0072;
        L_0x0091:
            r0 = 6;
            r10.state = r0;
            r0 = 0;
            goto L_0x0006;
        L_0x0097:
            if (r2 < 0) goto L_0x009f;
        L_0x0099:
            r1 = r1 << 6;
            r1 = r1 | r2;
            r2 = r3 + 1;
            goto L_0x0073;
        L_0x009f:
            r7 = -2;
            if (r2 != r7) goto L_0x00ae;
        L_0x00a2:
            r2 = r0 + 1;
            r3 = r1 >> 4;
            r3 = (byte) r3;
            r5[r0] = r3;
            r0 = 4;
            r9 = r2;
            r2 = r0;
            r0 = r9;
            goto L_0x0073;
        L_0x00ae:
            r7 = -1;
            if (r2 == r7) goto L_0x0072;
        L_0x00b1:
            r0 = 6;
            r10.state = r0;
            r0 = 0;
            goto L_0x0006;
        L_0x00b7:
            if (r2 < 0) goto L_0x00d1;
        L_0x00b9:
            r1 = r1 << 6;
            r1 = r1 | r2;
            r2 = r0 + 2;
            r3 = (byte) r1;
            r5[r2] = r3;
            r2 = r0 + 1;
            r3 = r1 >> 8;
            r3 = (byte) r3;
            r5[r2] = r3;
            r2 = r1 >> 16;
            r2 = (byte) r2;
            r5[r0] = r2;
            r0 = r0 + 3;
            r2 = 0;
            goto L_0x0073;
        L_0x00d1:
            r7 = -2;
            if (r2 != r7) goto L_0x00e4;
        L_0x00d4:
            r2 = r0 + 1;
            r3 = r1 >> 2;
            r3 = (byte) r3;
            r5[r2] = r3;
            r2 = r1 >> 10;
            r2 = (byte) r2;
            r5[r0] = r2;
            r0 = r0 + 2;
            r2 = 5;
            goto L_0x0073;
        L_0x00e4:
            r7 = -1;
            if (r2 == r7) goto L_0x0072;
        L_0x00e7:
            r0 = 6;
            r10.state = r0;
            r0 = 0;
            goto L_0x0006;
        L_0x00ed:
            r7 = -2;
            if (r2 != r7) goto L_0x00f3;
        L_0x00f0:
            r2 = r3 + 1;
            goto L_0x0073;
        L_0x00f3:
            r7 = -1;
            if (r2 == r7) goto L_0x0072;
        L_0x00f6:
            r0 = 6;
            r10.state = r0;
            r0 = 0;
            goto L_0x0006;
        L_0x00fc:
            r7 = -1;
            if (r2 == r7) goto L_0x0072;
        L_0x00ff:
            r0 = 6;
            r10.state = r0;
            r0 = 0;
            goto L_0x0006;
        L_0x0105:
            switch(r3) {
                case 0: goto L_0x0108;
                case 1: goto L_0x010f;
                case 2: goto L_0x0115;
                case 3: goto L_0x011e;
                case 4: goto L_0x012d;
                default: goto L_0x0108;
            };
        L_0x0108:
            r10.state = r3;
            r10.op = r0;
            r0 = 1;
            goto L_0x0006;
        L_0x010f:
            r0 = 6;
            r10.state = r0;
            r0 = 0;
            goto L_0x0006;
        L_0x0115:
            r1 = r0 + 1;
            r2 = r2 >> 4;
            r2 = (byte) r2;
            r5[r0] = r2;
            r0 = r1;
            goto L_0x0108;
        L_0x011e:
            r1 = r0 + 1;
            r4 = r2 >> 10;
            r4 = (byte) r4;
            r5[r0] = r4;
            r0 = r1 + 1;
            r2 = r2 >> 2;
            r2 = (byte) r2;
            r5[r1] = r2;
            goto L_0x0108;
        L_0x012d:
            r0 = 6;
            r10.state = r0;
            r0 = 0;
            goto L_0x0006;
        L_0x0133:
            r2 = r1;
            goto L_0x005d;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.tts.loopj.Base64.Decoder.process(byte[], int, int, boolean):boolean");
        }
    }

    class Encoder extends Coder {
        private static final byte[] ENCODE;
        private static final byte[] ENCODE_WEBSAFE;
        public static final int LINE_GROUPS = 19;
        private final byte[] alphabet;
        private int count;
        public final boolean do_cr;
        public final boolean do_newline;
        public final boolean do_padding;
        private final byte[] tail;
        int tailLen;

        static {
            ENCODE = new byte[]{(byte) 65, (byte) 66, DroneMap.f8357k, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, DroneMap.f8355i, (byte) 81, DroneMap.f8356j, (byte) 83, (byte) 84, C1874p.f9570f, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, C1448k.f6847b, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, SmileConstants.TOKEN_KEY_LONG_STRING, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 43, (byte) 47};
            ENCODE_WEBSAFE = new byte[]{(byte) 65, (byte) 66, DroneMap.f8357k, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, DroneMap.f8355i, (byte) 81, DroneMap.f8356j, (byte) 83, (byte) 84, C1874p.f9570f, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, C1448k.f6847b, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, SmileConstants.TOKEN_KEY_LONG_STRING, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 45, (byte) 95};
        }

        public Encoder(int i, byte[] bArr) {
            boolean z = true;
            this.output = bArr;
            this.do_padding = (i & Base64.NO_PADDING) == 0;
            this.do_newline = (i & Base64.NO_WRAP) == 0;
            if ((i & Base64.CRLF) == 0) {
                z = false;
            }
            this.do_cr = z;
            this.alphabet = (i & Base64.URL_SAFE) == 0 ? ENCODE : ENCODE_WEBSAFE;
            this.tail = new byte[Base64.NO_WRAP];
            this.tailLen = Base64.DEFAULT;
            this.count = this.do_newline ? LINE_GROUPS : -1;
        }

        public int maxOutputSize(int i) {
            return ((i * Base64.URL_SAFE) / 5) + 10;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean process(byte[] r11, int r12, int r13, boolean r14) {
            /* JADX: method processing error */
/*
            Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:42)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:66)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
            /*
            r10 = this;
            r6 = r10.alphabet;
            r7 = r10.output;
            r1 = 0;
            r0 = r10.count;
            r8 = r13 + r12;
            r2 = -1;
            r3 = r10.tailLen;
            switch(r3) {
                case 0: goto L_0x00a7;
                case 1: goto L_0x00aa;
                case 2: goto L_0x00cd;
                default: goto L_0x000f;
            };
        L_0x000f:
            r3 = r12;
        L_0x0010:
            r4 = -1;
            if (r2 == r4) goto L_0x0220;
        L_0x0013:
            r4 = 1;
            r5 = r2 >> 18;
            r5 = r5 & 63;
            r5 = r6[r5];
            r7[r1] = r5;
            r1 = 2;
            r5 = r2 >> 12;
            r5 = r5 & 63;
            r5 = r6[r5];
            r7[r4] = r5;
            r4 = 3;
            r5 = r2 >> 6;
            r5 = r5 & 63;
            r5 = r6[r5];
            r7[r1] = r5;
            r1 = 4;
            r2 = r2 & 63;
            r2 = r6[r2];
            r7[r4] = r2;
            r0 = r0 + -1;
            if (r0 != 0) goto L_0x0220;
        L_0x0039:
            r0 = r10.do_cr;
            if (r0 == 0) goto L_0x0224;
        L_0x003d:
            r0 = 5;
            r2 = 13;
            r7[r1] = r2;
        L_0x0042:
            r1 = r0 + 1;
            r2 = 10;
            r7[r0] = r2;
            r0 = 19;
            r5 = r0;
            r4 = r1;
        L_0x004c:
            r0 = r3 + 3;
            if (r0 > r8) goto L_0x00f0;
        L_0x0050:
            r0 = r11[r3];
            r0 = r0 & 255;
            r0 = r0 << 16;
            r1 = r3 + 1;
            r1 = r11[r1];
            r1 = r1 & 255;
            r1 = r1 << 8;
            r0 = r0 | r1;
            r1 = r3 + 2;
            r1 = r11[r1];
            r1 = r1 & 255;
            r0 = r0 | r1;
            r1 = r0 >> 18;
            r1 = r1 & 63;
            r1 = r6[r1];
            r7[r4] = r1;
            r1 = r4 + 1;
            r2 = r0 >> 12;
            r2 = r2 & 63;
            r2 = r6[r2];
            r7[r1] = r2;
            r1 = r4 + 2;
            r2 = r0 >> 6;
            r2 = r2 & 63;
            r2 = r6[r2];
            r7[r1] = r2;
            r1 = r4 + 3;
            r0 = r0 & 63;
            r0 = r6[r0];
            r7[r1] = r0;
            r3 = r3 + 3;
            r1 = r4 + 4;
            r0 = r5 + -1;
            if (r0 != 0) goto L_0x0220;
        L_0x0092:
            r0 = r10.do_cr;
            if (r0 == 0) goto L_0x021d;
        L_0x0096:
            r0 = r1 + 1;
            r2 = 13;
            r7[r1] = r2;
        L_0x009c:
            r1 = r0 + 1;
            r2 = 10;
            r7[r0] = r2;
            r0 = 19;
            r5 = r0;
            r4 = r1;
            goto L_0x004c;
        L_0x00a7:
            r3 = r12;
            goto L_0x0010;
        L_0x00aa:
            r3 = r12 + 2;
            if (r3 > r8) goto L_0x000f;
        L_0x00ae:
            r2 = r10.tail;
            r3 = 0;
            r2 = r2[r3];
            r2 = r2 & 255;
            r2 = r2 << 16;
            r3 = r12 + 1;
            r4 = r11[r12];
            r4 = r4 & 255;
            r4 = r4 << 8;
            r2 = r2 | r4;
            r12 = r3 + 1;
            r3 = r11[r3];
            r3 = r3 & 255;
            r2 = r2 | r3;
            r3 = 0;
            r10.tailLen = r3;
            r3 = r12;
            goto L_0x0010;
        L_0x00cd:
            r3 = r12 + 1;
            if (r3 > r8) goto L_0x000f;
        L_0x00d1:
            r2 = r10.tail;
            r3 = 0;
            r2 = r2[r3];
            r2 = r2 & 255;
            r2 = r2 << 16;
            r3 = r10.tail;
            r4 = 1;
            r3 = r3[r4];
            r3 = r3 & 255;
            r3 = r3 << 8;
            r2 = r2 | r3;
            r3 = r12 + 1;
            r4 = r11[r12];
            r4 = r4 & 255;
            r2 = r2 | r4;
            r4 = 0;
            r10.tailLen = r4;
            goto L_0x0010;
        L_0x00f0:
            if (r14 == 0) goto L_0x01e7;
        L_0x00f2:
            r0 = r10.tailLen;
            r0 = r3 - r0;
            r1 = r8 + -1;
            if (r0 != r1) goto L_0x0152;
        L_0x00fa:
            r2 = 0;
            r0 = r10.tailLen;
            if (r0 <= 0) goto L_0x014c;
        L_0x00ff:
            r0 = r10.tail;
            r1 = 1;
            r0 = r0[r2];
        L_0x0104:
            r0 = r0 & 255;
            r2 = r0 << 4;
            r0 = r10.tailLen;
            r0 = r0 - r1;
            r10.tailLen = r0;
            r1 = r4 + 1;
            r0 = r2 >> 6;
            r0 = r0 & 63;
            r0 = r6[r0];
            r7[r4] = r0;
            r0 = r1 + 1;
            r2 = r2 & 63;
            r2 = r6[r2];
            r7[r1] = r2;
            r1 = r10.do_padding;
            if (r1 == 0) goto L_0x012f;
        L_0x0123:
            r1 = r0 + 1;
            r2 = 61;
            r7[r0] = r2;
            r0 = r1 + 1;
            r2 = 61;
            r7[r1] = r2;
        L_0x012f:
            r1 = r10.do_newline;
            if (r1 == 0) goto L_0x0145;
        L_0x0133:
            r1 = r10.do_cr;
            if (r1 == 0) goto L_0x013e;
        L_0x0137:
            r1 = r0 + 1;
            r2 = 13;
            r7[r0] = r2;
            r0 = r1;
        L_0x013e:
            r1 = r0 + 1;
            r2 = 10;
            r7[r0] = r2;
            r0 = r1;
        L_0x0145:
            r4 = r0;
        L_0x0146:
            r10.op = r4;
            r10.count = r5;
            r0 = 1;
            return r0;
        L_0x014c:
            r0 = r3 + 1;
            r0 = r11[r3];
            r1 = r2;
            goto L_0x0104;
        L_0x0152:
            r0 = r10.tailLen;
            r0 = r3 - r0;
            r1 = r8 + -2;
            if (r0 != r1) goto L_0x01cb;
        L_0x015a:
            r2 = 0;
            r0 = r10.tailLen;
            r1 = 1;
            if (r0 <= r1) goto L_0x01be;
        L_0x0160:
            r0 = r10.tail;
            r1 = 1;
            r0 = r0[r2];
            r2 = r3;
        L_0x0166:
            r0 = r0 & 255;
            r3 = r0 << 10;
            r0 = r10.tailLen;
            if (r0 <= 0) goto L_0x01c6;
        L_0x016e:
            r0 = r10.tail;
            r2 = r1 + 1;
            r0 = r0[r1];
            r1 = r2;
        L_0x0175:
            r0 = r0 & 255;
            r0 = r0 << 2;
            r0 = r0 | r3;
            r2 = r10.tailLen;
            r1 = r2 - r1;
            r10.tailLen = r1;
            r1 = r4 + 1;
            r2 = r0 >> 12;
            r2 = r2 & 63;
            r2 = r6[r2];
            r7[r4] = r2;
            r2 = r1 + 1;
            r3 = r0 >> 6;
            r3 = r3 & 63;
            r3 = r6[r3];
            r7[r1] = r3;
            r1 = r2 + 1;
            r0 = r0 & 63;
            r0 = r6[r0];
            r7[r2] = r0;
            r0 = r10.do_padding;
            if (r0 == 0) goto L_0x021b;
        L_0x01a0:
            r0 = r1 + 1;
            r2 = 61;
            r7[r1] = r2;
        L_0x01a6:
            r1 = r10.do_newline;
            if (r1 == 0) goto L_0x01bc;
        L_0x01aa:
            r1 = r10.do_cr;
            if (r1 == 0) goto L_0x01b5;
        L_0x01ae:
            r1 = r0 + 1;
            r2 = 13;
            r7[r0] = r2;
            r0 = r1;
        L_0x01b5:
            r1 = r0 + 1;
            r2 = 10;
            r7[r0] = r2;
            r0 = r1;
        L_0x01bc:
            r4 = r0;
            goto L_0x0146;
        L_0x01be:
            r1 = r3 + 1;
            r0 = r11[r3];
            r9 = r2;
            r2 = r1;
            r1 = r9;
            goto L_0x0166;
        L_0x01c6:
            r0 = r2 + 1;
            r0 = r11[r2];
            goto L_0x0175;
        L_0x01cb:
            r0 = r10.do_newline;
            if (r0 == 0) goto L_0x0146;
        L_0x01cf:
            if (r4 <= 0) goto L_0x0146;
        L_0x01d1:
            r0 = 19;
            if (r5 == r0) goto L_0x0146;
        L_0x01d5:
            r0 = r10.do_cr;
            if (r0 == 0) goto L_0x0219;
        L_0x01d9:
            r0 = r4 + 1;
            r1 = 13;
            r7[r4] = r1;
        L_0x01df:
            r4 = r0 + 1;
            r1 = 10;
            r7[r0] = r1;
            goto L_0x0146;
        L_0x01e7:
            r0 = r8 + -1;
            if (r3 != r0) goto L_0x01f9;
        L_0x01eb:
            r0 = r10.tail;
            r1 = r10.tailLen;
            r2 = r1 + 1;
            r10.tailLen = r2;
            r2 = r11[r3];
            r0[r1] = r2;
            goto L_0x0146;
        L_0x01f9:
            r0 = r8 + -2;
            if (r3 != r0) goto L_0x0146;
        L_0x01fd:
            r0 = r10.tail;
            r1 = r10.tailLen;
            r2 = r1 + 1;
            r10.tailLen = r2;
            r2 = r11[r3];
            r0[r1] = r2;
            r0 = r10.tail;
            r1 = r10.tailLen;
            r2 = r1 + 1;
            r10.tailLen = r2;
            r2 = r3 + 1;
            r2 = r11[r2];
            r0[r1] = r2;
            goto L_0x0146;
        L_0x0219:
            r0 = r4;
            goto L_0x01df;
        L_0x021b:
            r0 = r1;
            goto L_0x01a6;
        L_0x021d:
            r0 = r1;
            goto L_0x009c;
        L_0x0220:
            r5 = r0;
            r4 = r1;
            goto L_0x004c;
        L_0x0224:
            r0 = r1;
            goto L_0x0042;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.tts.loopj.Base64.Encoder.process(byte[], int, int, boolean):boolean");
        }
    }

    private Base64() {
    }

    public static byte[] decode(String str, int i) {
        return decode(str.getBytes(), i);
    }

    public static byte[] decode(byte[] bArr, int i) {
        return decode(bArr, DEFAULT, bArr.length, i);
    }

    public static byte[] decode(byte[] bArr, int i, int i2, int i3) {
        Decoder decoder = new Decoder(i3, new byte[((i2 * 3) / CRLF)]);
        if (!decoder.process(bArr, i, i2, true)) {
            throw new IllegalArgumentException("bad base-64");
        } else if (decoder.op == decoder.output.length) {
            return decoder.output;
        } else {
            Object obj = new byte[decoder.op];
            System.arraycopy(decoder.output, DEFAULT, obj, DEFAULT, decoder.op);
            return obj;
        }
    }

    public static byte[] encode(byte[] bArr, int i) {
        return encode(bArr, DEFAULT, bArr.length, i);
    }

    public static byte[] encode(byte[] bArr, int i, int i2, int i3) {
        Encoder encoder = new Encoder(i3, null);
        int i4 = (i2 / 3) * CRLF;
        if (!encoder.do_padding) {
            switch (i2 % 3) {
                case DEFAULT /*0*/:
                    break;
                case NO_PADDING /*1*/:
                    i4 += NO_WRAP;
                    break;
                case NO_WRAP /*2*/:
                    i4 += 3;
                    break;
                default:
                    break;
            }
        } else if (i2 % 3 > 0) {
            i4 += CRLF;
        }
        if (encoder.do_newline && i2 > 0) {
            i4 += (encoder.do_cr ? NO_WRAP : NO_PADDING) * (((i2 - 1) / 57) + NO_PADDING);
        }
        encoder.output = new byte[i4];
        encoder.process(bArr, i, i2, true);
        return encoder.output;
    }

    public static String encodeToString(byte[] bArr, int i) {
        try {
            return new String(encode(bArr, i), "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public static String encodeToString(byte[] bArr, int i, int i2, int i3) {
        try {
            return new String(encode(bArr, i, i2, i3), "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }
}
