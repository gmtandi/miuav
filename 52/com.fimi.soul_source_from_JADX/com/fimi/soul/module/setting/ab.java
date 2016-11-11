package com.fimi.soul.module.setting;

import java.util.Observable;
import java.util.Observer;

class ab implements Observer {
    final /* synthetic */ MapSettingFragment f9207a;

    ab(MapSettingFragment mapSettingFragment) {
        this.f9207a = mapSettingFragment;
    }

    public void update(Observable observable, Object obj) {
        if (this.f9207a.f9144h != null) {
            this.f9207a.f9144h.m11688a(this.f9207a.f9146j);
        }
    }
}
