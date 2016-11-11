package com.fimi.soul.media.player;

import android.os.Bundle;
import android.text.TextUtils;
import com.fimi.soul.module.setting.newhand.C1873o;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

public class FimiMediaMeta {
    public static final long AV_CH_BACK_CENTER = 256;
    public static final long AV_CH_BACK_LEFT = 16;
    public static final long AV_CH_BACK_RIGHT = 32;
    public static final long AV_CH_FRONT_CENTER = 4;
    public static final long AV_CH_FRONT_LEFT = 1;
    public static final long AV_CH_FRONT_LEFT_OF_CENTER = 64;
    public static final long AV_CH_FRONT_RIGHT = 2;
    public static final long AV_CH_FRONT_RIGHT_OF_CENTER = 128;
    public static final long AV_CH_LAYOUT_2POINT1 = 11;
    public static final long AV_CH_LAYOUT_2_1 = 259;
    public static final long AV_CH_LAYOUT_2_2 = 1539;
    public static final long AV_CH_LAYOUT_3POINT1 = 15;
    public static final long AV_CH_LAYOUT_4POINT0 = 263;
    public static final long AV_CH_LAYOUT_4POINT1 = 271;
    public static final long AV_CH_LAYOUT_5POINT0 = 1543;
    public static final long AV_CH_LAYOUT_5POINT0_BACK = 55;
    public static final long AV_CH_LAYOUT_5POINT1 = 1551;
    public static final long AV_CH_LAYOUT_5POINT1_BACK = 63;
    public static final long AV_CH_LAYOUT_6POINT0 = 1799;
    public static final long AV_CH_LAYOUT_6POINT0_FRONT = 1731;
    public static final long AV_CH_LAYOUT_6POINT1 = 1807;
    public static final long AV_CH_LAYOUT_6POINT1_BACK = 319;
    public static final long AV_CH_LAYOUT_6POINT1_FRONT = 1739;
    public static final long AV_CH_LAYOUT_7POINT0 = 1591;
    public static final long AV_CH_LAYOUT_7POINT0_FRONT = 1735;
    public static final long AV_CH_LAYOUT_7POINT1 = 1599;
    public static final long AV_CH_LAYOUT_7POINT1_WIDE = 1743;
    public static final long AV_CH_LAYOUT_7POINT1_WIDE_BACK = 255;
    public static final long AV_CH_LAYOUT_HEXAGONAL = 311;
    public static final long AV_CH_LAYOUT_MONO = 4;
    public static final long AV_CH_LAYOUT_OCTAGONAL = 1847;
    public static final long AV_CH_LAYOUT_QUAD = 51;
    public static final long AV_CH_LAYOUT_STEREO = 3;
    public static final long AV_CH_LAYOUT_STEREO_DOWNMIX = 1610612736;
    public static final long AV_CH_LAYOUT_SURROUND = 7;
    public static final long AV_CH_LOW_FREQUENCY = 8;
    public static final long AV_CH_LOW_FREQUENCY_2 = 34359738368L;
    public static final long AV_CH_SIDE_LEFT = 512;
    public static final long AV_CH_SIDE_RIGHT = 1024;
    public static final long AV_CH_STEREO_LEFT = 536870912;
    public static final long AV_CH_STEREO_RIGHT = 1073741824;
    public static final long AV_CH_SURROUND_DIRECT_LEFT = 8589934592L;
    public static final long AV_CH_SURROUND_DIRECT_RIGHT = 17179869184L;
    public static final long AV_CH_TOP_BACK_CENTER = 65536;
    public static final long AV_CH_TOP_BACK_LEFT = 32768;
    public static final long AV_CH_TOP_BACK_RIGHT = 131072;
    public static final long AV_CH_TOP_CENTER = 2048;
    public static final long AV_CH_TOP_FRONT_CENTER = 8192;
    public static final long AV_CH_TOP_FRONT_LEFT = 4096;
    public static final long AV_CH_TOP_FRONT_RIGHT = 16384;
    public static final long AV_CH_WIDE_LEFT = 2147483648L;
    public static final long AV_CH_WIDE_RIGHT = 4294967296L;
    public static final String IJKM_KEY_AUDIO_STREAM = "audio";
    public static final String IJKM_KEY_BITRATE = "bitrate";
    public static final String IJKM_KEY_CHANNEL_LAYOUT = "channel_layout";
    public static final String IJKM_KEY_CODEC_LONG_NAME = "codec_long_name";
    public static final String IJKM_KEY_CODEC_NAME = "codec_name";
    public static final String IJKM_KEY_CODEC_PROFILE = "codec_profile";
    public static final String IJKM_KEY_DURATION_US = "duration_us";
    public static final String IJKM_KEY_FORMAT = "format";
    public static final String IJKM_KEY_FPS_DEN = "fps_den";
    public static final String IJKM_KEY_FPS_NUM = "fps_num";
    public static final String IJKM_KEY_HEIGHT = "height";
    public static final String IJKM_KEY_SAMPLE_RATE = "sample_rate";
    public static final String IJKM_KEY_SAR_DEN = "sar_den";
    public static final String IJKM_KEY_SAR_NUM = "sar_num";
    public static final String IJKM_KEY_START_US = "start_us";
    public static final String IJKM_KEY_STREAMS = "streams";
    public static final String IJKM_KEY_TBR_DEN = "tbr_den";
    public static final String IJKM_KEY_TBR_NUM = "tbr_num";
    public static final String IJKM_KEY_TYPE = "type";
    public static final String IJKM_KEY_VIDEO_STREAM = "video";
    public static final String IJKM_KEY_WIDTH = "width";
    public static final String IJKM_VAL_TYPE__AUDIO = "audio";
    public static final String IJKM_VAL_TYPE__UNKNOWN = "unknown";
    public static final String IJKM_VAL_TYPE__VIDEO = "video";
    public IjkStreamMeta mAudioStream;
    public long mBitrate;
    public long mDurationUS;
    public String mFormat;
    public Bundle mMediaMeta;
    public long mStartUS;
    public ArrayList<IjkStreamMeta> mStreams;
    public IjkStreamMeta mVideoStream;

