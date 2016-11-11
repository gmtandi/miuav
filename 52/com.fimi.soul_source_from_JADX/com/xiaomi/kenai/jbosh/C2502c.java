package com.xiaomi.kenai.jbosh;

/* renamed from: com.xiaomi.kenai.jbosh.c */
abstract class C2502c extends C2498a<Integer> {
    protected C2502c(String str) {
        super(Integer.valueOf(C2502c.m14372a(str)));
    }

    private static int m14372a(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Throwable e) {
            throw new aa("Could not parse an integer from the value provided: " + str, e);
        }
    }

    protected final void m14373a(int i) {
        int intValue = ((Integer) m14304a()).intValue();
        if (intValue < i) {
            throw new aa("Illegal attribute value '" + intValue + "' provided.  " + "Must be >= " + i);
        }
    }

    public int m14374b() {
        return ((Integer) m14304a()).intValue();
    }
}
