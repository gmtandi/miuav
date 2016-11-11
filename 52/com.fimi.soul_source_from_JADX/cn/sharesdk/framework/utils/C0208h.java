package cn.sharesdk.framework.utils;

import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: cn.sharesdk.framework.utils.h */
final class C0208h extends ThreadLocal<char[]> {
    C0208h() {
    }

    protected char[] m756a() {
        return new char[SmileConstants.MAX_SHARED_STRING_VALUES];
    }

    protected /* synthetic */ Object initialValue() {
        return m756a();
    }
}
