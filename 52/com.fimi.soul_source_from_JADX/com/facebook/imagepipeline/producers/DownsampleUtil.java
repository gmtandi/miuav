package com.facebook.imagepipeline.producers;

import com.amap.api.maps.model.WeightedLatLng;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.logging.FLog;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.fimi.soul.view.photodraweeview.C2020f;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

public class DownsampleUtil {
    private static final int DEFAULT_SAMPLE_SIZE = 1;
    private static final float INTERVAL_ROUNDING = 0.33333334f;
    private static final float MAX_BITMAP_SIZE = 2048.0f;

    private DownsampleUtil() {
    }

    @VisibleForTesting
    static float determineDownsampleRatio(ImageRequest imageRequest, EncodedImage encodedImage) {
        Preconditions.checkArgument(EncodedImage.isMetaDataAvailable(encodedImage));
        ResizeOptions resizeOptions = imageRequest.getResizeOptions();
        if (resizeOptions == null || resizeOptions.height <= 0 || resizeOptions.width <= 0 || encodedImage.getWidth() == 0 || encodedImage.getHeight() == 0) {
            return C2020f.f10933c;
        }
        int rotationAngle = getRotationAngle(imageRequest, encodedImage);
        int i = (rotationAngle == 90 || rotationAngle == 270) ? DEFAULT_SAMPLE_SIZE : 0;
        FLog.m7631v("DownsampleUtil", "Downsample - Specified size: %dx%d, image size: %dx%d ratio: %.1f x %.1f, ratio: %.3f for %s", Integer.valueOf(resizeOptions.width), Integer.valueOf(resizeOptions.height), Integer.valueOf(i != 0 ? encodedImage.getHeight() : encodedImage.getWidth()), Integer.valueOf(i != 0 ? encodedImage.getWidth() : encodedImage.getHeight()), Float.valueOf(((float) resizeOptions.width) / ((float) (i != 0 ? encodedImage.getHeight() : encodedImage.getWidth()))), Float.valueOf(((float) resizeOptions.height) / ((float) (i != 0 ? encodedImage.getWidth() : encodedImage.getHeight()))), Float.valueOf(Math.max(((float) resizeOptions.width) / ((float) (i != 0 ? encodedImage.getHeight() : encodedImage.getWidth())), ((float) resizeOptions.height) / ((float) (i != 0 ? encodedImage.getWidth() : encodedImage.getHeight())))), imageRequest.getSourceUri().toString());
        return Math.max(((float) resizeOptions.width) / ((float) (i != 0 ? encodedImage.getHeight() : encodedImage.getWidth())), ((float) resizeOptions.height) / ((float) (i != 0 ? encodedImage.getWidth() : encodedImage.getHeight())));
    }

    public static int determineSampleSize(ImageRequest imageRequest, EncodedImage encodedImage) {
        if (!EncodedImage.isMetaDataAvailable(encodedImage)) {
            return DEFAULT_SAMPLE_SIZE;
        }
        float determineDownsampleRatio = determineDownsampleRatio(imageRequest, encodedImage);
        int ratioToSampleSizeJPEG = encodedImage.getImageFormat() == ImageFormat.JPEG ? ratioToSampleSizeJPEG(determineDownsampleRatio) : ratioToSampleSize(determineDownsampleRatio);
        int max = Math.max(encodedImage.getHeight(), encodedImage.getWidth());
        while (((float) (max / ratioToSampleSizeJPEG)) > MAX_BITMAP_SIZE) {
            ratioToSampleSizeJPEG = encodedImage.getImageFormat() == ImageFormat.JPEG ? ratioToSampleSizeJPEG * 2 : ratioToSampleSizeJPEG + DEFAULT_SAMPLE_SIZE;
        }
        return ratioToSampleSizeJPEG;
    }

    private static int getRotationAngle(ImageRequest imageRequest, EncodedImage encodedImage) {
        boolean z = false;
        if (!imageRequest.getAutoRotateEnabled()) {
            return 0;
        }
        int rotationAngle = encodedImage.getRotationAngle();
        if (rotationAngle == 0 || rotationAngle == 90 || rotationAngle == Opcodes.GETFIELD || rotationAngle == 270) {
            z = true;
        }
        Preconditions.checkArgument(z);
        return rotationAngle;
    }

    @VisibleForTesting
    static int ratioToSampleSize(float f) {
        if (f > 0.6666667f) {
            return DEFAULT_SAMPLE_SIZE;
        }
        int i = 2;
        while (true) {
            if (((WeightedLatLng.DEFAULT_INTENSITY / (Math.pow((double) i, 2.0d) - ((double) i))) * 0.3333333432674408d) + (WeightedLatLng.DEFAULT_INTENSITY / ((double) i)) <= ((double) f)) {
                return i - 1;
            }
            i += DEFAULT_SAMPLE_SIZE;
        }
    }

    @VisibleForTesting
    static int ratioToSampleSizeJPEG(float f) {
        if (f > 0.6666667f) {
            return DEFAULT_SAMPLE_SIZE;
        }
        int i = 2;
        while (true) {
            if (((WeightedLatLng.DEFAULT_INTENSITY / ((double) (i * 2))) * 0.3333333432674408d) + (WeightedLatLng.DEFAULT_INTENSITY / ((double) (i * 2))) <= ((double) f)) {
                return i;
            }
            i *= 2;
        }
    }

    @VisibleForTesting
    static int roundToPowerOfTwo(int i) {
        int i2 = DEFAULT_SAMPLE_SIZE;
        while (i2 < i) {
            i2 *= 2;
        }
        return i2;
    }
}
