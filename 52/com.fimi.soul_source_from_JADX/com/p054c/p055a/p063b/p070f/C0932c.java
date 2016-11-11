package com.p054c.p055a.p063b.p070f;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import com.p054c.p055a.p063b.C0936g;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.c.a.b.f.c */
public class C0932c implements OnScrollListener {
    private C0936g f4879a;
    private final boolean f4880b;
    private final boolean f4881c;
    private final OnScrollListener f4882d;

    public C0932c(C0936g c0936g, boolean z, boolean z2) {
        this(c0936g, z, z2, null);
    }

    public C0932c(C0936g c0936g, boolean z, boolean z2, OnScrollListener onScrollListener) {
        this.f4879a = c0936g;
        this.f4880b = z;
        this.f4881c = z2;
        this.f4882d = onScrollListener;
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (this.f4882d != null) {
            this.f4882d.onScroll(absListView, i, i2, i3);
        }
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        switch (i) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                this.f4879a.m7441j();
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                if (this.f4880b) {
                    this.f4879a.m7440i();
                    break;
                }
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                if (this.f4881c) {
                    this.f4879a.m7440i();
                    break;
                }
                break;
        }
        if (this.f4882d != null) {
            this.f4882d.onScrollStateChanged(absListView, i);
        }
    }
}
