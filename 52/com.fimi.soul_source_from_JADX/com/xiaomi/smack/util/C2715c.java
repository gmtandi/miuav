package com.xiaomi.smack.util;

import android.text.TextUtils;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.message.RMsgInfo;
import com.tencent.mm.sdk.message.RMsgInfoDB;
import com.tencent.mm.sdk.platformtools.LocaleUtil;
import com.tencent.open.SocialConstants;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.push.service.C2647c;
import com.xiaomi.push.service.C2669v;
import com.xiaomi.push.service.C2669v.C2667b;
import com.xiaomi.push.service.C2673z;
import com.xiaomi.smack.C2678j;
import com.xiaomi.smack.C2710s.C2709b;
import com.xiaomi.smack.C2710s.C2709b.C2708a;
import com.xiaomi.smack.C2723w;
import com.xiaomi.smack.packet.C2692a;
import com.xiaomi.smack.packet.C2694d;
import com.xiaomi.smack.packet.C2695b;
import com.xiaomi.smack.packet.C2695b.C2693a;
import com.xiaomi.smack.packet.C2696c;
import com.xiaomi.smack.packet.C2699f;
import com.xiaomi.smack.packet.C2699f.C2697a;
import com.xiaomi.smack.packet.C2699f.C2698b;
import com.xiaomi.smack.packet.C2700g;
import com.xiaomi.smack.packet.C2702h;
import com.xiaomi.smack.packet.C2702h.C2701a;
import com.xiaomi.smack.provider.C2704c;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.p122a.p123a.C2915a;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* renamed from: com.xiaomi.smack.util.c */
public class C2715c {
    private static XmlPullParser f13423a;

    static {
        f13423a = null;
    }

    public static C2692a m15345a(String str, String str2, XmlPullParser xmlPullParser) {
        Object a = C2704c.m15286a().m15289a("all", "xm:chat");
        return (a == null || !(a instanceof C2647c)) ? null : ((C2647c) a).m15010b(xmlPullParser);
    }

    public static C2695b m15346a(XmlPullParser xmlPullParser, C2678j c2678j) {
        String attributeValue = xmlPullParser.getAttributeValue(C2915a.f14760f, LocaleUtil.INDONESIAN);
        String attributeValue2 = xmlPullParser.getAttributeValue(C2915a.f14760f, "to");
        String attributeValue3 = xmlPullParser.getAttributeValue(C2915a.f14760f, "from");
        String attributeValue4 = xmlPullParser.getAttributeValue(C2915a.f14760f, "chid");
        C2693a a = C2693a.m15225a(xmlPullParser.getAttributeValue(C2915a.f14760f, SocialConstants.PARAM_TYPE));
        Object obj = null;
        C2702h c2702h = null;
        C2695b c2695b = null;
        while (obj == null) {
            Object obj2;
            C2695b c2695b2;
            C2702h c2702h2;
            Object obj3;
            int next = xmlPullParser.next();
            if (next == 2) {
                String name = xmlPullParser.getName();
                String namespace = xmlPullParser.getNamespace();
                if (name.equals(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2)) {
                    c2702h = C2715c.m15352e(xmlPullParser);
                } else {
                    c2695b = new C2695b();
                    c2695b.m15229a(C2715c.m15345a(name, namespace, xmlPullParser));
                }
                obj2 = obj;
                c2695b2 = c2695b;
                c2702h2 = c2702h;
                obj3 = obj2;
            } else if (next == 3 && xmlPullParser.getName().equals("iq")) {
                c2695b2 = c2695b;
                c2702h2 = c2702h;
                int i = 1;
            } else {
                obj2 = obj;
                c2695b2 = c2695b;
                c2702h2 = c2702h;
                obj3 = obj2;
            }
            obj2 = obj3;
            c2702h = c2702h2;
            c2695b = c2695b2;
            obj = obj2;
        }
        if (c2695b == null) {
            if (C2693a.f13302a == a || C2693a.f13303b == a) {
                C2694d c2716d = new C2716d();
                c2716d.m15233k(attributeValue);
                c2716d.m15237m(attributeValue3);
                c2716d.m15239n(attributeValue2);
                c2716d.m15250a(C2693a.f13305d);
                c2716d.m15235l(attributeValue4);
                c2716d.m15230a(new C2702h(C2701a.f13359e));
                c2678j.m15155a(c2716d);
                C2463b.m14127c("iq usage error. send packet in packet parser.");
                return null;
            }
            c2695b = new C2717e();
        }
        c2695b.m15233k(attributeValue);
        c2695b.m15237m(attributeValue2);
        c2695b.m15235l(attributeValue4);
        c2695b.m15239n(attributeValue3);
        c2695b.m15250a(a);
        c2695b.m15230a(c2702h);
        return c2695b;
    }

