package com.fimi.soul.base;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import com.fimi.kernel.p083d.C1160b;
import com.fimi.soul.drone.C1234i;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.utils.ay;
import com.xiaomi.mistatistic.sdk.MiStatInterface;
import org.p122a.p123a.C2915a;

@SuppressLint({"NewApi"})
@TargetApi(11)
public class BaseActivity extends BaseFimiActivity implements C1234i {
    public DroidPlannerApp dpa;
    public C1433a drone;
    protected ay preferencesUtil;
    public C1160b speakTTs;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.dpa = (DroidPlannerApp) getApplication();
        this.drone = this.dpa.f5570a;
        ViewGroup viewGroup = (ViewGroup) getWindow().getDecorView();
        this.preferencesUtil = new ay(this);
        this.speakTTs = C1160b.m7951a((Context) this);
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        finish();
        return true;
    }

    protected void onPause() {
        super.onPause();
        MiStatInterface.recordPageEnd();
    }

    protected void onResume() {
        super.onResume();
        MiStatInterface.recordPageStart(this, getTitle() != null ? getTitle().toString() : C2915a.f14760f);
    }

    protected void onStart() {
        super.onStart();
        this.drone.m9590a((C1234i) this);
    }

    protected void onStop() {
        super.onStop();
        this.drone.m9594b((C1234i) this);
    }

    public void setContentView(int i) {
        setAbContentView(i);
    }
}
