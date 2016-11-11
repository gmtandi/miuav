package com.fimi.soul.module.setting.newhand;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.BaseActivity;
import java.util.ArrayList;
import java.util.List;

public class NewHandActivity extends BaseActivity {
    private NewHandAdapt f9370a;
    private ViewPager f9371b;
    private int f9372c;
    private int f9373d;
    private int f9374e;

    public ViewPager m11784a() {
        if (this.f9371b == null) {
            this.f9371b = (ViewPager) findViewById(C1205R.id.view_pager);
        }
        return this.f9371b;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1205R.layout.activity_new_hand);
        List arrayList = new ArrayList();
        arrayList.add(new NewHandStartPagerFragment());
        arrayList.add(new NewHandOneFragment());
        arrayList.add(new NewHandTwoFragment());
        arrayList.add(new NewHandThreeFragment());
        arrayList.add(new NewHandFourFragment());
        arrayList.add(new NewHandFiveFragment());
        arrayList.add(new NewHandSixFragment());
        arrayList.add(new NewHandSevenFragment());
        this.f9370a = new NewHandAdapt(getSupportFragmentManager(), arrayList, m11784a());
        m11784a().setAdapter(this.f9370a);
        m11784a().setCurrentItem(0);
        m11784a().setOnPageChangeListener(new C1872n(this));
    }
}
