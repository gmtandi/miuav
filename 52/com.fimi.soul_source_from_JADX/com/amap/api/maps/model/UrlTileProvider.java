package com.amap.api.maps.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

public abstract class UrlTileProvider implements TileProvider {
    private final int f1874a;
    private final int f1875b;

    public UrlTileProvider(int i, int i2) {
        this.f1874a = i;
        this.f1875b = i2;
    }

    private static long m3172a(InputStream inputStream, OutputStream outputStream) {
        byte[] bArr = new byte[Opcodes.ACC_SYNTHETIC];
        long j = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return j;
            }
            outputStream.write(bArr, 0, read);
            j += (long) read;
        }
    }

    private static byte[] m3173a(InputStream inputStream) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        m3172a(inputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public final Tile getTile(int i, int i2, int i3) {
        URL tileUrl = getTileUrl(i, i2, i3);
        if (tileUrl == null) {
            return NO_TILE;
        }
        try {
            return new Tile(this.f1874a, this.f1875b, m3173a(tileUrl.openStream()));
        } catch (IOException e) {
            return NO_TILE;
        }
    }

    public int getTileHeight() {
        return this.f1875b;
    }

    public abstract URL getTileUrl(int i, int i2, int i3);

    public int getTileWidth() {
        return this.f1874a;
    }
}
