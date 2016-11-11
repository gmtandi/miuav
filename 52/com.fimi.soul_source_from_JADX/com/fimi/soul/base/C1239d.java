package com.fimi.soul.base;

import com.fimi.soul.biz.p103k.C1384h;
import com.fimi.soul.utils.C1981u;
import java.io.PrintStream;

/* renamed from: com.fimi.soul.base.d */
class C1239d {
    final /* synthetic */ ErrorReportApp f5631a;
    private Throwable f5632b;

    public C1239d(ErrorReportApp errorReportApp, Throwable th) {
        this.f5631a = errorReportApp;
        this.f5632b = th;
    }

    public void m8536a() {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(this.f5632b.toString());
            for (StackTraceElement stackTraceElement : this.f5632b.getStackTrace()) {
                stringBuffer.append(", Class Name:");
                stringBuffer.append(stackTraceElement.getClassName());
                stringBuffer.append(" , Method Name:");
                stringBuffer.append(stackTraceElement.getMethodName());
                stringBuffer.append(" , LineNumber:");
                stringBuffer.append(stackTraceElement.getLineNumber());
            }
            C1384h.m9307a(this.f5631a.getApplicationContext()).m9308a(stringBuffer.toString());
            PrintStream printStream = new PrintStream(C1981u.m12515b());
            this.f5632b.printStackTrace(printStream);
            printStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
