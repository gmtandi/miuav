package com.fimi.soul.module.p091a;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.p109a.C1458u;
import com.fimi.soul.drone.p107c.p108a.p109a.af;
import com.fimi.soul.drone.p107c.p108a.p109a.ai;
import com.fimi.soul.drone.p107c.p108a.p109a.aj;
import com.fimi.soul.drone.p107c.p108a.p109a.ax;
import com.fimi.soul.drone.p107c.p108a.p109a.ba;
import com.fimi.soul.drone.p116g.C1543c;
import com.fimi.soul.drone.p116g.C1544d;
import com.fimi.soul.module.calibcompass.C1683q;
import com.fimi.soul.module.droneui.DroneMap;
import com.fimi.soul.module.setting.newhand.C1874p;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.module.a.h */
public class C1664h {
    private static C1664h f7851a;
    private static C1433a f7852b;
    private C1683q f7853c;
    private C1662d f7854d;

    static {
        f7851a = null;
    }

    private C1664h() {
        this.f7853c = C1683q.m10886a();
        this.f7854d = C1662d.m10808a();
    }

    public static C1664h m10813a(C1433a c1433a) {
        f7852b = c1433a;
        if (f7851a == null) {
            f7851a = new C1664h();
        }
        return f7851a;
    }

    private void m10814a(C1465c c1465c) {
        C1658a a = C1658a.m10799a(this.f7854d);
        this.f7853c.m10888a(c1465c.f7000c + C2915a.f14760f, a);
        a.m10804a(f7852b, c1465c);
        a.m10803a();
        C1543c.m10118a(C1544d.PHONESEND);
    }

    public void m10815a() {
        if (!f7852b.aa()) {
            ax axVar = new ax();
            axVar.f6694d = (short) 1;
            f7852b.m9569P().m9993a(axVar.m9683a());
            m10814a(axVar.m9683a());
        }
    }

    public void m10816a(byte b, byte b2, byte b3) {
        C1465c c1465c = new C1465c();
        c1465c.f7000c = Opcodes.IFEQ;
        c1465c.f6999b = 3;
        c1465c.f7001d.m9828b(b);
        c1465c.f7001d.m9828b(b2);
        c1465c.f7001d.m9828b(b3);
        f7852b.m9569P().m9993a(c1465c);
        m10814a(c1465c);
    }

    public void m10817a(byte b, byte b2, short s, short s2, byte b3, short s3, float f, float f2) {
        C1465c c1465c = new C1465c();
        c1465c.f7000c = Opcodes.I2F;
        c1465c.f6999b = 19;
        c1465c.f7001d.m9828b((byte) 1);
        c1465c.f7001d.m9828b(b);
        c1465c.f7001d.m9828b(b2);
        c1465c.f7001d.m9826a(s);
        c1465c.f7001d.m9826a(s2);
        c1465c.f7001d.m9828b(b3);
        c1465c.f7001d.m9826a(s3);
        c1465c.f7001d.m9823a(f);
        c1465c.f7001d.m9823a(f2);
        c1465c.f7001d.m9828b((byte) 0);
        f7852b.m9569P().m9993a(c1465c);
        m10814a(c1465c);
    }

    public void m10818a(double d, double d2) {
        C1465c c1465c = new C1465c();
        c1465c.f7000c = SmileConstants.TOKEN_PREFIX_TINY_UNICODE;
        c1465c.f6999b = 14;
        c1465c.f7001d.m9828b((byte) 1);
        c1465c.f7001d.m9828b((byte) 0);
        c1465c.f7001d.m9823a((float) d);
        c1465c.f7001d.m9823a((float) d2);
        c1465c.f7001d.m9826a((short) 0);
        c1465c.f7001d.m9828b((byte) 0);
        c1465c.f7001d.m9828b((byte) 0);
        f7852b.m9569P().m9993a(c1465c);
        m10814a(c1465c);
    }

    public void m10819a(int i) {
        C1465c c1465c = new C1465c();
        c1465c.f7000c = Opcodes.IXOR;
        c1465c.f6999b = 2;
        c1465c.f7001d.m9826a((short) i);
        f7852b.m9569P().m9993a(c1465c);
        m10814a(c1465c);
    }

