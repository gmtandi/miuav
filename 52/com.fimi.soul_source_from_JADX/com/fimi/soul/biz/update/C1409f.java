package com.fimi.soul.biz.update;

import android.os.Message;
import android.util.Log;
import com.fimi.kernel.p076b.C1153f;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.biz.update.f */
class C1409f implements C1153f<String> {
    final /* synthetic */ C1408e f6354a;

    C1409f(C1408e c1408e) {
        this.f6354a = c1408e;
    }

    public void m9450a(String str) {
        Log.d("Good", str);
        try {
            int parseInt = Integer.parseInt(str.trim().replace("\n", C2915a.f14760f));
            Message message = new Message();
            message.what = 1;
            if (parseInt < 100) {
                message.arg1 = 1;
                message.arg2 = parseInt;
            } else {
                message.arg1 = 0;
                message.arg2 = 0;
                this.f6354a.f6353a.f6329q = false;
            }
            this.f6354a.f6353a.m7685a().sendMessage(message);
        } catch (Exception e) {
            Log.d("Good", "Error");
            this.f6354a.f6353a.f6328o = this.f6354a.f6353a.f6328o + C1404a.f6319g;
        }
    }

    public void m9452b(String str) {
        this.f6354a.f6353a.f6328o = this.f6354a.f6353a.f6328o + C1404a.f6319g;
    }
}
