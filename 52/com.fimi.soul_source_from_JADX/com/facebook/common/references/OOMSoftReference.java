package com.facebook.common.references;

import java.lang.ref.SoftReference;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class OOMSoftReference<T> {
    SoftReference<T> softRef1;
    SoftReference<T> softRef2;
    SoftReference<T> softRef3;

    public OOMSoftReference() {
        this.softRef1 = null;
        this.softRef2 = null;
        this.softRef3 = null;
    }

    public void clear() {
        if (this.softRef1 != null) {
            this.softRef1.clear();
            this.softRef1 = null;
        }
        if (this.softRef2 != null) {
            this.softRef2.clear();
            this.softRef2 = null;
        }
        if (this.softRef3 != null) {
            this.softRef3.clear();
            this.softRef3 = null;
        }
    }

    @Nullable
    public T get() {
        return this.softRef1 == null ? null : this.softRef1.get();
    }

    public void set(@Nonnull T t) {
        this.softRef1 = new SoftReference(t);
        this.softRef2 = new SoftReference(t);
        this.softRef3 = new SoftReference(t);
    }
}
