package com.fimi.soul.media.player.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.fimi.soul.media.player.IMediaPlayer;
import com.fimi.soul.media.player.widget.IRenderView.IRenderCallback;
import com.fimi.soul.media.player.widget.IRenderView.ISurfaceHolder;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.p122a.p123a.C2915a;

public class SurfaceRenderView extends SurfaceView implements IRenderView {
    private MeasureHelper mMeasureHelper;
    private SurfaceCallback mSurfaceCallback;

    final class InternalSurfaceHolder implements ISurfaceHolder {
        private SurfaceHolder mSurfaceHolder;
        private SurfaceRenderView mSurfaceView;

        public InternalSurfaceHolder(@NonNull SurfaceRenderView surfaceRenderView, @Nullable SurfaceHolder surfaceHolder) {
            this.mSurfaceView = surfaceRenderView;
            this.mSurfaceHolder = surfaceHolder;
        }

        public void bindToMediaPlayer(IMediaPlayer iMediaPlayer) {
            if (iMediaPlayer != null) {
                iMediaPlayer.setDisplay(this.mSurfaceHolder);
            }
        }

        @NonNull
        public IRenderView getRenderView() {
            return this.mSurfaceView;
        }

        @Nullable
        public SurfaceHolder getSurfaceHolder() {
            return this.mSurfaceHolder;
        }

        @Nullable
        public SurfaceTexture getSurfaceTexture() {
            return null;
        }

        @Nullable
        public Surface openSurface() {
            return this.mSurfaceHolder == null ? null : this.mSurfaceHolder.getSurface();
        }
    }

    final class SurfaceCallback implements Callback {
        private int mFormat;
        private int mHeight;
        private boolean mIsFormatChanged;
        private Map<IRenderCallback, Object> mRenderCallbackMap;
        private SurfaceHolder mSurfaceHolder;
        private WeakReference<SurfaceRenderView> mWeakSurfaceView;
        private int mWidth;

        public SurfaceCallback(@NonNull SurfaceRenderView surfaceRenderView) {
            this.mRenderCallbackMap = new ConcurrentHashMap();
            this.mWeakSurfaceView = new WeakReference(surfaceRenderView);
        }

        public void addRenderCallback(@NonNull IRenderCallback iRenderCallback) {
            this.mRenderCallbackMap.put(iRenderCallback, iRenderCallback);
            ISurfaceHolder iSurfaceHolder = null;
            if (this.mSurfaceHolder != null) {
                if (null == null) {
                    iSurfaceHolder = new InternalSurfaceHolder((SurfaceRenderView) this.mWeakSurfaceView.get(), this.mSurfaceHolder);
                }
                iRenderCallback.onSurfaceCreated(iSurfaceHolder, this.mWidth, this.mHeight);
            }
            if (this.mIsFormatChanged) {
                if (iSurfaceHolder == null) {
                    iSurfaceHolder = new InternalSurfaceHolder((SurfaceRenderView) this.mWeakSurfaceView.get(), this.mSurfaceHolder);
                }
                iRenderCallback.onSurfaceChanged(iSurfaceHolder, this.mFormat, this.mWidth, this.mHeight);
            }
        }

        public void removeRenderCallback(@NonNull IRenderCallback iRenderCallback) {
            this.mRenderCallbackMap.remove(iRenderCallback);
        }

        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            this.mSurfaceHolder = surfaceHolder;
            this.mIsFormatChanged = true;
            this.mFormat = i;
            this.mWidth = i2;
            this.mHeight = i3;
            ISurfaceHolder internalSurfaceHolder = new InternalSurfaceHolder((SurfaceRenderView) this.mWeakSurfaceView.get(), this.mSurfaceHolder);
            for (IRenderCallback onSurfaceChanged : this.mRenderCallbackMap.keySet()) {
                onSurfaceChanged.onSurfaceChanged(internalSurfaceHolder, i, i2, i3);
            }
        }

        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            this.mSurfaceHolder = surfaceHolder;
            this.mIsFormatChanged = false;
            this.mFormat = 0;
            this.mWidth = 0;
            this.mHeight = 0;
            ISurfaceHolder internalSurfaceHolder = new InternalSurfaceHolder((SurfaceRenderView) this.mWeakSurfaceView.get(), this.mSurfaceHolder);
            for (IRenderCallback onSurfaceCreated : this.mRenderCallbackMap.keySet()) {
                onSurfaceCreated.onSurfaceCreated(internalSurfaceHolder, 0, 0);
            }
        }

        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            this.mSurfaceHolder = null;
            this.mIsFormatChanged = false;
            this.mFormat = 0;
            this.mWidth = 0;
            this.mHeight = 0;
            ISurfaceHolder internalSurfaceHolder = new InternalSurfaceHolder((SurfaceRenderView) this.mWeakSurfaceView.get(), this.mSurfaceHolder);
            for (IRenderCallback onSurfaceDestroyed : this.mRenderCallbackMap.keySet()) {
                onSurfaceDestroyed.onSurfaceDestroyed(internalSurfaceHolder);
            }
        }
    }

    public SurfaceRenderView(Context context) {
        super(context);
        initView(context);
    }

    public SurfaceRenderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    public SurfaceRenderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView(context);
    }

    public SurfaceRenderView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i);
        initView(context);
    }

    private void initView(Context context) {
        this.mMeasureHelper = new MeasureHelper(this);
        this.mSurfaceCallback = new SurfaceCallback(this);
        getHolder().addCallback(this.mSurfaceCallback);
        getHolder().setType(0);
    }

    public void addRenderCallback(IRenderCallback iRenderCallback) {
        this.mSurfaceCallback.addRenderCallback(iRenderCallback);
    }

    public View getView() {
        return this;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(SurfaceRenderView.class.getName());
    }

    @TargetApi(14)
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        if (VERSION.SDK_INT >= 14) {
            accessibilityNodeInfo.setClassName(SurfaceRenderView.class.getName());
        }
    }

    protected void onMeasure(int i, int i2) {
        this.mMeasureHelper.doMeasure(i, i2);
        setMeasuredDimension(this.mMeasureHelper.getMeasuredWidth(), this.mMeasureHelper.getMeasuredHeight());
    }

    public void removeRenderCallback(IRenderCallback iRenderCallback) {
        this.mSurfaceCallback.removeRenderCallback(iRenderCallback);
    }

    public void setAspectRatio(int i) {
        this.mMeasureHelper.setAspectRatio(i);
        requestLayout();
    }

    public void setVideoRotation(int i) {
        Log.e(C2915a.f14760f, "SurfaceView doesn't support rotation (" + i + ")!\n");
    }

    public void setVideoSampleAspectRatio(int i, int i2) {
        if (i > 0 && i2 > 0) {
            this.mMeasureHelper.setVideoSampleAspectRatio(i, i2);
            requestLayout();
        }
    }

    public void setVideoSize(int i, int i2) {
        if (i > 0 && i2 > 0) {
            this.mMeasureHelper.setVideoSize(i, i2);
            getHolder().setFixedSize(i, i2);
            requestLayout();
        }
    }

    public boolean shouldWaitForResize() {
        return true;
    }
}
