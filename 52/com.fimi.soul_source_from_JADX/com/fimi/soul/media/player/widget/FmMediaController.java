package com.fimi.soul.media.player.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnLayoutChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.fimi.kernel.p084e.C1186y;
import com.fimi.soul.C1205R;
import com.fimi.soul.utils.be;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import java.lang.reflect.Method;
import java.util.Formatter;
import java.util.Locale;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.smile.SmileConstants;
import p147m.framework.ui.widget.asyncview.AsyncImageView;

public class FmMediaController extends FrameLayout {
    private static final int FADE_OUT = 1;
    private static final int SHOW_PROGRESS = 2;
    private static final int sDefaultTimeout = 3000;
    private static final int updatePause = 0;
    private View mAnchor;
    private Context mContext;
    private TextView mCurrentTime;
    private View mDecor;
    private LayoutParams mDecorLayoutParams;
    private boolean mDragging;
    private TextView mEndTime;
    private ImageButton mFfwdButton;
    private OnClickListener mFfwdListener;
    StringBuilder mFormatBuilder;
    Formatter mFormatter;
    private boolean mFromXml;
    private Handler mHandler;
    private OnLayoutChangeListener mLayoutChangeListener;
    private boolean mListenersSet;
    private ImageButton mNextButton;
    private OnClickListener mNextListener;
    private ImageButton mPauseButton;
    private OnClickListener mPauseListener;
    private MediaPlayerControl mPlayer;
    private ImageButton mPrevButton;
    private OnClickListener mPrevListener;
    private SeekBar mProgress;
    private ImageButton mRewButton;
    private OnClickListener mRewListener;
    private View mRoot;
    private OnSeekBarChangeListener mSeekListener;
    private boolean mShowing;
    private OnTouchListener mTouchListener;
    private boolean mUseFastForward;
    private Window mWindow;
    private WindowManager mWindowManager;

    public interface MediaPlayerControl {
        boolean canPause();

        boolean canSeekBackward();

        boolean canSeekForward();

        int getAudioSessionId();

        int getBufferPercentage();

        int getCurrentPosition();

        int getDuration();

        boolean isPlaying();

        void pause();

        void seekTo(int i);

        void start();
    }

