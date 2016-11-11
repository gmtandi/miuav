package com.fimi.soul.biz.p103k;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import com.fimi.soul.media.player.FimiMediaMeta;
import java.util.Timer;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.biz.k.aq */
public class aq implements Callback {
    private static aq f6079b = null;
    private static final int f6080h = 1;
    private as f6081a;
    private long f6082c;
    private long f6083d;
    private Handler f6084e;
    private Context f6085f;
    private Timer f6086g;

    public aq(Context context) {
        this.f6082c = 0;
        this.f6083d = 0;
        this.f6085f = null;
        this.f6084e = new Handler(this);
        this.f6085f = context;
    }

    public static aq m9219a(Context context) {
        if (f6079b == null) {
            f6079b = new aq(context);
        }
        return f6079b;
    }

    private String m9222c() {
        long totalRxBytes = TrafficStats.getUidRxBytes(this.f6085f.getApplicationInfo().uid) == -1 ? 0 : TrafficStats.getTotalRxBytes() / FimiMediaMeta.AV_CH_SIDE_RIGHT;
        long currentTimeMillis = System.currentTimeMillis();
        long j = (((totalRxBytes - this.f6082c) * 1000) / 8) / (currentTimeMillis - this.f6083d);
        this.f6083d = currentTimeMillis;
        this.f6082c = totalRxBytes;
        return String.valueOf(j) + " KB/s";
    }

    public void m9223a() {
        if (this.f6086g != null) {
            this.f6086g.cancel();
        }
    }

    public void m9224a(as asVar) {
        this.f6081a = asVar;
        this.f6082c = TrafficStats.getUidRxBytes(this.f6085f.getApplicationInfo().uid) == -1 ? 0 : TrafficStats.getTotalRxBytes() / FimiMediaMeta.AV_CH_SIDE_RIGHT;
        this.f6083d = System.currentTimeMillis();
        this.f6086g = new Timer();
        this.f6086g.schedule(new ar(this), 1000, 2000);
    }

    public String m9225b() {
        String str = C2915a.f14760f;
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.f6085f.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            if (activeNetworkInfo.getType() == f6080h) {
                return "WiFi";
            }
            if (activeNetworkInfo.getType() == 0) {
                String subtypeName = activeNetworkInfo.getSubtypeName();
                switch (activeNetworkInfo.getSubtype()) {
                    case f6080h /*1*/:
                    case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    case Type.LONG /*7*/:
                    case Opcodes.T_LONG /*11*/:
                        return "2G";
                    case Type.BYTE /*3*/:
                    case Type.INT /*5*/:
                    case Type.FLOAT /*6*/:
                    case Type.DOUBLE /*8*/:
                    case Type.ARRAY /*9*/:
                    case Type.OBJECT /*10*/:
                    case Opcodes.FCONST_1 /*12*/:
                    case Opcodes.DCONST_0 /*14*/:
                    case Opcodes.DCONST_1 /*15*/:
                        return "3G";
                    case Opcodes.FCONST_2 /*13*/:
                        return "4G";
                    default:
                        return (subtypeName.equalsIgnoreCase("TD-SCDMA") || subtypeName.equalsIgnoreCase("WCDMA") || subtypeName.equalsIgnoreCase("CDMA2000")) ? "3G" : subtypeName;
                }
            }
        }
        return str;
    }

    public boolean handleMessage(Message message) {
        if (message.what == f6080h) {
            this.f6081a.m9226a((String) message.obj, m9225b());
        }
        return false;
    }
}
