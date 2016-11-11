package com.tencent.mm.sdk.platformtools;

import java.nio.ByteBuffer;
import org.p122a.p123a.C2915a;

public class LVBuffer {
    public static final int LENGTH_ALLOC_PER_NEW = 4096;
    public static final int MAX_STRING_LENGTH = 2048;
    private ByteBuffer ap;
    private boolean aq;

    private int m13537a(int i) {
        if (this.ap.limit() - this.ap.position() <= i) {
            ByteBuffer allocate = ByteBuffer.allocate(this.ap.limit() + LENGTH_ALLOC_PER_NEW);
            allocate.put(this.ap.array(), 0, this.ap.position());
            this.ap = allocate;
        }
        return 0;
    }

    public byte[] buildFinish() {
        if (this.aq) {
            m13537a(1);
            this.ap.put((byte) 125);
            Object obj = new byte[this.ap.position()];
            System.arraycopy(this.ap.array(), 0, obj, 0, obj.length);
            return obj;
        }
        throw new Exception("Buffer For Parse");
    }

    public boolean checkGetFinish() {
        return this.ap.limit() - this.ap.position() <= 1;
    }

    public int getInt() {
        if (!this.aq) {
            return this.ap.getInt();
        }
        throw new Exception("Buffer For Build");
    }

    public long getLong() {
        if (!this.aq) {
            return this.ap.getLong();
        }
        throw new Exception("Buffer For Build");
    }

    public String getString() {
        if (this.aq) {
            throw new Exception("Buffer For Build");
        }
        short s = this.ap.getShort();
        if (s > (short) 2048) {
            this.ap = null;
            throw new Exception("Buffer String Length Error");
        } else if (s == (short) 0) {
            return C2915a.f14760f;
        } else {
            byte[] bArr = new byte[s];
            this.ap.get(bArr, 0, s);
            return new String(bArr);
        }
    }

    public int initBuild() {
        this.ap = ByteBuffer.allocate(LENGTH_ALLOC_PER_NEW);
        this.ap.put((byte) 123);
        this.aq = true;
        return 0;
    }

    public int initParse(byte[] bArr) {
        int i = (bArr == null || bArr.length == 0) ? -1 : bArr[0] != 123 ? -2 : bArr[bArr.length + -1] != 125 ? -3 : 0;
        if (i != 0) {
            this.ap = null;
            throw new Exception("Parse Buffer Check Failed :" + i);
        }
        this.ap = ByteBuffer.wrap(bArr);
        this.ap.position(1);
        this.aq = false;
        return 0;
    }

    public int putInt(int i) {
        if (this.aq) {
            m13537a(4);
            this.ap.putInt(i);
            return 0;
        }
        throw new Exception("Buffer For Parse");
    }

    public int putLong(long j) {
        if (this.aq) {
            m13537a(8);
            this.ap.putLong(j);
            return 0;
        }
        throw new Exception("Buffer For Parse");
    }

    public int putString(String str) {
        if (this.aq) {
            byte[] bArr = null;
            if (str != null) {
                bArr = str.getBytes();
            }
            if (bArr == null) {
                bArr = new byte[0];
            }
            if (bArr.length > MAX_STRING_LENGTH) {
                throw new Exception("Buffer String Length Error");
            }
            m13537a(bArr.length + 2);
            this.ap.putShort((short) bArr.length);
            if (bArr.length > 0) {
                this.ap.put(bArr);
            }
            return 0;
        }
        throw new Exception("Buffer For Parse");
    }
}
