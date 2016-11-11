package com.fimi.soul.drone.p107c.p108a;

import android.util.Log;
import com.fimi.soul.drone.p107c.p108a.p109a.C1439b;
import com.fimi.soul.drone.p107c.p108a.p109a.C1440c;
import com.fimi.soul.drone.p107c.p108a.p109a.C1441d;
import com.fimi.soul.drone.p107c.p108a.p109a.C1442e;
import com.fimi.soul.drone.p107c.p108a.p109a.C1446i;
import com.fimi.soul.drone.p107c.p108a.p109a.C1447j;
import com.fimi.soul.drone.p107c.p108a.p109a.C1448k;
import com.fimi.soul.drone.p107c.p108a.p109a.C1449l;
import com.fimi.soul.drone.p107c.p108a.p109a.C1450m;
import com.fimi.soul.drone.p107c.p108a.p109a.C1451n;
import com.fimi.soul.drone.p107c.p108a.p109a.C1452o;
import com.fimi.soul.drone.p107c.p108a.p109a.C1453p;
import com.fimi.soul.drone.p107c.p108a.p109a.C1454q;
import com.fimi.soul.drone.p107c.p108a.p109a.C1455r;
import com.fimi.soul.drone.p107c.p108a.p109a.C1456s;
import com.fimi.soul.drone.p107c.p108a.p109a.C1457t;
import com.fimi.soul.drone.p107c.p108a.p109a.C1458u;
import com.fimi.soul.drone.p107c.p108a.p109a.C1459v;
import com.fimi.soul.drone.p107c.p108a.p109a.C1460w;
import com.fimi.soul.drone.p107c.p108a.p109a.C1461x;
import com.fimi.soul.drone.p107c.p108a.p109a.C1462y;
import com.fimi.soul.drone.p107c.p108a.p109a.C1463z;
import com.fimi.soul.drone.p107c.p108a.p109a.aa;
import com.fimi.soul.drone.p107c.p108a.p109a.ab;
import com.fimi.soul.drone.p107c.p108a.p109a.ac;
import com.fimi.soul.drone.p107c.p108a.p109a.ad;
import com.fimi.soul.drone.p107c.p108a.p109a.ae;
import com.fimi.soul.drone.p107c.p108a.p109a.af;
import com.fimi.soul.drone.p107c.p108a.p109a.ag;
import com.fimi.soul.drone.p107c.p108a.p109a.ah;
import com.fimi.soul.drone.p107c.p108a.p109a.ai;
import com.fimi.soul.drone.p107c.p108a.p109a.aj;
import com.fimi.soul.drone.p107c.p108a.p109a.ak;
import com.fimi.soul.drone.p107c.p108a.p109a.al;
import com.fimi.soul.drone.p107c.p108a.p109a.am;
import com.fimi.soul.drone.p107c.p108a.p109a.ao;
import com.fimi.soul.drone.p107c.p108a.p109a.ap;
import com.fimi.soul.drone.p107c.p108a.p109a.aq;
import com.fimi.soul.drone.p107c.p108a.p109a.as;
import com.fimi.soul.drone.p107c.p108a.p109a.at;
import com.fimi.soul.drone.p107c.p108a.p109a.aw;
import com.fimi.soul.drone.p107c.p108a.p109a.ax;
import com.fimi.soul.drone.p107c.p108a.p109a.ay;
import com.fimi.soul.drone.p107c.p108a.p109a.az;
import com.fimi.soul.drone.p107c.p108a.p109a.ba;
import com.fimi.soul.drone.p107c.p108a.p109a.bb;
import com.fimi.soul.drone.p107c.p108a.p109a.bc;
import com.fimi.soul.drone.p107c.p108a.p109a.bd;
import com.fimi.soul.drone.p107c.p108a.p109a.be;
import com.fimi.soul.drone.p107c.p108a.p109a.bh;
import com.fimi.soul.drone.p107c.p108a.p109a.bj;
import com.fimi.soul.drone.p107c.p108a.p109a.bl;
import com.fimi.soul.drone.p107c.p108a.p109a.bm;
import com.fimi.soul.drone.p107c.p108a.p109a.bn;
import it.p074a.p075a.C2799f;
import java.io.Serializable;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.util.TokenBuffer.Segment;

/* renamed from: com.fimi.soul.drone.c.a.c */
public class C1465c implements Serializable {
    public static final int f6998a = 254;
    private static final long serialVersionUID = 2095947771227815314L;
    public int f6999b;
    public int f7000c;
    public C1466d f7001d;
    public C1464a f7002e;

    public C1465c() {
        this.f7001d = new C1466d();
    }

    public C1465c(C1466d c1466d) {
        this.f7001d = c1466d;
    }

    public boolean m9813a() {
        return this.f7001d.m9827b() >= 511 || this.f7001d.m9827b() == this.f6999b;
    }

    public void m9814b() {
        this.f7002e = new C1464a();
        this.f7002e.m9808a(this.f6999b);
        this.f7002e.m9808a(this.f7000c);
        this.f7001d.m9831c();
        for (int i = 0; i < this.f7001d.m9827b(); i++) {
            this.f7002e.m9808a(this.f7001d.m9833d());
        }
    }

    public void m9815c() {
        this.f7002e = new C1464a();
        this.f7002e.m9808a(this.f6999b + 1);
        this.f7002e.m9808a(this.f7000c);
        this.f7001d.m9831c();
        for (int i = 0; i < this.f7001d.m9827b(); i++) {
            this.f7002e.m9808a(this.f7001d.m9833d());
        }
    }

