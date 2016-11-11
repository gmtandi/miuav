package com.facebook.imagepipeline.producers;

import android.content.ContentResolver;
import android.database.Cursor;
import android.media.ExifInterface;
import android.net.Uri;
import android.util.Pair;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.UriUtil;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.imagepipeline.memory.PooledByteBufferFactory;
import com.facebook.imagepipeline.memory.PooledByteBufferInputStream;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imageutils.BitmapUtil;
import com.facebook.imageutils.JfifUtil;
import java.io.File;
import java.util.Map;
import java.util.concurrent.Executor;

public class LocalExifThumbnailProducer implements Producer<EncodedImage> {
    @VisibleForTesting
    static final String CREATED_THUMBNAIL = "createdThumbnail";
    @VisibleForTesting
    static final String PRODUCER_NAME = "LocalExifThumbnailProducer";
    private final ContentResolver mContentResolver;
    private final Executor mExecutor;
    private final PooledByteBufferFactory mPooledByteBufferFactory;

    /* renamed from: com.facebook.imagepipeline.producers.LocalExifThumbnailProducer.1 */
    class C10531 extends StatefulProducerRunnable<EncodedImage> {
        final /* synthetic */ ImageRequest val$imageRequest;

        C10531(Consumer consumer, ProducerListener producerListener, String str, String str2, ImageRequest imageRequest) {
            this.val$imageRequest = imageRequest;
            super(consumer, producerListener, str, str2);
        }

        protected void disposeResult(EncodedImage encodedImage) {
            EncodedImage.closeSafely(encodedImage);
        }

        protected Map<String, String> getExtraMapOnSuccess(EncodedImage encodedImage) {
            return ImmutableMap.of(LocalExifThumbnailProducer.CREATED_THUMBNAIL, Boolean.toString(encodedImage != null));
        }

        protected EncodedImage getResult() {
            ExifInterface exifInterface = LocalExifThumbnailProducer.this.getExifInterface(this.val$imageRequest.getSourceUri());
            if (exifInterface == null || !exifInterface.hasThumbnail()) {
                return null;
            }
            return LocalExifThumbnailProducer.this.buildEncodedImage(LocalExifThumbnailProducer.this.mPooledByteBufferFactory.newByteBuffer(exifInterface.getThumbnail()), exifInterface);
        }
    }

    /* renamed from: com.facebook.imagepipeline.producers.LocalExifThumbnailProducer.2 */
    class C10542 extends BaseProducerContextCallbacks {
        final /* synthetic */ StatefulProducerRunnable val$cancellableProducerRunnable;

        C10542(StatefulProducerRunnable statefulProducerRunnable) {
            this.val$cancellableProducerRunnable = statefulProducerRunnable;
        }

        public void onCancellationRequested() {
            this.val$cancellableProducerRunnable.cancel();
        }
    }

    public LocalExifThumbnailProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory, ContentResolver contentResolver) {
        this.mExecutor = executor;
        this.mPooledByteBufferFactory = pooledByteBufferFactory;
        this.mContentResolver = contentResolver;
    }

    private EncodedImage buildEncodedImage(PooledByteBuffer pooledByteBuffer, ExifInterface exifInterface) {
        Pair decodeDimensions = BitmapUtil.decodeDimensions(new PooledByteBufferInputStream(pooledByteBuffer));
        int rotationAngle = getRotationAngle(exifInterface);
        int intValue = decodeDimensions != null ? ((Integer) decodeDimensions.first).intValue() : -1;
        int intValue2 = decodeDimensions != null ? ((Integer) decodeDimensions.second).intValue() : -1;
        EncodedImage encodedImage = new EncodedImage(CloseableReference.of(pooledByteBuffer));
        encodedImage.setImageFormat(ImageFormat.JPEG);
        encodedImage.setRotationAngle(rotationAngle);
        encodedImage.setWidth(intValue);
        encodedImage.setHeight(intValue2);
        return encodedImage;
    }

    private String getRealPathFromUri(Uri uri) {
        if (!UriUtil.isLocalContentUri(uri)) {
            return UriUtil.isLocalFileUri(uri) ? uri.getPath() : null;
        } else {
            Cursor query = this.mContentResolver.query(uri, null, null, null, null);
            if (query == null) {
                return null;
            }
            query.moveToFirst();
            String string = query.getString(query.getColumnIndex("_data"));
            query.close();
            return string;
        }
    }

    private int getRotationAngle(ExifInterface exifInterface) {
        return JfifUtil.getAutoRotateAngleFromOrientation(Integer.parseInt(exifInterface.getAttribute("Orientation")));
    }

    @VisibleForTesting
    boolean canReadAsFile(String str) {
        if (str == null) {
            return false;
        }
        File file = new File(str);
        return file.exists() && file.canRead();
    }

    @VisibleForTesting
    ExifInterface getExifInterface(Uri uri) {
        String realPathFromUri = getRealPathFromUri(uri);
        return canReadAsFile(realPathFromUri) ? new ExifInterface(realPathFromUri) : null;
    }

    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        Consumer<EncodedImage> consumer2 = consumer;
        Runnable c10531 = new C10531(consumer2, producerContext.getListener(), PRODUCER_NAME, producerContext.getId(), producerContext.getImageRequest());
        producerContext.addCallbacks(new C10542(c10531));
        this.mExecutor.execute(c10531);
    }
}
