package com.p054c.p055a.p056a.p061b.p062a;

import android.graphics.Bitmap;
import com.p054c.p055a.p056a.p061b.C0875c;
import java.util.Collection;
import java.util.Comparator;

/* renamed from: com.c.a.a.b.a.b */
public class C0879b implements C0875c {
    private final C0875c f4722a;
    private final Comparator<String> f4723b;

    public C0879b(C0875c c0875c, Comparator<String> comparator) {
        this.f4722a = c0875c;
        this.f4723b = comparator;
    }

    public Bitmap m7096a(String str) {
        return this.f4722a.m7073a(str);
    }

    public Collection<String> m7097a() {
        return this.f4722a.m7074a();
    }

    public boolean m7098a(String str, Bitmap bitmap) {
        synchronized (this.f4722a) {
            for (String str2 : this.f4722a.m7074a()) {
                if (this.f4723b.compare(str, str2) == 0) {
                    break;
                }
            }
            String str22 = null;
            if (str22 != null) {
                this.f4722a.m7076b(str22);
            }
        }
        return this.f4722a.m7075a(str, bitmap);
    }

    public Bitmap m7099b(String str) {
        return this.f4722a.m7076b(str);
    }

    public void m7100b() {
        this.f4722a.m7077b();
    }
}
