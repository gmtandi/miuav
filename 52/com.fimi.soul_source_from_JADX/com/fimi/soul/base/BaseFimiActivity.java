package com.fimi.soul.base;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import com.fimi.kernel.BaseActivity;
import com.fimi.kernel.p084e.ae;
import com.fimi.soul.view.titlebar.FmBottomBar;
import com.fimi.soul.view.titlebar.FmTitleBar;
import com.tencent.mm.sdk.platformtools.Util;

public abstract class BaseFimiActivity extends BaseActivity {
    public Application abApplication;
    public RelativeLayout ab_base;
    protected RelativeLayout contentLayout;
    private FmBottomBar mAbBottomBar;
    private FmTitleBar mAbTitleBar;
    public LayoutInflater mInflater;
    protected ae mTintManager;

    public BaseFimiActivity() {
        this.abApplication = null;
        this.ab_base = null;
        this.mAbTitleBar = null;
        this.mAbBottomBar = null;
        this.contentLayout = null;
    }

    public void finish() {
        super.finish();
    }

    public FmBottomBar getBottomBar() {
        return this.mAbBottomBar;
    }

    public FmTitleBar getTitleBar() {
        this.mAbTitleBar.setVisibility(0);
        return this.mAbTitleBar;
    }

    public void onConfigurationChanged(Configuration configuration) {
        this.mAbTitleBar.m13024b();
        super.onConfigurationChanged(configuration);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        this.mInflater = LayoutInflater.from(this);
        this.mTintManager = new ae(this);
        if (VERSION.SDK_INT >= 19) {
            setTranslucentStatus(true);
        }
        this.mTintManager.m8046a(true);
        this.mAbTitleBar = new FmTitleBar(this);
        this.ab_base = new RelativeLayout(this);
        this.ab_base.setBackgroundColor(Color.rgb(Util.MASK_8BIT, Util.MASK_8BIT, Util.MASK_8BIT));
        this.contentLayout = new RelativeLayout(this);
        this.contentLayout.setPadding(0, 0, 0, 0);
        this.mAbBottomBar = new FmBottomBar(this);
        this.ab_base.addView(this.mAbTitleBar, new LayoutParams(-1, -2));
        this.mAbTitleBar.setVisibility(8);
        ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(12, -1);
        this.ab_base.addView(this.mAbBottomBar, layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(3, this.mAbTitleBar.getId());
        layoutParams.addRule(2, this.mAbBottomBar.getId());
        this.ab_base.addView(this.contentLayout, layoutParams);
        this.abApplication = getApplication();
        setContentView(this.ab_base, new LayoutParams(-1, -1));
    }

    protected void onDestroy() {
        this.mAbTitleBar.m13024b();
        super.onDestroy();
    }

    protected void onPause() {
        super.onPause();
        this.mAbTitleBar.m13024b();
    }

    public void setAbContentView(int i) {
        setAbContentView(this.mInflater.inflate(i, null));
    }

    public void setAbContentView(View view) {
        this.contentLayout.removeAllViews();
        this.contentLayout.addView(view, new LayoutParams(-1, -1));
    }

    public void setContentView(int i) {
        super.setContentView(i);
    }

    public void setContentView(View view) {
        super.setContentView(view);
    }

    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
    }

    public void setStatusBarTintResource(int i) {
        this.mTintManager.m8057d(i);
    }

    public void setTitleBarOverlay(boolean z) {
        this.ab_base.removeAllViews();
        if (z) {
            ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams.addRule(2, this.mAbBottomBar.getId());
            this.ab_base.addView(this.contentLayout, layoutParams);
            layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams.addRule(10, -1);
            this.ab_base.addView(this.mAbTitleBar, layoutParams);
            layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams.addRule(12, -1);
            this.ab_base.addView(this.mAbBottomBar, layoutParams);
            return;
        }
        this.ab_base.addView(this.mAbTitleBar, new LayoutParams(-1, -2));
        layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(12, -1);
        this.ab_base.addView(this.mAbBottomBar, layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(3, this.mAbTitleBar.getId());
        layoutParams.addRule(2, this.mAbBottomBar.getId());
        this.ab_base.addView(this.contentLayout, layoutParams);
    }

    public void setTitleColor(int i) {
        super.setTitleColor(i);
    }

    @TargetApi(19)
    protected void setTranslucentStatus(boolean z) {
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (z) {
            attributes.flags |= 67108864;
        } else {
            attributes.flags &= -67108865;
        }
        window.setAttributes(attributes);
    }
}
