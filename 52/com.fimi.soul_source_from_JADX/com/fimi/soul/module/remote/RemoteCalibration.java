package com.fimi.soul.module.remote;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.BaseActivity;
import com.fimi.soul.drone.C1234i;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.module.calibcompass.C1683q;
import com.fimi.soul.module.p091a.C1664h;
import com.fimi.soul.view.aq;
import org.codehaus.jackson.smile.SmileConstants;

public class RemoteCalibration extends BaseActivity implements C1234i, C1820a {
    private RemoteMidCalibrationFragment f8948a;
    private BaseRemoteFragment f8949b;
    private FragmentManager f8950c;
    private C1664h f8951d;
    private RemoteRollerFragment f8952e;

    public void m11564a() {
        aq aqVar = new aq(this);
        aqVar.m12748a(getString(C1205R.string.interruptcaliremote));
        aqVar.m12753b(getString(C1205R.string.cancel), new C1825f(this)).m12749a(getString(C1205R.string.ensure), new C1824e(this)).m12746a().show();
    }

    public void m11565a(int i, int i2) {
        this.f8950c.beginTransaction().hide(this.f8950c.findFragmentById(i)).commit();
        this.f8950c.beginTransaction().show(this.f8950c.findFragmentById(i2)).commit();
    }

    public void m11566a(int i, int i2, String str, boolean z) {
        if (!(this.f8950c.findFragmentById(i) instanceof ErrorCaliBretionFragment)) {
            this.f8950c.beginTransaction().hide(this.f8950c.findFragmentById(i)).commit();
            if (this.f8950c.findFragmentById(i2) != null && (this.f8950c.findFragmentById(i2) instanceof ErrorCaliBretionFragment)) {
                ErrorCaliBretionFragment errorCaliBretionFragment = (ErrorCaliBretionFragment) this.f8950c.findFragmentById(i2);
                errorCaliBretionFragment.m11559a(str, z);
                if (errorCaliBretionFragment.isHidden()) {
                    this.f8950c.beginTransaction().show(errorCaliBretionFragment).commit();
                }
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1205R.layout.remotecalibration);
        getWindow().setFlags(SmileConstants.TOKEN_PREFIX_TINY_UNICODE, SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
        this.f8951d = C1664h.m10813a(this.drone);
        this.f8950c = getSupportFragmentManager();
        if (bundle == null) {
            this.f8949b = (BaseRemoteFragment) this.f8950c.findFragmentById(C1205R.id.basefragment);
            if (this.f8949b == null) {
                this.f8949b = new BaseRemoteFragment();
                this.f8950c.beginTransaction().add((int) C1205R.id.basefragment, this.f8949b).commit();
            }
            this.f8952e = (RemoteRollerFragment) this.f8950c.findFragmentById(C1205R.id.remote_roller_fragment);
            if (this.f8952e == null) {
                this.f8952e = new RemoteRollerFragment();
            }
            this.f8950c.beginTransaction().add((int) C1205R.id.remote_roller_fragment, this.f8952e).commit();
            Fragment fragment = (RemoteMidcalingFragment) this.f8950c.findFragmentById(C1205R.id.midcalibrationing);
            if (fragment == null) {
                fragment = new RemoteMidcalingFragment();
            }
            this.f8950c.beginTransaction().add((int) C1205R.id.midcalibrationing, fragment).hide(fragment).commit();
            fragment = (RemoteEndCaliFragment) this.f8950c.findFragmentById(C1205R.id.endmidcalibration);
            if (fragment == null) {
                fragment = new RemoteEndCaliFragment();
            }
            this.f8950c.beginTransaction().add((int) C1205R.id.endmidcalibration, fragment).hide(fragment).commit();
            fragment = (CarliRemoteFragment) this.f8950c.findFragmentById(C1205R.id.caliremotestart);
            if (fragment == null) {
                fragment = new CarliRemoteFragment();
            }
            this.f8950c.beginTransaction().add((int) C1205R.id.caliremotestart, fragment).hide(fragment).commit();
            fragment = (WhellRemoteFragment) this.f8950c.findFragmentById(C1205R.id.cariremotecomple);
            if (fragment == null) {
                fragment = new WhellRemoteFragment();
            }
            this.f8950c.beginTransaction().replace(C1205R.id.cariremotecomple, fragment).hide(fragment).commit();
            fragment = (ErrorCaliBretionFragment) this.f8950c.findFragmentById(C1205R.id.errorcalifragment);
            if (fragment == null) {
                fragment = new ErrorCaliBretionFragment();
            }
            this.f8950c.beginTransaction().add((int) C1205R.id.errorcalifragment, fragment).hide(fragment).commit();
        }
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
        switch (C1826g.f9010a[c1584h.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                C1683q.m10886a().m10887a("98");
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                for (Fragment fragment : this.f8950c.getFragments()) {
                    if (fragment.isVisible() && this.f8952e != null && !this.f8952e.isVisible()) {
                        m11566a(fragment.getId(), C1205R.id.errorcalifragment, getString(C1205R.string.calidisconremote), false);
                        return;
                    }
                }
            default:
        }
    }

    public void onHandleMessage(Message message) {
        super.onHandleMessage(message);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        if (this.f8952e == null || !this.f8952e.isVisible()) {
            m11564a();
        } else {
            finish();
        }
        return false;
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    protected void onStart() {
        super.onStart();
        this.drone.m9590a((C1234i) this);
        this.f8951d.m10834j();
    }
}
