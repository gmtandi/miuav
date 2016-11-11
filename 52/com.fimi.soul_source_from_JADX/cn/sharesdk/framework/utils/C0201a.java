package cn.sharesdk.framework.utils;

import android.util.Base64;
import com.mob.tools.network.KVPair;
import com.tencent.open.SocialConstants;
import com.xiaomi.openauth.BuildConfig;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.codehaus.jackson.org.objectweb.asm.signature.SignatureVisitor;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;
import org.p122a.p123a.C3004e;
import org.p122a.p123a.p152c.p156c.C2951i;
import org.p122a.p123a.p152c.p156c.C2955m;
import org.p122a.p123a.p152c.p156c.C2956n;
import org.p122a.p123a.p152c.p161f.C2989l;

/* renamed from: cn.sharesdk.framework.utils.a */
public class C0201a {
    private C0200b f369a;
    private C0203b f370b;

    /* renamed from: cn.sharesdk.framework.utils.a.1 */
    /* synthetic */ class C01981 {
        static final /* synthetic */ int[] f360a;

        static {
            f360a = new int[C0199a.values().length];
            try {
                f360a[C0199a.HMAC_SHA1.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f360a[C0199a.PLAINTEXT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    /* renamed from: cn.sharesdk.framework.utils.a.a */
    public enum C0199a {
        HMAC_SHA1,
        PLAINTEXT
    }

    /* renamed from: cn.sharesdk.framework.utils.a.b */
    public class C0200b {
        public String f364a;
        public String f365b;
        public String f366c;
        public String f367d;
        public String f368e;
    }

    public C0201a() {
        this.f369a = new C0200b();
        this.f370b = new C0203b("-._~", false);
    }

    private ArrayList<KVPair<String>> m714a(long j, String str) {
        ArrayList<KVPair<String>> arrayList = new ArrayList();
        arrayList.add(new KVPair(SocialConstants.PARAM_CONSUMER_KEY, this.f369a.f364a));
        arrayList.add(new KVPair("oauth_signature_method", str));
        arrayList.add(new KVPair("oauth_timestamp", String.valueOf(j / 1000)));
        arrayList.add(new KVPair("oauth_nonce", String.valueOf(j)));
        arrayList.add(new KVPair("oauth_version", BuildConfig.VERSION_NAME));
        String str2 = this.f369a.f366c;
        if (str2 != null && str2.length() > 0) {
            arrayList.add(new KVPair("oauth_token", str2));
        }
        return arrayList;
    }

    private ArrayList<KVPair<String>> m715a(long j, ArrayList<KVPair<String>> arrayList, String str) {
        Iterator it;
        int i = 0;
        HashMap hashMap = new HashMap();
        if (arrayList != null) {
            it = arrayList.iterator();
            while (it.hasNext()) {
                KVPair kVPair = (KVPair) it.next();
                hashMap.put(m719a(kVPair.name), m719a((String) kVPair.value));
            }
        }
        ArrayList a = m714a(j, str);
        if (a != null) {
            it = a.iterator();
            while (it.hasNext()) {
                kVPair = (KVPair) it.next();
                hashMap.put(m719a(kVPair.name), m719a((String) kVPair.value));
            }
        }
        String[] strArr = new String[hashMap.size()];
        int i2 = 0;
        for (Entry key : hashMap.entrySet()) {
            strArr[i2] = (String) key.getKey();
            i2++;
        }
        Arrays.sort(strArr);
        ArrayList<KVPair<String>> arrayList2 = new ArrayList();
        i2 = strArr.length;
        while (i < i2) {
            String str2 = strArr[i];
            arrayList2.add(new KVPair(str2, hashMap.get(str2)));
            i++;
        }
        return arrayList2;
    }

    private ArrayList<KVPair<String>> m716a(String str, String str2, ArrayList<KVPair<String>> arrayList, C0199a c0199a) {
        Object trim;
        String str3 = null;
        long currentTimeMillis = System.currentTimeMillis();
        switch (C01981.f360a[c0199a.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                str3 = "HMAC-SHA1";
                Key secretKeySpec = new SecretKeySpec((m719a(this.f369a.f365b) + '&' + m719a(this.f369a.f367d)).getBytes("utf-8"), "HMAC-SHA1");
                Mac instance = Mac.getInstance("HMAC-SHA1");
                instance.init(secretKeySpec);
                trim = new String(Base64.encode(instance.doFinal((str2 + '&' + m719a(str.toLowerCase()) + '&' + m719a(m717b(m715a(currentTimeMillis, (ArrayList) arrayList, str3)))).getBytes("utf-8")), 0)).trim();
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                str3 = "PLAINTEXT";
                trim = m719a(this.f369a.f365b) + '&' + m719a(this.f369a.f367d);
                break;
            default:
                trim = null;
                break;
        }
        ArrayList<KVPair<String>> a = m714a(currentTimeMillis, str3);
        a.add(new KVPair("oauth_signature", trim));
        return a;
    }

    private String m717b(ArrayList<KVPair<String>> arrayList) {
        if (arrayList == null || arrayList.size() <= 0) {
            return C2915a.f14760f;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            KVPair kVPair = (KVPair) it.next();
            if (i > 0) {
                stringBuilder.append('&');
            }
            stringBuilder.append(kVPair.name).append(SignatureVisitor.INSTANCEOF).append((String) kVPair.value);
            i++;
        }
        return stringBuilder.toString();
    }

    public C0200b m718a() {
        return this.f369a;
    }

    public String m719a(String str) {
        return str == null ? C2915a.f14760f : this.f370b.escape(str);
    }

    public ArrayList<KVPair<String>> m720a(String str, ArrayList<KVPair<String>> arrayList) {
        return m721a(str, (ArrayList) arrayList, C0199a.HMAC_SHA1);
    }

    public ArrayList<KVPair<String>> m721a(String str, ArrayList<KVPair<String>> arrayList, C0199a c0199a) {
        return m716a(str, C2955m.f14864a, arrayList, c0199a);
    }

    public ArrayList<KVPair<String>> m722a(ArrayList<KVPair<String>> arrayList) {
        StringBuilder stringBuilder = new StringBuilder("OAuth ");
        Iterator it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            KVPair kVPair = (KVPair) it.next();
            if (i > 0) {
                stringBuilder.append(',');
            }
            stringBuilder.append(kVPair.name).append("=\"").append(m719a((String) kVPair.value)).append("\"");
            i++;
        }
        ArrayList<KVPair<String>> arrayList2 = new ArrayList();
        arrayList2.add(new KVPair(C3004e.f15022h, stringBuilder.toString()));
        arrayList2.add(new KVPair(C3004e.f15031q, C2989l.f14939a));
        return arrayList2;
    }

    public void m723a(String str, String str2) {
        this.f369a.f366c = str;
        this.f369a.f367d = str2;
    }

    public void m724a(String str, String str2, String str3) {
        this.f369a.f364a = str;
        this.f369a.f365b = str2;
        this.f369a.f368e = str3;
    }

    public ArrayList<KVPair<String>> m725b(String str, ArrayList<KVPair<String>> arrayList) {
        return m726b(str, arrayList, C0199a.HMAC_SHA1);
    }

    public ArrayList<KVPair<String>> m726b(String str, ArrayList<KVPair<String>> arrayList, C0199a c0199a) {
        return m716a(str, C2951i.f14860a, arrayList, c0199a);
    }

    public ArrayList<KVPair<String>> m727c(String str, ArrayList<KVPair<String>> arrayList, C0199a c0199a) {
        return m716a(str, C2956n.f14865a, arrayList, c0199a);
    }
}
