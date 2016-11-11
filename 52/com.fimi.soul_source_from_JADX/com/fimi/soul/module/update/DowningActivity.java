package com.fimi.soul.module.update;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.fimi.kernel.C1189f;
import com.fimi.kernel.view.progress.ProgressView;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.BaseActivity;
import com.fimi.soul.biz.update.C1403p;
import com.fimi.soul.biz.update.ak;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.entity.UpdateVersonBean;
import com.fimi.soul.utils.C1969i;
import java.io.File;
import java.util.List;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

public class DowningActivity extends BaseActivity {
    private static final int f9684g = 100;
    private static final int f9685h = 0;
    private static final int f9686m = 1;
    C1919k f9687a;
    List<UpdateVersonBean> f9688b;
    private ImageView f9689c;
    private ProgressView f9690d;
    private TextView f9691e;
    private int f9692f;
    private long f9693i;
    private int f9694j;
    private long f9695k;
    private boolean f9696l;
    private C1433a f9697n;
    private Toast f9698o;

    public DowningActivity() {
        this.f9692f = f9685h;
        this.f9687a = null;
        this.f9693i = 0;
        this.f9694j = f9686m;
    }

    private void m11932a() {
        if (this.f9696l) {
            C1403p.f6309b = true;
            this.f9698o.cancel();
            m11939a(FindOnlineFirmwareAvtivity.class);
            finish();
            return;
        }
        this.f9696l = true;
        this.f9698o = Toast.makeText(getApplicationContext(), C1205R.string.cancel_downing, f9685h);
        this.f9698o.show();
        getHandler().sendEmptyMessageDelayed(f9686m, 2000);
    }

    private void m11933a(UpdateVersonBean updateVersonBean) {
        this.f9687a.m12080b(updateVersonBean, this.f9687a.m12081e(updateVersonBean), new C1915g(this));
    }

    private void m11934a(List<UpdateVersonBean> list) {
        File file = new File(C1969i.m12493p());
        if (!file.exists()) {
            file.mkdir();
        }
        if (list != null && list.size() > 0) {
            this.f9692f = list.size();
            this.f9687a = new C1919k();
            UpdateVersonBean updateVersonBean = (UpdateVersonBean) list.get(f9685h);
            this.f9693i = ak.m9422a((List) list);
            this.f9690d.setMaxCount((float) this.f9693i);
            m11933a(updateVersonBean);
        }
    }

    public void m11939a(Class cls) {
        startActivity(new Intent(this, cls));
        finish();
        overridePendingTransition(17432576, 17432577);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.dpa.m8523b((Activity) this);
        setContentView(C1205R.layout.activity_downing);
        getWindow().setFlags(SmileConstants.TOKEN_PREFIX_TINY_UNICODE, SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
        this.f9689c = (ImageView) findViewById(C1205R.id.down_img_anim);
        this.f9690d = (ProgressView) findViewById(C1205R.id.down_progress_view);
        this.f9691e = (TextView) findViewById(C1205R.id.tv_down_progress);
        C1403p.f6309b = false;
        C1403p.f6310c = false;
        this.f9688b = C1189f.m8333c().m7935b(UpdateVersonBean.SP_NEED_DOWN_FIRMWWARES, UpdateVersonBean.class);
        m11934a(this.f9688b);
        this.f9697n = this.dpa.f5570a;
    }

    protected void onDestroy() {
        super.onDestroy();
        C1403p.f6310c = true;
    }

    public void onHandleMessage(Message message) {
        super.onHandleMessage(message);
        if (message.what == f9684g) {
            long j = this.f9695k + ((long) message.arg1);
            this.f9691e.setText(((100 * j) / this.f9693i) + C2915a.f14760f);
            this.f9690d.setCurrentCount((float) j);
        }
        if (message.what == 0) {
            this.f9695k = Long.valueOf(((UpdateVersonBean) this.f9688b.get(this.f9694j - 1)).getSize()).longValue() + this.f9695k;
            UpdateVersonBean updateVersonBean = (UpdateVersonBean) this.f9688b.get(this.f9694j);
            this.f9694j += f9686m;
            m11933a(updateVersonBean);
        }
        if (message.what == f9686m) {
            this.f9696l = false;
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        m11932a();
        return false;
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        this.f9689c.setBackgroundResource(C1205R.drawable.update_anim);
        ((AnimationDrawable) this.f9689c.getBackground()).start();
    }
}
