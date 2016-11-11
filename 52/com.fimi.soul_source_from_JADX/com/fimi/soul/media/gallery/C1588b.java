package com.fimi.soul.media.gallery;

import android.graphics.drawable.Animatable;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.imagepipeline.image.ImageInfo;

/* renamed from: com.fimi.soul.media.gallery.b */
class C1588b extends BaseControllerListener<ImageInfo> {
    final /* synthetic */ DroneImageDetailFragment f7809a;

    private C1588b(DroneImageDetailFragment droneImageDetailFragment) {
        this.f7809a = droneImageDetailFragment;
    }

    public void m10772a(String str, ImageInfo imageInfo, Animatable animatable) {
        super.onFinalImageSet(str, imageInfo, animatable);
        this.f7809a.f7686f.setVisibility(8);
        if (imageInfo != null && this.f7809a.f7685e != null) {
            this.f7809a.f7685e.m12934a(imageInfo.getWidth(), imageInfo.getHeight());
        }
    }

    public void onFailure(String str, Throwable th) {
        super.onFailure(str, th);
        this.f7809a.f7686f.setVisibility(8);
    }

    public /* synthetic */ void onFinalImageSet(String str, Object obj, Animatable animatable) {
        m10772a(str, (ImageInfo) obj, animatable);
    }
}
