package cn.sharesdk.framework.utils;

import android.text.TextUtils;
import android.util.Xml;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.SharedPref;
import java.util.HashMap;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/* renamed from: cn.sharesdk.framework.utils.i */
public class C0210i {

    /* renamed from: cn.sharesdk.framework.utils.i.a */
    class C0209a extends DefaultHandler {
        private HashMap<String, Object> f384a;
        private HashMap<String, Object> f385b;

        public C0209a() {
            this.f384a = new HashMap();
        }

        public HashMap<String, Object> m757a() {
            return this.f384a;
        }

        public void characters(char[] cArr, int i, int i2) {
            CharSequence trim = String.valueOf(cArr, i, i2).trim();
            if (!TextUtils.isEmpty(trim) && this.f385b != null) {
                this.f385b.put(SharedPref.VALUE, trim);
            }
        }

        public void endElement(String str, String str2, String str3) {
            this.f385b = null;
        }

        public void startElement(String str, String str2, String str3, Attributes attributes) {
            if (this.f385b != null) {
                HashMap hashMap = new HashMap();
                this.f385b.put(str2, hashMap);
                this.f385b = hashMap;
            } else {
                this.f385b = new HashMap();
                this.f384a.put(str2, this.f385b);
            }
            int length = attributes.getLength();
            for (int i = 0; i < length; i++) {
                this.f385b.put(attributes.getLocalName(i), attributes.getValue(i));
            }
        }
    }

    public HashMap<String, Object> m758a(String str) {
        Object c0209a = new C0209a();
        Xml.parse(str, c0209a);
        return c0209a.m757a();
    }
}
