package com.amap.api.services.help;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.C0471d;
import com.amap.api.services.core.C0475h;
import com.amap.api.services.core.C0476i;
import com.amap.api.services.core.C0480l;
import com.amap.api.services.core.C0490p;
import java.util.ArrayList;
import java.util.List;
import org.p122a.p123a.C2915a;

public final class Inputtips {
    private Context f3267a;
    private InputtipsListener f3268b;
    private Handler f3269c;

    /* renamed from: com.amap.api.services.help.Inputtips.1 */
    class C05121 extends Thread {
        final /* synthetic */ String f3264a;
        final /* synthetic */ String f3265b;
        final /* synthetic */ Inputtips f3266c;

        C05121(Inputtips inputtips, String str, String str2) {
            this.f3266c = inputtips;
            this.f3264a = str;
            this.f3265b = str2;
        }

        public void run() {
            C0475h c0475h = new C0475h(this.f3266c.f3267a, new C0476i(this.f3264a, this.f3265b));
            Message obtainMessage = C0490p.m4850a().obtainMessage();
            obtainMessage.obj = this.f3266c.f3268b;
            obtainMessage.arg1 = 5;
            try {
                ArrayList arrayList = (ArrayList) c0475h.m4458g();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("result", arrayList);
                obtainMessage.setData(bundle);
                obtainMessage.what = 0;
            } catch (Throwable e) {
                C0471d.m4762a(e, "Inputtips", "requestInputtips");
                obtainMessage.what = e.getErrorCode();
            } finally {
                this.f3266c.f3269c.sendMessage(obtainMessage);
            }
        }
    }

    public interface InputtipsListener {
        void onGetInputtips(List<Tip> list, int i);
    }

    public Inputtips(Context context, InputtipsListener inputtipsListener) {
        this.f3267a = context.getApplicationContext();
        this.f3268b = inputtipsListener;
        this.f3269c = C0490p.m4850a();
    }

    public void requestInputtips(String str, String str2) {
        if (str == null || str.equals(C2915a.f14760f)) {
            throw new AMapException(AMapException.ERROR_INVALID_PARAMETER);
        }
        C0480l.m4841a(this.f3267a);
        new C05121(this, str, str2).start();
    }
}
