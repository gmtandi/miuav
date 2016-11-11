package com.facebook.imagepipeline.image;

import android.graphics.Bitmap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.references.ResourceReleaser;
import com.facebook.imageutils.BitmapUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class CloseableAnimatedBitmap extends CloseableBitmap {
    @GuardedBy("this")
    private List<CloseableReference<Bitmap>> mBitmapReferences;
    private volatile List<Bitmap> mBitmaps;
    private volatile List<Integer> mDurations;

    public CloseableAnimatedBitmap(List<CloseableReference<Bitmap>> list, List<Integer> list2) {
        boolean z = true;
        Preconditions.checkNotNull(list);
        Preconditions.checkState(list.size() >= 1, "Need at least 1 frame!");
        this.mBitmapReferences = new ArrayList();
        this.mBitmaps = new ArrayList();
        for (CloseableReference closeableReference : list) {
            this.mBitmapReferences.add(closeableReference.clone());
            this.mBitmaps.add(closeableReference.get());
        }
        this.mDurations = (List) Preconditions.checkNotNull(list2);
        if (this.mDurations.size() != this.mBitmaps.size()) {
            z = false;
        }
        Preconditions.checkState(z, "Arrays length mismatch!");
    }

    public CloseableAnimatedBitmap(List<Bitmap> list, List<Integer> list2, ResourceReleaser<Bitmap> resourceReleaser) {
        boolean z = true;
        Preconditions.checkNotNull(list);
        Preconditions.checkState(list.size() >= 1, "Need at least 1 frame!");
        this.mBitmaps = new ArrayList();
        this.mBitmapReferences = new ArrayList();
        for (Bitmap bitmap : list) {
            this.mBitmapReferences.add(CloseableReference.of(bitmap, resourceReleaser));
            this.mBitmaps.add(bitmap);
        }
        this.mDurations = (List) Preconditions.checkNotNull(list2);
        if (this.mDurations.size() != this.mBitmaps.size()) {
            z = false;
        }
        Preconditions.checkState(z, "Arrays length mismatch!");
    }

    public void close() {
        synchronized (this) {
            if (this.mBitmapReferences == null) {
                return;
            }
            Iterable iterable = this.mBitmapReferences;
            this.mBitmapReferences = null;
            this.mBitmaps = null;
            this.mDurations = null;
            CloseableReference.closeSafely(iterable);
        }
    }

    public List<Bitmap> getBitmaps() {
        return this.mBitmaps;
    }

    public List<Integer> getDurations() {
        return this.mDurations;
    }

    public int getHeight() {
        List list = this.mBitmaps;
        return list == null ? 0 : ((Bitmap) list.get(0)).getHeight();
    }

    public int getSizeInBytes() {
        List list = this.mBitmaps;
        return (list == null || list.size() == 0) ? 0 : BitmapUtil.getSizeInBytes((Bitmap) list.get(0)) * list.size();
    }

    public Bitmap getUnderlyingBitmap() {
        List list = this.mBitmaps;
        return list != null ? (Bitmap) list.get(0) : null;
    }

    public int getWidth() {
        List list = this.mBitmaps;
        return list == null ? 0 : ((Bitmap) list.get(0)).getWidth();
    }

    public synchronized boolean isClosed() {
        return this.mBitmaps == null;
    }
}
