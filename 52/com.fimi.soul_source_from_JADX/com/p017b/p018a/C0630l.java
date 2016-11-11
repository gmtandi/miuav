package com.p017b.p018a;

import com.tencent.mm.sdk.platformtools.Util;

/* renamed from: com.b.a.l */
public class C0630l implements ar {
    public Object m5622a(float f, Object obj, Object obj2) {
        int intValue = ((Integer) obj).intValue();
        int i = intValue >> 24;
        int i2 = (intValue >> 16) & Util.MASK_8BIT;
        int i3 = (intValue >> 8) & Util.MASK_8BIT;
        intValue &= Util.MASK_8BIT;
        int intValue2 = ((Integer) obj2).intValue();
        return Integer.valueOf((intValue + ((int) (((float) ((intValue2 & Util.MASK_8BIT) - intValue)) * f))) | ((((i + ((int) (((float) ((intValue2 >> 24) - i)) * f))) << 24) | ((i2 + ((int) (((float) (((intValue2 >> 16) & Util.MASK_8BIT) - i2)) * f))) << 16)) | ((((int) (((float) (((intValue2 >> 8) & Util.MASK_8BIT) - i3)) * f)) + i3) << 8)));
    }
}
