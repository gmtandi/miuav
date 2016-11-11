package com.fimi.soul.module.setting;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.fimi.kernel.p084e.aa;
import com.fimi.kernel.p084e.ak;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.BaseActivity;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.biz.p103k.ao;
import com.fimi.soul.entity.SuggestBean;
import com.fimi.soul.module.login.LoginActivity;
import com.fimi.soul.utils.ay;
import com.fimi.soul.utils.be;
import com.fimi.soul.view.titlebar.FmTitleBar;
import com.tencent.connect.common.Constants;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import java.io.IOException;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.util.BufferRecycler;

public class UserFeedBackActivity extends BaseActivity implements OnClickListener {
    private final String f9169A;
    private final String f9170B;
    private String f9171C;
    private OnCheckedChangeListener f9172D;
    private ImageView f9173a;
    private ImageView f9174b;
    private Bitmap f9175c;
    private Bitmap f9176d;
    private Button f9177e;
    private Button f9178f;
    private FmTitleBar f9179g;
    private InputMethodManager f9180h;
    private CheckBox f9181i;
    private CheckBox f9182j;
    private CheckBox f9183k;
    private CheckBox f9184l;
    private TextView f9185m;
    private TextView f9186n;
    private TextView f9187o;
    private TextView f9188p;
    private EditText f9189q;
    private EditText f9190r;
    private TextView f9191s;
    private TextView f9192t;
    private TextView f9193u;
    private TextView f9194v;
    private ProgressDialog f9195w;
    private SuggestBean f9196x;
    private final String f9197y;
    private final String f9198z;

    public UserFeedBackActivity() {
        this.f9173a = null;
        this.f9174b = null;
        this.f9175c = null;
        this.f9176d = null;
        this.f9195w = null;
        this.f9196x = new SuggestBean();
        this.f9197y = Constants.VIA_TO_TYPE_QQ_GROUP;
        this.f9198z = Constants.VIA_SSO_LOGIN;
        this.f9169A = Constants.VIA_TO_TYPE_QQ_DISCUSS_GROUP;
        this.f9170B = Constants.VIA_TO_TYPE_QZONE;
        this.f9171C = Constants.VIA_TO_TYPE_QQ_GROUP;
        this.f9172D = new aw(this);
    }

    private void m11651a(int i, int i2) {
        ak.m8083a((Context) this, i, i2);
    }

    private void m11654b() {
        this.f9177e = (Button) findViewById(C1205R.id.back_btn);
        this.f9177e.setOnClickListener(new av(this));
    }

    private void m11656c() {
        this.f9191s = (TextView) findViewById(C1205R.id.textView1);
        this.f9192t = (TextView) findViewById(C1205R.id.textView2);
        this.f9193u = (TextView) findViewById(C1205R.id.textView3);
        this.f9194v = (TextView) findViewById(C1205R.id.textView4);
        this.f9181i = (CheckBox) findViewById(C1205R.id.fimi_plane);
        this.f9182j = (CheckBox) findViewById(C1205R.id.fimi_control);
        this.f9183k = (CheckBox) findViewById(C1205R.id.fimi_camera);
        this.f9184l = (CheckBox) findViewById(C1205R.id.fimi_app);
        this.f9181i.setChecked(true);
        this.f9181i.setOnCheckedChangeListener(this.f9172D);
        this.f9182j.setOnCheckedChangeListener(this.f9172D);
        this.f9183k.setOnCheckedChangeListener(this.f9172D);
        this.f9184l.setOnCheckedChangeListener(this.f9172D);
        this.f9185m = (TextView) findViewById(C1205R.id.plane_des);
        this.f9186n = (TextView) findViewById(C1205R.id.control_des);
        this.f9188p = (TextView) findViewById(C1205R.id.app_des);
        this.f9187o = (TextView) findViewById(C1205R.id.camera_des);
        this.f9189q = (EditText) findViewById(C1205R.id.suggestEdit);
        this.f9190r = (EditText) findViewById(C1205R.id.contactEdit);
        this.f9173a = (ImageView) findViewById(C1205R.id.image_add0);
        this.f9174b = (ImageView) findViewById(C1205R.id.image_del);
        this.f9178f = (Button) findViewById(C1205R.id.sendSuggest);
        this.f9174b.setVisibility(4);
        this.f9174b.setOnClickListener(this);
        this.f9173a.setOnClickListener(this);
        this.f9178f.setOnClickListener(this);
    }

    private boolean m11658d() {
        if (ay.m12293a((Context) this).contains("isfirstloading")) {
            return true;
        }
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("login", true);
        startActivity(intent);
        finish();
        return false;
    }

    public void m11660a() {
        Log.v("Method:", "doTrans");
        try {
            if (TextUtils.isEmpty(this.f9189q.getText())) {
                m11651a((int) C1205R.string.please_fill_feedcontent, (int) XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER);
            } else if (this.f9189q.getText().length() < 5) {
                m11651a((int) C1205R.string.feedsize, (int) XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER);
            } else if (this.f9189q.getText().length() >= XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER) {
                m11651a((int) C1205R.string.feed_max_size, (int) XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER);
            } else {
                this.f9196x.setModel(this.f9171C);
                this.f9195w = ProgressDialog.show(this, null, getString(C1205R.string.commit_ing));
                ao aoVar = new ao(this);
                if (!(this.f9175c == null || this.f9175c.isRecycled())) {
                    aoVar.m9215a(this.f9175c);
                }
                this.f9196x.setUserID(C1236a.m8533b(this).getUserID());
                this.f9196x.setContact(this.f9190r.getText().toString());
                this.f9196x.setContent(this.f9189q.getText().toString());
                aoVar.m9214a(this.f9196x, new ax(this));
            }
        } catch (Exception e) {
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1) {
            if (!(this.f9176d == null || this.f9176d.isRecycled())) {
                this.f9176d.recycle();
                this.f9176d = null;
            }
            getContentResolver();
            try {
                this.f9176d = aa.m7981a((Context) this, intent.getData(), getResources().getInteger(C1205R.integer.image_lagar));
            } catch (IOException e) {
                e.getStackTrace();
            }
            if (this.f9176d != null) {
                this.f9176d = Bitmap.createScaledBitmap(this.f9176d, Opcodes.I2D, 76, true);
                if (i == 0) {
                    this.f9173a.setImageBitmap(this.f9176d);
                    this.f9175c = this.f9176d;
                    this.f9174b.setVisibility(0);
                }
                this.f9176d = null;
            }
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1205R.id.image_add0:
                Intent intent = new Intent("android.intent.action.GET_CONTENT");
                intent.setType("image/*");
                startActivityForResult(intent, 0);
            case C1205R.id.image_del:
                this.f9173a.setImageBitmap(null);
                this.f9174b.setVisibility(4);
            case C1205R.id.sendSuggest:
                if (be.m12370b((Context) this)) {
                    m11660a();
                } else {
                    m11651a((int) C1205R.string.login_result_net, (int) BufferRecycler.DEFAULT_WRITE_CONCAT_BUFFER_LEN);
                }
            default:
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1205R.layout.activity_userfeedback);
        m11654b();
        m11656c();
        be.m12359a(getAssets(), this.f9181i, this.f9182j, this.f9183k, this.f9184l, this.f9185m, this.f9186n, this.f9187o, this.f9188p, this.f9189q, this.f9190r, this.f9178f, this.f9191s, this.f9192t, this.f9193u, this.f9194v);
    }
}
