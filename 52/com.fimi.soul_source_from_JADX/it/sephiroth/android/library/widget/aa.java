package it.sephiroth.android.library.widget;

class aa implements Runnable {
    final /* synthetic */ AdapterView f14407a;

    private aa(AdapterView adapterView) {
        this.f14407a = adapterView;
    }

    public void run() {
        if (!this.f14407a.aJ) {
            this.f14407a.m16060a();
            this.f14407a.m16063b();
        } else if (this.f14407a.getAdapter() != null) {
            this.f14407a.post(this);
        }
    }
}