    public class IjkStreamMeta {
        public long mBitrate;
        public long mChannelLayout;
        public String mCodecLongName;
        public String mCodecName;
        public String mCodecProfile;
        public int mFpsDen;
        public int mFpsNum;
        public int mHeight;
        public int mIndex;
        public Bundle mMeta;
        public int mSampleRate;
        public int mSarDen;
        public int mSarNum;
        public int mTbrDen;
        public int mTbrNum;
        public String mType;
        public int mWidth;

        public IjkStreamMeta(int i) {
            this.mIndex = i;
        }

        public String getBitrateInline() {
            if (this.mBitrate <= 0) {
                return C1873o.an;
            }
            if (this.mBitrate < 1000) {
                return String.format(Locale.US, "%d bit/s", new Object[]{Long.valueOf(this.mBitrate)});
            }
            return String.format(Locale.US, "%d kb/s", new Object[]{Long.valueOf(this.mBitrate / 1000)});
        }

        public String getChannelLayoutInline() {
            if (this.mChannelLayout <= 0) {
                return C1873o.an;
            }
            if (this.mChannelLayout == FimiMediaMeta.AV_CH_LAYOUT_MONO) {
                return "mono";
            }
            if (this.mChannelLayout == FimiMediaMeta.AV_CH_LAYOUT_STEREO) {
                return "stereo";
            }
            return String.format(Locale.US, "%x", new Object[]{Long.valueOf(this.mChannelLayout)});
        }

        public String getCodecLongNameInline() {
            return !TextUtils.isEmpty(this.mCodecLongName) ? this.mCodecLongName : !TextUtils.isEmpty(this.mCodecName) ? this.mCodecName : C1873o.an;
        }

        public String getFpsInline() {
            return (this.mFpsNum <= 0 || this.mFpsDen <= 0) ? C1873o.an : String.valueOf(((float) this.mFpsNum) / ((float) this.mFpsDen));
        }

        public int getInt(String str) {
            return getInt(str, 0);
        }

        public int getInt(String str, int i) {
            Object string = getString(str);
            if (!TextUtils.isEmpty(string)) {
                try {
                    i = Integer.parseInt(string);
                } catch (NumberFormatException e) {
                }
            }
            return i;
        }

        public long getLong(String str) {
            return getLong(str, 0);
        }

        public long getLong(String str, long j) {
            Object string = getString(str);
            if (!TextUtils.isEmpty(string)) {
                try {
                    j = Long.parseLong(string);
                } catch (NumberFormatException e) {
                }
            }
            return j;
        }

        public String getResolutionInline() {
            if (this.mWidth <= 0 || this.mHeight <= 0) {
                return C1873o.an;
            }
            if (this.mSarNum <= 0 || this.mSarDen <= 0) {
                return String.format(Locale.US, "%d x %d", new Object[]{Integer.valueOf(this.mWidth), Integer.valueOf(this.mHeight)});
            }
            return String.format(Locale.US, "%d x %d [SAR %d:%d]", new Object[]{Integer.valueOf(this.mWidth), Integer.valueOf(this.mHeight), Integer.valueOf(this.mSarNum), Integer.valueOf(this.mSarDen)});
        }

        public String getSampleRateInline() {
            if (this.mSampleRate <= 0) {
                return C1873o.an;
            }
            return String.format(Locale.US, "%d Hz", new Object[]{Integer.valueOf(this.mSampleRate)});
        }

        public String getString(String str) {
            return this.mMeta.getString(str);
        }
    }

