package com.facebook.datasource;

public abstract class BaseBooleanSubscriber implements DataSubscriber<Boolean> {
    public void onCancellation(DataSource<Boolean> dataSource) {
    }

    public void onFailure(DataSource<Boolean> dataSource) {
        try {
            onFailureImpl(dataSource);
        } finally {
            dataSource.close();
        }
    }

    protected abstract void onFailureImpl(DataSource<Boolean> dataSource);

    public void onNewResult(DataSource<Boolean> dataSource) {
        try {
            onNewResultImpl(((Boolean) dataSource.getResult()).booleanValue());
        } finally {
            dataSource.close();
        }
    }

    protected abstract void onNewResultImpl(boolean z);

    public void onProgressUpdate(DataSource<Boolean> dataSource) {
    }
}
