package com.fimi.soul.media.player;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.fimi.kernel.p084e.aa;
import com.fimi.kernel.p084e.ak;
import com.fimi.kernel.view.percent.PercentRelativeLayout;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.BaseActivity;
import com.fimi.soul.media.player.IMediaPlayer.OnCompletionListener;
import com.fimi.soul.media.player.IMediaPlayer.OnErrorListener;
import com.fimi.soul.media.player.IMediaPlayer.OnPreparedListener;
import com.fimi.soul.media.player.widget.FimiVideoView;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import java.io.File;
import java.util.Formatter;
import java.util.Locale;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

public class FermiPlayerActivity extends BaseActivity implements OnClickListener {
    public static final String ONLINEFILE = "online_file";
    public static final String PLAY_NOW = "play_start";
    public static final String SP_KEY_PLAYER_ADDR = "SP_KEY_PLAYER_ADDR_VideoDialogFragment";
    public static final String SP_KEY_PLAYER_IS_REMOTE_ADDR = "SP_KEY_PLAYER_IS_REMOTE_ADDR_VideoDialogFragment";
    private final int BOTTOM_MSG_GONE;
    private final int BOTTOM_MSG_SHOW;
    private final int PROGRESS_MSG;
    private Bitmap bitmap;
    private PercentRelativeLayout bottom_layout;
    private ImageButton btnClose;
    private int current_position;
    boolean finishPlay;
    Handler handler;
    boolean isPause;
    boolean isShow;
    private TextView mCurrentTime;
    private TextView mEndTime;
    StringBuilder mFormatBuilder;
    Formatter mFormatter;
    private TextView mNameText;
    private SeekBar mProgress;
    boolean onlineFile;
    public String onlineUrl;
    private ImageButton playBtn;
    boolean play_start;
    private FimiVideoView player;
    Thread thread;
    private View top_layout;
    public String url;
    private ImageView videoThumbnail;

