package com.facebook.datasource;

import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class FirstAvailableDataSourceSupplier<T> implements Supplier<DataSource<T>> {
    private final List<Supplier<DataSource<T>>> mDataSourceSuppliers;

    @ThreadSafe
    class FirstAvailableDataSource extends AbstractDataSource<T> {
        private DataSource<T> mCurrentDataSource;
        private DataSource<T> mDataSourceWithResult;
        private int mIndex;

        class InternalDataSubscriber implements DataSubscriber<T> {
            private InternalDataSubscriber() {
            }

            public void onCancellation(DataSource<T> dataSource) {
            }

            public void onFailure(DataSource<T> dataSource) {
                FirstAvailableDataSource.this.onDataSourceFailed(dataSource);
            }

            public void onNewResult(DataSource<T> dataSource) {
                if (dataSource.hasResult()) {
                    FirstAvailableDataSource.this.onDataSourceNewResult(dataSource);
                } else if (dataSource.isFinished()) {
                    FirstAvailableDataSource.this.onDataSourceFailed(dataSource);
                }
            }

            public void onProgressUpdate(DataSource<T> dataSource) {
                FirstAvailableDataSource.this.setProgress(Math.max(FirstAvailableDataSource.this.getProgress(), dataSource.getProgress()));
            }
        }

        public FirstAvailableDataSource() {
            this.mIndex = 0;
            this.mCurrentDataSource = null;
            this.mDataSourceWithResult = null;
            if (!startNextDataSource()) {
                setFailure(new RuntimeException("No data source supplier or supplier returned null."));
            }
        }

        private synchronized boolean clearCurrentDataSource(DataSource<T> dataSource) {
            boolean z;
            if (isClosed() || dataSource != this.mCurrentDataSource) {
                z = false;
            } else {
                this.mCurrentDataSource = null;
                z = true;
            }
            return z;
        }

        private void closeSafely(DataSource<T> dataSource) {
            if (dataSource != null) {
                dataSource.close();
            }
        }

        @Nullable
        private synchronized DataSource<T> getDataSourceWithResult() {
            return this.mDataSourceWithResult;
        }

        @Nullable
        private synchronized Supplier<DataSource<T>> getNextSupplier() {
            Supplier<DataSource<T>> supplier;
            if (isClosed() || this.mIndex >= FirstAvailableDataSourceSupplier.this.mDataSourceSuppliers.size()) {
                supplier = null;
            } else {
                List access$100 = FirstAvailableDataSourceSupplier.this.mDataSourceSuppliers;
                int i = this.mIndex;
                this.mIndex = i + 1;
                supplier = (Supplier) access$100.get(i);
            }
            return supplier;
        }

        private void maybeSetDataSourceWithResult(DataSource<T> dataSource, boolean z) {
            DataSource dataSource2 = null;
            synchronized (this) {
                if (dataSource != this.mCurrentDataSource || dataSource == this.mDataSourceWithResult) {
                    return;
                }
                if (this.mDataSourceWithResult == null || z) {
                    dataSource2 = this.mDataSourceWithResult;
                    this.mDataSourceWithResult = dataSource;
                }
                closeSafely(dataSource2);
            }
        }

        private void onDataSourceFailed(DataSource<T> dataSource) {
            if (clearCurrentDataSource(dataSource)) {
                if (dataSource != getDataSourceWithResult()) {
                    closeSafely(dataSource);
                }
                if (!startNextDataSource()) {
                    setFailure(dataSource.getFailureCause());
                }
            }
        }

        private void onDataSourceNewResult(DataSource<T> dataSource) {
            maybeSetDataSourceWithResult(dataSource, dataSource.isFinished());
            if (dataSource == getDataSourceWithResult()) {
                setResult(null, dataSource.isFinished());
            }
        }

        private synchronized boolean setCurrentDataSource(DataSource<T> dataSource) {
            boolean z;
            if (isClosed()) {
                z = false;
            } else {
                this.mCurrentDataSource = dataSource;
                z = true;
            }
            return z;
        }

        private boolean startNextDataSource() {
            Supplier nextSupplier = getNextSupplier();
            DataSource dataSource = nextSupplier != null ? (DataSource) nextSupplier.get() : null;
            if (!setCurrentDataSource(dataSource) || dataSource == null) {
                closeSafely(dataSource);
                return false;
            }
            dataSource.subscribe(new InternalDataSubscriber(), CallerThreadExecutor.getInstance());
            return true;
        }

        public boolean close() {
            synchronized (this) {
                if (super.close()) {
                    DataSource dataSource = this.mCurrentDataSource;
                    this.mCurrentDataSource = null;
                    DataSource dataSource2 = this.mDataSourceWithResult;
                    this.mDataSourceWithResult = null;
                    closeSafely(dataSource2);
                    closeSafely(dataSource);
                    return true;
                }
                return false;
            }
        }

        @Nullable
        public synchronized T getResult() {
            DataSource dataSourceWithResult;
            dataSourceWithResult = getDataSourceWithResult();
            return dataSourceWithResult != null ? dataSourceWithResult.getResult() : null;
        }

        public synchronized boolean hasResult() {
            boolean z;
            DataSource dataSourceWithResult = getDataSourceWithResult();
            z = dataSourceWithResult != null && dataSourceWithResult.hasResult();
            return z;
        }
    }

    private FirstAvailableDataSourceSupplier(List<Supplier<DataSource<T>>> list) {
        Preconditions.checkArgument(!list.isEmpty(), "List of suppliers is empty!");
        this.mDataSourceSuppliers = list;
    }

    public static <T> FirstAvailableDataSourceSupplier<T> create(List<Supplier<DataSource<T>>> list) {
        return new FirstAvailableDataSourceSupplier(list);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FirstAvailableDataSourceSupplier)) {
            return false;
        }
        return Objects.equal(this.mDataSourceSuppliers, ((FirstAvailableDataSourceSupplier) obj).mDataSourceSuppliers);
    }

    public DataSource<T> get() {
        return new FirstAvailableDataSource();
    }

    public int hashCode() {
        return this.mDataSourceSuppliers.hashCode();
    }

    public String toString() {
        return Objects.toStringHelper((Object) this).add("list", this.mDataSourceSuppliers).toString();
    }
}
