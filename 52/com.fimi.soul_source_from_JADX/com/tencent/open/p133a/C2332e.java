package com.tencent.open.p133a;

import android.util.Log;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.util.TokenBuffer.Segment;

/* renamed from: com.tencent.open.a.e */
public final class C2332e extends C2319i {
    public static final C2332e f12011a;

    static {
        f12011a = new C2332e();
    }

    protected void m13749a(int i, Thread thread, long j, String str, String str2, Throwable th) {
        switch (i) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                Log.v(str, str2, th);
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                Log.d(str, str2, th);
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                Log.i(str, str2, th);
            case Type.DOUBLE /*8*/:
                Log.w(str, str2, th);
            case Segment.TOKENS_PER_SEGMENT /*16*/:
                Log.e(str, str2, th);
            case Opcodes.ACC_SYNCHRONIZED /*32*/:
                Log.e(str, str2, th);
            default:
        }
    }
}
