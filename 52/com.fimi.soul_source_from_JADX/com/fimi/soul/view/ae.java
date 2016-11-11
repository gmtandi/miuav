package com.fimi.soul.view;

import android.content.SharedPreferences;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import com.fimi.soul.drone.p116g.C1543c;

class ae implements OnClickListener {
    final /* synthetic */ RadioButton f10661a;
    final /* synthetic */ SharedPreferences f10662b;
    final /* synthetic */ ad f10663c;

    ae(ad adVar, RadioButton radioButton, SharedPreferences sharedPreferences) {
        this.f10663c = adVar;
        this.f10661a = radioButton;
        this.f10662b = sharedPreferences;
    }

    public void onClick(View view) {
        if (this.f10663c.f10660f) {
            this.f10661a.setChecked(false);
            this.f10663c.f10660f = false;
            if (this.f10663c.f10659e.m8599a().get() == 1) {
                this.f10662b.edit().putBoolean(C1543c.bC, false).commit();
                return;
            } else if (this.f10663c.f10659e.m8599a().get() == 2) {
                this.f10662b.edit().putBoolean(C1543c.bD, false).commit();
                return;
            } else if (this.f10663c.f10659e.m8599a().get() == 3) {
                this.f10662b.edit().putBoolean(C1543c.bE, false).commit();
                return;
            } else if (this.f10663c.f10659e.m8599a().get() == 4) {
                this.f10662b.edit().putBoolean(C1543c.bF, false).commit();
                return;
            } else {
                return;
            }
        }
        this.f10661a.setChecked(true);
        this.f10663c.f10660f = true;
        if (this.f10663c.f10659e.m8599a().get() == 1) {
            this.f10662b.edit().putBoolean(C1543c.bC, true).commit();
        } else if (this.f10663c.f10659e.m8599a().get() == 2) {
            this.f10662b.edit().putBoolean(C1543c.bD, true).commit();
        } else if (this.f10663c.f10659e.m8599a().get() == 3) {
            this.f10662b.edit().putBoolean(C1543c.bE, true).commit();
        } else if (this.f10663c.f10659e.m8599a().get() == 4) {
            this.f10662b.edit().putBoolean(C1543c.bF, true).commit();
        }
    }
}
