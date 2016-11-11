package com.p054c.p055a.p056a.p057a.p058a;

import android.graphics.Bitmap;
import com.p054c.p055a.p056a.p057a.p060b.C0872a;
import com.p054c.p055a.p063b.C0905a;
import com.p054c.p055a.p072c.C0947e;
import java.io.File;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.c.a.a.a.a.b */
public class C0870b extends C0869a {
    private final long f4711j;
    private final Map<File, Long> f4712k;

    public C0870b(File file, long j) {
        this(file, null, C0905a.m7219b(), j);
    }

    public C0870b(File file, File file2, long j) {
        this(file, file2, C0905a.m7219b(), j);
    }

    public C0870b(File file, File file2, C0872a c0872a, long j) {
        super(file, file2, c0872a);
        this.f4712k = Collections.synchronizedMap(new HashMap());
        this.f4711j = 1000 * j;
    }

    private void m7063d(String str) {
        File c = m7061c(str);
        long currentTimeMillis = System.currentTimeMillis();
        c.setLastModified(currentTimeMillis);
        this.f4712k.put(c, Long.valueOf(currentTimeMillis));
    }

    public File m7064a(String str) {
        File a = super.m7053a(str);
        if (a != null && a.exists()) {
            Object obj;
            Long l = (Long) this.f4712k.get(a);
            if (l == null) {
                obj = null;
                l = Long.valueOf(a.lastModified());
            } else {
                obj = 1;
            }
            if (System.currentTimeMillis() - l.longValue() > this.f4711j) {
                a.delete();
                this.f4712k.remove(a);
            } else if (obj == null) {
                this.f4712k.put(a, l);
            }
        }
        return a;
    }

    public boolean m7065a(String str, Bitmap bitmap) {
        boolean a = super.m7056a(str, bitmap);
        m7063d(str);
        return a;
    }

    public boolean m7066a(String str, InputStream inputStream, C0947e c0947e) {
        boolean a = super.m7057a(str, inputStream, c0947e);
        m7063d(str);
        return a;
    }

    public boolean m7067b(String str) {
        this.f4712k.remove(m7061c(str));
        return super.m7060b(str);
    }

    public void m7068c() {
        super.m7062c();
        this.f4712k.clear();
    }
}
