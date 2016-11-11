package it.sephiroth.android.library.widget;

class aq implements Runnable {
    final /* synthetic */ HListView f14482a;
    private int f14483b;
    private int f14484c;

    private aq(HListView hListView) {
        this.f14482a = hListView;
    }

    public aq m16306a(int i, int i2) {
        this.f14483b = i;
        this.f14484c = i2;
        return this;
    }

    public void run() {
        this.f14482a.m16245j(this.f14483b, this.f14484c);
    }
}
