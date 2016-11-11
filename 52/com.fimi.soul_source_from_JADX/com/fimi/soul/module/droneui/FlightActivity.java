package com.fimi.soul.module.droneui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.fimi.kernel.C1189f;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.BaseActivity;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.biz.p090b.C1247f;
import com.fimi.soul.biz.p090b.C1253k;
import com.fimi.soul.biz.p097e.C1328a;
import com.fimi.soul.biz.p100g.C1345l;
import com.fimi.soul.biz.p103k.ab;
import com.fimi.soul.drone.C1234i;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.drone.p107c.p108a.p109a.C1448k;
import com.fimi.soul.drone.p107c.p108a.p109a.ba;
import com.fimi.soul.media.player.FimiMediaMeta;
import com.fimi.soul.media.player.FimiMediaPlayer;
import com.fimi.soul.module.droneFragment.DroneTakePhotoFragment;
import com.fimi.soul.module.droneFragment.FlyActionSettingFragment;
import com.fimi.soul.module.droneFragment.ShowDroneStatusFragment;
import com.fimi.soul.module.droneFragment.ShowDroneStatusLineFragment;
import com.fimi.soul.module.droneFragment.ShowDroneUiFragment;
import com.fimi.soul.module.droneFragment.bh;
import com.fimi.soul.module.dronemanage.C1734u;
import com.fimi.soul.module.dronemanage.FlightMapFragment;
import com.fimi.soul.module.login.C1739a;
import com.fimi.soul.module.login.FirstLeadFragment;
import com.fimi.soul.module.setting.MapSettingFragment;
import com.fimi.soul.module.setting.al;
import com.fimi.soul.service.CheckCampassCaliService;
import com.fimi.soul.utils.ay;
import com.fimi.soul.utils.be;
import com.fimi.soul.view.C2040u;
import com.fimi.soul.view.aq;
import com.hoho.android.usbserial.driver.FtdiSerialDriver;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.util.BufferRecycler;
import org.codehaus.jackson.util.TokenBuffer.Segment;

public class FlightActivity extends BaseActivity implements C1234i, bh, C1734u, C1739a, al {
    private static final int f8568k = 1;
    private static final int f8569s = 123;
    View f8570a;
    public C1755q f8571b;
    private FragmentManager f8572c;
    private FlightMapFragment f8573d;
    private FirstLeadFragment f8574e;
    private ShowDroneUiFragment f8575f;
    private ShowDroneStatusFragment f8576g;
    private ShowDroneStatusLineFragment f8577h;
    private DroneTakePhotoFragment f8578i;
    private MapSettingFragment f8579j;
    private boolean f8580l;
    private ProgressBar f8581m;
    private Handler f8582n;
    private RelativeLayout f8583o;
    private DrawerLayout f8584p;
    private FlyActionSettingFragment f8585q;
    private TextView f8586r;
    private int f8587t;
    private boolean f8588u;
    private Toast f8589v;

    public FlightActivity() {
        this.f8571b = C1755q.EarthMap;
        this.f8580l = true;
        this.f8582n = new C1749k(this);
        this.f8587t = 5;
    }

    private void m11327a(int i) {
        if (!this.f8581m.isShown()) {
            this.f8581m.setVisibility(0);
            if (this.f8582n.hasMessages(f8568k)) {
                this.f8582n.removeMessages(f8568k);
            }
            this.f8580l = true;
            if (C1247f.m8565k().m8578f().size() < f8568k) {
                this.f8582n.sendEmptyMessageDelayed(f8568k, (long) i);
            } else {
                this.f8582n.sendEmptyMessageDelayed(f8568k, (long) (C1247f.m8565k().m8578f().size() * BufferRecycler.DEFAULT_WRITE_CONCAT_BUFFER_LEN));
            }
        }
    }

