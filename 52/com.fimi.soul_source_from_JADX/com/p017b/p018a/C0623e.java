package com.p017b.p018a;

import android.view.animation.Interpolator;
import com.fimi.soul.view.photodraweeview.C2020f;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.b.a.e */
public final class C0623e extends C0616a {
    boolean f3850b;
    private ArrayList<C0616a> f3851c;
    private HashMap<C0616a, C0629k> f3852d;
    private ArrayList<C0629k> f3853e;
    private ArrayList<C0629k> f3854f;
    private boolean f3855g;
    private C0625g f3856h;
    private boolean f3857i;
    private long f3858j;
    private as f3859k;
    private long f3860l;

    public C0623e() {
        this.f3851c = new ArrayList();
        this.f3852d = new HashMap();
        this.f3853e = new ArrayList();
        this.f3854f = new ArrayList();
        this.f3855g = true;
        this.f3856h = null;
        this.f3850b = false;
        this.f3857i = false;
        this.f3858j = 0;
        this.f3859k = null;
        this.f3860l = -1;
    }

    private void m5582o() {
        int size;
        C0629k c0629k;
        int i;
        if (this.f3855g) {
            this.f3854f.clear();
            ArrayList arrayList = new ArrayList();
            size = this.f3853e.size();
            for (int i2 = 0; i2 < size; i2++) {
                c0629k = (C0629k) this.f3853e.get(i2);
                if (c0629k.f3876b == null || c0629k.f3876b.size() == 0) {
                    arrayList.add(c0629k);
                }
            }
            Object arrayList2 = new ArrayList();
            while (arrayList.size() > 0) {
                int size2 = arrayList.size();
                for (i = 0; i < size2; i++) {
                    c0629k = (C0629k) arrayList.get(i);
                    this.f3854f.add(c0629k);
                    if (c0629k.f3879e != null) {
                        int size3 = c0629k.f3879e.size();
                        for (size = 0; size < size3; size++) {
                            C0629k c0629k2 = (C0629k) c0629k.f3879e.get(size);
                            c0629k2.f3878d.remove(c0629k);
                            if (c0629k2.f3878d.size() == 0) {
                                arrayList2.add(c0629k2);
                            }
                        }
                    }
                }
                arrayList.clear();
                arrayList.addAll(arrayList2);
                arrayList2.clear();
            }
            this.f3855g = false;
            if (this.f3854f.size() != this.f3853e.size()) {
                throw new IllegalStateException("Circular dependencies cannot exist in AnimatorSet");
            }
            return;
        }
        int size4 = this.f3853e.size();
        for (i = 0; i < size4; i++) {
            c0629k = (C0629k) this.f3853e.get(i);
            if (c0629k.f3876b != null && c0629k.f3876b.size() > 0) {
                int size5 = c0629k.f3876b.size();
                for (size = 0; size < size5; size++) {
                    C0627i c0627i = (C0627i) c0629k.f3876b.get(size);
                    if (c0629k.f3878d == null) {
                        c0629k.f3878d = new ArrayList();
                    }
                    if (!c0629k.f3878d.contains(c0627i.f3870c)) {
                        c0629k.f3878d.add(c0627i.f3870c);
                    }
                }
            }
            c0629k.f3880f = false;
        }
    }

    public C0626h m5583a(C0616a c0616a) {
        if (c0616a == null) {
            return null;
        }
        this.f3855g = true;
        return new C0626h(this, c0616a);
    }

