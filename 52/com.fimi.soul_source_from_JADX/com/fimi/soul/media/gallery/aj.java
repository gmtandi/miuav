package com.fimi.soul.media.gallery;

import android.graphics.drawable.Animatable;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.imagepipeline.image.ImageInfo;

class aj extends BaseControllerListener<ImageInfo> {
    final /* synthetic */ ImageDetailFragment f7792a;

    aj(ImageDetailFragment imageDetailFragment) {
        this.f7792a = imageDetailFragment;
    }

    public void m10757a(String str, ImageInfo imageInfo, Animatable animatable) {
        super.onFinalImageSet(str, imageInfo, animatable);
        if (imageInfo != null && this.f7792a.f7771c != null) {
            this.f7792a.f7771c.m12934a(imageInfo.getWidth(), imageInfo.getHeight());
        }
    }

    public /* synthetic */ void onFinalImageSet(String str, Object obj, Animatable animatable) {
        m10757a(str, (ImageInfo) obj, animatable);
    }
}
