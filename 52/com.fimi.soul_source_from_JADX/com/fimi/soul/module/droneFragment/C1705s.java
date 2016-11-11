package com.fimi.soul.module.droneFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.fimi.soul.C1205R;
import com.fimi.soul.utils.be;

/* renamed from: com.fimi.soul.module.droneFragment.s */
class C1705s extends BaseAdapter {
    final /* synthetic */ C1697k f8328a;
    private Context f8329b;
    private String[] f8330c;

    public C1705s(C1697k c1697k, Context context, String[] strArr) {
        this.f8328a = c1697k;
        this.f8329b = context;
        this.f8330c = strArr;
    }

    public int getCount() {
        return this.f8330c == null ? 0 : this.f8330c.length;
    }

    public Object getItem(int i) {
        return this.f8330c == null ? null : this.f8330c[i];
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(this.f8329b).inflate(C1205R.layout.mysimple_spinner_dropdown_item, null);
        TextView textView = (TextView) inflate.findViewById(C1205R.id.itemangle);
        LayoutParams layoutParams = (LayoutParams) textView.getLayoutParams();
        if (this.f8328a.f8316v != null) {
            layoutParams.width = this.f8328a.f8316v.getWidth();
        }
        textView.setLayoutParams(layoutParams);
        textView.setText(this.f8330c[i]);
        be.m12359a(this.f8329b.getAssets(), textView);
        return inflate;
    }
}