    public void m5584a() {
        int i;
        int i2;
        ArrayList arrayList;
        int i3 = 0;
        this.f3850b = false;
        this.f3857i = true;
        m5582o();
        int size = this.f3854f.size();
        for (i = 0; i < size; i++) {
            C0629k c0629k = (C0629k) this.f3854f.get(i);
            Collection h = c0629k.f3875a.m5389h();
            if (h != null && h.size() > 0) {
                Iterator it = new ArrayList(h).iterator();
                while (it.hasNext()) {
                    C0620b c0620b = (C0620b) it.next();
                    if ((c0620b instanceof C0628j) || (c0620b instanceof C0625g)) {
                        c0629k.f3875a.m5383b(c0620b);
                    }
                }
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (i2 = 0; i2 < size; i2++) {
            c0629k = (C0629k) this.f3854f.get(i2);
            if (this.f3856h == null) {
                this.f3856h = new C0625g(this, this);
            }
            if (c0629k.f3876b == null || c0629k.f3876b.size() == 0) {
                arrayList2.add(c0629k);
            } else {
                int size2 = c0629k.f3876b.size();
                for (i = 0; i < size2; i++) {
                    C0627i c0627i = (C0627i) c0629k.f3876b.get(i);
                    c0627i.f3870c.f3875a.m5379a(new C0628j(this, c0629k, c0627i.f3871d));
                }
                c0629k.f3877c = (ArrayList) c0629k.f3876b.clone();
            }
            c0629k.f3875a.m5379a(this.f3856h);
        }
        if (this.f3858j <= 0) {
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                c0629k = (C0629k) it2.next();
                c0629k.f3875a.m5376a();
                this.f3851c.add(c0629k.f3875a);
            }
        } else {
            this.f3859k = as.m5508b(0.0f, C2020f.f10933c);
            this.f3859k.m5537d(this.f3858j);
            this.f3859k.m5379a(new C0624f(this, arrayList2));
            this.f3859k.m5518a();
        }
        if (this.a != null) {
            arrayList = (ArrayList) this.a.clone();
            i2 = arrayList.size();
            for (i = 0; i < i2; i++) {
                ((C0620b) arrayList.get(i)).m5564a(this);
            }
        }
        if (this.f3853e.size() == 0 && this.f3858j == 0) {
            this.f3857i = false;
            if (this.a != null) {
                arrayList = (ArrayList) this.a.clone();
                i = arrayList.size();
                while (i3 < i) {
                    ((C0620b) arrayList.get(i3)).m5565b(this);
                    i3++;
                }
            }
        }
    }

    public void m5585a(long j) {
        this.f3858j = j;
    }

    public void m5586a(Interpolator interpolator) {
        Iterator it = this.f3853e.iterator();
        while (it.hasNext()) {
            ((C0629k) it.next()).f3875a.m5378a(interpolator);
        }
    }

    public void m5587a(Object obj) {
        Iterator it = this.f3853e.iterator();
        while (it.hasNext()) {
            C0616a c0616a = ((C0629k) it.next()).f3875a;
            if (c0616a instanceof C0623e) {
                ((C0623e) c0616a).m5587a(obj);
            } else if (c0616a instanceof C0640v) {
                ((C0640v) c0616a).m5681a(obj);
            }
        }
    }

    public void m5588a(Collection<C0616a> collection) {
        if (collection != null && collection.size() > 0) {
            this.f3855g = true;
            C0626h c0626h = null;
            for (C0616a c0616a : collection) {
                C0626h a;
                if (c0626h == null) {
                    a = m5583a(c0616a);
                } else {
                    c0626h.m5612a(c0616a);
                    a = c0626h;
                }
                c0626h = a;
            }
        }
    }

    public void m5589a(List<C0616a> list) {
        if (list != null && list.size() > 0) {
            this.f3855g = true;
            if (list.size() == 1) {
                m5583a((C0616a) list.get(0));
                return;
            }
            for (int i = 0; i < list.size() - 1; i++) {
                m5583a((C0616a) list.get(i)).m5613b((C0616a) list.get(i + 1));
            }
        }
    }

    public void m5590a(C0616a... c0616aArr) {
        int i = 1;
        if (c0616aArr != null) {
            this.f3855g = true;
            C0626h a = m5583a(c0616aArr[0]);
            while (i < c0616aArr.length) {
                a.m5612a(c0616aArr[i]);
                i++;
            }
        }
    }

    public /* synthetic */ C0616a m5591b(long j) {
        return m5594c(j);
    }

    public void m5592b() {
        this.f3850b = true;
        if (m5599g()) {
            Iterator it;
            ArrayList arrayList;
            if (this.a != null) {
                ArrayList arrayList2 = (ArrayList) this.a.clone();
                it = arrayList2.iterator();
                while (it.hasNext()) {
                    ((C0620b) it.next()).m5566c(this);
                }
                arrayList = arrayList2;
            } else {
                arrayList = null;
            }
            if (this.f3859k != null && this.f3859k.m5540f()) {
                this.f3859k.m5530b();
            } else if (this.f3854f.size() > 0) {
                it = this.f3854f.iterator();
                while (it.hasNext()) {
                    ((C0629k) it.next()).f3875a.m5382b();
                }
            }
            if (arrayList != null) {
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    ((C0620b) it2.next()).m5565b(this);
                }
            }
            this.f3857i = false;
        }
    }

    public void m5593b(C0616a... c0616aArr) {
        int i = 0;
        if (c0616aArr != null) {
            this.f3855g = true;
            if (c0616aArr.length == 1) {
                m5583a(c0616aArr[0]);
                return;
            }
            while (i < c0616aArr.length - 1) {
                m5583a(c0616aArr[i]).m5613b(c0616aArr[i + 1]);
                i++;
            }
        }
    }

