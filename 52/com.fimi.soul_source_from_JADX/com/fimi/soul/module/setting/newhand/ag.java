package com.fimi.soul.module.setting.newhand;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

class ag implements OnItemClickListener {
    final /* synthetic */ af f9493a;

    ag(af afVar) {
        this.f9493a = afVar;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.f9493a.f9491g.m11855a(i);
        if (this.f9493a.f9488d != null) {
            this.f9493a.f9488d.dismiss();
            this.f9493a.f9488d = null;
        }
    }
}
