package com.tencent.open;

import android.app.Activity;
import android.os.Bundle;
import com.tencent.connect.auth.QQToken;
import com.tencent.open.utils.Util;
import com.tencent.tauth.IUiListener;
import com.xiaomi.market.sdk.C2537j;

public class SocialApi {
    private SocialApiIml f11844a;

    public SocialApi(QQToken qQToken) {
        this.f11844a = new SocialApiIml(qQToken);
    }

    public void ask(Activity activity, Bundle bundle, IUiListener iUiListener) {
        this.f11844a.ask(activity, bundle, iUiListener);
    }

    public void brag(Activity activity, Bundle bundle, IUiListener iUiListener) {
        this.f11844a.brag(activity, bundle, iUiListener);
    }

    public void challenge(Activity activity, Bundle bundle, IUiListener iUiListener) {
        this.f11844a.challenge(activity, bundle, iUiListener);
    }

    public boolean checkVoiceApi(Activity activity, Bundle bundle, IUiListener iUiListener) {
        bundle.putString(C2537j.aq, Util.getAppVersion(activity));
        this.f11844a.grade(activity, bundle, iUiListener);
        return true;
    }

    public void gift(Activity activity, Bundle bundle, IUiListener iUiListener) {
        this.f11844a.gift(activity, bundle, iUiListener);
    }

    public void grade(Activity activity, Bundle bundle, IUiListener iUiListener) {
        bundle.putString(C2537j.aq, Util.getAppVersion(activity));
        this.f11844a.grade(activity, bundle, iUiListener);
    }

    public void invite(Activity activity, Bundle bundle, IUiListener iUiListener) {
        this.f11844a.invite(activity, bundle, iUiListener);
    }

    public void reactive(Activity activity, Bundle bundle, IUiListener iUiListener) {
        this.f11844a.reactive(activity, bundle, iUiListener);
    }

    public void story(Activity activity, Bundle bundle, IUiListener iUiListener) {
        this.f11844a.story(activity, bundle, iUiListener);
    }

    public void voice(Activity activity, Bundle bundle, IUiListener iUiListener) {
        bundle.putString(C2537j.aq, Util.getAppVersion(activity));
        this.f11844a.voice(activity, bundle, iUiListener);
    }
}
