package com.fimi.kernel.p076b.p080d;

import com.fimi.kernel.p076b.p079c.C1133a;
import com.xiaomi.infra.galaxy.fds.android.FDSClientConfiguration;
import org.codehaus.jackson.util.MinimalPrettyPrinter;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.kernel.b.d.d */
class C1141d implements Runnable {
    final /* synthetic */ C1140c f5200a;

    C1141d(C1140c c1140c) {
        this.f5200a = c1140c;
    }

    public void run() {
        byte[] bArr = new byte[this.f5200a.f5192b.m7897e()];
        do {
            if (this.f5200a.f5193c.isConnected()) {
                while (true) {
                    this.f5200a.f5198i = false;
                    int read = this.f5200a.f5195e.read(bArr);
                    if (read == -1) {
                        break;
                    } else if (this.f5200a.f5197h.size() > 0) {
                        for (C1133a a : this.f5200a.f5197h) {
                            a.m7858a(read, bArr);
                        }
                    } else {
                        try {
                            String str = new String(bArr, 0, read, this.f5200a.f5192b.m7898f());
                            if (this.f5200a.f5192b.m7896d()) {
                                this.f5200a.m7870c(str);
                            } else {
                                String replace = str.replace(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, C2915a.f14760f);
                                if (replace.startsWith("{\"rval\":") || replace.startsWith("{\"msg_id\"")) {
                                    this.f5200a.f5199j.delete(0, this.f5200a.f5199j.length());
                                    this.f5200a.f5191a = true;
                                }
                                if (this.f5200a.f5191a) {
                                    this.f5200a.f5199j.append(str);
                                    if (this.f5200a.f5199j.toString().contains(":1282") && (this.f5200a.f5199j.length() > FDSClientConfiguration.DEFAULT_SOCKET_TIMEOUT_MS || str.endsWith("]}"))) {
                                        str = this.f5200a.f5199j.toString();
                                        if (str.length() > FDSClientConfiguration.DEFAULT_SOCKET_TIMEOUT_MS) {
                                            str = str.substring(0, FDSClientConfiguration.DEFAULT_SOCKET_TIMEOUT_MS);
                                            str = str.substring(0, str.lastIndexOf("}") + 1) + "]}";
                                        }
                                        this.f5200a.m7870c(str);
                                        this.f5200a.f5199j.delete(0, this.f5200a.f5199j.length());
                                    } else if (!this.f5200a.f5199j.toString().contains(":1282") && str.endsWith("}")) {
                                        this.f5200a.m7870c(this.f5200a.f5199j.toString());
                                        this.f5200a.f5199j.delete(0, this.f5200a.f5199j.length());
                                    }
                                }
                            }
                        } catch (Exception e) {
                            this.f5200a.f5198i = true;
                            e.printStackTrace();
                            return;
                        }
                    }
                }
            }
        } while (!this.f5200a.f5198i);
    }
}
