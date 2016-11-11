package com.baidu.tts.p041e;

import com.fimi.soul.media.player.IMediaPlayer;
import com.xiaomi.auth.AuthConstants;

/* renamed from: com.baidu.tts.e.n */
public enum C0802n {
    ONLINE_ENGINE_AUTH_FAILURE(C0801a.f4487b, -1, "online engine auth failure"),
    ONLINE_ENGINE_HTTP_REQUEST_FAILURE(C0801a.f4487b, -2, "request failure"),
    ONLINE_ENGINE_CANCEL_FAILURE(C0801a.f4487b, -3, "cancel failure"),
    ONLINE_AUTH_INTERRUPTED_EXCEPTION(C0801a.f4487b, -4, "InterruptedException"),
    ONLINE_AUTH_EXECUTION_EXCEPTION(C0801a.f4487b, -5, "ExecutionException"),
    ONLINE_AUTH_TIMEOUT_EXCEPTION(C0801a.f4487b, -6, "TimeoutException"),
    ONLINE_ENGINE_REQUEST_RESULT_ERROR(C0801a.f4487b, -7, "request result contains error message"),
    ONLINE_TOKEN_IS_NULL(C0801a.f4487b, -8, "access token is null, please check your apikey and secretkey or product id"),
    ONLINE_ENGINE_UNINITIALIZED(C0801a.f4487b, -9, "online engine is not initial"),
    ONLINE_ENGINE_CALL_EXCEPTION(C0801a.f4487b, -10, "online engine call synthesize exception"),
    ONLINE_UNSUPPORTED_OPERATION(C0801a.f4487b, -11, "this method is not supported by online mode(please use other mode)"),
    ONLINE_ENGINE_HTTP_REQUEST_PARSE_ERROR(C0801a.f4487b, -12, "request result parse error may responsebag is null"),
    ONLINE_ENGINE_GET_INTERRUPTED(C0801a.f4487b, -13, "online synthesize get was interrupted"),
    ONLINE_ENGINE_GET_EXECUTION_EXCEPTION(C0801a.f4487b, -14, "online synthesize get exception"),
    ONLINE_ENGINE_GET_TIMEOUT(C0801a.f4487b, -15, "online synthesize get was timeout"),
    ONLINE_AUTH_CANCELLATION_EXCEPTION(C0801a.f4487b, -16, "CancellationException"),
    OFFLINE_ENGINE_AUTH_FAILURE(C0801a.f4488c, -100, "offline engine auth failure,please check you offline auth params"),
    OFFLINE_ENGINE_CANCEL_FAILURE(C0801a.f4488c, -101, "offline engine cancel failure"),
    OFFLINE_ENGINE_DOWNLOAD_LICENSE_FAILED(C0801a.f4488c, -102, "offline engine download license failure"),
    OFFLINE_ENGINE_AUTH_NULL(C0801a.f4488c, -103, "offline engine auth authinfo is null"),
    OFFLINE_AUTH_INTERRUPTED_EXCEPTION(C0801a.f4488c, -105, "InterruptedException"),
    OFFLINE_AUTH_EXECUTION_EXCEPTION(C0801a.f4488c, -106, "ExecutionException"),
    OFFLINE_AUTH_TIMEOUT_EXCEPTION(C0801a.f4488c, -107, "TimeoutException"),
    OFFLINE_ENGINE_INIT_FAILED(C0801a.f4488c, -108, "bdTTSEngineInit failed,please check you offline params"),
    OFFLINE_ENGINE_UNINITIALIZED(C0801a.f4488c, -109, "offline engine is uninitialized,please invoke initTts() method"),
    OFFLINE_ENGINE_CALL_EXCEPTION(C0801a.f4488c, IMediaPlayer.MEDIA_ERROR_TIMED_OUT, "offline engine call synthesize exception"),
    OFFLINE_ENGINE_SYNTHESIZE_ERROR(C0801a.f4488c, -111, "offline engine synthesize result not 0"),
    OFFLINE_ENGINE_AUTH_EXPIRED(C0801a.f4488c, -112, "offline engine auth verify expired,formal expired or temp expired"),
    OFFLINE_ENGINE_AUTH_PACKAGE_UNMATCH(C0801a.f4488c, -113, "package name is unmatch"),
    OFFLINE_ENGINE_AUTH_SIGN_UNMATCH(C0801a.f4488c, -114, "app sign is unmatch"),
    OFFLINE_ENGINE_AUTH_CUID_UNMATCH(C0801a.f4488c, -115, "devices cuid is unmatch"),
    OFFLINE_ENGINE_AUTH_PLATFORM_ERROR(C0801a.f4488c, -116, "platform is unmatch"),
    OFFLINE_ENGINE_AUTH_LICENSE_FILE_INVALID(C0801a.f4488c, -117, "license file not exist or file length is 0 (download license fail)"),
    OFFLINE_AUTH_CANCELLATION_EXCEPTION(C0801a.f4487b, -118, "CancellationException"),
    MIX_ENGINE_AUTH_FAILURE(C0801a.f4486a, -200, "both online and offline engine auth failue"),
    MIX_AUTH_INTERRUPTED_EXCEPTION(C0801a.f4486a, -201, "InterruptedException"),
    MIX_AUTH_EXECUTION_EXCEPTION(C0801a.f4486a, -202, "ExecutionException"),
    MIX_AUTH_TIMEOUT_EXCEPTION(C0801a.f4486a, -203, "TimeoutException"),
    MIX_ENGINE_OFFLINE_INIT_FAILURE(C0801a.f4486a, -204, "mix engine initTTS, the offline init failure"),
    MIX_AUTH_CANCELLATION_EXCEPTION(C0801a.f4486a, -205, "CancellationException"),
    TEXT_IS_EMPTY(C0801a.f4489d, -300, "text is null or empty double quotation marks"),
    TEXT_IS_TOO_LONG(C0801a.f4489d, -301, "text length in gbk is more than 1024, the text is too long, cut it short than 1024"),
    TEXT_ENCODE_IS_WRONG(C0801a.f4489d, -302, "text encode is not gbk, please use gbk text"),
    TTS_UNINITIAL(C0801a.f4490e, -400, "tts has not been initialized,invoke in a wrong state"),
    TTS_MODE_ILLEGAL(C0801a.f4490e, -401, "tts mode unset or not the spechified value"),
    TTS_QUEUE_IS_FULL(C0801a.f4490e, -402, "\u961f\u5217\u957f\u5ea6\u5c0f\u4e8eMAX_QUEUE_SIZE\u65f6\u624d\u80fd\u52a0\u5165\u5408\u6210\u961f\u5217"),
    TTS_LIST_IS_TOO_LONG(C0801a.f4490e, -403, "list\u7684size\u5c0f\u4e8eMAX_LIST_SIZE\u65f6\u624d\u6709\u6548"),
    TTS_ENGINE_STOP_FAILURE(C0801a.f4490e, -404, "\u5f15\u64ce\u505c\u6b62\u5931\u8d25"),
    TTS_APP_ID_IS_INVALID(C0801a.f4490e, -405, "app id is invalid,must be less than int(11)"),
    TTS_PARAMETER_INVALID(C0801a.f4490e, -406, "arguments of the method is invalid"),
    APP_RESOURCE_IS_NULL(C0801a.f4491f, -500, "context was released or persistent app value is null"),
    PLAYER_IS_NULL(C0801a.f4492g, -600, "player is null"),
    MODEL_PARAMS_ERROR(C0801a.f4493h, AuthConstants.ERROR_CONNECT_FAILED, "params is wrong"),
    MODEL_REQUEST_ERROR(C0801a.f4493h, AuthConstants.ERROR_NEED_AUTHORIZE, "request error"),
    MODEL_SERVER_ERROR(C0801a.f4493h, AuthConstants.ERROR_LOGIN_FAILED, "server error"),
    MODEL_DB_MODEL_INVALID(C0801a.f4493h, AuthConstants.ERROR_AUTH_FAILED, "model item in db is invalid(fileids is empty)"),
    MODEL_DB_MODEL_FILE_PATHS_INVALID(C0801a.f4493h, IMediaPlayer.MEDIA_ERROR_IO, "model file in db is invalid(abspath is empty)"),
    MODEL_EXISTS(C0801a.f4493h, -1005, "this model exists(have downloaded success ever)"),
    MODEL_BAGS_EMPTY(C0801a.f4493h, -1006, "can't get server model info,maybe modelid invalid or request failure"),
    MODEL_FILE_BAG_EMPTY(C0801a.f4493h, IMediaPlayer.MEDIA_ERROR_MALFORMED, "can't get server file info,maybe fileid invalid or request failure"),
    MODEL_CHECK_EXCEPTION(C0801a.f4493h, -1008, "CheckWork exception happened"),
    MODEL_FILE_DOWNLOAD_EXCEPTION(C0801a.f4493h, -1009, "exception happens when file downloadwork execute"),
    TTS_ERROR_UNKNOW(C0801a.f4494i, -9999, "unknow");
    
