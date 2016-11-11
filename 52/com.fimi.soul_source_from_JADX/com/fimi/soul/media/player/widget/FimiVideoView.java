package com.fimi.soul.media.player.widget;

import android.content.Context;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.fimi.soul.media.player.FimiMediaMeta;
import com.fimi.soul.media.player.FimiMediaPlayer;
import com.fimi.soul.media.player.IMediaPlayer;
import com.fimi.soul.media.player.IMediaPlayer.OnBufferingUpdateListener;
import com.fimi.soul.media.player.IMediaPlayer.OnCompletionListener;
import com.fimi.soul.media.player.IMediaPlayer.OnErrorListener;
import com.fimi.soul.media.player.IMediaPlayer.OnInfoListener;
import com.fimi.soul.media.player.IMediaPlayer.OnPreparedListener;
import com.fimi.soul.media.player.IMediaPlayer.OnVideoSizeChangedListener;
import com.fimi.soul.media.player.widget.FmMediaController.MediaPlayerControl;
import com.fimi.soul.media.player.widget.IRenderView.IRenderCallback;
import com.fimi.soul.media.player.widget.IRenderView.ISurfaceHolder;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

public class FimiVideoView extends FrameLayout implements MediaPlayerControl {
    public static final int RENDER_NONE = 0;
    public static final int RENDER_SURFACE_VIEW = 1;
    public static final int RENDER_TEXTURE_VIEW = 2;
    private static final int STATE_ERROR = -1;
    private static final int STATE_IDLE = 0;
    private static final int STATE_PAUSED = 4;
    private static final int STATE_PLAYBACK_COMPLETED = 5;
    private static final int STATE_PLAYING = 3;
    private static final int STATE_PREPARED = 2;
    private static final int STATE_PREPARING = 1;
    private static final int[] s_allAspectRatio;
    private String TAG;
    private int decodeType;
    private List<Integer> mAllRenders;
    private Context mAppContext;
    private OnBufferingUpdateListener mBufferingUpdateListener;
    private boolean mCanPause;
    private boolean mCanSeekBack;
    private boolean mCanSeekForward;
    private OnCompletionListener mCompletionListener;
    private int mCurrentAspectRatio;
    private int mCurrentAspectRatioIndex;
    private int mCurrentBufferPercentage;
    private int mCurrentRender;
    private int mCurrentRenderIndex;
    private int mCurrentState;
    private OnErrorListener mErrorListener;
    private Map<String, String> mHeaders;
    private OnInfoListener mInfoListener;
    private IMediaController mMediaController;
    private IMediaPlayer mMediaPlayer;
    private OnCompletionListener mOnCompletionListener;
    private OnErrorListener mOnErrorListener;
    private OnInfoListener mOnInfoListener;
    private OnPreparedListener mOnPreparedListener;
    OnPreparedListener mPreparedListener;
    private IRenderView mRenderView;
    IRenderCallback mSHCallback;
    private int mSeekWhenPrepared;
    OnVideoSizeChangedListener mSizeChangedListener;
    private int mSurfaceHeight;
    private ISurfaceHolder mSurfaceHolder;
    private int mSurfaceWidth;
    private int mTargetState;
    private Uri mUri;
    private int mVideoHeight;
    private int mVideoRotationDegree;
    private int mVideoSarDen;
    private int mVideoSarNum;
    private int mVideoWidth;

    /* renamed from: com.fimi.soul.media.player.widget.FimiVideoView.1 */
    class C16441 implements OnVideoSizeChangedListener {
        C16441() {
        }

