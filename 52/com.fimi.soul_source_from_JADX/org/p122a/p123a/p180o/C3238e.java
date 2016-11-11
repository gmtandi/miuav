package org.p122a.p123a.p180o;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

/* renamed from: org.a.a.o.e */
public final class C3238e {
    public static void m17909a(StringBuilder stringBuilder, SocketAddress socketAddress) {
        C3234a.m17886a((Object) stringBuilder, "Buffer");
        C3234a.m17886a((Object) socketAddress, "Socket address");
        if (socketAddress instanceof InetSocketAddress) {
            InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddress;
            Object address = inetSocketAddress.getAddress();
            if (address != null) {
                address = address.getHostAddress();
            }
            stringBuilder.append(address).append(':').append(inetSocketAddress.getPort());
            return;
        }
        stringBuilder.append(socketAddress);
    }
}
