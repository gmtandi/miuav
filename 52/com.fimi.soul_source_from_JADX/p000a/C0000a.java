package p000a;

import java.util.ArrayList;
import java.util.List;

/* renamed from: a.a */
public class C0000a extends Exception {
    private static final long serialVersionUID = 1;
    private Throwable[] f0a;

    public C0000a(String str, Throwable[] thArr) {
        Throwable th = (thArr == null || thArr.length <= 0) ? null : thArr[0];
        super(str, th);
        if (thArr == null || thArr.length <= 0) {
            thArr = null;
        }
        this.f0a = thArr;
    }

    @Deprecated
    public C0000a(List<Exception> list) {
        this("There were multiple errors.", (Throwable[]) list.toArray(new Exception[list.size()]));
    }

    @Deprecated
    public List<Exception> m0a() {
        List<Exception> arrayList = new ArrayList();
        if (this.f0a == null) {
            return arrayList;
        }
        for (Throwable th : this.f0a) {
            if (th instanceof Exception) {
                arrayList.add((Exception) th);
            } else {
                arrayList.add(new Exception(th));
            }
        }
        return arrayList;
    }

    public Throwable[] m1b() {
        return this.f0a;
    }
}
