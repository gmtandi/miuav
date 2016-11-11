package org.p122a.p123a.p167i.p174d;

import java.util.Date;
import org.apache.http.cookie.SetCookie2;
import org.p122a.p123a.p150a.C2913c;

@C2913c
/* renamed from: org.a.a.i.d.c */
public class C3140c extends C3139d implements SetCookie2 {
    private static final long serialVersionUID = -7744598295706617057L;
    private String f15481a;
    private int[] f15482b;
    private boolean f15483c;

    public C3140c(String str, String str2) {
        super(str, str2);
    }

    public Object clone() {
        C3140c c3140c = (C3140c) super.clone();
        if (this.f15482b != null) {
            c3140c.f15482b = (int[]) this.f15482b.clone();
        }
        return c3140c;
    }

    public String getCommentURL() {
        return this.f15481a;
    }

    public int[] getPorts() {
        return this.f15482b;
    }

    public boolean isExpired(Date date) {
        return this.f15483c || super.isExpired(date);
    }

    public boolean isPersistent() {
        return !this.f15483c && super.isPersistent();
    }

    public void setCommentURL(String str) {
        this.f15481a = str;
    }

    public void setDiscard(boolean z) {
        this.f15483c = z;
    }

    public void setPorts(int[] iArr) {
        this.f15482b = iArr;
    }
}
