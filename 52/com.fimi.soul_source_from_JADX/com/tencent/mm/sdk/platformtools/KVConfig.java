package com.tencent.mm.sdk.platformtools;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.p122a.p123a.C2915a;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class KVConfig {
    private static boolean aa;

    static {
        aa = false;
    }

    private static void m13532a(Map<String, String> map) {
        if (map == null || map.size() <= 0) {
            Log.m13547v("MicroMsg.SDK.KVConfig", "empty values");
            return;
        }
        for (Entry entry : map.entrySet()) {
            Log.m13547v("MicroMsg.SDK.KVConfig", "key=" + ((String) entry.getKey()) + " value=" + ((String) entry.getValue()));
        }
    }

    private static void m13533a(Map<String, String> map, String str, Node node, int i) {
        int i2 = 0;
        if (node.getNodeName().equals("#text")) {
            map.put(str, node.getNodeValue());
        } else if (node.getNodeName().equals("#cdata-section")) {
            map.put(str, node.getNodeValue());
        } else {
            int i3;
            String str2 = str + "." + node.getNodeName() + (i > 0 ? Integer.valueOf(i) : C2915a.f14760f);
            map.put(str2, node.getNodeValue());
            NamedNodeMap attributes = node.getAttributes();
            if (attributes != null) {
                for (i3 = 0; i3 < attributes.getLength(); i3++) {
                    Node item = attributes.item(i3);
                    map.put(str2 + ".$" + item.getNodeName(), item.getNodeValue());
                }
            }
            HashMap hashMap = new HashMap();
            NodeList childNodes = node.getChildNodes();
            while (i2 < childNodes.getLength()) {
                Node item2 = childNodes.item(i2);
                i3 = Util.nullAsNil((Integer) hashMap.get(item2.getNodeName()));
                m13533a(map, str2, item2, i3);
                hashMap.put(item2.getNodeName(), Integer.valueOf(i3 + 1));
                i2++;
            }
        }
    }

    public static Map<String, String> parseIni(String str) {
        if (str == null || str.length() <= 0) {
            return null;
        }
        Map<String, String> hashMap = new HashMap();
        for (String str2 : str.split("\n")) {
            if (str2 != null && str2.length() > 0) {
                String[] split = str2.trim().split("=", 2);
                if (split != null && split.length >= 2) {
                    String str3 = split[0];
                    Object obj = split[1];
                    if (str3 != null && str3.length() > 0 && str3.matches("^[a-zA-Z0-9_]*")) {
                        hashMap.put(str3, obj);
                    }
                }
            }
        }
        if (!aa) {
            return hashMap;
        }
        m13532a(hashMap);
        return hashMap;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Map<java.lang.String, java.lang.String> parseXml(java.lang.String r8, java.lang.String r9, java.lang.String r10) {
        /*
        r7 = 1;
        r6 = 0;
        r0 = 0;
        r1 = 60;
        r1 = r8.indexOf(r1);
        if (r1 >= 0) goto L_0x0013;
    L_0x000b:
        r1 = "MicroMsg.SDK.KVConfig";
        r2 = "text not in xml format";
        com.tencent.mm.sdk.platformtools.Log.m13541e(r1, r2);
    L_0x0012:
        return r0;
    L_0x0013:
        if (r1 <= 0) goto L_0x0028;
    L_0x0015:
        r2 = "MicroMsg.SDK.KVConfig";
        r3 = "fix xml header from + %d";
        r4 = new java.lang.Object[r7];
        r5 = java.lang.Integer.valueOf(r1);
        r4[r6] = r5;
        com.tencent.mm.sdk.platformtools.Log.m13550w(r2, r3, r4);
        r8 = r8.substring(r1);
    L_0x0028:
        if (r8 == 0) goto L_0x0012;
    L_0x002a:
        r1 = r8.length();
        if (r1 <= 0) goto L_0x0012;
    L_0x0030:
        r1 = new java.util.HashMap;
        r1.<init>();
        r2 = javax.xml.parsers.DocumentBuilderFactory.newInstance();
        r2 = r2.newDocumentBuilder();	 Catch:{ ParserConfigurationException -> 0x0047 }
        if (r2 != 0) goto L_0x004c;
    L_0x003f:
        r1 = "MicroMsg.SDK.KVConfig";
        r2 = "new Document Builder failed";
        com.tencent.mm.sdk.platformtools.Log.m13541e(r1, r2);
        goto L_0x0012;
    L_0x0047:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0012;
    L_0x004c:
        r3 = new org.xml.sax.InputSource;	 Catch:{ DOMException -> 0x0070, SAXException -> 0x0076, IOException -> 0x007b, Exception -> 0x0080 }
        r4 = new java.io.ByteArrayInputStream;	 Catch:{ DOMException -> 0x0070, SAXException -> 0x0076, IOException -> 0x007b, Exception -> 0x0080 }
        r5 = r8.getBytes();	 Catch:{ DOMException -> 0x0070, SAXException -> 0x0076, IOException -> 0x007b, Exception -> 0x0080 }
        r4.<init>(r5);	 Catch:{ DOMException -> 0x0070, SAXException -> 0x0076, IOException -> 0x007b, Exception -> 0x0080 }
        r3.<init>(r4);	 Catch:{ DOMException -> 0x0070, SAXException -> 0x0076, IOException -> 0x007b, Exception -> 0x0080 }
        if (r10 == 0) goto L_0x005f;
    L_0x005c:
        r3.setEncoding(r10);	 Catch:{ DOMException -> 0x0070, SAXException -> 0x0076, IOException -> 0x007b, Exception -> 0x0080 }
    L_0x005f:
        r3 = r2.parse(r3);	 Catch:{ DOMException -> 0x0070, SAXException -> 0x0076, IOException -> 0x007b, Exception -> 0x0080 }
        r3.normalize();	 Catch:{ DOMException -> 0x00d8, SAXException -> 0x0076, IOException -> 0x007b, Exception -> 0x0080 }
    L_0x0066:
        if (r3 != 0) goto L_0x0085;
    L_0x0068:
        r1 = "MicroMsg.SDK.KVConfig";
        r2 = "new Document failed";
        com.tencent.mm.sdk.platformtools.Log.m13541e(r1, r2);
        goto L_0x0012;
    L_0x0070:
        r2 = move-exception;
        r3 = r0;
    L_0x0072:
        r2.printStackTrace();
        goto L_0x0066;
    L_0x0076:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0012;
    L_0x007b:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0012;
    L_0x0080:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0012;
    L_0x0085:
        r2 = r3.getDocumentElement();
        if (r2 != 0) goto L_0x0093;
    L_0x008b:
        r1 = "MicroMsg.SDK.KVConfig";
        r2 = "getDocumentElement failed";
        com.tencent.mm.sdk.platformtools.Log.m13541e(r1, r2);
        goto L_0x0012;
    L_0x0093:
        if (r9 == 0) goto L_0x00ae;
    L_0x0095:
        r3 = r2.getNodeName();
        r3 = r9.equals(r3);
        if (r3 == 0) goto L_0x00ae;
    L_0x009f:
        r0 = "";
        m13533a(r1, r0, r2, r6);
    L_0x00a4:
        r0 = aa;
        if (r0 == 0) goto L_0x00ab;
    L_0x00a8:
        m13532a(r1);
    L_0x00ab:
        r0 = r1;
        goto L_0x0012;
    L_0x00ae:
        r2 = r2.getElementsByTagName(r9);
        r3 = r2.getLength();
        if (r3 > 0) goto L_0x00c1;
    L_0x00b8:
        r1 = "MicroMsg.SDK.KVConfig";
        r2 = "parse item null";
        com.tencent.mm.sdk.platformtools.Log.m13541e(r1, r2);
        goto L_0x0012;
    L_0x00c1:
        r0 = r2.getLength();
        if (r0 <= r7) goto L_0x00ce;
    L_0x00c7:
        r0 = "MicroMsg.SDK.KVConfig";
        r3 = "parse items more than one";
        com.tencent.mm.sdk.platformtools.Log.m13549w(r0, r3);
    L_0x00ce:
        r0 = "";
        r2 = r2.item(r6);
        m13533a(r1, r0, r2, r6);
        goto L_0x00a4;
    L_0x00d8:
        r2 = move-exception;
        goto L_0x0072;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.mm.sdk.platformtools.KVConfig.parseXml(java.lang.String, java.lang.String, java.lang.String):java.util.Map<java.lang.String, java.lang.String>");
    }
}
