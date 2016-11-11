package com.xiaomi.kenai.jbosh;

import java.lang.ref.SoftReference;
import java.util.logging.Logger;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

final class ae implements ac {
    private static final Logger f12639a;
    private static final ThreadLocal<SoftReference<XmlPullParser>> f12640b;

    static {
        f12639a = Logger.getLogger(ae.class.getName());
        f12640b = new af();
    }

    ae() {
    }

    private static XmlPullParser m14311a() {
        XmlPullParser xmlPullParser = (XmlPullParser) ((SoftReference) f12640b.get()).get();
        if (xmlPullParser != null) {
            return xmlPullParser;
        }
        try {
            XmlPullParserFactory newInstance = XmlPullParserFactory.newInstance();
            newInstance.setNamespaceAware(true);
            newInstance.setValidating(false);
            xmlPullParser = newInstance.newPullParser();
            f12640b.set(new SoftReference(xmlPullParser));
            return xmlPullParser;
        } catch (Throwable e) {
            throw new IllegalStateException("Could not create XmlPull parser", e);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xiaomi.kenai.jbosh.ad m14312a(java.lang.String r11) {
        /*
        r10 = this;
        r3 = new com.xiaomi.kenai.jbosh.ad;
        r3.<init>();
        r4 = m14311a();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r0 = new java.io.StringReader;	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r0.<init>(r11);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r4.setInput(r0);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r0 = r4.getEventType();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
    L_0x0015:
        r1 = 1;
        if (r0 == r1) goto L_0x018d;
    L_0x0018:
        r1 = 2;
        if (r0 != r1) goto L_0x0118;
    L_0x001b:
        r0 = f12639a;	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r1 = java.util.logging.Level.FINEST;	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r0 = r0.isLoggable(r1);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        if (r0 == 0) goto L_0x0041;
    L_0x0025:
        r0 = f12639a;	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r1 = new java.lang.StringBuilder;	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r1.<init>();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r2 = "Start tag: ";
        r1 = r1.append(r2);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r2 = r4.getName();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r1 = r1.append(r2);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r1 = r1.toString();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r0.finest(r1);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
    L_0x0041:
        r0 = r4.getPrefix();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        if (r0 != 0) goto L_0x0049;
    L_0x0047:
        r0 = "";
    L_0x0049:
        r1 = r4.getNamespace();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r2 = r4.getName();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r5 = new com.xiaomi.kenai.jbosh.an;	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r5.<init>(r1, r2, r0);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r6 = f12639a;	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r7 = java.util.logging.Level.FINEST;	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r6 = r6.isLoggable(r7);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        if (r6 == 0) goto L_0x00af;
    L_0x0060:
        r6 = f12639a;	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r7 = "Start element: ";
        r6.finest(r7);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r6 = f12639a;	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r7 = new java.lang.StringBuilder;	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r7.<init>();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r8 = "    prefix: ";
        r7 = r7.append(r8);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r0 = r7.append(r0);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r0 = r0.toString();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r6.finest(r0);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r0 = f12639a;	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r6 = new java.lang.StringBuilder;	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r6.<init>();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r7 = "    URI: ";
        r6 = r6.append(r7);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r6 = r6.append(r1);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r6 = r6.toString();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r0.finest(r6);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r0 = f12639a;	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r6 = new java.lang.StringBuilder;	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r6.<init>();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r7 = "    local: ";
        r6 = r6.append(r7);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r6 = r6.append(r2);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r6 = r6.toString();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r0.finest(r6);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
    L_0x00af:
        r0 = com.xiaomi.kenai.jbosh.C2501b.m14335c();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r5 = r0.m14318a(r5);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        if (r5 != 0) goto L_0x011e;
    L_0x00b9:
        r3 = new java.lang.IllegalStateException;	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r4 = new java.lang.StringBuilder;	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r4.<init>();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r5 = "Root element was not '";
        r4 = r4.append(r5);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r5 = r0.m14319b();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r4 = r4.append(r5);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r5 = "' in the '";
        r4 = r4.append(r5);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r0 = r0.m14317a();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r0 = r4.append(r0);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r4 = "' namespace.  (Was '";
        r0 = r0.append(r4);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r0 = r0.append(r2);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r2 = "' in '";
        r0 = r0.append(r2);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r0 = r0.append(r1);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r1 = "')";
        r0 = r0.append(r1);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r0 = r0.toString();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r3.<init>(r0);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        throw r3;	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
    L_0x00fe:
        r0 = move-exception;
    L_0x00ff:
        r1 = new com.xiaomi.kenai.jbosh.aa;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "Could not parse body:\n";
        r2 = r2.append(r3);
        r2 = r2.append(r11);
        r2 = r2.toString();
        r1.<init>(r2, r0);
        throw r1;
    L_0x0118:
        r0 = r4.next();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        goto L_0x0015;
    L_0x011e:
        r0 = 0;
        r2 = r0;
    L_0x0120:
        r0 = r4.getAttributeCount();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        if (r2 >= r0) goto L_0x018d;
    L_0x0126:
        r0 = r4.getAttributeNamespace(r2);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r1 = r0.length();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        if (r1 != 0) goto L_0x0194;
    L_0x0130:
        r0 = 0;
        r0 = r4.getNamespace(r0);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r1 = r0;
    L_0x0136:
        r0 = r4.getAttributePrefix(r2);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        if (r0 != 0) goto L_0x013e;
    L_0x013c:
        r0 = "";
    L_0x013e:
        r5 = r4.getAttributeName(r2);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r6 = r4.getAttributeValue(r2);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r0 = com.xiaomi.kenai.jbosh.ag.m14316a(r1, r5, r0);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r7 = f12639a;	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r8 = java.util.logging.Level.FINEST;	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r7 = r7.isLoggable(r8);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        if (r7 == 0) goto L_0x0186;
    L_0x0154:
        r7 = f12639a;	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r8 = new java.lang.StringBuilder;	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r8.<init>();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r9 = "        Attribute: {";
        r8 = r8.append(r9);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r1 = r8.append(r1);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r8 = "}";
        r1 = r1.append(r8);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r1 = r1.append(r5);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r5 = " = '";
        r1 = r1.append(r5);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r1 = r1.append(r6);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r5 = "'";
        r1 = r1.append(r5);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r1 = r1.toString();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r7.finest(r1);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
    L_0x0186:
        r3.m14310a(r0, r6);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x018e }
        r0 = r2 + 1;
        r2 = r0;
        goto L_0x0120;
    L_0x018d:
        return r3;
    L_0x018e:
        r0 = move-exception;
        goto L_0x00ff;
    L_0x0191:
        r0 = move-exception;
        goto L_0x00ff;
    L_0x0194:
        r1 = r0;
        goto L_0x0136;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.kenai.jbosh.ae.a(java.lang.String):com.xiaomi.kenai.jbosh.ad");
    }
}
