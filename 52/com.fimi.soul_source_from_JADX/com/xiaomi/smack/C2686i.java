package com.xiaomi.smack;

import android.text.TextUtils;
import com.tencent.mm.sdk.message.RMsgInfoDB;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.kenai.jbosh.C2501b;
import com.xiaomi.kenai.jbosh.C2527z;
import com.xiaomi.kenai.jbosh.ab;
import com.xiaomi.kenai.jbosh.ag;
import com.xiaomi.smack.packet.C2699f;
import com.xiaomi.smack.packet.C2699f.C2698b;
import com.xiaomi.smack.util.C2715c;
import java.io.StringReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

/* renamed from: com.xiaomi.smack.i */
public class C2686i implements C2527z {
    private C2679b f13288a;

    public C2686i(C2679b c2679b) {
        this.f13288a = c2679b;
    }

    public boolean m15202a(ab abVar) {
        Exception exception;
        C2501b a = abVar.m14307a();
        boolean z = false;
        if (a != null) {
            try {
                boolean z2;
                XmlPullParser newPullParser;
                int next;
                if (this.f13288a.m15171i()) {
                    Object a2 = a.m14336a(ag.m14315a("xm", "challenge"));
                    if (!TextUtils.isEmpty(a2)) {
                        this.f13288a.m15157a((String) a2);
                        z2 = true;
                        if (this.f13288a.f13266b == null) {
                            this.f13288a.f13266b = a.m14336a(ag.m14315a("xm", "sid"));
                            z2 = true;
                        }
                        if (this.f13288a.f13265a == null) {
                            this.f13288a.f13265a = a.m14336a(ag.m14315a("xm", "authid"));
                            z2 = true;
                        }
                        newPullParser = XmlPullParserFactory.newInstance().newPullParser();
                        newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
                        newPullParser.setInput(new StringReader(a.m14338b()));
                        newPullParser.getEventType();
                        while (true) {
                            next = newPullParser.next();
                            this.f13288a.m15178p();
                            if (next == 2) {
                                if (newPullParser.getName().equals("body")) {
                                    z = z2;
                                } else if (newPullParser.getName().equals(RMsgInfoDB.TABLE)) {
                                    this.f13288a.m15195b(C2715c.m15347a(newPullParser));
                                    z = true;
                                } else if (newPullParser.getName().equals("iq")) {
                                    this.f13288a.m15195b(C2715c.m15346a(newPullParser, this.f13288a));
                                    z = true;
                                } else if (newPullParser.getName().equals("presence")) {
                                    this.f13288a.m15195b(C2715c.m15349b(newPullParser));
                                    z = true;
                                } else if (newPullParser.getName().equals("challenge")) {
                                    this.f13288a.m15157a(newPullParser.nextText());
                                    z = true;
                                } else if (newPullParser.getName().equals(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2)) {
                                    break;
                                } else {
                                    if (newPullParser.getName().equals("warning")) {
                                        try {
                                            newPullParser.next();
                                            if (newPullParser.getName().equals("multi-login")) {
                                                this.f13288a.m15190a(new C2699f(C2698b.unavailable), 6, null);
                                                z = true;
                                            }
                                        } catch (Exception e) {
                                            Exception exception2 = e;
                                            z = true;
                                            exception = exception2;
                                        }
                                    } else if (newPullParser.getName().equals("bind")) {
                                        this.f13288a.m15195b(C2715c.m15350c(newPullParser));
                                    }
                                    z = true;
                                }
                                if (next != 1) {
                                    break;
                                }
                                z2 = z;
                            }
                            z = z2;
                            if (next != 1) {
                                break;
                            }
                            z2 = z;
                        }
                        throw new C2723w(C2715c.m15351d(newPullParser));
                    }
                }
                z2 = false;
                try {
                    if (this.f13288a.f13266b == null) {
                        this.f13288a.f13266b = a.m14336a(ag.m14315a("xm", "sid"));
                        z2 = true;
                    }
                    if (this.f13288a.f13265a == null) {
                        this.f13288a.f13265a = a.m14336a(ag.m14315a("xm", "authid"));
                        z2 = true;
                    }
                    newPullParser = XmlPullParserFactory.newInstance().newPullParser();
                    newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
                    newPullParser.setInput(new StringReader(a.m14338b()));
                    newPullParser.getEventType();
                    while (true) {
                        next = newPullParser.next();
                        this.f13288a.m15178p();
                        if (next == 2) {
                            if (newPullParser.getName().equals("body")) {
                                z = z2;
                            } else if (newPullParser.getName().equals(RMsgInfoDB.TABLE)) {
                                this.f13288a.m15195b(C2715c.m15347a(newPullParser));
                                z = true;
                            } else if (newPullParser.getName().equals("iq")) {
                                this.f13288a.m15195b(C2715c.m15346a(newPullParser, this.f13288a));
                                z = true;
                            } else if (newPullParser.getName().equals("presence")) {
                                this.f13288a.m15195b(C2715c.m15349b(newPullParser));
                                z = true;
                            } else if (newPullParser.getName().equals("challenge")) {
                                this.f13288a.m15157a(newPullParser.nextText());
                                z = true;
                            } else if (newPullParser.getName().equals(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2)) {
                                break;
                                throw new C2723w(C2715c.m15351d(newPullParser));
                            } else {
                                if (newPullParser.getName().equals("warning")) {
                                    newPullParser.next();
                                    if (newPullParser.getName().equals("multi-login")) {
                                        this.f13288a.m15190a(new C2699f(C2698b.unavailable), 6, null);
                                        z = true;
                                    }
                                } else if (newPullParser.getName().equals("bind")) {
                                    this.f13288a.m15195b(C2715c.m15350c(newPullParser));
                                }
                                z = true;
                            }
                            if (next != 1) {
                                break;
                            }
                            z2 = z;
                        }
                        z = z2;
                        if (next != 1) {
                            break;
                        }
                        z2 = z;
                    }
                } catch (Exception e2) {
                    exception = e2;
                    z = z2;
                }
            } catch (Exception e3) {
                exception = e3;
                if (this.f13288a.m15172j()) {
                    this.f13288a.m15191a(exception);
                }
                return z;
            }
        }
        return z;
    }
}
