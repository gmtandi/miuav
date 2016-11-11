package com.fimi.soul.media.gallery;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.ArrayList;

class al extends FragmentStatePagerAdapter {
    public ArrayList<String> f7794a;
    public boolean f7795b;
    final /* synthetic */ ImagePagerActivity f7796c;

    public al(ImagePagerActivity imagePagerActivity, FragmentManager fragmentManager, ArrayList<String> arrayList, boolean z) {
        this.f7796c = imagePagerActivity;
        super(fragmentManager);
        this.f7794a = arrayList;
        this.f7795b = z;
    }

    public int getCount() {
        return this.f7794a == null ? 0 : this.f7794a.size();
    }

    public Fragment getItem(int i) {
        return ImageDetailFragment.m10745a((String) this.f7794a.get(i), this.f7795b);
    }
}
