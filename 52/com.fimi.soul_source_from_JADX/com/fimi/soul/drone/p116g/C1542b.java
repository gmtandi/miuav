package com.fimi.soul.drone.p116g;

import com.fimi.soul.media.player.FimiMediaMeta;
import com.tencent.mm.sdk.platformtools.Util;
import it.sephiroth.android.library.widget.ExpandableHListView;

/* renamed from: com.fimi.soul.drone.g.b */
public class C1542b {
    public static long m10113a(int[] iArr, int i) {
        long j = -1;
        int i2 = 0;
        while (true) {
            int i3 = i - 1;
            if (i <= 0) {
                return j;
            }
            long j2 = FimiMediaMeta.AV_CH_WIDE_LEFT;
            int i4 = i2 + 1;
            long j3 = (long) iArr[i2];
            long j4 = j;
            for (j = 0; j < 32; j++) {
                j4 = (FimiMediaMeta.AV_CH_WIDE_LEFT & j4) == FimiMediaMeta.AV_CH_WIDE_LEFT ? ((j4 << 1) & ExpandableHListView.aZ) ^ 79764919 : (j4 << 1) & ExpandableHListView.aZ;
                if ((j3 & j2) == j2) {
                    j4 ^= 79764919;
                }
                j2 >>= 1;
            }
            j = j4;
            i = i3;
            i2 = i4;
        }
    }

    public static byte[] m10114a(int i) {
        byte[] bArr = new byte[4];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr[i2] = (byte) ((i >> (i2 * 8)) & Util.MASK_8BIT);
        }
        return bArr;
    }

    public static byte[] m10115a(long j) {
        byte[] bArr = new byte[4];
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) ((int) ((j >> (i * 8)) & 255));
        }
        return bArr;
    }

    public static byte[] m10116a(short s) {
        byte[] bArr = new byte[2];
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) ((s >> (i * 8)) & Util.MASK_8BIT);
        }
        return bArr;
    }
}