    public void m10820a(int i, double d, double d2, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        C1465c c1465c = new C1465c();
        c1465c.f7000c = Opcodes.LOR;
        c1465c.f6999b = 19;
        c1465c.f7001d.m9828b((byte) i);
        c1465c.f7001d.m9828b((byte) i3);
        c1465c.f7001d.m9823a((float) d);
        c1465c.f7001d.m9823a((float) d2);
        c1465c.f7001d.m9826a((short) i2);
        c1465c.f7001d.m9826a((short) i4);
        c1465c.f7001d.m9828b((byte) i5);
        c1465c.f7001d.m9828b((byte) i6);
        c1465c.f7001d.m9828b((byte) i7);
        c1465c.f7001d.m9828b((byte) i8);
        c1465c.f7001d.m9828b((byte) 0);
        f7852b.m9569P().m9993a(c1465c);
        m10814a(c1465c);
    }

    public void m10821a(int i, double d, double d2, short s, int i2) {
        C1465c c1465c = new C1465c();
        c1465c.f7000c = Opcodes.I2L;
        c1465c.f6999b = 16;
        c1465c.f7001d.m9826a((short) i);
        c1465c.f7001d.m9823a((float) d);
        c1465c.f7001d.m9823a((float) d2);
        c1465c.f7001d.m9826a(s);
        c1465c.f7001d.m9828b((byte) 0);
        c1465c.f7001d.m9828b((byte) i2);
        c1465c.f7001d.m9828b((byte) 0);
        c1465c.f7001d.m9828b((byte) 0);
        f7852b.m9569P().m9993a(c1465c);
        m10814a(c1465c);
    }

    public void m10822a(int i, double d, double d2, short s, short s2) {
        C1465c c1465c = new C1465c();
        c1465c.f7000c = Opcodes.LXOR;
        c1465c.f6999b = 19;
        c1465c.f7001d.m9826a((short) i);
        c1465c.f7001d.m9823a((float) d);
        c1465c.f7001d.m9823a((float) d2);
        c1465c.f7001d.m9826a(s);
        c1465c.f7001d.m9826a((short) 0);
        c1465c.f7001d.m9826a((short) 0);
        c1465c.f7001d.m9828b((byte) 0);
        c1465c.f7001d.m9826a(s2);
        f7852b.m9569P().m9993a(c1465c);
        m10814a(c1465c);
    }

    public void m10823b() {
        aj ajVar = new aj();
        ajVar.f6601d = (short) 1;
        f7852b.m9569P().m9993a(ajVar.m9655a());
        m10814a(ajVar.m9655a());
    }

    public void m10824b(byte b, byte b2, byte b3) {
        C1465c c1465c = new C1465c();
        c1465c.f7000c = Opcodes.IFEQ;
        c1465c.f6999b = 3;
        c1465c.f7001d.m9828b(b);
        c1465c.f7001d.m9828b(b2);
        c1465c.f7001d.m9828b(b3);
        f7852b.m9569P().m9993a(c1465c);
        m10814a(c1465c);
    }

    public void m10825b(int i) {
        C1465c c1465c = new C1465c();
        c1465c.f7000c = C1458u.f6934b;
        c1465c.f6999b = 2;
        c1465c.f7001d.m9828b((byte) i);
        c1465c.f7001d.m9828b((byte) 2);
        f7852b.m9569P().m9993a(c1465c);
        m10814a(c1465c);
    }

    public void m10826c() {
        af afVar = new af();
        afVar.f6571d = (short) 1;
        f7852b.m9569P().m9993a(afVar.m9647a());
        m10814a(afVar.m9647a());
    }

    public void m10827c(int i) {
        this.f7853c.m10887a(i + C2915a.f14760f);
    }

    public void m10828d() {
        ai aiVar = new ai();
        aiVar.f6596d = (short) 1;
        aiVar.f6597e = DroneMap.f8357k;
        f7852b.m9569P().m9993a(aiVar.m9653a());
        m10814a(aiVar.m9653a());
    }

    public void m10829e() {
        ai aiVar = new ai();
        aiVar.f6596d = (short) 1;
        aiVar.f6597e = DroneMap.f8356j;
        f7852b.m9569P().m9993a(aiVar.m9653a());
        m10814a(aiVar.m9653a());
    }

