package com.fimi.soul.biz.update;

import com.fimi.kernel.p084e.ak;
import com.fimi.soul.C1205R;
import com.xiaomi.market.sdk.UpdateResponse;
import com.xiaomi.market.sdk.XiaomiUpdateListener;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.util.BufferRecycler;

/* renamed from: com.fimi.soul.biz.update.j */
class C1413j implements XiaomiUpdateListener {
    final /* synthetic */ int f6363a;
    final /* synthetic */ C1412i f6364b;

    C1413j(C1412i c1412i, int i) {
        this.f6364b = c1412i;
        this.f6363a = i;
    }

    public void onUpdateReturned(int i, UpdateResponse updateResponse) {
        switch (i) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                if (this.f6364b.f6360c != null) {
                    this.f6364b.f6360c.m9466a(updateResponse);
                }
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                if (this.f6363a == 1) {
                    ak.m8083a(this.f6364b.f6358a, (int) C1205R.string.version_tip, (int) BufferRecycler.DEFAULT_WRITE_CONCAT_BUFFER_LEN);
                }
            case Type.BYTE /*3*/:
                if (this.f6363a == 1) {
                    ak.m8083a(this.f6364b.f6358a, (int) C1205R.string.login_result_net, (int) BufferRecycler.DEFAULT_WRITE_CONCAT_BUFFER_LEN);
                }
            default:
        }
    }
}
