package com.fimi.soul.media.player;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build.VERSION;
import android.util.Log;
import com.fimi.kernel.p084e.C1173l;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.p122a.p123a.C2915a;

public class FermiPlayerUtils {
    private static SimpleDateFormat sdf;

    static {
        sdf = new SimpleDateFormat();
    }

    public static boolean createRemoteVideoThumbnail(String str, String str2) {
        return FimiMediaPlayer.getIntance().videoToJpg(str, str2);
    }

    public static Bitmap createVideoThumbnail(String str) {
        return ThumbnailUtils.createVideoThumbnail(str.replace("file://", C2915a.f14760f), 1);
    }

    public static Bitmap createVideoThumbnail(String str, int i) {
        String replace = str.replace("file://", C2915a.f14760f);
        if (VERSION.SDK_INT < 14) {
            return createVideoThumbnail(replace);
        }
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(replace);
        return mediaMetadataRetriever.getFrameAtTime((long) (i * XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER), 2);
    }

    public static Bitmap createVideoThumbnail(String str, int i, int i2) {
        Bitmap createVideoThumbnail = createVideoThumbnail(str);
        return createVideoThumbnail != null ? ThumbnailUtils.extractThumbnail(createVideoThumbnail, i, i2) : createVideoThumbnail;
    }

    public static Bitmap createVideoThumbnail(String str, int i, int i2, int i3) {
        Bitmap createVideoThumbnail = createVideoThumbnail(str, i3);
        return createVideoThumbnail != null ? ThumbnailUtils.extractThumbnail(createVideoThumbnail, i, i2) : createVideoThumbnail;
    }

    public static String getTimelineString(long j) {
        return getTimelineString(j, C1173l.f5335g);
    }

    public static String getTimelineString(long j, String str) {
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        sdf.applyPattern(str);
        return sdf.format(new Date(j));
    }

    public static long getVideoDuration(Context context, String str) {
        File file = new File(str);
        if (file.isFile() && file.exists()) {
            try {
                long duration = (long) MediaPlayer.create(context, Uri.fromFile(file)).getDuration();
                Log.d("Good", "time:" + duration);
                return duration;
            } catch (Exception e) {
            }
        }
        return 0;
    }

    public static String getVideoDurationString(Context context, String str) {
        return getTimelineString(getVideoDuration(context, str));
    }

    public static String getVideoDurationString(Context context, String str, String str2) {
        return getTimelineString(getVideoDuration(context, str), str2);
    }
}
