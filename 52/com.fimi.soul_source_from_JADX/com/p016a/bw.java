package com.p016a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.fimi.soul.receiver.NetworkStateReceiver;
import java.util.ArrayList;
import java.util.Collection;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.a.bw */
class bw extends BroadcastReceiver {
    final /* synthetic */ bs f703a;

    private bw(bs bsVar) {
        this.f703a = bsVar;
    }

    public void onReceive(Context context, Intent intent) {
        if (context != null && intent != null) {
            try {
                String action = intent.getAction();
                if (!TextUtils.isEmpty(action)) {
                    cr a = this.f703a.f687o;
                    if (action.equals("android.net.wifi.SCAN_RESULTS")) {
                        if (a != null) {
                            Collection collection = null;
                            collection = a.m1365a();
                            if (collection != null) {
                                synchronized (this.f703a.f680h) {
                                    this.f703a.f690r.clear();
                                    this.f703a.f690r.addAll(collection);
                                }
                            }
                            this.f703a.f651D = dn.m1519b();
                        }
                    } else if (action.equals("android.net.wifi.WIFI_STATE_CHANGED")) {
                        if (this.f703a.f687o != null) {
                            int i = 4;
                            try {
                                i = a.m1370c();
                            } catch (Throwable th) {
                                ev.m1777a(th, "APS", "onReceive part");
                            }
                            if (this.f703a.f690r == null) {
                                this.f703a.f690r = new ArrayList();
                            }
                            switch (i) {
                                case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                                    this.f703a.m1238n();
                                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                                    this.f703a.m1238n();
                                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                                    this.f703a.m1238n();
                                default:
                            }
                        }
                    } else if (action.equals("android.intent.action.SCREEN_ON")) {
                        this.f703a.f683k = true;
                    } else if (action.equals("android.intent.action.SCREEN_OFF")) {
                        this.f703a.f683k = false;
                        if (this.f703a.f659L != null) {
                            this.f703a.m1245u();
                        }
                    } else if (!action.equals("android.intent.action.AIRPLANE_MODE") && !action.equals("android.location.GPS_FIX_CHANGE") && action.equals(NetworkStateReceiver.f9876a) && this.f703a.m1186A()) {
                        this.f703a.m1213a(true, 2);
                    }
                }
            } catch (Throwable th2) {
                ev.m1777a(th2, "APS", "onReceive");
            }
        }
    }
}
