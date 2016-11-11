package cn.sharesdk.framework.authorize;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import cn.sharesdk.framework.utils.C0205d;
import com.mob.tools.utils.C2178R;

/* renamed from: cn.sharesdk.framework.authorize.c */
class C0156c implements OnClickListener {
    final /* synthetic */ RegisterView f202a;

    C0156c(RegisterView registerView) {
        this.f202a = registerView;
    }

    public void onClick(View view) {
        try {
            int stringRes = C2178R.getStringRes(view.getContext(), "ssdk_website");
            Object obj = null;
            if (stringRes > 0) {
                obj = view.getResources().getString(stringRes);
            }
            if (!TextUtils.isEmpty(obj)) {
                view.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(obj)));
            }
        } catch (Throwable th) {
            C0205d.m752a().m738d(th);
        }
    }
}
