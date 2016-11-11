package com.fimi.soul.module.droneFragment;

import android.graphics.Paint;
import android.text.Editable;
import android.text.TextWatcher;
import org.p122a.p123a.C2915a;

class ap implements TextWatcher {
    final /* synthetic */ ShowDroneStatusLineFragment f8184a;

    ap(ShowDroneStatusLineFragment showDroneStatusLineFragment) {
        this.f8184a = showDroneStatusLineFragment;
    }

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (!C2915a.f14760f.equals(charSequence.toString())) {
            this.f8184a.f8071I = charSequence.toString();
        }
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (!C2915a.f14760f.equals(charSequence.toString()) && !charSequence.toString().equals(this.f8184a.f8071I)) {
            int measuredWidth = this.f8184a.f8087n.getMeasuredWidth();
            new Paint().setTextSize(this.f8184a.f8087n.getTextSize());
            if (this.f8184a.m10930b(charSequence.toString()) <= ((float) measuredWidth)) {
                this.f8184a.f8087n.m12544b();
            } else if (!this.f8184a.f8087n.f10225b) {
                this.f8184a.f8087n.m12542a();
            }
        }
    }
}
