package com.xiaomi.kenai.jbosh;

import java.lang.ref.SoftReference;
import org.xmlpull.v1.XmlPullParser;

final class af extends ThreadLocal<SoftReference<XmlPullParser>> {
    af() {
    }

    protected SoftReference<XmlPullParser> m14313a() {
        return new SoftReference(null);
    }

    protected /* synthetic */ Object initialValue() {
        return m14313a();
    }
}
