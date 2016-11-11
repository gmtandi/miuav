package com.facebook.imagepipeline.animated.impl;

import android.app.ActivityManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.support.v4.util.SparseArrayCompat;
import com.facebook.common.executors.SerialExecutorService;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.references.ResourceReleaser;
import com.facebook.common.time.MonotonicClock;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableBackend;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableCachingBackend;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableFrameInfo.DisposalMethod;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableOptions;
import com.facebook.imagepipeline.animated.base.DelegatingAnimatedDrawableBackend;
import com.facebook.imagepipeline.animated.impl.AnimatedImageCompositor.Callback;
import com.facebook.imagepipeline.animated.util.AnimatedDrawableUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.concurrent.GuardedBy;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;
import p000a.C0001q;
import p000a.C0018s;

public class AnimatedDrawableCachingBackendImpl extends DelegatingAnimatedDrawableBackend implements AnimatedDrawableCachingBackend {
    private static final int PREFETCH_FRAMES = 3;
    private static final Class<?> TAG;
    private static final AtomicInteger sTotalBitmaps;
    private final ActivityManager mActivityManager;
    private final AnimatedDrawableBackend mAnimatedDrawableBackend;
    private final AnimatedDrawableOptions mAnimatedDrawableOptions;
    private final AnimatedDrawableUtil mAnimatedDrawableUtil;
    private final AnimatedImageCompositor mAnimatedImageCompositor;
    private final double mApproxKiloBytesToHoldAllFrames;
    @GuardedBy("this")
    private final WhatToKeepCachedArray mBitmapsToKeepCached;
    @GuardedBy("this")
    private final SparseArrayCompat<CloseableReference<Bitmap>> mCachedBitmaps;
    @GuardedBy("ui-thread")
    private int mCurrentFrameIndex;
    @GuardedBy("this")
    private final SparseArrayCompat<C0018s<Object>> mDecodesInFlight;
    private final SerialExecutorService mExecutorService;
    @GuardedBy("this")
    private final List<Bitmap> mFreeBitmaps;
    private final double mMaximumKiloBytes;
    private final MonotonicClock mMonotonicClock;
    private final ResourceReleaser<Bitmap> mResourceReleaserForBitmaps;

    /* renamed from: com.facebook.imagepipeline.animated.impl.AnimatedDrawableCachingBackendImpl.1 */
    class C09981 implements Callback {
        C09981() {
        }

        public CloseableReference<Bitmap> getCachedBitmap(int i) {
            return AnimatedDrawableCachingBackendImpl.this.getCachedOrPredecodedFrame(i);
        }

        public void onIntermediateResult(int i, Bitmap bitmap) {
            AnimatedDrawableCachingBackendImpl.this.maybeCacheBitmapDuringRender(i, bitmap);
        }
    }

    /* renamed from: com.facebook.imagepipeline.animated.impl.AnimatedDrawableCachingBackendImpl.2 */
    class C09992 implements ResourceReleaser<Bitmap> {
        C09992() {
        }

        public void release(Bitmap bitmap) {
            AnimatedDrawableCachingBackendImpl.this.releaseBitmapInternal(bitmap);
        }
    }

    /* renamed from: com.facebook.imagepipeline.animated.impl.AnimatedDrawableCachingBackendImpl.3 */
    class C10003 implements Callable<Object> {
        final /* synthetic */ int val$frameNumber;

        C10003(int i) {
            this.val$frameNumber = i;
        }

        public Object call() {
            AnimatedDrawableCachingBackendImpl.this.runPrefetch(this.val$frameNumber);
            return null;
        }
    }

    /* renamed from: com.facebook.imagepipeline.animated.impl.AnimatedDrawableCachingBackendImpl.4 */
    class C10014 implements C0001q<Object, Object> {
        final /* synthetic */ int val$frameNumber;
        final /* synthetic */ C0018s val$newFuture;

        C10014(C0018s c0018s, int i) {
            this.val$newFuture = c0018s;
            this.val$frameNumber = i;
        }

        public Object then(C0018s<Object> c0018s) {
            AnimatedDrawableCachingBackendImpl.this.onFutureFinished(this.val$newFuture, this.val$frameNumber);
            return null;
        }
    }

    static {
        TAG = AnimatedDrawableCachingBackendImpl.class;
        sTotalBitmaps = new AtomicInteger();
    }

