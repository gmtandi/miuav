package com.fimi.soul.module.setting.newhand;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.fimi.soul.C1205R;
import com.fimi.soul.utils.be;
import java.util.ArrayList;

public class ai extends BaseAdapter {
    private Context f9494a;
    private ArrayList<String> f9495b;
    private LayoutInflater f9496c;
    private int f9497d;

    public ai(Context context, ArrayList<String> arrayList) {
        this.f9494a = context;
        this.f9495b = arrayList;
        this.f9496c = LayoutInflater.from(this.f9494a);
    }

    public void m11856a(int i) {
        this.f9497d = i;
    }

    public int getCount() {
        return this.f9495b.size();
    }

    public Object getItem(int i) {
        return this.f9495b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        aj ajVar;
        if (view == null) {
            ajVar = new aj(this);
            view = this.f9496c.inflate(C1205R.layout.item_set_newhand_mode, null);
            view.setTag(ajVar);
            ajVar.f9498a = (TextView) view.findViewById(C1205R.id.tv_group_item);
        } else {
            ajVar = (aj) view.getTag();
        }
        if (i == this.f9497d) {
            ajVar.f9498a.setTextColor(this.f9494a.getResources().getColor(C1205R.color.color_set_model));
        } else {
            ajVar.f9498a.setTextColor(this.f9494a.getResources().getColor(17170443));
        }
        ajVar.f9498a.setText((CharSequence) this.f9495b.get(i));
        be.m12359a(this.f9494a.getAssets(), ajVar.f9498a);
        return view;
    }
}
