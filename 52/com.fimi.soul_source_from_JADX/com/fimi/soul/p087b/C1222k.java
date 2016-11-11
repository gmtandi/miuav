package com.fimi.soul.p087b;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.fimi.soul.C1205R;
import com.fimi.soul.entity.FmMenuItem;
import java.util.List;

/* renamed from: com.fimi.soul.b.k */
public class C1222k extends BaseAdapter {
    private Context f5508a;
    private List<FmMenuItem> f5509b;
    private int f5510c;

    public C1222k(Context context, List<FmMenuItem> list) {
        this.f5508a = context;
        this.f5509b = list;
        this.f5510c = C1205R.layout.item_list_pop;
    }

    public int getCount() {
        return this.f5509b.size();
    }

    public Object getItem(int i) {
        return this.f5509b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        C1223l c1223l;
        if (view == null) {
            view = LayoutInflater.from(this.f5508a).inflate(this.f5510c, null);
            c1223l = new C1223l();
            c1223l.f5511a = (TextView) view.findViewById(C1205R.id.pop_item);
            view.setTag(c1223l);
        } else {
            c1223l = (C1223l) view.getTag();
        }
        FmMenuItem fmMenuItem = (FmMenuItem) this.f5509b.get(i);
        c1223l.f5511a.setText(fmMenuItem.getText());
        view.setId(fmMenuItem.getId());
        return view;
    }
}
