package p147m.framework.p148a;

import java.io.InputStream;
import org.apache.http.entity.InputStreamEntity;

/* renamed from: m.framework.a.c */
public abstract class C2846c {
    protected abstract InputStream m16415a();

    protected abstract long m16416b();

    public InputStreamEntity m16417c() {
        return new InputStreamEntity(m16415a(), m16416b());
    }
}
