package com.fimi.soul.module.login;

/* renamed from: com.fimi.soul.module.login.n */
class C1802n implements Runnable {
    final /* synthetic */ LoginActivity f8858a;

    C1802n(LoginActivity loginActivity) {
        this.f8858a = loginActivity;
    }

    public void run() {
        if (this.f8858a.f8810z == 0) {
            this.f8858a.f8802i.m12659a();
            this.f8858a.f8803j.setVisibility(4);
            this.f8858a.f8802i.setVisibility(0);
            return;
        }
        if (this.f8858a.f8794a) {
            this.f8858a.f8807p.start();
            this.f8858a.f8808q.setVisibility(0);
            this.f8858a.f8805n.setVisibility(0);
            this.f8858a.getFragmentManager().beginTransaction().remove(this.f8858a.f8799f).commitAllowingStateLoss();
            this.f8858a.f8794a = false;
        }
        this.f8858a.f8803j.setVisibility(0);
        this.f8858a.f8802i.setVisibility(4);
        this.f8858a.f8803j.setting(this.f8858a.f8804k);
        this.f8858a.f8804k = this.f8858a.f8804k + LoginActivity.f8778u;
        if (this.f8858a.f8804k == 8) {
            this.f8858a.f8804k = 0;
            this.f8858a.f8790K.postDelayed(this.f8858a.f8792M, 600);
            return;
        }
        this.f8858a.f8790K.postDelayed(this.f8858a.f8792M, 200);
    }
}
