package com.fimi.soul.biz.p090b;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.fimi.soul.C1205R;
import com.fimi.soul.utils.be;
import java.util.ArrayList;

/* renamed from: com.fimi.soul.biz.b.m */
public class C1255m {
    private static BitmapDescriptor f5691a;

    public static BitmapDescriptor m8605a(int i) {
        f5691a = BitmapDescriptorFactory.fromResource(i);
        return f5691a;
    }

    public static BitmapDescriptor m8606a(Context context, int i, int i2, boolean z) {
        View inflate = LayoutInflater.from(context).inflate(C1205R.layout.action_markericon, null);
        TextView textView = (TextView) inflate.findViewById(C1205R.id.point_heightValue);
        textView.setText(i2 + "m");
        if (z) {
            textView.setTextColor(context.getResources().getColor(C1205R.color.onmarker_color));
        } else {
            textView.setTextColor(context.getResources().getColor(C1205R.color.mystyle_markerinfo));
        }
        be.m12368b(context.getAssets(), textView);
        ImageView imageView = (ImageView) inflate.findViewById(C1205R.id.markerIcon);
        if (i != 0) {
            imageView.setBackgroundResource(i);
        }
        return BitmapDescriptorFactory.fromView(inflate);
    }

    public static ArrayList<BitmapDescriptor> m8607a(Context context, int i, boolean z, int i2, int i3) {
        ArrayList<BitmapDescriptor> arrayList = new ArrayList();
        arrayList.add(C1255m.m8609b(context, i2, i, z));
        arrayList.add(C1255m.m8609b(context, i3, i, z));
        return arrayList;
    }

    public static void m8608a() {
        if (f5691a != null) {
            f5691a.recycle();
        }
    }

    public static BitmapDescriptor m8609b(Context context, int i, int i2, boolean z) {
        View inflate = LayoutInflater.from(context).inflate(C1205R.layout.action_markericon_animation, null);
        TextView textView = (TextView) inflate.findViewById(C1205R.id.point_heightValue);
        textView.setText(i2 + "m");
        if (z) {
            textView.setTextColor(context.getResources().getColor(C1205R.color.onmarker_color));
        } else {
            textView.setTextColor(context.getResources().getColor(C1205R.color.mystyle_markerinfo));
        }
        be.m12368b(context.getAssets(), textView);
        ImageView imageView = (ImageView) inflate.findViewById(C1205R.id.markerIcon);
        if (i != 0) {
            imageView.setBackgroundResource(i);
        }
        return BitmapDescriptorFactory.fromView(inflate);
    }
}
