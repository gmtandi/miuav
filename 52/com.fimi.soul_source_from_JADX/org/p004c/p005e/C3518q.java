package org.p004c.p005e;

import java.io.ObjectInputStream;
import java.io.ObjectInputStream.GetField;
import java.io.ObjectOutputStream;
import java.io.ObjectOutputStream.PutField;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.p004c.p005e.p007b.C3493a;

/* renamed from: org.c.e.q */
class C3518q implements Serializable {
    private static final long serialVersionUID = 1;
    private final AtomicInteger f16064a;
    private final AtomicInteger f16065b;
    private final List<C3493a> f16066c;
    private final long f16067d;
    private final long f16068e;

    private C3518q(GetField getField) {
        this.f16064a = (AtomicInteger) getField.get("fCount", null);
        this.f16065b = (AtomicInteger) getField.get("fIgnoreCount", null);
        this.f16066c = (List) getField.get("fFailures", null);
        this.f16067d = getField.get("fRunTime", 0);
        this.f16068e = getField.get("fStartTime", 0);
    }

    public C3518q(C3515n c3515n) {
        this.f16064a = c3515n.f16057a;
        this.f16065b = c3515n.f16058b;
        this.f16066c = Collections.synchronizedList(new ArrayList(c3515n.f16059c));
        this.f16067d = c3515n.f16060d.longValue();
        this.f16068e = c3515n.f16061e.longValue();
    }

    public static C3518q m19154a(ObjectInputStream objectInputStream) {
        return new C3518q(objectInputStream.readFields());
    }

    public void m19159a(ObjectOutputStream objectOutputStream) {
        PutField putFields = objectOutputStream.putFields();
        putFields.put("fCount", this.f16064a);
        putFields.put("fIgnoreCount", this.f16065b);
        putFields.put("fFailures", this.f16066c);
        putFields.put("fRunTime", this.f16067d);
        putFields.put("fStartTime", this.f16068e);
        objectOutputStream.writeFields();
    }
}
