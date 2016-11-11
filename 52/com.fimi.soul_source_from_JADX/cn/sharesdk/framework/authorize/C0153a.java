package cn.sharesdk.framework.authorize;

import android.content.Context;
import android.content.Intent;
import com.mob.tools.FakeActivity;

/* renamed from: cn.sharesdk.framework.authorize.a */
public class C0153a extends FakeActivity {
    protected AuthorizeHelper f198a;

    public AuthorizeHelper m446a() {
        return this.f198a;
    }

    public void m447a(AuthorizeHelper authorizeHelper) {
        this.f198a = authorizeHelper;
        super.show(authorizeHelper.getPlatform().getContext(), null);
    }

    public void show(Context context, Intent intent) {
        throw new RuntimeException("This method is deprecated, use show(AuthorizeHelper, Intent) instead");
    }
}