    private void m11333k() {
        File file = new File(be.m12397o(this));
        if (!file.exists()) {
            try {
                file.createNewFile();
                InputStream open = getAssets().open("NoFlyZone-V1.0.0-DB.sqlite");
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                byte[] bArr = new byte[Opcodes.ACC_STRICT];
                while (true) {
                    int read = open.read(bArr);
                    if (read != -1) {
                        fileOutputStream.write(bArr, 0, read);
                    } else {
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        open.close();
                        return;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void m11334l() {
    }

    private void m11335m() {
        if (!this.f8583o.isShown()) {
            this.f8583o.setVisibility(0);
            this.f8572c.beginTransaction().hide(this.f8585q).commitAllowingStateLoss();
            if (this.f8578i.isVisible()) {
                this.f8572c.beginTransaction().hide(this.f8578i).commitAllowingStateLoss();
            }
            this.drone.m9589a(C1584h.HIDEDISPLAYCIRCLE);
        }
    }

    private void m11336n() {
        if (this.f8588u) {
            this.f8589v.cancel();
            finish();
            Process.killProcess(Process.myPid());
            this.f8575f.m11005e();
            return;
        }
        this.f8588u = true;
        this.f8589v = Toast.makeText(getApplicationContext(), C1205R.string.again_exit, 0);
        this.f8589v.show();
        getHandler().sendEmptyMessageDelayed(0, 2000);
    }

    public void m11337a() {
        if (this.f8579j != null) {
            this.f8579j.m11642a();
            this.f8572c.beginTransaction().show(this.f8579j).commit();
        }
    }

    public void m11338a(C1755q c1755q) {
        this.f8571b = c1755q;
    }

    public void m11339a(List<com.fimi.soul.drone.p117h.bh> list) {
    }

    public C1755q m11340b() {
        return this.f8571b;
    }

    public void m11341c() {
        if (this.f8579j != null) {
            this.f8572c.beginTransaction().hide(this.f8579j).commit();
            if (this.f8575f != null) {
                this.f8575f.onStart();
            }
        }
        if (this.f8573d != null) {
            this.f8573d.m11177a();
        }
        C1345l.m9001b().clear();
    }

    public void m11342d() {
        this.f8572c.beginTransaction().hide(this.f8573d).commitAllowingStateLoss();
        this.f8572c.executePendingTransactions();
    }

    public void m11343e() {
        this.f8572c.beginTransaction().show(this.f8573d).commitAllowingStateLoss();
    }

    public void m11344f() {
        this.f8570a.setVisibility(4);
    }

    public void m11345g() {
        this.f8570a.setVisibility(0);
    }

    public void m11346h() {
        if (C1189f.m8335e().m8031c()) {
            findViewById(C1205R.id.bottom_state_rl).setVisibility(0);
        } else {
            findViewById(C1205R.id.bottom_state_rl).setVisibility(4);
        }
    }

    public void m11347i() {
        if (be.m12370b((Context) this)) {
            ay ayVar = new ay(this);
            int a = ayVar.m12294a("insurance_ret");
            if (a != -2 && a != 100) {
                C2040u c2040u = new C2040u(this);
                c2040u.m13036b(getString(C1205R.string.insurance_content));
                c2040u.m13032a(getString(C1205R.string.insurance_title));
                c2040u.m13033a(getString(C1205R.string.insurance_confirm), new C1753o(this, ayVar, a)).m13037b(getString(C1205R.string.cancel), new C1752n(this, ayVar, a)).m13030a().show();
            }
        }
    }

    public void m11348j() {
        this.f8572c.beginTransaction().remove(this.f8574e).commit();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        SharedPreferences a = ay.m12293a((Context) this);
        if (a.getBoolean(C1236a.f5588L, false)) {
            Editor edit = ay.m12293a((Context) this).edit();
            edit.putBoolean(C1236a.f5588L, true);
            if (!(!a.getBoolean(C1236a.f5586J, false) || this.f8579j == null || this.f8579j.isHidden())) {
                this.f8572c.beginTransaction().hide(this.f8579j).commit();
            }
            edit.putBoolean(C1236a.f5586J, false);
            edit.commit();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(SmileConstants.TOKEN_PREFIX_TINY_UNICODE, SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
        this.dpa.m8525d();
        FimiMediaPlayer.loadLibrariesOnce(null);
        FimiMediaPlayer.native_profileBegin("libfmplayer.so");
        this.f8570a = findViewById(C1205R.id.showStatus);
        setContentView(C1205R.layout.activity_main);
        this.f8586r = (TextView) findViewById(C1205R.id.reciprocal_value);
        this.f8574e = new FirstLeadFragment();
        this.f8574e.m11468a((C1739a) this);
        this.f8581m = (ProgressBar) findViewById(C1205R.id.progrebar);
        this.f8583o = (RelativeLayout) findViewById(C1205R.id.normal_rla);
        this.f8584p = (DrawerLayout) findViewById(C1205R.id.drawerview);
        this.f8584p.setDrawerLockMode(f8568k);
        this.f8584p.setFocusableInTouchMode(false);
        if (ab.m9180a((Context) this).m9184b()) {
            AudioManager audioManager = (AudioManager) getSystemService(FimiMediaMeta.IJKM_VAL_TYPE__AUDIO);
            audioManager.setStreamVolume(3, audioManager.getStreamMaxVolume(3), 0);
        }
        this.f8572c = getSupportFragmentManager();
        this.f8578i = (DroneTakePhotoFragment) this.f8572c.findFragmentById(C1205R.id.takephoto);
        if (this.f8578i == null) {
            this.f8578i = new DroneTakePhotoFragment();
            this.f8572c.beginTransaction().add(C1205R.id.takephoto, this.f8578i, "takephoto").hide(this.f8578i).commitAllowingStateLoss();
        } else {
            this.f8572c.beginTransaction().hide(this.f8578i).commit();
        }
        this.f8585q = (FlyActionSettingFragment) this.f8572c.findFragmentById(C1205R.id.flyaction);
        if (this.f8585q == null) {
            this.f8585q = new FlyActionSettingFragment();
            this.f8572c.beginTransaction().add(C1205R.id.flyaction, this.f8585q, "fly").hide(this.f8585q).commit();
        } else {
            this.f8572c.beginTransaction().hide(this.f8585q).commit();
        }
        this.f8573d = (FlightMapFragment) this.f8572c.findFragmentById(C1205R.id.mapFragment);
        if (this.f8573d == null) {
            this.f8573d = new FlightMapFragment();
            this.f8572c.beginTransaction().add((int) C1205R.id.mapFragment, this.f8573d).commit();
        }
        this.f8577h = (ShowDroneStatusLineFragment) this.f8572c.findFragmentById(C1205R.id.showBasedata);
        if (this.f8577h == null) {
            this.f8577h = new ShowDroneStatusLineFragment();
            this.f8572c.beginTransaction().add((int) C1205R.id.showBasedata, this.f8577h).commit();
        }
        this.f8576g = (ShowDroneStatusFragment) this.f8572c.findFragmentById(C1205R.id.showStatus);
        if (this.f8576g == null) {
            this.f8576g = new ShowDroneStatusFragment();
            this.f8572c.beginTransaction().add((int) C1205R.id.showStatus, this.f8576g).commit();
        }
        this.f8575f = (ShowDroneUiFragment) this.f8572c.findFragmentById(C1205R.id.handleWapoint);
        if (this.f8575f == null) {
            this.f8575f = new ShowDroneUiFragment();
            this.f8575f.m11000a(this.f8577h, this.f8576g);
            this.f8572c.beginTransaction().add((int) C1205R.id.handleWapoint, this.f8575f).commit();
        }
        this.f8579j = (MapSettingFragment) this.f8572c.findFragmentById(C1205R.id.activity_general_setting);
        if (this.f8579j == null) {
            this.f8579j = new MapSettingFragment();
            this.f8572c.beginTransaction().add((int) C1205R.id.activity_general_setting, this.f8579j).hide(this.f8579j).commit();
        }
        m11333k();
        startService(new Intent(this, CheckCampassCaliService.class));
        if (ay.m12293a((Context) this).getInt("curModel", f8568k) == f8568k) {
            this.f8572c.beginTransaction().add((int) C1205R.id.lead_layout, this.f8574e).commit();
        }
        new C1328a(this).m8949a();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.f8575f != null) {
            this.f8575f.m11001a(false);
        }
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
        switch (C1754p.f8623a[c1584h.ordinal()]) {
            case f8568k /*1*/:
                if (this.f8582n.hasMessages(f8569s)) {
                    this.f8582n.removeMessages(f8569s);
                }
                if (this.f8582n.hasMessages(f8568k)) {
                    this.f8582n.removeMessages(f8568k);
                }
                this.f8586r.setVisibility(8);
                this.f8581m.setVisibility(8);
                this.f8580l = false;
                return;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                if (c1433a.an().m10147j() == 18) {
                    if (!this.f8582n.hasMessages(f8569s)) {
                        this.f8587t = 5;
                    }
                    this.f8582n.sendEmptyMessage(f8569s);
                }
                if (this.f8582n.hasMessages(f8568k)) {
                    this.f8582n.removeMessages(f8568k);
                }
                this.f8581m.setVisibility(8);
                this.f8580l = false;
                return;
            case Type.BYTE /*3*/:
                if (!this.f8583o.isShown()) {
                    this.f8583o.setVisibility(0);
                }
                if (this.f8578i.isVisible()) {
                    this.f8572c.beginTransaction().hide(this.f8578i).commitAllowingStateLoss();
                    return;
                }
                return;
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                if (this.f8582n.hasMessages(f8568k)) {
                    this.f8582n.removeMessages(f8568k);
                }
                this.f8581m.setVisibility(8);
                this.f8580l = false;
                return;
            case Type.INT /*5*/:
                m11327a((int) FtdiSerialDriver.USB_WRITE_TIMEOUT_MILLIS);
                return;
            case Type.FLOAT /*6*/:
                if (c1433a.ah().m10172g().isExceAction() && !this.f8583o.isShown()) {
                    this.f8583o.setVisibility(0);
                    this.f8572c.beginTransaction().hide(this.f8585q).commitAllowingStateLoss();
                    c1433a.m9589a(C1584h.HIDEDISPLAYCIRCLE);
                    return;
                }
                return;
            case Type.LONG /*7*/:
                this.f8583o.setVisibility(0);
                this.f8572c.beginTransaction().hide(this.f8585q).commitAllowingStateLoss();
                this.f8572c.beginTransaction().hide(this.f8578i).commitAllowingStateLoss();
                this.f8585q.setUserVisibleHint(true);
                c1433a.m9589a(C1584h.HIDEDISPLAYCIRCLE);
                return;
            case Type.DOUBLE /*8*/:
                if (c1433a.m9570Q() && !c1433a.aa()) {
                    aq aqVar = new aq(this);
                    aqVar.m12748a(getString(C1205R.string.showCampassmess));
                    aqVar.m12747a(getResources().getColor(C1205R.color.dialog_ensure_hot));
                    aqVar.m12753b(getString(C1205R.string.ensure), new C1751m(this)).m12749a(getString(C1205R.string.cancel), new C1750l(this)).m12746a().show();
                    return;
                }
                return;
            case Type.ARRAY /*9*/:
                C1253k a = C1253k.m8598a(getApplicationContext());
                if (!c1433a.aa() || a.m8599a().get() == 0) {
                    this.f8583o.setVisibility(0);
                    this.f8572c.beginTransaction().hide(this.f8585q).commitAllowingStateLoss();
                    this.f8572c.beginTransaction().hide(this.f8578i).commitAllowingStateLoss();
                } else if (c1433a.ah().m10172g().judgeNoAction()) {
                    this.f8583o.setVisibility(4);
                    if (a.m8599a().get() == 4) {
                        this.f8572c.beginTransaction().show(this.f8578i).commitAllowingStateLoss();
                    } else {
                        this.f8572c.beginTransaction().show(this.f8585q).commitAllowingStateLoss();
                    }
                }
                this.f8584p.closeDrawer((int) GravityCompat.END);
                return;
            case Type.OBJECT /*10*/:
                this.f8584p.openDrawer((int) GravityCompat.END);
                return;
            case Opcodes.T_LONG /*11*/:
                if (this.f8571b != C1755q.Camera) {
                    m11342d();
                    break;
                } else {
                    m11343e();
                    break;
                }
            case Opcodes.FCONST_1 /*12*/:
                break;
            case Opcodes.FCONST_2 /*13*/:
                m11347i();
                return;
            case Opcodes.DCONST_0 /*14*/:
                m11335m();
                return;
            case Opcodes.DCONST_1 /*15*/:
                m11335m();
                return;
            case Segment.TOKENS_PER_SEGMENT /*16*/:
                m11335m();
                return;
            default:
                return;
        }
        if (!this.f8583o.isShown()) {
            this.f8583o.setVisibility(0);
        }
        if (this.f8585q.isVisible()) {
            this.f8572c.beginTransaction().hide(this.f8585q).commitAllowingStateLoss();
        }
        if (this.f8578i.isVisible()) {
            this.f8572c.beginTransaction().hide(this.f8578i).commitAllowingStateLoss();
        }
    }

    public void onHandleMessage(Message message) {
        super.onHandleMessage(message);
        this.f8588u = false;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.f8584p.isDrawerOpen((int) GravityCompat.END)) {
            this.f8584p.closeDrawer((int) GravityCompat.END);
            return true;
        }
        this.drone.ah().m10170e();
        if (this.f8585q != null && !this.f8585q.isHidden()) {
            this.f8585q.m10899a();
            if (!this.f8583o.isShown()) {
                this.f8583o.setVisibility(0);
            }
            if (!this.f8585q.isVisible()) {
                return true;
            }
            this.f8572c.beginTransaction().hide(this.f8585q).commitAllowingStateLoss();
            return true;
        } else if (this.f8578i != null && !this.f8578i.isHidden()) {
            if (!this.f8583o.isShown()) {
                this.f8583o.setVisibility(0);
            }
            if (!this.f8578i.isVisible()) {
                return true;
            }
            this.f8572c.beginTransaction().hide(this.f8578i).commitAllowingStateLoss();
            return true;
        } else if (this.f8579j != null && !this.f8579j.isHidden()) {
            if (this.f8579j.m11647c()) {
                this.f8572c.beginTransaction().hide(this.f8579j).commit();
            }
            return false;
        } else if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        } else {
            m11336n();
            return false;
        }
    }

    protected void onPause() {
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        if (this.f8579j == null) {
            this.f8579j = new MapSettingFragment();
            this.f8572c.beginTransaction().add((int) C1205R.id.activity_general_setting, this.f8579j).hide(this.f8579j).commit();
        }
        if (this.f8575f != null) {
            this.f8575f.m11001a(this.drone.m9575V());
        }
        C1345l.m9001b().clear();
        if (this.drone.m9569P().m9995a() && this.drone.m9570Q()) {
            ba baVar = new ba();
            baVar.f6719d = C1448k.f6847b;
            baVar.f6720e = (byte) 1;
            baVar.f6721f = (byte) 0;
            this.drone.m9569P().m9993a(baVar.m9691a());
        }
    }

    protected void onStart() {
        super.onStart();
    }

    protected void onStop() {
        super.onStop();
        FimiMediaPlayer.native_profileEnd();
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        m11334l();
    }
}
