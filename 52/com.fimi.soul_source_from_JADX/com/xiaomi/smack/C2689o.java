package com.xiaomi.smack;

import com.tencent.mm.sdk.message.RMsgInfoDB;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.smack.C2678j.C2687a;
import com.xiaomi.smack.packet.C2694d;
import com.xiaomi.smack.util.C2715c;
import org.p122a.p123a.C2915a;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

/* renamed from: com.xiaomi.smack.o */
class C2689o {
    private Thread f13291a;
    private C2711t f13292b;
    private XmlPullParser f13293c;
    private boolean f13294d;

    protected C2689o(C2711t c2711t) {
        this.f13292b = c2711t;
        m15208a();
    }

    private void m15205a(C2694d c2694d) {
        if (c2694d != null) {
            for (C2687a a : this.f13292b.f.values()) {
                a.m15203a(c2694d);
            }
        }
    }

    private void m15206e() {
        this.f13293c = XmlPullParserFactory.newInstance().newPullParser();
        this.f13293c.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
        this.f13293c.setInput(this.f13292b.i);
    }

    private void m15207f() {
        try {
            m15206e();
            int eventType = this.f13293c.getEventType();
            String str = C2915a.f14760f;
            do {
                this.f13292b.m15178p();
                if (eventType == 2) {
                    String name = this.f13293c.getName();
                    if (this.f13293c.getName().equals(RMsgInfoDB.TABLE)) {
                        m15205a(C2715c.m15347a(this.f13293c));
                        str = name;
                    } else if (this.f13293c.getName().equals("iq")) {
                        m15205a(C2715c.m15346a(this.f13293c, this.f13292b));
                        str = name;
                    } else if (this.f13293c.getName().equals("presence")) {
                        m15205a(C2715c.m15349b(this.f13293c));
                        str = name;
                    } else if (this.f13293c.getName().equals("stream")) {
                        str = C2915a.f14760f;
                        for (int i = 0; i < this.f13293c.getAttributeCount(); i++) {
                            if (this.f13293c.getAttributeName(i).equals("from")) {
                                this.f13292b.m.m15135a(this.f13293c.getAttributeValue(i));
                            } else if (this.f13293c.getAttributeName(i).equals("challenge")) {
                                str = this.f13293c.getAttributeValue(i);
                            }
                        }
                        this.f13292b.m15157a(str);
                        str = name;
                    } else if (this.f13293c.getName().equals(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2)) {
                        throw new C2723w(C2715c.m15351d(this.f13293c));
                    } else {
                        if (this.f13293c.getName().equals("warning")) {
                            this.f13293c.next();
                            if (this.f13293c.getName().equals("multi-login")) {
                                m15209a(6, null);
                                str = name;
                            }
                        } else if (this.f13293c.getName().equals("bind")) {
                            m15205a(C2715c.m15350c(this.f13293c));
                            str = name;
                        }
                        str = name;
                    }
                } else if (eventType == 3 && this.f13293c.getName().equals("stream")) {
                    m15209a(13, null);
                }
                eventType = this.f13293c.next();
                if (this.f13294d) {
                    break;
                }
            } while (eventType != 1);
            if (eventType == 1) {
                throw new Exception("SMACK: server close the connection or timeout happened, last element name=" + str + " host=" + this.f13292b.m15332e());
            }
        } catch (Throwable e) {
            C2463b.m14125a(e);
            if (this.f13294d) {
                C2463b.m14126b("reader is shutdown, ignore the exception.");
            } else {
                m15209a(9, e);
            }
        }
    }

    protected void m15208a() {
        this.f13294d = false;
        this.f13291a = new C2690p(this, "Smack Packet Reader (" + this.f13292b.l + ")");
    }

    void m15209a(int i, Exception exception) {
        this.f13294d = true;
        this.f13292b.m15322a(i, exception);
    }

    public void m15210b() {
        this.f13291a.start();
    }

    public void m15211c() {
        this.f13294d = true;
    }

    void m15212d() {
        this.f13292b.f.clear();
    }
}