    public static FimiMediaMeta parse(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        FimiMediaMeta fimiMediaMeta = new FimiMediaMeta();
        fimiMediaMeta.mMediaMeta = bundle;
        fimiMediaMeta.mFormat = fimiMediaMeta.getString(IJKM_KEY_FORMAT);
        fimiMediaMeta.mDurationUS = fimiMediaMeta.getLong(IJKM_KEY_DURATION_US);
        fimiMediaMeta.mStartUS = fimiMediaMeta.getLong(IJKM_KEY_START_US);
        fimiMediaMeta.mBitrate = fimiMediaMeta.getLong(IJKM_KEY_BITRATE);
        int i = fimiMediaMeta.getInt(IJKM_VAL_TYPE__VIDEO, -1);
        int i2 = fimiMediaMeta.getInt(IJKM_VAL_TYPE__AUDIO, -1);
        ArrayList parcelableArrayList = fimiMediaMeta.getParcelableArrayList(IJKM_KEY_STREAMS);
        if (parcelableArrayList == null) {
            return fimiMediaMeta;
        }
        Iterator it = parcelableArrayList.iterator();
        int i3 = -1;
        while (it.hasNext()) {
            Bundle bundle2 = (Bundle) it.next();
            i3++;
            if (bundle2 != null) {
                IjkStreamMeta ijkStreamMeta = new IjkStreamMeta(i3);
                ijkStreamMeta.mMeta = bundle2;
                ijkStreamMeta.mType = ijkStreamMeta.getString(IJKM_KEY_TYPE);
                if (!TextUtils.isEmpty(ijkStreamMeta.mType)) {
                    ijkStreamMeta.mCodecName = ijkStreamMeta.getString(IJKM_KEY_CODEC_NAME);
                    ijkStreamMeta.mCodecProfile = ijkStreamMeta.getString(IJKM_KEY_CODEC_PROFILE);
                    ijkStreamMeta.mCodecLongName = ijkStreamMeta.getString(IJKM_KEY_CODEC_LONG_NAME);
                    ijkStreamMeta.mBitrate = (long) ijkStreamMeta.getInt(IJKM_KEY_BITRATE);
                    if (ijkStreamMeta.mType.equalsIgnoreCase(IJKM_VAL_TYPE__VIDEO)) {
                        ijkStreamMeta.mWidth = ijkStreamMeta.getInt(IJKM_KEY_WIDTH);
                        ijkStreamMeta.mHeight = ijkStreamMeta.getInt(IJKM_KEY_HEIGHT);
                        ijkStreamMeta.mFpsNum = ijkStreamMeta.getInt(IJKM_KEY_FPS_NUM);
                        ijkStreamMeta.mFpsDen = ijkStreamMeta.getInt(IJKM_KEY_FPS_DEN);
                        ijkStreamMeta.mTbrNum = ijkStreamMeta.getInt(IJKM_KEY_TBR_NUM);
                        ijkStreamMeta.mTbrDen = ijkStreamMeta.getInt(IJKM_KEY_TBR_DEN);
                        ijkStreamMeta.mSarNum = ijkStreamMeta.getInt(IJKM_KEY_SAR_NUM);
                        ijkStreamMeta.mSarDen = ijkStreamMeta.getInt(IJKM_KEY_SAR_DEN);
                        if (i == i3) {
                            fimiMediaMeta.mVideoStream = ijkStreamMeta;
                        }
                    } else if (ijkStreamMeta.mType.equalsIgnoreCase(IJKM_VAL_TYPE__AUDIO)) {
                        ijkStreamMeta.mSampleRate = ijkStreamMeta.getInt(IJKM_KEY_SAMPLE_RATE);
                        ijkStreamMeta.mChannelLayout = ijkStreamMeta.getLong(IJKM_KEY_CHANNEL_LAYOUT);
                        if (i2 == i3) {
                            fimiMediaMeta.mAudioStream = ijkStreamMeta;
                        }
                    }
                }
            }
        }
        return fimiMediaMeta;
    }

    public String getDurationInline() {
        long j = (this.mDurationUS + 5000) / 1000000;
        long j2 = j / 60;
        j %= 60;
        long j3 = j2 / 60;
        j2 %= 60;
        return String.format(Locale.US, "%02d:%02d:%02d", new Object[]{Long.valueOf(j3), Long.valueOf(j2), Long.valueOf(j)});
    }

    public int getInt(String str) {
        return getInt(str, 0);
    }

    public int getInt(String str, int i) {
        Object string = getString(str);
        if (!TextUtils.isEmpty(string)) {
            try {
                i = Integer.parseInt(string);
            } catch (NumberFormatException e) {
            }
        }
        return i;
    }

    public long getLong(String str) {
        return getLong(str, 0);
    }

    public long getLong(String str, long j) {
        Object string = getString(str);
        if (!TextUtils.isEmpty(string)) {
            try {
                j = Long.parseLong(string);
            } catch (NumberFormatException e) {
            }
        }
        return j;
    }

    public ArrayList<Bundle> getParcelableArrayList(String str) {
        return this.mMediaMeta.getParcelableArrayList(str);
    }

    public String getString(String str) {
        return this.mMediaMeta.getString(str);
    }
}
