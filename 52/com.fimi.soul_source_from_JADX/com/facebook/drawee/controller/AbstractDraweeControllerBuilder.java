package com.facebook.drawee.controller;

import android.content.Context;
import android.graphics.drawable.Animatable;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.datasource.DataSource;
import com.facebook.datasource.DataSources;
import com.facebook.datasource.FirstAvailableDataSourceSupplier;
import com.facebook.datasource.IncreasingQualityDataSourceSupplier;
import com.facebook.drawee.components.RetryManager;
import com.facebook.drawee.gestures.GestureDetector;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.interfaces.SimpleDraweeControllerBuilder;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nullable;

public abstract class AbstractDraweeControllerBuilder<BUILDER extends AbstractDraweeControllerBuilder<BUILDER, REQUEST, IMAGE, INFO>, REQUEST, IMAGE, INFO> implements SimpleDraweeControllerBuilder {
    private static final NullPointerException NO_REQUEST_EXCEPTION;
    private static final ControllerListener<Object> sAutoPlayAnimationsListener;
    private static final AtomicLong sIdCounter;
    private boolean mAutoPlayAnimations;
    private final Set<ControllerListener> mBoundControllerListeners;
    @Nullable
    private Object mCallerContext;
    private final Context mContext;
    @Nullable
    private ControllerListener<? super INFO> mControllerListener;
    @Nullable
    private Supplier<DataSource<IMAGE>> mDataSourceSupplier;
    @Nullable
    private REQUEST mImageRequest;
    @Nullable
    private REQUEST mLowResImageRequest;
    @Nullable
    private REQUEST[] mMultiImageRequests;
    @Nullable
    private DraweeController mOldController;
    private boolean mRetainImageOnFailure;
    private boolean mTapToRetryEnabled;
    private boolean mTryCacheOnlyFirst;

    /* renamed from: com.facebook.drawee.controller.AbstractDraweeControllerBuilder.1 */
    final class C09841 extends BaseControllerListener<Object> {
        C09841() {
        }

        public void onFinalImageSet(String str, @Nullable Object obj, @Nullable Animatable animatable) {
            if (animatable != null) {
                animatable.start();
            }
        }
    }

    /* renamed from: com.facebook.drawee.controller.AbstractDraweeControllerBuilder.2 */
    class C09852 implements Supplier<DataSource<IMAGE>> {
        final /* synthetic */ boolean val$bitmapCacheOnly;
        final /* synthetic */ Object val$callerContext;
        final /* synthetic */ Object val$imageRequest;

        C09852(Object obj, Object obj2, boolean z) {
            this.val$imageRequest = obj;
            this.val$callerContext = obj2;
            this.val$bitmapCacheOnly = z;
        }

        public DataSource<IMAGE> get() {
            return AbstractDraweeControllerBuilder.this.getDataSourceForRequest(this.val$imageRequest, this.val$callerContext, this.val$bitmapCacheOnly);
        }

        public String toString() {
            return Objects.toStringHelper((Object) this).add(SocialConstants.TYPE_REQUEST, this.val$imageRequest.toString()).toString();
        }
    }

    static {
        sAutoPlayAnimationsListener = new C09841();
        NO_REQUEST_EXCEPTION = new NullPointerException("No image request was specified!");
        sIdCounter = new AtomicLong();
    }

    protected AbstractDraweeControllerBuilder(Context context, Set<ControllerListener> set) {
        this.mContext = context;
        this.mBoundControllerListeners = set;
        init();
    }

    protected static String generateUniqueControllerId() {
        return String.valueOf(sIdCounter.getAndIncrement());
    }

    private void init() {
        this.mCallerContext = null;
        this.mImageRequest = null;
        this.mLowResImageRequest = null;
        this.mMultiImageRequests = null;
        this.mTryCacheOnlyFirst = true;
        this.mControllerListener = null;
        this.mTapToRetryEnabled = false;
        this.mAutoPlayAnimations = false;
        this.mOldController = null;
    }

    public AbstractDraweeController build() {
        validate();
        if (this.mImageRequest == null && this.mMultiImageRequests == null && this.mLowResImageRequest != null) {
            this.mImageRequest = this.mLowResImageRequest;
            this.mLowResImageRequest = null;
        }
        return buildController();
    }

    protected AbstractDraweeController buildController() {
        AbstractDraweeController obtainController = obtainController();
        obtainController.setRetainImageOnFailure(getRetainImageOnFailure());
        maybeBuildAndSetRetryManager(obtainController);
        maybeAttachListeners(obtainController);
        return obtainController;
    }

    public boolean getAutoPlayAnimations() {
        return this.mAutoPlayAnimations;
    }

    @Nullable
    public Object getCallerContext() {
        return this.mCallerContext;
    }

    protected Context getContext() {
        return this.mContext;
    }

    @Nullable
    public ControllerListener<? super INFO> getControllerListener() {
        return this.mControllerListener;
    }

    protected abstract DataSource<IMAGE> getDataSourceForRequest(REQUEST request, Object obj, boolean z);

    @Nullable
    public Supplier<DataSource<IMAGE>> getDataSourceSupplier() {
        return this.mDataSourceSupplier;
    }

    protected Supplier<DataSource<IMAGE>> getDataSourceSupplierForRequest(REQUEST request) {
        return getDataSourceSupplierForRequest(request, false);
    }

    protected Supplier<DataSource<IMAGE>> getDataSourceSupplierForRequest(REQUEST request, boolean z) {
        return new C09852(request, getCallerContext(), z);
    }

