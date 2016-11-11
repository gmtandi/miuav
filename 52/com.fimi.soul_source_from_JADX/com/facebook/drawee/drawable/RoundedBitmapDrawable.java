package com.facebook.drawee.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Path.FillType;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import javax.annotation.Nullable;

public class RoundedBitmapDrawable extends BitmapDrawable implements Rounded, TransformAwareDrawable {
    @VisibleForTesting
    int mBorderColor;
    private final Paint mBorderPaint;
    @VisibleForTesting
    float mBorderWidth;
    @VisibleForTesting
    float[] mCornerRadii;
    @VisibleForTesting
    final Matrix mInverseTransform;
    @VisibleForTesting
    boolean mIsCircle;
    @VisibleForTesting
    boolean mIsNonzero;
    private boolean mIsPathDirty;
    private boolean mIsShaderTransformDirty;
    private WeakReference<Bitmap> mLastBitmap;
    @VisibleForTesting
    final RectF mLastRootBounds;
    @VisibleForTesting
    final Matrix mLastTransform;
    private final Paint mPaint;
    private final Path mPath;
    @VisibleForTesting
    RectF mRootBounds;
    @VisibleForTesting
    final Matrix mTransform;
    @Nullable
    private TransformCallback mTransformCallback;

    public RoundedBitmapDrawable(Resources resources, Bitmap bitmap) {
        super(resources, bitmap);
        this.mIsCircle = false;
        this.mCornerRadii = new float[8];
        this.mRootBounds = new RectF();
        this.mLastRootBounds = new RectF();
        this.mTransform = new Matrix();
        this.mInverseTransform = new Matrix();
        this.mLastTransform = new Matrix();
        this.mBorderWidth = 0.0f;
        this.mBorderColor = 0;
        this.mIsNonzero = true;
        this.mPath = new Path();
        this.mIsPathDirty = true;
        this.mPaint = new Paint(1);
        this.mBorderPaint = new Paint(1);
        this.mIsShaderTransformDirty = true;
        this.mBorderPaint.setStyle(Style.STROKE);
    }

    public static RoundedBitmapDrawable fromBitmapDrawable(Resources resources, BitmapDrawable bitmapDrawable) {
        return new RoundedBitmapDrawable(resources, bitmapDrawable.getBitmap());
    }

    private void updateNonzero() {
        int i = 0;
        if (this.mIsPathDirty) {
            this.mIsNonzero = false;
            if (this.mIsCircle || this.mBorderWidth > 0.0f) {
                this.mIsNonzero = true;
            }
            while (i < this.mCornerRadii.length) {
                if (this.mCornerRadii[i] > 0.0f) {
                    this.mIsNonzero = true;
                }
                i++;
            }
        }
    }

    private void updatePaint() {
        Bitmap bitmap = getBitmap();
        if (this.mLastBitmap == null || this.mLastBitmap.get() != bitmap) {
            this.mLastBitmap = new WeakReference(bitmap);
            this.mPaint.setShader(new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP));
            this.mIsShaderTransformDirty = true;
        }
        if (this.mIsShaderTransformDirty) {
            this.mPaint.getShader().setLocalMatrix(this.mTransform);
            this.mIsShaderTransformDirty = false;
        }
    }

    private void updatePath() {
        if (this.mIsPathDirty) {
            this.mPath.reset();
            this.mRootBounds.inset(this.mBorderWidth / 2.0f, this.mBorderWidth / 2.0f);
            if (this.mIsCircle) {
                this.mPath.addCircle(this.mRootBounds.centerX(), this.mRootBounds.centerY(), Math.min(this.mRootBounds.width(), this.mRootBounds.height()) / 2.0f, Direction.CW);
            } else {
                this.mPath.addRoundRect(this.mRootBounds, this.mCornerRadii, Direction.CW);
            }
            this.mRootBounds.inset(-(this.mBorderWidth / 2.0f), -(this.mBorderWidth / 2.0f));
            this.mPath.setFillType(FillType.WINDING);
            this.mIsPathDirty = false;
        }
    }

    private void updateTransform() {
        if (this.mTransformCallback != null) {
            this.mTransformCallback.getTransform(this.mTransform);
            this.mTransformCallback.getRootBounds(this.mRootBounds);
        } else {
            this.mTransform.reset();
            this.mRootBounds.set(getBounds());
        }
        if (!this.mTransform.equals(this.mLastTransform)) {
            this.mIsShaderTransformDirty = true;
            if (!this.mTransform.invert(this.mInverseTransform)) {
                this.mInverseTransform.reset();
                this.mTransform.reset();
            }
            this.mLastTransform.set(this.mTransform);
        }
        if (!this.mRootBounds.equals(this.mLastRootBounds)) {
            this.mIsPathDirty = true;
            this.mLastRootBounds.set(this.mRootBounds);
        }
    }

    public void draw(Canvas canvas) {
        updateTransform();
        updateNonzero();
        if (this.mIsNonzero) {
            updatePath();
            updatePaint();
            int save = canvas.save();
            canvas.concat(this.mInverseTransform);
            canvas.drawPath(this.mPath, this.mPaint);
            if (this.mBorderWidth != 0.0f) {
                this.mBorderPaint.setStrokeWidth(this.mBorderWidth);
                this.mBorderPaint.setColor(DrawableUtils.multiplyColorAlpha(this.mBorderColor, this.mPaint.getAlpha()));
                canvas.drawPath(this.mPath, this.mBorderPaint);
            }
            canvas.restoreToCount(save);
            return;
        }
        super.draw(canvas);
    }

    public void setAlpha(int i) {
        if (i != this.mPaint.getAlpha()) {
            this.mPaint.setAlpha(i);
            invalidateSelf();
        }
    }

    public void setBorder(int i, float f) {
        if (this.mBorderColor != i || this.mBorderWidth != f) {
            this.mBorderColor = i;
            this.mBorderWidth = f;
            this.mIsPathDirty = true;
            invalidateSelf();
        }
    }

    public void setCircle(boolean z) {
        this.mIsCircle = z;
        this.mIsPathDirty = true;
        invalidateSelf();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
        super.setColorFilter(colorFilter);
    }

    public void setRadii(float[] fArr) {
        if (fArr == null) {
            Arrays.fill(this.mCornerRadii, 0.0f);
        } else {
            Preconditions.checkArgument(fArr.length == 8, "radii should have exactly 8 values");
            System.arraycopy(fArr, 0, this.mCornerRadii, 0, 8);
        }
        this.mIsPathDirty = true;
        invalidateSelf();
    }

    public void setRadius(float f) {
        Preconditions.checkState(f >= 0.0f);
        Arrays.fill(this.mCornerRadii, f);
        this.mIsPathDirty = true;
        invalidateSelf();
    }

    public void setTransformCallback(@Nullable TransformCallback transformCallback) {
        this.mTransformCallback = transformCallback;
    }
}
