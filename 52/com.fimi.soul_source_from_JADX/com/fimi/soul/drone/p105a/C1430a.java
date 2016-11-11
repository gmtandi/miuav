package com.fimi.soul.drone.p105a;

import android.content.Context;
import com.fimi.soul.biz.p090b.C1253k;
import com.fimi.soul.biz.p100g.C1335b;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.p109a.C1439b;
import com.fimi.soul.drone.p107c.p108a.p109a.C1440c;
import com.fimi.soul.drone.p107c.p108a.p109a.C1441d;
import com.fimi.soul.drone.p107c.p108a.p109a.C1442e;
import com.fimi.soul.drone.p107c.p108a.p109a.C1443f;
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
import com.fimi.soul.drone.p107c.p108a.p109a.C1463z;
import com.fimi.soul.drone.p107c.p108a.p109a.aa;
import com.fimi.soul.drone.p107c.p108a.p109a.ab;
import com.fimi.soul.drone.p107c.p108a.p109a.ad;
import com.fimi.soul.drone.p107c.p108a.p109a.ae;
import com.fimi.soul.drone.p107c.p108a.p109a.af;
import com.fimi.soul.drone.p107c.p108a.p109a.ah;
import com.fimi.soul.drone.p107c.p108a.p109a.ai;
import com.fimi.soul.drone.p107c.p108a.p109a.aj;
import com.fimi.soul.drone.p107c.p108a.p109a.ak;
import com.fimi.soul.drone.p107c.p108a.p109a.am;
import com.fimi.soul.drone.p107c.p108a.p109a.ao;
import com.fimi.soul.drone.p107c.p108a.p109a.ap;
import com.fimi.soul.drone.p107c.p108a.p109a.aq;
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
import com.fimi.soul.drone.p110d.C1494u;
import com.fimi.soul.drone.p117h.C1583z;
import com.fimi.soul.drone.p117h.al;
import com.fimi.soul.entity.SavreDroneInFoBean;
import com.fimi.soul.module.calibcompass.C1683q;
import com.fimi.soul.module.dronemanage.C1719f;
import com.fimi.soul.module.p091a.C1664h;
import com.fimi.soul.service.C1950q;
import com.fimi.soul.utils.C1972l;
import com.tencent.connect.common.Constants;
import it.p074a.p075a.C2799f;
import java.util.concurrent.ConcurrentHashMap;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.util.TokenBuffer.Segment;

/* renamed from: com.fimi.soul.drone.a.a */
public class C1430a {
    private static final int f6423g = 1;
    private C1433a f6424a;
    private Context f6425b;
    private SavreDroneInFoBean f6426c;
    private C1950q f6427d;
    private C1719f f6428e;
    private boolean f6429f;
    private long f6430h;
    private ConcurrentHashMap<String, C1465c> f6431i;

    public C1430a(C1433a c1433a, Context context) {
        this.f6430h = 0;
        this.f6424a = c1433a;
        this.f6425b = context;
        this.f6426c = new SavreDroneInFoBean();
        this.f6431i = this.f6426c.getHashMap();
        this.f6427d = C1950q.m12185a();
        this.f6428e = C1719f.m11222a();
    }

