package com.fimi.soul.view.sliding;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
import java.util.ArrayList;

public class FmFragmentPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> f10999a;

    public FmFragmentPagerAdapter(FragmentManager fragmentManager, ArrayList<Fragment> arrayList) {
        super(fragmentManager);
        this.f10999a = null;
        this.f10999a = arrayList;
    }

    public int getCount() {
        return this.f10999a.size();
    }

    public Fragment getItem(int i) {
        return i < this.f10999a.size() ? (Fragment) this.f10999a.get(i) : (Fragment) this.f10999a.get(0);
    }
}
