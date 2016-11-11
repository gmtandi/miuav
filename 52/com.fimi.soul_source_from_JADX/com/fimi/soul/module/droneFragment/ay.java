package com.fimi.soul.module.droneFragment;

import com.fimi.kernel.p084e.ak;
import com.fimi.soul.C1205R;
import com.fimi.soul.live.NativeAudiolive;
import com.fimi.soul.live.NativeAudiolive.LiveCallBackListener;
import org.codehaus.jackson.util.BufferRecycler;

class ay implements LiveCallBackListener {
    final /* synthetic */ ShowDroneUiFragment f8196a;

    ay(ShowDroneUiFragment showDroneUiFragment) {
        this.f8196a = showDroneUiFragment;
    }

    public String liveCallResult(int i, int i2) {
        if (i == NativeAudiolive.FM_LIVE_PUSH_OK) {
            ak.m8083a(this.f8196a.getActivity(), (int) C1205R.string.live_connected, (int) BufferRecycler.DEFAULT_WRITE_CONCAT_BUFFER_LEN);
        } else if (this.f8196a.f8147v) {
            if (i == NativeAudiolive.FM_LIVE_VIDEO_EXIT || i == NativeAudiolive.FM_LIVE_VIDEO_SYS_FAILED || i == NativeAudiolive.FM_LIVE_VIDEO_FORMAT_ERROR || i == NativeAudiolive.FM_LIVE_PUSH_FORMAT_ERROR || i == NativeAudiolive.FM_LIVE_PUSH_NET_BLOCKS) {
                this.f8196a.m10971c(i2);
            } else {
                this.f8196a.m10966b(i2);
            }
        }
        return null;
    }
}
