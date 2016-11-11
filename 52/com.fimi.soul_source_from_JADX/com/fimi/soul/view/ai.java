package com.fimi.soul.view;

class ai implements Runnable {
    final /* synthetic */ HorizontalListView f10668a;

    ai(HorizontalListView horizontalListView) {
        this.f10668a = horizontalListView;
    }

    public void run() {
        this.f10668a.requestLayout();
    }
}
