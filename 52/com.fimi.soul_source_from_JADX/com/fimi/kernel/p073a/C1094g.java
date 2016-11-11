package com.fimi.kernel.p073a;

import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.kernel.a.g */
public class C1094g {
    public String f5086a;
    public String f5087b;
    public String f5088c;
    public String f5089d;
    public int f5090e;
    public String f5091f;

    public C1094g(String str) {
        if (str != null) {
            String[] split = str.split("[\\s]+");
            if (split.length == 9) {
                this.f5089d = split[0];
                this.f5086a = split[1];
                this.f5088c = split[2];
                this.f5090e = Integer.parseInt(split[4]);
                this.f5087b = split[8];
                if (m7672a()) {
                    this.f5091f = this.f5086a;
                }
            }
        }
    }

    public boolean m7672a() {
        return "zygote".equals(this.f5087b);
    }

    public boolean m7673b() {
        return this.f5088c.equals(this.f5091f) && this.f5089d.startsWith("app_");
    }

    public String toString() {
        String str = ";";
        str = C2915a.f14760f;
        return "PsRow ( " + super.toString() + ";" + "pid = " + this.f5086a + ";" + "cmd = " + this.f5087b + ";" + "ppid = " + this.f5088c + ";" + "user = " + this.f5089d + ";" + "mem = " + this.f5090e + " )";
    }
}