    public void m9512a(C1437b c1437b) {
        switch (c1437b.f6533a) {
            case f6423g /*1*/:
                aq aqVar = (aq) c1437b;
                this.f6424a.m9616s().m10365a((double) aqVar.f6655d, (double) aqVar.f6656e, (double) aqVar.f6657f, (double) aqVar.f6658g, (double) aqVar.f6659h);
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                this.f6424a.m9595b(true);
                ah ahVar = (ah) c1437b;
                this.f6431i.put(Constants.VIA_SSO_LOGIN, ahVar.f6579d);
                this.f6424a.m9614q().m10275a((double) ahVar.f6583h, (double) ahVar.f6581f, (double) ahVar.f6585j, (double) ahVar.f6586k, (double) ahVar.f6587l, (double) ahVar.f6584i, (double) ahVar.f6582g, ahVar.f6580e, ahVar.f6588m, ahVar.f6589n, ahVar.f6592q, ahVar.f6593r, (double) ahVar.f6590o, (double) ahVar.f6591p);
                if (((ahVar.f6593r & 240) >> 4) == f6423g) {
                    this.f6424a.m9569P().m9997b(true);
                    this.f6424a.m9596c(true);
                    this.f6429f = true;
                } else {
                    this.f6424a.m9596c(false);
                    this.f6424a.m9569P().m9997b(false);
                    if (this.f6429f) {
                        this.f6424a.m9589a(C1584h.DRONEINFLOOR);
                    }
                    C1253k.m8598a(this.f6424a.f6507c).m8600a(0);
                    this.f6429f = false;
                }
                if ((ahVar.f6593r & 15) == f6423g) {
                    this.f6424a.m9599d(true);
                } else {
                    this.f6424a.m9599d(false);
                }
                C1494u.m9886a(this.f6424a);
                break;
            case Type.BYTE /*3*/:
                am amVar = (am) c1437b;
                this.f6431i.put(Constants.VIA_TO_TYPE_QQ_DISCUSS_GROUP, amVar.f6625k);
                this.f6424a.m9617t().m10357a((double) amVar.f6618d, amVar.f6619e, amVar.f6620f, amVar.f6621g, (double) amVar.f6622h, (double) amVar.f6623i, amVar.f6624j);
                break;
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                ak akVar = (ak) c1437b;
                this.f6431i.put(Constants.VIA_TO_TYPE_QZONE, akVar.f6610i);
                this.f6424a.m9618u().m10300a((double) akVar.f6605d, (double) akVar.f6606e, (double) akVar.f6607f, akVar.f6608g, akVar.f6609h, akVar.f6611j);
                break;
            case Type.INT /*5*/:
                C1442e c1442e = (C1442e) c1437b;
                this.f6431i.put(Constants.VIA_SHARE_TYPE_TEXT, c1442e.f6827o);
                this.f6424a.m9619v().m10515a(c1442e.f6816d, c1442e.f6817e, c1442e.f6818f, c1442e.f6819g, c1442e.f6820h, c1442e.f6821i, c1442e.f6822j, c1442e.f6823k, c1442e.f6824l, c1442e.f6826n);
                break;
            case Type.FLOAT /*6*/:
                ay ayVar = (ay) c1437b;
                this.f6424a.m9615r().m10254a((double) ayVar.f6698d, (double) ayVar.f6699e, (double) ayVar.f6700f, (double) ayVar.f6701g, (double) ayVar.f6702h, ayVar.f6703i, (double) ayVar.f6704j);
                break;
            case Type.LONG /*7*/:
                C1456s c1456s = (C1456s) c1437b;
                this.f6424a.m9604g().m10150a(c1456s.f6918d, c1456s.f6919e, c1456s.f6920f, c1456s.f6921g, c1456s.f6922h, c1456s.f6923i, c1456s.f6924j, c1456s.f6925k, c1456s.f6926l, c1456s.f6927m, c1456s.f6928n);
                break;
            case Type.ARRAY /*9*/:
                C1449l c1449l = (C1449l) c1437b;
                this.f6431i.put(C1972l.f10195n, c1449l.f6858b);
                this.f6424a.ab().m10204a(c1449l.m9774b(), c1449l.m9775c());
                break;
            case Type.OBJECT /*10*/:
                this.f6424a.m9593b(((C1460w) c1437b).f6947h);
                break;
            case Opcodes.T_LONG /*11*/:
                C1454q c1454q = (C1454q) c1437b;
                this.f6424a.ah().m10166a(c1454q.f6900d, c1454q.f6901e, c1454q.f6902f, c1454q.f6903g, c1454q.f6904h, c1454q.f6905i, c1454q.f6906j);
                break;
            case Segment.TOKENS_PER_SEGMENT /*16*/:
                ae aeVar = (ae) c1437b;
                this.f6431i.put(Constants.VIA_REPORT_TYPE_START_WAP, aeVar.f6567e);
                this.f6424a.m9568O().m10268a(aeVar.m9646b().m10269b());
                this.f6424a.m9589a(C1584h.GIMBAL_POSITION);
                break;
            case Opcodes.CALOAD /*52*/:
                C1448k c1448k = (C1448k) c1437b;
                C1583z e = this.f6424a.m9600e();
                e.m10630a((byte) c1448k.a);
                e.m10632b(c1448k.f6852g);
                e.m10634c(c1448k.f6853h);
                e.m10636d(c1448k.f6854i);
                e.m10638e(c1448k.f6855j);
                this.f6424a.m9589a(C1584h.EV_VALUE);
                break;
            case Opcodes.FADD /*98*/:
                ba baVar = (ba) c1437b;
                this.f6424a.m9561H().m10510a(baVar.f6719d, baVar.f6720e, baVar.f6721f);
                break;
            case Opcodes.DADD /*99*/:
                bb bbVar = (bb) c1437b;
                this.f6424a.m9560G().m10410a(bbVar.f6724d, bbVar.f6725e, bbVar.f6726f, bbVar.f6727g, bbVar.f6728h, bbVar.f6729i);
                break;
            case Opcodes.ISUB /*100*/:
                bc bcVar = (bc) c1437b;
                this.f6431i.put(com.fimi.soul.module.setting.am.f9227L, bcVar.f6743o);
                this.f6424a.m9562I().m10450a(bcVar.f6732d, bcVar.f6733e, bcVar.f6734f, bcVar.f6735g, bcVar.f6736h, bcVar.f6737i, bcVar.f6738j, bcVar.f6739k, bcVar.f6740l, bcVar.f6741m, bcVar.f6742n);
                break;
            case Opcodes.FSUB /*102*/:
                C1441d c1441d = (C1441d) c1437b;
                this.f6424a.m9576W().m10532a(c1441d.f6810d, c1441d.f6811e, c1441d.f6812f, c1441d.f6813g);
                break;
            case Opcodes.LMUL /*105*/:
                bd bdVar = (bd) c1437b;
                al f = this.f6424a.m9602f();
                f.m10323a(bdVar.f6746d);
                f.m10339h(bdVar.f6747e);
                f.m10337g(bdVar.f6748f);
                f.m10335f(bdVar.f6749g);
                f.m10333e(bdVar.f6750h);
                f.m10331d(bdVar.f6751i);
                f.m10329c(bdVar.f6752j);
                f.m10327b(bdVar.f6753k);
                f.m10324a(bdVar.f6754l);
                if (System.currentTimeMillis() - this.f6430h >= 1000) {
                    this.f6424a.m9589a(C1584h.Remotecontrol);
                    this.f6424a.m9589a(C1584h.NEWREMOTEMODEL);
                    this.f6430h = System.currentTimeMillis();
                    break;
                }
                break;
            case Opcodes.FMUL /*106*/:
                this.f6424a.m9578Y().m10420a(((C1459v) c1437b).f6940d);
                break;
            case Opcodes.IDIV /*108*/:
                C1453p c1453p = (C1453p) c1437b;
                com.fimi.soul.drone.p117h.am d = this.f6424a.m9598d();
                d.m10344a(c1453p.f6893i);
                d.m10346b(c1453p.f6894j);
                d.m10348c(c1453p.f6895k);
                d.m10350d(c1453p.f6896l);
                d.m10352e(c1453p.f6897m);
                this.f6424a.m9589a(C1584h.PAIR_CODE);
                break;
            case Opcodes.LREM /*113*/:
                this.f6424a.m9589a(C1584h.REGIMBAL_ONEKEY);
                break;
            case Opcodes.ISHR /*122*/:
                C1452o c1452o = (C1452o) c1437b;
                this.f6424a.ai().m10262a(c1452o.f6882d, c1452o.f6883e, c1452o.f6884f, c1452o.f6885g);
                break;
            case SmileConstants.TOKEN_PREFIX_TINY_UNICODE /*128*/:
                ao aoVar = (ao) c1437b;
                break;
            case Opcodes.LOR /*129*/:
                C1664h.m10813a(this.f6424a).m10827c(Opcodes.LOR);
                ap apVar = (ap) c1437b;
                C1335b.m8963a(apVar.f6652n);
                this.f6424a.m9613p().m10375a((double) apVar.f6642d, apVar.f6643e, apVar.f6644f, apVar.f6645g, (double) apVar.f6646h, apVar.f6647i, apVar.f6648j, apVar.f6649k, apVar.f6650l, apVar.f6651m, apVar.f6652n);
                break;
            case Opcodes.IXOR /*130*/:
                C1463z c1463z = (C1463z) c1437b;
                this.f6424a.m9609l().m10396a(c1463z.f6986d, c1463z.f6987e, c1463z.f6988f, (double) c1463z.f6989g, (double) c1463z.f6990h, (double) c1463z.f6991i, (double) c1463z.f6992j, c1463z.f6993k);
                break;
            case Opcodes.LXOR /*131*/:
                C1683q.m10886a().m10887a("131");
                aw awVar = (aw) c1437b;
                this.f6424a.m9608k().m10387a(awVar.f6684d, awVar.f6685e, awVar.f6686f, (double) awVar.f6687g, (double) awVar.f6688h, (double) awVar.f6689i, (double) awVar.f6690j, (double) awVar.f6691k);
                break;
            case Opcodes.IINC /*132*/:
                az azVar = (az) c1437b;
                break;
            case Opcodes.I2L /*133*/:
                C1451n c1451n = (C1451n) c1437b;
                this.f6424a.m9611n().m10431a((double) c1451n.f6872d, c1451n.f6873e, c1451n.f6874f, (double) c1451n.f6875g, c1451n.f6876h, (double) c1451n.f6877i, (double) c1451n.f6878j, (double) c1451n.f6879k);
                break;
            case Opcodes.I2F /*134*/:
                C1447j c1447j = (C1447j) c1437b;
                this.f6424a.m9612o().m10617a(c1447j.f6843d, c1447j.f6844e, c1447j.f6845f, c1447j.f6846g);
                break;
            case Opcodes.I2D /*135*/:
                C1455r c1455r = (C1455r) c1437b;
                if (c1455r.f6915j.f6999b != 8) {
                    this.f6424a.aj().m10312b(c1455r.f6915j);
                    if (this.f6424a.aj().f7366h != f6423g || this.f6424a.aj().f7365g != null) {
                        if (this.f6424a.aj().f7366h == f6423g) {
                            this.f6424a.aj().m10311a(false);
                            break;
                        }
                    }
                    this.f6424a.aj().m10311a(true);
                    break;
                }
                this.f6424a.aj().m10310a(c1455r.f6915j);
                if (this.f6424a.aj().f7365g != null) {
                    this.f6424a.aj().m10311a(false);
                    break;
                } else {
                    this.f6424a.aj().m10311a(true);
                    break;
                }
                break;
            case Opcodes.L2D /*138*/:
                C1450m c1450m = (C1450m) c1437b;
                break;
            case Opcodes.D2F /*144*/:
                af afVar = (af) c1437b;
                this.f6424a.m9589a(C1584h.REHOME);
                C1335b.m8963a(afVar.f6572e);
                break;
            case Opcodes.I2B /*145*/:
                aj ajVar = (aj) c1437b;
                this.f6424a.m9589a(C1584h.RELANDING);
                C1335b.m8963a(ajVar.f6602e);
                break;
            case Opcodes.I2C /*146*/:
                this.f6428e.m11224a(((ax) c1437b).f6695e, this.f6425b);
                this.f6424a.m9589a(C1584h.RETAKEUP);
                break;
            case Opcodes.I2S /*147*/:
                ai aiVar = (ai) c1437b;
                this.f6424a.m9559F().m10426a(aiVar.f6596d, aiVar.f6597e);
                C1664h.m10813a(this.f6424a).m10827c(Opcodes.I2S);
                C1335b.m8963a(aiVar.f6598f);
                break;
            case Opcodes.LCMP /*148*/:
                C1664h.m10813a(this.f6424a).m10827c(Opcodes.LCMP);
                this.f6424a.m9589a(C1584h.CLOSEOUTTIMEPROBAR);
                ab abVar = (ab) c1437b;
                C1335b.m8963a(abVar.f6548e);
                this.f6424a.m9555B().m10232a(abVar.f6547d, abVar.f6548e);
                break;
            case Opcodes.DCMPG /*152*/:
                C1439b c1439b = (C1439b) c1437b;
                this.f6424a.m9589a(C1584h.RECEIVEFOLLOWME);
                this.f6424a.m9610m().m10371a((double) c1439b.f6714d, c1439b.f6715e, c1439b.f6716f);
                break;
            case Opcodes.IFEQ /*153*/:
                C1440c c1440c = (C1440c) c1437b;
                this.f6424a.m9605h().m10600a(c1440c.f6802d, c1440c.f6803e, c1440c.f6804f, c1440c.f6805g, c1440c.f6806h);
                break;
            case Opcodes.INSTANCEOF /*193*/:
                ad adVar = (ad) c1437b;
                this.f6424a.m9606i().m10245a(adVar.f6555d, adVar.f6557f, adVar.f6558g, adVar.f6556e, adVar.f6559h, adVar.f6560i, adVar.f6561j, adVar.f6562k);
                break;
            case Opcodes.IFNULL /*198*/:
                aa aaVar = (aa) c1437b;
                this.f6424a.m9623z().m10526a(aaVar.f6540d, aaVar.f6541e, aaVar.f6542f, aaVar.f6543g, aaVar.f6544h);
                break;
            case Opcodes.IFNONNULL /*199*/:
                bh bhVar = (bh) c1437b;
                this.f6424a.m9564K().m10489a(bhVar.f6772d, bhVar.f6773e);
                break;
            case C2799f.f14282t /*200*/:
                be beVar = (be) c1437b;
                this.f6424a.m9565L().m10485a(beVar.f6757d, beVar.f6758e, beVar.f6759f);
                break;
            case bj.f6779b /*201*/:
                bj bjVar = (bj) c1437b;
                this.f6424a.m9563J().m10492a(bjVar.f6781d, bjVar.f6782e, bjVar.f6783f);
                break;
            case C2799f.f14283u /*202*/:
                bm bmVar = (bm) c1437b;
                this.f6424a.m9566M().m10480a(bmVar.f6795d, bmVar.f6796e);
                break;
            case bn.f6797b /*203*/:
                bl blVar = (bl) c1437b;
                this.f6424a.m9567N().m10482a(blVar.f6791d, blVar.f6792e);
                break;
            case C1458u.f6934b /*204*/:
                C1457t c1457t = (C1457t) c1437b;
                this.f6424a.m9577X().m10405a(c1457t.f6931d, c1457t.f6932e, c1457t.f6933f);
                break;
            case C1461x.f6948b /*291*/:
                C1664h.m10813a(this.f6424a).m10827c(Opcodes.I2F);
                C1461x c1461x = (C1461x) c1437b;
                C1335b.m8963a(c1461x.f6959m);
                this.f6424a.an().m10138a(c1461x.f6950d, c1461x.f6951e, c1461x.f6952f, c1461x.f6953g, c1461x.f6954h, (short) c1461x.f6955i, c1461x.f6956j, c1461x.f6957k, c1461x.f6958l, c1461x.f6959m);
                break;
        }
        C1443f.m9732a(c1437b, this.f6424a);
    }
}
