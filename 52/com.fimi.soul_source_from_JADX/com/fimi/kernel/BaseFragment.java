package com.fimi.kernel;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fimi.kernel.p082c.C1156a;
import com.fimi.kernel.p084e.ah;
import com.fimi.kernel.view.C1192b;

public abstract class BaseFragment extends Fragment {
    private Handler f5057a;

    public C1192b m7661a() {
        return C1189f.m8331b();
    }

    public void m7662a(Message message) {
    }

    public void m7663a(Runnable runnable) {
        ah.m8075a(runnable);
    }

    public C1156a m7664b() {
        return C1189f.m8333c();
    }

    protected Handler m7665c() {
        return this.f5057a;
    }

    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        this.f5057a = new C1158c(this);
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }
}
