package com.facebook.datasource;

public abstract class BaseDataSubscriber<T> implements DataSubscriber<T> {
    public void onCancellation(DataSource<T> dataSource) {
    }

    public void onFailure(DataSource<T> dataSource) {
        try {
            onFailureImpl(dataSource);
        } finally {
            dataSource.close();
        }
    }

    protected abstract void onFailureImpl(DataSource<T> dataSource);

    public void onNewResult(DataSource<T> dataSource) {
        try {
            onNewResultImpl(dataSource);
            if (dataSource.isFinished()) {
                dataSource.close();
            }
        } catch (Throwable th) {
            if (dataSource.isFinished()) {
                dataSource.close();
            }
        }
    }

    protected abstract void onNewResultImpl(DataSource<T> dataSource);

    public void onProgressUpdate(DataSource<T> dataSource) {
    }
}
