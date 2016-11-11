package com.android.volley.toolbox;

import com.android.volley.ag;

class ad implements C0575y {
    final /* synthetic */ boolean f3588a;
    final /* synthetic */ NetworkImageView f3589b;

    ad(NetworkImageView networkImageView, boolean z) {
        this.f3589b = networkImageView;
        this.f3588a = z;
    }

    public void m5156a(ag agVar) {
        if (this.f3589b.f3577c != 0) {
            this.f3589b.setImageResource(this.f3589b.f3577c);
        }
    }

    public void m5157a(C0597x c0597x, boolean z) {
        if (z && this.f3588a) {
            this.f3589b.post(new ae(this, c0597x));
        } else if (c0597x.m5270b() != null) {
            this.f3589b.setImageBitmap(c0597x.m5270b());
        } else if (this.f3589b.f3576b != 0) {
            this.f3589b.setImageResource(this.f3589b.f3576b);
        }
    }
}
