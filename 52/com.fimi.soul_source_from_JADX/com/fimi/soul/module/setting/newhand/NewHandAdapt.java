package com.fimi.soul.module.setting.newhand;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import java.util.List;

public class NewHandAdapt extends FragmentStatePagerAdapter {
    private List<Fragment> f9375a;
    private Fragment f9376b;
    private FragmentManager f9377c;
    private FragmentTransaction f9378d;
    private ViewPager f9379e;
    private int f9380f;

    public NewHandAdapt(FragmentManager fragmentManager, List<Fragment> list, ViewPager viewPager) {
        super(fragmentManager);
        this.f9375a = list;
        this.f9379e = viewPager;
        this.f9377c = fragmentManager;
    }

    public int getCount() {
        return this.f9375a.size();
    }

    public Fragment getItem(int i) {
        return (Fragment) this.f9375a.get(i);
    }
}
