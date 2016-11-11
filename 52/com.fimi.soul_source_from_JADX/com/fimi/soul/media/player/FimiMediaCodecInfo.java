package com.fimi.soul.media.player;

import android.annotation.TargetApi;
import android.media.MediaCodecInfo;
import android.media.MediaCodecInfo.CodecCapabilities;
import android.media.MediaCodecInfo.CodecProfileLevel;
import android.os.Build.VERSION;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.TextUtils;
import android.util.Log;
import com.fimi.soul.module.setting.newhand.ae;
import com.fimi.soul.utils.C1972l;
import com.p054c.p055a.p072c.C0957d;
import com.tencent.connect.common.Constants;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import it.p074a.p075a.C2799f;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.util.TokenBuffer.Segment;

public class FimiMediaCodecInfo {
    public static int RANK_ACCEPTABLE = 0;
    public static int RANK_LAST_CHANCE = 0;
    public static int RANK_MAX = 0;
    public static int RANK_NON_STANDARD = 0;
    public static int RANK_NO_SENSE = 0;
    public static int RANK_SECURE = 0;
    public static int RANK_SOFTWARE = 0;
    public static int RANK_TESTED = 0;
    private static final String TAG = "FimiMediaCodecInfo";
    private static Map<String, Integer> sKnownCodecList;
    public MediaCodecInfo mCodecInfo;
    public String mMimeType;
    public int mRank;

    static {
        RANK_MAX = XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER;
        RANK_TESTED = IMediaPlayer.MEDIA_INFO_BAD_INTERLEAVING;
        RANK_ACCEPTABLE = IMediaPlayer.MEDIA_INFO_VIDEO_TRACK_LAGGING;
        RANK_LAST_CHANCE = 600;
        RANK_SECURE = ae.f9482j;
        RANK_SOFTWARE = C2799f.f14282t;
        RANK_NON_STANDARD = 100;
        RANK_NO_SENSE = 0;
    }

    public FimiMediaCodecInfo() {
        this.mRank = 0;
    }

