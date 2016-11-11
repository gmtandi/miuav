package com.facebook.datasource;

import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class IncreasingQualityDataSourceSupplier<T> implements Supplier<DataSource<T>> {
    private final List<Supplier<DataSource<T>>> mDataSourceSuppliers;

    @ThreadSafe
    class IncreasingQualityDataSource extends AbstractDataSource<T> {
        @GuardedBy("IncreasingQualityDataSource.this")
        @Nullable
        private ArrayList<DataSource<T>> mDataSources;
        @GuardedBy("IncreasingQualityDataSource.this")
        private int mIndexOfDataSourceWithResult;

        class InternalDataSubscriber implements DataSubscriber<T> {
            private int mIndex;

            public InternalDataSubscriber(int i) {
                this.mIndex = i;
            }

            public void onCancellation(DataSource<T> dataSource) {
            }

            public void onFailure(DataSource<T> dataSource) {
                IncreasingQualityDataSource.this.onDataSourceFailed(this.mIndex, dataSource);
            }

            public void onNewResult(DataSource<T> dataSource) {
                if (dataSource.hasResult()) {
                    IncreasingQualityDataSource.this.onDataSourceNewResult(this.mIndex, dataSource);
                } else if (dataSource.isFinished()) {
                    IncreasingQualityDataSource.this.onDataSourceFailed(this.mIndex, dataSource);
                }
            }

            public void onProgressUpdate(DataSource<T> dataSource) {
                if (this.mIndex == 0) {
                    IncreasingQualityDataSource.this.setProgress(dataSource.getProgress());
                }
            }
        }

        public IncreasingQualityDataSource() {
            int size = IncreasingQualityDataSourceSupplier.this.mDataSourceSuppliers.size();
            this.mIndexOfDataSourceWithResult = size;
            this.mDataSources = new ArrayList(size);
            int i = 0;
            while (i < size) {
                DataSource dataSource = (DataSource) ((Supplier) IncreasingQualityDataSourceSupplier.this.mDataSourceSuppliers.get(i)).get();
                this.mDataSources.add(dataSource);
                dataSource.subscribe(new InternalDataSubscriber(i), CallerThreadExecutor.getInstance());
                if (!dataSource.hasResult()) {
                    i++;
                } else {
                    return;
                }
            }
        }

        private void closeSafely(DataSource<T> dataSource) {
            if (dataSource != null) {
                dataSource.close();
            }
        }

        @Nullable
        private synchronized DataSource<T> getAndClearDataSource(int i) {
            DataSource<T> dataSource = null;
            synchronized (this) {
                if (this.mDataSources != null && i < this.mDataSources.size()) {
                    dataSource = (DataSource) this.mDataSources.set(i, null);
                }
            }
            return dataSource;
        }

        @Nullable
        private synchronized DataSource<T> getDataSource(int i) {
            DataSource<T> dataSource;
            dataSource = (this.mDataSources == null || i >= this.mDataSources.size()) ? null : (DataSource) this.mDataSources.get(i);
            return dataSource;
        }

        @Nullable
        private synchronized DataSource<T> getDataSourceWithResult() {
            return getDataSource(this.mIndexOfDataSourceWithResult);
        }

        private void maybeSetIndexOfDataSourceWithResult(int i, DataSource<T> dataSource, boolean z) {
            synchronized (this) {
                int i2 = this.mIndexOfDataSourceWithResult;
                if (dataSource != getDataSource(i) || i == this.mIndexOfDataSourceWithResult) {
                    return;
                }
                if (getDataSourceWithResult() == null || (z && i < this.mIndexOfDataSourceWithResult)) {
                    this.mIndexOfDataSourceWithResult = i;
                } else {
                    i = i2;
                }
                for (int i3 = this.mIndexOfDataSourceWithResult; i3 > i; i3--) {
                    closeSafely(getAndClearDataSource(i3));
                }
            }
        }

        private void onDataSourceFailed(int i, DataSource<T> dataSource) {
            closeSafely(tryGetAndClearDataSource(i, dataSource));
            if (i == 0) {
                setFailure(dataSource.getFailureCause());
            }
        }

        private void onDataSourceNewResult(int i, DataSource<T> dataSource) {
            maybeSetIndexOfDataSourceWithResult(i, dataSource, dataSource.isFinished());
            if (dataSource == getDataSourceWithResult()) {
                boolean z = i == 0 && dataSource.isFinished();
                setResult(null, z);
            }
        }

        @Nullable
        private synchronized DataSource<T> tryGetAndClearDataSource(int i, DataSource<T> dataSource) {
            if (dataSource == getDataSourceWithResult()) {
                dataSource = null;
            } else if (dataSource == getDataSource(i)) {
                dataSource = getAndClearDataSource(i);
            }
            return dataSource;
        }

        public boolean close() {
            synchronized (this) {
                if (super.close()) {
                    ArrayList arrayList = this.mDataSources;
                    this.mDataSources = null;
                    if (arrayList != null) {
                        for (int i = 0; i < arrayList.size(); i++) {
                            closeSafely((DataSource) arrayList.get(i));
                        }
                    }
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

    private IncreasingQualityDataSourceSupplier(List<Supplier<DataSource<T>>> list) {
        Preconditions.checkArgument(!list.isEmpty(), "List of suppliers is empty!");
        this.mDataSourceSuppliers = list;
    }

    public static <T> IncreasingQualityDataSourceSupplier<T> create(List<Supplier<DataSource<T>>> list) {
        return new IncreasingQualityDataSourceSupplier(list);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof IncreasingQualityDataSourceSupplier)) {
            return false;
        }
        return Objects.equal(this.mDataSourceSuppliers, ((IncreasingQualityDataSourceSupplier) obj).mDataSourceSuppliers);
    }

    public DataSource<T> get() {
        return new IncreasingQualityDataSource();
    }

    public int hashCode() {
        return this.mDataSourceSuppliers.hashCode();
    }

    public String toString() {
        return Objects.toStringHelper((Object) this).add("list", this.mDataSourceSuppliers).toString();
    }
}