        public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i2, int i3, int i4) {
            FimiVideoView.this.mVideoWidth = iMediaPlayer.getVideoWidth();
            FimiVideoView.this.mVideoHeight = iMediaPlayer.getVideoHeight();
            FimiVideoView.this.mVideoSarNum = iMediaPlayer.getVideoSarNum();
            FimiVideoView.this.mVideoSarDen = iMediaPlayer.getVideoSarDen();
            if (FimiVideoView.this.mVideoWidth != 0 && FimiVideoView.this.mVideoHeight != 0) {
                if (FimiVideoView.this.mRenderView != null) {
                    FimiVideoView.this.mRenderView.setVideoSize(FimiVideoView.this.mVideoWidth, FimiVideoView.this.mVideoHeight);
                    FimiVideoView.this.mRenderView.setVideoSampleAspectRatio(FimiVideoView.this.mVideoSarNum, FimiVideoView.this.mVideoSarDen);
                }
                FimiVideoView.this.requestLayout();
            }
        }
    }

    /* renamed from: com.fimi.soul.media.player.widget.FimiVideoView.2 */
    class C16452 implements OnPreparedListener {
        C16452() {
        }

        public void onPrepared(IMediaPlayer iMediaPlayer) {
            FimiVideoView.this.mCurrentState = FimiVideoView.STATE_PREPARED;
            if (FimiVideoView.this.mOnPreparedListener != null) {
                FimiVideoView.this.mOnPreparedListener.onPrepared(FimiVideoView.this.mMediaPlayer);
            }
            if (FimiVideoView.this.mMediaController != null) {
                FimiVideoView.this.mMediaController.setEnabled(true);
            }
            FimiVideoView.this.mVideoWidth = iMediaPlayer.getVideoWidth();
            FimiVideoView.this.mVideoHeight = iMediaPlayer.getVideoHeight();
            int access$900 = FimiVideoView.this.mSeekWhenPrepared;
            if (access$900 != 0) {
                FimiVideoView.this.seekTo(access$900);
            }
            if (FimiVideoView.this.mVideoWidth == 0 || FimiVideoView.this.mVideoHeight == 0) {
                if (FimiVideoView.this.mTargetState == FimiVideoView.STATE_PLAYING) {
                    FimiVideoView.this.start();
                }
            } else if (FimiVideoView.this.mRenderView != null) {
                FimiVideoView.this.mRenderView.setVideoSize(FimiVideoView.this.mVideoWidth, FimiVideoView.this.mVideoHeight);
                FimiVideoView.this.mRenderView.setVideoSampleAspectRatio(FimiVideoView.this.mVideoSarNum, FimiVideoView.this.mVideoSarDen);
                if (FimiVideoView.this.mRenderView.shouldWaitForResize() && (FimiVideoView.this.mSurfaceWidth != FimiVideoView.this.mVideoWidth || FimiVideoView.this.mSurfaceHeight != FimiVideoView.this.mVideoHeight)) {
                    return;
                }
                if (FimiVideoView.this.mTargetState == FimiVideoView.STATE_PLAYING) {
                    FimiVideoView.this.start();
                    if (FimiVideoView.this.mMediaController != null) {
                        FimiVideoView.this.mMediaController.show();
                    }
                } else if (!FimiVideoView.this.isPlaying()) {
                    if ((access$900 != 0 || FimiVideoView.this.getCurrentPosition() > 0) && FimiVideoView.this.mMediaController != null) {
                        FimiVideoView.this.mMediaController.show(FimiVideoView.STATE_IDLE);
                    }
                }
            }
        }
    }

    /* renamed from: com.fimi.soul.media.player.widget.FimiVideoView.3 */
    class C16463 implements OnCompletionListener {
        C16463() {
        }

        public void onCompletion(IMediaPlayer iMediaPlayer) {
            FimiVideoView.this.mCurrentState = FimiVideoView.STATE_PLAYBACK_COMPLETED;
            FimiVideoView.this.mTargetState = FimiVideoView.STATE_PLAYBACK_COMPLETED;
            if (FimiVideoView.this.mMediaController != null) {
                FimiVideoView.this.mMediaController.hide();
            }
            if (FimiVideoView.this.mOnCompletionListener != null) {
                FimiVideoView.this.mOnCompletionListener.onCompletion(FimiVideoView.this.mMediaPlayer);
            }
        }
    }

    /* renamed from: com.fimi.soul.media.player.widget.FimiVideoView.4 */
    class C16474 implements OnInfoListener {
        C16474() {
        }

        public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i2) {
            if (FimiVideoView.this.mOnInfoListener != null) {
                FimiVideoView.this.mOnInfoListener.onInfo(iMediaPlayer, i, i2);
            }
            switch (i) {
                case IMediaPlayer.MEDIA_INFO_VIDEO_ROTATION_CHANGED /*10001*/:
                    FimiVideoView.this.mVideoRotationDegree = i2;
                    Log.d(FimiVideoView.this.TAG, "MEDIA_INFO_VIDEO_ROTATION_CHANGED: " + i2);
                    if (FimiVideoView.this.mRenderView != null) {
                        FimiVideoView.this.mRenderView.setVideoRotation(i2);
                        break;
                    }
                    break;
            }
            return true;
        }
    }

    /* renamed from: com.fimi.soul.media.player.widget.FimiVideoView.5 */
    class C16485 implements OnErrorListener {
        C16485() {
        }

        public boolean onError(IMediaPlayer iMediaPlayer, int i, int i2) {
            Log.d(FimiVideoView.this.TAG, "Error: " + i + MiPushClient.ACCEPT_TIME_SEPARATOR + i2);
            FimiVideoView.this.mCurrentState = FimiVideoView.STATE_ERROR;
            FimiVideoView.this.mTargetState = FimiVideoView.STATE_ERROR;
            if (FimiVideoView.this.mMediaController != null) {
                FimiVideoView.this.mMediaController.hide();
            }
            return FimiVideoView.this.mOnErrorListener != null && FimiVideoView.this.mOnErrorListener.onError(FimiVideoView.this.mMediaPlayer, i, i2);
        }
    }

    /* renamed from: com.fimi.soul.media.player.widget.FimiVideoView.6 */
    class C16496 implements OnBufferingUpdateListener {
        C16496() {
        }

        public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
            FimiVideoView.this.mCurrentBufferPercentage = i;
        }
    }

    /* renamed from: com.fimi.soul.media.player.widget.FimiVideoView.7 */
    class C16507 implements IRenderCallback {
        C16507() {
        }

        public void onSurfaceChanged(@NonNull ISurfaceHolder iSurfaceHolder, int i, int i2, int i3) {
            Object obj = null;
            if (iSurfaceHolder.getRenderView() != FimiVideoView.this.mRenderView) {
                Log.e(FimiVideoView.this.TAG, "onSurfaceChanged: unmatched render callback\n");
                return;
            }
            FimiVideoView.this.mSurfaceWidth = i2;
            FimiVideoView.this.mSurfaceHeight = i3;
            Object obj2 = FimiVideoView.this.mTargetState == FimiVideoView.STATE_PLAYING ? FimiVideoView.STATE_PREPARING : FimiVideoView.STATE_IDLE;
            if (!FimiVideoView.this.mRenderView.shouldWaitForResize() || (FimiVideoView.this.mVideoWidth == i2 && FimiVideoView.this.mVideoHeight == i3)) {
                obj = FimiVideoView.STATE_PREPARING;
            }
            if (FimiVideoView.this.mMediaPlayer != null && obj2 != null && r2 != null) {
                if (FimiVideoView.this.mSeekWhenPrepared != 0) {
                    FimiVideoView.this.seekTo(FimiVideoView.this.mSeekWhenPrepared);
                }
                FimiVideoView.this.start();
            }
        }

        public void onSurfaceCreated(@NonNull ISurfaceHolder iSurfaceHolder, int i, int i2) {
            if (iSurfaceHolder.getRenderView() != FimiVideoView.this.mRenderView) {
                Log.e(FimiVideoView.this.TAG, "onSurfaceCreated: unmatched render callback\n");
                return;
            }
            FimiVideoView.this.mSurfaceHolder = iSurfaceHolder;
            if (FimiVideoView.this.mMediaPlayer != null) {
                FimiVideoView.this.bindSurfaceHolder(FimiVideoView.this.mMediaPlayer, iSurfaceHolder);
            } else {
                FimiVideoView.this.openVideo(FimiVideoView.this.decodeType);
            }
        }

        public void onSurfaceDestroyed(@NonNull ISurfaceHolder iSurfaceHolder) {
            if (iSurfaceHolder.getRenderView() != FimiVideoView.this.mRenderView) {
                Log.e(FimiVideoView.this.TAG, "onSurfaceDestroyed: unmatched render callback\n");
                return;
            }
            FimiVideoView.this.mSurfaceHolder = null;
            FimiVideoView.this.releaseWithoutStop();
        }
    }

    static {
        s_allAspectRatio = new int[]{STATE_IDLE, STATE_PREPARING, STATE_PREPARED, STATE_PLAYING, STATE_PAUSED, STATE_PLAYBACK_COMPLETED};
    }

    public FimiVideoView(Context context) {
        super(context);
        this.TAG = "IjkVideoView";
        this.mCurrentState = STATE_IDLE;
        this.mTargetState = STATE_IDLE;
        this.mSurfaceHolder = null;
        this.mMediaPlayer = null;
        this.mCanPause = true;
        this.decodeType = STATE_IDLE;
        this.mSizeChangedListener = new C16441();
        this.mPreparedListener = new C16452();
        this.mCompletionListener = new C16463();
        this.mInfoListener = new C16474();
        this.mErrorListener = new C16485();
        this.mBufferingUpdateListener = new C16496();
        this.mSHCallback = new C16507();
        this.mCurrentAspectRatioIndex = STATE_IDLE;
        this.mCurrentAspectRatio = s_allAspectRatio[STATE_IDLE];
        this.mAllRenders = new ArrayList();
        this.mCurrentRenderIndex = STATE_IDLE;
        this.mCurrentRender = STATE_IDLE;
        initVideoView(context);
    }

    public FimiVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = "IjkVideoView";
        this.mCurrentState = STATE_IDLE;
        this.mTargetState = STATE_IDLE;
        this.mSurfaceHolder = null;
        this.mMediaPlayer = null;
        this.mCanPause = true;
        this.decodeType = STATE_IDLE;
        this.mSizeChangedListener = new C16441();
        this.mPreparedListener = new C16452();
        this.mCompletionListener = new C16463();
        this.mInfoListener = new C16474();
        this.mErrorListener = new C16485();
        this.mBufferingUpdateListener = new C16496();
        this.mSHCallback = new C16507();
        this.mCurrentAspectRatioIndex = STATE_IDLE;
        this.mCurrentAspectRatio = s_allAspectRatio[STATE_IDLE];
        this.mAllRenders = new ArrayList();
        this.mCurrentRenderIndex = STATE_IDLE;
        this.mCurrentRender = STATE_IDLE;
        initVideoView(context);
    }

    public FimiVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.TAG = "IjkVideoView";
        this.mCurrentState = STATE_IDLE;
        this.mTargetState = STATE_IDLE;
        this.mSurfaceHolder = null;
        this.mMediaPlayer = null;
        this.mCanPause = true;
        this.decodeType = STATE_IDLE;
        this.mSizeChangedListener = new C16441();
        this.mPreparedListener = new C16452();
        this.mCompletionListener = new C16463();
        this.mInfoListener = new C16474();
        this.mErrorListener = new C16485();
        this.mBufferingUpdateListener = new C16496();
        this.mSHCallback = new C16507();
        this.mCurrentAspectRatioIndex = STATE_IDLE;
        this.mCurrentAspectRatio = s_allAspectRatio[STATE_IDLE];
        this.mAllRenders = new ArrayList();
        this.mCurrentRenderIndex = STATE_IDLE;
        this.mCurrentRender = STATE_IDLE;
        initVideoView(context);
    }

    public FimiVideoView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i);
        this.TAG = "IjkVideoView";
        this.mCurrentState = STATE_IDLE;
        this.mTargetState = STATE_IDLE;
        this.mSurfaceHolder = null;
        this.mMediaPlayer = null;
        this.mCanPause = true;
        this.decodeType = STATE_IDLE;
        this.mSizeChangedListener = new C16441();
        this.mPreparedListener = new C16452();
        this.mCompletionListener = new C16463();
        this.mInfoListener = new C16474();
        this.mErrorListener = new C16485();
        this.mBufferingUpdateListener = new C16496();
        this.mSHCallback = new C16507();
        this.mCurrentAspectRatioIndex = STATE_IDLE;
        this.mCurrentAspectRatio = s_allAspectRatio[STATE_IDLE];
        this.mAllRenders = new ArrayList();
        this.mCurrentRenderIndex = STATE_IDLE;
        this.mCurrentRender = STATE_IDLE;
        initVideoView(context);
    }

    private void attachMediaController() {
        if (this.mMediaPlayer != null && this.mMediaController != null) {
            this.mMediaController.setMediaPlayer(this);
            this.mMediaController.setAnchorView(getParent() instanceof View ? (View) getParent() : this);
            this.mMediaController.setEnabled(isInPlaybackState());
        }
    }

    private void bindSurfaceHolder(IMediaPlayer iMediaPlayer, ISurfaceHolder iSurfaceHolder) {
        if (iMediaPlayer != null) {
            if (iSurfaceHolder == null) {
                iMediaPlayer.setDisplay(null);
            } else {
                iSurfaceHolder.bindToMediaPlayer(iMediaPlayer);
            }
        }
    }

    private void initRenders() {
        this.mAllRenders.clear();
        this.mAllRenders.add(Integer.valueOf(STATE_PREPARING));
        if (this.mAllRenders.isEmpty()) {
            this.mAllRenders.add(Integer.valueOf(STATE_PREPARING));
        }
        this.mCurrentRender = ((Integer) this.mAllRenders.get(this.mCurrentRenderIndex)).intValue();
        setRender(this.mCurrentRender);
    }

    private void initVideoView(Context context) {
        this.mAppContext = context.getApplicationContext();
        initRenders();
        this.mVideoWidth = STATE_IDLE;
        this.mVideoHeight = STATE_IDLE;
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        this.mCurrentState = STATE_IDLE;
        this.mTargetState = STATE_IDLE;
    }

    private boolean isInPlaybackState() {
        return (this.mMediaPlayer == null || this.mCurrentState == STATE_ERROR || this.mCurrentState == 0 || this.mCurrentState == STATE_PREPARING) ? false : true;
    }

    private void openVideo(int i) {
        if (this.mUri != null && this.mSurfaceHolder != null) {
            release(false);
            ((AudioManager) this.mAppContext.getSystemService(FimiMediaMeta.IJKM_VAL_TYPE__AUDIO)).requestAudioFocus(null, STATE_PLAYING, STATE_PREPARING);
            IMediaPlayer iMediaPlayer = null;
            try {
                if (this.mUri != null) {
                    iMediaPlayer = FimiMediaPlayer.getIntance();
                    FimiMediaPlayer.native_setLogLevel(STATE_PLAYING);
                    iMediaPlayer.setOption((int) STATE_PAUSED, "mediacodec", (long) i);
                    iMediaPlayer.setOption((int) STATE_PAUSED, "framedrop", 20);
                    iMediaPlayer.setOption((int) STATE_PAUSED, "start-on-prepared", 0);
                    iMediaPlayer.setOption((int) STATE_PREPARING, "http-detect-range-support", 0);
                    iMediaPlayer.setOption((int) STATE_PREPARED, "skip_loop_filter", 48);
                    iMediaPlayer.setOption((int) STATE_PREPARING, "analyzeduration", "20000");
                    iMediaPlayer.setOption((int) STATE_PREPARING, "probsize", "4096");
                }
                this.mMediaPlayer = iMediaPlayer;
                getContext();
                this.mMediaPlayer.setOnPreparedListener(this.mPreparedListener);
                this.mMediaPlayer.setOnVideoSizeChangedListener(this.mSizeChangedListener);
                this.mMediaPlayer.setOnCompletionListener(this.mCompletionListener);
                this.mMediaPlayer.setOnErrorListener(this.mErrorListener);
                this.mMediaPlayer.setOnInfoListener(this.mInfoListener);
                this.mMediaPlayer.setOnBufferingUpdateListener(this.mBufferingUpdateListener);
                this.mCurrentBufferPercentage = STATE_IDLE;
                if (VERSION.SDK_INT > 14) {
                    this.mMediaPlayer.setDataSource(this.mAppContext, this.mUri, this.mHeaders);
                } else {
                    this.mMediaPlayer.setDataSource(this.mUri.toString());
                }
                bindSurfaceHolder(this.mMediaPlayer, this.mSurfaceHolder);
                this.mMediaPlayer.setAudioStreamType(STATE_PLAYING);
                this.mMediaPlayer.setScreenOnWhilePlaying(true);
                this.mMediaPlayer.prepareAsync();
                this.mCurrentState = STATE_PREPARING;
                attachMediaController();
            } catch (Throwable e) {
                Log.w(this.TAG, "Unable to open content: " + this.mUri, e);
                this.mCurrentState = STATE_ERROR;
                this.mTargetState = STATE_ERROR;
                this.mErrorListener.onError(this.mMediaPlayer, STATE_PREPARING, STATE_IDLE);
            } catch (Throwable e2) {
                Log.w(this.TAG, "Unable to open content: " + this.mUri, e2);
                this.mCurrentState = STATE_ERROR;
                this.mTargetState = STATE_ERROR;
                this.mErrorListener.onError(this.mMediaPlayer, STATE_PREPARING, STATE_IDLE);
            }
        }
    }

    private void setVideoURI(Uri uri, Map<String, String> map) {
        this.mUri = uri;
        this.mHeaders = map;
        this.mSeekWhenPrepared = STATE_IDLE;
        openVideo(this.decodeType);
        requestLayout();
        invalidate();
    }

    private void toggleMediaControlsVisiblity() {
        if (this.mMediaController.isShowing()) {
            this.mMediaController.hide();
        } else {
            this.mMediaController.show();
        }
    }

    public boolean canPause() {
        return this.mCanPause;
    }

    public boolean canSeekBackward() {
        return this.mCanSeekBack;
    }

    public boolean canSeekForward() {
        return this.mCanSeekForward;
    }

    public int getAudioSessionId() {
        return STATE_IDLE;
    }

    public int getBufferPercentage() {
        return this.mMediaPlayer != null ? this.mCurrentBufferPercentage : STATE_IDLE;
    }

    public int getCurrentPosition() {
        return isInPlaybackState() ? (int) this.mMediaPlayer.getCurrentPosition() : STATE_IDLE;
    }

    public int getDuration() {
        Log.d("player", "getDuration");
        return isInPlaybackState() ? (int) this.mMediaPlayer.getDuration() : STATE_ERROR;
    }

    public int getmCurrentState() {
        return this.mCurrentState;
    }

    public boolean isPlaying() {
        return isInPlaybackState() && this.mMediaPlayer.isPlaying();
    }

    public void onFinishInflate() {
        Log.d("player", "onFinishInflate");
        super.onFinishInflate();
        if (isPlaying()) {
            pause();
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        boolean z = (i == STATE_PAUSED || i == 24 || i == 25 || i == Opcodes.IF_ICMPLE || i == 82 || i == STATE_PLAYBACK_COMPLETED || i == 6) ? false : true;
        if (isInPlaybackState() && z && this.mMediaController != null) {
            if (i == 79 || i == 85) {
                if (this.mMediaPlayer.isPlaying()) {
                    pause();
                    this.mMediaController.show();
                    return true;
                }
                start();
                this.mMediaController.hide();
                return true;
            } else if (i == Opcodes.IAND) {
                if (this.mMediaPlayer.isPlaying()) {
                    return true;
                }
                start();
                this.mMediaController.hide();
                return true;
            } else if (i != 86 && i != Opcodes.LAND) {
                toggleMediaControlsVisiblity();
            } else if (!this.mMediaPlayer.isPlaying()) {
                return true;
            } else {
                pause();
                this.mMediaController.show();
                return true;
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (isInPlaybackState() && this.mMediaController != null) {
            toggleMediaControlsVisiblity();
        }
        return false;
    }

    public boolean onTrackballEvent(MotionEvent motionEvent) {
        if (isInPlaybackState() && this.mMediaController != null) {
            toggleMediaControlsVisiblity();
        }
        return false;
    }

    public void pause() {
        Log.d("player", "pause");
        if (this.mMediaPlayer != null) {
            this.mMediaPlayer.pause();
            if (isInPlaybackState() && this.mMediaPlayer.isPlaying()) {
                this.mMediaPlayer.pause();
                this.mCurrentState = STATE_PAUSED;
            }
            this.mTargetState = STATE_PAUSED;
        }
    }

    public void release(boolean z) {
        if (this.mMediaPlayer != null) {
            this.mMediaPlayer.reset();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
            this.mCurrentState = STATE_IDLE;
            if (z) {
                this.mTargetState = STATE_IDLE;
            }
            ((AudioManager) this.mAppContext.getSystemService(FimiMediaMeta.IJKM_VAL_TYPE__AUDIO)).abandonAudioFocus(null);
        }
    }

    public void releaseWithoutStop() {
        if (this.mMediaPlayer != null) {
            this.mMediaPlayer.setDisplay(null);
        }
    }

    public void resume() {
        Log.d("Good", "resume");
        openVideo(this.decodeType);
    }

    public void seekTo(int i) {
        Log.d("player", "seekto");
        if (isInPlaybackState()) {
            this.mMediaPlayer.seekTo((long) i);
            this.mSeekWhenPrepared = STATE_IDLE;
            return;
        }
        this.mSeekWhenPrepared = i;
    }

    public void setDecodeType(int i) {
        this.decodeType = i;
    }

    public void setHideSurfaceView(boolean z) {
        if (this.mRenderView instanceof SurfaceRenderView) {
            ((SurfaceRenderView) this.mRenderView).getHolder().setFormat(z ? -2 : STATE_PAUSED);
        }
    }

    public void setMediaController(IMediaController iMediaController) {
        if (this.mMediaController != null) {
            this.mMediaController.hide();
        }
        this.mMediaController = iMediaController;
        attachMediaController();
    }

    public void setOnCompletionListener(OnCompletionListener onCompletionListener) {
        this.mOnCompletionListener = onCompletionListener;
    }

    public void setOnErrorListener(OnErrorListener onErrorListener) {
        this.mOnErrorListener = onErrorListener;
    }

    public void setOnInfoListener(OnInfoListener onInfoListener) {
        this.mOnInfoListener = onInfoListener;
    }

    public void setOnPreparedListener(OnPreparedListener onPreparedListener) {
        this.mOnPreparedListener = onPreparedListener;
    }

    public void setRender(int i) {
        switch (i) {
            case STATE_IDLE /*0*/:
                setRenderView(null);
            case STATE_PREPARING /*1*/:
                setRenderView(new SurfaceRenderView(getContext()));
            case STATE_PREPARED /*2*/:
                IRenderView textureRenderView = new TextureRenderView(getContext());
                if (this.mMediaPlayer != null) {
                    textureRenderView.getSurfaceHolder().bindToMediaPlayer(this.mMediaPlayer);
                    textureRenderView.setVideoSize(this.mMediaPlayer.getVideoWidth(), this.mMediaPlayer.getVideoHeight());
                    textureRenderView.setVideoSampleAspectRatio(this.mMediaPlayer.getVideoSarNum(), this.mMediaPlayer.getVideoSarDen());
                    textureRenderView.setAspectRatio(this.mCurrentAspectRatio);
                }
                setRenderView(textureRenderView);
            default:
                String str = this.TAG;
                Object[] objArr = new Object[STATE_PREPARING];
                objArr[STATE_IDLE] = Integer.valueOf(i);
                Log.e(str, String.format(Locale.getDefault(), "invalid render %d\n", objArr));
        }
    }

    public void setRenderView(IRenderView iRenderView) {
        if (this.mRenderView != null) {
            if (this.mMediaPlayer != null) {
                this.mMediaPlayer.setDisplay(null);
            }
            View view = this.mRenderView.getView();
            this.mRenderView.removeRenderCallback(this.mSHCallback);
            this.mRenderView = null;
            removeView(view);
        }
        if (iRenderView != null) {
            this.mRenderView = iRenderView;
            iRenderView.setAspectRatio(this.mCurrentAspectRatio);
            if (this.mVideoWidth > 0 && this.mVideoHeight > 0) {
                iRenderView.setVideoSize(this.mVideoWidth, this.mVideoHeight);
            }
            if (this.mVideoSarNum > 0 && this.mVideoSarDen > 0) {
                iRenderView.setVideoSampleAspectRatio(this.mVideoSarNum, this.mVideoSarDen);
            }
            view = this.mRenderView.getView();
            view.setLayoutParams(new LayoutParams(-2, -2, 17));
            addView(view);
            this.mRenderView.addRenderCallback(this.mSHCallback);
            this.mRenderView.setVideoRotation(this.mVideoRotationDegree);
        }
    }

    public void setVideoPath(String str) {
        setVideoURI(Uri.parse(str));
    }

    public void setVideoURI(Uri uri) {
        setVideoURI(uri, null);
    }

    public void setZOrderMediaOverlay(boolean z) {
        if (this.mRenderView instanceof SurfaceRenderView) {
            ((SurfaceRenderView) this.mRenderView).setZOrderMediaOverlay(z);
        }
    }

    public void start() {
        if (this.mCurrentState == STATE_PLAYBACK_COMPLETED) {
            setVideoURI(this.mUri);
        }
        if (isInPlaybackState()) {
            this.mMediaPlayer.start();
            this.mCurrentState = STATE_PLAYING;
        }
        this.mTargetState = STATE_PLAYING;
    }

    public void stopPlayback() {
        if (this.mMediaPlayer != null) {
            this.mMediaPlayer.stop();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
            this.mCurrentState = STATE_IDLE;
            this.mTargetState = STATE_IDLE;
            ((AudioManager) this.mAppContext.getSystemService(FimiMediaMeta.IJKM_VAL_TYPE__AUDIO)).abandonAudioFocus(null);
        }
    }

    public void suspend() {
        Log.d("Good", "suspend");
        release(false);
    }

    public int toggleAspectRatio() {
        this.mCurrentAspectRatioIndex += STATE_PREPARING;
        this.mCurrentAspectRatioIndex %= s_allAspectRatio.length;
        this.mCurrentAspectRatio = STATE_PREPARING;
        if (this.mRenderView != null) {
            this.mRenderView.setAspectRatio(this.mCurrentAspectRatio);
        }
        return this.mCurrentAspectRatio;
    }

    public int toggleAspectRatioPOi() {
        this.mCurrentAspectRatioIndex += STATE_PREPARING;
        this.mCurrentAspectRatioIndex %= s_allAspectRatio.length;
        this.mCurrentAspectRatio = STATE_PLAYING;
        if (this.mRenderView != null) {
            this.mRenderView.setAspectRatio(this.mCurrentAspectRatio);
        }
        return this.mCurrentAspectRatio;
    }

    public int toggleRender() {
        this.mCurrentRenderIndex += STATE_PREPARING;
        this.mCurrentRenderIndex %= this.mAllRenders.size();
        this.mCurrentRender = ((Integer) this.mAllRenders.get(this.mCurrentRenderIndex)).intValue();
        setRender(this.mCurrentRender);
        return this.mCurrentRender;
    }
}
