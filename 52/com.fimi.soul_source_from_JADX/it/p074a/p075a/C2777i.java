package it.p074a.p075a;

import android.support.v4.view.accessibility.AccessibilityEventCompat;
import com.tencent.connect.common.Constants;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import java.net.InetSocketAddress;
import java.net.Socket;

/* renamed from: it.a.a.i */
public abstract class C2777i {
    protected int f14160a;
    protected int f14161b;
    protected int f14162c;
    private boolean f14163d;
    private Socket f14164e;

    protected C2777i() {
        this(false);
    }

    protected C2777i(boolean z) {
        this.f14160a = 10;
        this.f14161b = 10;
        this.f14162c = 10;
        String property = System.getProperty(C2806q.f14307d);
        if ("true".equalsIgnoreCase(property) || "yes".equalsIgnoreCase(property) || Constants.VIA_TO_TYPE_QQ_GROUP.equals(property)) {
            this.f14163d = true;
        } else if ("false".equalsIgnoreCase(property) || "no".equalsIgnoreCase(property) || Constants.VIA_RESULT_SUCCESS.equals(property)) {
            this.f14163d = false;
        } else {
            this.f14163d = z;
        }
    }

    protected Socket m15832a(String str, int i) {
        try {
            this.f14164e = new Socket();
            this.f14164e.setKeepAlive(true);
            this.f14164e.setSoTimeout(this.f14161b * XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER);
            this.f14164e.setSoLinger(true, this.f14162c);
            this.f14164e.connect(new InetSocketAddress(str, i), this.f14160a * XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER);
            Socket socket = this.f14164e;
            return socket;
        } finally {
            this.f14164e = null;
        }
    }

    public void m15833a(int i) {
        this.f14160a = i;
    }

    public void m15834a(boolean z) {
        this.f14163d = z;
    }

    boolean m15835a() {
        return this.f14163d;
    }

    protected Socket m15836b(String str, int i) {
        Socket socket = new Socket();
        socket.setSoTimeout(this.f14161b * XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER);
        socket.setSoLinger(true, this.f14162c);
        socket.setReceiveBufferSize(AccessibilityEventCompat.TYPE_GESTURE_DETECTION_END);
        socket.setSendBufferSize(AccessibilityEventCompat.TYPE_GESTURE_DETECTION_END);
        socket.connect(new InetSocketAddress(str, i), this.f14160a * XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER);
        return socket;
    }

    public void m15837b() {
        if (this.f14164e != null) {
            try {
                this.f14164e.close();
            } catch (Throwable th) {
            }
        }
    }

    public void m15838b(int i) {
        this.f14161b = i;
    }

    public abstract Socket m15839c(String str, int i);

    public void m15840c(int i) {
        this.f14162c = i;
    }

    public abstract Socket m15841d(String str, int i);
}
