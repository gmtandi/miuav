package com.xiaomi.smack;

import com.xiaomi.smack.packet.C2700g;
import com.xiaomi.smack.packet.C2702h;
import java.io.PrintStream;
import java.io.PrintWriter;

/* renamed from: com.xiaomi.smack.w */
public class C2723w extends Exception {
    private C2700g f13447a;
    private C2702h f13448b;
    private Throwable f13449c;

    public C2723w() {
        this.f13447a = null;
        this.f13448b = null;
        this.f13449c = null;
    }

    public C2723w(C2700g c2700g) {
        this.f13447a = null;
        this.f13448b = null;
        this.f13449c = null;
        this.f13447a = c2700g;
    }

    public C2723w(String str) {
        super(str);
        this.f13447a = null;
        this.f13448b = null;
        this.f13449c = null;
    }

    public C2723w(String str, C2702h c2702h) {
        super(str);
        this.f13447a = null;
        this.f13448b = null;
        this.f13449c = null;
        this.f13448b = c2702h;
    }

    public C2723w(String str, Throwable th) {
        super(str);
        this.f13447a = null;
        this.f13448b = null;
        this.f13449c = null;
        this.f13449c = th;
    }

    public C2723w(Throwable th) {
        this.f13447a = null;
        this.f13448b = null;
        this.f13449c = null;
        this.f13449c = th;
    }

    public String getMessage() {
        String message = super.getMessage();
        return (message != null || this.f13448b == null) ? (message != null || this.f13447a == null) ? message : this.f13447a.toString() : this.f13448b.toString();
    }

    public void printStackTrace() {
        printStackTrace(System.err);
    }

    public void printStackTrace(PrintStream printStream) {
        super.printStackTrace(printStream);
        if (this.f13449c != null) {
            printStream.println("Nested Exception: ");
            this.f13449c.printStackTrace(printStream);
        }
    }

    public void printStackTrace(PrintWriter printWriter) {
        super.printStackTrace(printWriter);
        if (this.f13449c != null) {
            printWriter.println("Nested Exception: ");
            this.f13449c.printStackTrace(printWriter);
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        String message = super.getMessage();
        if (message != null) {
            stringBuilder.append(message).append(": ");
        }
        if (this.f13448b != null) {
            stringBuilder.append(this.f13448b);
        }
        if (this.f13447a != null) {
            stringBuilder.append(this.f13447a);
        }
        if (this.f13449c != null) {
            stringBuilder.append("\n  -- caused by: ").append(this.f13449c);
        }
        return stringBuilder.toString();
    }
}