    public AnimatedDrawableCachingBackendImpl(SerialExecutorService serialExecutorService, ActivityManager activityManager, AnimatedDrawableUtil animatedDrawableUtil, MonotonicClock monotonicClock, AnimatedDrawableBackend animatedDrawableBackend, AnimatedDrawableOptions animatedDrawableOptions) {
        super(animatedDrawableBackend);
        this.mExecutorService = serialExecutorService;
        this.mActivityManager = activityManager;
        this.mAnimatedDrawableUtil = animatedDrawableUtil;
        this.mMonotonicClock = monotonicClock;
        this.mAnimatedDrawableBackend = animatedDrawableBackend;
        this.mAnimatedDrawableOptions = animatedDrawableOptions;
        this.mMaximumKiloBytes = animatedDrawableOptions.maximumBytes >= 0 ? (double) (animatedDrawableOptions.maximumBytes / SmileConstants.MAX_SHARED_STRING_VALUES) : (double) (getDefaultMaxBytes(activityManager) / SmileConstants.MAX_SHARED_STRING_VALUES);
        this.mAnimatedImageCompositor = new AnimatedImageCompositor(animatedDrawableBackend, new C09981());
        this.mResourceReleaserForBitmaps = new C09992();
        this.mFreeBitmaps = new ArrayList();
        this.mDecodesInFlight = new SparseArrayCompat(10);
        this.mCachedBitmaps = new SparseArrayCompat(10);
        this.mBitmapsToKeepCached = new WhatToKeepCachedArray(this.mAnimatedDrawableBackend.getFrameCount());
        this.mApproxKiloBytesToHoldAllFrames = (double) ((((this.mAnimatedDrawableBackend.getRenderedWidth() * this.mAnimatedDrawableBackend.getRenderedHeight()) / SmileConstants.MAX_SHARED_STRING_VALUES) * this.mAnimatedDrawableBackend.getFrameCount()) * 4);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void cancelFuturesOutsideOfRange(int r3, int r4) {
        /*
        r2 = this;
        monitor-enter(r2);
        r1 = 0;
    L_0x0002:
        r0 = r2.mDecodesInFlight;	 Catch:{ all -> 0x002b }
        r0 = r0.size();	 Catch:{ all -> 0x002b }
        if (r1 >= r0) goto L_0x0029;
    L_0x000a:
        r0 = r2.mDecodesInFlight;	 Catch:{ all -> 0x002b }
        r0 = r0.keyAt(r1);	 Catch:{ all -> 0x002b }
        r0 = com.facebook.imagepipeline.animated.util.AnimatedDrawableUtil.isOutsideRange(r3, r4, r0);	 Catch:{ all -> 0x002b }
        if (r0 == 0) goto L_0x0026;
    L_0x0016:
        r0 = r2.mDecodesInFlight;	 Catch:{ all -> 0x002b }
        r0 = r0.valueAt(r1);	 Catch:{ all -> 0x002b }
        r0 = (p000a.C0018s) r0;	 Catch:{ all -> 0x002b }
        r0 = r2.mDecodesInFlight;	 Catch:{ all -> 0x002b }
        r0.removeAt(r1);	 Catch:{ all -> 0x002b }
        r0 = r1;
    L_0x0024:
        r1 = r0;
        goto L_0x0002;
    L_0x0026:
        r0 = r1 + 1;
        goto L_0x0024;
    L_0x0029:
        monitor-exit(r2);
        return;
    L_0x002b:
        r0 = move-exception;
        monitor-exit(r2);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.animated.impl.AnimatedDrawableCachingBackendImpl.cancelFuturesOutsideOfRange(int, int):void");
    }

    private void copyAndCacheBitmapDuringRendering(int i, Bitmap bitmap) {
        CloseableReference obtainBitmapInternal = obtainBitmapInternal();
        try {
            Canvas canvas = new Canvas((Bitmap) obtainBitmapInternal.get());
            canvas.drawColor(0, Mode.SRC);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, null);
            maybeCacheRenderedBitmap(i, obtainBitmapInternal);
        } finally {
            obtainBitmapInternal.close();
        }
    }

    private Bitmap createNewBitmap() {
        FLog.m7617v(TAG, "Creating new bitmap");
        sTotalBitmaps.incrementAndGet();
        FLog.m7618v(TAG, "Total bitmaps: %d", Integer.valueOf(sTotalBitmaps.get()));
        return Bitmap.createBitmap(this.mAnimatedDrawableBackend.getRenderedWidth(), this.mAnimatedDrawableBackend.getRenderedHeight(), Config.ARGB_8888);
    }

    private synchronized void doPrefetch(int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            int frameCount = (i + i3) % this.mAnimatedDrawableBackend.getFrameCount();
            C0018s c0018s = (C0018s) this.mDecodesInFlight.get(frameCount);
            if (!hasCachedOrPredecodedFrame(frameCount) && c0018s == null) {
                c0018s = C0018s.m80a(new C10003(frameCount), this.mExecutorService);
                this.mDecodesInFlight.put(frameCount, c0018s);
                c0018s.m95a(new C10014(c0018s, frameCount));
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void dropBitmapsThatShouldNotBeCached() {
        /*
        r3 = this;
        monitor-enter(r3);
        r1 = 0;
    L_0x0002:
        r0 = r3.mCachedBitmaps;	 Catch:{ all -> 0x0030 }
        r0 = r0.size();	 Catch:{ all -> 0x0030 }
        if (r1 >= r0) goto L_0x002e;
    L_0x000a:
        r0 = r3.mCachedBitmaps;	 Catch:{ all -> 0x0030 }
        r0 = r0.keyAt(r1);	 Catch:{ all -> 0x0030 }
        r2 = r3.mBitmapsToKeepCached;	 Catch:{ all -> 0x0030 }
        r0 = r2.get(r0);	 Catch:{ all -> 0x0030 }
        if (r0 != 0) goto L_0x002b;
    L_0x0018:
        r0 = r3.mCachedBitmaps;	 Catch:{ all -> 0x0030 }
        r0 = r0.valueAt(r1);	 Catch:{ all -> 0x0030 }
        r0 = (com.facebook.common.references.CloseableReference) r0;	 Catch:{ all -> 0x0030 }
        r2 = r3.mCachedBitmaps;	 Catch:{ all -> 0x0030 }
        r2.removeAt(r1);	 Catch:{ all -> 0x0030 }
        r0.close();	 Catch:{ all -> 0x0030 }
        r0 = r1;
    L_0x0029:
        r1 = r0;
        goto L_0x0002;
    L_0x002b:
        r0 = r1 + 1;
        goto L_0x0029;
    L_0x002e:
        monitor-exit(r3);
        return;
    L_0x0030:
        r0 = move-exception;
        monitor-exit(r3);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.animated.impl.AnimatedDrawableCachingBackendImpl.dropBitmapsThatShouldNotBeCached():void");
    }

    private CloseableReference<Bitmap> getBitmapForFrameInternal(int i, boolean z) {
        CloseableReference obtainBitmapInternal;
        Throwable th;
        Object obj;
        long now = this.mMonotonicClock.now();
        try {
            synchronized (this) {
                this.mBitmapsToKeepCached.set(i, true);
                CloseableReference<Bitmap> cachedOrPredecodedFrame = getCachedOrPredecodedFrame(i);
                long now2;
                String str;
                if (cachedOrPredecodedFrame != null) {
                    now2 = this.mMonotonicClock.now() - now;
                    if (now2 > 10) {
                        str = C2915a.f14760f;
                        FLog.m7620v(TAG, "obtainBitmap for frame %d took %d ms (%s)", Integer.valueOf(i), Long.valueOf(now2), (Object) "ok");
                    }
                    return cachedOrPredecodedFrame;
                }
                if (z) {
                    try {
                        obtainBitmapInternal = obtainBitmapInternal();
                        this.mAnimatedImageCompositor.renderFrame(i, (Bitmap) obtainBitmapInternal.get());
                        maybeCacheRenderedBitmap(i, obtainBitmapInternal);
                        cachedOrPredecodedFrame = obtainBitmapInternal.clone();
                        obtainBitmapInternal.close();
                        now2 = this.mMonotonicClock.now() - now;
                        if (now2 > 10) {
                            str = C2915a.f14760f;
                            FLog.m7620v(TAG, "obtainBitmap for frame %d took %d ms (%s)", Integer.valueOf(i), Long.valueOf(now2), (Object) "renderedOnCallingThread");
                        }
                        return cachedOrPredecodedFrame;
                    } catch (Throwable th2) {
                        th = th2;
                        int i2 = 1;
                        now2 = this.mMonotonicClock.now() - now;
                        if (now2 > 10) {
                            String str2 = C2915a.f14760f;
                            FLog.m7620v(TAG, "obtainBitmap for frame %d took %d ms (%s)", Integer.valueOf(i), Long.valueOf(now2), obj != null ? "renderedOnCallingThread" : "ok");
                        }
                        throw th;
                    }
                }
                now2 = this.mMonotonicClock.now() - now;
                if (now2 > 10) {
                    str = C2915a.f14760f;
                    FLog.m7620v(TAG, "obtainBitmap for frame %d took %d ms (%s)", Integer.valueOf(i), Long.valueOf(now2), (Object) "deferred");
                }
                return null;
            }
        } catch (Throwable th22) {
            th = th22;
            obj = null;
        }
    }

    private synchronized CloseableReference<Bitmap> getCachedOrPredecodedFrame(int i) {
        CloseableReference<Bitmap> cloneOrNull;
        cloneOrNull = CloseableReference.cloneOrNull((CloseableReference) this.mCachedBitmaps.get(i));
        if (cloneOrNull == null) {
            cloneOrNull = this.mAnimatedDrawableBackend.getPreDecodedFrame(i);
        }
        return cloneOrNull;
    }

    private static int getDefaultMaxBytes(ActivityManager activityManager) {
        return activityManager.getMemoryClass() > 32 ? 5242880 : 3145728;
    }

    private synchronized boolean hasCachedOrPredecodedFrame(int i) {
        boolean z;
        z = this.mCachedBitmaps.get(i) != null || this.mAnimatedDrawableBackend.hasPreDecodedFrame(i);
        return z;
    }

    private void maybeCacheBitmapDuringRender(int i, Bitmap bitmap) {
        Object obj = null;
        synchronized (this) {
            if (this.mBitmapsToKeepCached.get(i) && this.mCachedBitmaps.get(i) == null) {
                obj = 1;
            }
        }
        if (obj != null) {
            copyAndCacheBitmapDuringRendering(i, bitmap);
        }
    }

    private synchronized void maybeCacheRenderedBitmap(int i, CloseableReference<Bitmap> closeableReference) {
        if (this.mBitmapsToKeepCached.get(i)) {
            int indexOfKey = this.mCachedBitmaps.indexOfKey(i);
            if (indexOfKey >= 0) {
                ((CloseableReference) this.mCachedBitmaps.valueAt(indexOfKey)).close();
                this.mCachedBitmaps.removeAt(indexOfKey);
            }
            this.mCachedBitmaps.put(i, closeableReference.clone());
        }
    }

    private CloseableReference<Bitmap> obtainBitmapInternal() {
        Object createNewBitmap;
        synchronized (this) {
            long nanoTime = System.nanoTime();
            long convert = TimeUnit.NANOSECONDS.convert(20, TimeUnit.MILLISECONDS) + nanoTime;
            while (this.mFreeBitmaps.isEmpty() && nanoTime < convert) {
                try {
                    TimeUnit.NANOSECONDS.timedWait(this, convert - nanoTime);
                    nanoTime = System.nanoTime();
                } catch (Throwable e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
            }
            if (this.mFreeBitmaps.isEmpty()) {
                createNewBitmap = createNewBitmap();
            } else {
                Bitmap bitmap = (Bitmap) this.mFreeBitmaps.remove(this.mFreeBitmaps.size() - 1);
            }
        }
        return CloseableReference.of(createNewBitmap, this.mResourceReleaserForBitmaps);
    }

    private synchronized void onFutureFinished(C0018s<?> c0018s, int i) {
        int indexOfKey = this.mDecodesInFlight.indexOfKey(i);
        if (indexOfKey >= 0 && ((C0018s) this.mDecodesInFlight.valueAt(indexOfKey)) == c0018s) {
            this.mDecodesInFlight.removeAt(indexOfKey);
            if (c0018s.m109f() != null) {
                FLog.m7624v(TAG, c0018s.m109f(), "Failed to render frame %d", Integer.valueOf(i));
            }
        }
    }

    private void runPrefetch(int r6) {
        /* JADX: method processing error */
/*
        Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.imagepipeline.animated.impl.AnimatedDrawableCachingBackendImpl.runPrefetch(int):void. bs: [B:16:0x001f, B:22:0x002a]
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:86)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:57)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
        /*
        r5 = this;
        monitor-enter(r5);
        r0 = r5.mBitmapsToKeepCached;	 Catch:{ all -> 0x0013 }
        r0 = r0.get(r6);	 Catch:{ all -> 0x0013 }
        if (r0 != 0) goto L_0x000b;	 Catch:{ all -> 0x0013 }
    L_0x0009:
        monitor-exit(r5);	 Catch:{ all -> 0x0013 }
    L_0x000a:
        return;	 Catch:{ all -> 0x0013 }
    L_0x000b:
        r0 = r5.hasCachedOrPredecodedFrame(r6);	 Catch:{ all -> 0x0013 }
        if (r0 == 0) goto L_0x0016;	 Catch:{ all -> 0x0013 }
    L_0x0011:
        monitor-exit(r5);	 Catch:{ all -> 0x0013 }
        goto L_0x000a;	 Catch:{ all -> 0x0013 }
    L_0x0013:
        r0 = move-exception;	 Catch:{ all -> 0x0013 }
        monitor-exit(r5);	 Catch:{ all -> 0x0013 }
        throw r0;
    L_0x0016:
        monitor-exit(r5);	 Catch:{ all -> 0x0013 }
        r0 = r5.mAnimatedDrawableBackend;
        r1 = r0.getPreDecodedFrame(r6);
        if (r1 == 0) goto L_0x0026;
    L_0x001f:
        r5.maybeCacheRenderedBitmap(r6, r1);	 Catch:{ all -> 0x0047 }
    L_0x0022:
        com.facebook.common.references.CloseableReference.closeSafely(r1);
        goto L_0x000a;
    L_0x0026:
        r2 = r5.obtainBitmapInternal();	 Catch:{ all -> 0x0047 }
        r3 = r5.mAnimatedImageCompositor;	 Catch:{ all -> 0x004c }
        r0 = r2.get();	 Catch:{ all -> 0x004c }
        r0 = (android.graphics.Bitmap) r0;	 Catch:{ all -> 0x004c }
        r3.renderFrame(r6, r0);	 Catch:{ all -> 0x004c }
        r5.maybeCacheRenderedBitmap(r6, r2);	 Catch:{ all -> 0x004c }
        r0 = TAG;	 Catch:{ all -> 0x004c }
        r3 = "Prefetch rendered frame %d";	 Catch:{ all -> 0x004c }
        r4 = java.lang.Integer.valueOf(r6);	 Catch:{ all -> 0x004c }
        com.facebook.common.logging.FLog.m7618v(r0, r3, r4);	 Catch:{ all -> 0x004c }
        r2.close();	 Catch:{ all -> 0x0047 }
        goto L_0x0022;
    L_0x0047:
        r0 = move-exception;
        com.facebook.common.references.CloseableReference.closeSafely(r1);
        throw r0;
    L_0x004c:
        r0 = move-exception;
        r2.close();	 Catch:{ all -> 0x0047 }
        throw r0;	 Catch:{ all -> 0x0047 }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.animated.impl.AnimatedDrawableCachingBackendImpl.runPrefetch(int):void");
    }

    private synchronized void schedulePrefetches() {
        int i = 1;
        synchronized (this) {
            int i2 = this.mAnimatedDrawableBackend.getFrameInfo(this.mCurrentFrameIndex).disposalMethod == DisposalMethod.DISPOSE_TO_PREVIOUS ? 1 : 0;
            int max = Math.max(0, this.mCurrentFrameIndex - (i2 != 0 ? 1 : 0));
            int i3 = this.mAnimatedDrawableOptions.allowPrefetching ? PREFETCH_FRAMES : 0;
            if (i2 == 0) {
                i = 0;
            }
            int max2 = Math.max(i3, i);
            i = (max + max2) % this.mAnimatedDrawableBackend.getFrameCount();
            cancelFuturesOutsideOfRange(max, i);
            if (!shouldKeepAllFramesInMemory()) {
                this.mBitmapsToKeepCached.setAll(true);
                this.mBitmapsToKeepCached.removeOutsideRange(max, i);
                for (i = max; i >= 0; i--) {
                    if (this.mCachedBitmaps.get(i) != null) {
                        this.mBitmapsToKeepCached.set(i, true);
                        break;
                    }
                }
                dropBitmapsThatShouldNotBeCached();
            }
            if (this.mAnimatedDrawableOptions.allowPrefetching) {
                doPrefetch(max, max2);
            } else {
                cancelFuturesOutsideOfRange(this.mCurrentFrameIndex, this.mCurrentFrameIndex);
            }
        }
    }

    private boolean shouldKeepAllFramesInMemory() {
        return this.mAnimatedDrawableOptions.forceKeepAllFramesInMemory || this.mApproxKiloBytesToHoldAllFrames < this.mMaximumKiloBytes;
    }

    public void appendDebugOptionString(StringBuilder stringBuilder) {
        if (this.mAnimatedDrawableOptions.forceKeepAllFramesInMemory) {
            stringBuilder.append("Pinned To Memory");
        } else {
            if (this.mApproxKiloBytesToHoldAllFrames < this.mMaximumKiloBytes) {
                stringBuilder.append("within ");
            } else {
                stringBuilder.append("exceeds ");
            }
            this.mAnimatedDrawableUtil.appendMemoryString(stringBuilder, (int) this.mMaximumKiloBytes);
        }
        if (shouldKeepAllFramesInMemory() && this.mAnimatedDrawableOptions.allowPrefetching) {
            stringBuilder.append(" MT");
        }
    }

    public synchronized void dropCaches() {
        this.mBitmapsToKeepCached.setAll(false);
        dropBitmapsThatShouldNotBeCached();
        for (Bitmap recycle : this.mFreeBitmaps) {
            recycle.recycle();
            sTotalBitmaps.decrementAndGet();
        }
        this.mFreeBitmaps.clear();
        this.mAnimatedDrawableBackend.dropCaches();
        FLog.m7618v(TAG, "Total bitmaps: %d", Integer.valueOf(sTotalBitmaps.get()));
    }

    protected synchronized void finalize() {
        super.finalize();
        if (this.mCachedBitmaps.size() > 0) {
            FLog.m7577d(TAG, "Finalizing with rendered bitmaps");
        }
        sTotalBitmaps.addAndGet(-this.mFreeBitmaps.size());
        this.mFreeBitmaps.clear();
    }

    public AnimatedDrawableCachingBackend forNewBounds(Rect rect) {
        AnimatedDrawableBackend forNewBounds = this.mAnimatedDrawableBackend.forNewBounds(rect);
        return forNewBounds == this.mAnimatedDrawableBackend ? this : new AnimatedDrawableCachingBackendImpl(this.mExecutorService, this.mActivityManager, this.mAnimatedDrawableUtil, this.mMonotonicClock, forNewBounds, this.mAnimatedDrawableOptions);
    }

    public CloseableReference<Bitmap> getBitmapForFrame(int i) {
        this.mCurrentFrameIndex = i;
        CloseableReference<Bitmap> bitmapForFrameInternal = getBitmapForFrameInternal(i, false);
        schedulePrefetches();
        return bitmapForFrameInternal;
    }

    @VisibleForTesting
    CloseableReference<Bitmap> getBitmapForFrameBlocking(int i) {
        this.mCurrentFrameIndex = i;
        CloseableReference<Bitmap> bitmapForFrameInternal = getBitmapForFrameInternal(i, true);
        schedulePrefetches();
        return bitmapForFrameInternal;
    }

    @VisibleForTesting
    synchronized Map<Integer, C0018s<?>> getDecodesInFlight() {
        Map<Integer, C0018s<?>> hashMap;
        hashMap = new HashMap();
        for (int i = 0; i < this.mDecodesInFlight.size(); i++) {
            hashMap.put(Integer.valueOf(this.mDecodesInFlight.keyAt(i)), this.mDecodesInFlight.valueAt(i));
        }
        return hashMap;
    }

    @VisibleForTesting
    synchronized Set<Integer> getFramesCached() {
        Set<Integer> hashSet;
        hashSet = new HashSet();
        for (int i = 0; i < this.mCachedBitmaps.size(); i++) {
            hashSet.add(Integer.valueOf(this.mCachedBitmaps.keyAt(i)));
        }
        return hashSet;
    }

    public int getMemoryUsage() {
        int i;
        int i2 = 0;
        synchronized (this) {
            i = 0;
            for (Bitmap sizeOfBitmap : this.mFreeBitmaps) {
                i += this.mAnimatedDrawableUtil.getSizeOfBitmap(sizeOfBitmap);
            }
            while (i2 < this.mCachedBitmaps.size()) {
                i += this.mAnimatedDrawableUtil.getSizeOfBitmap((Bitmap) ((CloseableReference) this.mCachedBitmaps.valueAt(i2)).get());
                i2++;
            }
        }
        return this.mAnimatedDrawableBackend.getMemoryUsage() + i;
    }

    public CloseableReference<Bitmap> getPreviewBitmap() {
        return getAnimatedImageResult().getPreviewBitmap();
    }

    synchronized void releaseBitmapInternal(Bitmap bitmap) {
        this.mFreeBitmaps.add(bitmap);
    }

    public void renderFrame(int i, Canvas canvas) {
        throw new IllegalStateException();
    }
}
