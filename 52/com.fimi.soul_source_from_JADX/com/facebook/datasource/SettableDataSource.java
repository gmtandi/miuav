package com.facebook.datasource;

import com.facebook.common.internal.Preconditions;

public class SettableDataSource<T> extends AbstractDataSource<T> {
    private SettableDataSource() {
    }

    public static <T> SettableDataSource<T> create() {
        return new SettableDataSource();
    }

    public boolean setFailure(Throwable th) {
        return super.setFailure((Throwable) Preconditions.checkNotNull(th));
    }

    public boolean setProgress(float f) {
        return super.setProgress(f);
    }

    public boolean setResult(T t) {
        return super.setResult(Preconditions.checkNotNull(t), true);
    }

    public boolean setResult(T t, boolean z) {
        return super.setResult(Preconditions.checkNotNull(t), z);
    }
}
