package com.p016a;

import com.fimi.soul.entity.User;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/* renamed from: com.a.fx */
class fx extends DefaultHandler {
    fx() {
    }

    public void characters(char[] cArr, int i, int i2) {
        if (fw.f1242b) {
            fw.f1241a = new String(cArr, i, i2);
        }
    }

    public void endElement(String str, String str2, String str3) {
        fw.f1242b = false;
    }

    public void startElement(String str, String str2, String str3, Attributes attributes) {
        if (str2.equals("string") && "UTDID".equals(attributes.getValue(User.FN_NAME))) {
            fw.f1242b = true;
        }
    }
}
