package com.facebook.imagepipeline.core;

import android.net.Uri;
import android.os.Build.VERSION;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.media.MediaUtils;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import com.facebook.imagepipeline.producers.Producer;
import com.facebook.imagepipeline.producers.RemoveImageTransformMetaDataProducer;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequest.RequestLevel;
import java.util.HashMap;
import java.util.Map;

public class ProducerSequenceFactory {
    private static final int MAX_SIMULTANEOUS_FILE_FETCH_AND_RESIZE = 5;
    @VisibleForTesting
    Producer<EncodedImage> mBackgroundNetworkFetchToEncodedMemorySequence;
    @VisibleForTesting
    Map<Producer<CloseableReference<CloseableImage>>, Producer<Void>> mCloseableImagePrefetchSequences;
    private Producer<EncodedImage> mCommonNetworkFetchToEncodedMemorySequence;
    @VisibleForTesting
    Producer<CloseableReference<CloseableImage>> mDataFetchSequence;
    private final boolean mDownsampleEnabled;
    @VisibleForTesting
    Producer<CloseableReference<PooledByteBuffer>> mEncodedImageProducerSequence;
    @VisibleForTesting
    Producer<CloseableReference<CloseableImage>> mLocalAssetFetchSequence;
    @VisibleForTesting
    Producer<CloseableReference<CloseableImage>> mLocalContentUriFetchSequence;
    @VisibleForTesting
    Producer<CloseableReference<CloseableImage>> mLocalImageFileFetchSequence;
    @VisibleForTesting
    Producer<CloseableReference<CloseableImage>> mLocalResourceFetchSequence;
    @VisibleForTesting
    Producer<CloseableReference<CloseableImage>> mLocalVideoFileFetchSequence;
    @VisibleForTesting
    Producer<CloseableReference<CloseableImage>> mNetworkFetchSequence;
    @VisibleForTesting
    Producer<Void> mNetworkFetchToEncodedMemoryPrefetchSequence;
    private final NetworkFetcher mNetworkFetcher;
    @VisibleForTesting
    Map<Producer<CloseableReference<CloseableImage>>, Producer<CloseableReference<CloseableImage>>> mPostprocessorSequences;
    private final ProducerFactory mProducerFactory;
    private final boolean mResizeAndRotateEnabledForNetwork;

    public ProducerSequenceFactory(ProducerFactory producerFactory, NetworkFetcher networkFetcher, boolean z, boolean z2) {
        this.mProducerFactory = producerFactory;
        this.mNetworkFetcher = networkFetcher;
        this.mResizeAndRotateEnabledForNetwork = z;
        this.mDownsampleEnabled = z2;
        this.mPostprocessorSequences = new HashMap();
        this.mCloseableImagePrefetchSequences = new HashMap();
    }

    private synchronized Producer<EncodedImage> getBackgroundNetworkFetchToEncodedMemorySequence() {
        if (this.mBackgroundNetworkFetchToEncodedMemorySequence == null) {
            this.mBackgroundNetworkFetchToEncodedMemorySequence = this.mProducerFactory.newBackgroundThreadHandoffProducer(getCommonNetworkFetchToEncodedMemorySequence());
        }
        return this.mBackgroundNetworkFetchToEncodedMemorySequence;
    }

    private Producer<CloseableReference<CloseableImage>> getBasicDecodedImageSequence(ImageRequest imageRequest) {
        Preconditions.checkNotNull(imageRequest);
        Uri sourceUri = imageRequest.getSourceUri();
        Preconditions.checkNotNull(sourceUri, "Uri is null.");
        if (UriUtil.isNetworkUri(sourceUri)) {
            return getNetworkFetchSequence();
        }
        if (UriUtil.isLocalFileUri(sourceUri)) {
            return MediaUtils.isVideo(MediaUtils.extractMime(sourceUri.getPath())) ? getLocalVideoFileFetchSequence() : getLocalImageFileFetchSequence();
        } else {
            if (UriUtil.isLocalContentUri(sourceUri)) {
                return getLocalContentUriFetchSequence();
            }
            if (UriUtil.isLocalAssetUri(sourceUri)) {
                return getLocalAssetFetchSequence();
            }
            if (UriUtil.isLocalResourceUri(sourceUri)) {
                return getLocalResourceFetchSequence();
            }
            if (UriUtil.isDataUri(sourceUri)) {
                return getDataFetchSequence();
            }
            String uri = sourceUri.toString();
            if (uri.length() > 30) {
                uri = uri.substring(0, 30) + "...";
            }
            throw new RuntimeException("Unsupported uri scheme! Uri is: " + uri);
        }
    }