    /* renamed from: com.fimi.soul.media.player.FermiPlayerActivity.1 */
    class C16191 implements OnTouchListener {
        C16191() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    FermiPlayerActivity.this.handler.removeMessages(2);
                    FermiPlayerActivity.this.handler.removeMessages(3);
                    FermiPlayerActivity.this.bottom_layout.setVisibility(0);
                    FermiPlayerActivity.this.top_layout.setVisibility(0);
                    break;
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    if (!FermiPlayerActivity.this.isShow) {
                        FermiPlayerActivity.this.handler.sendEmptyMessage(2);
                        break;
                    }
                    FermiPlayerActivity.this.handler.sendEmptyMessage(3);
                    break;
            }
            return true;
        }
    }

    /* renamed from: com.fimi.soul.media.player.FermiPlayerActivity.2 */
    class C16202 implements OnErrorListener {
        C16202() {
        }

        public boolean onError(IMediaPlayer iMediaPlayer, int i, int i2) {
            FermiPlayerActivity.this.player.stopPlayback();
            ak.m8082a(FermiPlayerActivity.this, (int) C1205R.string.file_error);
            FermiPlayerActivity.this.finish();
            return false;
        }
    }

    /* renamed from: com.fimi.soul.media.player.FermiPlayerActivity.3 */
    class C16213 implements OnCompletionListener {
        C16213() {
        }

        public void onCompletion(IMediaPlayer iMediaPlayer) {
            FermiPlayerActivity.this.player.seekTo(FermiPlayerActivity.this.player.getCurrentPosition());
            FermiPlayerActivity.this.preferencesUtil.m12295a(0);
            FermiPlayerActivity.this.playBtn.setBackgroundResource(C1205R.drawable.drone_play);
            FermiPlayerActivity.this.player.release(true);
            FermiPlayerActivity.this.finishPlay = true;
        }
    }

    /* renamed from: com.fimi.soul.media.player.FermiPlayerActivity.4 */
    class C16224 implements OnPreparedListener {
        C16224() {
        }

        public void onPrepared(IMediaPlayer iMediaPlayer) {
            FermiPlayerActivity.this.handler.sendEmptyMessageDelayed(2, 500);
            int c = (int) FermiPlayerActivity.this.preferencesUtil.m12301c();
            FermiPlayerActivity.this.mProgress.setMax(FermiPlayerActivity.this.player.getDuration());
            FermiPlayerActivity.this.player.seekTo(c);
        }
    }

    /* renamed from: com.fimi.soul.media.player.FermiPlayerActivity.5 */
    class C16235 extends Handler {
        C16235() {
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                    super.handleMessage(message);
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    if (FermiPlayerActivity.this.player != null && FermiPlayerActivity.this.player.isPlaying()) {
                        FermiPlayerActivity.this.videoThumbnail.setVisibility(4);
                        FermiPlayerActivity.this.setProgress();
                    }
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    FermiPlayerActivity.this.top_layout.setVisibility(0);
                    FermiPlayerActivity.this.bottom_layout.setVisibility(0);
                    FermiPlayerActivity.this.handler.sendEmptyMessageDelayed(3, 3000);
                    FermiPlayerActivity.this.isShow = true;
                case Type.BYTE /*3*/:
                    FermiPlayerActivity.this.top_layout.setVisibility(8);
                    FermiPlayerActivity.this.bottom_layout.setVisibility(8);
                    FermiPlayerActivity.this.isShow = false;
                default:
            }
        }
    }

    /* renamed from: com.fimi.soul.media.player.FermiPlayerActivity.6 */
    class C16246 extends Thread {
        C16246() {
        }

        public void run() {
            while (FermiPlayerActivity.this.player != null && FermiPlayerActivity.this.player.getCurrentPosition() != FermiPlayerActivity.this.player.getDuration()) {
                try {
                    if (FermiPlayerActivity.this.player.isPlaying()) {
                        FermiPlayerActivity.this.handler.sendMessageDelayed(FermiPlayerActivity.this.handler.obtainMessage(1), 1);
                    }
                    C16246.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* renamed from: com.fimi.soul.media.player.FermiPlayerActivity.7 */
    class C16257 implements OnSeekBarChangeListener {
        C16257() {
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (z) {
                FermiPlayerActivity.this.bottom_layout.setVisibility(0);
                FermiPlayerActivity.this.top_layout.setVisibility(0);
                FermiPlayerActivity.this.isPause = true;
            }
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            int progress = seekBar.getProgress();
            FermiPlayerActivity.this.player.seekTo(progress);
            if (FermiPlayerActivity.this.player.isPlaying()) {
                FermiPlayerActivity.this.setProgress();
            } else {
                FermiPlayerActivity.this.preferencesUtil.m12295a((long) progress);
                if (FermiPlayerActivity.this.mCurrentTime != null) {
                    FermiPlayerActivity.this.mCurrentTime.setText(FermiPlayerActivity.this.stringForTime(progress));
                }
            }
            FermiPlayerActivity.this.isPause = false;
        }
    }

    public FermiPlayerActivity() {
        this.url = null;
        this.onlineUrl = null;
        this.isShow = false;
        this.finishPlay = false;
        this.PROGRESS_MSG = 1;
        this.BOTTOM_MSG_SHOW = 2;
        this.BOTTOM_MSG_GONE = 3;
        this.handler = new C16235();
        this.isPause = false;
        this.thread = new C16246();
    }

    private void initMediaContoller() {
        this.bottom_layout = (PercentRelativeLayout) findViewById(C1205R.id.bottom_layout);
        this.mEndTime = (TextView) this.bottom_layout.findViewById(C1205R.id.time);
        this.mCurrentTime = (TextView) this.bottom_layout.findViewById(C1205R.id.time_current);
        this.playBtn = (ImageButton) this.bottom_layout.findViewById(C1205R.id.pause);
        this.mProgress = (SeekBar) this.bottom_layout.findViewById(C1205R.id.fmmediacontroller_progress);
        this.mFormatBuilder = new StringBuilder();
        this.mFormatter = new Formatter(this.mFormatBuilder, Locale.getDefault());
        this.playBtn.setOnClickListener(this);
        this.mProgress.setOnSeekBarChangeListener(new C16257());
    }

    private void initPlay() {
        this.player.setDecodeType(1);
        this.player.setVideoPath(this.url);
        this.finishPlay = false;
        this.player.setOnPreparedListener(new C16224());
    }

    private void play() {
        if (!this.player.isPlaying()) {
            this.player.start();
            this.playBtn.setBackgroundResource(C1205R.drawable.drone_stop);
        }
    }

    private int setProgress() {
        if (this.player == null || this.isPause) {
            return 0;
        }
        int currentPosition = this.player.getCurrentPosition();
        int duration = this.player.getDuration();
        if (this.mProgress != null) {
            this.mProgress.setProgress(currentPosition);
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

    @TargetApi(16)
    private void showVideoThumbnail(Bitmap bitmap) {
        if (bitmap != null) {
            this.videoThumbnail.setBackground(aa.m7991a(bitmap));
            this.videoThumbnail.setVisibility(0);
        }
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
        return this.mFormatter.format("%02d:%02d", new Object[]{Integer.valueOf(i4), Integer.valueOf(i3)}).toString();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1205R.id.mBplayButton:
                play();
            case C1205R.id.iv_close:
                finish();
            case C1205R.id.pause:
                if (this.player.isPlaying()) {
                    this.player.pause();
                    this.playBtn.setBackgroundResource(C1205R.drawable.drone_play);
                    return;
                }
                if (this.finishPlay) {
                    initPlay();
                }
                this.player.start();
                this.playBtn.setBackgroundResource(C1205R.drawable.drone_stop);
            default:
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1205R.layout.activity_player);
        initMediaContoller();
        this.videoThumbnail = (ImageView) findViewById(C1205R.id.videoThumbnail);
        this.btnClose = (ImageButton) getWindow().findViewById(C1205R.id.iv_close);
        this.btnClose.setOnClickListener(this);
        Intent intent = getIntent();
        if (intent != null) {
            this.url = intent.getStringExtra(SP_KEY_PLAYER_ADDR);
            this.onlineUrl = intent.getStringExtra(SP_KEY_PLAYER_IS_REMOTE_ADDR);
        }
        this.preferencesUtil.m12295a(0);
        this.top_layout = findViewById(C1205R.id.rl_topView);
        this.mNameText = (TextView) findViewById(C1205R.id.iv_name);
        this.play_start = getIntent().getBooleanExtra(PLAY_NOW, false);
        this.onlineFile = getIntent().getBooleanExtra(ONLINEFILE, false);
        int lastIndexOf = this.url.lastIndexOf("/");
        if (lastIndexOf > 1) {
            this.mNameText.setText(this.url.substring(lastIndexOf + 1));
            if (this.onlineUrl == null || !new File(this.onlineUrl).exists()) {
                this.bitmap = FermiPlayerUtils.createVideoThumbnail(this.url);
            } else {
                this.bitmap = BitmapFactory.decodeFile(this.onlineUrl);
            }
            showVideoThumbnail(this.bitmap);
        }
        this.player = (FimiVideoView) findViewById(C1205R.id.video_view);
        this.player.setOnClickListener(this);
        this.player.setOnTouchListener(new C16191());
        initPlay();
        this.player.setOnErrorListener(new C16202());
        this.player.setOnCompletionListener(new C16213());
        this.thread.start();
    }

    public void onDestroy() {
        super.onDestroy();
        if (!(this.bitmap == null || this.bitmap.isRecycled())) {
            this.bitmap.recycle();
            Log.d("player", "bitmap recycle");
        }
        this.bitmap = null;
        this.player.release(true);
        this.player = null;
        getIntent().putExtra(PLAY_NOW, false);
        System.gc();
    }

    public void onResume() {
        super.onResume();
        this.player.setDecodeType(1);
        this.player.setVideoPath(this.url);
        if (this.play_start) {
            play();
        }
        showVideoThumbnail(this.bitmap);
    }

    protected void onSaveInstanceState(Bundle bundle) {
        this.preferencesUtil.m12295a((long) this.current_position);
        super.onSaveInstanceState(bundle);
    }

    public void onStop() {
        super.onStop();
        this.player.pause();
        this.current_position = this.player.getCurrentPosition();
        this.preferencesUtil.m12295a((long) this.current_position);
        this.playBtn.setBackgroundResource(C1205R.drawable.drone_play);
        if (!this.onlineFile) {
            this.player.release(true);
        }
        this.play_start = false;
    }
}
