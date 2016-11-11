package com.xiaomi.mistatistic.sdk;

import com.facebook.common.util.UriUtil;
import com.xiaomi.mistatistic.sdk.controller.C2596j;
import com.xiaomi.mistatistic.sdk.controller.C2601o;
import com.xiaomi.mistatistic.sdk.controller.HttpEventFilter;
import com.xiaomi.mistatistic.sdk.data.HttpEvent;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.p122a.p123a.C2915a;

public class URLStatsRecorder {
    private static final Map f12898a;
    private static final List f12899b;
    private static final List f12900c;
    private static final List f12901d;
    private static final List f12902e;

    static {
        f12898a = new HashMap();
        f12899b = new ArrayList();
        f12900c = new ArrayList();
        f12901d = new ArrayList();
        f12902e = new ArrayList();
        f12899b.add(UriUtil.LOCAL_FILE_SCHEME);
        f12899b.add("ftp");
        f12899b.add(UriUtil.HTTP_SCHEME);
        f12899b.add(UriUtil.HTTPS_SCHEME);
        f12899b.add("jar");
        f12900c.add(UriUtil.HTTP_SCHEME);
    }

    public static void addHttpEvent(HttpEvent httpEvent) {
        C2596j.m14753a().m14760a(httpEvent);
    }

    public static void dump() {
        for (HttpEvent httpEvent : C2596j.m14753a().m14762b()) {
            try {
                System.out.println("EVENT: " + httpEvent.getUrl() + ": " + httpEvent.toJSON().toString());
            } catch (JSONException e) {
            }
        }
    }

    public static boolean enableAutoRecord() {
        try {
            for (String url : f12899b) {
                URL url2 = new URL(url, "www.xiaomi.com", C2915a.f14760f);
            }
            Field declaredField = URL.class.getDeclaredField("streamHandlers");
            declaredField.setAccessible(true);
            Hashtable hashtable = (Hashtable) declaredField.get(null);
            for (String str : f12899b) {
                f12898a.put(str, (URLStreamHandler) hashtable.get(str));
            }
            URL.setURLStreamHandlerFactory(new C2581c());
            return true;
        } catch (Throwable th) {
            new C2601o().m14770a("failed to enable url interceptor", th);
            return false;
        }
    }

    public static void setEventFilter(HttpEventFilter httpEventFilter) {
        C2596j.m14753a().m14759a(httpEventFilter);
    }
}
