package com.amap.api.mapcore.util;

import java.util.LinkedHashMap;

public class bf<K, V> {
    private final LinkedHashMap<K, V> f2161a;
    private int f2162b;
    private int f2163c;
    private int f2164d;
    private int f2165e;
    private int f2166f;
    private int f2167g;
    private int f2168h;

    public bf(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.f2163c = i;
        this.f2161a = new LinkedHashMap(0, 0.75f, true);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m3511a(int r6) {
        /*
        r5 = this;
        r1 = 0;
    L_0x0001:
        monitor-enter(r5);
        r0 = r5.f2162b;	 Catch:{ all -> 0x0034 }
        if (r0 < 0) goto L_0x0012;
    L_0x0006:
        r0 = r5.f2161a;	 Catch:{ all -> 0x0034 }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x0034 }
        if (r0 == 0) goto L_0x0012;
    L_0x000e:
        r0 = r5.f2162b;	 Catch:{ all -> 0x0034 }
        if (r0 == 0) goto L_0x0012;
    L_0x0012:
        r0 = r5.f2162b;	 Catch:{ all -> 0x0034 }
        if (r0 > r6) goto L_0x0018;
    L_0x0016:
        monitor-exit(r5);	 Catch:{ all -> 0x0034 }
    L_0x0017:
        return;
    L_0x0018:
        r0 = r5.f2161a;	 Catch:{ all -> 0x0034 }
        r0 = r0.entrySet();	 Catch:{ all -> 0x0034 }
        r2 = r0.iterator();	 Catch:{ all -> 0x0034 }
        r0 = r1;
    L_0x0023:
        r3 = r2.hasNext();	 Catch:{ all -> 0x0034 }
        if (r3 == 0) goto L_0x0030;
    L_0x0029:
        r0 = r2.next();	 Catch:{ all -> 0x0034 }
        r0 = (java.util.Map.Entry) r0;	 Catch:{ all -> 0x0034 }
        goto L_0x0023;
    L_0x0030:
        if (r0 != 0) goto L_0x0037;
    L_0x0032:
        monitor-exit(r5);	 Catch:{ all -> 0x0034 }
        goto L_0x0017;
    L_0x0034:
        r0 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x0034 }
        throw r0;
    L_0x0037:
        r2 = r0.getKey();	 Catch:{ all -> 0x0034 }
        r0 = r0.getValue();	 Catch:{ all -> 0x0034 }
        r3 = r5.f2161a;	 Catch:{ all -> 0x0034 }
        r3.remove(r2);	 Catch:{ all -> 0x0034 }
        r3 = r5.f2162b;	 Catch:{ all -> 0x0034 }
        r4 = r5.m3512c(r2, r0);	 Catch:{ all -> 0x0034 }
        r3 = r3 - r4;
        r5.f2162b = r3;	 Catch:{ all -> 0x0034 }
        r3 = r5.f2166f;	 Catch:{ all -> 0x0034 }
        r3 = r3 + 1;
        r5.f2166f = r3;	 Catch:{ all -> 0x0034 }
        monitor-exit(r5);	 Catch:{ all -> 0x0034 }
        r3 = 1;
        r5.m3516a(r3, r2, r0, r1);
        goto L_0x0001;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.bf.a(int):void");
    }

    private int m3512c(K k, V v) {
        int a = m3513a(k, v);
        if (a >= 0) {
            return a;
        }
        throw new IllegalStateException("Negative size: " + k + "=" + v);
    }

    protected int m3513a(K k, V v) {
        return 1;
    }

    public final V m3514a(K k) {
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        synchronized (this) {
            V v = this.f2161a.get(k);
            if (v != null) {
                this.f2167g++;
                return v;
            }
            this.f2168h++;
            V b = m3517b(k);
            if (b == null) {
                return null;
            }
            synchronized (this) {
                this.f2165e++;
                v = this.f2161a.put(k, b);
                if (v != null) {
                    this.f2161a.put(k, v);
                } else {
                    this.f2162b += m3512c(k, b);
                }
            }
            if (v != null) {
                m3516a(false, k, b, v);
                return v;
            }
            m3511a(this.f2163c);
            return b;
        }
    }

    public final void m3515a() {
        m3511a(-1);
    }

    protected void m3516a(boolean z, K k, V v, V v2) {
    }

    protected V m3517b(K k) {
        return null;
    }

    public final V m3518b(K k, V v) {
        if (k == null || v == null) {
            throw new NullPointerException("key == null || value == null");
        }
        V put;
        synchronized (this) {
            this.f2164d++;
            this.f2162b += m3512c(k, v);
            put = this.f2161a.put(k, v);
            if (put != null) {
                this.f2162b -= m3512c(k, put);
            }
        }
        if (put != null) {
            m3516a(false, k, put, v);
        }
        m3511a(this.f2163c);
        return put;
    }

    public final synchronized String toString() {
        String format;
        int i = 0;
        synchronized (this) {
            int i2 = this.f2167g + this.f2168h;
            if (i2 != 0) {
                i = (this.f2167g * 100) / i2;
            }
            format = String.format("LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", new Object[]{Integer.valueOf(this.f2163c), Integer.valueOf(this.f2167g), Integer.valueOf(this.f2168h), Integer.valueOf(i)});
        }
        return format;
    }
}
