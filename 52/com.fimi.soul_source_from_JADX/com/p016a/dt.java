package com.p016a;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import com.tencent.connect.common.Constants;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.a.dt */
class dt extends Handler {
    dr f964a;
    final /* synthetic */ dr f965b;
    private boolean f966c;
    private boolean f967d;

    public dt(dr drVar, dr drVar2) {
        this.f965b = drVar;
        this.f964a = null;
        this.f966c = true;
        this.f967d = true;
        this.f964a = drVar2;
    }

    public void handleMessage(Message message) {
        try {
            switch (message.what) {
                case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                    synchronized (this.f965b.f948l) {
                        this.f965b.f960x = true;
                        this.f965b.f939c = message.replyTo;
                        this.f964a.f948l.notify();
                        break;
                    }
                    break;
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    try {
                        synchronized (this.f965b.f948l) {
                            this.f965b.f960x = true;
                            Bundle data = message.getData();
                            this.f965b.f943g = data.getBoolean("isfirst");
                            Messenger messenger = message.replyTo;
                            long b = dn.m1519b();
                            boolean z = data.getBoolean("isNeedAddress");
                            boolean z2 = data.getBoolean("isOffset");
                            if (!(z == this.f966c && z2 == this.f967d)) {
                                this.f964a.f950n = 0;
                            }
                            this.f966c = z;
                            this.f967d = z2;
                            if (this.f965b.f949m == null || this.f965b.f949m.m5310a() != 0 || b - this.f964a.f950n >= 800) {
                                if (!this.f965b.f944h.contains(messenger)) {
                                    this.f965b.f944h.add(messenger);
                                }
                                this.f965b.f947k = true;
                                this.f964a.f948l.notify();
                                break;
                            }
                            Message obtain = Message.obtain();
                            Bundle bundle = new Bundle();
                            bundle.putParcelable("location", this.f965b.f949m);
                            obtain.setData(bundle);
                            obtain.what = 1;
                            messenger.send(obtain);
                            boolean z3 = data.getBoolean("wifiactivescan");
                            this.f965b.f938b = data.getBoolean("isKillProcess");
                            b = data.getLong("httptimeout");
                            if (this.f965b.f951o != null) {
                                this.f965b.f951o.put("reversegeo", z);
                                this.f965b.f951o.put("isOffset", z2);
                                this.f965b.f951o.put("wifiactivescan", z3 ? Constants.VIA_TO_TYPE_QQ_GROUP : Constants.VIA_RESULT_SUCCESS);
                                this.f965b.f951o.put("httptimeout", b);
                            }
                            this.f964a.m1557a(this.f965b.f951o);
                            break;
                        }
                        break;
                    } catch (Throwable th) {
                        ev.m1777a(th, "APSServiceCore", "handleMessage LOCATION");
                        break;
                    }
                    break;
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    this.f965b.m1581a();
                    break;
                case Type.BYTE /*3*/:
                    this.f965b.m1582b();
                    break;
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    synchronized (this.f965b.f948l) {
                        if (dj.m1471d() && this.f965b.f961y < dj.m1472e()) {
                            this.f965b.f961y = this.f965b.f961y + 1;
                            this.f965b.f947k = true;
                            this.f964a.f948l.notify();
                            this.f965b.f941e.sendEmptyMessageDelayed(4, 2000);
                        }
                        break;
                    }
                    break;
                case Type.INT /*5*/:
                    synchronized (this.f965b.f948l) {
                        if (dj.m1473f() && dj.m1474g() > 2) {
                            this.f965b.f947k = true;
                            if (!dj.m1475h()) {
                                if (!dn.m1534d(this.f965b.f937a)) {
                                    this.f964a.f948l.notify();
                                }
                                break;
                            }
                            this.f964a.f948l.notify();
                            this.f965b.f941e.sendEmptyMessageDelayed(5, (long) (dj.m1474g() * XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER));
                        }
                        break;
                    }
                    break;
            }
            super.handleMessage(message);
        } catch (Throwable th2) {
            ev.m1777a(th2, "APSServiceCore", "handleMessage STARTCOLL");
        }
    }
}
