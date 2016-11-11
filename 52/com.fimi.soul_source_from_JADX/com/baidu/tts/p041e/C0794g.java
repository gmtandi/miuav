package com.baidu.tts.p041e;

import com.facebook.common.util.UriUtil;
import com.fimi.soul.entity.User;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.platformtools.LocaleUtil;
import com.tencent.mm.sdk.platformtools.Util;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.OAuth;
import com.tencent.open.GameAppOperation;
import com.tencent.open.SocialConstants;
import com.tencent.stat.DeviceInfo;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.market.sdk.C2537j;
import org.p122a.p123a.C2915a;

/* renamed from: com.baidu.tts.e.g */
public enum C0794g {
    STATE(null, XiaomiOAuthConstants.EXTRA_STATE_2, null),
    CODE(null, XiaomiOAuthConstants.EXTRA_CODE_2, null),
    DATA(null, UriUtil.DATA_SCHEME, null),
    IVERSION(null, "iversion", null),
    URL(null, SocialConstants.PARAM_URL, null),
    MD5(null, "md5", null),
    LENGTH(null, "length", null),
    ABS_PATH(null, "absPath", null),
    ID(null, LocaleUtil.INDONESIAN, null),
    GENDER(null, "gender", null),
    DOMAIN(null, "domain", null),
    QUALITY(null, "quality", null),
    DATA_COUNT(null, "data_count", null),
    DATA_LIST(null, "data_list", null),
    NAME(null, User.FN_NAME, null),
    VERSION_MIN(null, "version_min", null),
    VERSION_MAX(null, "version_max", null),
    TEXT_DATA_ID(null, "text_data_id", null),
    SPEECH_DATA_ID(null, "speech_data_id", null),
    FUNCTION("func", "function", C2915a.f14760f),
    ERROR_NUMBER("err_no", "errorNumber", C2915a.f14760f),
    ERROR_MESSAGE("err_msg", "errorMessage", C2915a.f14760f),
    MIX_MODE(null, "mixMode", null),
    NOTIFICATION_COUNT_PER_SECOND("ncps", "notificationCountPerSecond", C2915a.f14760f),
    PERCENT("pct", "percent", C2915a.f14760f),
    APP_CODE("ac", "appCode", C2915a.f14760f),
    PACKAGE_NAME("pn", C2537j.f12839W, GameAppOperation.QQFAV_DATALINE_APPNAME),
    PLATFORM(C2915a.f14760f, Constants.PARAM_PLATFORM, C2915a.f14760f),
    SPEED("spd", "speed", C2915a.f14760f),
    VOLUME("vol", "volume", C2915a.f14760f),
    PITCH("pit", "pitch", C2915a.f14760f),
    LANGUAGE("lan", "language", C2915a.f14760f),
    TEXT_ENCODE("cod", "textEncode", C2915a.f14760f),
    STREAM_TYPE("st", "streamType", C2915a.f14760f),
    AUDIO_ENCODE("aue", "audioEncode", C2915a.f14760f),
    BITRATE("rate", "audioRate", C2915a.f14760f),
    SPEAKER("per", "speaker", C2915a.f14760f),
    STYLE("sty", "style", C2915a.f14760f),
    BACKGROUND("bcg", "background", C2915a.f14760f),
    PRODUCT_ID("pdt", "productId", C2915a.f14760f),
    TEXT_DAT_PATH("tdp", "textDatPath", C2915a.f14760f),
    SPEECH_DAT_PATH("sdp", "speechDatPath", C2915a.f14760f),
    TTS_LICENSE_FILE_PATH("tlfp", "ttsLicenseFilePath", C2915a.f14760f),
    CUSTOM_SYNTH("cs", "custom_synth", C2915a.f14760f),
    OPEN_XML("ox", "open_xml", C2915a.f14760f),
    TTS_VOCODER_OPTIMIZATION("tvo", "ttsVocoderOptimzation", C2915a.f14760f),
    SAMPLE_RATE("sr", "sampleRate", C2915a.f14760f),
    SERIAL_NUMBER("sn", "serialNumber", C2915a.f14760f),
    INDEX("idx", "index", C2915a.f14760f),
    TEXT("tex", "text", C2915a.f14760f),
    CTP("ctp", "clientPath", C2915a.f14760f),
    CUID("cuid", C2537j.as, "wise_cuid"),
    VERSION(DeviceInfo.TAG_VERSION, C2537j.aq, "sdk_version"),
    NUMBER("num", "number", C2915a.f14760f),
    ENGINE(Util.ENGLISH, "engine", C2915a.f14760f),
    TERRITORY("ter", "territory", C2915a.f14760f),
    PUNCTUATION("puc", "punctuation", C2915a.f14760f),
    CONTEXT("ctx", "context", C2915a.f14760f),
    API_KEY(C2915a.f14760f, OAuth.API_KEY, C2915a.f14760f),
    SECRET_KEY(C2915a.f14760f, "secretKey", C2915a.f14760f),
    TOKEN("tok", "token", C2915a.f14760f);
    
    private final String aj;
    private final String ak;
    private final String al;

    private C0794g(String str, String str2, String str3) {
        this.aj = str;
        this.ak = str2;
        this.al = str3;
    }

    public static String m6740a(C0794g c0794g) {
        return c0794g == null ? null : c0794g.name();
    }

    public String m6741a() {
        return this.aj;
    }

    public String m6742b() {
        return this.ak;
    }
}
