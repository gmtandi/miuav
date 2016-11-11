package com.facebook.imagepipeline.cache;

import android.net.Uri;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.SimpleCacheKey;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.Postprocessor;

public class DefaultCacheKeyFactory implements CacheKeyFactory {
    private static DefaultCacheKeyFactory sInstance;

    static {
        sInstance = null;
    }

    protected DefaultCacheKeyFactory() {
    }

    public static synchronized DefaultCacheKeyFactory getInstance() {
        DefaultCacheKeyFactory defaultCacheKeyFactory;
        synchronized (DefaultCacheKeyFactory.class) {
            if (sInstance == null) {
                sInstance = new DefaultCacheKeyFactory();
            }
            defaultCacheKeyFactory = sInstance;
        }
        return defaultCacheKeyFactory;
    }

    public CacheKey getBitmapCacheKey(ImageRequest imageRequest) {
        return new BitmapMemoryCacheKey(getCacheKeySourceUri(imageRequest.getSourceUri()).toString(), imageRequest.getResizeOptions(), imageRequest.getAutoRotateEnabled(), imageRequest.getImageDecodeOptions(), null, null);
    }

    public Uri getCacheKeySourceUri(Uri uri) {
        return uri;
    }

    public CacheKey getEncodedCacheKey(ImageRequest imageRequest) {
        return new SimpleCacheKey(getCacheKeySourceUri(imageRequest.getSourceUri()).toString());
    }

    public CacheKey getPostprocessedBitmapCacheKey(ImageRequest imageRequest) {
        CacheKey postprocessorCacheKey;
        String str = null;
        Postprocessor postprocessor = imageRequest.getPostprocessor();
        if (postprocessor != null) {
            postprocessorCacheKey = postprocessor.getPostprocessorCacheKey();
            str = postprocessor.getClass().getName();
        } else {
            postprocessorCacheKey = null;
        }
        return new BitmapMemoryCacheKey(getCacheKeySourceUri(imageRequest.getSourceUri()).toString(), imageRequest.getResizeOptions(), imageRequest.getAutoRotateEnabled(), imageRequest.getImageDecodeOptions(), postprocessorCacheKey, str);
    }
}