    private synchronized Producer<EncodedImage> getCommonNetworkFetchToEncodedMemorySequence() {
        if (this.mCommonNetworkFetchToEncodedMemorySequence == null) {
            this.mCommonNetworkFetchToEncodedMemorySequence = ProducerFactory.newAddImageTransformMetaDataProducer(newEncodedCacheMultiplexToTranscodeSequence(this.mProducerFactory.newNetworkFetchProducer(this.mNetworkFetcher)));
            if (this.mResizeAndRotateEnabledForNetwork && !this.mDownsampleEnabled) {
                this.mCommonNetworkFetchToEncodedMemorySequence = this.mProducerFactory.newResizeAndRotateProducer(this.mCommonNetworkFetchToEncodedMemorySequence);
            }
        }
        return this.mCommonNetworkFetchToEncodedMemorySequence;
    }

    private synchronized Producer<CloseableReference<CloseableImage>> getDataFetchSequence() {
        if (this.mDataFetchSequence == null) {
            Producer newDataFetchProducer = this.mProducerFactory.newDataFetchProducer();
            if (VERSION.SDK_INT < 18) {
                newDataFetchProducer = this.mProducerFactory.newWebpTranscodeProducer(newDataFetchProducer);
            }
            ProducerFactory producerFactory = this.mProducerFactory;
            newDataFetchProducer = ProducerFactory.newAddImageTransformMetaDataProducer(newDataFetchProducer);
            if (!this.mDownsampleEnabled) {
                newDataFetchProducer = this.mProducerFactory.newResizeAndRotateProducer(newDataFetchProducer);
            }
            this.mDataFetchSequence = newBitmapCacheGetToDecodeSequence(newDataFetchProducer);
        }
        return this.mDataFetchSequence;
    }

    private synchronized Producer<Void> getDecodedImagePrefetchSequence(Producer<CloseableReference<CloseableImage>> producer) {
        if (!this.mCloseableImagePrefetchSequences.containsKey(producer)) {
            ProducerFactory producerFactory = this.mProducerFactory;
            this.mCloseableImagePrefetchSequences.put(producer, ProducerFactory.newSwallowResultProducer(producer));
        }
        return (Producer) this.mCloseableImagePrefetchSequences.get(producer);
    }

    private synchronized Producer<CloseableReference<CloseableImage>> getLocalAssetFetchSequence() {
        if (this.mLocalAssetFetchSequence == null) {
            this.mLocalAssetFetchSequence = newBitmapCacheGetToLocalTransformSequence(this.mProducerFactory.newLocalAssetFetchProducer());
        }
        return this.mLocalAssetFetchSequence;
    }

    private synchronized Producer<CloseableReference<CloseableImage>> getLocalContentUriFetchSequence() {
        if (this.mLocalContentUriFetchSequence == null) {
            this.mLocalContentUriFetchSequence = newBitmapCacheGetToLocalTransformSequence(this.mProducerFactory.newContentUriFetchProducer());
        }
        return this.mLocalContentUriFetchSequence;
    }

    private synchronized Producer<CloseableReference<CloseableImage>> getLocalImageFileFetchSequence() {
        if (this.mLocalImageFileFetchSequence == null) {
            this.mLocalImageFileFetchSequence = newBitmapCacheGetToLocalTransformSequence(this.mProducerFactory.newLocalFileFetchProducer());
        }
        return this.mLocalImageFileFetchSequence;
    }

    private synchronized Producer<CloseableReference<CloseableImage>> getLocalResourceFetchSequence() {
        if (this.mLocalResourceFetchSequence == null) {
            this.mLocalResourceFetchSequence = newBitmapCacheGetToLocalTransformSequence(this.mProducerFactory.newLocalResourceFetchProducer());
        }
        return this.mLocalResourceFetchSequence;
    }

    private synchronized Producer<CloseableReference<CloseableImage>> getLocalVideoFileFetchSequence() {
        if (this.mLocalVideoFileFetchSequence == null) {
            this.mLocalVideoFileFetchSequence = newBitmapCacheGetToBitmapCacheSequence(this.mProducerFactory.newLocalVideoThumbnailProducer());
        }
        return this.mLocalVideoFileFetchSequence;
    }

    private synchronized Producer<CloseableReference<CloseableImage>> getNetworkFetchSequence() {
        if (this.mNetworkFetchSequence == null) {
            this.mNetworkFetchSequence = newBitmapCacheGetToDecodeSequence(getCommonNetworkFetchToEncodedMemorySequence());
        }
        return this.mNetworkFetchSequence;
    }

    private synchronized Producer<Void> getNetworkFetchToEncodedMemoryPrefetchSequence() {
        if (this.mNetworkFetchToEncodedMemoryPrefetchSequence == null) {
            ProducerFactory producerFactory = this.mProducerFactory;
            this.mNetworkFetchToEncodedMemoryPrefetchSequence = ProducerFactory.newSwallowResultProducer(getBackgroundNetworkFetchToEncodedMemorySequence());
        }
        return this.mNetworkFetchToEncodedMemoryPrefetchSequence;
    }

