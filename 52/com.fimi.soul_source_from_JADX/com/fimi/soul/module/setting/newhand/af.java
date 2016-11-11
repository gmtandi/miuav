package com.fimi.soul.module.setting.newhand;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.fimi.soul.C1205R;
import java.util.ArrayList;

public class af {
    View f9485a;
    ListView f9486b;
    ArrayList<String> f9487c;
    PopupWindow f9488d;
    ai f9489e;
    TextView f9490f;
    ah f9491g;
    OnItemClickListener f9492h;

    public af() {
        this.f9492h = new ag(this);
    }

    public void m11854a(TextView textView, Context context, int i, ah ahVar) {
        this.f9490f = textView;
        this.f9491g = ahVar;
        this.f9485a = LayoutInflater.from(context).inflate(C1205R.layout.listview_newhand, null);
        this.f9486b = (ListView) this.f9485a.findViewById(C1205R.id.lv_group);
        this.f9487c = new ArrayList();
        this.f9487c.add(context.getString(C1205R.string.new_driver_mode));
        this.f9487c.add(context.getString(C1205R.string.normal_driver_model));
        this.f9489e = new ai(context, this.f9487c);
        this.f9489e.m11856a(i);
        this.f9486b.setAdapter(this.f9489e);
        this.f9488d = new PopupWindow(this.f9485a, -2, -2);
        this.f9488d.setBackgroundDrawable(context.getResources().getDrawable(C1205R.drawable.radio));
        this.f9488d.setFocusable(true);
        int[] iArr = new int[2];
        textView.getLocationOnScreen(iArr);
        this.f9488d.showAtLocation(textView, 0, iArr[0], iArr[1]);
        this.f9486b.setOnItemClickListener(this.f9492h);
    }
}
