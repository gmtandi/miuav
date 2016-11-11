package com.xiaomi.push.log;

import com.xiaomi.channel.commonutils.logger.LoggerInterface;

/* renamed from: com.xiaomi.push.log.a */
public class C2625a implements LoggerInterface {
    private LoggerInterface f13048a;
    private LoggerInterface f13049b;

    public C2625a(LoggerInterface loggerInterface, LoggerInterface loggerInterface2) {
        this.f13048a = null;
        this.f13049b = null;
        this.f13048a = loggerInterface;
        this.f13049b = loggerInterface2;
    }

    public void log(String str) {
        if (this.f13048a != null) {
            this.f13048a.log(str);
        }
        if (this.f13049b != null) {
            this.f13049b.log(str);
        }
    }

    public void log(String str, Throwable th) {
        if (this.f13048a != null) {
            this.f13048a.log(str, th);
        }
        if (this.f13049b != null) {
            this.f13049b.log(str, th);
        }
    }

    public void setTag(String str) {
    }
}
