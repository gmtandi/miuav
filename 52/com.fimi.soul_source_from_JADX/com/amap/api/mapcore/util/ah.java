package com.amap.api.mapcore.util;

public interface ah {

    /* renamed from: com.amap.api.mapcore.util.ah.a */
    public enum C0341a {
        amap_exception(-1),
        network_exception(-1),
        file_io_exception(0),
        success_no_exception(1),
        cancel_no_exception(2);
        
        private int f2113f;

        private C0341a(int i) {
            this.f2113f = i;
        }
    }

    void m3423a(long j, long j2);

    void m3424a(C0341a c0341a);

    void m3425m();

    void m3426n();

    void m3427o();
}
