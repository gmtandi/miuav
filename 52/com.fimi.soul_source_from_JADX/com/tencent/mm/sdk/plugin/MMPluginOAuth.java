package com.tencent.mm.sdk.plugin;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.tencent.mm.sdk.platformtools.Log;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.OAuth;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.PluginIntent;
import java.util.HashMap;
import java.util.Map;

public class MMPluginOAuth {
    private final Context f11810R;
    private final IResult bA;
    private String bB;
    private String bC;
    private Handler handler;

    /* renamed from: com.tencent.mm.sdk.plugin.MMPluginOAuth.1 */
    class C22761 implements Runnable {
        final /* synthetic */ MMPluginOAuth bD;

        C22761(MMPluginOAuth mMPluginOAuth) {
            this.bD = mMPluginOAuth;
        }

        public void run() {
            if (this.bD.bA != null) {
                this.bD.bA.onResult(this.bD);
            }
        }
    }

    public interface IResult {
        void onResult(MMPluginOAuth mMPluginOAuth);

        void onSessionTimeOut();
    }

    public class Receiver extends BroadcastReceiver {
        private static final Map<String, MMPluginOAuth> aA;
        private final MMPluginOAuth bE;

        /* renamed from: com.tencent.mm.sdk.plugin.MMPluginOAuth.Receiver.1 */
        class C22771 implements Runnable {
            final /* synthetic */ MMPluginOAuth bF;
            final /* synthetic */ String bG;
            final /* synthetic */ Receiver bH;

            C22771(Receiver receiver, MMPluginOAuth mMPluginOAuth, String str) {
                this.bH = receiver;
                this.bF = mMPluginOAuth;
                this.bG = str;
            }

            public void run() {
                MMPluginOAuth.m13567a(this.bF, this.bG);
            }
        }

        static {
            aA = new HashMap();
        }

        public Receiver() {
            this(null);
        }

        public Receiver(MMPluginOAuth mMPluginOAuth) {
            this.bE = mMPluginOAuth;
        }

        public static void register(String str, MMPluginOAuth mMPluginOAuth) {
            aA.put(str, mMPluginOAuth);
        }

        public static void unregister(String str) {
            aA.remove(str);
        }

        public void onReceive(Context context, Intent intent) {
            MMPluginOAuth mMPluginOAuth;
            Log.m13539d("MicroMsg.SDK.MMPluginOAuth", "receive oauth result");
            String stringExtra = intent.getStringExtra(PluginIntent.REQUEST_TOKEN);
            String stringExtra2 = intent.getStringExtra(PluginIntent.ACCESS_TOKEN);
            if (this.bE != null) {
                mMPluginOAuth = this.bE;
            } else {
                mMPluginOAuth = (MMPluginOAuth) aA.get(stringExtra);
                if (mMPluginOAuth == null) {
                    Log.m13541e("MicroMsg.SDK.MMPluginOAuth", "oauth unregistered, request token = " + stringExtra);
                    return;
                }
                unregister(mMPluginOAuth.bC);
            }
            new Handler().post(new C22771(this, mMPluginOAuth, stringExtra2));
        }
    }

    public MMPluginOAuth(Context context, IResult iResult) {
        this.f11810R = context;
        this.bA = iResult;
    }

    static /* synthetic */ void m13567a(MMPluginOAuth mMPluginOAuth, String str) {
        Receiver.unregister(mMPluginOAuth.bC);
        mMPluginOAuth.bB = str;
        Log.m13545i("MicroMsg.SDK.MMPluginOAuth", "access token: " + str);
        if (mMPluginOAuth.bA != null) {
            mMPluginOAuth.bA.onResult(mMPluginOAuth);
        }
    }

    public String getAccessToken() {
        return this.bB;
    }

    public String getRequestToken() {
        return this.bC;
    }

    public void start() {
        start(null);
    }

    public boolean start(Handler handler) {
        if (handler == null) {
            handler = new Handler();
        }
        this.handler = handler;
        Cursor query = this.f11810R.getContentResolver().query(OAuth.CONTENT_URI, null, null, new String[]{this.f11810R.getPackageName(), OAuth.ACTION_REQUEST_TOKEN}, null);
        if (query != null) {
            if (query.moveToFirst() && query.getColumnCount() >= 2) {
                this.bC = query.getString(0);
                this.bB = query.getString(1);
            }
            query.close();
        }
        Log.m13545i("MicroMsg.SDK.MMPluginOAuth", "request token = " + this.bC);
        if (this.bC == null) {
            Log.m13541e("MicroMsg.SDK.MMPluginOAuth", "request token failed");
            return false;
        } else if (this.bB != null) {
            this.handler.post(new C22761(this));
            return true;
        } else {
            int i;
            Log.m13539d("MicroMsg.SDK.MMPluginOAuth", "begin to show user oauth page");
            Intent intent = new Intent();
            intent.setClassName(PluginIntent.APP_PACKAGE_PATTERN, "com.tencent.mm.plugin.PluginOAuthUI");
            intent.putExtra(PluginIntent.REQUEST_TOKEN, this.bC);
            intent.putExtra(PluginIntent.PACKAGE, this.f11810R.getPackageName());
            if (this.f11810R.getPackageManager().resolveActivity(intent, AccessibilityNodeInfoCompat.ACTION_CUT) == null) {
                Log.m13541e("MicroMsg.SDK.MMPluginOAuth", "show oauth page failed, activity not found");
                i = 0;
            } else {
                if (!(this.f11810R instanceof Activity)) {
                    intent.setFlags(268435456);
                }
                this.f11810R.startActivity(intent);
                i = 1;
            }
            if (i == 0) {
                return false;
            }
            Receiver.register(this.bC, this);
            return true;
        }
    }
}
