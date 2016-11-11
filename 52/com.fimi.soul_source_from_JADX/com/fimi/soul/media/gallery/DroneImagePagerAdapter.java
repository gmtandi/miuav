package com.fimi.soul.media.gallery;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import com.fimi.soul.view.photodraweeview.C1587g;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class DroneImagePagerAdapter extends FragmentStatePagerAdapter {
    Map<Integer, DroneImageDetailFragment> f7725a;
    private ArrayList<String> f7726b;
    private ArrayList<String> f7727c;
    private DroneImageDetailFragment f7728d;
    private C1587g f7729e;
    private ViewPager f7730f;

    public DroneImagePagerAdapter(FragmentManager fragmentManager, ArrayList<String> arrayList) {
        super(fragmentManager);
        this.f7725a = new Hashtable();
        this.f7726b = arrayList;
    }

    public DroneImagePagerAdapter(FragmentManager fragmentManager, ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        super(fragmentManager);
        this.f7725a = new Hashtable();
        this.f7726b = arrayList;
        this.f7727c = arrayList2;
    }

    public DroneImageDetailFragment m10695a(int i) {
        return (DroneImageDetailFragment) this.f7725a.get(Integer.valueOf(i));
    }

    public void m10696a(ViewPager viewPager) {
        this.f7730f = viewPager;
    }

    public void m10697a(C1587g c1587g) {
        this.f7729e = c1587g;
    }

    public void m10698a(ArrayList<String> arrayList) {
        this.f7726b = arrayList;
    }

    public int getCount() {
        return this.f7726b == null ? 0 : this.f7726b.size();
    }

    public Fragment getItem(int i) {
        String str = (String) this.f7726b.get(i);
        this.f7728d = new DroneImageDetailFragment();
        this.f7728d.m10649b(str);
        this.f7728d.m10646a(i);
        if (this.f7729e != null) {
            this.f7728d.m10647a(this.f7729e);
        }
        if (this.f7727c != null && this.f7727c.size() > 0) {
            this.f7728d.m10651c((String) this.f7727c.get(i));
        }
        this.f7725a.put(Integer.valueOf(i), this.f7728d);
        return this.f7728d;
    }

    public int getItemPosition(Object obj) {
        return (obj == null || ((DroneImageDetailFragment) obj).m10645a() != this.f7730f.getCurrentItem()) ? super.getItemPosition(obj) : -2;
    }
}
