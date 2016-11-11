package cn.sharesdk.framework.p013b;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import cn.sharesdk.framework.p013b.p014a.C0169e;
import cn.sharesdk.framework.p013b.p015b.C0171c;
import cn.sharesdk.framework.p013b.p015b.C0175e;
import cn.sharesdk.framework.p013b.p015b.C0178g;
import cn.sharesdk.framework.utils.C0205d;
import com.hoho.android.usbserial.driver.UsbId;
import com.mob.commons.appcollector.PackageCollector;
import com.mob.commons.appcollector.RuntimeCollector;
import com.mob.tools.SSDKHandlerThread;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.FileLocker;
import com.tencent.mm.sdk.platformtools.Util;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import java.io.File;
import java.util.Calendar;
import java.util.Random;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: cn.sharesdk.framework.b.e */
public class C0182e extends SSDKHandlerThread {
    private static C0182e f291a;
    private Context f292b;
    private DeviceHelper f293c;
    private C0170a f294d;
    private String f295e;
    private Handler f296f;
    private boolean f297g;
    private int f298h;
    private boolean f299i;
    private long f300j;
    private boolean f301k;
    private File f302l;
    private FileLocker f303m;

    private C0182e(Context context, String str) {
        super("Thread-" + Math.abs(-12504));
        this.f292b = context;
        this.f295e = str;
        this.f293c = DeviceHelper.getInstance(context);
        this.f294d = C0170a.m499a(context, str);
        this.f303m = new FileLocker();
        this.f302l = new File(context.getFilesDir(), ".statistics");
        if (!this.f302l.exists()) {
            try {
                this.f302l.createNewFile();
            } catch (Throwable e) {
                C0205d.m752a().m738d(e);
            }
        }
    }

    public static synchronized C0182e m613a(Context context, String str) {
        C0182e c0182e = null;
        synchronized (C0182e.class) {
            if (f291a == null) {
                if (context != null) {
                    if (!TextUtils.isEmpty(str)) {
                        f291a = new C0182e(context.getApplicationContext(), str);
                    }
                }
            }
            c0182e = f291a;
        }
        return c0182e;
    }

    private void m614a() {
        boolean b = m616b();
        if (b) {
            if (!this.f301k) {
                this.f301k = b;
                this.f300j = System.currentTimeMillis();
                m622a(new C0178g());
            }
        } else if (this.f301k) {
            this.f301k = b;
            long currentTimeMillis = System.currentTimeMillis() - this.f300j;
            C0171c c0175e = new C0175e();
            c0175e.f259a = currentTimeMillis;
            m622a(c0175e);
        }
    }

    private void m615b(C0171c c0171c) {
        c0171c.f234f = this.f293c.getDeviceKey();
        c0171c.f235g = this.f295e;
        c0171c.f236h = this.f293c.getPackageName();
        c0171c.f237i = this.f293c.getAppVersion();
        c0171c.f238j = String.valueOf(UsbId.SILAB_CP2102 + this.f298h);
        c0171c.f239k = this.f293c.getPlatformCode();
        c0171c.f240l = this.f293c.getDetailNetworkTypeForStatic();
        if (TextUtils.isEmpty(this.f295e)) {
            System.err.println("Your appKey of ShareSDK is null , this will cause its data won't be count!");
        } else if (!"cn.sharesdk.demo".equals(c0171c.f236h) && ("api20".equals(this.f295e) || "androidv1101".equals(this.f295e))) {
            System.err.println("Your app is using the appkey of ShareSDK Demo, this will cause its data won't be count!");
        }
        c0171c.f241m = this.f293c.getDeviceData();
    }

    private boolean m616b() {
        DeviceHelper instance = DeviceHelper.getInstance(this.f292b);
        String topTaskPackageName = instance.getTopTaskPackageName();
        String packageName = instance.getPackageName();
        return packageName != null && packageName.equals(topTaskPackageName);
    }

    private void m617c() {
        try {
            this.f294d.m520e();
        } catch (Throwable th) {
            C0205d.m752a().m738d(th);
        }
    }

    private void m618c(C0171c c0171c) {
        try {
            this.f294d.m512a(c0171c);
            c0171c.m528b(this.f292b);
        } catch (Throwable th) {
            C0205d.m752a().m738d(th);
            C0205d.m752a().m737d(c0171c.toString(), new Object[0]);
        }
    }

