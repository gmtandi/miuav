package com.tencent.connect.dataprovider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import com.fimi.soul.media.player.FimiMediaMeta;
import com.tencent.connect.dataprovider.DataType.TextAndMediaPath;
import com.tencent.connect.dataprovider.DataType.TextOnly;
import java.io.File;
import java.lang.ref.WeakReference;

public final class CallbackManager {
    private WeakReference<Context> f11484a;
    private Uri f11485b;
    private String f11486c;
    private String f11487d;
    private String f11488e;
    private String f11489f;
    private boolean f11490g;
    private int f11491h;

    public CallbackManager(Activity activity) {
        this.f11490g = false;
        this.f11484a = new WeakReference(activity.getApplicationContext());
        Intent intent = activity.getIntent();
        if (intent != null) {
            this.f11485b = intent.getData();
            this.f11486c = intent.getStringExtra(Constants.SRC_PACKAGE_NAME);
            this.f11487d = intent.getStringExtra(Constants.SRC_ACTIVITY_CLASS_NAME);
            this.f11488e = intent.getStringExtra(Constants.SRC_ACTIVITY_ACTION);
            this.f11491h = intent.getIntExtra(Constants.REQUEST_TYPE, 0);
            this.f11489f = intent.getStringExtra(Constants.APPID);
            if (this.f11485b != null && this.f11487d != null) {
                this.f11490g = true;
            }
        }
    }

    private int m13325a(Bundle bundle) {
        if (!this.f11490g) {
            return -2;
        }
        Intent intent = new Intent();
        intent.setClassName(this.f11486c, this.f11487d);
        intent.setAction(this.f11488e);
        bundle.putString(Constants.APPID, this.f11489f);
        intent.putExtras(bundle);
        intent.setFlags(268435456);
        ((Context) this.f11484a.get()).startActivity(intent);
        return 0;
    }

    private int m13326a(String str) {
        if (str == null) {
            return -7;
        }
        String toLowerCase = str.toLowerCase();
        if (toLowerCase.startsWith("http://")) {
            return 0;
        }
        if (!Environment.getExternalStorageState().equals("mounted")) {
            return -10;
        }
        if (!toLowerCase.startsWith(Environment.getExternalStorageDirectory().toString().toLowerCase())) {
            return -5;
        }
        File file = new File(str);
        if (!file.exists() || file.isDirectory()) {
            return -8;
        }
        long length = file.length();
        return length == 0 ? -9 : length > FimiMediaMeta.AV_CH_STEREO_RIGHT ? -6 : 0;
    }

    public int getRequestDateTypeFlag() {
        return this.f11491h;
    }

    public boolean isCallFromTencentApp() {
        return this.f11490g;
    }

    public boolean isSupportType(int i) {
        return (getRequestDateTypeFlag() & i) != 0;
    }

    public int sendTextAndImagePath(String str, String str2) {
        if (!isSupportType(1)) {
            return -1;
        }
        int a = m13326a(str2);
        if (a != 0) {
            return a;
        }
        Parcelable textAndMediaPath = new TextAndMediaPath(str, str2);
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.DATA_TYPE, 1);
        bundle.putParcelable(Constants.CONTENT_DATA, textAndMediaPath);
        return m13325a(bundle);
    }

    public int sendTextAndVideoPath(String str, String str2) {
        if (!isSupportType(2)) {
            return -1;
        }
        int a = m13326a(str2);
        if (a != 0) {
            return a;
        }
        Parcelable textAndMediaPath = new TextAndMediaPath(str, str2);
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.DATA_TYPE, 2);
        bundle.putParcelable(Constants.CONTENT_DATA, textAndMediaPath);
        return m13325a(bundle);
    }

    public int sendTextOnly(String str) {
        if (!isSupportType(4)) {
            return -1;
        }
        Parcelable textOnly = new TextOnly(str);
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.DATA_TYPE, 4);
        bundle.putParcelable(Constants.CONTENT_DATA, textOnly);
        return m13325a(bundle);
    }
}
