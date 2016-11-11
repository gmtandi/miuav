package com.fimi.soul.media.gallery;

class ab implements Runnable {
    final /* synthetic */ HorizontalListView f7785a;

    ab(HorizontalListView horizontalListView) {
        this.f7785a = horizontalListView;
    }

    public void run() {
        this.f7785a.requestLayout();
    }
}
