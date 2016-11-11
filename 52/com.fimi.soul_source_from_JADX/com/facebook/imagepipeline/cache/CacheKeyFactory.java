package com.facebook.imagepipeline.cache;

import android.net.Uri;
import com.facebook.cache.common.CacheKey;
import com.facebook.imagepipeline.request.ImageRequest;

public interface CacheKeyFactory {
    CacheKey getBitmapCacheKey(ImageRequest imageRequest);

    Uri getCacheKeySourceUri(Uri uri);

    CacheKey getEncodedCacheKey(ImageRequest imageRequest);

    CacheKey getPostprocessedBitmapCacheKey(ImageRequest imageRequest);
}
