package com.fimi.soul.media.player.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.fimi.soul.media.player.IMediaPlayer;
import com.fimi.soul.media.player.ISurfaceTextureHolder;
import com.fimi.soul.media.player.widget.IRenderView.IRenderCallback;
import com.fimi.soul.media.player.widget.IRenderView.ISurfaceHolder;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@TargetApi(14)
public class TextureRenderView extends TextureView implements IRenderView {
    private MeasureHelper mMeasureHelper;
    private SurfaceCallback mSurfaceCallback;

    final class InternalSurfaceHolder implements ISurfaceHolder {
        private SurfaceTexture mSurfaceTexture;
        private TextureRenderView mTextureView;

        public InternalSurfaceHolder(@NonNull TextureRenderView textureRenderView, @Nullable SurfaceTexture surfaceTexture) {
            this.mTextureView = textureRenderView;
            this.mSurfaceTexture = surfaceTexture;
        }

        @TargetApi(16)
        public void bindToMediaPlayer(IMediaPlayer iMediaPlayer) {
            if (iMediaPlayer != null) {
                if (VERSION.SDK_INT < 16 || !(iMediaPlayer instanceof ISurfaceTextureHolder)) {
                    iMediaPlayer.setSurface(openSurface());
                    return;
                }
                ISurfaceTextureHolder iSurfaceTextureHolder = (ISurfaceTextureHolder) iMediaPlayer;
                this.mTextureView.mSurfaceCallback.setOwnSurfaceTecture(false);
                SurfaceTexture surfaceTexture = iSurfaceTextureHolder.getSurfaceTexture();
                if (surfaceTexture != null) {
                    this.mTextureView.setSurfaceTexture(surfaceTexture);
                } else {
                    iSurfaceTextureHolder.setSurfaceTexture(this.mSurfaceTexture);
                }
            }
        }

        @NonNull
        public IRenderView getRenderView() {
            return this.mTextureView;
        }

        @Nullable
        public SurfaceHolder getSurfaceHolder() {
            return null;
        }

        @Nullable
        public SurfaceTexture getSurfaceTexture() {
            return this.mSurfaceTexture;
        }

        @Nullable
        public Surface openSurface() {
            return this.mSurfaceTexture == null ? null : new Surface(this.mSurfaceTexture);
        }
    }

    final class SurfaceCallback implements SurfaceTextureListener {
        private int mHeight;
        private boolean mIsFormatChanged;
        private boolean mOwnSurfaceTecture;
        private Map<IRenderCallback, Object> mRenderCallbackMap;
        private SurfaceTexture mSurfaceTexture;
        private WeakReference<TextureRenderView> mWeakRenderView;
        private int mWidth;

        public SurfaceCallback(@NonNull TextureRenderView textureRenderView) {
            this.mOwnSurfaceTecture = true;
            this.mRenderCallbackMap = new ConcurrentHashMap();
            this.mWeakRenderView = new WeakReference(textureRenderView);
        }

        public void addRenderCallback(@NonNull IRenderCallback iRenderCallback) {
            this.mRenderCallbackMap.put(iRenderCallback, iRenderCallback);
            ISurfaceHolder iSurfaceHolder = null;
            if (this.mSurfaceTexture != null) {
                if (null == null) {
                    iSurfaceHolder = new InternalSurfaceHolder((TextureRenderView) this.mWeakRenderView.get(), this.mSurfaceTexture);
                }
                iRenderCallback.onSurfaceCreated(iSurfaceHolder, this.mWidth, this.mHeight);
            }
            if (this.mIsFormatChanged) {
                if (iSurfaceHolder == null) {
                    iSurfaceHolder = new InternalSurfaceHolder((TextureRenderView) this.mWeakRenderView.get(), this.mSurfaceTexture);
                }
                iRenderCallback.onSurfaceChanged(iSurfaceHolder, 0, this.mWidth, this.mHeight);
            }
        }

        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
            this.mSurfaceTexture = surfaceTexture;
            this.mIsFormatChanged = false;
            this.mWidth = 0;
            this.mHeight = 0;
            ISurfaceHolder internalSurfaceHolder = new InternalSurfaceHolder((TextureRenderView) this.mWeakRenderView.get(), surfaceTexture);
            for (IRenderCallback onSurfaceCreated : this.mRenderCallbackMap.keySet()) {
                onSurfaceCreated.onSurfaceCreated(internalSurfaceHolder, 0, 0);
            }
        }

        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            this.mSurfaceTexture = surfaceTexture;
            this.mIsFormatChanged = false;
            this.mWidth = 0;
            this.mHeight = 0;
            ISurfaceHolder internalSurfaceHolder = new InternalSurfaceHolder((TextureRenderView) this.mWeakRenderView.get(), surfaceTexture);
            for (IRenderCallback onSurfaceDestroyed : this.mRenderCallbackMap.keySet()) {
                onSurfaceDestroyed.onSurfaceDestroyed(internalSurfaceHolder);
            }
            return this.mOwnSurfaceTecture;
        }

        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
            this.mSurfaceTexture = surfaceTexture;
            this.mIsFormatChanged = true;
            this.mWidth = i;
            this.mHeight = i2;
            ISurfaceHolder internalSurfaceHolder = new InternalSurfaceHolder((TextureRenderView) this.mWeakRenderView.get(), surfaceTexture);
            for (IRenderCallback onSurfaceChanged : this.mRenderCallbackMap.keySet()) {
                onSurfaceChanged.onSurfaceChanged(internalSurfaceHolder, 0, i, i2);
            }
        }

        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }

        public void removeRenderCallback(@NonNull IRenderCallback iRenderCallback) {
            this.mRenderCallbackMap.remove(iRenderCallback);
        }

        public void setOwnSurfaceTecture(boolean z) {
            this.mOwnSurfaceTecture = z;
        }
    }

    public TextureRenderView(Context context) {
        super(context);
        initView(context);
    }

    public TextureRenderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    public TextureRenderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView(context);
    }

    public TextureRenderView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i);
        initView(context);
    }

    private void initView(Context context) {
        this.mMeasureHelper = new MeasureHelper(this);
        this.mSurfaceCallback = new SurfaceCallback(this);
        setSurfaceTextureListener(this.mSurfaceCallback);
    }

    public void addRenderCallback(IRenderCallback iRenderCallback) {
        this.mSurfaceCallback.addRenderCallback(iRenderCallback);
    }

    public ISurfaceHolder getSurfaceHolder() {
        return new InternalSurfaceHolder(this, this.mSurfaceCallback.mSurfaceTexture);
    }

    public View getView() {
        return this;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(TextureRenderView.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(TextureRenderView.class.getName());
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
        this.mMeasureHelper.setVideoRotation(i);
        setRotation((float) i);
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
            requestLayout();
        }
    }

    public boolean shouldWaitForResize() {
        return false;
    }
}
