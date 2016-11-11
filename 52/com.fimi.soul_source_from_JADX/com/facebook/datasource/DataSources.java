package com.facebook.datasource;

import com.facebook.common.internal.Supplier;

public class DataSources {

    /* renamed from: com.facebook.datasource.DataSources.1 */
    final class C09781 implements Supplier<DataSource<T>> {
        final /* synthetic */ Throwable val$failure;

        C09781(Throwable th) {
            this.val$failure = th;
        }

        public DataSource<T> get() {
            return DataSources.immediateFailedDataSource(this.val$failure);
        }
    }

    private DataSources() {
    }

    public static <T> Supplier<DataSource<T>> getFailedDataSourceSupplier(Throwable th) {
        return new C09781(th);
    }

    public static <T> DataSource<T> immediateFailedDataSource(Throwable th) {
        DataSource create = SettableDataSource.create();
        create.setFailure(th);
        return create;
    }
}
