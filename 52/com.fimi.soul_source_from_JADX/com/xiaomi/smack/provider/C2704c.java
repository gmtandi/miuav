package com.xiaomi.smack.provider;

import com.fimi.kernel.p076b.p080d.C1142e;
import com.xiaomi.smack.packet.C2691e;
import com.xiaomi.smack.packet.C2695b;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

/* renamed from: com.xiaomi.smack.provider.c */
public class C2704c {
    private static C2704c f13386a;
    private Map<String, Object> f13387b;
    private Map<String, Object> f13388c;

    private C2704c() {
        this.f13387b = new ConcurrentHashMap();
        this.f13388c = new ConcurrentHashMap();
        m15291b();
    }

    public static synchronized C2704c m15286a() {
        C2704c c2704c;
        synchronized (C2704c.class) {
            if (f13386a == null) {
                f13386a = new C2704c();
            }
            c2704c = f13386a;
        }
        return c2704c;
    }

    private String m15287b(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<").append(str).append("/>");
        if (str != null) {
            stringBuilder.append("<").append(str2).append("/>");
        }
        return stringBuilder.toString();
    }

    private ClassLoader[] m15288c() {
        int i = 0;
        ClassLoader[] classLoaderArr = new ClassLoader[]{C2704c.class.getClassLoader(), Thread.currentThread().getContextClassLoader()};
        List arrayList = new ArrayList();
        int length = classLoaderArr.length;
        while (i < length) {
            Object obj = classLoaderArr[i];
            if (obj != null) {
                arrayList.add(obj);
            }
            i++;
        }
        return (ClassLoader[]) arrayList.toArray(new ClassLoader[arrayList.size()]);
    }

    public Object m15289a(String str, String str2) {
        return this.f13387b.get(m15287b(str, str2));
    }

    public void m15290a(String str, String str2, Object obj) {
        if ((obj instanceof C2646b) || (obj instanceof Class)) {
            this.f13387b.put(m15287b(str, str2), obj);
            return;
        }
        throw new IllegalArgumentException("Provider must be a PacketExtensionProvider or a Class instance.");
    }

    protected void m15291b() {
        try {
            for (ClassLoader resources : m15288c()) {
                Enumeration resources2 = resources.getResources("META-INF/smack.providers");
                while (resources2.hasMoreElements()) {
                    InputStream inputStream = null;
                    inputStream = ((URL) resources2.nextElement()).openStream();
                    XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
                    newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
                    newPullParser.setInput(inputStream, C1142e.f5201a);
                    int eventType = newPullParser.getEventType();
                    do {
                        if (eventType == 2) {
                            String nextText;
                            String nextText2;
                            String nextText3;
                            Class cls;
                            if (newPullParser.getName().equals("iqProvider")) {
                                newPullParser.next();
                                newPullParser.next();
                                nextText = newPullParser.nextText();
                                newPullParser.next();
                                newPullParser.next();
                                nextText2 = newPullParser.nextText();
                                newPullParser.next();
                                newPullParser.next();
                                nextText3 = newPullParser.nextText();
                                nextText = m15287b(nextText, nextText2);
                                if (!this.f13388c.containsKey(nextText)) {
                                    try {
                                        cls = Class.forName(nextText3);
                                        if (C2703a.class.isAssignableFrom(cls)) {
                                            this.f13388c.put(nextText, cls.newInstance());
                                        } else if (C2695b.class.isAssignableFrom(cls)) {
                                            this.f13388c.put(nextText, cls);
                                        }
                                    } catch (ClassNotFoundException e) {
                                        e.printStackTrace();
                                    } catch (Throwable th) {
                                        try {
                                            inputStream.close();
                                        } catch (Exception e2) {
                                        }
                                    }
                                }
                            } else if (newPullParser.getName().equals("extensionProvider")) {
                                newPullParser.next();
                                newPullParser.next();
                                nextText = newPullParser.nextText();
                                newPullParser.next();
                                newPullParser.next();
                                nextText2 = newPullParser.nextText();
                                newPullParser.next();
                                newPullParser.next();
                                nextText3 = newPullParser.nextText();
                                nextText = m15287b(nextText, nextText2);
                                if (!this.f13387b.containsKey(nextText)) {
                                    try {
                                        cls = Class.forName(nextText3);
                                        if (C2646b.class.isAssignableFrom(cls)) {
                                            this.f13387b.put(nextText, cls.newInstance());
                                        } else if (C2691e.class.isAssignableFrom(cls)) {
                                            this.f13387b.put(nextText, cls);
                                        }
                                    } catch (ClassNotFoundException e3) {
                                        e3.printStackTrace();
                                    }
                                }
                            }
                        }
                        eventType = newPullParser.next();
                    } while (eventType != 1);
                    try {
                        inputStream.close();
                    } catch (Exception e4) {
                    }
                }
            }
        } catch (Exception e5) {
            e5.printStackTrace();
        }
    }
}
