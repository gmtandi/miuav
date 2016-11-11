package org.codehaus.jackson.io;

import java.io.Writer;
import org.codehaus.jackson.util.BufferRecycler;
import org.codehaus.jackson.util.TextBuffer;

public final class SegmentedStringWriter extends Writer {
    protected final TextBuffer _buffer;

    public SegmentedStringWriter(BufferRecycler bufferRecycler) {
        this._buffer = new TextBuffer(bufferRecycler);
    }

    public Writer append(char c) {
        write((int) c);
        return this;
    }

    public Writer append(CharSequence charSequence) {
        String obj = charSequence.toString();
        this._buffer.append(obj, 0, obj.length());
        return this;
    }

    public Writer append(CharSequence charSequence, int i, int i2) {
        String obj = charSequence.subSequence(i, i2).toString();
        this._buffer.append(obj, 0, obj.length());
        return this;
    }

    public void close() {
    }

    public void flush() {
    }

    public String getAndClear() {
        String contentsAsString = this._buffer.contentsAsString();
        this._buffer.releaseBuffers();
        return contentsAsString;
    }

    public void write(int i) {
        this._buffer.append((char) i);
    }

    public void write(String str) {
        this._buffer.append(str, 0, str.length());
    }

    public void write(String str, int i, int i2) {
        this._buffer.append(str, 0, str.length());
    }

    public void write(char[] cArr) {
        this._buffer.append(cArr, 0, cArr.length);
    }

    public void write(char[] cArr, int i, int i2) {
        this._buffer.append(cArr, i, i2);
    }
}