    public static C2694d m15347a(XmlPullParser xmlPullParser) {
        String attributeValue;
        boolean z;
        if (Constants.VIA_TO_TYPE_QQ_GROUP.equals(xmlPullParser.getAttributeValue(C2915a.f14760f, "s"))) {
            attributeValue = xmlPullParser.getAttributeValue(C2915a.f14760f, "chid");
            String attributeValue2 = xmlPullParser.getAttributeValue(C2915a.f14760f, LocaleUtil.INDONESIAN);
            String attributeValue3 = xmlPullParser.getAttributeValue(C2915a.f14760f, "from");
            String attributeValue4 = xmlPullParser.getAttributeValue(C2915a.f14760f, "to");
            String attributeValue5 = xmlPullParser.getAttributeValue(C2915a.f14760f, SocialConstants.PARAM_TYPE);
            C2667b b = C2669v.m15106a().m15114b(attributeValue, attributeValue4);
            C2667b b2 = b == null ? C2669v.m15106a().m15114b(attributeValue, attributeValue3) : b;
            if (b2 == null) {
                throw new C2723w("the channel id is wrong while receiving a encrypted message");
            }
            z = false;
            C2694d c2694d = null;
            while (!z) {
                int next = xmlPullParser.next();
                if (next == 2) {
                    if (!"s".equals(xmlPullParser.getName())) {
                        throw new C2723w("error while receiving a encrypted message with wrong format");
                    } else if (xmlPullParser.next() != 4) {
                        throw new C2723w("error while receiving a encrypted message with wrong format");
                    } else {
                        String text = xmlPullParser.getText();
                        if (Constants.VIA_SHARE_TYPE_TEXT.equals(attributeValue) || Constants.VIA_SHARE_TYPE_INFO.equals(attributeValue)) {
                            c2694d = new C2696c();
                            c2694d.m15235l(attributeValue);
                            c2694d.m15259b(true);
                            c2694d.m15239n(attributeValue3);
                            c2694d.m15237m(attributeValue4);
                            c2694d.m15233k(attributeValue2);
                            c2694d.m15266f(attributeValue5);
                            C2692a c2692a = new C2692a("s", null, (String[]) null, (String[]) null);
                            c2692a.m15220b(text);
                            c2694d.m15229a(c2692a);
                            return c2694d;
                        }
                        C2715c.m15348a(C2673z.m15131b(C2673z.m15128a(b2.f13185i, attributeValue2), text));
                        f13423a.next();
                        c2694d = C2715c.m15347a(f13423a);
                    }
                } else if (next == 3 && xmlPullParser.getName().equals(RMsgInfoDB.TABLE)) {
                    z = true;
                }
            }
            if (c2694d != null) {
                return c2694d;
            }
            throw new C2723w("error while receiving a encrypted message with wrong format");
        }
        Object attributeValue6;
        Object attributeValue7;
        C2694d c2696c = new C2696c();
        String attributeValue8 = xmlPullParser.getAttributeValue(C2915a.f14760f, LocaleUtil.INDONESIAN);
        if (attributeValue8 == null) {
            attributeValue8 = "ID_NOT_AVAILABLE";
        }
        c2696c.m15233k(attributeValue8);
        c2696c.m15237m(xmlPullParser.getAttributeValue(C2915a.f14760f, "to"));
        c2696c.m15239n(xmlPullParser.getAttributeValue(C2915a.f14760f, "from"));
        c2696c.m15235l(xmlPullParser.getAttributeValue(C2915a.f14760f, "chid"));
        c2696c.m15254a(xmlPullParser.getAttributeValue(C2915a.f14760f, SocialConstants.PARAM_APP_ID));
        try {
            attributeValue6 = xmlPullParser.getAttributeValue(C2915a.f14760f, "transient");
        } catch (Exception e) {
            attributeValue6 = null;
        }
        try {
            attributeValue = xmlPullParser.getAttributeValue(C2915a.f14760f, "seq");
            if (!TextUtils.isEmpty(attributeValue)) {
                c2696c.m15258b(attributeValue);
            }
        } catch (Exception e2) {
        }
        try {
            attributeValue7 = xmlPullParser.getAttributeValue(C2915a.f14760f, "mseq");
            if (!TextUtils.isEmpty(attributeValue7)) {
                c2696c.m15260c(attributeValue7);
            }
        } catch (Exception e3) {
        }
        try {
            attributeValue7 = xmlPullParser.getAttributeValue(C2915a.f14760f, "fseq");
            if (!TextUtils.isEmpty(attributeValue7)) {
                c2696c.m15262d(attributeValue7);
            }
        } catch (Exception e4) {
        }
        try {
            attributeValue7 = xmlPullParser.getAttributeValue(C2915a.f14760f, RMsgInfo.COL_STATUS);
            if (!TextUtils.isEmpty(attributeValue7)) {
                c2696c.m15264e(attributeValue7);
            }
        } catch (Exception e5) {
        }
        z = !TextUtils.isEmpty(attributeValue6) && attributeValue6.equalsIgnoreCase("true");
        c2696c.m15256a(z);
        c2696c.m15266f(xmlPullParser.getAttributeValue(C2915a.f14760f, SocialConstants.PARAM_TYPE));
        attributeValue8 = C2715c.m15354g(xmlPullParser);
        if (attributeValue8 == null || C2915a.f14760f.equals(attributeValue8.trim())) {
            C2694d.m15227u();
        } else {
            c2696c.m15273j(attributeValue8);
        }
        attributeValue8 = null;
        boolean z2 = false;
        while (!z2) {
            int next2 = xmlPullParser.next();
            if (next2 == 2) {
                attributeValue = xmlPullParser.getName();
                String namespace = xmlPullParser.getNamespace();
                if (TextUtils.isEmpty(namespace)) {
                    namespace = "xm";
                }
                if (attributeValue.equals("subject")) {
                    if (C2715c.m15354g(xmlPullParser) == null) {
                        c2696c.m15268g(C2715c.m15353f(xmlPullParser));
                    } else {
                        c2696c.m15268g(C2715c.m15353f(xmlPullParser));
                    }
                } else if (attributeValue.equals("body")) {
                    Object attributeValue9 = xmlPullParser.getAttributeValue(C2915a.f14760f, "encode");
                    attributeValue = C2715c.m15353f(xmlPullParser);
                    if (TextUtils.isEmpty(attributeValue9)) {
                        c2696c.m15270h(attributeValue);
                    } else {
                        c2696c.m15255a(attributeValue, attributeValue9);
                    }
                } else if (attributeValue.equals("thread")) {
                    if (attributeValue8 == null) {
                        attributeValue8 = xmlPullParser.nextText();
                    }
                } else if (attributeValue.equals(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2)) {
                    c2696c.m15230a(C2715c.m15352e(xmlPullParser));
                } else {
                    c2696c.m15229a(C2715c.m15345a(attributeValue, namespace, xmlPullParser));
                }
            } else if (next2 == 3 && xmlPullParser.getName().equals(RMsgInfoDB.TABLE)) {
                z2 = true;
            }
        }
        c2696c.m15272i(attributeValue8);
        return c2696c;
    }

