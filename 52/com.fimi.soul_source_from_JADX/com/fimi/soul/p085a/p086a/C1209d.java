package com.fimi.soul.p085a.p086a;

import android.app.Activity;
import android.preference.PreferenceManager;
import android.view.WindowManager;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.fimi.soul.a.a.d */
public class C1209d {
    public int f5450a;
    private Activity f5451b;

    public C1209d(Activity activity) {
        this.f5451b = activity;
    }

    private void m8433c() {
        this.f5451b.setRequestedOrientation(this.f5450a);
    }

    private void m8434d() {
        int rotation = ((WindowManager) this.f5451b.getSystemService("window")).getDefaultDisplay().getRotation();
        int i = this.f5451b.getResources().getConfiguration().orientation;
        i = (((rotation == 0 || rotation == 2) && i == 2) || ((rotation == 1 || rotation == 3) && i == 1)) ? 1 : 0;
        if (i == 0) {
            switch (rotation) {
                case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                    this.f5450a = 1;
                    break;
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    this.f5450a = 0;
                    break;
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    this.f5450a = 9;
                    break;
                case Type.BYTE /*3*/:
                    this.f5450a = 8;
                    break;
                default:
                    break;
            }
        }
        switch (rotation) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                this.f5450a = 0;
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                this.f5450a = 9;
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                this.f5450a = 8;
                break;
            case Type.BYTE /*3*/:
                this.f5450a = 1;
                break;
        }
        m8433c();
    }

    private boolean m8435e() {
        return PreferenceManager.getDefaultSharedPreferences(this.f5451b.getApplicationContext()).getBoolean("pref_lock_screen_orientation", false);
    }

    public void m8436a() {
        if (m8435e()) {
            m8434d();
        }
    }

    public void m8437b() {
        if (this.f5450a != -1) {
            this.f5450a = -1;
            m8433c();
        }
    }
}
