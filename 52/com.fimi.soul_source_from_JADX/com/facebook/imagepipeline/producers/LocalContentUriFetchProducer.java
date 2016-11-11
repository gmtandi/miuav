package com.facebook.imagepipeline.producers;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Rect;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Images.Thumbnails;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.logging.FLog;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.BitmapCounterProvider;
import com.facebook.imagepipeline.memory.PooledByteBufferFactory;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imageutils.JfifUtil;
import com.xiaomi.market.sdk.C2538k;
import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

public class LocalContentUriFetchProducer extends LocalFetchProducer {
    private static final float ACCEPTABLE_REQUESTED_TO_ACTUAL_SIZE_RATIO = 1.3333334f;
    private static final String DISPLAY_PHOTO_PATH;
    private static final Rect MICRO_THUMBNAIL_DIMENSIONS;
    private static final Rect MINI_THUMBNAIL_DIMENSIONS;
    private static final int NO_THUMBNAIL = 0;
    @VisibleForTesting
    static final String PRODUCER_NAME = "LocalContentUriFetchProducer";
    private static final String[] PROJECTION;
    private static final Class<?> TAG;
    private static final String[] THUMBNAIL_PROJECTION;
    private final ContentResolver mContentResolver;

    static {
        TAG = LocalContentUriFetchProducer.class;
        DISPLAY_PHOTO_PATH = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "display_photo").getPath();
        PROJECTION = new String[]{C2538k._ID, "_data"};
        THUMBNAIL_PROJECTION = new String[]{"_data"};
        MINI_THUMBNAIL_DIMENSIONS = new Rect(NO_THUMBNAIL, NO_THUMBNAIL, Opcodes.ACC_INTERFACE, BitmapCounterProvider.MAX_BITMAP_COUNT);
        MICRO_THUMBNAIL_DIMENSIONS = new Rect(NO_THUMBNAIL, NO_THUMBNAIL, 96, 96);
    }

    public LocalContentUriFetchProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory, ContentResolver contentResolver, boolean z) {
        super(executor, pooledByteBufferFactory, z);
        this.mContentResolver = contentResolver;
    }

    @Nullable
    private EncodedImage getCameraImage(Uri uri, ResizeOptions resizeOptions) {
        EncodedImage encodedImage = null;
        Cursor query = this.mContentResolver.query(uri, PROJECTION, encodedImage, encodedImage, encodedImage);
        if (query == null) {
            return encodedImage;
        }
        try {
            if (query.getCount() == 0) {
                return encodedImage;
            }
            query.moveToFirst();
            String string = query.getString(query.getColumnIndex("_data"));
            if (resizeOptions != null) {
                EncodedImage thumbnail = getThumbnail(resizeOptions, query.getInt(query.getColumnIndex(C2538k._ID)));
                if (thumbnail != null) {
                    thumbnail.setRotationAngle(getRotationAngle(string));
                    query.close();
                    return thumbnail;
                }
            }
            if (string != null) {
                encodedImage = getEncodedImage(new FileInputStream(string), getLength(string));
                query.close();
                return encodedImage;
            }
            query.close();
            return encodedImage;
        } finally {
            query.close();
        }
    }

    private static int getLength(String str) {
        return str == null ? -1 : (int) new File(str).length();
    }

    private static int getRotationAngle(String str) {
        int i = NO_THUMBNAIL;
        if (str != null) {
            try {
                i = JfifUtil.getAutoRotateAngleFromOrientation(new ExifInterface(str).getAttributeInt("Orientation", 1));
            } catch (Throwable e) {
                FLog.m7596e(TAG, e, "Unable to retrieve thumbnail rotation for %s", str);
            }
        }
        return i;
    }

    private EncodedImage getThumbnail(ResizeOptions resizeOptions, int i) {
        Throwable th;
        EncodedImage encodedImage = null;
        int thumbnailKind = getThumbnailKind(resizeOptions);
        if (thumbnailKind != 0) {
            Cursor queryMiniThumbnail;
            try {
                queryMiniThumbnail = Thumbnails.queryMiniThumbnail(this.mContentResolver, (long) i, thumbnailKind, THUMBNAIL_PROJECTION);
                if (queryMiniThumbnail != null) {
                    try {
                        queryMiniThumbnail.moveToFirst();
                        if (queryMiniThumbnail.getCount() > 0) {
                            String string = queryMiniThumbnail.getString(queryMiniThumbnail.getColumnIndex("_data"));
                            if (new File(string).exists()) {
                                encodedImage = getEncodedImage(new FileInputStream(string), getLength(string));
                                if (queryMiniThumbnail != null) {
                                    queryMiniThumbnail.close();
                                }
                            }
                        }
                        if (queryMiniThumbnail != null) {
                            queryMiniThumbnail.close();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (queryMiniThumbnail != null) {
                            queryMiniThumbnail.close();
                        }
                        throw th;
                    }
                } else if (queryMiniThumbnail != null) {
                    queryMiniThumbnail.close();
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                queryMiniThumbnail = null;
                th = th4;
                if (queryMiniThumbnail != null) {
                    queryMiniThumbnail.close();
                }
                throw th;
            }
        }
        return encodedImage;
    }

    private static int getThumbnailKind(ResizeOptions resizeOptions) {
        return isThumbnailBigEnough(resizeOptions, MICRO_THUMBNAIL_DIMENSIONS) ? 3 : isThumbnailBigEnough(resizeOptions, MINI_THUMBNAIL_DIMENSIONS) ? 1 : NO_THUMBNAIL;
    }

    private static boolean isCameraUri(Uri uri) {
        String uri2 = uri.toString();
        return uri2.startsWith(Media.EXTERNAL_CONTENT_URI.toString()) || uri2.startsWith(Media.INTERNAL_CONTENT_URI.toString());
    }

    private static boolean isContactUri(Uri uri) {
        return "com.android.contacts".equals(uri.getAuthority()) && !uri.getPath().startsWith(DISPLAY_PHOTO_PATH);
    }

    @VisibleForTesting
    static boolean isThumbnailBigEnough(ResizeOptions resizeOptions, Rect rect) {
        return ((float) resizeOptions.width) <= ((float) rect.width()) * ACCEPTABLE_REQUESTED_TO_ACTUAL_SIZE_RATIO && ((float) resizeOptions.height) <= ((float) rect.height()) * ACCEPTABLE_REQUESTED_TO_ACTUAL_SIZE_RATIO;
    }

    protected EncodedImage getEncodedImage(ImageRequest imageRequest) {
        Uri sourceUri = imageRequest.getSourceUri();
        if (isContactUri(sourceUri)) {
            return getEncodedImage(sourceUri.toString().endsWith("/photo") ? this.mContentResolver.openInputStream(sourceUri) : Contacts.openContactPhotoInputStream(this.mContentResolver, sourceUri), -1);
        }
        if (isCameraUri(sourceUri)) {
            EncodedImage cameraImage = getCameraImage(sourceUri, imageRequest.getResizeOptions());
            if (cameraImage != null) {
                return cameraImage;
            }
        }
        return getEncodedImage(this.mContentResolver.openInputStream(sourceUri), -1);
    }

    protected String getProducerName() {
        return PRODUCER_NAME;
    }
}
