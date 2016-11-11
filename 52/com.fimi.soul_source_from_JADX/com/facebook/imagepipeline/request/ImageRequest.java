package com.facebook.imagepipeline.request;

import android.net.Uri;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.common.ResizeOptions;
import java.io.File;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

@Immutable
public class ImageRequest {
    private final boolean mAutoRotateEnabled;
    private final ImageDecodeOptions mImageDecodeOptions;
    private final ImageType mImageType;
    private final boolean mIsDiskCacheEnabled;
    private final boolean mLocalThumbnailPreviewsEnabled;
    private final RequestLevel mLowestPermittedRequestLevel;
    private final Postprocessor mPostprocessor;
    private final boolean mProgressiveRenderingEnabled;
    private final Priority mRequestPriority;
    @Nullable
    ResizeOptions mResizeOptions;
    private File mSourceFile;
    private final Uri mSourceUri;

    public enum ImageType {
        SMALL,
        DEFAULT
    }

    public enum RequestLevel {
        FULL_FETCH(1),
        DISK_CACHE(2),
        ENCODED_MEMORY_CACHE(3),
        BITMAP_MEMORY_CACHE(4);
        
        private int mValue;

        private RequestLevel(int i) {
            this.mValue = i;
        }

        public static RequestLevel getMax(RequestLevel requestLevel, RequestLevel requestLevel2) {
            return requestLevel.getValue() > requestLevel2.getValue() ? requestLevel : requestLevel2;
        }

        public int getValue() {
            return this.mValue;
        }
    }

    protected ImageRequest(ImageRequestBuilder imageRequestBuilder) {
        this.mResizeOptions = null;
        this.mImageType = imageRequestBuilder.getImageType();
        this.mSourceUri = imageRequestBuilder.getSourceUri();
        this.mProgressiveRenderingEnabled = imageRequestBuilder.isProgressiveRenderingEnabled();
        this.mLocalThumbnailPreviewsEnabled = imageRequestBuilder.isLocalThumbnailPreviewsEnabled();
        this.mImageDecodeOptions = imageRequestBuilder.getImageDecodeOptions();
        this.mResizeOptions = imageRequestBuilder.getResizeOptions();
        this.mAutoRotateEnabled = imageRequestBuilder.isAutoRotateEnabled();
        this.mRequestPriority = imageRequestBuilder.getRequestPriority();
        this.mLowestPermittedRequestLevel = imageRequestBuilder.getLowestPermittedRequestLevel();
        this.mIsDiskCacheEnabled = imageRequestBuilder.isDiskCacheEnabled();
        this.mPostprocessor = imageRequestBuilder.getPostprocessor();
    }

    public static ImageRequest fromUri(@Nullable Uri uri) {
        return uri == null ? null : ImageRequestBuilder.newBuilderWithSource(uri).build();
    }

    public static ImageRequest fromUri(@Nullable String str) {
        return (str == null || str.length() == 0) ? null : fromUri(Uri.parse(str));
    }

    public boolean getAutoRotateEnabled() {
        return this.mAutoRotateEnabled;
    }

    public ImageDecodeOptions getImageDecodeOptions() {
        return this.mImageDecodeOptions;
    }

    public ImageType getImageType() {
        return this.mImageType;
    }

    public boolean getLocalThumbnailPreviewsEnabled() {
        return this.mLocalThumbnailPreviewsEnabled;
    }

    public RequestLevel getLowestPermittedRequestLevel() {
        return this.mLowestPermittedRequestLevel;
    }

    @Nullable
    public Postprocessor getPostprocessor() {
        return this.mPostprocessor;
    }

    public int getPreferredHeight() {
        return this.mResizeOptions != null ? this.mResizeOptions.height : Opcodes.ACC_STRICT;
    }

    public int getPreferredWidth() {
        return this.mResizeOptions != null ? this.mResizeOptions.width : Opcodes.ACC_STRICT;
    }

    public Priority getPriority() {
        return this.mRequestPriority;
    }

    public boolean getProgressiveRenderingEnabled() {
        return this.mProgressiveRenderingEnabled;
    }

    @Nullable
    public ResizeOptions getResizeOptions() {
        return this.mResizeOptions;
    }

    public synchronized File getSourceFile() {
        if (this.mSourceFile == null) {
            this.mSourceFile = new File(this.mSourceUri.getPath());
        }
        return this.mSourceFile;
    }

    public Uri getSourceUri() {
        return this.mSourceUri;
    }

    public boolean isDiskCacheEnabled() {
        return this.mIsDiskCacheEnabled;
    }
}
