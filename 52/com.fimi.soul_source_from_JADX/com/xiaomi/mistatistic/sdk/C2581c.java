package com.xiaomi.mistatistic.sdk;

import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

/* renamed from: com.xiaomi.mistatistic.sdk.c */
final class C2581c implements URLStreamHandlerFactory {
    C2581c() {
    }

    public URLStreamHandler createURLStreamHandler(String str) {
        return URLStatsRecorder.f12898a.containsKey(str) ? new C2613d((URLStreamHandler) URLStatsRecorder.f12898a.get(str)) : null;
    }
}
