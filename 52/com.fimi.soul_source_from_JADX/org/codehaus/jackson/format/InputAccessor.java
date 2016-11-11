package org.codehaus.jackson.format;

import java.io.EOFException;
import java.io.InputStream;
import org.codehaus.jackson.JsonFactory;

public interface InputAccessor {

    public class Std implements InputAccessor {
        protected final byte[] _buffer;
        protected int _bufferedAmount;
        protected final InputStream _in;
        protected int _ptr;

        public Std(InputStream inputStream, byte[] bArr) {
            this._in = inputStream;
            this._buffer = bArr;
            this._bufferedAmount = 0;
        }

        public Std(byte[] bArr) {
            this._in = null;
            this._buffer = bArr;
            this._bufferedAmount = bArr.length;
        }

        public DataFormatMatcher createMatcher(JsonFactory jsonFactory, MatchStrength matchStrength) {
            return new DataFormatMatcher(this._in, this._buffer, this._bufferedAmount, jsonFactory, matchStrength);
        }

        public boolean hasMoreBytes() {
            if (this._ptr < this._bufferedAmount) {
                return true;
            }
            int length = this._buffer.length - this._ptr;
            if (length < 1) {
                return false;
            }
            length = this._in.read(this._buffer, this._ptr, length);
            if (length <= 0) {
                return false;
            }
            this._bufferedAmount += length;
            return true;
        }

        public byte nextByte() {
            if (this._ptr <= (-this._bufferedAmount) || hasMoreBytes()) {
                byte[] bArr = this._buffer;
                int i = this._ptr;
                this._ptr = i + 1;
                return bArr[i];
            }
            throw new EOFException("Could not read more than " + this._ptr + " bytes (max buffer size: " + this._buffer.length + ")");
        }

        public void reset() {
            this._ptr = 0;
        }
    }

    boolean hasMoreBytes();

    byte nextByte();

    void reset();
}
