package com.fimi.soul.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;

public abstract class NetworkStateReceiver extends BroadcastReceiver {
    public static final String f9876a = "android.net.conn.CONNECTIVITY_CHANGE";

    private boolean m12097a(State state) {
        return state != null && State.CONNECTED == state;
    }

    public abstract void m12098a(C1933a c1933a, Context context);

    public void onReceive(Context context, Intent intent) {
        if (f9876a.equals(intent.getAction())) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            State state = connectivityManager.getNetworkInfo(1).getState();
            if (connectivityManager.getNetworkInfo(0) != null) {
                State state2 = connectivityManager.getNetworkInfo(0).getState();
                if (m12097a(state)) {
                    m12098a(C1933a.Wifi, context);
                } else if (m12097a(state2)) {
                    m12098a(C1933a.Mobile, context);
                } else {
                    m12098a(C1933a.None, context);
                }
            }
        }
    }
}
