package com.fimi.soul.biz.update;

import com.xiaomi.market.sdk.UpdateResponse;
import com.xiaomi.market.sdk.XiaomiUpdateListener;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.fimi.soul.biz.update.k */
class C1414k implements XiaomiUpdateListener {
    final /* synthetic */ C1412i f6365a;

    C1414k(C1412i c1412i) {
        this.f6365a = c1412i;
    }

    public void onUpdateReturned(int i, UpdateResponse updateResponse) {
        switch (i) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                this.f6365a.f6359b.m9465a(String.valueOf(updateResponse.versionCode));
            default:
        }
    }
}
