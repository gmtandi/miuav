package com.fimi.soul.module.droneui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import com.fimi.kernel.p084e.ak;
import com.fimi.soul.C1205R;
import com.fimi.soul.module.insurance.ReceiveInsuranceActivity;
import com.fimi.soul.module.social.WebViewActivity;
import com.fimi.soul.utils.ay;
import com.fimi.soul.utils.be;
import com.tencent.open.SocialConstants;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;

/* renamed from: com.fimi.soul.module.droneui.o */
class C1753o implements OnClickListener {
    final /* synthetic */ ay f8620a;
    final /* synthetic */ int f8621b;
    final /* synthetic */ FlightActivity f8622c;

    C1753o(FlightActivity flightActivity, ay ayVar, int i) {
        this.f8622c = flightActivity;
        this.f8620a = ayVar;
        this.f8621b = i;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (be.m12370b(this.f8622c)) {
            this.f8620a.m12296a("insurance_ret", this.f8621b + 1);
            Intent intent = new Intent(this.f8622c, WebViewActivity.class);
            intent.putExtra(SocialConstants.PARAM_URL, ReceiveInsuranceActivity.f8720a);
            intent.putExtra(SocialConstants.PARAM_TITLE, this.f8622c.getString(C1205R.string.xiaomi_insurance));
            this.f8622c.startActivity(intent);
        } else {
            ak.m8083a(this.f8622c, (int) C1205R.string.login_result_net, (int) XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER);
        }
        dialogInterface.dismiss();
    }
}
