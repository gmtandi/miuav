package com.tencent.open.p133a;

import android.text.format.Time;
import android.util.Log;
import com.fimi.soul.module.setting.newhand.C1873o;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.util.TokenBuffer.Segment;
import org.p122a.p123a.p124f.p125c.C3022o;

/* renamed from: com.tencent.open.a.h */
public final class C2335h {
    public static final C2335h f12018a;

    static {
        f12018a = new C2335h();
    }

    public final String m13766a(int i) {
        switch (i) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return "V";
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return "D";
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                return "I";
            case Type.DOUBLE /*8*/:
                return "W";
            case Segment.TOKENS_PER_SEGMENT /*16*/:
                return "E";
            case Opcodes.ACC_SYNCHRONIZED /*32*/:
                return "A";
            default:
                return "-";
        }
    }

    public String m13767a(int i, Thread thread, long j, String str, String str2, Throwable th) {
        long j2 = j % 1000;
        Time time = new Time();
        time.set(j);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(m13766a(i)).append('/').append(time.format("%Y-%m-%d %H:%M:%S")).append('.');
        if (j2 < 10) {
            stringBuilder.append("00");
        } else if (j2 < 100) {
            stringBuilder.append('0');
        }
        stringBuilder.append(j2).append(C3022o.f15055c).append('[');
        if (thread == null) {
            stringBuilder.append(C1873o.an);
        } else {
            stringBuilder.append(thread.getName());
        }
        stringBuilder.append(']').append('[').append(str).append(']').append(C3022o.f15055c).append(str2).append('\n');
        if (th != null) {
            stringBuilder.append("* Exception : \n").append(Log.getStackTraceString(th)).append('\n');
        }
        return stringBuilder.toString();
    }
}
