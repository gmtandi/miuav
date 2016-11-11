package com.p054c.p055a.p063b.p064a;

import android.widget.ImageView;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.c.a.b.a.i */
public enum C0903i {
    FIT_INSIDE,
    CROP;

    public static C0903i m7210a(ImageView imageView) {
        switch (C0904j.f4782a[imageView.getScaleType().ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
            case Type.BYTE /*3*/:
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
            case Type.INT /*5*/:
                return FIT_INSIDE;
            default:
                return CROP;
        }
    }
}
