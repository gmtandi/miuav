package org.p004c.p188a.p195e;

/* renamed from: org.c.a.e.h */
class C3373h extends C3359g {
    final /* synthetic */ Object f15868a;
    final /* synthetic */ String f15869b;

    C3373h(Object obj, String str) {
        this.f15868a = obj;
        this.f15869b = str;
    }

    public Object m18544a() {
        return this.f15868a;
    }

    public String m18545b() {
        String str;
        if (this.f15868a == null) {
            str = "null";
        } else {
            try {
                str = String.format("\"%s\"", new Object[]{this.f15868a});
            } catch (Throwable th) {
                str = String.format("[toString() threw %s: %s]", new Object[]{th.getClass().getSimpleName(), th.getMessage()});
            }
        }
        return String.format("%s <from %s>", new Object[]{str, this.f15869b});
    }

    public String toString() {
        return String.format("[%s]", new Object[]{this.f15868a});
    }
}
