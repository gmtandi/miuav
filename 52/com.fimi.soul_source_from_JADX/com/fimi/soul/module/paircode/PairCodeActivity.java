package com.fimi.soul.module.paircode;

import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.BaseActivity;
import com.fimi.soul.drone.C1234i;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.drone.p117h.am;
import com.fimi.soul.utils.be;
import com.fimi.soul.view.aq;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

public class PairCodeActivity extends BaseActivity implements OnClickListener, C1234i {
    private FragmentManager f8868a;
    private PairResultFragment f8869b;
    private PairErrorFragment f8870c;
    private PairFragment f8871d;
    private TextView f8872e;
    private ImageView f8873f;
    private C1812a f8874g;

    private void m11541a() {
        aq aqVar = new aq(this);
        aqVar.m12748a(getString(C1205R.string.exit_dialog_msg));
        aqVar.m12749a(getString(C1205R.string.cancel), new C1813b(this));
        aqVar.m12753b(getString(C1205R.string.ensure), new C1814c(this));
        aqVar.m12746a().show();
    }

    public void m11542a(C1812a c1812a) {
        this.f8874g = c1812a;
    }

    public void onBackPressed() {
        if (this.f8869b == null || this.f8869b.f8890d != 2) {
            super.onBackPressed();
        } else {
            m11541a();
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1205R.id.pair_back_btn:
                if (this.f8869b == null || this.f8869b.f8890d != 2) {
                    finish();
                } else {
                    m11541a();
                }
            default:
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1205R.layout.paircode_layout);
        this.f8872e = (TextView) findViewById(C1205R.id.pair_title);
        this.f8873f = (ImageView) findViewById(C1205R.id.pair_back_btn);
        this.f8873f.setOnClickListener(this);
        be.m12359a(getAssets(), this.f8872e);
        this.f8868a = getFragmentManager();
        this.f8869b = new PairResultFragment();
        this.f8871d = new PairFragment();
        this.f8870c = new PairErrorFragment();
        this.f8868a.beginTransaction().replace(C1205R.id.pair_context, this.f8871d).commit();
        getWindow().addFlags(SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
        super.onDroneEvent(c1584h, c1433a);
        switch (C1815d.f8906a[c1584h.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                this.f8868a.beginTransaction().replace(C1205R.id.pair_context, this.f8869b).commit();
                am d = c1433a.m9598d();
                byte c = d.m10347c();
                if (this.f8874g != null) {
                    this.f8874g.m11545a(c, d.m10351e());
                }
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                this.f8868a.beginTransaction().replace(C1205R.id.pair_context, this.f8870c).commit();
            case Type.BYTE /*3*/:
                if (this.f8874g != null) {
                    this.f8874g.m11544a(16);
                }
            default:
        }
    }
}
