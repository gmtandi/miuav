package com.fimi.soul.utils;

class av implements Runnable {
    final /* synthetic */ au f10056a;

    av(au auVar) {
        this.f10056a = auVar;
    }

    public void run() {
        this.f10056a.f10051e = this.f10056a.f10051e - 1;
        if (this.f10056a.f10051e >= 0) {
            this.f10056a.f10055i.postDelayed(this, 1000);
        } else if (this.f10056a.f10049c != null) {
            this.f10056a.f10049c.m8983a();
        }
    }
}
