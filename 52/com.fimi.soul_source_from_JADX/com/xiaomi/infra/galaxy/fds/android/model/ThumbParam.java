package com.xiaomi.infra.galaxy.fds.android.model;

import com.tencent.connect.common.Constants;

public class ThumbParam extends UserParam {
    public ThumbParam(int i, int i2) {
        this.params.put("thumb", Constants.VIA_TO_TYPE_QQ_GROUP);
        this.params.put("w", Integer.toString(i));
        this.params.put("h", Integer.toString(i2));
    }
}