    private final C0801a al;
    private final int am;
    private final String an;

    /* renamed from: com.baidu.tts.e.n.a */
    public enum C0801a {
        public static final C0801a f4486a;
        public static final C0801a f4487b;
        public static final C0801a f4488c;
        public static final C0801a f4489d;
        public static final C0801a f4490e;
        public static final C0801a f4491f;
        public static final C0801a f4492g;
        public static final C0801a f4493h;
        public static final C0801a f4494i;
        private static final /* synthetic */ C0801a[] f4495j;

        static {
            f4486a = new C0801a("MIX_ERROR", 0);
            f4487b = new C0801a("ONLINE_ENGINE_ERROR", 1);
            f4488c = new C0801a("OFFLINE_ENGINE_ERROR", 2);
            f4489d = new C0801a("TEXT", 3);
            f4490e = new C0801a("TTS", 4);
            f4491f = new C0801a("APP", 5);
            f4492g = new C0801a("PLAYER", 6);
            f4493h = new C0801a("MODEL", 7);
            f4494i = new C0801a("UNKNOW", 8);
            f4495j = new C0801a[]{f4486a, f4487b, f4488c, f4489d, f4490e, f4491f, f4492g, f4493h, f4494i};
        }

        private C0801a(String str, int i) {
        }

        public static C0801a valueOf(String str) {
            return (C0801a) Enum.valueOf(C0801a.class, str);
        }

        public static C0801a[] values() {
            return (C0801a[]) f4495j.clone();
        }
    }

    private C0802n(C0801a c0801a, int i, String str) {
        this.al = c0801a;
        this.am = i;
        this.an = str;
    }

    public C0801a m6751a() {
        return this.al;
    }

    public int m6752b() {
        return this.am;
    }

    public String m6753c() {
        return this.an;
    }
}
