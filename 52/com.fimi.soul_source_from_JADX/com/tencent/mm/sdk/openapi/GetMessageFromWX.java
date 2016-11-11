package com.tencent.mm.sdk.openapi;

import android.os.Bundle;
import com.tencent.mm.sdk.openapi.WXMediaMessage.Builder;
import com.tencent.mm.sdk.platformtools.Log;

public final class GetMessageFromWX {

    public class Req extends BaseReq {
        public String username;

        public Req(Bundle bundle) {
            fromBundle(bundle);
        }

        final boolean checkArgs() {
            return true;
        }

        public void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
        }

        public int getType() {
            return 3;
        }

        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
        }
    }

    public class Resp extends BaseResp {
        public WXMediaMessage message;

        public Resp(Bundle bundle) {
            fromBundle(bundle);
        }

        final boolean checkArgs() {
            if (this.message != null) {
                return this.message.checkArgs();
            }
            Log.m13541e("MicroMsg.SDK.GetMessageFromWX.Resp", "checkArgs fail, message is null");
            return false;
        }

        public void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
            this.message = Builder.fromBundle(bundle);
        }

        public int getType() {
            return 3;
        }

        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
            bundle.putAll(Builder.toBundle(this.message));
        }
    }

    private GetMessageFromWX() {
    }
}
