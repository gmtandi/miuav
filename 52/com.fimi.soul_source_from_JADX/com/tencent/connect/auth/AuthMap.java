package com.tencent.connect.auth;

import com.tencent.tauth.IUiListener;
import java.util.HashMap;
import org.p122a.p123a.C2915a;

public class AuthMap {
    static final /* synthetic */ boolean f11418a;
    private static int f11419b;
    public static AuthMap sInstance;
    public final String KEY_CHAR_LIST;
    public HashMap<String, Auth> authMap;

    public class Auth {
        public AuthDialog dialog;
        public String key;
        public IUiListener listener;
    }

    static {
        f11418a = !AuthMap.class.desiredAssertionStatus();
        f11419b = 0;
    }

    public AuthMap() {
        this.authMap = new HashMap();
        this.KEY_CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    }

    private String m13270a(String str, String str2) {
        int i = 0;
        if (f11418a || str.length() % 2 == 0) {
            StringBuilder stringBuilder = new StringBuilder();
            int length = str2.length();
            int length2 = str.length() / 2;
            int i2 = 0;
            while (i < length2) {
                stringBuilder.append((char) (Integer.parseInt(str.substring(i * 2, (i * 2) + 2), 16) ^ str2.charAt(i2)));
                i2 = (i2 + 1) % length;
                i++;
            }
            return stringBuilder.toString();
        }
        throw new AssertionError();
    }

    public static AuthMap getInstance() {
        if (sInstance == null) {
            sInstance = new AuthMap();
        }
        return sInstance;
    }

    public static int getSerial() {
        int i = f11419b + 1;
        f11419b = i;
        return i;
    }

    public String decode(String str, String str2) {
        return m13270a(str, str2);
    }

    public Auth get(String str) {
        return (Auth) this.authMap.get(str);
    }

    public String makeKey() {
        int ceil = (int) Math.ceil((Math.random() * 20.0d) + 3.0d);
        char[] toCharArray = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        int length = toCharArray.length;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < ceil; i++) {
            stringBuffer.append(toCharArray[(int) (Math.random() * ((double) length))]);
        }
        return stringBuffer.toString();
    }

    public void remove(String str) {
        this.authMap.remove(str);
    }

    public String set(Auth auth) {
        int serial = getSerial();
        try {
            this.authMap.put(C2915a.f14760f + serial, auth);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return C2915a.f14760f + serial;
    }
}
