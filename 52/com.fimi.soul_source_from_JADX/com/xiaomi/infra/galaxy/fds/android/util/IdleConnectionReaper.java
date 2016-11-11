package com.xiaomi.infra.galaxy.fds.android.util;

import com.tencent.mm.sdk.platformtools.Util;
import java.util.concurrent.TimeUnit;
import org.apache.http.conn.ClientConnectionManager;

public final class IdleConnectionReaper extends Thread {
    private static final int PERIOD_MILLISECONDS = 60000;
    private final ClientConnectionManager connectionManager;

    public IdleConnectionReaper(ClientConnectionManager clientConnectionManager) {
        super("java-sdk-http-connection-reaper");
        this.connectionManager = clientConnectionManager;
        setDaemon(true);
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(Util.MILLSECONDS_OF_MINUTE);
                this.connectionManager.closeIdleConnections(60, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
