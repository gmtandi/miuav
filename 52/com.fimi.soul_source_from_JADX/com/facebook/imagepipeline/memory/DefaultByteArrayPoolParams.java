package com.facebook.imagepipeline.memory;

import android.util.SparseIntArray;

public class DefaultByteArrayPoolParams {
    private static final int DEFAULT_BUCKET_SIZE = 5;
    private static final int DEFAULT_IO_BUFFER_SIZE = 16384;
    private static final int MAX_SIZE_HARD_CAP = 1048576;
    private static final int MAX_SIZE_SOFT_CAP = 81920;

    public static PoolParams get() {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sparseIntArray.put(DEFAULT_IO_BUFFER_SIZE, DEFAULT_BUCKET_SIZE);
        return new PoolParams(MAX_SIZE_SOFT_CAP, MAX_SIZE_HARD_CAP, sparseIntArray);
    }
}