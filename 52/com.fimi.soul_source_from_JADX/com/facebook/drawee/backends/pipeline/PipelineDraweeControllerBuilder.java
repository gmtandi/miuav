package com.facebook.drawee.backends.pipeline;

import android.content.Context;
import android.net.Uri;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.Set;
import javax.annotation.Nullable;

public class PipelineDraweeControllerBuilder extends AbstractDraweeControllerBuilder<PipelineDraweeControllerBuilder, ImageRequest, CloseableReference<CloseableImage>, ImageInfo> {
    private final ImagePipeline mImagePipeline;
    private final PipelineDraweeControllerFactory mPipelineDraweeControllerFactory;

    public PipelineDraweeControllerBuilder(Context context, PipelineDraweeControllerFactory pipelineDraweeControllerFactory, ImagePipeline imagePipeline, Set<ControllerListener> set) {
        super(context, set);
        this.mImagePipeline = imagePipeline;
        this.mPipelineDraweeControllerFactory = pipelineDraweeControllerFactory;
    }

    protected DataSource<CloseableReference<CloseableImage>> getDataSourceForRequest(ImageRequest imageRequest, Object obj, boolean z) {
        return z ? this.mImagePipeline.fetchImageFromBitmapCache(imageRequest, obj) : this.mImagePipeline.fetchDecodedImage(imageRequest, obj);
    }

    protected PipelineDraweeControllerBuilder getThis() {
        return this;
    }

    protected PipelineDraweeController obtainController() {
        DraweeController oldController = getOldController();
        if (!(oldController instanceof PipelineDraweeController)) {
            return this.mPipelineDraweeControllerFactory.newController(obtainDataSourceSupplier(), AbstractDraweeControllerBuilder.generateUniqueControllerId(), getCallerContext());
        }
        PipelineDraweeController pipelineDraweeController = (PipelineDraweeController) oldController;
        pipelineDraweeController.initialize(obtainDataSourceSupplier(), AbstractDraweeControllerBuilder.generateUniqueControllerId(), getCallerContext());
        return pipelineDraweeController;
    }

    public PipelineDraweeControllerBuilder setUri(Uri uri) {
        return (PipelineDraweeControllerBuilder) super.setImageRequest(ImageRequest.fromUri(uri));
    }

    public PipelineDraweeControllerBuilder setUri(@Nullable String str) {
        return (PipelineDraweeControllerBuilder) super.setImageRequest(ImageRequest.fromUri(str));
    }
}
