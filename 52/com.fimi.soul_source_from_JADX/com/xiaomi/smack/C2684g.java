package com.xiaomi.smack;

import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.xiaomi.smack.g */
class C2684g extends Thread {
    final /* synthetic */ C2679b f13283a;
    private Thread f13284b;
    private int f13285c;

    C2684g(C2679b c2679b) {
        this.f13283a = c2679b;
        this.f13284b = this;
        this.f13285c = SmileConstants.MAX_SHARED_STRING_VALUES;
    }

    public void run() {
        try {
            char[] cArr = new char[this.f13285c];
            while (this.f13283a.f13276x == this.f13284b && !this.f13283a.f13273u) {
                this.f13283a.i.read(cArr, 0, this.f13285c);
            }
        } catch (Throwable th) {
        }
    }
}
