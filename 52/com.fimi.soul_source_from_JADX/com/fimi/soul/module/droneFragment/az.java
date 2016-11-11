package com.fimi.soul.module.droneFragment;

import com.fimi.kernel.p084e.ak;
import com.fimi.soul.C1205R;
import com.fimi.soul.biz.camera.C1314u;
import com.mi.live.openlivesdk.p120a.C1687a;
import com.tencent.open.yyb.AppbarJsBridge;
import org.codehaus.jackson.io.CharacterEscapes;
import org.codehaus.jackson.util.BufferRecycler;

class az implements C1687a {
    final /* synthetic */ ShowDroneUiFragment f8197a;

    az(ShowDroneUiFragment showDroneUiFragment) {
        this.f8197a = showDroneUiFragment;
    }

    public void m11035a() {
        this.f8197a.m10977e(false);
    }

    public void m11036a(int i, String str) {
        switch (i) {
            case AppbarJsBridge.Code_Java_Exception /*-3*/:
                ak.m8083a(this.f8197a.getActivity(), (int) C1205R.string.stream_live_version_error, (int) BufferRecycler.DEFAULT_WRITE_CONCAT_BUFFER_LEN);
            case CharacterEscapes.ESCAPE_CUSTOM /*-2*/:
                ak.m8083a(this.f8197a.getActivity(), (int) C1205R.string.stream_live_uninstall_error, (int) BufferRecycler.DEFAULT_WRITE_CONCAT_BUFFER_LEN);
            default:
        }
    }

    public void m11037a(String str) {
        this.f8197a.f8126a = str;
        this.f8197a.f8146u.fmLiveStart(C1314u.f5879f, str);
        this.f8197a.m10977e(true);
    }

    public void m11038b(int i, String str) {
        switch (i) {
            case AppbarJsBridge.Code_Java_Exception /*-3*/:
                ak.m8083a(this.f8197a.getActivity(), (int) C1205R.string.stream_live_version_error, (int) BufferRecycler.DEFAULT_WRITE_CONCAT_BUFFER_LEN);
            case CharacterEscapes.ESCAPE_CUSTOM /*-2*/:
                ak.m8083a(this.f8197a.getActivity(), (int) C1205R.string.stream_live_uninstall_error, (int) BufferRecycler.DEFAULT_WRITE_CONCAT_BUFFER_LEN);
                this.f8197a.m10977e(false);
            default:
        }
    }
}