    private static synchronized Map<String, Integer> getKnownCodecList() {
        Map<String, Integer> map;
        synchronized (FimiMediaCodecInfo.class) {
            if (sKnownCodecList != null) {
                map = sKnownCodecList;
            } else {
                sKnownCodecList = new TreeMap(String.CASE_INSENSITIVE_ORDER);
                sKnownCodecList.put("OMX.Nvidia.h264.decode", Integer.valueOf(RANK_TESTED));
                sKnownCodecList.put("OMX.Nvidia.h264.decode.secure", Integer.valueOf(RANK_SECURE));
                sKnownCodecList.put("OMX.Intel.hw_vd.h264", Integer.valueOf(RANK_TESTED + 1));
                sKnownCodecList.put("OMX.Intel.VideoDecoder.AVC", Integer.valueOf(RANK_TESTED));
                sKnownCodecList.put("OMX.qcom.video.decoder.avc", Integer.valueOf(RANK_TESTED));
                sKnownCodecList.put("OMX.ittiam.video.decoder.avc", Integer.valueOf(RANK_NO_SENSE));
                sKnownCodecList.put("OMX.SEC.avc.dec", Integer.valueOf(RANK_TESTED));
                sKnownCodecList.put("OMX.SEC.AVC.Decoder", Integer.valueOf(RANK_TESTED - 1));
                sKnownCodecList.put("OMX.SEC.avcdec", Integer.valueOf(RANK_TESTED - 2));
                sKnownCodecList.put("OMX.SEC.avc.sw.dec", Integer.valueOf(RANK_SOFTWARE));
                sKnownCodecList.put("OMX.Exynos.avc.dec", Integer.valueOf(RANK_TESTED));
                sKnownCodecList.put("OMX.Exynos.AVC.Decoder", Integer.valueOf(RANK_TESTED - 1));
                sKnownCodecList.put("OMX.k3.video.decoder.avc", Integer.valueOf(RANK_TESTED));
                sKnownCodecList.put("OMX.IMG.MSVDX.Decoder.AVC", Integer.valueOf(RANK_TESTED));
                sKnownCodecList.put("OMX.TI.DUCATI1.VIDEO.DECODER", Integer.valueOf(RANK_TESTED));
                sKnownCodecList.put("OMX.rk.video_decoder.avc", Integer.valueOf(RANK_TESTED));
                sKnownCodecList.put("OMX.amlogic.avc.decoder.awesome", Integer.valueOf(RANK_TESTED));
                sKnownCodecList.put("OMX.MARVELL.VIDEO.HW.CODA7542DECODER", Integer.valueOf(RANK_TESTED));
                sKnownCodecList.put("OMX.MARVELL.VIDEO.H264DECODER", Integer.valueOf(RANK_SOFTWARE));
                sKnownCodecList.remove("OMX.BRCM.vc4.decoder.avc");
                sKnownCodecList.remove("OMX.brcm.video.h264.hw.decoder");
                sKnownCodecList.remove("OMX.brcm.video.h264.decoder");
                sKnownCodecList.remove("OMX.ST.VFM.H264Dec");
                sKnownCodecList.remove("OMX.allwinner.video.decoder.avc");
                sKnownCodecList.remove("OMX.MS.AVC.Decoder");
                sKnownCodecList.remove("OMX.hantro.81x0.video.decoder");
                sKnownCodecList.remove("OMX.hisi.video.decoder");
                sKnownCodecList.remove("OMX.cosmo.video.decoder.avc");
                sKnownCodecList.remove("OMX.duos.h264.decoder");
                sKnownCodecList.remove("OMX.bluestacks.hw.decoder");
                sKnownCodecList.put("OMX.google.h264.decoder", Integer.valueOf(RANK_SOFTWARE));
                sKnownCodecList.put("OMX.google.h264.lc.decoder", Integer.valueOf(RANK_SOFTWARE));
                sKnownCodecList.put("OMX.k3.ffmpeg.decoder", Integer.valueOf(RANK_SOFTWARE));
                sKnownCodecList.put("OMX.ffmpeg.video.decoder", Integer.valueOf(RANK_SOFTWARE));
                map = sKnownCodecList;
            }
        }
        return map;
    }