    protected Supplier<DataSource<IMAGE>> getFirstAvailableDataSourceSupplier(REQUEST[] requestArr, boolean z) {
        int i = 0;
        List arrayList = new ArrayList(requestArr.length * 2);
        if (z) {
            for (Object dataSourceSupplierForRequest : requestArr) {
                arrayList.add(getDataSourceSupplierForRequest(dataSourceSupplierForRequest, true));
            }
        }
        while (i < requestArr.length) {
            arrayList.add(getDataSourceSupplierForRequest(requestArr[i]));
            i++;
        }
        return FirstAvailableDataSourceSupplier.create(arrayList);
    }

    @Nullable
    public REQUEST[] getFirstAvailableImageRequests() {
        return this.mMultiImageRequests;
    }

    @Nullable
    public REQUEST getImageRequest() {
        return this.mImageRequest;
    }

    @Nullable
    public REQUEST getLowResImageRequest() {
        return this.mLowResImageRequest;
    }

    @Nullable
    public DraweeController getOldController() {
        return this.mOldController;
    }

    public boolean getRetainImageOnFailure() {
        return this.mRetainImageOnFailure;
    }

    public boolean getTapToRetryEnabled() {
        return this.mTapToRetryEnabled;
    }

    protected abstract BUILDER getThis();

    protected void maybeAttachListeners(AbstractDraweeController abstractDraweeController) {
        if (this.mBoundControllerListeners != null) {
            for (ControllerListener addControllerListener : this.mBoundControllerListeners) {
                abstractDraweeController.addControllerListener(addControllerListener);
            }
        }
        if (this.mControllerListener != null) {
            abstractDraweeController.addControllerListener(this.mControllerListener);
        }
        if (this.mAutoPlayAnimations) {
            abstractDraweeController.addControllerListener(sAutoPlayAnimationsListener);
        }
    }

    protected void maybeBuildAndSetGestureDetector(AbstractDraweeController abstractDraweeController) {
        if (abstractDraweeController.getGestureDetector() == null) {
            abstractDraweeController.setGestureDetector(GestureDetector.newInstance(this.mContext));
        }
    }

    protected void maybeBuildAndSetRetryManager(AbstractDraweeController abstractDraweeController) {
        if (this.mTapToRetryEnabled) {
            RetryManager retryManager = abstractDraweeController.getRetryManager();
            if (retryManager == null) {
                retryManager = new RetryManager();
                abstractDraweeController.setRetryManager(retryManager);
            }
            retryManager.setTapToRetryEnabled(this.mTapToRetryEnabled);
            maybeBuildAndSetGestureDetector(abstractDraweeController);
        }
    }

    protected abstract AbstractDraweeController obtainController();

    protected Supplier<DataSource<IMAGE>> obtainDataSourceSupplier() {
        if (this.mDataSourceSupplier != null) {
            return this.mDataSourceSupplier;
        }
        Supplier<DataSource<IMAGE>> supplier = null;
        if (this.mImageRequest != null) {
            supplier = getDataSourceSupplierForRequest(this.mImageRequest);
        } else if (this.mMultiImageRequests != null) {
            supplier = getFirstAvailableDataSourceSupplier(this.mMultiImageRequests, this.mTryCacheOnlyFirst);
        }
        if (!(supplier == null || this.mLowResImageRequest == null)) {
            List arrayList = new ArrayList(2);
            arrayList.add(supplier);
            arrayList.add(getDataSourceSupplierForRequest(this.mLowResImageRequest));
            supplier = IncreasingQualityDataSourceSupplier.create(arrayList);
        }
        return supplier == null ? DataSources.getFailedDataSourceSupplier(NO_REQUEST_EXCEPTION) : supplier;
    }

    public BUILDER reset() {
        init();
        return getThis();
    }

    public BUILDER setAutoPlayAnimations(boolean z) {
        this.mAutoPlayAnimations = z;
        return getThis();
    }

    public BUILDER setCallerContext(Object obj) {
        this.mCallerContext = obj;
        return getThis();
    }

    public BUILDER setControllerListener(ControllerListener<? super INFO> controllerListener) {
        this.mControllerListener = controllerListener;
        return getThis();
    }

    public void setDataSourceSupplier(@Nullable Supplier<DataSource<IMAGE>> supplier) {
        this.mDataSourceSupplier = supplier;
    }

    public BUILDER setFirstAvailableImageRequests(REQUEST[] requestArr) {
        return setFirstAvailableImageRequests(requestArr, true);
    }

    public BUILDER setFirstAvailableImageRequests(REQUEST[] requestArr, boolean z) {
        this.mMultiImageRequests = requestArr;
        this.mTryCacheOnlyFirst = z;
        return getThis();
    }

    public BUILDER setImageRequest(REQUEST request) {
        this.mImageRequest = request;
        return getThis();
    }

    public BUILDER setLowResImageRequest(REQUEST request) {
        this.mLowResImageRequest = request;
        return getThis();
    }

    public BUILDER setOldController(@Nullable DraweeController draweeController) {
        this.mOldController = draweeController;
        return getThis();
    }

    public BUILDER setRetainImageOnFailure(boolean z) {
        this.mRetainImageOnFailure = z;
        return getThis();
    }

    public BUILDER setTapToRetryEnabled(boolean z) {
        this.mTapToRetryEnabled = z;
        return getThis();
    }

    protected void validate() {
        boolean z = false;
        boolean z2 = this.mMultiImageRequests == null || this.mImageRequest == null;
        Preconditions.checkState(z2, "Cannot specify both ImageRequest and FirstAvailableImageRequests!");
        if (this.mDataSourceSupplier == null || (this.mMultiImageRequests == null && this.mImageRequest == null && this.mLowResImageRequest == null)) {
            z = true;
        }
        Preconditions.checkState(z, "Cannot specify DataSourceSupplier with other ImageRequests! Use one or the other.");
    }
}
