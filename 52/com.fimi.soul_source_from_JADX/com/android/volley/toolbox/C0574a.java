package com.android.volley.toolbox;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerFuture;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.android.volley.C0553a;

/* renamed from: com.android.volley.toolbox.a */
public class C0574a implements C0573b {
    private final AccountManager f3580a;
    private final Account f3581b;
    private final String f3582c;
    private final boolean f3583d;

    C0574a(AccountManager accountManager, Account account, String str, boolean z) {
        this.f3580a = accountManager;
        this.f3581b = account;
        this.f3582c = str;
        this.f3583d = z;
    }

    public C0574a(Context context, Account account, String str) {
        this(context, account, str, false);
    }

    public C0574a(Context context, Account account, String str, boolean z) {
        this(AccountManager.get(context), account, str, z);
    }

    public Account m5143a() {
        return this.f3581b;
    }

    public void m5144a(String str) {
        this.f3580a.invalidateAuthToken(this.f3581b.type, str);
    }

    public String m5145b() {
        String str = null;
        AccountManagerFuture authToken = this.f3580a.getAuthToken(this.f3581b, this.f3582c, this.f3583d, null, null);
        try {
            Bundle bundle = (Bundle) authToken.getResult();
            if (authToken.isDone() && !authToken.isCancelled()) {
                if (bundle.containsKey("intent")) {
                    throw new C0553a((Intent) bundle.getParcelable("intent"));
                }
                str = bundle.getString("authtoken");
            }
            if (str != null) {
                return str;
            }
            throw new C0553a("Got null auth token for type: " + this.f3582c);
        } catch (Exception e) {
            throw new C0553a("Error while retrieving auth token", e);
        }
    }
}
