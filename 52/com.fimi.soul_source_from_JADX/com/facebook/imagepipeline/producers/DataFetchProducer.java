package com.facebook.imagepipeline.producers;

import android.net.Uri;
import android.util.Base64;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.PooledByteBufferFactory;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.ByteArrayInputStream;

public class DataFetchProducer extends LocalFetchProducer {
    private static final String PRODUCER_NAME = "DataFetchProducer";

    public DataFetchProducer(PooledByteBufferFactory pooledByteBufferFactory, boolean z) {
        super(CallerThreadExecutor.getInstance(), pooledByteBufferFactory, z);
    }

    @VisibleForTesting
    static byte[] getData(String str) {
        Preconditions.checkArgument(str.substring(0, 5).equals("data:"));
        int indexOf = str.indexOf(44);
        String substring = str.substring(indexOf + 1, str.length());
        return isBase64(str.substring(0, indexOf)) ? Base64.decode(substring, 0) : Uri.decode(substring).getBytes();
    }

    @VisibleForTesting
    static boolean isBase64(String str) {
        if (!str.contains(";")) {
            return false;
        }
        String[] split = str.split(";");
        return split[split.length - 1].equals("base64");
    }

    protected EncodedImage getEncodedImage(ImageRequest imageRequest) {
        byte[] data = getData(imageRequest.getSourceUri().toString());
        return getByteBufferBackedEncodedImage(new ByteArrayInputStream(data), data.length);
    }

    protected String getProducerName() {
        return PRODUCER_NAME;
    }
}