    public void m10830f() {
        ai aiVar = new ai();
        aiVar.f6596d = (short) 1;
        aiVar.f6597e = DroneMap.f8355i;
        f7852b.m9569P().m9993a(aiVar.m9653a());
        m10814a(aiVar.m9653a());
    }

    public void m10831g() {
        ba baVar = new ba();
        baVar.f6720e = (byte) 1;
        baVar.f6719d = (byte) 114;
        baVar.f6721f = (byte) 3;
        f7852b.m9569P().m9993a(baVar.m9691a());
        m10814a(baVar.m9691a());
    }

    public void m10832h() {
        ba baVar = new ba();
        baVar.f6720e = (byte) 1;
        baVar.f6719d = (byte) 114;
        baVar.f6721f = (byte) 1;
        f7852b.m9569P().m9993a(baVar.m9691a());
        m10814a(baVar.m9691a());
    }

    public void m10833i() {
        ba baVar = new ba();
        baVar.f6720e = (byte) 1;
        baVar.f6719d = (byte) 114;
        baVar.f6721f = (byte) 2;
        f7852b.m9569P().m9993a(baVar.m9691a());
        m10814a(baVar.m9691a());
    }

    public void m10834j() {
        ba baVar = new ba();
        baVar.f6720e = (byte) 1;
        baVar.f6719d = (byte) 87;
        baVar.f6721f = (byte) 0;
        f7852b.m9569P().m9993a(baVar.m9691a());
        m10814a(baVar.m9691a());
    }

    public void m10835k() {
        ba baVar = new ba();
        baVar.f6720e = (byte) 2;
        baVar.f6719d = DroneMap.f8356j;
        baVar.f6721f = (byte) 0;
        f7852b.m9569P().m9993a(baVar.m9691a());
        m10814a(baVar.m9691a());
    }

    public void m10836l() {
        ba baVar = new ba();
        baVar.f6720e = (byte) 1;
        baVar.f6719d = (byte) 84;
        baVar.f6721f = (byte) 0;
        f7852b.m9569P().m9993a(baVar.m9691a());
        m10814a(baVar.m9691a());
    }

    public void m10837m() {
        ba baVar = new ba();
        baVar.f6720e = (byte) 1;
        baVar.f6719d = (byte) 88;
        baVar.f6721f = (byte) 0;
        f7852b.m9569P().m9993a(baVar.m9691a());
        m10814a(baVar.m9691a());
    }

    public void m10838n() {
        ba baVar = new ba();
        baVar.f6720e = (byte) 1;
        baVar.f6719d = (byte) 86;
        baVar.f6721f = (byte) 2;
        f7852b.m9569P().m9993a(baVar.m9691a());
        m10814a(baVar.m9691a());
    }

    public void m10839o() {
        ba baVar = new ba();
        baVar.f6720e = (byte) 1;
        baVar.f6719d = C1874p.f9570f;
        baVar.f6721f = (byte) 1;
        f7852b.m9569P().m9993a(baVar.m9691a());
        m10814a(baVar.m9691a());
    }

    public void m10840p() {
        ba baVar = new ba();
        baVar.f6720e = (byte) 1;
        baVar.f6719d = (byte) 86;
        baVar.f6721f = (byte) 1;
        f7852b.m9569P().m9993a(baVar.m9691a());
        m10814a(baVar.m9691a());
    }

    public void m10841q() {
        C1465c c1465c = new C1465c();
        c1465c.f7000c = Opcodes.LCMP;
        c1465c.f6999b = 2;
        c1465c.f7001d.m9826a((short) 1);
        f7852b.m9569P().m9993a(c1465c);
        m10814a(c1465c);
    }

    public void m10842r() {
        C1465c c1465c = new C1465c();
        c1465c.f7000c = Opcodes.ISHR;
        c1465c.f6999b = 2;
        c1465c.f7001d.m9828b((byte) 10);
        c1465c.f7001d.m9828b((byte) 0);
        f7852b.m9569P().m9993a(c1465c);
        m10814a(c1465c);
    }
}
