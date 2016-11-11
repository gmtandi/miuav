package com.xiaomi.push.service;

import com.xiaomi.smack.packet.C2692a;
import com.xiaomi.smack.provider.C2646b;
import com.xiaomi.smack.provider.C2704c;
import com.xiaomi.smack.util.C2718g;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* renamed from: com.xiaomi.push.service.c */
public class C2647c implements C2646b {
    public static C2692a m15008a(XmlPullParser xmlPullParser) {
        List list = null;
        if (xmlPullParser.getEventType() != 2) {
            return null;
        }
        String[] strArr;
        String[] strArr2;
        int i;
        String str;
        String name = xmlPullParser.getName();
        String namespace = xmlPullParser.getNamespace();
        if (xmlPullParser.getAttributeCount() > 0) {
            strArr = new String[xmlPullParser.getAttributeCount()];
            strArr2 = new String[xmlPullParser.getAttributeCount()];
            for (i = 0; i < xmlPullParser.getAttributeCount(); i++) {
                strArr[i] = xmlPullParser.getAttributeName(i);
                strArr2[i] = C2718g.m15362b(xmlPullParser.getAttributeValue(i));
            }
            str = null;
        } else {
            str = null;
            strArr2 = null;
            strArr = null;
        }
        while (true) {
            i = xmlPullParser.next();
            if (i == 3) {
                return new C2692a(name, namespace, strArr, strArr2, str, list);
            }
            if (i == 4) {
                str = xmlPullParser.getText().trim();
            } else if (i == 2) {
                if (list == null) {
                    list = new ArrayList();
                }
                C2692a a = C2647c.m15008a(xmlPullParser);
                if (a != null) {
                    list.add(a);
                }
            }
        }
    }

    public void m15009a() {
        C2704c.m15286a().m15290a("all", "xm:chat", this);
    }

    public C2692a m15010b(XmlPullParser xmlPullParser) {
        int eventType = xmlPullParser.getEventType();
        while (eventType != 1 && eventType != 2) {
            eventType = xmlPullParser.next();
        }
        return eventType == 2 ? C2647c.m15008a(xmlPullParser) : null;
    }
}
