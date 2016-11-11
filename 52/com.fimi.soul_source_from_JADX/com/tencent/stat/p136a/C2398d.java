package com.tencent.stat.p136a;

import android.content.Context;
import com.tencent.stat.StatConfig;
import com.tencent.stat.common.C2418k;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import org.json.JSONObject;

/* renamed from: com.tencent.stat.a.d */
public class C2398d extends C2394e {
    private String f12268a;
    private int f12269l;
    private int f12270m;

    public C2398d(Context context, int i, int i2, Throwable th) {
        super(context, i);
        this.f12270m = 100;
        if (th != null) {
            Throwable th2 = new Throwable(th);
            try {
                StackTraceElement[] stackTrace = th2.getStackTrace();
                if (stackTrace != null && stackTrace.length > this.f12270m) {
                    StackTraceElement[] stackTraceElementArr = new StackTraceElement[this.f12270m];
                    for (int i3 = 0; i3 < this.f12270m; i3++) {
                        stackTraceElementArr[i3] = stackTrace[i3];
                    }
                    th2.setStackTrace(stackTraceElementArr);
                }
            } catch (Throwable th3) {
                th3.printStackTrace();
            }
            Writer stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            th2.printStackTrace(printWriter);
            this.f12268a = stringWriter.toString();
            this.f12269l = i2;
            printWriter.close();
        }
    }

    public C2398d(Context context, int i, String str, int i2, int i3) {
        super(context, i);
        this.f12270m = 100;
        if (str != null) {
            if (i3 <= 0) {
                i3 = StatConfig.getMaxReportEventLength();
            }
            if (str.length() <= i3) {
                this.f12268a = str;
            } else {
                this.f12268a = str.substring(0, i3);
            }
        }
        this.f12269l = i2;
    }

    public C2399f m13947a() {
        return C2399f.ERROR;
    }

    public void m13948a(long j) {
        this.c = j;
    }

    public boolean m13949a(JSONObject jSONObject) {
        C2418k.m14014a(jSONObject, "er", this.f12268a);
        jSONObject.put("ea", this.f12269l);
        return true;
    }
}
