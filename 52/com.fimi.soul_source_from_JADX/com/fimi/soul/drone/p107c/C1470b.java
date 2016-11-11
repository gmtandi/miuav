package com.fimi.soul.drone.p107c;

import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1467e;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.fimi.soul.drone.c.b */
public class C1470b {
    static boolean f7012b;
    C1472d f7013a;
    public C1467e f7014c;
    private C1465c f7015d;

    public C1470b() {
        this.f7013a = C1472d.MILINK_PARSE_STATE_UNINIT;
        this.f7014c = new C1467e();
    }

    public C1465c m9852a(int i) {
        f7012b = false;
        switch (C1471c.f7016a[this.f7013a.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                if (i == C1465c.f6998a) {
                    this.f7013a = C1472d.MILINK_PARSE_STATE_GOT_STX;
                    this.f7015d = new C1465c();
                    break;
                }
                break;
            case Type.BYTE /*3*/:
                if (!f7012b) {
                    this.f7015d.f6999b = i;
                    this.f7013a = C1472d.MILINK_PARSE_STATE_GOT_LENGTH;
                    break;
                }
                f7012b = false;
                this.f7013a = C1472d.MILINK_PARSE_STATE_IDLE;
                break;
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                this.f7015d.f7000c = i;
                this.f7013a = C1472d.MILINK_PARSE_STATE_GOT_MSGID;
                break;
            case Type.INT /*5*/:
                this.f7015d.f7001d.m9820a((byte) i);
                if (this.f7015d.m9813a()) {
                    this.f7013a = C1472d.MILINK_PARSE_STATE_GOT_PAYLOAD;
                    break;
                }
                break;
            case Type.FLOAT /*6*/:
                this.f7015d.m9814b();
                if (i == this.f7015d.f7002e.m9811c()) {
                    this.f7013a = C1472d.MILINK_PARSE_STATE_GOT_CRC1;
                    break;
                }
                f7012b = false;
                this.f7013a = C1472d.MILINK_PARSE_STATE_IDLE;
                if (i == C1465c.f6998a) {
                    this.f7013a = C1472d.MILINK_PARSE_STATE_GOT_STX;
                    this.f7015d.f7002e.m9807a();
                }
                this.f7014c.m9844a();
                break;
            case Type.LONG /*7*/:
                if (i == this.f7015d.f7002e.m9809b()) {
                    this.f7014c.m9846a(this.f7015d);
                    f7012b = true;
                    this.f7013a = C1472d.MILINK_PARSE_STATE_IDLE;
                    break;
                }
                f7012b = false;
                this.f7013a = C1472d.MILINK_PARSE_STATE_IDLE;
                if (i == C1465c.f6998a) {
                    this.f7013a = C1472d.MILINK_PARSE_STATE_GOT_STX;
                    this.f7015d.f7002e.m9807a();
                }
                this.f7014c.m9844a();
                break;
        }
        return f7012b ? this.f7015d : null;
    }
}
