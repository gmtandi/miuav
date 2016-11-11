package com.fimi.soul.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.RemoteViews;
import android.widget.Toast;
import com.fimi.soul.C1205R;
import com.fimi.soul.biz.p103k.am;
import com.fimi.soul.utils.C1971k;
import org.codehaus.jackson.util.MinimalPrettyPrinter;

public class UpdateApkService extends Service {
    private static final int f9951e = 8888;
    private static boolean f9952h;
    Handler f9953a;
    private am f9954b;
    private NotificationManager f9955c;
    private Notification f9956d;
    private final int f9957f;
    private final int f9958g;

    static {
        f9952h = false;
    }

    public UpdateApkService() {
        this.f9957f = 1;
        this.f9958g = 2;
        this.f9953a = new C1958y(this);
    }

    public void m12161a() {
        this.f9955c.notify(f9951e, this.f9956d);
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        this.f9954b = am.m9205a((Context) this);
        this.f9955c = (NotificationManager) getSystemService("notification");
        PendingIntent activity = PendingIntent.getActivity(this, 0, new Intent(), 0);
        this.f9956d = new Notification();
        this.f9956d.icon = C1205R.drawable.ic_launcher;
        this.f9956d.tickerText = getString(C1205R.string.down_ing) + getString(C1205R.string.app_name);
        this.f9956d.contentIntent = activity;
        this.f9956d.contentView = new RemoteViews(getPackageName(), C1205R.layout.up_notification);
        this.f9956d.contentView.setProgressBar(C1205R.id.download_bar, 100, 0, false);
        this.f9956d.contentView.setTextViewText(C1205R.id.text_download_title, getString(C1205R.string.down_ing) + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + getString(C1205R.string.app_name));
        this.f9956d.contentView.setTextViewText(C1205R.id.text_download_process, "0%");
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        if (!(intent == null || intent.getStringExtra("down_url") == null)) {
            m12161a();
            if (f9952h) {
                Toast.makeText(this, C1205R.string.apk_downing, 0).show();
            } else {
                new Thread(new C1971k(intent.getStringExtra("down_url"), this.f9953a)).start();
            }
        }
        return super.onStartCommand(intent, i, i2);
    }
}
