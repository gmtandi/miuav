package com.fimi.soul.drone.droneconnection.connection;

import com.fimi.soul.drone.p107c.p108a.C1465c;
import java.io.Serializable;

/* renamed from: com.fimi.soul.drone.droneconnection.connection.b */
public class C1503b implements Serializable {
    private int f7102a;
    private String f7103b;
    private C1465c f7104c;

    public int m9946a() {
        return this.f7102a;
    }

    public void m9947a(int i) {
        this.f7102a = i;
    }

    public void m9948a(C1465c c1465c) {
        this.f7104c = c1465c;
    }

    public void m9949a(String str) {
        this.f7103b = str;
    }

    public String m9950b() {
        return this.f7103b;
    }

    public C1465c m9951c() {
        return this.f7104c;
    }
}
