package com.xiaomi.market.sdk;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* renamed from: com.xiaomi.market.sdk.e */
public class C2533e extends C2532i {
    final /* synthetic */ C2530c f12822G;
    private File f12823H;

    public C2533e(C2530c c2530c, File file) {
        this.f12822G = c2530c;
        super(c2530c, new FileOutputStream(file));
        this.f12823H = file;
    }

    public void reset() {
        try {
            this.S.close();
        } catch (IOException e) {
        }
        this.f12823H.delete();
        try {
            this.S = new FileOutputStream(this.f12823H);
        } catch (FileNotFoundException e2) {
        }
    }
}
