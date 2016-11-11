package com.fimi.soul.module.droneFragment;

import android.graphics.Point;
import android.view.Display;
import android.widget.RelativeLayout.LayoutParams;
import java.util.Observable;
import java.util.Observer;

class ao implements Observer {
    final /* synthetic */ ShowDroneStatusLineFragment f8183a;

    ao(ShowDroneStatusLineFragment showDroneStatusLineFragment) {
        this.f8183a = showDroneStatusLineFragment;
    }

    public void update(Observable observable, Object obj) {
        Point point = new Point();
        Display defaultDisplay = this.f8183a.getActivity().getWindow().getWindowManager().getDefaultDisplay();
        defaultDisplay.getSize(point);
        if (this.f8183a.f8065C.isShown() || this.f8183a.f8088o.isShown()) {
            LayoutParams layoutParams = (LayoutParams) this.f8183a.f8087n.getLayoutParams();
            layoutParams.addRule(0, this.f8183a.f8091r.getId());
            layoutParams.setMargins((int) (((double) defaultDisplay.getHeight()) * 0.01851d), (int) (((double) defaultDisplay.getHeight()) * 0.036d), (int) (((double) defaultDisplay.getHeight()) * 0.01851d), 0);
            layoutParams.addRule(1, this.f8183a.f8093t.getId());
            this.f8183a.f8087n.setLayoutParams(layoutParams);
            return;
        }
        layoutParams = (LayoutParams) this.f8183a.f8087n.getLayoutParams();
        layoutParams.addRule(0, this.f8183a.f8092s.getId());
        layoutParams.setMargins((int) (((double) defaultDisplay.getHeight()) * 0.01851d), (int) (((double) defaultDisplay.getHeight()) * 0.036d), (int) (((double) defaultDisplay.getHeight()) * 0.01851d), 0);
        layoutParams.addRule(1, this.f8183a.f8093t.getId());
        this.f8183a.f8087n.setLayoutParams(layoutParams);
    }
}
