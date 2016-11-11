package it.p074a.p075a.p141b;

import it.p074a.p075a.C2784u;
import java.util.ArrayList;

/* renamed from: it.a.a.b.b */
public class C2785b implements C2784u {
    private ArrayList f14183a;

    public C2785b() {
        this.f14183a = new ArrayList();
    }

    public C2785b(ArrayList arrayList) {
        this.f14183a = new ArrayList();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Object obj = arrayList.get(i);
            if (obj instanceof String) {
                m15860b((String) obj);
            }
        }
    }

    public C2785b(String[] strArr) {
        this.f14183a = new ArrayList();
        for (String b : strArr) {
            m15860b(b);
        }
    }

    public boolean m15859a(String str) {
        boolean contains;
        synchronized (this.f14183a) {
            contains = this.f14183a.contains(str);
        }
        return contains;
    }

    public void m15860b(String str) {
        synchronized (this.f14183a) {
            this.f14183a.add(str.toLowerCase());
        }
    }

    public String[] m15861b() {
        String[] strArr;
        synchronized (this.f14183a) {
            int size = this.f14183a.size();
            strArr = new String[size];
            for (int i = 0; i < size; i++) {
                strArr[i] = (String) this.f14183a.get(i);
            }
        }
        return strArr;
    }

    public void m15862c(String str) {
        synchronized (this.f14183a) {
            this.f14183a.remove(str.toLowerCase());
        }
    }
}
