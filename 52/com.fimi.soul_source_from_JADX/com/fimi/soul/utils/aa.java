package com.fimi.soul.utils;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.internal.Sets;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.core.ImagePipelineConfig.Builder;
import com.facebook.imagepipeline.listener.RequestLoggingListener;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.fimi.kernel.C1189f;
import com.fimi.soul.biz.camera.entity.X11FileInfo;

public class aa {
    private static final String f10006a = "imagepipeline_cache";
    private static final int f10007b;
    private static final int f10008c = 41943040;
    private static final int f10009d;
    private static ImagePipelineConfig f10010e;

    static {
        f10007b = (int) Runtime.getRuntime().maxMemory();
        f10009d = f10007b / 4;
    }

    public static void m12207a() {
        Fresco.initialize(C1189f.m8327a(), m12214c());
    }

    public static void m12208a(SimpleDraweeView simpleDraweeView, String str) {
        m12209a(simpleDraweeView, str, null);
    }

    public static void m12209a(SimpleDraweeView simpleDraweeView, String str, ControllerListener controllerListener) {
        if (str != null) {
            int i = simpleDraweeView.getLayoutParams().width;
            int i2 = simpleDraweeView.getLayoutParams().height;
            PipelineDraweeControllerBuilder newDraweeControllerBuilder = Fresco.newDraweeControllerBuilder();
            newDraweeControllerBuilder.setOldController(simpleDraweeView.getController());
            newDraweeControllerBuilder.setAutoPlayAnimations(true);
            ImageRequestBuilder newBuilderWithSource = ImageRequestBuilder.newBuilderWithSource(Uri.parse(str));
            newBuilderWithSource.setProgressiveRenderingEnabled(true);
            Log.d("Good", str);
            if (i > 0 && i2 > 0) {
                newBuilderWithSource.setResizeOptions(new ResizeOptions(i, i2));
            }
            newDraweeControllerBuilder.setImageRequest(newBuilderWithSource.build());
            newDraweeControllerBuilder.setControllerListener(controllerListener);
            simpleDraweeView.setController(newDraweeControllerBuilder.build());
        }
    }

    public static void m12210a(SimpleDraweeView simpleDraweeView, String str, String str2, ControllerListener controllerListener) {
        if (str2 != null) {
            int i = simpleDraweeView.getLayoutParams().width;
            int i2 = simpleDraweeView.getLayoutParams().height;
            PipelineDraweeControllerBuilder newDraweeControllerBuilder = Fresco.newDraweeControllerBuilder();
            if (!str.startsWith("file:///")) {
                str = "file://" + str;
            }
            if (str != null && str.length() > 0) {
                newDraweeControllerBuilder.setLowResImageRequest(ImageRequest.fromUri(str));
            }
            newDraweeControllerBuilder.setOldController(simpleDraweeView.getController());
            newDraweeControllerBuilder.setAutoPlayAnimations(true);
            ImageRequestBuilder newBuilderWithSource = str2.endsWith(X11FileInfo.FILE_TYPE_MP4) ? ImageRequestBuilder.newBuilderWithSource(Uri.parse(str)) : ImageRequestBuilder.newBuilderWithSource(Uri.parse(str2));
            if (i > 0 && i2 > 0) {
                newBuilderWithSource.setResizeOptions(new ResizeOptions(i, i2));
            }
            newDraweeControllerBuilder.setImageRequest(newBuilderWithSource.build());
            newDraweeControllerBuilder.setControllerListener(controllerListener);
            simpleDraweeView.setController(newDraweeControllerBuilder.build());
        }
    }

    private static void m12211a(Builder builder) {
        builder.setRequestListeners(Sets.newHashSet(new RequestLoggingListener()));
    }

    private static void m12212a(Builder builder, Context context) {
        builder.setBitmapMemoryCacheParamsSupplier(new ab(new MemoryCacheParams(f10009d, Integer.MAX_VALUE, f10009d, Integer.MAX_VALUE, Integer.MAX_VALUE))).setMainDiskCacheConfig(DiskCacheConfig.newBuilder().setBaseDirectoryPath(context.getApplicationContext().getCacheDir()).setBaseDirectoryName(f10006a).setMaxCacheSize(41943040).build());
    }

    public static void m12213b() {
        Fresco.shutDown();
    }

    public static ImagePipelineConfig m12214c() {
        if (f10010e == null) {
            Builder newBuilder = ImagePipelineConfig.newBuilder(C1189f.m8327a());
            m12212a(newBuilder, C1189f.m8327a());
            m12211a(newBuilder);
            f10010e = newBuilder.build();
        }
        return f10010e;
    }

    public static void m12215d() {
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        imagePipeline.clearMemoryCaches();
        imagePipeline.clearDiskCaches();
    }
}