    private void m619d() {
        int nextInt = new Random().nextInt(Opcodes.ISHL) + 60;
        C0205d.m752a().m737d("sendUploadCellInfoMsg deley second == " + nextInt, new Object[0]);
        this.handler.sendEmptyMessageDelayed(5, (long) (nextInt * XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER));
    }

    public void m620a(int i) {
        this.f298h = i;
    }

    public void m621a(Handler handler) {
        this.f296f = handler;
    }

    public void m622a(C0171c c0171c) {
        if (this.f299i) {
            m615b(c0171c);
            if (c0171c.m526a(this.f292b)) {
                Message message = new Message();
                message.what = 3;
                message.obj = c0171c;
                try {
                    this.handler.sendMessage(message);
                    return;
                } catch (Throwable th) {
                    C0205d.m752a().m738d(th);
                    return;
                }
            }
            C0205d.m752a().m737d("Drop event: " + c0171c.toString(), new Object[0]);
        }
    }

    public void m623a(boolean z) {
        this.f297g = z;
    }

    protected void onMessage(Message message) {
        int i;
        int i2;
        switch (message.what) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                m614a();
                try {
                    this.handler.sendEmptyMessageDelayed(1, 1000);
                } catch (Throwable th) {
                    C0205d.m752a().m738d(th);
                }
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                m617c();
                try {
                    this.handler.sendEmptyMessageDelayed(2, 10000);
                } catch (Throwable th2) {
                    C0205d.m752a().m738d(th2);
                }
            case Type.BYTE /*3*/:
                if (message.obj != null) {
                    m618c((C0171c) message.obj);
                }
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                long longValue = C0169e.m467a(this.f292b).m491h().longValue();
                Calendar instance = Calendar.getInstance();
                instance.setTimeInMillis(longValue);
                int i3 = instance.get(1);
                int i4 = instance.get(2);
                i = instance.get(5);
                instance.setTimeInMillis(System.currentTimeMillis());
                int i5 = instance.get(1);
                int i6 = instance.get(2);
                i2 = instance.get(5);
                if (i3 == i5 && i4 == i6 && i == i2) {
                    this.f294d.m518c();
                } else {
                    this.f294d.m517b();
                }
                this.handler.sendEmptyMessageDelayed(4, Util.MILLSECONDS_OF_HOUR);
            case Type.INT /*5*/:
                C0169e a = C0169e.m467a(this.f292b);
                long i7 = a.m493i("appFirstInitSDKTime");
                long currentTimeMillis = System.currentTimeMillis();
                if (i7 == 0) {
                    a.m472a("appFirstInitSDKTime", Long.valueOf(currentTimeMillis));
                } else if (currentTimeMillis - i7 >= 259200000) {
                    String h = a.m492h("appCellIdAndLacInfo");
                    i2 = this.f293c.getCellId();
                    i = this.f293c.getCellLac();
                    if (i2 != -1 && i != -1) {
                        String str = i2 + "_" + i;
                        C0205d.m752a().m737d("curCellInfo == " + str, new Object[0]);
                        if (!str.equals(h) && this.f294d.m515a(i2, i)) {
                            a.m478b("appCellIdAndLacInfo", str);
                        }
                    }
                }
            default:
        }
    }

    protected void onStart(Message message) {
        if (!this.f299i) {
            this.f299i = true;
            try {
                this.f303m.setLockFile(this.f302l.getAbsolutePath());
                if (this.f303m.lock(false)) {
                    this.f294d.m511a();
                    this.f294d.m517b();
                    this.f294d.m518c();
                    this.f294d.m519d();
                    this.f294d.m523h();
                    this.handler.sendEmptyMessageDelayed(4, Util.MILLSECONDS_OF_HOUR);
                    this.f294d.m514a(this.f297g);
                    this.handler.sendEmptyMessage(1);
                    this.handler.sendEmptyMessage(2);
                    m619d();
                    PackageCollector.register(this.f292b, this.f295e);
                    RuntimeCollector.startCollector(this.f292b, this.f295e);
                }
            } catch (Throwable th) {
                C0205d.m752a().m738d(th);
            }
        }
    }

    protected void onStop(Message message) {
        if (this.f299i) {
            long currentTimeMillis = System.currentTimeMillis() - this.f300j;
            C0171c c0175e = new C0175e();
            c0175e.f259a = currentTimeMillis;
            m622a(c0175e);
            this.f299i = false;
            try {
                this.f296f.sendEmptyMessage(1);
            } catch (Throwable th) {
                C0205d.m752a().m738d(th);
            }
            f291a = null;
            this.handler.getLooper().quit();
        }
    }
}
