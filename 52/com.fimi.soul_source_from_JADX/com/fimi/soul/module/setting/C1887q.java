package com.fimi.soul.module.setting;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;

/* renamed from: com.fimi.soul.module.setting.q */
class C1887q implements OnItemLongClickListener {
    final /* synthetic */ FlyLogsActivity f9614a;

    C1887q(FlyLogsActivity flyLogsActivity) {
        this.f9614a = flyLogsActivity;
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.f9614a.f9059h.m11904a(true);
        this.f9614a.f9062k.setVisibility(0);
        this.f9614a.f9063l.setVisibility(4);
        this.f9614a.f9064m.setVisibility(0);
        return false;
    }
}
