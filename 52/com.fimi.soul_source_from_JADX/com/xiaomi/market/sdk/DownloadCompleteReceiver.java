package com.xiaomi.market.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class DownloadCompleteReceiver extends BroadcastReceiver {
    private static final String TAG = "MarketSDKDownloadReceiver";

    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.DOWNLOAD_COMPLETE".equals(intent.getAction())) {
            long longExtra = intent.getLongExtra("extra_download_id", -1);
            Log.d(TAG, "on sdk download complete : id = " + longExtra);
            if (longExtra != -1) {
                C2540m.m14532i(context).m14533a(longExtra);
            }
        }
    }
}
