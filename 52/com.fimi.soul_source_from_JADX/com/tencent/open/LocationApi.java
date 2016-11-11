package com.tencent.open;

import android.app.Activity;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.tencent.connect.auth.QQAuth;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.connect.p128a.C2186a;
import com.tencent.open.C2354c.C2290a;
import com.tencent.open.p133a.C2333f;
import com.tencent.open.utils.Global;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.HttpUtils.HttpStatusException;
import com.tencent.open.utils.HttpUtils.NetworkUnavailableException;
import com.tencent.open.utils.Util;
import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import org.apache.http.conn.ConnectTimeoutException;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.json.JSONException;
import org.json.JSONObject;
import org.p122a.p123a.p152c.p156c.C2951i;

public class LocationApi extends BaseApi implements C2290a {
    private HandlerThread f11838a;
    private Handler f11839b;
    private Handler f11840c;
    private C2354c f11841d;
    private Bundle f11842e;
    private IUiListener f11843f;

    /* renamed from: com.tencent.open.LocationApi.1 */
    class C22851 extends Handler {
        final /* synthetic */ LocationApi f11830a;

        C22851(LocationApi locationApi, Looper looper) {
            this.f11830a = locationApi;
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case Opcodes.LSUB /*101*/:
                    C2333f.m13754b(C2333f.f12014d, "location: get location timeout.");
                    this.f11830a.m13586a(-13, Constants.MSG_LOCATION_TIMEOUT_ERROR);
                    break;
                case Opcodes.DSUB /*103*/:
                    C2333f.m13754b(C2333f.f12014d, "location: verify sosocode success.");
                    this.f11830a.f11841d.m13808a(Global.getContext(), this.f11830a);
                    this.f11830a.f11840c.sendEmptyMessageDelayed(Opcodes.LSUB, 10000);
                    break;
                case Opcodes.IMUL /*104*/:
                    C2333f.m13754b(C2333f.f12014d, "location: verify sosocode failed.");
                    this.f11830a.m13586a(-14, Constants.MSG_LOCATION_VERIFY_ERROR);
                    break;
            }
            super.handleMessage(message);
        }
    }

    /* renamed from: com.tencent.open.LocationApi.2 */
    class C22862 implements Runnable {
        final /* synthetic */ LocationApi f11831a;

        C22862(LocationApi locationApi) {
            this.f11831a = locationApi;
        }

        public void run() {
            if (this.f11831a.f11841d.m13809a()) {
                Message.obtain(this.f11831a.f11840c, Opcodes.DSUB).sendToTarget();
            } else {
                Message.obtain(this.f11831a.f11840c, Opcodes.IMUL).sendToTarget();
            }
        }
    }

    /* renamed from: com.tencent.open.LocationApi.3 */
    class C22873 implements Runnable {
        final /* synthetic */ String[] f11832a;
        final /* synthetic */ String f11833b;
        final /* synthetic */ LocationApi f11834c;

        C22873(LocationApi locationApi, String[] strArr, String str) {
            this.f11834c = locationApi;
            this.f11832a = strArr;
            this.f11833b = str;
        }

        public void run() {
            if (this.f11832a != null && this.f11832a.length != 0) {
                C2186a.m13215a(Global.getContext(), this.f11834c.mToken, "search_nearby".equals(this.f11833b) ? "id_search_nearby" : "id_delete_location", this.f11832a);
            }
        }
    }

    /* renamed from: com.tencent.open.LocationApi.a */
    abstract class C2288a implements IRequestListener {
        final /* synthetic */ LocationApi f11835a;

        private C2288a(LocationApi locationApi) {
            this.f11835a = locationApi;
        }

        protected abstract void m13582a(Exception exception);

        public void onConnectTimeoutException(ConnectTimeoutException connectTimeoutException) {
            m13582a(connectTimeoutException);
        }

        public void onHttpStatusException(HttpStatusException httpStatusException) {
            m13582a(httpStatusException);
        }

        public void onIOException(IOException iOException) {
            m13582a(iOException);
        }

        public void onJSONException(JSONException jSONException) {
            m13582a(jSONException);
        }

        public void onMalformedURLException(MalformedURLException malformedURLException) {
            m13582a(malformedURLException);
        }

        public void onNetworkUnavailableException(NetworkUnavailableException networkUnavailableException) {
            m13582a(networkUnavailableException);
        }

        public void onSocketTimeoutException(SocketTimeoutException socketTimeoutException) {
            m13582a(socketTimeoutException);
        }

        public void onUnknowException(Exception exception) {
            m13582a(exception);
        }
    }

    /* renamed from: com.tencent.open.LocationApi.b */
    class C2289b extends C2288a {
        final /* synthetic */ LocationApi f11836b;
        private IUiListener f11837c;

        public C2289b(LocationApi locationApi, IUiListener iUiListener) {
            this.f11836b = locationApi;
            super(null);
            this.f11837c = iUiListener;
        }

        protected void m13583a(Exception exception) {
            if (this.f11837c != null) {
                this.f11837c.onError(new UiError(100, exception.getMessage(), null));
            }
        }

        public void onComplete(JSONObject jSONObject) {
            if (this.f11837c != null) {
                this.f11837c.onComplete(jSONObject);
            }
            C2333f.m13754b("SDKQQAgentPref", "GetNearbySwitchEnd:" + SystemClock.elapsedRealtime());
        }
    }

    public LocationApi(QQAuth qQAuth, QQToken qQToken) {
        super(qQAuth, qQToken);
        m13585a();
    }

    public LocationApi(QQToken qQToken) {
        super(qQToken);
        m13585a();
    }

    private void m13585a() {
        this.f11841d = new C2354c();
        this.f11838a = new HandlerThread("get_location");
        this.f11838a.start();
        this.f11839b = new Handler(this.f11838a.getLooper());
        this.f11840c = new C22851(this, Global.getContext().getMainLooper());
    }

    private void m13586a(int i, String str) {
        this.f11841d.m13810b();
        if (this.f11843f != null) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("ret", i);
                jSONObject.put("errMsg", str);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.f11843f.onComplete(jSONObject);
        }
    }

    private void m13587a(Location location) {
        Bundle bundle;
        C2333f.m13754b(C2333f.f12014d, "location: search mParams: " + this.f11842e);
        if (this.f11842e != null) {
            bundle = new Bundle(this.f11842e);
            bundle.putAll(composeCGIParams());
        } else {
            bundle = composeCGIParams();
        }
        String valueOf = String.valueOf(location.getLatitude());
        String valueOf2 = String.valueOf(location.getLongitude());
        bundle.putString(SocialConstants.PARAM_APP_ID, this.mToken.getAppId());
        if (!bundle.containsKey("latitude")) {
            bundle.putString("latitude", valueOf);
        }
        if (!bundle.containsKey("longitude")) {
            bundle.putString("longitude", valueOf2);
        }
        if (!bundle.containsKey("page")) {
            bundle.putString("page", String.valueOf(1));
        }
        valueOf2 = "encrytoken";
        bundle.putString(valueOf2, Util.encrypt("tencent&sdk&qazxc***14969%%" + this.mToken.getAccessToken() + this.mToken.getAppId() + this.mToken.getOpenId() + "qzone3.4"));
        C2333f.m13754b(C2333f.f12014d, "location: search params: " + bundle);
        C2333f.m13754b("SDKQQAgentPref", "GetNearbySwitchStart:" + SystemClock.elapsedRealtime());
        HttpUtils.requestAsync(this.mToken, Global.getContext(), "http://fusion.qq.com/cgi-bin/qzapps/mapp_lbs_getnear.cgi", bundle, C2951i.f14860a, new C2289b(this, this.f11843f));
    }

    private void m13589a(String str, String... strArr) {
        this.f11839b.post(new C22873(this, strArr, str));
    }

    private void m13591b() {
        this.f11841d.m13810b();
    }

    private boolean m13593c() {
        ConnectivityManager connectivityManager = (ConnectivityManager) Global.getContext().getSystemService("connectivity");
        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable();
    }

    private JSONObject m13594d() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ret", -9);
            jSONObject.put("errMsg", Constants.MSG_IO_ERROR);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public void deleteLocation(Activity activity, Bundle bundle, IUiListener iUiListener) {
        if (m13593c()) {
            Bundle bundle2;
            if (bundle != null) {
                bundle2 = new Bundle(bundle);
                bundle2.putAll(composeCGIParams());
            } else {
                bundle2 = composeCGIParams();
            }
            bundle2.putString(SocialConstants.PARAM_APP_ID, this.mToken.getAppId());
            bundle2.putString("timestamp", String.valueOf(System.currentTimeMillis()));
            String str = "encrytoken";
            bundle2.putString(str, Util.encrypt("tencent&sdk&qazxc***14969%%" + this.mToken.getAccessToken() + this.mToken.getAppId() + this.mToken.getOpenId() + "qzone3.4"));
            C2333f.m13754b(C2333f.f12014d, "location: delete params: " + bundle2);
            HttpUtils.requestAsync(this.mToken, Global.getContext(), "http://fusion.qq.com/cgi-bin/qzapps/mapp_lbs_delete.cgi", bundle2, C2951i.f14860a, new C2289b(this, iUiListener));
            m13589a("delete_location", "success");
        } else if (iUiListener != null) {
            iUiListener.onComplete(m13594d());
        }
    }

    public void onLocationUpdate(Location location) {
        m13587a(location);
        m13591b();
        this.f11840c.removeMessages(Opcodes.LSUB);
    }

    public void searchNearby(Activity activity, Bundle bundle, IUiListener iUiListener) {
        if (m13593c()) {
            this.f11842e = bundle;
            this.f11843f = iUiListener;
            this.f11839b.post(new C22862(this));
        } else if (iUiListener != null) {
            iUiListener.onComplete(m13594d());
        }
    }
}
