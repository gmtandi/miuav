package com.fimi.soul.module.setting;

import java.io.Serializable;
import org.p122a.p123a.C2915a;

public class aa implements Serializable {
    String f9200a;
    String f9201b;
    int f9202c;
    String f9203d;
    long f9204e;
    int f9205f;
    boolean f9206g;

    public aa() {
        this.f9200a = C2915a.f14760f;
        this.f9201b = C2915a.f14760f;
        this.f9202c = 0;
        this.f9203d = "0 k";
        this.f9205f = 0;
        this.f9206g = false;
    }

    public void m11661a(int i) {
        this.f9202c = i;
    }

    public void m11662a(long j) {
        this.f9204e = j;
    }

    public void m11663a(String str) {
        this.f9200a = str;
    }

    public void m11664a(boolean z) {
        this.f9206g = z;
    }

    public boolean m11665a() {
        return this.f9206g;
    }

    public String m11666b() {
        return this.f9200a;
    }

    public void m11667b(int i) {
        this.f9205f = i;
    }

    public void m11668b(String str) {
        this.f9201b = str;
    }

    public String m11669c() {
        return this.f9201b;
    }

    public void m11670c(String str) {
        this.f9203d = str;
    }

    public int m11671d() {
        return this.f9202c;
    }

    public String m11672e() {
        return this.f9203d;
    }

    public long m11673f() {
        return this.f9204e;
    }

    public int m11674g() {
        return this.f9205f;
    }

    public String toString() {
        return "LogFileEntity{fileName='" + this.f9200a + '\'' + ", path='" + this.f9201b + '\'' + ", hasSync=" + this.f9202c + ", fileSize='" + this.f9203d + '\'' + ", createDate=" + this.f9204e + ", percent=" + this.f9205f + '}';
    }
}
