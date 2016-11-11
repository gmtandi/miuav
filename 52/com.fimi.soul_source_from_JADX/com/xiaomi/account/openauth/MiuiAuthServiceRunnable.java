package com.xiaomi.account.openauth;

import android.accounts.Account;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import com.xiaomi.account.IXiaomiAuthService;
import com.xiaomi.account.IXiaomiAuthService.Stub;
import p010c.p011a.C0147a;
import p010c.p011a.C0148b;

abstract class MiuiAuthServiceRunnable<V> extends XiaomiOAuthRunnable<V> implements ServiceConnection {
    private static final String ACTION_FOR_AUTH_SERVICE = "android.intent.action.XIAOMI_ACCOUNT_AUTHORIZE";
    private static final String PACKAGE_NAME_FOR_AUTH_SERVICE = "com.xiaomi.account";
    protected final Account account;
    private final Context context;
    protected final Bundle options;

    class NoMiuiAuthServiceException extends Exception {
        NoMiuiAuthServiceException() {
        }
    }

    MiuiAuthServiceRunnable(Context context, Account account, Bundle bundle) {
        this.context = context;
        this.account = account;
        this.options = bundle;
    }

    private static Intent getAuthServiceIntent() {
        Intent intent = new Intent(ACTION_FOR_AUTH_SERVICE);
        if (VERSION.SDK_INT >= 21) {
            intent.setPackage(PACKAGE_NAME_FOR_AUTH_SERVICE);
        }
        return intent;
    }

    public final void doRun() {
        if (!this.context.bindService(getAuthServiceIntent(), this, 1)) {
            this.context.unbindService(this);
            this.mFuture.setException(new NoMiuiAuthServiceException());
        }
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        try {
            this.mFuture.set(talkWithMiuiV6(Stub.asInterface(iBinder)));
            this.context.unbindService(this);
        } catch (SecurityException e) {
            try {
                this.mFuture.set(talkWithMiuiV5(C0148b.m389a(iBinder)));
                this.context.unbindService(this);
            } catch (SecurityException e2) {
                try {
                    this.mFuture.setException(new NoMiuiAuthServiceException());
                } catch (Throwable e3) {
                    this.mFuture.setException(e3);
                } finally {
                    this.context.unbindService(this);
                }
            }
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
    }

    protected abstract V talkWithMiuiV5(C0147a c0147a);

    protected abstract V talkWithMiuiV6(IXiaomiAuthService iXiaomiAuthService);
}