    public C0623e m5594c(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("duration must be a value of zero or greater");
        }
        Iterator it = this.f3853e.iterator();
        while (it.hasNext()) {
            ((C0629k) it.next()).f3875a.m5381b(j);
        }
        this.f3860l = j;
        return this;
    }

    public void m5595c() {
        this.f3850b = true;
        if (m5599g()) {
            Iterator it;
            if (this.f3854f.size() != this.f3853e.size()) {
                m5582o();
                it = this.f3854f.iterator();
                while (it.hasNext()) {
                    C0629k c0629k = (C0629k) it.next();
                    if (this.f3856h == null) {
                        this.f3856h = new C0625g(this, this);
                    }
                    c0629k.f3875a.m5379a(this.f3856h);
                }
            }
            if (this.f3859k != null) {
                this.f3859k.m5530b();
            }
            if (this.f3854f.size() > 0) {
                it = this.f3854f.iterator();
                while (it.hasNext()) {
                    ((C0629k) it.next()).f3875a.m5384c();
                }
            }
            if (this.a != null) {
                it = ((ArrayList) this.a.clone()).iterator();
                while (it.hasNext()) {
                    ((C0620b) it.next()).m5565b(this);
                }
            }
            this.f3857i = false;
        }
    }

    public /* synthetic */ Object clone() {
        return m5604n();
    }

    public long m5596d() {
        return this.f3858j;
    }

    public long m5597e() {
        return this.f3860l;
    }

    public boolean m5598f() {
        Iterator it = this.f3853e.iterator();
        while (it.hasNext()) {
            if (((C0629k) it.next()).f3875a.m5387f()) {
                return true;
            }
        }
        return false;
    }

    public boolean m5599g() {
        return this.f3857i;
    }

    public /* synthetic */ C0616a m5600j() {
        return m5604n();
    }

    public void m5601k() {
        Iterator it = this.f3853e.iterator();
        while (it.hasNext()) {
            ((C0629k) it.next()).f3875a.m5392k();
        }
    }

    public void m5602l() {
        Iterator it = this.f3853e.iterator();
        while (it.hasNext()) {
            ((C0629k) it.next()).f3875a.m5393l();
        }
    }

    public ArrayList<C0616a> m5603m() {
        ArrayList<C0616a> arrayList = new ArrayList();
        Iterator it = this.f3853e.iterator();
        while (it.hasNext()) {
            arrayList.add(((C0629k) it.next()).f3875a);
        }
        return arrayList;
    }

    public C0623e m5604n() {
        C0623e c0623e = (C0623e) super.m5391j();
        c0623e.f3855g = true;
        c0623e.f3850b = false;
        c0623e.f3857i = false;
        c0623e.f3851c = new ArrayList();
        c0623e.f3852d = new HashMap();
        c0623e.f3853e = new ArrayList();
        c0623e.f3854f = new ArrayList();
        HashMap hashMap = new HashMap();
        Iterator it = this.f3853e.iterator();
        while (it.hasNext()) {
            C0629k c0629k = (C0629k) it.next();
            C0629k a = c0629k.m5620a();
            hashMap.put(c0629k, a);
            c0623e.f3853e.add(a);
            c0623e.f3852d.put(a.f3875a, a);
            a.f3876b = null;
            a.f3877c = null;
            a.f3879e = null;
            a.f3878d = null;
            ArrayList h = a.f3875a.m5389h();
            if (h != null) {
                Iterator it2 = h.iterator();
                ArrayList arrayList = null;
                while (it2.hasNext()) {
                    C0620b c0620b = (C0620b) it2.next();
                    if (c0620b instanceof C0625g) {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(c0620b);
                    }
                }
                if (arrayList != null) {
                    Iterator it3 = arrayList.iterator();
                    while (it3.hasNext()) {
                        h.remove((C0620b) it3.next());
                    }
                }
            }
        }
        it = this.f3853e.iterator();
        while (it.hasNext()) {
            c0629k = (C0629k) it.next();
            a = (C0629k) hashMap.get(c0629k);
            if (c0629k.f3876b != null) {
                Iterator it4 = c0629k.f3876b.iterator();
                while (it4.hasNext()) {
                    C0627i c0627i = (C0627i) it4.next();
                    a.m5621a(new C0627i((C0629k) hashMap.get(c0627i.f3870c), c0627i.f3871d));
                }
            }
        }
        return c0623e;
    }
}
