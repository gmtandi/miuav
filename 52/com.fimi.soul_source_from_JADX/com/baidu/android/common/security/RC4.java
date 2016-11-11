package com.baidu.android.common.security;

import com.tencent.mm.sdk.platformtools.Util;

public class RC4 {
    private static final int STATE_LENGTH = 256;
    private byte[] engineState;
    private byte[] workingKey;
    private int f4013x;
    private int f4014y;

    public RC4(String str) {
        this.engineState = null;
        this.f4013x = 0;
        this.f4014y = 0;
        this.workingKey = null;
        this.workingKey = str.getBytes();
    }

    private void processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        if (i + i2 > bArr.length) {
            throw new RuntimeException("input buffer too short");
        } else if (i3 + i2 > bArr2.length) {
            throw new RuntimeException("output buffer too short");
        } else {
            for (int i4 = 0; i4 < i2; i4++) {
                this.f4013x = (this.f4013x + 1) & Util.MASK_8BIT;
                this.f4014y = (this.engineState[this.f4013x] + this.f4014y) & Util.MASK_8BIT;
                byte b = this.engineState[this.f4013x];
                this.engineState[this.f4013x] = this.engineState[this.f4014y];
                this.engineState[this.f4014y] = b;
                bArr2[i4 + i3] = (byte) (bArr[i4 + i] ^ this.engineState[(this.engineState[this.f4013x] + this.engineState[this.f4014y]) & Util.MASK_8BIT]);
            }
        }
    }

    private void reset() {
        setKey(this.workingKey);
    }

    private void setKey(byte[] bArr) {
        int i;
        int i2 = 0;
        this.f4013x = 0;
        this.f4014y = 0;
        if (this.engineState == null) {
            this.engineState = new byte[STATE_LENGTH];
        }
        for (i = 0; i < STATE_LENGTH; i++) {
            this.engineState[i] = (byte) i;
        }
        i = 0;
        int i3 = 0;
        while (i2 < STATE_LENGTH) {
            i = (i + ((bArr[i3] & Util.MASK_8BIT) + this.engineState[i2])) & Util.MASK_8BIT;
            byte b = this.engineState[i2];
            this.engineState[i2] = this.engineState[i];
            this.engineState[i] = b;
            i3 = (i3 + 1) % bArr.length;
            i2++;
        }
    }

    public byte[] decrypt(byte[] bArr) {
        reset();
        byte[] bArr2 = new byte[bArr.length];
        processBytes(bArr, 0, bArr.length, bArr2, 0);
        return bArr2;
    }

    public byte[] encrypt(byte[] bArr) {
        reset();
        byte[] bArr2 = new byte[bArr.length];
        processBytes(bArr, 0, bArr.length, bArr2, 0);
        return bArr2;
    }
}
