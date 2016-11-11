package com.autonavi.amap.mapcore;

/* renamed from: com.autonavi.amap.mapcore.e */
class C0613e {
    String f3725a;
    int f3726b;
    int f3727c;
    int f3728d;

    public C0613e(String str, int i) {
        this.f3728d = 0;
        if (str != null) {
            try {
                if (str.length() != 0) {
                    this.f3726b = (int) (System.currentTimeMillis() / 1000);
                    this.f3727c = i;
                    this.f3725a = str;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                this.f3725a = null;
            }
        }
    }
}
