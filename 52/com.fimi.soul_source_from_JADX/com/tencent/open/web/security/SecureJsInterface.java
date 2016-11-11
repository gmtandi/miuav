package com.tencent.open.web.security;

import com.tencent.open.C2336a.C2292b;
import com.tencent.open.p133a.C2333f;
import org.p122a.p123a.C2915a;

public class SecureJsInterface extends C2292b {
    private static final String f12128a;
    public static boolean isPWDEdit;
    private String f12129b;

    static {
        f12128a = C2333f.f12014d + ".SI";
        isPWDEdit = false;
    }

    public void clearAllEdit() {
        C2333f.m13757c(f12128a, "-->clear all edit.");
        try {
            JniInterface.clearAllPWD();
        } catch (Throwable e) {
            C2333f.m13759e(f12128a, "-->clear all edit exception: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void curPosFromJS(String str) {
        C2333f.m13757c(f12128a, "-->curPosFromJS: " + str);
        int i = -1;
        try {
            i = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            C2333f.m13759e(f12128a, "-->curPosFromJS number format exception.");
        }
        if (i < 0) {
            throw new RuntimeException("position is illegal.");
        }
        if (C2363a.f12132c) {
        }
        if (!C2363a.f12131b) {
            this.f12129b = C2363a.f12130a;
            JniInterface.insetTextToArray(i, this.f12129b, this.f12129b.length());
            C2333f.m13754b(f12128a, "mKey: " + this.f12129b);
        } else if (Boolean.valueOf(JniInterface.BackSpaceChar(C2363a.f12131b, i)).booleanValue()) {
            C2363a.f12131b = false;
        }
    }

    public boolean customCallback() {
        return true;
    }

    public String getMD5FromNative() {
        C2333f.m13757c(f12128a, "-->get md5 form native");
        String str = C2915a.f14760f;
        try {
            str = JniInterface.getPWDKeyToMD5(null);
            C2333f.m13754b(f12128a, "-->getMD5FromNative, MD5= " + str);
            return str;
        } catch (Throwable e) {
            C2333f.m13759e(f12128a, "-->get md5 form native exception: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void isPasswordEdit(String str) {
        C2333f.m13757c(f12128a, "-->is pswd edit, flag: " + str);
        int i = -1;
        try {
            i = Integer.parseInt(str);
        } catch (Exception e) {
            C2333f.m13759e(f12128a, "-->is pswd edit exception: " + e.getMessage());
        }
        if (i != 0 && i != 1) {
            throw new RuntimeException("is pswd edit flag is illegal.");
        } else if (i == 0) {
            isPWDEdit = false;
        } else if (i == 1) {
            isPWDEdit = true;
        }
    }
}
