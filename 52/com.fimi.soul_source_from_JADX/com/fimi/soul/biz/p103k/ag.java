package com.fimi.soul.biz.p103k;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.fimi.kernel.C1189f;
import com.fimi.soul.C1205R;
import com.fimi.soul.entity.ShareInfo;
import com.fimi.soul.utils.be;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.PluginIntent;

/* renamed from: com.fimi.soul.biz.k.ag */
class ag extends AlertDialog {
    ImageView f6042a;
    ImageView f6043b;
    ImageView f6044c;
    Button f6045d;
    final /* synthetic */ ac f6046e;
    private Context f6047f;
    private boolean f6048g;
    private boolean f6049h;

    public ag(ac acVar, Context context, ShareInfo shareInfo) {
        this.f6046e = acVar;
        super(context);
        this.f6047f = context;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1205R.layout.dialog_share);
        TextView textView = (TextView) findViewById(C1205R.id.share_tv);
        this.f6042a = (ImageView) findViewById(C1205R.id.wechat_iv);
        this.f6042a.setOnClickListener(new ah(this));
        this.f6043b = (ImageView) findViewById(C1205R.id.wechat_friend_iv);
        this.f6043b.setOnClickListener(new ai(this));
        this.f6044c = (ImageView) findViewById(C1205R.id.sinaweibo_iv);
        this.f6044c.setOnClickListener(new aj(this));
        this.f6045d = (Button) findViewById(C1205R.id.cancle_btn);
        this.f6045d.setOnClickListener(new ak(this));
        be.m12358a(C1189f.m8327a().getResources().getAssets(), (ViewGroup) getWindow().getDecorView());
    }

    protected void onStart() {
        super.onStart();
        if (be.m12379d(this.f6047f, PluginIntent.APP_PACKAGE_PATTERN)) {
            this.f6048g = true;
            this.f6042a.setAlpha(C2020f.f10933c);
            this.f6043b.setAlpha(C2020f.f10933c);
        } else {
            this.f6048g = false;
            this.f6042a.setAlpha(0.3f);
            this.f6043b.setAlpha(0.3f);
        }
        if (be.m12379d(this.f6047f, "com.sina.weibo")) {
            this.f6049h = true;
            this.f6044c.setAlpha(C2020f.f10933c);
            return;
        }
        this.f6049h = false;
        this.f6044c.setAlpha(0.3f);
    }
}
