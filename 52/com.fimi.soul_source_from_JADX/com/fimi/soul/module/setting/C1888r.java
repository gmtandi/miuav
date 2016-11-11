package com.fimi.soul.module.setting;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.fimi.soul.C1205R;

/* renamed from: com.fimi.soul.module.setting.r */
class C1888r implements OnItemClickListener {
    final /* synthetic */ FlyLogsActivity f9615a;

    C1888r(FlyLogsActivity flyLogsActivity) {
        this.f9615a = flyLogsActivity;
    }

    @SuppressLint({"StringFormatMatches"})
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (this.f9615a.f9059h.m11905a()) {
            aa aaVar = (aa) this.f9615a.f9059h.getItem(i);
            if (aaVar.f9206g) {
                synchronized (this.f9615a.f9061j) {
                    for (aa aaVar2 : this.f9615a.f9061j) {
                        if (aaVar.m11669c().equals(aaVar2.m11669c())) {
                            this.f9615a.f9061j.remove(aaVar2);
                            break;
                        }
                    }
                }
            } else {
                this.f9615a.f9061j.add(aaVar);
            }
            this.f9615a.f9059h.m11900a(i);
            this.f9615a.f9054c.setText(String.format(this.f9615a.getResources().getString(C1205R.string.has_select_num, new Object[]{Integer.valueOf(this.f9615a.f9061j.size())}), new Object[0]));
            if (this.f9615a.f9059h.getCount() == this.f9615a.f9061j.size()) {
                this.f9615a.f9056e.setText(C1205R.string.undo_select_all);
            } else {
                this.f9615a.f9056e.setText(C1205R.string.select_all);
            }
        }
    }
}
