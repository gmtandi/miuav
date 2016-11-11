package com.p016a;

import java.util.LinkedHashMap;

/* renamed from: com.a.dc */
public class dc<K, V> {
    private final LinkedHashMap<K, V> f828a;
    private int f829b;
    private int f830c;

    public dc(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.f830c = i;
        this.f828a = new LinkedHashMap(0, 0.75f, true);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m1428a(int r6) {
        /*
        r5 = this;
        r1 = 0;
    L_0x0001:
        monitor-enter(r5);
        r0 = r5.f829b;	 Catch:{ all -> 0x0033 }
        if (r0 < 0) goto L_0x0012;
    L_0x0006:
        r0 = r5.f828a;	 Catch:{ all -> 0x0033 }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x0033 }
        if (r0 == 0) goto L_0x0036;
    L_0x000e:
        r0 = r5.f829b;	 Catch:{ all -> 0x0033 }
        if (r0 == 0) goto L_0x0036;
    L_0x0012:
        r0 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x0033 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0033 }
        r1.<init>();	 Catch:{ all -> 0x0033 }
        r2 = r5.getClass();	 Catch:{ all -> 0x0033 }
        r2 = r2.getName();	 Catch:{ all -> 0x0033 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0033 }
        r2 = ".sizeOf() is reporting inconsistent results!";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0033 }
        r1 = r1.toString();	 Catch:{ all -> 0x0033 }
        r0.<init>(r1);	 Catch:{ all -> 0x0033 }
        throw r0;	 Catch:{ all -> 0x0033 }
    L_0x0033:
        r0 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x0033 }
        throw r0;
    L_0x0036:
        r0 = r5.f829b;	 Catch:{ all -> 0x0033 }
        if (r0 > r6) goto L_0x003c;
    L_0x003a:
        monitor-exit(r5);	 Catch:{ all -> 0x0033 }
    L_0x003b:
        return;
    L_0x003c:
        r0 = r5.f828a;	 Catch:{ all -> 0x0033 }
        r0 = r0.entrySet();	 Catch:{ all -> 0x0033 }
        r2 = r0.iterator();	 Catch:{ all -> 0x0033 }
        r0 = r1;
    L_0x0047:
        r3 = r2.hasNext();	 Catch:{ all -> 0x0033 }
        if (r3 == 0) goto L_0x0054;
    L_0x004d:
        r0 = r2.next();	 Catch:{ all -> 0x0033 }
        r0 = (java.util.Map.Entry) r0;	 Catch:{ all -> 0x0033 }
        goto L_0x0047;
    L_0x0054:
        if (r0 != 0) goto L_0x0058;
    L_0x0056:
        monitor-exit(r5);	 Catch:{ all -> 0x0033 }
        goto L_0x003b;
    L_0x0058:
        r2 = r0.getKey();	 Catch:{ all -> 0x0033 }
        r0 = r0.getValue();	 Catch:{ all -> 0x0033 }
        r3 = r5.f828a;	 Catch:{ all -> 0x0033 }
        r3.remove(r2);	 Catch:{ all -> 0x0033 }
        r3 = r5.f829b;	 Catch:{ all -> 0x0033 }
        r4 = r5.m1429c(r2, r0);	 Catch:{ all -> 0x0033 }
        r3 = r3 - r4;
        r5.f829b = r3;	 Catch:{ all -> 0x0033 }
        monitor-exit(r5);	 Catch:{ all -> 0x0033 }
        r3 = 1;
        r5.m1433a(r3, r2, r0, r1);
        goto L_0x0001;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.dc.a(int):void");
    }

    private int m1429c(K k, V v) {
        int a = m1430a(k, v);
        if (a >= 0) {
            return a;
        }
        throw new IllegalStateException("Negative size: " + k + "=" + v);
    }

    protected int m1430a(K k, V v) {
        return 1;
    }

    protected V m1431a(K k) {
        return null;
    }

    public final void m1432a() {
        m1428a(-1);
    }

    protected void m1433a(boolean z, K k, V v, V v2) {
    }

    public final V m1434b(K k) {
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        synchronized (this) {
            V v = this.f828a.get(k);
            if (v != null) {
                return v;
            }
            V a = m1431a((Object) k);
            if (a == null) {
                return null;
            }
            synchronized (this) {
                v = this.f828a.put(k, a);
                if (v != null) {
                    this.f828a.put(k, v);
                } else {
                    this.f829b += m1429c(k, a);
                }
            }
            if (v != null) {
                m1433a(false, k, a, v);
                return v;
            }
            m1428a(this.f830c);
            return a;
        }
    }

    public final V m1435b(K k, V v) {
        if (k == null || v == null) {
            throw new NullPointerException("key == null || value == null");
        }
        V put;
        synchronized (this) {
            this.f829b += m1429c(k, v);
            put = this.f828a.put(k, v);
            if (put != null) {
                this.f829b -= m1429c(k, put);
            }
        }
        if (put != null) {
            m1433a(false, k, put, v);
        }
        m1428a(this.f830c);
        return put;
    }

    public final V m1436c(K k) {
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        V remove;
        synchronized (this) {
            remove = this.f828a.remove(k);
            if (remove != null) {
                this.f829b -= m1429c(k, remove);
            }
        }
        if (remove != null) {
            m1433a(false, k, remove, null);
        }
        return remove;
    }
}
