package com.fimi.soul.media.gallery;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.fimi.kernel.p084e.C1167f;
import com.fimi.soul.C1205R;
import com.fimi.soul.biz.camera.entity.X11FileInfo;
import com.fimi.soul.utils.aa;
import com.fimi.soul.view.photodraweeview.C1587g;
import com.fimi.soul.view.photodraweeview.PhotoDraweeView;

public class DroneImageDetailFragment extends Fragment {
    private int f7681a;
    private String f7682b;
    private boolean f7683c;
    private String f7684d;
    private PhotoDraweeView f7685e;
    private ProgressBar f7686f;
    private Bitmap f7687g;
    private C1587g f7688h;

    public DroneImageDetailFragment() {
        this.f7681a = 0;
        this.f7683c = false;
        this.f7687g = null;
    }

    public static DroneImageDetailFragment m10643a(String str) {
        return new DroneImageDetailFragment();
    }

    public int m10645a() {
        return this.f7681a;
    }

    public void m10646a(int i) {
        this.f7681a = i;
    }

    public void m10647a(C1587g c1587g) {
        this.f7688h = c1587g;
    }

    public void m10648b() {
        Log.d("Good", "loadImageView");
        if (this.f7682b.contains("&&")) {
            this.f7682b = this.f7682b.split("&&")[0];
        }
        if (this.f7684d != null) {
            aa.m12210a(this.f7685e, this.f7684d, this.f7682b, new C1588b());
        } else {
            aa.m12209a(this.f7685e, this.f7682b, new C1588b());
        }
        this.f7683c = true;
    }

    public void m10649b(String str) {
        this.f7682b = str;
    }

    public PhotoDraweeView m10650c() {
        return this.f7685e;
    }

    public void m10651c(String str) {
        this.f7684d = str;
    }

    public String m10652d() {
        return this.f7682b;
    }

    public String m10653e() {
        return this.f7684d;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.f7685e.getLayoutParams().height = C1167f.m8115d(getActivity()).heightPixels;
        this.f7685e.getLayoutParams().width = C1167f.m8115d(getActivity()).widthPixels;
        if (this.f7688h != null) {
            this.f7685e.setOnPhotoTapListener(this.f7688h);
        }
        if (this.f7683c) {
            m10648b();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C1205R.layout.image_detail_fragment, viewGroup, false);
        this.f7686f = (ProgressBar) inflate.findViewById(C1205R.id.loading);
        if (this.f7682b == null || this.f7682b.endsWith(X11FileInfo.FILE_TYPE_MP4)) {
            this.f7686f.setVisibility(8);
        } else {
            this.f7686f.setVisibility(0);
        }
        this.f7685e = (PhotoDraweeView) inflate.findViewById(C1205R.id.image);
        return inflate;
    }

    public void onDestroy() {
        super.onDestroy();
        this.f7687g = null;
        System.gc();
    }

    public void onStop() {
        super.onStop();
        if (!(this.f7687g == null || this.f7687g.isRecycled())) {
            this.f7687g.recycle();
        }
        System.gc();
    }
}
