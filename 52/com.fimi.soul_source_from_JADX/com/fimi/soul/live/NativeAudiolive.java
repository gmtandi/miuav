package com.fimi.soul.live;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import com.fimi.soul.C1205R;

public class NativeAudiolive implements Callback {
    public static final int FM_LIVE_PUSHING = 43691;
    public static final int FM_LIVE_PUSH_FORMAT_ERROR = 5001219;
    public static final int FM_LIVE_PUSH_NET_BLOCKS = 5001221;
    public static final int FM_LIVE_PUSH_OK = 5001223;
    public static final int FM_LIVE_PUSH_RUNNIG = 5001222;
    public static final int FM_LIVE_VIDEO_DISCONNECTED = 5002756;
    public static final int FM_LIVE_VIDEO_EXIT = 5002753;
    public static final int FM_LIVE_VIDEO_FORMAT_ERROR = 5002755;
    public static final int FM_LIVE_VIDEO_SYS_FAILED = 5002754;
    private final int FM_LIVE_AUDIO_BUSY_DEV;
    private final int FM_LIVE_AUDIO_EXIT;
    private final int FM_LIVE_PUSH_DISCONNECTED;
    private final int FM_LIVE_PUSH_EXIT;
    private final int FM_LIVE_PUSH_SYS_FAILED;
    Handler handler;
    LiveCallBackListener liveCallBackListener;

    public interface LiveCallBackListener {
        String liveCallResult(int i, int i2);
    }

    static {
        System.loadLibrary("fmlive");
    }

    public NativeAudiolive() {
        this.FM_LIVE_AUDIO_EXIT = 4997377;
        this.FM_LIVE_AUDIO_BUSY_DEV = 4997378;
        this.FM_LIVE_PUSH_EXIT = 5001217;
        this.FM_LIVE_PUSH_SYS_FAILED = 5001218;
        this.FM_LIVE_PUSH_DISCONNECTED = 5001220;
        this.handler = new Handler(this);
    }

    public void callBack(int i, int i2, int i3) {
        Log.v("FM", "msg=" + Integer.toHexString(i).toUpperCase());
        Log.v("FM", "arg1=" + i2);
        Log.v("FM", "arg2=" + i3);
        int i4 = 0;
        switch (i) {
            case 4997377:
                i4 = C1205R.string.live_audio_exit;
                break;
            case 4997378:
                i4 = C1205R.string.live_audio_busy_dev;
                break;
            case 5001217:
                i4 = C1205R.string.live_push_exit;
                break;
            case 5001218:
                i4 = C1205R.string.live_push_sys_failed;
                break;
            case FM_LIVE_PUSH_FORMAT_ERROR /*5001219*/:
                i4 = C1205R.string.live_push_format_error;
                break;
            case 5001220:
                i4 = C1205R.string.live_push_disconnected;
                break;
            case FM_LIVE_PUSH_NET_BLOCKS /*5001221*/:
                i4 = C1205R.string.live_push_net_blocks;
                break;
            case FM_LIVE_PUSH_RUNNIG /*5001222*/:
                return;
            case FM_LIVE_PUSH_OK /*5001223*/:
                i4 = C1205R.string.live_push_ok;
                break;
            case FM_LIVE_VIDEO_EXIT /*5002753*/:
                i4 = C1205R.string.live_video_exit;
                break;
            case FM_LIVE_VIDEO_SYS_FAILED /*5002754*/:
                i4 = C1205R.string.live_video_sys_failed;
                break;
            case FM_LIVE_VIDEO_FORMAT_ERROR /*5002755*/:
                i4 = C1205R.string.live_video_format_error;
                break;
            case FM_LIVE_VIDEO_DISCONNECTED /*5002756*/:
                i4 = C1205R.string.live_video_disconnected;
                break;
        }
        Message obtain = Message.obtain();
        obtain.obj = Integer.valueOf(i4);
        obtain.arg1 = i;
        this.handler.sendMessage(obtain);
    }

    public native int fmLiveGetStatus();

    public native int fmLiveStart(String str, String str2);

    public native int fmLiveStop();

    public LiveCallBackListener getLiveCallBackListener() {
        return this.liveCallBackListener;
    }

    public boolean handleMessage(Message message) {
        if (this.liveCallBackListener != null) {
            this.liveCallBackListener.liveCallResult(message.arg1, ((Integer) message.obj).intValue());
        }
        return false;
    }

    public boolean isLivePushing() {
        return fmLiveGetStatus() == FM_LIVE_PUSHING;
    }

    public void setLiveCallBackListener(LiveCallBackListener liveCallBackListener) {
        this.liveCallBackListener = liveCallBackListener;
    }
}
