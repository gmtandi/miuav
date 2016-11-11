package com.facebook.imagepipeline.producers;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.PooledByteBufferFactory;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.IOException;
import java.util.concurrent.Executor;

public class LocalAssetFetchProducer extends LocalFetchProducer {
    @VisibleForTesting
    static final String PRODUCER_NAME = "LocalAssetFetchProducer";
    private final AssetManager mAssetManager;

    public LocalAssetFetchProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory, AssetManager assetManager, boolean z) {
        super(executor, pooledByteBufferFactory, z);
        this.mAssetManager = assetManager;
    }

    private static String getAssetName(ImageRequest imageRequest) {
        return imageRequest.getSourceUri().getPath().substring(1);
    }

    private int getLength(ImageRequest imageRequest) {
        int length;
        Throwable th;
        AssetFileDescriptor openFd;
        try {
            openFd = this.mAssetManager.openFd(getAssetName(imageRequest));
            try {
                length = (int) openFd.getLength();
                if (openFd != null) {
                    try {
                        openFd.close();
                    } catch (IOException e) {
                    }
                }
            } catch (IOException e2) {
                length = -1;
                if (openFd != null) {
                    try {
                        openFd.close();
                    } catch (IOException e3) {
                    }
                }
                return length;
            } catch (Throwable th2) {
                th = th2;
                if (openFd != null) {
                    try {
                        openFd.close();
                    } catch (IOException e4) {
                    }
                }
                throw th;
            }
        } catch (IOException e5) {
            openFd = null;
            length = -1;
            if (openFd != null) {
                openFd.close();
            }
            return length;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            openFd = null;
            th = th4;
            if (openFd != null) {
                openFd.close();
            }
            throw th;
        }
        return length;
    }

    protected EncodedImage getEncodedImage(ImageRequest imageRequest) {
        return getEncodedImage(this.mAssetManager.open(getAssetName(imageRequest), 2), getLength(imageRequest));
    }

    protected String getProducerName() {
        return PRODUCER_NAME;
    }
}
