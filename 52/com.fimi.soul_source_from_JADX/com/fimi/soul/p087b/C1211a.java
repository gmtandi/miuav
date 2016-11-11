package com.fimi.soul.p087b;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.fimi.kernel.p084e.ab;
import com.fimi.soul.C1205R;
import com.fimi.soul.entity.Describehistor;
import com.fimi.soul.entity.User;
import com.fimi.soul.utils.aa;
import java.util.List;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.b.a */
public class C1211a extends BaseAdapter {
    SharedPreferences f5458a;
    private Context f5459b;
    private List<Describehistor> f5460c;

    public C1211a(Context context) {
        this.f5459b = context;
        this.f5458a = context.getSharedPreferences("imagehead", 0);
    }

    public void m8438a() {
        notifyDataSetChanged();
    }

    public void m8439a(List<Describehistor> list) {
        this.f5460c = list;
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.f5460c == null ? 0 : this.f5460c.size();
    }

    public Object getItem(int i) {
        return this.f5460c == null ? null : (Describehistor) this.f5460c.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        C1212b c1212b;
        Describehistor describehistor = (Describehistor) this.f5460c.get(i);
        if (this.f5458a != null && describehistor.getUserID().equals(this.f5458a.getString(User.FN_NAME, C2915a.f14760f))) {
            describehistor.setUserImgUrl(this.f5458a.getString(describehistor.getUserID(), C2915a.f14760f));
        }
        if (view == null) {
            view = LayoutInflater.from(this.f5459b).inflate(C1205R.layout.describehistoryitem, null);
            C1212b c1212b2 = new C1212b(this);
            c1212b2.f5462b = (TextView) view.findViewById(C1205R.id.nickname);
            c1212b2.f5461a = (TextView) view.findViewById(C1205R.id.listnum);
            c1212b2.f5463c = (TextView) view.findViewById(C1205R.id.flytimedescribe);
            c1212b2.f5464d = (SimpleDraweeView) view.findViewById(C1205R.id.imageView1);
            view.setTag(c1212b2);
            c1212b = c1212b2;
        } else {
            c1212b = (C1212b) view.getTag();
        }
        c1212b.f5462b.setText(describehistor.getName());
        c1212b.f5461a.setText((i + 1) + C2915a.f14760f);
        c1212b.f5463c.setText(ab.m8015a(describehistor.getSumTime() / 60.0d, 2) + "Hrs");
        aa.m12208a(c1212b.f5464d, describehistor.getUserImgUrl());
        return view;
    }
}