    private static void m15348a(byte[] bArr) {
        if (f13423a == null) {
            try {
                f13423a = XmlPullParserFactory.newInstance().newPullParser();
                f13423a.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
        }
        f13423a.setInput(new InputStreamReader(new ByteArrayInputStream(bArr)));
    }

    public static C2699f m15349b(XmlPullParser xmlPullParser) {
        C2698b c2698b = C2698b.available;
        String attributeValue = xmlPullParser.getAttributeValue(C2915a.f14760f, SocialConstants.PARAM_TYPE);
        if (!(attributeValue == null || attributeValue.equals(C2915a.f14760f))) {
            try {
                c2698b = C2698b.valueOf(attributeValue);
            } catch (IllegalArgumentException e) {
                System.err.println("Found invalid presence type " + attributeValue);
            }
        }
        C2699f c2699f = new C2699f(c2698b);
        c2699f.m15237m(xmlPullParser.getAttributeValue(C2915a.f14760f, "to"));
        c2699f.m15239n(xmlPullParser.getAttributeValue(C2915a.f14760f, "from"));
        c2699f.m15235l(xmlPullParser.getAttributeValue(C2915a.f14760f, "chid"));
        String attributeValue2 = xmlPullParser.getAttributeValue(C2915a.f14760f, LocaleUtil.INDONESIAN);
        if (attributeValue2 == null) {
            attributeValue2 = "ID_NOT_AVAILABLE";
        }
        c2699f.m15233k(attributeValue2);
        int i = 0;
        while (i == 0) {
            int next = xmlPullParser.next();
            if (next == 2) {
                String name = xmlPullParser.getName();
                String namespace = xmlPullParser.getNamespace();
                if (name.equals(RMsgInfo.COL_STATUS)) {
                    c2699f.m15278a(xmlPullParser.nextText());
                } else if (name.equals("priority")) {
                    try {
                        c2699f.m15275a(Integer.parseInt(xmlPullParser.nextText()));
                    } catch (NumberFormatException e2) {
                    } catch (IllegalArgumentException e3) {
                        c2699f.m15275a(0);
                    }
                } else if (name.equals("show")) {
                    name = xmlPullParser.nextText();
                    try {
                        c2699f.m15276a(C2697a.valueOf(name));
                    } catch (IllegalArgumentException e4) {
                        System.err.println("Found invalid presence mode " + name);
                    }
                } else if (name.equals(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2)) {
                    c2699f.m15230a(C2715c.m15352e(xmlPullParser));
                } else {
                    c2699f.m15229a(C2715c.m15345a(name, namespace, xmlPullParser));
                }
            } else if (next == 3 && xmlPullParser.getName().equals("presence")) {
                i = 1;
            }
        }
        return c2699f;
    }

    public static C2709b m15350c(XmlPullParser xmlPullParser) {
        C2709b c2709b = new C2709b();
        String attributeValue = xmlPullParser.getAttributeValue(C2915a.f14760f, LocaleUtil.INDONESIAN);
        String attributeValue2 = xmlPullParser.getAttributeValue(C2915a.f14760f, "to");
        String attributeValue3 = xmlPullParser.getAttributeValue(C2915a.f14760f, "from");
        String attributeValue4 = xmlPullParser.getAttributeValue(C2915a.f14760f, "chid");
        C2708a a = C2708a.m15306a(xmlPullParser.getAttributeValue(C2915a.f14760f, SocialConstants.PARAM_TYPE));
        c2709b.m15233k(attributeValue);
        c2709b.m15237m(attributeValue2);
        c2709b.m15239n(attributeValue3);
        c2709b.m15235l(attributeValue4);
        c2709b.m15308a(a);
        Object obj = null;
        C2702h c2702h = null;
        while (obj == null) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2)) {
                    c2702h = C2715c.m15352e(xmlPullParser);
                } else if (xmlPullParser.getName().equals("p")) {
                    try {
                        c2709b.m15309a(true, Integer.parseInt(xmlPullParser.getAttributeValue(C2915a.f14760f, "period")));
                    } catch (NumberFormatException e) {
                        C2463b.m14127c("Bind result parse error: " + e.toString());
                    }
                }
            } else if (next == 3 && xmlPullParser.getName().equals("bind")) {
                obj = 1;
            }
        }
        c2709b.m15230a(c2702h);
        return c2709b;
    }

    public static C2700g m15351d(XmlPullParser xmlPullParser) {
        C2700g c2700g = null;
        Object obj = null;
        while (obj == null) {
            int next = xmlPullParser.next();
            if (next == 2) {
                c2700g = new C2700g(xmlPullParser.getName());
            } else if (next == 3 && xmlPullParser.getName().equals(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2)) {
                obj = 1;
            }
        }
        return c2700g;
    }

    public static C2702h m15352e(XmlPullParser xmlPullParser) {
        String attributeValue;
        String attributeValue2;
        String str = "urn:ietf:params:xml:ns:xmpp-stanzas";
        List arrayList = new ArrayList();
        String str2 = null;
        String str3 = null;
        String str4 = "-1";
        int i = 0;
        while (i < xmlPullParser.getAttributeCount()) {
            attributeValue = xmlPullParser.getAttributeName(i).equals(XiaomiOAuthConstants.EXTRA_CODE_2) ? xmlPullParser.getAttributeValue(C2915a.f14760f, XiaomiOAuthConstants.EXTRA_CODE_2) : str4;
            attributeValue2 = xmlPullParser.getAttributeName(i).equals(SocialConstants.PARAM_TYPE) ? xmlPullParser.getAttributeValue(C2915a.f14760f, SocialConstants.PARAM_TYPE) : str3;
            if (xmlPullParser.getAttributeName(i).equals("reason")) {
                str2 = xmlPullParser.getAttributeValue(C2915a.f14760f, "reason");
            }
            i++;
            str3 = attributeValue2;
            str4 = attributeValue;
        }
        Object obj = null;
        attributeValue2 = null;
        attributeValue = null;
        while (obj == null) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("text")) {
                    attributeValue = xmlPullParser.nextText();
                } else {
                    String name = xmlPullParser.getName();
                    String namespace = xmlPullParser.getNamespace();
                    if ("urn:ietf:params:xml:ns:xmpp-stanzas".equals(namespace)) {
                        attributeValue2 = name;
                    } else {
                        arrayList.add(C2715c.m15345a(name, namespace, xmlPullParser));
                    }
                }
            } else if (next == 3) {
                if (xmlPullParser.getName().equals(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2)) {
                    obj = 1;
                }
            } else if (next == 4) {
                attributeValue = xmlPullParser.getText();
            }
        }
        return new C2702h(Integer.parseInt(str4), str3 == null ? "cancel" : str3, str2, attributeValue2, attributeValue, arrayList);
    }

    private static String m15353f(XmlPullParser xmlPullParser) {
        String str = C2915a.f14760f;
        int depth = xmlPullParser.getDepth();
        while (true) {
            if (xmlPullParser.next() == 3 && xmlPullParser.getDepth() == depth) {
                return str;
            }
            str = str + xmlPullParser.getText();
        }
    }

    private static String m15354g(XmlPullParser xmlPullParser) {
        int i = 0;
        while (i < xmlPullParser.getAttributeCount()) {
            String attributeName = xmlPullParser.getAttributeName(i);
            if ("xml:lang".equals(attributeName) || ("lang".equals(attributeName) && "xml".equals(xmlPullParser.getAttributePrefix(i)))) {
                return xmlPullParser.getAttributeValue(i);
            }
            i++;
        }
        return null;
    }
}