    public static String getLevelName(int i) {
        switch (i) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return Constants.VIA_TO_TYPE_QQ_GROUP;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return "1b";
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                return Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE;
            case Type.DOUBLE /*8*/:
                return Constants.VIA_REPORT_TYPE_SET_AVATAR;
            case Segment.TOKENS_PER_SEGMENT /*16*/:
                return Constants.VIA_REPORT_TYPE_JOININ_GROUP;
            case Opcodes.ACC_SYNCHRONIZED /*32*/:
                return Constants.VIA_SSO_LOGIN;
            case SmileConstants.TOKEN_PREFIX_TINY_ASCII /*64*/:
                return Constants.VIA_REPORT_TYPE_QQFAVORITES;
            case SmileConstants.TOKEN_PREFIX_TINY_UNICODE /*128*/:
                return Constants.VIA_REPORT_TYPE_DATALINE;
            case Opcodes.ACC_NATIVE /*256*/:
                return Constants.VIA_TO_TYPE_QQ_DISCUSS_GROUP;
            case Opcodes.ACC_INTERFACE /*512*/:
                return "31";
            case SmileConstants.MAX_SHARED_STRING_VALUES /*1024*/:
                return C1972l.f10200s;
            case Opcodes.ACC_STRICT /*2048*/:
                return Constants.VIA_TO_TYPE_QZONE;
            case Opcodes.ACC_SYNTHETIC /*4096*/:
                return "41";
            case Opcodes.ACC_ANNOTATION /*8192*/:
                return "42";
            case Opcodes.ACC_ENUM /*16384*/:
                return Constants.VIA_SHARE_TYPE_TEXT;
            case C0957d.f5043a /*32768*/:
                return "51";
            case AccessibilityNodeInfoCompat.ACTION_CUT /*65536*/:
                return "52";
            default:
                return Constants.VIA_RESULT_SUCCESS;
        }
    }

    public static String getProfileLevelName(int i, int i2) {
        return String.format(Locale.US, " %s Profile Level %s (%d,%d)", new Object[]{getProfileName(i), getLevelName(i2), Integer.valueOf(i), Integer.valueOf(i2)});
    }

    public static String getProfileName(int i) {
        switch (i) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return "Baseline";
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return "Main";
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                return "Extends";
            case Type.DOUBLE /*8*/:
                return "High";
            case Segment.TOKENS_PER_SEGMENT /*16*/:
                return "High10";
            case Opcodes.ACC_SYNCHRONIZED /*32*/:
                return "High422";
            case SmileConstants.TOKEN_PREFIX_TINY_ASCII /*64*/:
                return "High444";
            default:
                return "Unknown";
        }
    }

    @TargetApi(16)
    public static FimiMediaCodecInfo setupCandidate(MediaCodecInfo mediaCodecInfo, String str) {
        if (mediaCodecInfo == null || VERSION.SDK_INT < 16) {
            return null;
        }
        Object name = mediaCodecInfo.getName();
        if (TextUtils.isEmpty(name)) {
            return null;
        }
        int i;
        String toLowerCase = name.toLowerCase(Locale.US);
        int i2 = RANK_NO_SENSE;
        if (!toLowerCase.startsWith("omx.")) {
            i = RANK_NON_STANDARD;
        } else if (toLowerCase.startsWith("omx.pv")) {
            i = RANK_SOFTWARE;
        } else if (toLowerCase.startsWith("omx.google.")) {
            i = RANK_SOFTWARE;
        } else if (toLowerCase.startsWith("omx.ffmpeg.")) {
            i = RANK_SOFTWARE;
        } else if (toLowerCase.startsWith("omx.k3.ffmpeg.")) {
            i = RANK_SOFTWARE;
        } else if (toLowerCase.startsWith("omx.avcodec.")) {
            i = RANK_SOFTWARE;
        } else if (toLowerCase.startsWith("omx.ittiam.")) {
            i = RANK_NO_SENSE;
        } else if (toLowerCase.startsWith("omx.mtk.")) {
            i = VERSION.SDK_INT < 18 ? RANK_NO_SENSE : RANK_TESTED;
        } else {
            Integer num = (Integer) getKnownCodecList().get(toLowerCase);
            if (num != null) {
                i = num.intValue();
            } else {
                try {
                    i = mediaCodecInfo.getCapabilitiesForType(str) != null ? RANK_ACCEPTABLE : RANK_LAST_CHANCE;
                } catch (Throwable th) {
                    i = RANK_LAST_CHANCE;
                }
            }
        }
        FimiMediaCodecInfo fimiMediaCodecInfo = new FimiMediaCodecInfo();
        fimiMediaCodecInfo.mCodecInfo = mediaCodecInfo;
        fimiMediaCodecInfo.mRank = i;
        fimiMediaCodecInfo.mMimeType = str;
        return fimiMediaCodecInfo;
    }

    @TargetApi(16)
    public void dumpProfileLevels(String str) {
        int i = 0;
        if (VERSION.SDK_INT >= 16) {
            try {
                int i2;
                CodecCapabilities capabilitiesForType = this.mCodecInfo.getCapabilitiesForType(str);
                if (capabilitiesForType == null || capabilitiesForType.profileLevels == null) {
                    i2 = 0;
                } else {
                    i2 = 0;
                    for (CodecProfileLevel codecProfileLevel : capabilitiesForType.profileLevels) {
                        if (codecProfileLevel != null) {
                            i2 = Math.max(i2, codecProfileLevel.profile);
                            i = Math.max(i, codecProfileLevel.level);
                        }
                    }
                }
                Log.i(TAG, String.format(Locale.US, "%s", new Object[]{getProfileLevelName(i2, i)}));
            } catch (Throwable th) {
                Log.i(TAG, "profile-level: exception");
            }
        }
    }
}