    private synchronized Producer<CloseableReference<CloseableImage>> getPostprocessorSequence(Producer<CloseableReference<CloseableImage>> producer) {
        if (!this.mPostprocessorSequences.containsKey(producer)) {
            this.mPostprocessorSequences.put(producer, this.mProducerFactory.newPostprocessorBitmapMemoryCacheProducer(this.mProducerFactory.newPostprocessorProducer(producer)));
        }
        return (Producer) this.mPostprocessorSequences.get(producer);
    }

    private Producer<CloseableReference<CloseableImage>> newBitmapCacheGetToBitmapCacheSequence(Producer<CloseableReference<CloseableImage>> producer) {
        return this.mProducerFactory.newBitmapMemoryCacheGetProducer(this.mProducerFactory.newBackgroundThreadHandoffProducer(this.mProducerFactory.newBitmapMemoryCacheKeyMultiplexProducer(this.mProducerFactory.newBitmapMemoryCacheProducer(producer))));
    }

    private Producer<CloseableReference<CloseableImage>> newBitmapCacheGetToDecodeSequence(Producer<EncodedImage> producer) {
        return newBitmapCacheGetToBitmapCacheSequence(this.mProducerFactory.newDecodeProducer(producer));
    }

    private Producer<CloseableReference<CloseableImage>> newBitmapCacheGetToLocalTransformSequence(Producer<EncodedImage> producer) {
        return newBitmapCacheGetToDecodeSequence(newLocalTransformationsSequence(newEncodedCacheMultiplexToTranscodeSequence(producer)));
    }

    private Producer<EncodedImage> newEncodedCacheMultiplexToTranscodeSequence(Producer<EncodedImage> producer) {
        Producer newWebpTranscodeProducer;
        if (VERSION.SDK_INT < 18) {
            newWebpTranscodeProducer = this.mProducerFactory.newWebpTranscodeProducer(producer);
        }
        return this.mProducerFactory.newEncodedCacheKeyMultiplexProducer(this.mProducerFactory.newEncodedMemoryCacheProducer(this.mProducerFactory.newDiskCacheProducer(newWebpTranscodeProducer)));
    }

    private Producer<EncodedImage> newLocalTransformationsSequence(Producer<EncodedImage> producer) {
        ProducerFactory producerFactory = this.mProducerFactory;
        Producer newAddImageTransformMetaDataProducer = ProducerFactory.newAddImageTransformMetaDataProducer(producer);
        if (!this.mDownsampleEnabled) {
            newAddImageTransformMetaDataProducer = this.mProducerFactory.newResizeAndRotateProducer(newAddImageTransformMetaDataProducer);
        }
        Producer newThrottlingProducer = this.mProducerFactory.newThrottlingProducer(MAX_SIMULTANEOUS_FILE_FETCH_AND_RESIZE, newAddImageTransformMetaDataProducer);
        newAddImageTransformMetaDataProducer = this.mProducerFactory.newLocalExifThumbnailProducer();
        if (!this.mDownsampleEnabled) {
            newAddImageTransformMetaDataProducer = this.mProducerFactory.newResizeAndRotateProducer(newAddImageTransformMetaDataProducer);
        }
        ProducerFactory producerFactory2 = this.mProducerFactory;
        return ProducerFactory.newBranchOnSeparateImagesProducer(newAddImageTransformMetaDataProducer, newThrottlingProducer);
    }

    private static void validateEncodedImageRequest(ImageRequest imageRequest) {
        Preconditions.checkNotNull(imageRequest);
        Preconditions.checkArgument(UriUtil.isNetworkUri(imageRequest.getSourceUri()));
        Preconditions.checkArgument(imageRequest.getLowestPermittedRequestLevel().getValue() <= RequestLevel.ENCODED_MEMORY_CACHE.getValue());
    }

    public Producer<Void> getDecodedImagePrefetchProducerSequence(ImageRequest imageRequest) {
        return getDecodedImagePrefetchSequence(getBasicDecodedImageSequence(imageRequest));
    }

    public Producer<CloseableReference<CloseableImage>> getDecodedImageProducerSequence(ImageRequest imageRequest) {
        Producer<CloseableReference<CloseableImage>> basicDecodedImageSequence = getBasicDecodedImageSequence(imageRequest);
        return imageRequest.getPostprocessor() != null ? getPostprocessorSequence(basicDecodedImageSequence) : basicDecodedImageSequence;
    }

    public Producer<Void> getEncodedImagePrefetchProducerSequence(ImageRequest imageRequest) {
        validateEncodedImageRequest(imageRequest);
        return getNetworkFetchToEncodedMemoryPrefetchSequence();
    }

    public Producer<CloseableReference<PooledByteBuffer>> getEncodedImageProducerSequence(ImageRequest imageRequest) {
        validateEncodedImageRequest(imageRequest);
        synchronized (this) {
            if (this.mEncodedImageProducerSequence == null) {
                this.mEncodedImageProducerSequence = new RemoveImageTransformMetaDataProducer(getBackgroundNetworkFetchToEncodedMemorySequence());
            }
        }
        return this.mEncodedImageProducerSequence;
    }
}
