package cn.sharesdk.sina.weibo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.os.IBinder;
import android.text.TextUtils;
import cn.sharesdk.framework.authorize.C0158e;
import cn.sharesdk.framework.authorize.C0159f;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.lang.reflect.Method;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: cn.sharesdk.sina.weibo.f */
public class C0216f extends C0159f implements ServiceConnection {
    private String f402d;
    private String f403e;
    private String[] f404f;

    public C0216f(C0158e c0158e) {
        super(c0158e);
    }

    private boolean m795a(String str, String str2) {
        boolean z = true;
        Intent intent = new Intent();
        intent.setClassName(str, str2);
        intent.putExtra("appKey", this.f402d);
        intent.putExtra("redirectUri", this.f403e);
        if (this.f404f != null && this.f404f.length > 0) {
            intent.putExtra(XiaomiOAuthConstants.EXTRA_SCOPE_2, TextUtils.join(MiPushClient.ACCEPT_TIME_SEPARATOR, this.f404f));
        }
        if (!m796b(intent)) {
            return false;
        }
        try {
            this.a.startActivityForResult(intent, this.b);
        } catch (Throwable th) {
            z = false;
        }
        this.a.getContext().getApplicationContext().unbindService(this);
        return z;
    }

    private boolean m796b(Intent intent) {
        ResolveInfo resolveActivity = this.a.getContext().getPackageManager().resolveActivity(intent, 0);
        if (resolveActivity == null) {
            return false;
        }
        try {
            for (Signature toCharsString : this.a.getContext().getPackageManager().getPackageInfo(resolveActivity.activityInfo.packageName, 64).signatures) {
                if ("30820295308201fea00302010202044b4ef1bf300d06092a864886f70d010105050030818d310b300906035504061302434e3110300e060355040813074265694a696e673110300e060355040713074265694a696e67312c302a060355040a132353696e612e436f6d20546563686e6f6c6f677920284368696e612920436f2e204c7464312c302a060355040b132353696e612e436f6d20546563686e6f6c6f677920284368696e612920436f2e204c74643020170d3130303131343130323831355a180f32303630303130323130323831355a30818d310b300906035504061302434e3110300e060355040813074265694a696e673110300e060355040713074265694a696e67312c302a060355040a132353696e612e436f6d20546563686e6f6c6f677920284368696e612920436f2e204c7464312c302a060355040b132353696e612e436f6d20546563686e6f6c6f677920284368696e612920436f2e204c746430819f300d06092a864886f70d010101050003818d00308189028181009d367115bc206c86c237bb56c8e9033111889b5691f051b28d1aa8e42b66b7413657635b44786ea7e85d451a12a82a331fced99c48717922170b7fc9bc1040753c0d38b4cf2b22094b1df7c55705b0989441e75913a1a8bd2bc591aa729a1013c277c01c98cbec7da5ad7778b2fad62b85ac29ca28ced588638c98d6b7df5a130203010001300d06092a864886f70d0101050500038181000ad4b4c4dec800bd8fd2991adfd70676fce8ba9692ae50475f60ec468d1b758a665e961a3aedbece9fd4d7ce9295cd83f5f19dc441a065689d9820faedbb7c4a4c4635f5ba1293f6da4b72ed32fb8795f736a20c95cda776402099054fccefb4a1a558664ab8d637288feceba9508aa907fc1fe2b1ae5a0dec954ed831c0bea4".equals(toCharsString.toCharsString())) {
                    return true;
                }
            }
            return false;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    private void m797c(Intent intent) {
        if (this.c != null) {
            String stringExtra = intent.getStringExtra(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2);
            if (stringExtra == null) {
                stringExtra = intent.getStringExtra("error_type");
            }
            if (stringExtra == null) {
                this.c.onComplete(intent.getExtras());
            } else if (stringExtra.equals("access_denied") || stringExtra.equals("OAuthAccessDeniedException")) {
                this.c.onCancel();
            } else {
                String stringExtra2 = intent.getStringExtra(XiaomiOAuthConstants.EXTRA_ERROR_DESCRIPTION_2);
                if (stringExtra2 != null) {
                    stringExtra = stringExtra + ": " + stringExtra2;
                }
                this.c.onFailed(new Throwable(stringExtra));
            }
        }
    }

    private void m798d(Intent intent) {
        if (intent != null) {
            String stringExtra = intent.getStringExtra(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2);
            Throwable th = new Throwable(stringExtra + " (" + intent.getIntExtra("error_code", -1) + ")");
            if (this.c != null) {
                this.c.onFailed(th);
            }
        } else if (this.c != null) {
            this.c.onCancel();
        }
    }

    public void m799a() {
        Intent intent = new Intent();
        intent.setClassName("com.sina.weibo", "com.sina.weibo.business.RemoteSSOService");
        if (!this.a.getContext().getApplicationContext().bindService(intent, this, 1)) {
            this.a.finish();
            if (this.c != null) {
                this.c.onFailed(new Throwable());
            }
        }
    }

    public void m800a(int i, int i2, Intent intent) {
        this.a.finish();
        if (i == this.b) {
            switch (i2) {
                case Opcodes.F_NEW /*-1*/:
                    m797c(intent);
                case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                    m798d(intent);
                default:
            }
        }
    }

    public void m801a(String str, String str2, String[] strArr) {
        this.f402d = str;
        this.f403e = str2;
        this.f404f = strArr;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        try {
            Class cls = Class.forName("com.sina.sso.RemoteSSO$Stub");
            Method method = cls.getMethod("asInterface", new Class[]{IBinder.class});
            method.setAccessible(true);
            Object invoke = method.invoke(null, new Object[]{iBinder});
            Method method2 = cls.getMethod("getPackageName", new Class[0]);
            method2.setAccessible(true);
            String valueOf = String.valueOf(method2.invoke(invoke, new Object[0]));
            Method method3 = cls.getMethod("getActivityName", new Class[0]);
            method3.setAccessible(true);
            if (!m795a(valueOf, String.valueOf(method3.invoke(invoke, new Object[0])))) {
                this.a.finish();
                if (this.c != null) {
                    this.c.onFailed(new Throwable());
                }
            }
        } catch (Throwable th) {
            this.a.finish();
            if (this.c != null) {
                this.c.onFailed(th);
            }
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        this.a.finish();
        if (this.c != null) {
            this.c.onFailed(new Throwable());
        }
    }
}
