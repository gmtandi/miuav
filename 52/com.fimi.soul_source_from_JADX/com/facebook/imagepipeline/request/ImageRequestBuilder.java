package com.facebook.imagepipeline.request;

import android.net.Uri;
import android.net.Uri.Builder;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest.ImageType;
import com.facebook.imagepipeline.request.ImageRequest.RequestLevel;
import javax.annotation.Nullable;

public class ImageRequestBuilder {
    private boolean mAutoRotateEnabled;
    private ImageDecodeOptions mImageDecodeOptions;
    private ImageType mImageType;
    private boolean mLocalThumbnailPreviewsEnabled;
    private RequestLevel mLowestPermittedRequestLevel;
    @Nullable
    private Postprocessor mPostprocessor;
    private boolean mProgressiveRenderingEnabled;
    private Priority mRequestPriority;
    @Nullable
    private ResizeOptions mResizeOptions;
    private Uri mSourceUri;

    public class BuilderException extends RuntimeException {
        public BuilderException(String str) {
            super("Invalid request builder: " + str);
        }
    }

    private ImageRequestBuilder() {
        this.mSourceUri = null;
        this.mLowestPermittedRequestLevel = RequestLevel.FULL_FETCH;
        this.mAutoRotateEnabled = false;
        this.mResizeOptions = null;
        this.mImageDecodeOptions = ImageDecodeOptions.defaults();
        this.mImageType = ImageType.DEFAULT;
        this.mProgressiveRenderingEnabled = false;
        this.mLocalThumbnailPreviewsEnabled = false;
        this.mRequestPriority = Priority.HIGH;
        this.mPostprocessor = null;
    }

    public static ImageRequestBuilder fromRequest(ImageRequest imageRequest) {
        return newBuilderWithSource(imageRequest.getSourceUri()).setAutoRotateEnabled(imageRequest.getAutoRotateEnabled()).setImageDecodeOptions(imageRequest.getImageDecodeOptions()).setImageType(imageRequest.getImageType()).setLocalThumbnailPreviewsEnabled(imageRequest.getLocalThumbnailPreviewsEnabled()).setLowestPermittedRequestLevel(imageRequest.getLowestPermittedRequestLevel()).setPostprocessor(imageRequest.getPostprocessor()).setProgressiveRenderingEnabled(imageRequest.getProgressiveRenderingEnabled()).setRequestPriority(imageRequest.getPriority()).setResizeOptions(imageRequest.getResizeOptions());
    }

    public static ImageRequestBuilder newBuilderWithResourceId(int i) {
        return newBuilderWithSource(new Builder().scheme(UriUtil.LOCAL_RESOURCE_SCHEME).path(String.valueOf(i)).build());
    }

    public static ImageRequestBuilder newBuilderWithSource(Uri uri) {
        return new ImageRequestBuilder().setSource(uri);
    }

    public ImageRequest build() {
        validate();
        return new ImageRequest(this);
    }

    public ImageDecodeOptions getImageDecodeOptions() {
        return this.mImageDecodeOptions;
    }

    public ImageType getImageType() {
        return this.mImageType;
    }

    public RequestLevel getLowestPermittedRequestLevel() {
        return this.mLowestPermittedRequestLevel;
    }

    @Nullable
    public Postprocessor getPostprocessor() {
        return this.mPostprocessor;
    }

    public Priority getRequestPriority() {
        return this.mRequestPriority;
    }

    @Nullable
    public ResizeOptions getResizeOptions() {
        return this.mResizeOptions;
    }

    public Uri getSourceUri() {
        return this.mSourceUri;
    }

    public boolean isAutoRotateEnabled() {
        return this.mAutoRotateEnabled;
    }

    public boolean isDiskCacheEnabled() {
        return UriUtil.isNetworkUri(this.mSourceUri);
    }

    public boolean isLocalThumbnailPreviewsEnabled() {
        return this.mLocalThumbnailPreviewsEnabled;
    }

    public boolean isProgressiveRenderingEnabled() {
        return this.mProgressiveRenderingEnabled;
    }

    public ImageRequestBuilder setAutoRotateEnabled(boolean z) {
        this.mAutoRotateEnabled = z;
        return this;
    }

    public ImageRequestBuilder setImageDecodeOptions(ImageDecodeOptions imageDecodeOptions) {
        this.mImageDecodeOptions = imageDecodeOptions;
        return this;
    }

    public ImageRequestBuilder setImageType(ImageType imageType) {
        this.mImageType = imageType;
        return this;
    }

    public ImageRequestBuilder setLocalThumbnailPreviewsEnabled(boolean z) {
        this.mLocalThumbnailPreviewsEnabled = z;
        return this;
    }

    public ImageRequestBuilder setLowestPermittedRequestLevel(RequestLevel requestLevel) {
        this.mLowestPermittedRequestLevel = requestLevel;
        return this;
    }

    public ImageRequestBuilder setPostprocessor(Postprocessor postprocessor) {
        this.mPostprocessor = postprocessor;
        return this;
    }

    public ImageRequestBuilder setProgressiveRenderingEnabled(boolean z) {
        this.mProgressiveRenderingEnabled = z;
        return this;
    }

    public ImageRequestBuilder setRequestPriority(Priority priority) {
        this.mRequestPriority = priority;
        return this;
    }

    public ImageRequestBuilder setResizeOptions(ResizeOptions resizeOptions) {
        this.mResizeOptions = resizeOptions;
        return this;
    }

    public ImageRequestBuilder setSource(Uri uri) {
        Preconditions.checkNotNull(uri);
        this.mSourceUri = uri;
        return this;
    }

    protected void validate() {
        if (this.mSourceUri == null) {
            throw new BuilderException("Source must be set!");
        }
        if (UriUtil.isLocalResourceUri(this.mSourceUri)) {
            if (!this.mSourceUri.isAbsolute()) {
                throw new BuilderException("Resource URI path must be absolute.");
            } else if (this.mSourceUri.getPath().isEmpty()) {
                throw new BuilderException("Resource URI must not be empty");
            } else {
                try {
                    Integer.parseInt(this.mSourceUri.getPath().substring(1));
                } catch (NumberFormatException e) {
                    throw new BuilderException("Resource URI path must be a resource id.");
                }
            }
        }
        if (UriUtil.isLocalAssetUri(this.mSourceUri) && !this.mSourceUri.isAbsolute()) {
            throw new BuilderException("Asset URI path must be absolute.");
        }
    }
}
