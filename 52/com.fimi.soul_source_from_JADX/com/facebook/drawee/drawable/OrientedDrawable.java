package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

public class OrientedDrawable extends ForwardingDrawable {
    private int mRotationAngle;
    @VisibleForTesting
    final Matrix mRotationMatrix;
    private final Matrix mTempMatrix;
    private final RectF mTempRectF;

    public OrientedDrawable(Drawable drawable, int i) {
        super(drawable);
        this.mTempMatrix = new Matrix();
        this.mTempRectF = new RectF();
        Preconditions.checkArgument(i % 90 == 0);
        this.mRotationMatrix = new Matrix();
        this.mRotationAngle = i;
    }

    public void draw(Canvas canvas) {
        if (this.mRotationAngle <= 0) {
            super.draw(canvas);
            return;
        }
        int save = canvas.save();
        canvas.concat(this.mRotationMatrix);
        super.draw(canvas);
        canvas.restoreToCount(save);
    }

    public int getIntrinsicHeight() {
        return this.mRotationAngle % Opcodes.GETFIELD == 0 ? super.getIntrinsicHeight() : super.getIntrinsicWidth();
    }

    public int getIntrinsicWidth() {
        return this.mRotationAngle % Opcodes.GETFIELD == 0 ? super.getIntrinsicWidth() : super.getIntrinsicHeight();
    }

    public void getTransform(Matrix matrix) {
        getParentTransform(matrix);
        if (!this.mRotationMatrix.isIdentity()) {
            matrix.preConcat(this.mRotationMatrix);
        }
    }

    protected void onBoundsChange(Rect rect) {
        Drawable current = getCurrent();
        if (this.mRotationAngle > 0) {
            this.mRotationMatrix.setRotate((float) this.mRotationAngle, (float) rect.centerX(), (float) rect.centerY());
            this.mTempMatrix.reset();
            this.mRotationMatrix.invert(this.mTempMatrix);
            this.mTempRectF.set(rect);
            this.mTempMatrix.mapRect(this.mTempRectF);
            current.setBounds((int) this.mTempRectF.left, (int) this.mTempRectF.top, (int) this.mTempRectF.right, (int) this.mTempRectF.bottom);
            return;
        }
        current.setBounds(rect);
    }
}
