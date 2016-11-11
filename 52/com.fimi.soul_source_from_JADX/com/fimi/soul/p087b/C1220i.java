package com.fimi.soul.p087b;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import com.fimi.soul.C1205R;
import java.util.HashMap;
import java.util.List;

/* renamed from: com.fimi.soul.b.i */
public class C1220i extends BaseExpandableListAdapter {
    HashMap<String, List<String>> f5503a;
    List<String> f5504b;
    Context f5505c;

    public C1220i(Context context, HashMap<String, List<String>> hashMap, List<String> list) {
        this.f5505c = context;
        this.f5503a = hashMap;
        this.f5504b = list;
    }

    public void m8463a(HashMap<String, List<String>> hashMap, List<String> list) {
        this.f5503a = hashMap;
        this.f5504b = list;
        notifyDataSetChanged();
    }

    public Object getChild(int i, int i2) {
        return this.f5503a == null ? null : (String) ((List) this.f5503a.get(this.f5504b.get(i))).get(i2);
    }

    public long getChildId(int i, int i2) {
        return (long) i2;
    }

    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        C1221j c1221j;
        if (view == null) {
            view = LayoutInflater.from(this.f5505c).inflate(C1205R.layout.errorcodebody, null);
            C1221j c1221j2 = new C1221j(this);
            c1221j2.f5506a = (TextView) view.findViewById(C1205R.id.errorbody);
            view.setTag(c1221j2);
            c1221j = c1221j2;
        } else {
            c1221j = (C1221j) view.getTag();
        }
        c1221j.f5506a.setText((CharSequence) ((List) this.f5503a.get(this.f5504b.get(i))).get(i2));
        return view;
    }

    public int getChildrenCount(int i) {
        return this.f5503a == null ? 0 : ((List) this.f5503a.get(this.f5504b.get(i))).size();
    }

    public Object getGroup(int i) {
        return this.f5504b.get(i);
    }

    public int getGroupCount() {
        return this.f5504b.size();
    }

    public long getGroupId(int i) {
        return (long) i;
    }

    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(this.f5505c).inflate(C1205R.layout.errorcodeheard, null);
        ((TextView) inflate.findViewById(C1205R.id.errorheard)).setText((CharSequence) this.f5504b.get(i));
        return inflate;
    }

    public boolean hasStableIds() {
        return false;
    }

    public boolean isChildSelectable(int i, int i2) {
        return false;
    }
}