    /* renamed from: com.fimi.soul.media.player.widget.FmMediaController.1 */
    class C16511 implements OnLayoutChangeListener {
        C16511() {
        }

        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            FmMediaController.this.updateFloatingWindowLayout();
            if (FmMediaController.this.mShowing) {
                FmMediaController.this.mWindowManager.updateViewLayout(FmMediaController.this.mDecor, FmMediaController.this.mDecorLayoutParams);
            }
        }
    }

    /* renamed from: com.fimi.soul.media.player.widget.FmMediaController.2 */
    class C16522 implements OnTouchListener {
        C16522() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0 && FmMediaController.this.mShowing) {
                FmMediaController.this.hide();
            }
            return false;
        }
    }

    /* renamed from: com.fimi.soul.media.player.widget.FmMediaController.3 */
    class C16533 extends Handler {
        C16533() {
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                    if (FmMediaController.this.mPlayer.isPlaying()) {
                        FmMediaController.this.mPauseButton.setBackgroundResource(C1205R.drawable.drone_stop);
                    } else {
                        FmMediaController.this.mPauseButton.setBackgroundResource(C1205R.drawable.drone_play);
                    }
                case FmMediaController.FADE_OUT /*1*/:
                    FmMediaController.this.hide();
                case FmMediaController.SHOW_PROGRESS /*2*/:
                    Log.d("player", "handle SHOW_PROGRESS");
                    int access$500 = FmMediaController.this.setProgress();
                    if (!FmMediaController.this.mDragging && FmMediaController.this.mShowing && FmMediaController.this.mPlayer.isPlaying()) {
                        sendMessageDelayed(obtainMessage(FmMediaController.SHOW_PROGRESS), (long) (1000 - (access$500 % XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER)));
                    }
                default:
            }
        }
    }

    /* renamed from: com.fimi.soul.media.player.widget.FmMediaController.4 */
    class C16544 implements OnClickListener {
        C16544() {
        }

        public void onClick(View view) {
            FmMediaController.this.doPauseResume();
            FmMediaController.this.show(FmMediaController.sDefaultTimeout);
        }
    }

    /* renamed from: com.fimi.soul.media.player.widget.FmMediaController.5 */
    class C16555 implements OnSeekBarChangeListener {
        C16555() {
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            Log.d("player", "onProgressChanged");
            if (z) {
                long duration = (((long) FmMediaController.this.mPlayer.getDuration()) * ((long) i)) / 1000;
                FmMediaController.this.mPlayer.seekTo((int) duration);
                if (FmMediaController.this.mCurrentTime != null) {
                    FmMediaController.this.mCurrentTime.setText(FmMediaController.this.stringForTime((int) duration));
                }
            }
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
            Log.d("player", "onStartTrackingTouch");
            FmMediaController.this.show(3600000);
            FmMediaController.this.mDragging = true;
            FmMediaController.this.mHandler.removeMessages(FmMediaController.SHOW_PROGRESS);
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            Log.d("player", "onStopTrackingTouch");
            FmMediaController.this.mDragging = false;
            FmMediaController.this.setProgress();
            FmMediaController.this.updatePausePlay();
            FmMediaController.this.show(FmMediaController.sDefaultTimeout);
            FmMediaController.this.mHandler.sendEmptyMessage(FmMediaController.SHOW_PROGRESS);
        }
    }

    /* renamed from: com.fimi.soul.media.player.widget.FmMediaController.6 */
    class C16566 implements OnClickListener {
        C16566() {
        }

        public void onClick(View view) {
            FmMediaController.this.mPlayer.seekTo(FmMediaController.this.mPlayer.getCurrentPosition() - 5000);
            FmMediaController.this.setProgress();
            FmMediaController.this.show(FmMediaController.sDefaultTimeout);
        }
    }

    /* renamed from: com.fimi.soul.media.player.widget.FmMediaController.7 */
    class C16577 implements OnClickListener {
        C16577() {
        }

        public void onClick(View view) {
            FmMediaController.this.mPlayer.seekTo(FmMediaController.this.mPlayer.getCurrentPosition() + 15000);
            FmMediaController.this.setProgress();
            FmMediaController.this.show(FmMediaController.sDefaultTimeout);
        }
    }

    public FmMediaController(Context context) {
        this(context, true);
    }

    public FmMediaController(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mLayoutChangeListener = new C16511();
        this.mTouchListener = new C16522();
        this.mHandler = new C16533();
        this.mPauseListener = new C16544();
        this.mSeekListener = new C16555();
        this.mRewListener = new C16566();
        this.mFfwdListener = new C16577();
        this.mRoot = this;
        this.mContext = context;
        this.mUseFastForward = true;
        this.mFromXml = true;
    }

    public FmMediaController(Context context, boolean z) {
        super(context);
        this.mLayoutChangeListener = new C16511();
        this.mTouchListener = new C16522();
        this.mHandler = new C16533();
        this.mPauseListener = new C16544();
        this.mSeekListener = new C16555();
        this.mRewListener = new C16566();
        this.mFfwdListener = new C16577();
        this.mContext = context;
        this.mUseFastForward = z;
        initFloatingWindowLayout();
        initFloatingWindow();
    }

    private void disableUnsupportedButtons() {
        try {
            if (!(this.mPauseButton == null || this.mPlayer.canPause())) {
                this.mPauseButton.setEnabled(false);
            }
            if (!(this.mRewButton == null || this.mPlayer.canSeekBackward())) {
                this.mRewButton.setEnabled(false);
            }
            if (this.mFfwdButton != null && !this.mPlayer.canSeekForward()) {
                this.mFfwdButton.setEnabled(false);
            }
        } catch (IncompatibleClassChangeError e) {
        }
    }

    private void doPauseResume() {
        if (this.mPlayer.isPlaying()) {
            this.mPlayer.pause();
        } else {
            this.mPlayer.start();
        }
        updatePausePlay();
    }

    private void initControllerView(View view) {
        this.mPauseButton = (ImageButton) view.findViewById(C1205R.id.pause);
        if (this.mPauseButton != null) {
            this.mPauseButton.requestFocus();
            this.mPauseButton.setOnClickListener(this.mPauseListener);
        }
        this.mFfwdButton = (ImageButton) view.findViewById(C1205R.id.ffwd);
        if (this.mFfwdButton != null) {
            this.mFfwdButton.setOnClickListener(this.mFfwdListener);
            if (!this.mFromXml) {
                this.mFfwdButton.setVisibility(this.mUseFastForward ? 0 : 8);
            }
        }
        this.mRewButton = (ImageButton) view.findViewById(C1205R.id.rew);
        if (this.mRewButton != null) {
            this.mRewButton.setOnClickListener(this.mRewListener);
            if (!this.mFromXml) {
                this.mRewButton.setVisibility(this.mUseFastForward ? 0 : 8);
            }
        }
        this.mNextButton = (ImageButton) view.findViewById(C1205R.id.next);
        if (!(this.mNextButton == null || this.mFromXml || this.mListenersSet)) {
            this.mNextButton.setVisibility(8);
        }
        this.mPrevButton = (ImageButton) view.findViewById(C1205R.id.prev);
        if (!(this.mPrevButton == null || this.mFromXml || this.mListenersSet)) {
            this.mPrevButton.setVisibility(8);
        }
        this.mProgress = (SeekBar) view.findViewById(C1205R.id.fmmediacontroller_progress);
        if (this.mProgress != null) {
            if (this.mProgress instanceof SeekBar) {
                this.mProgress.setOnSeekBarChangeListener(this.mSeekListener);
            }
            this.mProgress.setMax(XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER);
        }
        this.mEndTime = (TextView) view.findViewById(C1205R.id.time);
        this.mCurrentTime = (TextView) view.findViewById(C1205R.id.time_current);
        this.mFormatBuilder = new StringBuilder();
        this.mFormatter = new Formatter(this.mFormatBuilder, Locale.getDefault());
        AssetManager assets = ((Activity) this.mContext).getAssets();
        View[] viewArr = new View[SHOW_PROGRESS];
        viewArr[0] = this.mEndTime;
        viewArr[FADE_OUT] = this.mCurrentTime;
        be.m12359a(assets, viewArr);
        installPrevNextListeners();
    }

    private void initFloatingWindow() {
        this.mWindowManager = (WindowManager) this.mContext.getSystemService("window");
        initWindow();
        this.mWindow.setWindowManager(this.mWindowManager, null, null);
        this.mWindow.requestFeature(FADE_OUT);
        this.mDecor = this.mWindow.getDecorView();
        this.mDecor.setOnTouchListener(this.mTouchListener);
        this.mWindow.setContentView(this);
        this.mWindow.setBackgroundDrawableResource(AsyncImageView.f14604a);
        this.mWindow.setVolumeControlStream(3);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setDescendantFocusability(AccessibilityEventCompat.TYPE_GESTURE_DETECTION_START);
        requestFocus();
    }

    private void initFloatingWindowLayout() {
        this.mDecorLayoutParams = new LayoutParams();
        LayoutParams layoutParams = this.mDecorLayoutParams;
        layoutParams.gravity = 51;
        layoutParams.height = -2;
        layoutParams.x = 0;
        layoutParams.format = -3;
        layoutParams.type = XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER;
        layoutParams.flags |= 8519712;
        layoutParams.token = null;
        layoutParams.windowAnimations = 0;
    }

    private void initWindow() {
        try {
            Method[] methods = Class.forName("com.android.internal.policy.PolicyManager").getMethods();
            Method method = null;
            for (int i = 0; i < methods.length; i += FADE_OUT) {
                if (methods[i].getName().endsWith("makeNewWindow")) {
                    method = methods[i];
                }
            }
            Object[] objArr = new Object[FADE_OUT];
            objArr[0] = this.mContext;
            this.mWindow = (Window) method.invoke(null, objArr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void installPrevNextListeners() {
        boolean z = true;
        if (this.mNextButton != null) {
            this.mNextButton.setOnClickListener(this.mNextListener);
            this.mNextButton.setEnabled(this.mNextListener != null);
        }
        if (this.mPrevButton != null) {
            this.mPrevButton.setOnClickListener(this.mPrevListener);
            ImageButton imageButton = this.mPrevButton;
            if (this.mPrevListener == null) {
                z = false;
            }
            imageButton.setEnabled(z);
        }
    }

    public static void main(String[] strArr) {
        System.out.println(9);
    }

    private int setProgress() {
        Log.d("player", "setProgress");
        if (this.mPlayer == null || this.mDragging) {
            return 0;
        }
        int currentPosition = this.mPlayer.getCurrentPosition();
        int duration = this.mPlayer.getDuration();
        Log.d("player", "position" + currentPosition + " duration" + duration);
        if (this.mProgress != null && duration > 0) {
            if (duration / XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER > 0) {
                this.mProgress.setProgress((int) ((1000 * ((long) (currentPosition / XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER))) / ((long) (duration / XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER))));
            } else {
                this.mProgress.setProgress(0);
            }
        }
        if (this.mEndTime != null) {
            this.mEndTime.setText(stringForTime(duration));
        }
        if (!(this.mCurrentTime == null || this.mCurrentTime.getText().toString().equals(this.mEndTime.getText().toString()))) {
            this.mCurrentTime.setText(stringForTime(currentPosition));
        }
        if (currentPosition != 0) {
            return currentPosition;
        }
        this.mCurrentTime.setText(stringForTime(currentPosition));
        return currentPosition;
    }

    private String stringForTime(int i) {
        int i2 = i / XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER;
        int i3 = i2 % 60;
        int i4 = (i2 / 60) % 60;
        i2 /= 3600;
        this.mFormatBuilder.setLength(0);
        if (i2 > 0) {
            return this.mFormatter.format("%d:%02d:%02d", new Object[]{Integer.valueOf(i2), Integer.valueOf(i4), Integer.valueOf(i3)}).toString();
        }
        Object[] objArr = new Object[SHOW_PROGRESS];
        objArr[0] = Integer.valueOf(i4);
        objArr[FADE_OUT] = Integer.valueOf(i3);
        return this.mFormatter.format("%02d:%02d", objArr).toString();
    }

    private void updateFloatingWindowLayout() {
        int[] iArr = new int[SHOW_PROGRESS];
        this.mAnchor.getLocationOnScreen(iArr);
        this.mDecor.measure(MeasureSpec.makeMeasureSpec(this.mAnchor.getWidth(), C1186y.f5353a), MeasureSpec.makeMeasureSpec(this.mAnchor.getHeight(), C1186y.f5353a));
        LayoutParams layoutParams = this.mDecorLayoutParams;
        layoutParams.width = this.mAnchor.getWidth();
        layoutParams.x = iArr[0] + ((this.mAnchor.getWidth() - layoutParams.width) / SHOW_PROGRESS);
        layoutParams.y = (iArr[FADE_OUT] + this.mAnchor.getHeight()) - this.mDecor.getMeasuredHeight();
    }

    private void updatePausePlay() {
        if (this.mRoot != null && this.mPauseButton != null) {
            if (this.mPlayer.isPlaying()) {
                this.mPauseButton.setBackgroundResource(C1205R.drawable.drone_stop);
            } else {
                this.mPauseButton.setBackgroundResource(C1205R.drawable.drone_play);
            }
            this.mHandler.sendEmptyMessageDelayed(0, 200);
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        Object obj = (keyEvent.getRepeatCount() == 0 && keyEvent.getAction() == 0) ? FADE_OUT : null;
        if (keyCode == 79 || keyCode == 85 || keyCode == 62) {
            if (obj != null) {
                doPauseResume();
                show(sDefaultTimeout);
                if (this.mPauseButton != null) {
                    this.mPauseButton.requestFocus();
                }
            }
            return true;
        } else if (keyCode == Opcodes.IAND) {
            if (!(obj == null || this.mPlayer.isPlaying())) {
                this.mPlayer.start();
                updatePausePlay();
                show(sDefaultTimeout);
            }
            return true;
        } else if (keyCode == 86 || keyCode == Opcodes.LAND) {
            if (obj != null && this.mPlayer.isPlaying()) {
                this.mPlayer.pause();
                updatePausePlay();
                show(sDefaultTimeout);
            }
            return true;
        } else if (keyCode == 25 || keyCode == 24 || keyCode == Opcodes.IF_ICMPLE || keyCode == 27) {
            return super.dispatchKeyEvent(keyEvent);
        } else {
            if (keyCode == 4) {
                if (obj != null) {
                    hide();
                    ((Activity) this.mContext).finish();
                }
                return true;
            } else if (keyCode == 82) {
                if (obj != null) {
                    hide();
                }
                return true;
            } else {
                show(sDefaultTimeout);
                return super.dispatchKeyEvent(keyEvent);
            }
        }
    }

    public void hide() {
        if (this.mAnchor != null && this.mShowing) {
            try {
                this.mHandler.removeMessages(SHOW_PROGRESS);
                this.mWindowManager.removeView(this.mDecor);
            } catch (IllegalArgumentException e) {
                Log.w("MediaController", "already removed");
            }
            this.mShowing = false;
        }
    }

    public boolean isShowing() {
        return this.mShowing;
    }

    protected View makeControllerView() {
        this.mRoot = ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(C1205R.layout.fm_media_controller, null);
        initControllerView(this.mRoot);
        return this.mRoot;
    }

    public void onFinishInflate() {
        if (this.mRoot != null) {
            initControllerView(this.mRoot);
        }
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(FmMediaController.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(FmMediaController.class.getName());
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        show(sDefaultTimeout);
        return true;
    }

    public boolean onTrackballEvent(MotionEvent motionEvent) {
        show(sDefaultTimeout);
        return false;
    }

    public void setAnchorView(View view) {
        if (this.mAnchor != null) {
            this.mAnchor.removeOnLayoutChangeListener(this.mLayoutChangeListener);
        }
        this.mAnchor = view;
        if (this.mAnchor != null) {
            this.mAnchor.addOnLayoutChangeListener(this.mLayoutChangeListener);
        }
        ViewGroup.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        removeAllViews();
        addView(makeControllerView(), layoutParams);
    }

    public void setEnabled(boolean z) {
        boolean z2 = true;
        if (this.mPauseButton != null) {
            this.mPauseButton.setEnabled(z);
        }
        if (this.mFfwdButton != null) {
            this.mFfwdButton.setEnabled(z);
        }
        if (this.mRewButton != null) {
            this.mRewButton.setEnabled(z);
        }
        if (this.mNextButton != null) {
            ImageButton imageButton = this.mNextButton;
            boolean z3 = z && this.mNextListener != null;
            imageButton.setEnabled(z3);
        }
        if (this.mPrevButton != null) {
            ImageButton imageButton2 = this.mPrevButton;
            if (!z || this.mPrevListener == null) {
                z2 = false;
            }
            imageButton2.setEnabled(z2);
        }
        if (this.mProgress != null) {
            this.mProgress.setEnabled(z);
        }
        disableUnsupportedButtons();
        super.setEnabled(z);
    }

    public void setMediaPlayer(MediaPlayerControl mediaPlayerControl) {
        this.mPlayer = mediaPlayerControl;
        updatePausePlay();
    }

    public void setPrevNextListeners(OnClickListener onClickListener, OnClickListener onClickListener2) {
        this.mNextListener = onClickListener;
        this.mPrevListener = onClickListener2;
        this.mListenersSet = true;
        if (this.mRoot != null) {
            installPrevNextListeners();
            if (!(this.mNextButton == null || this.mFromXml)) {
                this.mNextButton.setVisibility(0);
            }
            if (this.mPrevButton != null && !this.mFromXml) {
                this.mPrevButton.setVisibility(0);
            }
        }
    }

    public void show() {
        show(sDefaultTimeout);
    }

    public void show(int i) {
        Log.d("player", "show");
        if (!(this.mShowing || this.mAnchor == null)) {
            setProgress();
            if (this.mPauseButton != null) {
                this.mPauseButton.requestFocus();
            }
            disableUnsupportedButtons();
            updateFloatingWindowLayout();
            try {
                this.mWindowManager.addView(this.mDecor, this.mDecorLayoutParams);
            } catch (Exception e) {
                Log.e("player", "mWindowManager addview error");
            }
            this.mShowing = true;
        }
        updatePausePlay();
        this.mHandler.sendEmptyMessage(SHOW_PROGRESS);
        Message obtainMessage = this.mHandler.obtainMessage(FADE_OUT);
        if (i != 0) {
            this.mHandler.removeMessages(FADE_OUT);
            this.mHandler.sendMessageDelayed(obtainMessage, (long) i);
        }
    }

    public void update() {
        updatePausePlay();
    }
}
