package com.xiaomi.smack;

import java.util.concurrent.ThreadFactory;

/* renamed from: com.xiaomi.smack.c */
class C2680c implements ThreadFactory {
    final /* synthetic */ C2679b f13279a;

    C2680c(C2679b c2679b) {
        this.f13279a = c2679b;
    }

    public Thread newThread(Runnable runnable) {
        return new Thread(runnable, "Smack Listener Processor (" + this.f13279a.l + ")");
    }
}
