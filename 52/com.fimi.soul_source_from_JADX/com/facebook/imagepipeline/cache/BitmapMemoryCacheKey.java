package com.facebook.imagepipeline.cache;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.util.HashCodeUtil;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.common.ResizeOptions;
import java.util.Locale;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public class BitmapMemoryCacheKey implements CacheKey {
    private final boolean mAutoRotated;
    private final int mHash;
    private final ImageDecodeOptions mImageDecodeOptions;
    @Nullable
    private final CacheKey mPostprocessorCacheKey;
    @Nullable
    private final String mPostprocessorName;
    @Nullable
    private final ResizeOptions mResizeOptions;
    private final String mSourceString;

    public BitmapMemoryCacheKey(String str, @Nullable ResizeOptions resizeOptions, boolean z, ImageDecodeOptions imageDecodeOptions, @Nullable CacheKey cacheKey, @Nullable String str2) {
        this.mSourceString = (String) Preconditions.checkNotNull(str);
        this.mResizeOptions = resizeOptions;
        this.mAutoRotated = z;
        this.mImageDecodeOptions = imageDecodeOptions;
        this.mPostprocessorCacheKey = cacheKey;
        this.mPostprocessorName = str2;
        this.mHash = HashCodeUtil.hashCode(Integer.valueOf(str.hashCode()), Integer.valueOf(resizeOptions != null ? resizeOptions.hashCode() : 0), Integer.valueOf(z ? Boolean.TRUE.hashCode() : Boolean.FALSE.hashCode()), this.mImageDecodeOptions, this.mPostprocessorCacheKey, (Object) str2);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BitmapMemoryCacheKey)) {
            return false;
        }
        BitmapMemoryCacheKey bitmapMemoryCacheKey = (BitmapMemoryCacheKey) obj;
        return this.mHash == bitmapMemoryCacheKey.mHash && this.mSourceString.equals(bitmapMemoryCacheKey.mSourceString) && Objects.equal(this.mResizeOptions, bitmapMemoryCacheKey.mResizeOptions) && this.mAutoRotated == bitmapMemoryCacheKey.mAutoRotated && Objects.equal(this.mImageDecodeOptions, bitmapMemoryCacheKey.mImageDecodeOptions) && Objects.equal(this.mPostprocessorCacheKey, bitmapMemoryCacheKey.mPostprocessorCacheKey) && Objects.equal(this.mPostprocessorName, bitmapMemoryCacheKey.mPostprocessorName);
    }

    @Nullable
    public String getPostprocessorName() {
        return this.mPostprocessorName;
    }

    public String getSourceUriString() {
        return this.mSourceString;
    }

    public int hashCode() {
        return this.mHash;
    }

    public String toString() {
        return String.format((Locale) null, "%s_%s_%s_%s_%s_%s_%d", new Object[]{this.mSourceString, this.mResizeOptions, Boolean.toString(this.mAutoRotated), this.mImageDecodeOptions, this.mPostprocessorCacheKey, this.mPostprocessorName, Integer.valueOf(this.mHash)});
    }
}