    public byte[] m9816d() {
        int i = 0;
        byte[] bArr = new byte[((this.f6999b + 3) + 2)];
        bArr[0] = (byte) -2;
        bArr[1] = (byte) (this.f6999b + 1);
        int i2 = 3;
        bArr[2] = (byte) this.f7000c;
        while (i < this.f7001d.m9827b() - 1) {
            int i3 = i2 + 1;
            bArr[i2] = this.f7001d.f7004b.get(i);
            i++;
            i2 = i3;
        }
        m9815c();
        i = i2 + 1;
        bArr[i2] = (byte) this.f7002e.m9811c();
        i2 = i + 1;
        bArr[i] = (byte) this.f7002e.m9809b();
        return bArr;
    }

    public byte[] m9817e() {
        int i = 0;
        byte[] bArr = new byte[((this.f6999b + 2) + 2)];
        bArr[0] = (byte) -2;
        bArr[1] = (byte) this.f6999b;
        int i2 = 3;
        bArr[2] = (byte) this.f7000c;
        while (i < this.f7001d.m9827b() - 1) {
            int i3 = i2 + 1;
            bArr[i2] = this.f7001d.f7004b.get(i);
            i++;
            i2 = i3;
        }
        m9814b();
        i = i2 + 1;
        bArr[i2] = (byte) this.f7002e.m9811c();
        i2 = i + 1;
        bArr[i] = (byte) this.f7002e.m9809b();
        return bArr;
    }

    public C1437b m9818f() {
        C1437b c1448k;
        switch (this.f7000c) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return new aq(this);
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return new ah(this);
            case Type.BYTE /*3*/:
                return new am(this);
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                return new ak(this);
            case Type.INT /*5*/:
                return new C1442e(this);
            case Type.FLOAT /*6*/:
                return new ay(this);
            case Type.LONG /*7*/:
                return new C1456s(this);
            case Type.ARRAY /*9*/:
                return new C1449l(this);
            case Type.OBJECT /*10*/:
                return new C1460w(this);
            case Opcodes.T_LONG /*11*/:
                return new C1454q(this);
            case Segment.TOKENS_PER_SEGMENT /*16*/:
                return new ae(this, 16);
            case Opcodes.CALOAD /*52*/:
                c1448k = new C1448k();
                c1448k.m9770a(this);
                return c1448k;
            case Opcodes.FADD /*98*/:
                return new ba(this);
            case Opcodes.DADD /*99*/:
                return new bb(this);
            case Opcodes.ISUB /*100*/:
                return new bc(this);
            case Opcodes.FSUB /*102*/:
                return new C1441d(this);
            case Opcodes.LMUL /*105*/:
                return new bd(this);
            case Opcodes.FMUL /*106*/:
                return new C1459v(this);
            case Opcodes.IDIV /*108*/:
                c1448k = new C1453p();
                c1448k.m9783a(this);
                return c1448k;
            case Opcodes.LREM /*113*/:
                return new ae(this, Opcodes.LREM);
            case Opcodes.ISHR /*122*/:
                return new C1452o(this);
            case SmileConstants.TOKEN_PREFIX_TINY_UNICODE /*128*/:
                return new ao(this);
            case Opcodes.LOR /*129*/:
                return new ap(this);
            case Opcodes.IXOR /*130*/:
                return new C1463z(this);
            case Opcodes.LXOR /*131*/:
                return new aw(this);
            case Opcodes.IINC /*132*/:
                return new az(this);
            case Opcodes.I2L /*133*/:
                return new C1451n(this);
            case Opcodes.I2F /*134*/:
                this.f7001d.m9832c(1);
                byte d = this.f7001d.m9833d();
                return (d == 18 || d == 19) ? new C1461x(this) : new C1447j(this);
            case Opcodes.I2D /*135*/:
                return new C1455r(this);
            case Opcodes.L2D /*138*/:
                return new C1450m(this);
            case Opcodes.D2F /*144*/:
                return new af(this);
            case Opcodes.I2B /*145*/:
                return new aj(this);
            case Opcodes.I2C /*146*/:
                return new ax(this);
            case Opcodes.I2S /*147*/:
                return new ai(this);
            case Opcodes.LCMP /*148*/:
                return new ab(this);
            case Opcodes.FCMPL /*149*/:
                return new al(this);
            case Opcodes.FCMPG /*150*/:
                return new as(this);
            case Opcodes.DCMPL /*151*/:
                return new ac(this);
            case Opcodes.DCMPG /*152*/:
                return new C1439b(this);
            case Opcodes.IFEQ /*153*/:
                return new C1440c(this);
            case SmileConstants.TOKEN_PREFIX_SMALL_INT /*192*/:
                return new ag(this);
            case Opcodes.INSTANCEOF /*193*/:
                return new ad(this);
            case Opcodes.MULTIANEWARRAY /*197*/:
                return new at(this);
            case Opcodes.IFNULL /*198*/:
                return new aa(this);
            case Opcodes.IFNONNULL /*199*/:
                return new bh(this);
            case C2799f.f14282t /*200*/:
                return new be(this);
            case bj.f6779b /*201*/:
                return new bj(this);
            case C2799f.f14283u /*202*/:
                return new bm(this);
            case bn.f6797b /*203*/:
                return new bl(this);
            case C1458u.f6934b /*204*/:
                return new C1457t(this);
            default:
                c1448k = C1446i.m9762a(this.f7000c, this);
                if (c1448k != null) {
                    return c1448k;
                }
                Log.d("MILink", "UNKNOW MESSAGE - " + this.f7000c);
                return new C1462y(this);
        }
    }
}
