package org.p122a.p123a.p167i.p174d;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.cookie.SetCookie;
import org.p122a.p123a.C3004e;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p180o.C3234a;

@C2913c
/* renamed from: org.a.a.i.d.d */
public class C3139d implements Serializable, Cloneable, ClientCookie, SetCookie {
    private static final long serialVersionUID = -3869795591041535538L;
    private final String f15472a;
    private Map<String, String> f15473b;
    private String f15474c;
    private String f15475d;
    private String f15476e;
    private Date f15477f;
    private String f15478g;
    private boolean f15479h;
    private int f15480i;

    public C3139d(String str, String str2) {
        C3234a.m17886a((Object) str, "Name");
        this.f15472a = str;
        this.f15473b = new HashMap();
        this.f15474c = str2;
    }

    public void m17678a(String str, String str2) {
        this.f15473b.put(str, str2);
    }

    public Object clone() {
        C3139d c3139d = (C3139d) super.clone();
        c3139d.f15473b = new HashMap(this.f15473b);
        return c3139d;
    }

    public boolean containsAttribute(String str) {
        return this.f15473b.get(str) != null;
    }

    public String getAttribute(String str) {
        return (String) this.f15473b.get(str);
    }

    public String getComment() {
        return this.f15475d;
    }

    public String getCommentURL() {
        return null;
    }

    public String getDomain() {
        return this.f15476e;
    }

    public Date getExpiryDate() {
        return this.f15477f;
    }

    public String getName() {
        return this.f15472a;
    }

    public String getPath() {
        return this.f15478g;
    }

    public int[] getPorts() {
        return null;
    }

    public String getValue() {
        return this.f15474c;
    }

    public int getVersion() {
        return this.f15480i;
    }

    public boolean isExpired(Date date) {
        C3234a.m17886a((Object) date, C3004e.f15032r);
        return this.f15477f != null && this.f15477f.getTime() <= date.getTime();
    }

    public boolean isPersistent() {
        return this.f15477f != null;
    }

    public boolean isSecure() {
        return this.f15479h;
    }

    public void setComment(String str) {
        this.f15475d = str;
    }

    public void setDomain(String str) {
        if (str != null) {
            this.f15476e = str.toLowerCase(Locale.ENGLISH);
        } else {
            this.f15476e = null;
        }
    }

    public void setExpiryDate(Date date) {
        this.f15477f = date;
    }

    public void setPath(String str) {
        this.f15478g = str;
    }

    public void setSecure(boolean z) {
        this.f15479h = z;
    }

    public void setValue(String str) {
        this.f15474c = str;
    }

    public void setVersion(int i) {
        this.f15480i = i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[version: ");
        stringBuilder.append(Integer.toString(this.f15480i));
        stringBuilder.append("]");
        stringBuilder.append("[name: ");
        stringBuilder.append(this.f15472a);
        stringBuilder.append("]");
        stringBuilder.append("[value: ");
        stringBuilder.append(this.f15474c);
        stringBuilder.append("]");
        stringBuilder.append("[domain: ");
        stringBuilder.append(this.f15476e);
        stringBuilder.append("]");
        stringBuilder.append("[path: ");
        stringBuilder.append(this.f15478g);
        stringBuilder.append("]");
        stringBuilder.append("[expiry: ");
        stringBuilder.append(this.f15477f);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
