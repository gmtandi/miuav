package com.fimi.soul.media.gallery;

import android.os.Bundle;
import android.widget.TextView;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.BaseActivity;
import com.fimi.soul.view.HackyViewPager;
import java.util.ArrayList;

public class ImagePagerActivity extends BaseActivity {
    public static final String f7773a = "image_index";
    public static final String f7774b = "image_urls";
    public static final String f7775c = "image_native";
    private static final String f7776d = "STATE_POSITION";
    private HackyViewPager f7777e;
    private int f7778f;
    private TextView f7779g;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1205R.layout.image_detail_pager);
        this.f7778f = getIntent().getIntExtra(f7773a, 0);
        ArrayList stringArrayListExtra = getIntent().getStringArrayListExtra(f7774b);
        boolean booleanExtra = getIntent().getBooleanExtra(f7775c, false);
        this.f7777e = (HackyViewPager) findViewById(C1205R.id.pager);
        this.f7777e.setAdapter(new al(this, getSupportFragmentManager(), stringArrayListExtra, booleanExtra));
        this.f7779g = (TextView) findViewById(C1205R.id.indicator);
        if (this.f7777e.getAdapter().getCount() == 1) {
            this.f7779g.setVisibility(4);
        }
        this.f7779g.setText(getString(C1205R.string.viewpager_indicator, new Object[]{Integer.valueOf(1), Integer.valueOf(this.f7777e.getAdapter().getCount())}));
        this.f7777e.setOnPageChangeListener(new ak(this));
        if (bundle != null) {
            this.f7778f = bundle.getInt(f7776d);
        }
        this.f7777e.setCurrentItem(this.f7778f);
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putInt(f7776d, this.f7777e.getCurrentItem());
    }
}
