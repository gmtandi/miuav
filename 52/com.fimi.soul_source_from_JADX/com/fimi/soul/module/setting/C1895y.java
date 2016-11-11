package com.fimi.soul.module.setting;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.fimi.soul.C1205R;
import java.io.File;
import org.codehaus.jackson.util.MinimalPrettyPrinter;

/* renamed from: com.fimi.soul.module.setting.y */
class C1895y implements OnClickListener {
    final /* synthetic */ aa f9628a;
    final /* synthetic */ C1894x f9629b;

    C1895y(C1894x c1894x, aa aaVar) {
        this.f9629b = c1894x;
        this.f9628a = aaVar;
    }

    public void onClick(View view) {
        if (this.f9629b.f9624c != null && this.f9628a.m11671d() == 0) {
            String e = this.f9628a.m11672e();
            if (e == null || Double.valueOf(e.split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR)[0]).doubleValue() <= 0.0d) {
                ((TextView) this.f9629b.f9625d.findViewWithTag("percent_" + this.f9628a.m11669c())).setText(C1205R.string.inabvility);
            } else {
                this.f9629b.f9624c.m9235a(new File(this.f9628a.m11669c()));
            }
        }
    }
}
