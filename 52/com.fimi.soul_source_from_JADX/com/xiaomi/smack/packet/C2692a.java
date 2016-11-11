package com.xiaomi.smack.packet;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import com.xiaomi.smack.util.C2718g;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.codehaus.jackson.util.MinimalPrettyPrinter;

/* renamed from: com.xiaomi.smack.packet.a */
public class C2692a implements C2691e {
    private String f13296a;
    private String f13297b;
    private String[] f13298c;
    private String[] f13299d;
    private String f13300e;
    private List<C2692a> f13301f;

    public C2692a(String str, String str2, String[] strArr, String[] strArr2) {
        this.f13298c = null;
        this.f13299d = null;
        this.f13301f = null;
        this.f13296a = str;
        this.f13297b = str2;
        this.f13298c = strArr;
        this.f13299d = strArr2;
    }

    public C2692a(String str, String str2, String[] strArr, String[] strArr2, String str3, List<C2692a> list) {
        this.f13298c = null;
        this.f13299d = null;
        this.f13301f = null;
        this.f13296a = str;
        this.f13297b = str2;
        this.f13298c = strArr;
        this.f13299d = strArr2;
        this.f13300e = str3;
        this.f13301f = list;
    }

    public static C2692a m15214a(Bundle bundle) {
        List arrayList;
        int i = 0;
        String string = bundle.getString("ext_ele_name");
        String string2 = bundle.getString("ext_ns");
        String string3 = bundle.getString("ext_text");
        Bundle bundle2 = bundle.getBundle("attributes");
        Set<String> keySet = bundle2.keySet();
        String[] strArr = new String[keySet.size()];
        String[] strArr2 = new String[keySet.size()];
        int i2 = 0;
        for (String str : keySet) {
            strArr[i2] = str;
            strArr2[i2] = bundle2.getString(str);
            i2++;
        }
        if (bundle.containsKey("children")) {
            Parcelable[] parcelableArray = bundle.getParcelableArray("children");
            arrayList = new ArrayList(parcelableArray.length);
            int length = parcelableArray.length;
            while (i < length) {
                arrayList.add(C2692a.m15214a((Bundle) parcelableArray[i]));
                i++;
            }
        } else {
            arrayList = null;
        }
        return new C2692a(string, string2, strArr, strArr2, string3, arrayList);
    }

    public static Parcelable[] m15215a(List<C2692a> list) {
        return C2692a.m15216a((C2692a[]) list.toArray(new C2692a[list.size()]));
    }

    public static Parcelable[] m15216a(C2692a[] c2692aArr) {
        if (c2692aArr == null) {
            return null;
        }
        Parcelable[] parcelableArr = new Parcelable[c2692aArr.length];
        for (int i = 0; i < c2692aArr.length; i++) {
            parcelableArr[i] = c2692aArr[i].m15224f();
        }
        return parcelableArr;
    }

    public String m15217a() {
        return this.f13296a;
    }

    public String m15218a(String str) {
        if (str == null) {
            throw new IllegalArgumentException();
        }
        if (this.f13298c != null) {
            for (int i = 0; i < this.f13298c.length; i++) {
                if (str.equals(this.f13298c[i])) {
                    return this.f13299d[i];
                }
            }
        }
        return null;
    }

    public String m15219b() {
        return this.f13297b;
    }

    public void m15220b(String str) {
        if (TextUtils.isEmpty(str)) {
            this.f13300e = str;
        } else {
            this.f13300e = C2718g.m15358a(str);
        }
    }

    public String m15221c() {
        return !TextUtils.isEmpty(this.f13300e) ? C2718g.m15362b(this.f13300e) : this.f13300e;
    }

    public String m15222d() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<").append(this.f13296a);
        if (!TextUtils.isEmpty(this.f13297b)) {
            stringBuilder.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append("xmlns=").append("\"").append(this.f13297b).append("\"");
        }
        if (this.f13298c != null && this.f13298c.length > 0) {
            for (int i = 0; i < this.f13298c.length; i++) {
                if (!TextUtils.isEmpty(this.f13299d[i])) {
                    stringBuilder.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(this.f13298c[i]).append("=\"").append(C2718g.m15358a(this.f13299d[i])).append("\"");
                }
            }
        }
        if (!TextUtils.isEmpty(this.f13300e)) {
            stringBuilder.append(">").append(this.f13300e).append("</").append(this.f13296a).append(">");
        } else if (this.f13301f == null || this.f13301f.size() <= 0) {
            stringBuilder.append("/>");
        } else {
            stringBuilder.append(">");
            for (C2692a d : this.f13301f) {
                stringBuilder.append(d.m15222d());
            }
            stringBuilder.append("</").append(this.f13296a).append(">");
        }
        return stringBuilder.toString();
    }

    public Bundle m15223e() {
        Bundle bundle = new Bundle();
        bundle.putString("ext_ele_name", this.f13296a);
        bundle.putString("ext_ns", this.f13297b);
        bundle.putString("ext_text", this.f13300e);
        Bundle bundle2 = new Bundle();
        if (this.f13298c != null && this.f13298c.length > 0) {
            for (int i = 0; i < this.f13298c.length; i++) {
                bundle2.putString(this.f13298c[i], this.f13299d[i]);
            }
        }
        bundle.putBundle("attributes", bundle2);
        if (this.f13301f != null && this.f13301f.size() > 0) {
            bundle.putParcelableArray("children", C2692a.m15215a(this.f13301f));
        }
        return bundle;
    }

    public Parcelable m15224f() {
        return m15223e();
    }

    public String toString() {
        return m15222d();
    }
}
